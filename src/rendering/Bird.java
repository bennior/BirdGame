package rendering;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import buttons.MyButton;
import drawings.DrawCoins;
import main.GameLoop;
import main.Panel;
import saves.Coins;
import saves.Shop;
import updating.GameStates;


public class Bird {
	
	BufferedImage img;
	private double height =  0.0;
	private int jumpCount = 0;
	private int dropCount = 0;
	private double time = 0.0d;
	private double add;
	private BufferedImage[] birdType;
	private int currentBirdType;
	private int animationTick;
	private int animationIndex;
	private double lastDistance = height;

	private double rotationRequired;
	AffineTransform tx;
	AffineTransformOp af;
	
	public Bird(BufferedImage img) {
		this.img = img;
	}
	
	
	
	public void runBird(Graphics g) {
		playing(g);
		
	}
	
	
	
	public void playing(Graphics g) {
		
		if(GameStates.currentGameState == GameStates.GAME) {
			currentBirdType = birdAnimation(10);
			loadAnimation();
			defineAffineTransform();
//			System.out.println(calculateSpeed());

			if(GameStates.jump == false) {

				if(dropCount == 0) {
					add = 0;
					time = 0;
				}
				drop(g);
				dropCount++;
				}else {					
				if(jumpCount == 0) {
					add = 0;
					time = 0;
					dropCount = 0;
				}
				
				jump(g);
				jumpCount++;
					
				if(jumpCount >= 49) {
					GameStates.jump = false;
					jumpCount = 0;
				}
				
				
			}
		
		if(height > 400.0) {
			MyButton.kb.setTrue(5);
//			SoundLoop.sounds.death();
			GameLoop.sounds.death();
		}
		}
	}
	
	private void jump(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		add = calculate(time + 1.0/120) - calculate(time);
		g2D.drawImage(af.filter(birdType[currentBirdType], null), (800 - 50) / 2, (int) height, null);
		height -= (4.0 - 100*add);
		time += 1.0/120;
	}
	
	private void drop(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		add = calculate(time + 1.0/120) - calculate(time);
//		g.drawImage(birdType[currentBirdType], (800 - 50) / 2, (int) height, null);
		g2D.drawImage(af.filter(birdType[currentBirdType], null), (800 - 50) / 2, (int) height, null);
		height += 100*add;
		time += 1.0/120;
	}
	
	public void restart() {
		height = 0.0;
		dropCount = 0;
		jumpCount = 0;
		GameStates.jump = false;
		Panel.generation.generateRandom();
		Panel.generation1.generateRandom();
		Panel.generation2.generateRandom();
		Panel.secondCount = 0;
		Panel.score = 0;
		Coins.coins = 0;
		Coins.collectedCoins = 0;
		DrawCoins.alpha = 0;
		DrawCoins.alphaImage = 0f;
	}
	
	private double calculate(double time) {
		return 0.5*10*time*time;
	}
	
	public int getPosition() {
		return (int) height;
	}
	
	private int birdAnimation(int animationSpeed) {
		
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationIndex++;
			animationTick = 0;
			if(animationIndex > 7) {
				animationIndex = 0;
			}
		}
		return animationIndex;
	}
	
	private void loadAnimation() {
		birdType = new BufferedImage[8];

		for(int i = 0; i < birdType.length; i++) {
			birdType[i] = img.getSubimage(40*i, 40*Integer.parseInt(Shop.selectedBird), 40, 40);
		}
	}
	
	private double calculateSpeed() {
		double currentDistance = height;
		double speed;
		double distance;


		distance = currentDistance - lastDistance;
		speed = distance / (1.0/120);

		lastDistance = currentDistance;
		return speed;
	}
	
	private void defineAffineTransform() {
		rotationRequired = Math.toRadians(calculateSpeed()*0.07);
//		rotationRequired = Math.toRadians(0);

		tx = AffineTransform.getRotateInstance(rotationRequired, 25, 25);
		af = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}
}
