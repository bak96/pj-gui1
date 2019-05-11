package app;

import helpers.AppConstants;
import helpers.TimeHelper;
import serializers.ShapeDeserializer;
import shapes.SerializableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShapeRendererWindow extends JFrame {
	private boolean rendering;
	private Thread renderingThread;

	BufferedImage bf;

	private List<SerializableShape> shapes;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ShapeRendererWindow shapeRenderer = new ShapeRendererWindow();
			shapeRenderer.showWindow();
			shapeRenderer.startRendering();
		});
	}

	ShapeRendererWindow() {
		setSize(AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_HEIGHT);
		setTitle("Shape renderer");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		shapes = new ArrayList<>();

		initRenderingThread();
		bf = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public void showWindow() {
		setVisible(true);
	}

	public void paint(Graphics windowGraphics) {
		clearScreen();
		drawShapes();

		windowGraphics.drawImage(bf, 0, 0, getWidth(), getHeight(), null);
	}

	public void startRendering() {
		renderingThread.start();
	}

	private void clearScreen() {
		Graphics g = bf.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, bf.getWidth(), bf.getHeight());
	}

	private void drawShapes() {
		Graphics2D g2 = (Graphics2D) bf.getGraphics();

		for (SerializableShape shape : shapes) {
			shape.draw(g2);
		}
	}

	private void initRenderingThread() {
		renderingThread = new Thread(() -> {
			rendering = true;

			while (rendering) {
				this.updateShapes();
				this.render();
				TimeHelper.sleep(200);
			}
		});
	}

	private void updateShapes() {
		this.shapes = readShapesFromFile();
	}

	private void render() {
		paint(this.getGraphics());
	}


	private List<SerializableShape> readShapesFromFile() {
		Scanner scanner = null;
		ArrayList<SerializableShape> newShapes = new ArrayList<>();

		try {
			scanner = new Scanner(new File(AppConstants.SHAPES_FILE_NAME));

			while (scanner.hasNextLine()) {
				SerializableShape sr = ShapeDeserializer.deserializeShape(scanner.nextLine());

				if (sr != null) {
					newShapes.add(sr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return newShapes;
	}
}
