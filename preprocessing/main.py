#!/usr/bin/env python3
"""
Election Data Preprocessing CLI

Command-line interface for running the election data pipeline.

Usage:
    python main.py --preset eavs        # Run EAVS preset nodes
    python main.py --preset voter       # Run voter registration preset
    python main.py --preset election    # Run election preset
    python main.py --preset all         # Run all exportable nodes
    python main.py --node RegionsNode   # Run single node (with dependencies)
    python main.py --list-nodes         # List all registered nodes
"""

import argparse
import sys
import traceback

from election import (
    Pipeline,
    NODE_REGISTRY,
    EAVS_PRESET,
    VOTER_REGISTRATION_PRESET,
    ELECTION_PRESET,
    ALL_PRESET,
)


def main():
    parser = argparse.ArgumentParser(
        description='Election Data Preprocessing Pipeline',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples:
  python main.py --preset eavs          Run EAVS processing nodes
  python main.py --preset voter         Run voter registration nodes
  python main.py --preset election      Run election analysis nodes
  python main.py --preset all           Run all exportable nodes
  python main.py --node RegionsNode     Run single node with dependencies
  python main.py --list-nodes           List all available nodes
        """
    )

    parser.add_argument(
        '--preset',
        choices=['eavs', 'voter', 'election', 'all'],
        help='Run a predefined set of nodes'
    )

    parser.add_argument(
        '--node',
        type=str,
        help='Run a single node by class name (with its dependencies)'
    )

    parser.add_argument(
        '--list-nodes',
        action='store_true',
        help='List all registered nodes'
    )

    parser.add_argument(
        '--output-dir',
        type=str,
        default='./output',
        help='Output directory for CSV files (default: ./output)'
    )

    parser.add_argument(
        '--data-dir',
        type=str,
        default='.',
        help='Base directory for input data files (default: .)'
    )

    args = parser.parse_args()

    # Create pipeline
    pipeline = Pipeline(output_dir=args.output_dir, data_dir=args.data_dir)

    # Handle --list-nodes
    if args.list_nodes:
        pipeline.list_nodes()
        return 0

    # Determine which nodes to run
    if args.preset:
        preset_map = {
            'eavs': EAVS_PRESET,
            'voter': VOTER_REGISTRATION_PRESET,
            'election': ELECTION_PRESET,
            'all': ALL_PRESET,
        }
        nodes = preset_map[args.preset]

        print(f"Running preset: {args.preset}")
        if nodes:
            print(f"Nodes: {', '.join(cls.__name__ for cls in nodes)}")
        else:
            print("Nodes: All exportable nodes")

        try:
            pipeline.run(nodes)
            return 0
        except Exception as e:
            print(f"\nPipeline failed: {e}")
            return 1

    elif args.node:
        # Find node class by name
        node_cls = None
        for cls in NODE_REGISTRY.values():
            if cls.__name__ == args.node:
                node_cls = cls
                break

        if node_cls is None:
            print(f"Error: Node '{args.node}' is not registered.")
            print(f"Available nodes: {', '.join(sorted(cls.__name__ for cls in NODE_REGISTRY.values()))}")
            return 1

        try:
            result = pipeline.run_node(node_cls)
            print(f"\nResult preview ({len(result)} rows):")
            print(result.head(10))
            return 0
        except Exception as e:
            print(f"\nNode execution failed: {e}")
            traceback.print_exc()
            return 1

    else:
        parser.print_help()
        return 0


if __name__ == '__main__':
    sys.exit(main())

