**Repository Overview**

This project contains various data structure and algorithm implementations written mainly in Java with a few Kotlin classes. Code under `src/` is organized by topic:

```
src/
 ├─ array/            # ordered arrays and binary search
 ├─ hash/             # several hash table variants
 ├─ heap/             # heap and heap-sort logic (Java & Kotlin)
 ├─ linkedlist/       # singly and doubly linked list utilities
 ├─ queue/            # queue, dequeue and priority queue implementations
 ├─ recursion/        # recursive algorithms (Hanoi, Knapsack, etc.)
 ├─ sort/             # sorting algorithms and utilities
 ├─ stack/            # stack operations and expression evaluators
 └─ tree/             # general binary trees, red/black trees, B-trees, etc.
```

The `test/` directory contains JUnit tests. Some tests rely on Cucumber feature files under `resources/test/`. For example, a knapsack problem scenario is specified in `Knapsack.feature`. Most source files include small `main()` demos, which are useful to understand how each structure works.

### Key Components

* **Binary and red/black trees** – `tree/Tree.java`, `tree/RBTree.java` and related node classes implement balanced trees. The RB-tree supports insert and validation but the delete operation is not implemented yet.
* **Multiway trees** – `tree/btree` provides a B-tree of configurable order and a specialized 2-3 tree. Node manipulation uses `MultiNode`, which keeps arrays of children and keys. There is a TODO regarding item count management when inserting items.
* **Hash tables** – Several implementations exist (open addressing, chaining, digit folding, etc.) in `src/hash/`.
* **Sorting and searching** – Classes under `src/sort` and `src/array` implement quicksort variants, radix sort, insertion/selection/shell sorts, and binary search arrays.
* **Recursion examples** – `src/recursion` includes classical problems such as Merge Sort, Towers of Hanoi, and a recursive knapsack solution.
* **Stack and queue utilities** – Generic stacks, expression evaluators, and multiple queue styles (priority queue, deque, etc.) are present in `src/stack` and `src/queue`.

### Tests

JUnit tests exercise portions of the tree and recursion code. For instance, `BTreeTest` inserts values and verifies ordering and minimum retrieval. A feature file drives Cucumber tests for the knapsack algorithm.

### Notable Points

* Classes occasionally mix Java and Kotlin (e.g., `HeapSort.kt`, `PriorityQHeap.kt`).
* Some comments provide suggestions or TODOs for future refactoring or implementation.
* There is unimplemented functionality, such as the RB-tree deletion method and a TODO in `MultiNode.insertItem`.
* A few comments include informal language, notably in `Knapsack.feature` line 4 and `MultiNode.java` line 111.

### Suggested Next Steps for Learners

1. **Build/Run Setup** – Establish a standard build tool (Maven or Gradle) and document how to compile and run the demos/tests.
2. **Complete Missing Features** – Implement the RB-tree deletion operation and resolve the TODO in `MultiNode.insertItem`.
3. **Explore Tree Algorithms** – Study the 2-3 tree and B-tree implementations (`Tree23` and `BTree`) to understand multiway tree splitting and balancing logic.
4. **Review and Extend Tests** – Many algorithms have small demos but limited unit tests. Adding tests for other structures (queues, stacks, hashing) would strengthen reliability.
5. **Refactor for Generics** – Several classes operate on `Object` or non-parameterized types. Introducing generics could improve type safety, especially in the tree and list utilities.

### Potential Issues & Fixes

*Comment cleanup*
- Profanity appears in the knapsack feature file.
- An off-topic joke comment in `MultiNode.java`

:::task-stub{title="Remove unprofessional comments"}
- Edit `resources/test/recursion/Knapsack.feature` to drop the phrase “This fucking scenario...”.
- Delete the “What do I do Lord? Corrupt them all.” comment in `src/tree/btree/MultiNode.java`.
:::

*RB-tree deletion missing*
:::task-stub{title="Implement RBTree.delete()"}
- Complete the `delete` method in `src/tree/RBTree.java` where it currently returns `false` unconditionally.
- Ensure deletion maintains red-black properties; update or add unit tests under `test/tree`.
:::

*Insertion logic TODO*
:::task-stub{title="Fix MultiNode.insertItem itemCount logic"}
- Refactor `insertItem` in `src/tree/btree/MultiNode.java` so `itemCount` increments only after elements shift into place, as hinted by the TODO at lines 161–163.
- Verify BTree and Tree23 operations still function via existing tests.
:::

*Project documentation*
:::task-stub{title="Add project README"}
- Create a root `README.md` summarizing project purpose.
- Document directory layout, compilation instructions, and how to run tests or demos.
:::

These adjustments will help make the repository more polished and easier for newcomers to use.
