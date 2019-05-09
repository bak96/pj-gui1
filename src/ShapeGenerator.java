import Shapes.SerializableEllipse;
import Shapes.SerializableRectangle;
import Shapes.SerializableShape;
import Shapes.SerializableTriangle;

import java.awt.*;

public class ShapeGenerator {

	public static SerializableRectangle generateRectangle() {
		return new SerializableRectangle(0, 0, (int)(Math.random() * 60 + 20), (int)(Math.random() * 60 + 20), randomColor());
	}

	public static SerializableEllipse generateEllipse() {
		return new SerializableEllipse(0, 0, (int)(Math.random() * 60 + 20), (int)(Math.random() * 60 + 20), randomColor());
	}

	public static SerializableTriangle generateTriangle() {
		int x1 = 0;
		int y1 = (int)(Math.random() * 60) + 20;

		int x2 = (int)(Math.random() * 60) + 40;
		int y2 = y1;

		int x3 = (int)(Math.random() * x2);
		int y3 = 0;

		SerializableTriangle st = new SerializableTriangle(x1, y1, x2, y2, x3, y3, randomColor());
		st.rotate(Math.random() * Math.PI * 2);

		return st;
	}

	public static SerializableShape generateRandomShape() {
		int shape = (int)(Math.random() * 3);

		switch (shape) {
			case 0:
				return generateRectangle();
			case 1:
				return generateEllipse();
			case 2:
				return generateTriangle();
			default:
				return null;
		}
	}

	private static Color randomColor() {
		return new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
	}
}
