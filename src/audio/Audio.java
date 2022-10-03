package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;




public class Audio {
	
    Clip clip;
    FloatControl controle;
    AudioInputStream audioInputStream;
    
    public Audio(String file) {

		try {
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(file));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			controle = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
		}catch(Exception e) {
			
		}
    }
    
	public void play() {

		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void controle(float volume) {
		

		if(volume == -30.0f) {
			controle.setValue(-80.0f);
		}else {
			controle.setValue(volume); // Reduce volume by 10 decibels.
		}
	}
	
	public void mute() {
		controle.setValue(-80.0f);
	}
	
}
