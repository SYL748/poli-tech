import React from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TablePagination,
  Box,
  TableFooter
} from "@mui/material";

interface CustomTableProps {
  columns: string[];
  rows: (string | number | boolean | React.ReactNode)[][];
  rowsPerPage?: number;
  maxChars?: number;
  total?: boolean;
}

const CustomTable: React.FC<CustomTableProps> = ({
  columns,
  rows,
  rowsPerPage = 6,
  maxChars = 15,
  total = true
}) => {
  const firstColumnWidth = 14 * 9;

  const [page, setPage] = React.useState(0);
  const handleChangePage = (_: unknown, newPage: number) => setPage(newPage);

  const headerRef = React.useRef<HTMLTableRowElement>(null);
  const containerRef = React.useRef<HTMLDivElement>(null);

  const [headerHeight, setHeaderHeight] = React.useState(0);
  const [containerHeight, setContainerHeight] = React.useState(0);

  React.useEffect(() => {
    if (!headerRef.current || !containerRef.current) return;

    const updateHeights = () => {
      setHeaderHeight(headerRef.current!.offsetHeight);
      setContainerHeight(containerRef.current!.offsetHeight);
    };

    updateHeights();
    window.addEventListener("resize", updateHeights);
    return () => window.removeEventListener("resize", updateHeights);
  }, []);

  // footer row exists only when total === true
  const footerRowsCount = total ? 1 : 0;

  const rowHeight =
    containerHeight > 0
      ? (containerHeight - headerHeight - 9) / (rowsPerPage + footerRowsCount)
      : undefined;

  const wrapText = (
    text: string | number | boolean | React.ReactNode,
    maxChars = 15
  ) => {
    if (React.isValidElement(text)) return text;

    if (typeof text === "boolean") return text ? "Yes" : "No";
    if (typeof text !== "string") return text;

    const words = text.split(" ");
    const lines: string[] = [];
    let line = "";

    for (const word of words) {
      if ((line + word).length > maxChars) {
        lines.push(line.trim());
        line = word + " ";
      } else {
        line += word + " ";
      }
    }

    if (line.trim()) lines.push(line.trim());
    return lines.join("\n");
  };

  const pageRows = rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

  // only compute totals when enabled
  const columnTotals = React.useMemo(() => {
    if (!total) return [];

    return columns.map((_, colIdx) => {
      if (colIdx === 0) return "Total";

      let sum = 0;
      pageRows.forEach(row => {
        const value = row[colIdx];
        if (typeof value === "number") sum += value;
      });

      return sum;
    });
  }, [total, columns, pageRows]);

  return (
    <Paper
      sx={{
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
        overflow: "hidden"
      }}
    >
      <TableContainer
        ref={containerRef}
        sx={{
          flex: 1,
          width: "100%",
          overflowX: "auto",
          overflowY: "hidden"
        }}
      >
        <Box sx={{ minWidth: "max-content" }}>
          <Table stickyHeader>
            {/* HEADER */}
            <TableHead>
              <TableRow ref={headerRef}>
                {columns.map((col, i) => (
                  <TableCell
                    key={i}
                    sx={{
                      width: i === 0 ? firstColumnWidth : "auto",
                      maxWidth: i === 0 ? firstColumnWidth : "none",
                      fontWeight: 600,
                      bgcolor: "#f9f9f9",
                      textAlign: i === 0 ? "left" : "center",
                      whiteSpace: "pre-wrap",
                      wordBreak: "break-word",
                      borderBottom: "1px solid #ddd",
                      position: i === 0 ? "sticky" : "static",
                      left: i === 0 ? 0 : "auto",
                      zIndex: i === 0 ? 2 : 1,
                      backgroundColor: "#f9f9f9"
                    }}
                  >
                    {wrapText(col, maxChars)}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>

            {/* BODY */}
            <TableBody>
              {pageRows.map((row, rowIdx) => (
                <TableRow
                  key={rowIdx}
                  sx={{
                    "&:nth-of-type(even)": { backgroundColor: "#f5f5f5" },
                    "&:nth-of-type(odd)": { backgroundColor: "#ffffff" },
                    ...(rowHeight ? { height: rowHeight } : {}),
                    "& td": {
                      ...(rowHeight ? { height: rowHeight } : {}),
                      padding: "8px",
                      lineHeight: "1.2"
                    }
                  }}
                >
                  {row.map((cell, cellIdx) => (
                    <TableCell
                      key={cellIdx}
                      align={cellIdx === 0 ? "left" : "center"}
                      sx={{
                        whiteSpace: "pre-wrap",
                        wordBreak: "break-word",
                        borderBottom: "1px solid #eee",
                        position: cellIdx === 0 ? "sticky" : "static",
                        left: cellIdx === 0 ? 0 : "auto",
                        zIndex: cellIdx === 0 ? 1 : "auto",
                        backgroundColor: "inherit"
                      }}
                    >
                      {wrapText(
                        typeof cell === "number"
                          ? cell.toLocaleString("en-US")
                          : cell,
                        maxChars
                      )}
                    </TableCell>
                  ))}
                </TableRow>
              ))}
            </TableBody>

            {/* FOOTER TOTALS (optional) */}
            {total && (
              <TableFooter>
                <TableRow
                  sx={{
                    fontWeight: 600,
                    ...(rowHeight ? { height: rowHeight } : {}),
                    "& td": {
                      ...(rowHeight ? { height: rowHeight } : {}),
                      padding: "8px"
                    }
                  }}
                >
                  {columnTotals.map((t, i) => (
                    <TableCell
                      key={i}
                      align={i === 0 ? "left" : "center"}
                      sx={{
                        color: "black",
                        position: i === 0 ? "sticky" : "static",
                        left: i === 0 ? 0 : "auto",
                        zIndex: i === 0 ? 2 : 1,

                        fontSize: "0.875rem",
                        fontWeight: 400,
                        lineHeight: 1.43,
                        fontFamily: "Roboto, sans-serif",

                        borderTop: "1px solid black",
                        backgroundColor: "white",
                        whiteSpace: "pre-wrap",
                        wordBreak: "break-word"
                      }}
                    >
                      {typeof t === "number" ? t.toLocaleString("en-US") : t}
                    </TableCell>
                  ))}
                </TableRow>
              </TableFooter>
            )}
          </Table>
        </Box>
      </TableContainer>

      {/* PAGINATION */}
      <TablePagination
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        rowsPerPageOptions={[rowsPerPage]}
        page={page}
        onPageChange={handleChangePage}
      />
    </Paper>
  );
};

export default CustomTable;
