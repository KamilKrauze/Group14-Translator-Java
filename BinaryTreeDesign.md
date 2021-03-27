## Pseudocode


### Saved to a file
Preorder traversal, seperate value of node by a space and the nodes by /n

### Add to binary tree
Create a new node, set it's data, set its left & right pointers to null
If the tree is empty (i.e. root is null)
   Set root to be the new node
Else
   check if node is already in tree??, if not:
   Set current to root, previous to null, found to false
   while current isn't null (AND not found??)
      previous = current
      if ID at new < ID at current
         set current to current’s left
      else
         set current to current’s right
      End
   End
   if node not found (i.e. node isn't already in tree)
     if new node is smaller than previous
         add new node as left child of previous
     else
         add new node as right child of previous
   end
End

### Deleting nodes
if root is null throw an exception
traverse the tree looking for node todelete (viz previous pseudocodes)
    keep track of previous and parent
if node has no children
    set its reference at previous to null
elseif node has only left or right child
    set its reference at previous to the respective child
elseif node has both children
     * traverse left subtree looking for rightmost node
    set current to left child
    while current != null
        previous = current
        current = current.right

    * remove current from its postition - it can only have left children
    previous.right = current.left
    * copy all of toDelete's data to current
    current = toDelete
    * replace toDelete with current
    point toDelete parent to current

### Balancing nodes
We save the tree inorder to a List and then load it back from the list in the following way:
The inorder traversal produces an already sorted list and 

set root node to the middle of the list
split the list into left and right
set the root.left to the middle of the left list
set the root.right to the middle of the right list
recursively continue

# Test plan

## Binary tree testing

1. Binary tree adding
Create tree, add 3, 1, 5
Tree should look like that and not throw exception
**Passes ✔**

2. Lookup
Try checking if 3 exists 
expected true
**Passes ✔**

Try checking if 4 exists
expected false
**Passes ✔**

3. Try to add sth that exists
Try to add 3
expected - exception
**Passes ✔**

4. Retrieval of a node's value
Try to look up node with id 3 (it has value 3)
expected - 3
**Passes ✔**

Try to look up node with id 4 (it doesnt exist)
expected - exception
**Passes ✔**


5. Deletion of a node
Try to delete node with id 3
Check that the tree looks right in debugger (1, with 5 to its right)
**Passes ✔**

Try to delete node with id 1
Check that the tree looks right in debugger (3, with 5 to its right)
**Passes ✔**

Try to delete node with id 4
expected - exception
**Passes ✔**

Add 2 and try to delete it
Check that the tree is 3, with 2 to left and 5 to right
**Passes ✔**


6. Inorder traversal
Print for all the trees
expected: 1 5 \n 3 5 \n 2 3 5
**Passes ✔**

7. Postorder traversal
Print for all the trees
expected: 5 1 \n 5 3 \n 2 5 3
**Passes ✔**
