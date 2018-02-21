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
  public VisitableList<DocumentVisitor> getVisitableChildren() {
    VisitableList<DocumentVisitor> res = new VisitableList<>();
    res.add(child1);
    res.add(child2);
    return res;
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

## Code generation (using Maven)
Configure the Maven plugin as in this example:

```xml
<plugin>
    <groupId>com.github.ewanld.visitorj</groupId>
    <artifactId>visitorj-codegen-maven-plugin</artifactId>
    <version>0.1.0</version>
    <executions>
        <execution>
            <id>id1</id>
            <phase>generate-sources</phase>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <packageName>com.github.ewanld.visitorj.examples.model</packageName>
                <visitorName>Json</visitorName>
                <classes>
                    <class>com.github.visitorj.examples.model.JsonArray</class>
                    <class>com.github.visitorj.examples.model.JsonBoolean</class>
                    <class>com.github.visitorj.examples.model.JsonNumber</class>
                    <class>com.github.visitorj.examples.model.JsonObject</class>
                    <class>com.github.visitorj.examples.model.JsonObjectProperty</class>
                </classes>
            </configuration>
        </execution>
    </executions>
</plugin>
```

* The following files are created:
  * JsonVisitor.java: The Visitor interface.
  * JsonVisitorDelegate.java: a visitor delegate class.
  * JsonVisitorWithContext: An abstract visitor, implementing ```JsonVisitor```, that provides traversal context.
  * SimpleJsonVisitor: a default implementation of the ```JsonVisitor``` interface.
  * SimpleJsonVisitorWithContext: a default implementation of the ```JsonVisitorWithContext``` abstract class.

## Examples
Check out the example contained in the project ```visitorj-examples```.
