This project implements a Least Recently Used (LRU) Cache in Java using a combination of:

HashMap → for quick access in O(1)

Circular Doubly Linked List (CDLL) → to efficiently track usage order

The cache supports two main operations:

add(x) → Insert an element into the cache. If the element already exists, move it to the front. If the cache is full, evict the least recently used element.

getCacheElements() → Retrieve the elements in cache from least to most recently used.

Complexity

Time Complexity:

add(x) → O(1)

getCacheElements() → O(n) (to traverse CDLL)

Space Complexity: O(capacity)
