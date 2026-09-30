package bridge;

import java.util.Objects;

public abstract class Shape {
    private final String id;
    private Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = id;
        setImplementation(renderer);
    }

    public String getId() {
        return id;
    }

    public void setImplementation(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer, "renderer");
    }

    protected Renderer renderer() {
        return renderer;
    }

    public abstract String execute();
}
