# visitorj
A Visitor design pattern implementation and code generator in Java.

Features:
* Supports both depth-first and breadth-first traversal.
* The Visitors have access to the current traversal context.
* Visitor code generator.
* No boilerplate code to write.

Work in progress.

## Quickstart
* Add a dependecy to the project ```visitorj-runtime```.
* Create a new interface ```Visitor``` with two methods for each object to be traversed:
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
public class ClassA implements Visitable<Visitor> {
  @Override public VisitResult visitorEnter(Visitor visitor) { return visitor.enter(visitor); }
  @Override public void visitorLeave(Visitor visitor) { visitor.leave(visitor); }
  
  @Override
  public Iterator<Visitable<DocumentVisitor>> getVisitableChildren() {
    return Arrays.asList(child1, child2, etc).iterator();
  }
}
```
* Write a ```VisitorImpl``` class implementing ```Visitor```.
* Use the Visitor:
```java
  ClassA a = ...;
  a.accept(new VisitorImpl());
```

## Breadth-first traversal
```java
  ClassA a = ...;
  a.accept_breadthFirst(new VisitorImpl());
```


## Code generation
* Add a dependecy to the project ```visitorj-codegen```.
* Call the code generator:
```java
  final File outputDir = ...;
  final String packageName = "com.example";

	final List<JavaClass> classes = new ArrayList<>();
  // the 'main' class must be the first item in the list.
  classes.add(new JavaClass("com.example.ClassA", false)); // false means the class does not reference itself in its children 
  classes.add(new JavaClass("com.example.ClassB", true)); // true means the class references itself in its children
  final CodeGeneratorService codeGen = new CodeGeneratorService();
  codeGen.generateAll(outputDir, classes, packageName);
```

* The following files are created:
  * ClassAVisitor.java: The Visitor interface.
  * ClassAVisitorDelegate.java: a visitor delegate class.
  * ClassAVisitorWithContext: An abstract visitor, implementing ```ClassAVisitor```, that provides traversal context.
  * SimpleClassAVisitor: a default implementation of the ```ClassAVisitor``` interface.
  * SimpleClassAVisitorWithContext: a default implementation of the ```ClassAVisitorWithContext``` abstract class.

## Examples
Check out the example contained in the project ```visitorj-examples```.
