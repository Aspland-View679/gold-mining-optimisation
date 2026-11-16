# Gold Mining Optimisation

A Java program that calculates the maximum amount of gold that can be collected from a two-dimensional grid with a limited battery budget.

## How it works

The program considers each cell in the top row as a possible starting position. Each battery unit allows one collection action: collect gold from the current cell, the cell immediately to the left or right, or the cell directly below. The program uses recursion with memoisation to find the highest achievable total for the available battery budget.

## Technologies

- Java
- Dynamic programming
- Recursion and memoisation
- Two-dimensional arrays

## Input

Provide space- or newline-separated integers in this order:

1. Battery capacity
2. Grid depth (rows)
3. Grid width (columns)
4. Grid values, row by row

Example:

```text
2
2
3
1 2 3
4 5 6
```

For this example, the program prints `9` (collecting 3 from the top-right cell, then 6 from the cell below).

## Run

Requires a Java Development Kit (JDK).

```bash
javac Submission.java
java Submission
```

Enter the input values on standard input, or redirect them from a text file with `java Submission < input.txt`.

## Implementation notes

- The search starts from any cell in the top row.
- Each collection action consumes one unit of battery.
- The program permits collecting from the same cell on consecutive actions.
- Moves are limited to left, right and down; diagonal and upward moves are not considered.
- The implementation assumes a non-empty grid and a positive battery capacity.
- Memoisation stores results by row, column and remaining battery capacity.

## Potential improvements

- Add input validation and automated tests.
- Handle larger grids and battery budgets more efficiently.
- Record the path taken to achieve the maximum total.
