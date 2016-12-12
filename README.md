# visitorj
A Visitor design pattern implementation and code generator in Java.

Features:
* Supports both Depth-first and breadt-first traversal.
* The Visitors have access to the current traversal context.
* Visitor code generator.
* No boilerplate code to write.

Work in progress.

## Quickstart
* Add a dependecy to the project visitorj-runtime.
* Create a new interface Visitor with two methods for each object to be traversed:
```java
public interface Visitor {
  VisitResult enter(ClassA a);
  void leave(ClassA a);
  VisitResult enter(ClassB b);
  void leave(ClassB b);
}
```

* For each model object to be visited, implement the interface ```Visitable<Visitor>```:
```java
public class A implements Visitable<Visitor> {
  @Override public VisitResult enter(Visitor visitor) { return visitor.enter(visitor); }
  @Override public void leave(Visitor visitor) { visitor.leave(visitor); }
  
  @Override
  public Iterator<Visitable<DocumentVisitor>> getVisitableChildren() {
    return Arrays.asList(child1, child2, etc).iterator();
  }
}
```
* Write a VisitorImpl class implementing the Visitor.
* Use the Visitor:
```java
  ClassA a = ...;
  a.accept(new VisitorImpl());
```

## Code generation
TODO

## Examples
TODO
