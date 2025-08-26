package com.detrivos.auto.level.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.detrivos.auto.entity.spawners.TurretSpawner;
import com.detrivos.auto.level.Level;
import com.detrivos.auto.modes.Challenge;

public class ChallengeLevel extends Level {

	public ChallengeLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		super.loadLevel(path);
		
		add(new TurretSpawner(5 * 16, 5 * 16, 1, this));
	}
}
