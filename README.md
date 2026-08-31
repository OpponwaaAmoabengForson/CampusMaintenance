# Campus Maintenance Request Management System

## DCIT 204 – Data Structures and Algorithms I

This project is a Java-based Campus Maintenance Request Management System developed as part of Practical Lab 3 for DCIT 204.

The program manages campus maintenance requests and demonstrates the use of different searching and sorting algorithms.

## Features

The system provides a menu with the following operations:

1. Show original maintenance requests
2. Linear search for a Ticket ID
3. Sort requests by Priority using Selection Sort
4. Sort requests by Ticket ID in ascending order
5. Binary search for a Ticket ID after sorting
6. Find two jobs whose combined minutes match a target
7. Exit the program

## Data Stored

Each maintenance request contains:

- Ticket ID
- Location
- Priority
- Estimated time in minutes

The program uses parallel arrays to store the request information.

## Algorithms Implemented

### 1. Selection Sort

Selection Sort is used to arrange maintenance requests by priority.

When two requests have the same priority, their Ticket IDs are compared and the smaller Ticket ID is placed first.

The program also uses Selection Sort to arrange Ticket IDs in ascending order.

Time Complexity: Θ(n²)  
Auxiliary Space: Θ(1)

### 2. Linear Search

Linear Search checks the Ticket IDs one after another until the requested Ticket ID is found or all elements have been checked.

Best Case: Θ(1)  
Average Case: Θ(n)  
Worst Case: Θ(n)  
Auxiliary Space: Θ(1)

### 3. Binary Search

Binary Search is used to find a Ticket ID after the Ticket IDs have been sorted in ascending order.

The program checks that the IDs have been sorted before allowing Binary Search to run.

Best Case: Θ(1)  
Average Case: Θ(log n)  
Worst Case: Θ(log n)  
Auxiliary Space: Θ(1)

### 4. Exhaustive Pair Search

The program uses two nested loops to find two maintenance jobs whose estimated times add up to a specified target.

For example, using a target of 75 minutes, the program can find matching pairs such as:

- M001 (25 minutes) + M003 (50 minutes)
- M004 (30 minutes) + M009 (45 minutes)
- M006 (35 minutes) + M007 (40 minutes)

Time Complexity: Θ(n²)  
Auxiliary Space: Θ(1)

## Sample Dataset

| Ticket ID | Location | Priority | Minutes |
|-----------|----------|----------|---------|
| M006 | JQB-19 | 2 | 35 |
| M002 | CS-Lab | 1 | 20 |
| M009 | Balme-Library | 3 | 45 |
| M001 | CS-Office | 2 | 25 |
| M004 | Sarbah-Hall | 1 | 30 |
| M008 | Legon-Hall | 4 | 60 |
| M003 | UG-Main-Gate | 3 | 50 |
| M007 | N-Block | 2 | 40 |
| M005 | Night-Market | 5 | 70 |

## Technologies Used

- Java
- IntelliJ IDEA
- Git
- GitHub

## How to Run

1. Clone the repository.

2. Open the project in IntelliJ IDEA or another Java IDE.

3. Compile and run:

```bash
javac CampusMaintenance.java
java CampusMaintenance