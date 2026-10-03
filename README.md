# Assignment 3 | Bridge Pattern

Berdibek Meiirbek, SE-2528  
Topic A: Drawing  
Repository: https://github.com/Mikosh001/Assignment_3_Bridge_Pattern  
Base commit (Vector/Raster and T1-T5): `0242fdaf2fb7b165234b13fe4f078bd99ca39a7f`

Circle and Square keep their own dimensions. A Renderer supplies the rendering style. The renderer can be replaced on the same shape object.

| Bridge role | Class and source |
| --- | --- |
| Abstraction | `Shape` - `src/bridge/Shape.java` |
| A1 | `Circle` - `src/bridge/Circle.java` |
| A2 | `Square` - `src/bridge/Square.java` |
| Implementor | `Renderer` - `src/bridge/Renderer.java` |
| I1 | `VectorRenderer` - `src/bridge/VectorRenderer.java` |
| I2 | `RasterRenderer` - `src/bridge/RasterRenderer.java` |
| I3 | `AsciiRenderer` - `src/bridge/AsciiRenderer.java` |
| Client | `Main` - `src/Main.java` (default package) |

The bridge is the private `Renderer renderer` field in `Shape`. `Circle.execute()` calls `renderCircle()` and `Square.execute()` calls `renderSquare()` through that interface. `Shape.setImplementation(Renderer)` replaces the renderer. T5 in `Main` compares the original and later shape references with `==` and checks that its ID and radius did not change.

## Build and run

Use JDK 17. From the extracted project folder, run:

```text
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

No library or interactive input is needed. Expected results:

| Check | Expected result |
| --- | --- |
| T1 | `VECTOR circle radius=2` |
| T2 | `RASTER circle radius=2` |
| T3 | `VECTOR square side=3` |
| T4 | `RASTER square side=3` |
| T5 | Same Circle reference (`==`), ID `C5`, radius `2`; before `VECTOR circle radius=2`, after `RASTER circle radius=2` |
| T6 | `ASCII circle radius=2` |
| T7 | `ASCII square side=3` |

`demo-output.txt` contains the captured run. `extension.diff` shows the Java source changes from the base commit to the AsciiRenderer extension. OpenAI Codex assisted with the Java implementation, report drafting, and verification.
