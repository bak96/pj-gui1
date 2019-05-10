import Shapes.SerializableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ShapeGeneratorWindow extends JFrame {

	private boolean generating;
	private Thread generatorThread;

	BufferedImage bf;

	private ArrayList<SerializableShape> shapes;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ShapeGeneratorWindow shapeGenerator = new ShapeGeneratorWindow();
			shapeGenerator.showWindow();
			shapeGenerator.startGeneratingShapes();
		});
	}

	ShapeGeneratorWindow() {
		setSize(1024, 768);
		setTitle("Shape generator");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		shapes = new ArrayList<>();

		initGeneratorThread();
		bf = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public void showWindow() {
		setVisible(true);
	}

	public void paint(Graphics windowGraphics) {

		Graphics g = bf.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		Graphics2D g2 = (Graphics2D)g;

		for (SerializableShape shape : shapes) {
			shape.draw(g2);
		}

		windowGraphics.drawImage(bf, 0, 0, getWidth(), getHeight(), null);
	}

	private void initGeneratorThread() {
		generatorThread = new Thread(() -> {
			generating = true;
			int counter = 0;

			try {
				PrintWriter writer = new PrintWriter(new FileWriter("test"));

				while (generating) {
					System.out.println("generating shapes " + counter);

					SerializableShape sr = ShapeGenerator.generateRandomShape();
					setRandomPosition(sr);

					writer.println(sr.serialize());
					writer.flush();

					shapes.add(sr);
					paint(this.getGraphics());

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					counter++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void startGeneratingShapes() {
		generatorThread.start();
	}

	private void setRandomPosition(SerializableShape shape) {
		int minY = (int)(getHeight() - getContentPane().getSize().getHeight());
		int contentHeight = (int) getContentPane().getSize().getHeight();

		int posX = (int)(Math.random() * (getWidth() - shape.getWidth()));
		int posY = (int)(Math.random() * (contentHeight - shape.getHeight())) + minY;
		System.out.println(posX + " " + posY);
		shape.setPosition(posX, posY);
	}
}
