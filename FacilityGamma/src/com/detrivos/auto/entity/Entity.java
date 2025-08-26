package com.detrivos.auto.entity;

import java.util.Random;

import com.detrivos.auto.entity.utils.HealthBar;
import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.graphics.Sprite;
import com.detrivos.auto.level.Level;

public abstract class Entity {

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}


	/// movement
	protected Direction dir;
	protected double xPos, yPos, xMov, yMov, xTilePos, yTilePos;
	protected double speed = 1;
	protected boolean moving = false;

	/// entity specific
	public double health;
	public double stamina;

	protected HealthBar bar;
	protected boolean barAdd = false;

	public boolean locked = false;
	private boolean removed = false;//TODO:: This should be level-centric
	protected Sprite sprite;
	protected Level level;
	protected final Random random = new Random();//Is this necessary?
	
	public void tick() {

	}

	public abstract void render(Screen screen);

	public void move() {}
	
	public void setRemove(boolean remove) {
		if (remove == false) removed = false;
		else removed = true;
	}
	
	public void remove() {
		//Remove from level
		removed = true;
	}

	public double getHealth() {
		return health;
	}
	
	public double getXPosition() {
		return xPos;
	}
	
	public double getYPosition() {
		return yPos;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}

	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			//Player Specific
			double xt = ((xPos + xa) + c % 2 * -9 + 4) / 16;
			double yt = ((yPos + ya) + c / 2 * -1 + 1) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0)
				ix = (int) Math.floor(xt);
			if (c / 2 == 0)
				iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).isSolid())
				solid = true;
			xTilePos = ix;
			yTilePos = iy;
		}
		return solid;
	}
}
