package main;


import audio.Music;
import audio.Sounds;
import updating.Updating;

public class GameLoop implements Runnable {
	
	private Frame frame;
	public static Panel panel;
	private Thread gameThread;
	private Updating update;
	private final int FPS_SET = 120;
	private Music music = new Music();
	public static Sounds sounds = new Sounds();
	
	public GameLoop() {
		
		panel = new Panel();
		update = new Updating();
		frame = new Frame(panel);
		startGameLoop();
		music.play();
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
					
		while(true) {
			
			now = System.nanoTime();
			if(now - lastFrame >= timePerFrame) {
				panel.repaint();
				update.updating();
				panel.requestFocus();
				music.controle();
				sounds.controle();

				lastFrame = now;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		
	}

}
