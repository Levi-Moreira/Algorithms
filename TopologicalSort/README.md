# Kotlin implementation of Topological Sort(https://www.youtube.com/watch?v=eL-KzMXSXXI)

- Find Top Sort: O(V + E)

- Algorithm for Top Sort
    - Pick an unvisited node
    - Do a DFS on that node exploring only unvisited nodes
    - On the recursive callback, add the current node to the topological ordering in reverse order


- Algorithm for finding cycle in a graph based on DFS - O(V + E)
    - Create a visited array and a recursion visited array
    - Do a DFS from each unvisited node
    - Mark the current node as visited and recursion visited
    - Do a recursive call for all unvisited neighbors of the current node
    - If any of the recursive calls return true, then there's a cycle and you should return true = If the neighbor is
      visited in the recursion array, return true
    - Once the call return, mark the current node as unvisited in the recursion array.
    - If all recursive calls returned false, return false


- Algorithm for finding cycle in a graph based on BFS - O(V + E)
    - Find the in-degrees of all nodes
    - Create a visited nodes array = Add all in-degrees = 0 to a queue
        - For each element in the queue
            - Mark the element as visited
            - Decrease the in-degree of the neighbours of the current node
            - If an in-degree becomes zero, add it to the queue
    - Once you finish looking through all nodes in the queue. Check if you visited all nodes. If not, it means there's a
      cycle.