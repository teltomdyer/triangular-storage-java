# Triangular Storage
This project provides a function `answer` that returns the id of an item in a triangular storage
structure (example shown below) at column x and row y. Columns start at the left and rows start at the bottom.

The `Main` class exposes `answer` through a CLI that reads pairs of numbers 
from STDIN terminating on `0` and printing the resulting id for each pair of numbers.

###### Triangular storage example
```
|16
|11 17
|7  12  18
|4  8   13  19
|2  5   9   14  20
|1  3   6   10  15  21
```

## How It Works
If recursion is used naively in java, large values for x and y will result in a stack overflow. 
Elixir and other functional languages provide Tail Call Optimization (TCO) java does not. This means 
that recursive calls each allocate a new stack frame in java. Our implementation avoids this problem.

#### Functional Interfaces & Lambda Expressions
In this implementation a functional interface `TailCall` is provided. The abstract function called
`next` returns the next `TailCall` instance.

Instantiating an instance of the functional interface is done in the following ways:
1.  `() -> getNthTriangular(n-1, acc + add, add + 1)`
2. `TailCall.complete()`

The first returns an instance of `TailCall` that returns the next iteration or recursive call 
to `getNthTriangular`. 

The second returns a `TailCall` instance that contains the resulting value of the accumulation.

In this way pending tail calls are stacked up until `TailCall.execute()` is called and
`n` decrements, `acc` accumulates, and `add` increments as they are passed from one pending call 
to another until the completed `TailCall` instance returns the final `acc` value. This method doesn't
require a new stack frame for each method call and uses constant stack space. 
 
## Getting Started

##### Requirements
- Project requires java and gradle.

#### Running the CLI
Recommended to import the project and run with intellij or equivalent 

## Testing
#### Unit testing
Unit tests for the `answer` function and the underlying recursive `getNthTriangular` functions
are found in the `TriangularStorageTest` class and uses the JUnit framework. 

To run the unit tests run `$ gradle test` in the project root directory.

