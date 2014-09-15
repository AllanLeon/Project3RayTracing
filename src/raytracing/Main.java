package raytracing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import raytracing.data.WindowConstants;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private BufferedImage doubleBuffer;
	private JButton btnMain;

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
		mainPanel.setBounds(10, 10, WindowConstants.PANEL_WIDTH, WindowConstants.PANEL_HEIGHT);
		getContentPane().add(mainPanel);
		mainPanel.setBackground(Color.BLACK);
		
		btnMain = new JButton("Start");
		btnMain.setBounds(242, 621, 370, 40);
		getContentPane().add(btnMain);

		doubleBuffer = new BufferedImage(WindowConstants.PANEL_WIDTH, WindowConstants.PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		start();
	}

	private void start() {
		Timer timer = new Timer(1000 / 60, this);
		timer.start();
	}

	private void paint() {
		Graphics dbg = doubleBuffer.getGraphics();
	}

	@Override
	public void update(Graphics g) {
	}

	@Override
	public void paint(Graphics g) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		paint();
	}
}
