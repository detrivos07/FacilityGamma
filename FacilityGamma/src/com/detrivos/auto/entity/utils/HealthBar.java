package com.detrivos.auto.entity.utils;

import com.detrivos.auto.entity.Entity;
import com.detrivos.auto.graphics.Screen;

public class HealthBar extends Entity {
	
	private Entity m;
	private int oWidth = 18;
	private double health;
	public final int HEIGHT = 1;
	public int width = oWidth;
	public int col = 0xFF00FF00;	

	public HealthBar(Entity m, int oX, int oY) {
		this.xPos = (oX - 1);
		this.yPos = (oY - 2);
		this.m = m;
		this.health = m.getHealth();
	}
	
	public void tick() {
		changePos();
		changeWidth();
		changeColour();
	}
	
	private void changeWidth() {
		if (m.health > 6) this.width = (int) ((int) (m.health * oWidth) / health);
		else if (m.health <= 0) remove();
		else this.width = 1;
	}
	
	private void changeColour() {
		if ((m.health * oWidth) / health > 12) {
			col = 0xFF00FF00;
		} else if ((m.health * oWidth) / health < 6) {
			col = 0xFFFF0000;
		} else {
			col = 0xFFFFBB00;
		}
	}
	
	private void changePos() {
		this.xPos = (int) m.getXPosition() - 1;
		this.yPos = (int) m.getYPosition() - 2;
	}
	public void render(Screen screen) {
		if (m.health != health) screen.renderHealthBar((int) xPos, (int) yPos, this);
	}
}
