package bridge;

public class VectorRenderer implements Renderer {
    @Override
    public String renderCircle(int radius) {
        return "VECTOR circle radius=" + radius;
    }

    @Override
    public String renderSquare(int side) {
        return "VECTOR square side=" + side;
    }
}
