package com.detrivos.auto.level.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.detrivos.auto.entity.assets.Leecher;
import com.detrivos.auto.entity.assets.Turret;
import com.detrivos.auto.entity.assets.Turret.Type;
import com.detrivos.auto.entity.assets.drops.Medkit;
import com.detrivos.auto.entity.assets.drops.Medkit.Tier;
import com.detrivos.auto.level.Level;

public class LeecherTunnel1 extends Level {

	public LeecherTunnel1(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		super.loadLevel(path);
		
		add(new Turret(9, 2, Type.NORMAL));
		add(new Turret(36, 6, Type.NORMAL));
		
		add(new Turret(14, 5, Type.MACHINE));
		
		add(new Leecher(24, 4));
		add(new Leecher(20, 8));
		
		add(new Medkit(3, 3, Tier.LOW));
		add(new Medkit(24, 1, Tier.LOW));
		add(new Medkit(25, 6, Tier.LOW));
		
		add(new Medkit(45, 4, Tier.MID));
	}
}
