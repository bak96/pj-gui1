package Shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SerializableEllipse extends SerializableShape {

	private Ellipse2D ellipse2D;

	public SerializableEllipse(int x, int y, int width, int height, Color color) {
		super(color);
		ellipse2D = new Ellipse2D.Float(x, y, width, height);
	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.fill(ellipse2D);
	}

	public int getWidth() {
		return (int)ellipse2D.getWidth();
	}

	public int getHeight() {
		return (int)ellipse2D.getHeight();
	}

	public void setPosition(int x, int y) {
		ellipse2D.setFrame(x, y, ellipse2D.getWidth(), ellipse2D.getHeight());
	}

	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append("ellipse ");

		Rectangle2D bounds = ellipse2D.getBounds();

		sb.append(bounds.getX());
		sb.append(' ');
		sb.append(bounds.getY());
		sb.append(' ');
		sb.append(getWidth());
		sb.append(' ');
		sb.append(getHeight());
		sb.append(' ');
		sb.append(serializeColor());

		return sb.toString();
	}
}
