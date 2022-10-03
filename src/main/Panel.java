package main;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import audio.Audio;
import drawings.DrawBackgrounds;
import drawings.DrawCoins;
import drawings.DrawScores;
import rendering.Bird;
import rendering.Generation;
import saves.Coins;
import saves.MyFile;
import saves.Shop;
import updating.GameStates;
import updating.KeyBoardListener;
import updating.MyMouseListener;

public class Panel extends JPanel{
	
	private MyMouseListener mouseInputs;
	private KeyBoardListener keyInputs;
	private BufferedImage img;
	private DrawBackgrounds background;
	public static Bird bird;
	public static Generation generation;
	public static Generation generation1;
	public static Generation generation2;
	public static int secondCount;
	private String fileName = "gamefiles.txt";
	public static MyFile file = new MyFile();
	public static int score;
	public static Coins coins;
	private DrawScores drawScores;
	public static DrawCoins drawCoins;
	private String setHighScoreOutput;
	public Panel() {
		importImg();
		setPreferredSize(new Dimension(800, 400));
		init();
	}

	
	public void importImg() {
		InputStream is = getClass().getResourceAsStream("/main/birdatlas.png");

		try {
			img = ImageIO.read(is);
			}
			catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		background.paintBackgrounds(g, img, file);
		bird.runBird(g);
		multipleGeneration(g);	
		drawScores.drawScores(g, score);
	}
	
	public void multipleGeneration(Graphics g) {
		if(GameStates.currentGameState == GameStates.GAME) {
		secondCount++;
		Generation.difficultyGeneration();

		if(secondCount >= 0) {
			generation.move(g);
		}
		if(secondCount >= 300) {
			generation1.move(g);
		}
		if(secondCount >= 600) {
			generation2.move(g);
		}
		
		if(secondCount >= 630) {
			secondCount -= 1;
		}
		}
	}
	
	public void init() {
		createFile(file, fileName);
		try {
			Shop.initArray();
			Shop.initSelected();
		} catch (IOException e) {
			e.printStackTrace();
		}
		keyInputs = new KeyBoardListener();
		mouseInputs = new MyMouseListener(file);
		background = new DrawBackgrounds();
		bird = new Bird(img);
		generation = new Generation(file);
		generation1 = new Generation(file);
		generation2 = new Generation(file);
		coins = new Coins(file);
		drawCoins = new DrawCoins(background, file, img);
		drawScores = new DrawScores(file, drawCoins, img);
		
		addKeyListener(new KeyBoardListener());
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	
	private void createFile(MyFile file, String fileName) {
		try {
			file.createFile(fileName);
			try {
				int i = Integer.parseInt(file.readFile(fileName, 0));
			} catch(Exception x) {
				Shop.array[0] = "0";
				file.writeFile(fileName, "0", "0", "EASY", "0", "0.5f", "0.5f", "true");
			}
			}
			catch(Exception e) {
				
			}
	}
	
	public boolean checkHighScore(MyFile myFile) throws IOException {
		
		if(score > Integer.parseInt(myFile.readFile(fileName, 0))) {
			return true;
		}else {
			return false;
		}
	}
	
	public String setHighScore(MyFile file) throws IOException{
		if(checkHighScore(file) == true) {
			setHighScoreOutput = Integer.toString(score);
		}else {
			setHighScoreOutput = file.readFile(fileName, 0);
		}
		return setHighScoreOutput;
	}

}
