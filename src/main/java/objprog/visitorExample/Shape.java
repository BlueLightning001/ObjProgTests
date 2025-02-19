package objprog.visitorExample;

public abstract class Shape {
    public abstract void accept(ShapeVisitor visitor);
}
