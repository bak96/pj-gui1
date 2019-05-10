import Shapes.SerializableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShapeRendererWindow extends JFrame {

	private boolean rendering;
	private Thread renderingThread;

	BufferedImage bf;

	private ArrayList<SerializableShape> shapes;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ShapeRendererWindow shapeRenderer = new ShapeRendererWindow();
			shapeRenderer.showWindow();
			shapeRenderer.startRendering();
		});
	}

	ShapeRendererWindow() {
		setSize(1024, 768);
		setTitle("Shape renderer");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		shapes = new ArrayList<>();

		initRenderingThread();
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

	private void initRenderingThread() {
		renderingThread = new Thread(() -> {
			rendering = true;
			while (rendering) {
				try {
					Scanner sc = new Scanner(new File("test"));
					shapes.clear();
					while (sc.hasNextLine()) {
						SerializableShape sr = ShapeDeserializer.deserializeShape(sc.nextLine());

						if (sr != null) {
							shapes.add(sr);
						}
					}

					sc.close();
					paint(this.getGraphics());
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void startRendering() {
		renderingThread.start();
	}
}
