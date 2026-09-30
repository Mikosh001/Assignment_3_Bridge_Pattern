package bridge;

public class Circle extends Shape {
    private final int radius;

    public Circle(String id, int radius, Renderer renderer) {
        super(id, renderer);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return renderer().renderCircle(radius);
    }
}
