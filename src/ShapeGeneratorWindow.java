import Shapes.SerializableRectangle;
import Shapes.SerializableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShapeGeneratorWindow extends JFrame {

	private boolean generating;
	private Thread generatorThread;

	BufferedImage bf;

	private ArrayList<SerializableShape> shapes;

	ShapeGeneratorWindow() {
		setSize(1024, 768);
		setTitle("Hello, world!");
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
		g2.setColor(new Color(255, 1, 100));



		for (SerializableShape shape : shapes) {
			shape.draw(g2);
		}

		windowGraphics.drawImage(bf, 0, 0, getWidth(), getHeight(), null);
	}

	private void initGeneratorThread() {
		generatorThread = new Thread(() -> {
			generating = true;
			int counter = 0;
			while (generating) {
				System.out.println("generating shapes " + counter);

				SerializableShape sr = ShapeGenerator.generateRandomShape();

				sr.setPosition((int)(Math.random() * (getWidth() - sr.getWidth())), (int)(Math.random() * (getContentPane().getSize().getHeight() - sr.getHeight())) + (int)(getHeight() - getContentPane().getSize().getHeight()));

				shapes.add(sr);
				paint(this.getGraphics());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				counter++;
			}
		});
	}

	public void startGeneratingShapes() {
		generatorThread.start();
	}
}
