import bridge.Circle;
import bridge.RasterRenderer;
import bridge.Renderer;
import bridge.Shape;
import bridge.Square;
import bridge.VectorRenderer;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !"--demo".equals(args[0])) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        int passed = 0;
        int total = 0;

        total++;
        passed += check("T1", new Circle("C1", 2, vector), vector, "VECTOR circle radius=2");
        total++;
        passed += check("T2", new Circle("C1", 2, raster), raster, "RASTER circle radius=2");
        total++;
        passed += check("T3", new Square("S1", 3, vector), vector, "VECTOR square side=3");
        total++;
        passed += check("T4", new Square("S1", 3, raster), raster, "RASTER square side=3");

        Circle circle = new Circle("C5", 2, vector);
        Shape original = circle;
        String idBefore = circle.getId();
        int radiusBefore = circle.getRadius();
        String before = circle.execute();
        circle.setImplementation(raster);
        Shape afterSwitch = circle;
        String after = circle.execute();
        boolean sameObject = original == afterSwitch;
        boolean stateUnchanged = idBefore.equals(circle.getId()) && radiusBefore == circle.getRadius();
        boolean switchPassed = sameObject && stateUnchanged
                && "VECTOR circle radius=2".equals(before)
                && "RASTER circle radius=2".equals(after);
        total++;
        if (switchPassed) {
            passed++;
        }
        System.out.println("T5 " + (switchPassed ? "PASS" : "FAIL")
                + " | Circle + VectorRenderer -> RasterRenderer"
                + " | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
        System.out.println("  id=" + circle.getId() + " | radius=" + circle.getRadius()
                + " | before=" + before + " | after=" + after);
        if (!switchPassed) {
            System.out.println("  expected: sameObject=true, stateUnchanged=true,"
                    + " before=VECTOR circle radius=2, after=RASTER circle radius=2");
        }

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static int check(String test, Shape shape, Renderer renderer, String expected) {
        String actual = shape.execute();
        boolean passed = expected.equals(actual);
        System.out.println(test + " " + (passed ? "PASS" : "FAIL") + " | "
                + shape.getClass().getSimpleName() + " + " + renderer.getClass().getSimpleName()
                + " | result=" + actual);
        if (!passed) {
            System.out.println("  expected=" + expected);
        }
        return passed ? 1 : 0;
    }
}
