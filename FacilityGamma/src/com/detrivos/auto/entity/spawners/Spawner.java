package com.detrivos.auto.entity.spawners;

import com.detrivos.auto.entity.Entity;
import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.level.Level;

public class Spawner extends Entity {

	private int sign;
	
	public Spawner(int x, int y, int amount, Level level) {
		init(level);
		this.xPos = x;
		this.yPos = y;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Screen screen) {
	}

	//Offsets used for mob spawning
	protected int xOffset(int r) {
		if (random.nextInt(2) == 0) sign = -1;
		else sign = 1;
		int result = random.nextInt(r) * sign;
		return result;
	}

	protected int yOffset(int r) {
		if (random.nextInt(2) == 0) sign = -1;
		else sign = 1;
		int result = random.nextInt(r) * sign;
		return result;
	}
}
