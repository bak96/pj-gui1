package shapes;

import java.awt.*;

public abstract class SerializableShape {
	private Color color;

	public SerializableShape() {
		this.color = Color.black;
	}

	public SerializableShape(Color color) {
		this.color = color;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(color);
	}

	public abstract int getWidth();
	public abstract int getHeight();
	public abstract void setPosition(int x, int y);

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract String serialize();

	public String serializeColor() {
		return Integer.toString(color.getRGB());
	}
}
