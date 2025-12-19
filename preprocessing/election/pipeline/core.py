"""
Pipeline Core Module

Defines the base DataNode class, node registry, and Pipeline executor.
Uses class-based registry for type safety and IDE autocomplete.
"""

from abc import ABC, abstractmethod
from typing import Optional, Dict, List, Set, Type
from pathlib import Path
import pandas as pd


NODE_REGISTRY: Dict[Type['DataNode'], Type['DataNode']] = {}


def register_node(cls: Type['DataNode']) -> Type['DataNode']:
    if cls in NODE_REGISTRY:
        raise ValueError(f"Node '{cls.__name__}' is already registered")
    NODE_REGISTRY[cls] = cls
    return cls


class DataNode(ABC):
    """
    Abstract base class for pipeline data nodes. Should be treated as a node in a dependency graph.

    Attributes:
        dependencies: List of node classes this node depends on
        output_filename: CSV filename for export, or None for intermediate nodes
    """

    dependencies: List[Type['DataNode']] = []
    output_filename: Optional[str] = None

    def __init__(self, data_dir: str = '.'):
        self.data_dir = Path(data_dir)

    @abstractmethod
    def process(self, inputs: Dict[Type['DataNode'], pd.DataFrame]) -> pd.DataFrame:
        """
        Process input DataFrames to produce output DataFrame.

        Args:
            inputs: Dictionary mapping dependency node classes to their output DataFrames

        Returns:
            Output DataFrame

        Raises:
            Exception: Any error during processing will halt the pipeline
        """
        pass

    def should_export(self) -> bool:
        """Check if this node should export to CSV."""
        return self.output_filename is not None


