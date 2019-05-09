package Shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class SerializableTriangle extends SerializableShape {
	private Path2D triangle2D;

	public SerializableTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
		super(color);

		triangle2D = new Path2D.Float();

		triangle2D.moveTo(x1, y1);
		triangle2D.lineTo(x2, y2);
		triangle2D.lineTo(x3, y3);
		triangle2D.lineTo(x1, y1);

		System.out.println("after creation: " + triangle2D.getBounds2D().getWidth() + " " + triangle2D.getBounds2D().getHeight());
	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.fill(triangle2D);
	}

	public int getWidth() {
		return (int)triangle2D.getBounds2D().getWidth();
	}

	public int getHeight() {
		return (int)triangle2D.getBounds2D().getHeight();
	}

	public void setPosition(int x, int y) {
		AffineTransform at = new AffineTransform();
		at.setToTranslation(x, y);

		triangle2D.transform(at);
	}

	public void rotate(double angle) {


		AffineTransform at = new AffineTransform();
		at.rotate(angle);

		triangle2D.transform(at);

		System.out.println("after rotation: " + triangle2D.getBounds2D().getWidth() + " " + triangle2D.getBounds2D().getHeight());
	}
}
