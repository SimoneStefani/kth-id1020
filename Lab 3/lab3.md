## Tasks

###2.2 BubbleSort Implementation (C) – 30P

1. **What are the best case and worst case time complexity of BubbleSort?** <br/>
The best case occours when the list is already sorted and it is in the order of **_O(n)_**.
The worst case occurs when the list is sorted in reversed order. In this case the operations are: <br/> 
_n + n - 1 + n - 2 ... + 1 = (n(n + 1))/2 =_ **_O(n²)_**


2. **Is BubbleSort stable?** <br/>
A sorting algorithm is said to be stable if two objects with equal keys appear in the same order 
in sorted output list as they appear in the unsorted input list. BubbleSort **is a stable** sorting
algorithm.


3. **Give an example of a case where the stability of a sorting algorithm is important.** <br/>
Many real word indexing system make use of several primary keys and sorting elements according to
one key shouldent affect the order of elements relatively to another key. For example sorting a list
of books by topic shouldn't affect the internal alphabetical order.<br/><br/>


###2.3 Inversion Count (C) – 30P
1. **Is the number of _Swap_ operations performed by BubbleSort the same as the number
of inversions? Why?**<br/>
**Yes it is**. A Swap in BubbleSort occurs when an element is found to be larger than an element 
that comes after. An inversion is essentially the mathematical representation of the same condition, when an element
is positioned before a smaller element in a list.
