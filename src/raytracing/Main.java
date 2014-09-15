package raytracing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import raytracing.model.scene.Scene;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 766;
	private static final int HEIGHT = 600;
	public static final int PANEL_WIDTH = 550;
	public static final int PANEL_HEIGHT = 550;

	private JPanel princPanel;
	private BufferedImage doubleBuffer;

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
		setTitle("Porygon 3D");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		princPanel = new JPanel();
		princPanel.setBounds(10, 10, 550, 550);
		getContentPane().add(princPanel);
		princPanel.setBackground(Color.BLACK);

		doubleBuffer = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		start();
	}

	private void start() {
		Timer timer = new Timer(1000 / 60, this);
		timer.start();
	}

	private void paint() {
		Graphics dbg = doubleBuffer.getGraphics();
		paintPerspective(dbg);
	}

	private void paintPerspective(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		princPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
	}

	@Override
	public void paint(Graphics g) {
	}

	public static Scene getScene() {
		return getScene();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		paint();
	}
}
