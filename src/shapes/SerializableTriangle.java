package shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class SerializableTriangle extends SerializableShape {
	private Path2D triangle2D;

	private int x1, y1, x2, y2, x3, y3;
	private int posX, posY;
	private double rotation;

	public SerializableTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
		super(color);

		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;

		this.posX = 0;
		this.posY = 0;

		this.rotation = 0;

		triangle2D = new Path2D.Float();

		triangle2D.moveTo(x1, y1);
		triangle2D.lineTo(x2, y2);
		triangle2D.lineTo(x3, y3);
		triangle2D.lineTo(x1, y1);
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
		posX = x;
		posY = y;

		AffineTransform at = new AffineTransform();
		at.setToTranslation(x, y);

		triangle2D.transform(at);
	}

	public void rotate(double angle) {
		this.rotation += angle;

		AffineTransform at = new AffineTransform();
		at.rotate(angle);

		triangle2D.transform(at);
	}

	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder();

		sb.append("triangle ");
		sb.append(x1);
		sb.append(' ');
		sb.append(y1);
		sb.append(' ');
		sb.append(x2);
		sb.append(' ');
		sb.append(y2);
		sb.append(' ');
		sb.append(x3);
		sb.append(' ');
		sb.append(y3);
		sb.append(' ');
		sb.append(posX);
		sb.append(' ');
		sb.append(posY);
		sb.append(' ');
		sb.append(rotation);
		sb.append(' ');
		sb.append(serializeColor());

		return sb.toString();
	}
}
