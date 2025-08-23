package com.detrivos.auto.level.tile;

import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;

	private boolean isSolid = false;
	private boolean isSolidToBullets = false;
	private boolean hasClothes = false;
	private boolean hasGun = false;
	
	public static Tile dirt = new Tile(Sprite.dirt);
	public static Tile grass = new Tile(Sprite.grass);
	
	public static Tile dirtSR = new Tile(Sprite.dirtSR);
	public static Tile dirtMR = new Tile(Sprite.dirtMR);
	public static Tile dirtLR = new Tile(Sprite.dirtLR);
	
	public static Tile grassFadeL = new Tile(Sprite.grassFadeL);
	public static Tile grassFadeT = new Tile(Sprite.grassFadeT);
	public static Tile grassFadeR = new Tile(Sprite.grassFadeR);
	public static Tile grassFadeB = new Tile(Sprite.grassFadeB);
	
	public static Tile grassFadeILT = new Tile(Sprite.grassFadeILT);
	public static Tile grassFadeIRT = new Tile(Sprite.grassFadeIRT);
	public static Tile grassFadeIRB = new Tile(Sprite.grassFadeIRB);
	public static Tile grassFadeILB = new Tile(Sprite.grassFadeILB);
	
	public static Tile grassFadeOLT = new Tile(Sprite.grassFadeOLT);
	public static Tile grassFadeORT = new Tile(Sprite.grassFadeORT);
	public static Tile grassFadeORB = new Tile(Sprite.grassFadeORB);
	public static Tile grassFadeOLB = new Tile(Sprite.grassFadeOLB);
	
	public static Tile sGrass = new Tile(Sprite.sGrass);
	
	public static Tile voidTile = new Tile(Sprite.voidSprite).setSolid().setSolidToBullets();
	
	public static Tile toCryo = new ToCryoTile(Sprite.whiteFloor);
	public static Tile toTurHall = new ToTurHallTile(Sprite.whiteFloor);
	public static Tile toturhall2 = new ToTurHall2Tile(Sprite.whiteFloor);
	public static Tile toturhall3 = new ToTurHall3Tile(Sprite.whiteFloor);
	public static Tile tothft = new ToTurHall3Tile(Sprite.mrtb);
	public static Tile toleech1 = new ToLeech1Tile(Sprite.dirtLR);
	
	//tunnel SS
	public static Tile dirtTLC = new Tile(Sprite.dirtTLC);
	public static Tile dirtTRC = new Tile(Sprite.dirtTRC);
	public static Tile dirtBRC = new Tile(Sprite.dirtBRC);
	public static Tile dirtBLC = new Tile(Sprite.dirtBLC);
	
	public static Tile dirtLeft = new Tile(Sprite.dirtLeft);
	public static Tile dirtRight = new Tile(Sprite.dirtRight);
	public static Tile dirtDown = new Tile(Sprite.dirtDown);
	public static Tile dirtTop = new Tile(Sprite.dirtTop);
	
	public static Tile dirtWall = new Tile(Sprite.dirtWall).setSolid().setSolidToBullets();
	
	public static Tile brt = new Tile(Sprite.brt);
	public static Tile brl = new Tile(Sprite.brl);
	
	public static Tile mrtb = new Tile(Sprite.mrtb);
	public static Tile mrr = new Tile(Sprite.mrr);
	
	public static Tile srtl = new Tile(Sprite.srtl);
	public static Tile srt = new Tile(Sprite.srt);
	
	
	//base spritesheet
	public static Tile fw1 = new Tile(Sprite.fw1);
	public static Tile fw2 = new Tile(Sprite.fw2);
	public static Tile fw3 = new Tile(Sprite.fw3);
	public static Tile fw4 = new Tile(Sprite.fw4);
	
	public static Tile fb1 = new Tile(Sprite.fb1);
	public static Tile fb2 = new Tile(Sprite.fb2);
	
	public static Tile wdt = new Tile(Sprite.wdt).setSolid().setSolidToBullets();
	public static Tile wdb = new Tile(Sprite.wdb).setSolid().setSolidToBullets();
	
	public static Tile tloc = new Tile(Sprite.tloc).setSolid().setSolidToBullets();
	public static Tile troc = new Tile(Sprite.troc).setSolid().setSolidToBullets();
	public static Tile bloc = new Tile(Sprite.bloc).setSolid().setSolidToBullets();
	public static Tile broc = new Tile(Sprite.broc).setSolid().setSolidToBullets();
	
	public static Tile bric = new Tile(Sprite.bric).setSolid().setSolidToBullets();
	public static Tile blic = new Tile(Sprite.blic).setSolid().setSolidToBullets();
	public static Tile tric = new Tile(Sprite.tric).setSolid().setSolidToBullets();
	public static Tile tlic = new Tile(Sprite.tlic).setSolid().setSolidToBullets();
	
	public static Tile bench = new Tile(Sprite.bench).setSolid();
	public static Tile crate1 = new Tile(Sprite.crate1).setSolid().setSolidToBullets();
	
	public static Tile tlw = new Tile(Sprite.tlw).setSolid().setSolidToBullets();
	public static Tile tlcw = new Tile(Sprite.tclw).setSolid().setSolidToBullets().setHasClothes();
	public static Tile blw = new Tile(Sprite.blw).setSolid().setSolidToBullets();
	public static Tile blgw = new Tile(Sprite.bglw).setSolid().setSolidToBullets().setHasGun();
	
	public static Tile rw = new Tile(Sprite.rw).setSolid().setSolidToBullets();
	public static Tile tw = new Tile(Sprite.tw).setSolid().setSolidToBullets();
	public static Tile lw = new Tile(Sprite.lw).setSolid().setSolidToBullets();
	public static Tile bw = new Tile(Sprite.bw).setSolid().setSolidToBullets();
	
	public static Tile ble = new Tile(Sprite.ble).setSolid().setSolidToBullets();
	public static Tile bre = new Tile(Sprite.bre).setSolid().setSolidToBullets();
	public static Tile tle = new Tile(Sprite.tle).setSolid().setSolidToBullets();
	public static Tile tre = new Tile(Sprite.tre).setSolid().setSolidToBullets();
	
	public static Tile ltd = new DoorTile(Sprite.ltd).setSolid().setSolidToBullets();
	public static Tile lbd = new DoorTile(Sprite.lbd).setSolid().setSolidToBullets();
	
	public static Tile whiteFloor = new Tile(Sprite.whiteFloor);
	public static Tile blueFloor = new Tile(Sprite.blueFloor);
	
	public static Tile changeFloor = new ChangeTile(Sprite.whiteFloor);
	public static Tile shotgun = new Tile(Sprite.blueFloor).setSolid().setSolidToBullets();
	
	public static Tile pFloor = new Tile(Sprite.whiteFloor).setSolid().setSolidToBullets();
	public static Tile dFloor = new DeadTile(Sprite.whiteFloor).setSolid().setSolidToBullets();
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	/// Sets the tile to solid for player
	public Tile setSolid() {
		this.isSolid = true;
		return this;
	}

	/// Only sets the tile as solid to bullets
	public Tile setSolidToBullets() {
		this.isSolidToBullets = true;
		return this;
	}

	public Tile setHasGun() {
		this.hasGun = true;
		return this;
	}

	public Tile setHasClothes() {
		this.hasClothes = true;
		return this;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public boolean isSolidToBullets() {
		return isSolidToBullets;
	}

	public boolean hasGun() {
		return hasGun;
	}
	
	public boolean hasClothes() {
		return hasClothes;
	}
}
