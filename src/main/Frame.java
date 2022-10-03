package main;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.GraphicsDevice;

public class Frame {

	Image image = new ImageIcon(this.getClass().getResource("/main/raven.png")).getImage();	
	ImageIcon img = new ImageIcon(image);
	private static JFrame frame;

	public Frame(Panel panel) {
		
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setResizable(false);
		frame.setTitle("BirdGame");
		frame.setIconImage(img.getImage());
		frame.pack();
		frame.setVisible(true);
//		showOnScreen(2, frame);
		frame.setLocationRelativeTo(null);
		
		
	}
	
	public static int getScreenDevice() {
		GraphicsDevice gd = frame.getGraphicsConfiguration().getDevice();
		int width = gd.getDisplayMode().getWidth();
//		int height = gd.getDisplayMode().getHeight();
		return width;
	}
	
//	private static void showOnScreen( int screen, Window frame ) {
//	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    GraphicsDevice[] gd = ge.getScreenDevices();
//	    GraphicsDevice graphicsDevice;
//	    if( screen > -1 && screen < gd.length ) {
//	        graphicsDevice = gd[screen];
//	    } else if( gd.length > 0 ) {
//	        graphicsDevice = gd[0];
//	    } else {
//	        throw new RuntimeException( "No Screens Found" );
//	    }
//	    Rectangle bounds = graphicsDevice.getDefaultConfiguration().getBounds();
//	    int screenWidth = graphicsDevice.getDisplayMode().getWidth();
//	    int screenHeight = graphicsDevice.getDisplayMode().getHeight();
//	    frame.setLocation(bounds.x + (screenWidth - frame.getPreferredSize().width) / 2,
//	            bounds.y + (screenHeight - frame.getPreferredSize().height) / 2);
//	    frame.setVisible(true);
//	}
	
}