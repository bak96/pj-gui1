package Shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SerializableRectangle extends SerializableShape {

	private Rectangle2D rectangle2D;

	public SerializableRectangle(int x, int y, int width, int height, Color color) {
		super(color);
		rectangle2D = new Rectangle2D.Float(x, y, width, height);
	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.fill(rectangle2D);
	}

	public void setPosition(int x, int y) {
		rectangle2D.setRect(x, y, rectangle2D.getWidth(), rectangle2D.getHeight());
	}

	public int getWidth() {
		return (int)rectangle2D.getWidth();
	}

	public int getHeight() {
		return (int)rectangle2D.getHeight();
	}
}