class Pipeline:
    """
    Pipeline executor for running nodes in dependency order.

    Performs topological sort of the dependency graph and executes nodes
    in the correct order. Uses fail-fast error handling - any node failure
    halts the entire pipeline.
    """

    def __init__(self, output_dir: str = './output', data_dir: str = '.'):
        """
        Initialize the pipeline.

        Args:
            output_dir: Directory for CSV exports
            data_dir: Base directory for input data files
        """
        self.output_dir = Path(output_dir)
        self.data_dir = Path(data_dir)
        self.output_dir.mkdir(parents=True, exist_ok=True)

        # Cache for executed node results
        self._cache: Dict[Type[DataNode], pd.DataFrame] = {}

    def run(self, nodes: Optional[List[Type[DataNode]]] = None) -> None:
        """
        Run the pipeline for specified nodes.

        Args:
            nodes: List of node classes to run, or None to run all exportable nodes

        Raises:
            ValueError: If a node is not registered or has invalid dependencies
            Exception: If any node fails during execution
        """
        # Determine which nodes to run
        if nodes is None:
            # Run all exportable nodes
            target_nodes = [
                cls for cls in NODE_REGISTRY.values()
                if cls.output_filename is not None
            ]
        else:
            target_nodes = list(nodes)

        # Validate all target nodes exist
        for cls in target_nodes:
            if cls not in NODE_REGISTRY:
                raise ValueError(f"Node '{cls.__name__}' is not registered")

        # Collect all nodes needed (including dependencies)
        all_needed = self._collect_dependencies(target_nodes)

        # Topologically sort
        sorted_nodes = self._topological_sort(all_needed)

        # Execute nodes in order
        print(f"Pipeline: Running {len(sorted_nodes)} nodes...")
        for node_cls in sorted_nodes:
            self._execute_node(node_cls)

        print(f"Pipeline: Complete! Processed {len(sorted_nodes)} nodes.")

    def run_node(self, node_cls: Type[DataNode]) -> pd.DataFrame:
        """
        Run a single node and its dependencies.

        Args:
            node_cls: Node class to run

        Returns:
            Output DataFrame from the specified node

        Raises:
            ValueError: If the node is not registered
            Exception: If any node fails during execution
        """
        if node_cls not in NODE_REGISTRY:
            raise ValueError(f"Node '{node_cls.__name__}' is not registered")

        # Collect all dependencies
        all_needed = self._collect_dependencies([node_cls])

        # Topologically sort
        sorted_nodes = self._topological_sort(all_needed)

        # Execute nodes in order
        print(f"Pipeline: Running node '{node_cls.__name__}' (with {len(sorted_nodes)} total nodes)...")
        for cls in sorted_nodes:
            self._execute_node(cls)

        print(f"Pipeline: Complete!")
        return self._cache[node_cls]

    @staticmethod
    def list_nodes() -> None:
        """Print all registered nodes with their metadata."""
        print(f"\nRegistered Nodes ({len(NODE_REGISTRY)}):")
        print("=" * 80)

        # Separate into source and processing nodes
        source_nodes = []
        intermediate_nodes = []
        export_nodes = []

        for cls in sorted(NODE_REGISTRY.values(), key=lambda c: c.__name__):
            if not cls.dependencies:
                source_nodes.append(cls)
            elif cls.output_filename is None:
                intermediate_nodes.append(cls)
            else:
                export_nodes.append(cls)

        if source_nodes:
            print("\nSource Nodes (no dependencies):")
            for cls in source_nodes:
                print(f"  - {cls.__name__}")

        if intermediate_nodes:
            print("\nIntermediate Nodes (not exported):")
            for cls in intermediate_nodes:
                deps = ", ".join(d.__name__ for d in cls.dependencies)
                print(f"  - {cls.__name__} (depends: {deps})")

        if export_nodes:
            print("\nExport Nodes:")
            for cls in export_nodes:
                deps = ", ".join(d.__name__ for d in cls.dependencies)
                print(f"  - {cls.__name__} → {cls.output_filename}")
                print(f"    (depends: {deps})")

        print()

    @staticmethod
    def _collect_dependencies(target_nodes: List[Type[DataNode]]) -> Set[Type[DataNode]]:
        """
        Collect all nodes needed to run target nodes (including transitive dependencies).

        Args:
            target_nodes: List of target node classes

        Returns:
            Set of all node classes needed

        Raises:
            ValueError: If a dependency is not registered
        """
        needed: Set[Type[DataNode]] = set()
        stack = list(target_nodes)

        while stack:
            node_cls = stack.pop()
            if node_cls in needed:
                continue

            if node_cls not in NODE_REGISTRY:
                raise ValueError(f"Node '{node_cls.__name__}' is not registered")

            needed.add(node_cls)

            # Add dependencies to stack
            for dep in node_cls.dependencies:
                if dep not in needed:
                    stack.append(dep)

        return needed

    @staticmethod
    def _topological_sort(nodes: Set[Type[DataNode]]) -> List[Type[DataNode]]:
        graph: Dict[Type[DataNode], List[Type[DataNode]]] = {cls: [] for cls in nodes}
        in_degree: Dict[Type[DataNode], int] = {cls: 0 for cls in nodes}
        for node_cls in nodes:
            for dep in node_cls.dependencies:
                if dep in nodes:
                    graph[dep].append(node_cls)
                    in_degree[node_cls] += 1

        # Kahn's algorithm
        queue = [cls for cls in nodes if in_degree[cls] == 0]
        result: List[Type[DataNode]] = []

        while queue:
            # Sort for deterministic order
            queue.sort(key=lambda c: c.__name__)
            node_cls = queue.pop(0)
            result.append(node_cls)

            for neighbor in graph[node_cls]:
                in_degree[neighbor] -= 1
                if in_degree[neighbor] == 0:
                    queue.append(neighbor)

        if len(result) != len(nodes):
            raise ValueError("Circular dependency detected in pipeline")

        return result

    def _execute_node(self, node_cls: Type[DataNode]) -> None:
        """
        Execute a single node.

        Args:
            node_cls: Node class to execute

        Raises:
            Exception: If node execution fails (fail-fast)
        """
        if node_cls in self._cache:
            return
        print(f"  Running: {node_cls.__name__}...")

        inputs: Dict[Type[DataNode], pd.DataFrame] = {}
        for dep_cls in node_cls.dependencies:
            if dep_cls not in self._cache:
                raise RuntimeError(f"Dependency '{dep_cls.__name__}' not in cache for node '{node_cls.__name__}'")
            inputs[dep_cls] = self._cache[dep_cls]

        try:
            node = node_cls(data_dir=str(self.data_dir))
            result = node.process(inputs)

            if not isinstance(result, pd.DataFrame):
                raise TypeError(f"Node '{node_cls.__name__}' must return a pd.DataFrame, got {type(result)}")

            # Cache result
            self._cache[node_cls] = result

            if node.should_export():
                output_path = self.output_dir / node.output_filename
                result.to_csv(output_path, index=True)
                print(f"    Exported: {output_path}")
        except Exception as e:
            print(f"    ERROR: Node '{node_cls.__name__}' failed: {e}")
            raise e

