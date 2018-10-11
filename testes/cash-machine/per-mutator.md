# Effects per Mutator

| Mutator | Mutations | Killed | Escaped | Errors | Timed Out | MSI | Covered MSI |
| ------- | --------- | ------ | ------- |------- | --------- | --- | ----------- |
| Assignment | 1 | 0 | 1 | 0 | 0 | 0| 0|
| DecrementInteger | 1 | 0 | 1 | 0 | 0 | 0| 0|
| Foreach_ | 1 | 1 | 0 | 0 | 0 | 100| 100|
| FunctionCallRemoval | 1 | 0 | 1 | 0 | 0 | 0| 0|
| GreaterThan | 1 | 0 | 1 | 0 | 0 | 0| 0|
| GreaterThanNegotiation | 1 | 0 | 0 | 0 | 1 | 100| 100|
| GreaterThanOrEqualTo | 1 | 1 | 0 | 0 | 0 | 100| 100|
| GreaterThanOrEqualToNegotiation | 1 | 1 | 0 | 0 | 0 | 100| 100|
| LessThan | 2 | 1 | 1 | 0 | 0 | 50| 50|
| LessThanNegotiation | 2 | 2 | 0 | 0 | 0 | 100| 100|
| LogicalNot | 1 | 1 | 0 | 0 | 0 | 100| 100|
| MethodCallRemoval | 1 | 0 | 1 | 0 | 0 | 0| 0|
| MinusEqual | 1 | 0 | 0 | 0 | 1 | 100| 100|
| OneZeroInteger | 1 | 0 | 1 | 0 | 0 | 0| 0|
| Throw_ | 3 | 3 | 0 | 0 | 0 | 100| 100|