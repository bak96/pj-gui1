import shapes.SerializableEllipse;
import shapes.SerializableRectangle;
import shapes.SerializableShape;
import shapes.SerializableTriangle;

import java.awt.*;
import java.util.Scanner;

public class ShapeDeserializer {
	public static SerializableShape deserializeShape(String line) {
		Scanner sc = new Scanner(line);

		String shapeName = sc.next();

		switch(shapeName) {
			case "rectangle":
				return deserializeRectangle(sc);
			case "ellipse":
				return deserializeEllipse(sc);
			case "triangle":
				return deserializeTriangle(sc);
			default:
				return null;
		}
	}

	public static SerializableRectangle deserializeRectangle(Scanner rectangleInfo) {
		int x = rectangleInfo.nextInt();
		int y = rectangleInfo.nextInt();
		int width = rectangleInfo.nextInt();
		int height = rectangleInfo.nextInt();
		int color = rectangleInfo.nextInt();

		return new SerializableRectangle(x, y, width, height, new Color(color));
	}

	public static SerializableEllipse deserializeEllipse(Scanner ellipseInfo) {
		int x = ellipseInfo.nextInt();
		int y = ellipseInfo.nextInt();
		int width = ellipseInfo.nextInt();
		int height = ellipseInfo.nextInt();
		int color = ellipseInfo.nextInt();

		return new SerializableEllipse(x, y, width, height, new Color(color));
	}

	public static SerializableTriangle deserializeTriangle(Scanner triangleInfo) {
		int x1 = triangleInfo.nextInt();
		int y1 = triangleInfo.nextInt();
		int x2 = triangleInfo.nextInt();
		int y2 = triangleInfo.nextInt();
		int x3 = triangleInfo.nextInt();
		int y3 = triangleInfo.nextInt();

		int posX = triangleInfo.nextInt();
		int posY = triangleInfo.nextInt();

		double rotation = triangleInfo.nextDouble();
		int color = triangleInfo.nextInt();

		SerializableTriangle triangle = new SerializableTriangle(x1, y1, x2, y2, x3, y3, new Color(color));
		triangle.rotate(rotation);
		triangle.setPosition(posX, posY);
		return triangle;
	}
}
