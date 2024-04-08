# Lock Free
An exploration of lock free programming over some common data structure

## Introduction
Lock-free programming is a technique used in concurrent programming where multiple threads or processes can operate 
on shared data structures without needing to acquire locks. Instead of using traditional synchronization mechanisms 
like mutexes or semaphores, lock-free programming relies on atomic operations and algorithms designed to ensure 
progress even in the presence of contention.

Data structures covered here:
- Stack
- Queue
- Binary Search Tree (construction only)


## Characteristics
Here are some key characteristics and principles of lock-free programming:

1. **Atomic Operations**: Atomic operations are indivisible operations that are guaranteed to execute without
   interruption from other threads. Common atomic operations include compare-and-swap (CAS), fetch-and-add,
   and load-link/store-conditional.

2. **Progress Guarantees**: Lock-free algorithms are designed to make progress even in the presence of contention.
   This means that even if some threads are delayed or blocked, the system as a whole can still make progress towards
   completing tasks.

3. **No Locks**: Unlike traditional locking mechanisms, lock-free programming does not use locks to protect shared 
resources. Instead, it relies on atomic operations provided by the hardware and programming language constructs 
to ensure thread safety.

4. **ABA Problem**: One common challenge in lock-free programming is the ABA problem, where a shared value changes 
from A to B and back to A again, leading to potential inconsistencies. Techniques such as using version numbers
or additional metadata are employed to mitigate this problem.

5. **Memory Reordering**: Lock-free programming requires careful consideration of memory ordering issues, 
as modern processors may perform optimizations such as memory reordering that can affect the visibility 
of changes between threads.

**Notes**: I did not try to solve the ABA problem.

## References
- https://www.baeldung.com/lock-free-programming
- https://www.baeldung.com/java-atomic-variables
- https://www.baeldung.com/java-atomicstampedreference
