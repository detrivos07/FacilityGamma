package com.detrivos.auto.entity.assets.story;

import com.detrivos.auto.entity.Entity;
import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.graphics.Sprite;

public class Note extends Entity {
	
	public enum Type {
		CONTROLS, CRYO
	}
	
	public Type t;
	
	public Note(int x, int y, Type t, Sprite sprite) {
		this.t = t;
		this.xPos = x;
		this.yPos = y;
		this.sprite = sprite;
	}
	
	public void tick() {
	}

	public void render(Screen screen) {
		screen.renderMob((int) xPos, (int) yPos, sprite, this);
	}
}
