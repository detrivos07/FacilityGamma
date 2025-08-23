package com.detrivos.auto.level.tile;

import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.graphics.Sprite;

public class DeadTile extends Tile {

	public DeadTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid() {
		return true;
	}
}
