import gc
import sys

class Node:
    def __init__(self, name):
        self.name = name
        self.link = None

gc.disable()

A = Node("A")
B = Node("B")

A.link = B
B.link = A

print("A refs:", sys.getrefcount(A))
print("B refs:", sys.getrefcount(B))

del A
del B

print("Objects before collection:",
      len(gc.get_objects()))

unreachable = gc.collect()

print("Unreachable objects collected:",
      unreachable)

print("Objects after collection:",
      len(gc.get_objects()))