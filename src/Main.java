import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShapeGeneratorWindow shapeGenerator = new ShapeGeneratorWindow();
            shapeGenerator.showWindow();
            shapeGenerator.startGeneratingShapes();
        });
    }
}