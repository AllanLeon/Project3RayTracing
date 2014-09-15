package raytracing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import raytracing.data.WindowConstants;
import raytracing.drawer.Drawer;
import raytracing.model.scene.Scene;
import raytracing.model.scene.SceneFactory;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private BufferedImage doubleBuffer;
	private Scene scene;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize and set up the basic components of the frame.
	 */
	private void initialize() {
		setTitle("Omg Ray Tracerz a lot...rays pew pew pew");
		setSize(WindowConstants.WIDTH, WindowConstants.HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, WindowConstants.WIDTH, WindowConstants.HEIGHT);
		getContentPane().add(mainPanel);
		mainPanel.setBackground(Color.BLACK);

		doubleBuffer = new BufferedImage(WindowConstants.WIDTH, WindowConstants.HEIGHT, BufferedImage.TYPE_INT_RGB);
		scene = SceneFactory.createBasicScene();
		paint();
	}

	private void paint() {
		Graphics dbg = doubleBuffer.getGraphics();
		Drawer.drawScene(dbg, scene);
		mainPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
	}

	@Override
	public void paint(Graphics g) {
	}
}
