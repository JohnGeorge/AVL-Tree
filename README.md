# AVL-Tree
AVL-tree implemented in Java

###To add a node to the tree first create an instance of the BinaryTree class and run the add() method with the value of the node as an argument.
```java
BinaryTree bt = new BinaryTree();
bt.add(5);
```

###To delete a node simply run the delete() method on the tree instance with the value to delete as argument.
```java
bt.delete(5);
```

###Output example.
The program when runing will output an inorder traversal of the tree and a visualisation of the tree.
```java
1
2
3
4
5
6
8
10
11
12
13
14
15
25
26
61
62
63
99
100
101
                                 /----- 101
                         /----- 100
                         |       \----- 99
                 /----- 63
                 |       \----- 62
         /----- 61
         |       |       /----- 26
         |       \----- 25
 /----- 15
 |       |       /----- 14
 |       |       |       \----- 13
 |       \----- 12
 |               \----- 11
10
 |       /----- 8
 |       |       \----- 6
 \----- 5
         |       /----- 4
         \----- 3
                 \----- 2
                         \----- 1

-----------------------------------
```
