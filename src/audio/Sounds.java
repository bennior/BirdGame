package audio;

import drawings.DrawBackgrounds;

public class Sounds {
	
	Audio death = new Audio("death.wav");
	Audio click = new Audio("click.wav");
	Audio flap = new Audio("flap.wav");
	
	public void death() {
		death.play();
	}
	
	public void click() {
		click.play();
	}
	
	public void flap() {
		flap.play();
	}
	
	public void controle() {
			death.controle((Float.valueOf(DrawBackgrounds.soundeffects.getPos()) * 36.0f) - 30.0f);
			click.controle((Float.valueOf(DrawBackgrounds.soundeffects.getPos()) * 36.0f) - 30.0f);
			flap.controle((Float.valueOf(DrawBackgrounds.soundeffects.getPos()) * 36.0f) - 30.0f);
	
	}
}
