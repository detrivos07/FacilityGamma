package com.detrivos.auto.entity.assets;

import java.util.List;

import com.detrivos.auto.Game;
import com.detrivos.auto.audio.SoundClip;
import com.detrivos.auto.entity.Entity;
import com.detrivos.auto.entity.assets.drops.BulletDrops;
import com.detrivos.auto.entity.assets.drops.BulletDrops.BType;
import com.detrivos.auto.entity.assets.drops.Medkit;
import com.detrivos.auto.entity.assets.story.Note;
import com.detrivos.auto.entity.assets.story.Note.Type;
import com.detrivos.auto.experience.Experience;
import com.detrivos.auto.graphics.AnimatedSprite;
import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.graphics.Sprite;
import com.detrivos.auto.graphics.SpriteSheet;
import com.detrivos.auto.input.Keyboard;
import com.detrivos.auto.input.Mouse;
import com.detrivos.auto.level.tile.ChangeTile;
import com.detrivos.auto.level.tile.DeadTile;
import com.detrivos.auto.level.tile.DoorTile;
import com.detrivos.auto.level.tile.PlayerTile;
import com.detrivos.auto.level.tile.ToCryoTile;
import com.detrivos.auto.level.tile.ToLeech1Tile;
import com.detrivos.auto.level.tile.ToTurHall2Tile;
import com.detrivos.auto.level.tile.ToTurHall3Tile;
import com.detrivos.auto.level.tile.ToTurHallTile;
import com.detrivos.auto.projectile.Bullet;
import com.detrivos.auto.projectile.Projectile;
import com.detrivos.auto.projectile.Rocket;
import com.detrivos.auto.ui.StoryUI;

public class Player extends Entity {

	public enum Weapon {
		PISTOL, MACHINE, SCATTER, ROCKET
	}

	public Weapon w;
	public Experience exp;

	private boolean pistol = false;
	private boolean machine = false;
	private boolean scatter = false;
	private boolean rocket = false;

	public static boolean isArmed = false;
	public static boolean isClothed = false;
	public static boolean hasScatter = false;
	public static boolean hasMachine = false;
	public static boolean hasRocket = false;
	public static boolean dead = false;

	private Keyboard input;
	private Sprite sprite;

	private double dx = 0, dy = 0;
	private double pdir = 0;

	private int baseHealth = 100;

	public int pistolBullets = 0;
	public static int scatterBullets = 0;
	public int machineBullets = 0;
	public int rockets = 0;

	public static int kills = 0;

	private int fireRate = 0;
	private int r = random.nextInt(5);
	boolean box = false;

	public static int animNum = 0;

	private boolean givenControl = false;
	private boolean hitZero = false;
	private boolean collided = false;
	public boolean hasControl = false;

	public static boolean gunThought = true;

	public static boolean animFin = false;

	private SoundClip shoot = new SoundClip("sounds/shoot1.wav");
	private SoundClip hp = SoundClip.hp;
	private SoundClip hurt = SoundClip.hurt;

	private AnimatedSprite naked = new AnimatedSprite(SpriteSheet.pnaked, 16, 16, 4);
	private AnimatedSprite clothed = new AnimatedSprite(SpriteSheet.pclothed, 16, 16, 4);
	private AnimatedSprite cgun = new AnimatedSprite(SpriteSheet.pcgun, 16, 16, 4);
	private AnimatedSprite gun = new AnimatedSprite(SpriteSheet.pgun, 16, 16, 4);
	private AnimatedSprite cs = new AnimatedSprite(SpriteSheet.cs, 16, 16, 4);
	private AnimatedSprite cm = new AnimatedSprite(SpriteSheet.cm, 16, 16, 4);
	private AnimatedSprite cb = new AnimatedSprite(SpriteSheet.cb, 16, 16, 4);
	private AnimatedSprite ns = new AnimatedSprite(SpriteSheet.ns, 16, 16, 4);
	private AnimatedSprite nm = new AnimatedSprite(SpriteSheet.nm, 16, 16, 4);
	private AnimatedSprite nb = new AnimatedSprite(SpriteSheet.nb, 16, 16, 4);

	private AnimatedSprite anim = null;

	public Player(int x, int y, Keyboard input, boolean story) {
		this.xPos = x;
		this.yPos = y;
		this.health = 100;
		this.stamina = 100;
		this.input = input;
		speed = 1.2;
		w = Weapon.PISTOL;
		exp = new Experience();
		pistol = true;
		anim = naked;
		animNum = 0;
	}

	public void tick() {// TODO :: Entity collision using A> pixel colour? B>HitBoxes
		if (this.moving) anim.tick();
		else anim.setFrame(0);

		if (pistolBullets > 500) pistolBullets = 500;
		if (scatterBullets > 500) scatterBullets = 500;
		if (machineBullets > 500) machineBullets = 500;
		if (rockets > 500) rockets = 500;

		switch (animNum) {
		case (0):
			anim = naked;
			break;
		case (1):
			anim = clothed;
			break;
		case (2):
			anim = gun;
			break;
		case (3):
			anim = cgun;
			break;
		case (4):
			anim = ns;
			break;
		case (5):
			anim = nm;
			break;
		case (6):
			anim = nb;
			break;
		case (7):
			anim = cs;
			break;
		case (8):
			anim = cm;
			break;
		case (9):
			anim = cb;
			break;
		default:
			anim = naked;
			break;
		}

		if (isArmed) {
			if (input.key1) {
				if (isClothed) {
					animNum = 3;
				} else {
					animNum = 2;
				}
				w = Weapon.PISTOL;
				pistol = true;
				machine = false;
				scatter = false;
				rocket = false;
			}
			if (input.key2 && hasScatter) {
				if (isClothed) {
					animNum = 7;
				} else {
					animNum = 4;
				}
				w = Weapon.SCATTER;
				pistol = false;
				machine = false;
				scatter = true;
				rocket = false;
			}
			if (input.key3 && hasMachine) {
				if (isClothed) {
					animNum = 8;
				} else {
					animNum = 5;
				}
				w = Weapon.MACHINE;
				pistol = false;
				machine = true;
				scatter = false;
				rocket = false;
			}
			if (input.key4 && hasRocket) {
				if (isClothed) {
					animNum = 9;
				} else {
					animNum = 6;
				}
				w = Weapon.ROCKET;
				pistol = false;
				machine = false;
				scatter = false;
				rocket = true;
				fireRate = Rocket.fireRate;
			}
		}

		if (w == Weapon.PISTOL && !pistol) {
			fireRate = Bullet.fireRate;
		}
		if (w == Weapon.MACHINE && !machine) {
			fireRate = Bullet.fireRate;
		}
		if (w == Weapon.SCATTER && !scatter) {
			fireRate = Bullet.fireRate;
		}
		if (w == Weapon.ROCKET && !rocket) {
			fireRate = Rocket.fireRate;
		}

		if (hasControl) {
			dx = Mouse.getX() - (Game.absWidth / 2);
			dy = Mouse.getY() - (Game.absHeight / 2);
			pdir = Math.atan2(dy, dx);
		} else {
			pdir = Math.toRadians(-180);
		}
		
		handleProjectiles();
		handleDrops();
		handleEntities();
		handleExperience();

		if (hasControl) {
			if (input.run && (input.up || input.down || input.left || input.right) && stamina > 0 && !hitZero) {
				speed = 1.5;
				stamina -= 0.5;
				anim.setFrameRate(5);
				if (stamina <= 0) {
					stamina = 0;
					hitZero = true;
				}
			} else {
				speed = 1.2;
				stamina += 0.25;
				if (stamina > 100) {
					stamina = 100;
					hitZero = false;
				}
				anim.setFrameRate(7);
			}
			if (fireRate > 0)
				fireRate--;

			if (input.up) {
				yMov -= speed;
			} else if (input.down) {
				yMov += speed;
			}
			if (input.left) {
				xMov -= speed;
			} else if (input.right) {
				xMov += speed;
			}
		}

		if (animFin && !hasControl) {
			xMov -= 0.2;
			anim.setFrameRate(15);
		}

		if (collision(4, 0) || collision(0, 4) || collision(-14, 0) || collision(0, -14)) {
			if (level.getTile((int) (xTilePos), (int) (yTilePos)).hasGun()) {
				if (isClothed)
					animNum = 3;
				else
					animNum = 2;
				isArmed = true;
			}

			if (level.getTile((int) (xTilePos), (int) (yTilePos)).hasClothes()) {
				if (isArmed)
					animNum = 3;
				else
					animNum = 1;
				isClothed = true;
			}
		}

		if (level.getTile((int) xTilePos, (int) yTilePos) instanceof ToCryoTile) {
			Game.toCryo = true;
		} else {
			Game.toCryo = false;
		}
		if (level.getTile((int) xTilePos, (int) yTilePos) instanceof ToTurHallTile) {
			Game.toTurHall = true;
		} else {
			Game.toTurHall = false;
		}
		if (level.getTile((int) xTilePos, (int) yTilePos) instanceof ToTurHall2Tile) {
			Game.turhall2 = true;
		} else {
			Game.turhall2 = false;
		}
		if (level.getTile((int) xTilePos, (int) yTilePos) instanceof ToTurHall3Tile) {
			Game.toturhall3 = true;
		} else {
			Game.toturhall3 = false;
		}
		if (level.getTile((int) xTilePos, (int) yTilePos) instanceof ToLeech1Tile) {
			Game.toleech1 = true;
		} else {
			Game.toleech1 = false;
		}

		move();

		if (this.health <= 0) {
			health = 0;
			dead = true;
			if (Game.onchal) exp.resetChalTables();
		} else {
			dead = false;
		}

		if (isArmed) {
			if (w == Weapon.PISTOL && pistolBullets > 0) {
				clear();
				shooting();
			}
			if (w == Weapon.SCATTER && scatterBullets > 0) {
				clear();
				shooting();
			}
			if (w == Weapon.MACHINE && machineBullets > 0) {
				clear();
				shooting();
			}
			if (w == Weapon.ROCKET && rockets > 0) {
				clear();
				shooting();
			}
		}
		thought();
	}
	
	private void handleDrops() {
		List<Entity> drops = level.getDrops(this, 7);
		if (drops.size() > 0) {
			for (int i = 0; i < drops.size(); i++) {
				Entity e = drops.get(i);
				if (e instanceof Medkit) {
					boolean fh = false;
					if (((Medkit) e).health == false) {
						if (baseHealth - this.health == 0)
							fh = true;
						else
							fh = false;
						if (this.health + ((Medkit) e).hp < baseHealth)
							this.health += ((Medkit) e).hp;
						else
							this.health += (baseHealth - this.health);
						((Medkit) e).health = false;
						if (!fh)
							SoundClip.play(hp, -10.0f);
					}
					if (!fh)
						e.setRemove(true);
				}

				if (e instanceof BulletDrops) {
					if (((BulletDrops) e).gave == false) {
						if (((BulletDrops) e).t == BType.PISTOL)
							pistolBullets += ((BulletDrops) e).quantity;
						if (((BulletDrops) e).t == BType.SCATTER)
							scatterBullets += ((BulletDrops) e).quantity;
						if (((BulletDrops) e).t == BType.MACHINE)
							machineBullets += ((BulletDrops) e).quantity;
						if (((BulletDrops) e).t == BType.ROCKET)
							rockets += ((BulletDrops) e).quantity;
						((BulletDrops) e).gave = true;
					}
					e.setRemove(true);
				}
			}
		}
	}
	
	private void handleProjectiles() {
		List<Projectile> projectiles = level.getProjectiles(this, 5);
		if (projectiles.size() > 0) {
			Projectile p = projectiles.get(0);
			if (p.type != 0) {
				if (p instanceof Bullet) {
					p.setRemove(true);
					SoundClip.play(hurt, -10.0f);
					this.health -= p.damage;
				}

				if (p instanceof Rocket) {
					((Rocket) p).exploded = true;
					if (((Rocket) p).didDamage == false && ((Rocket) p).exploded == true) {
						((Rocket) p).didDamage = true;
						this.health -= p.damage;
					}
				}
			}
		}
	}
	
	private void handleEntities() {
		List<Entity> entities = level.getEntities(this, 7);
		if (entities.size() > 0) {
			box = true;
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				if (e instanceof Leecher) {
					this.health -= 0.5;
					if (((Leecher) e).health < 100)
						((Leecher) e).health += 0.5;
				}

				if (e instanceof Landmine) {
					((Landmine) e).exploded = true;
					if (!((Landmine) e).hitPlayer)
						this.health -= 47;
					((Landmine) e).hitPlayer = true;
				}

				if (e instanceof Note) {
					if (((Note) e).t == Type.CONTROLS)
						Game.setUI(StoryUI.controls);
					else if (((Note) e).t == Type.CRYO)
						Game.setUI(StoryUI.cryo);
					else
						Game.setUI(null);
				} else {
					Game.setUI(null);
				}
			}
		} else {
			box = false;
			Game.setUI(null);
		}
	}

	private void handleExperience() {
		if (Game.onchal) exp.convertChalTables(Game.chal.dif);
		
		input.toggleKey(input.exp, Game.onExpMenu);
	}
	
	public void changePlayerLevel(double x, double y) {
		this.xPos = x;
		this.yPos = y;
	}

	public void changeMode(boolean story) {
		if (story) {
			pistol = true;
			scatter = false;
			machine = false;
			rocket = false;
			isArmed = false;
			hasScatter = false;
			hasMachine = false;
			hasRocket = false;
			isClothed = false;
			pistolBullets = 0;
			scatterBullets = 0;
			machineBullets = 0;
			rockets = 0;
			animFin = false;
			hasControl = false;
			givenControl = false;
			animNum = 0;
		} else {
			pistol = true;
			scatter = false;
			machine = false;
			rocket = false;
			isArmed = true;
			hasScatter = true;
			hasMachine = true;
			hasRocket = true;
			isClothed = true;
			pistolBullets = 250;
			scatterBullets = 500;
			machineBullets = 250;
			rockets = 5;
			animFin = true;
			hasControl = true;
			givenControl = true;
			animNum = 3;
		}
	}

	public void changePlayerHealth(int health) {
		this.health = health;
	}

	public String thought() {
		String s = "";
		if (collision(4, 0) || collision(0, 4) || collision(-14, 0) || collision(0, -14)) {
			if (level.getTile((int) (xTilePos), (int) (yTilePos)) instanceof PlayerTile) {
				s = "This one was mine, the only one that didn't get destroyed...";
			}
			if (level.getTile((int) (xTilePos), (int) (yTilePos)) instanceof DoorTile) {
				s = "Blocked.  By some unknown force...";
			}
			if (level.getTile((int) (xTilePos), (int) (yTilePos)) instanceof DeadTile) {
				if (!collided) {
					r = random.nextInt(5);
					collided = true;
				}
				switch (r) {
				case (0):
					s = "I knew these people, now they're dead...";
					break;
				case (1):
					s = "Why am I still here?";
					break;
				case (2):
					s = "What happened to this place?";
					break;
				case (3):
					s = "I need to move, I could be next...";
					break;
				default:
					s = "Why...";
					break;
				}
			} else {
				collided = false;
			}
			if (level.getTile((int) (xTilePos), (int) (yTilePos)).hasGun()) {s = "A pistol!";}

			if (level.getTile((int) (xTilePos), (int) (yTilePos)).hasClothes()) {s = "Clothes!";}

			if (level.getTile((int) (xTilePos), (int) (yTilePos)) instanceof ChangeTile && gunThought) {
				s = "I should find something to protect myself...";
			}
		} else {
			s = "";
		}
		return s;
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved())
				level.getProjectiles().remove(i);
		}
	}

	private void shooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx, dy;

			double sx = Math.sqrt(61) * Math.sin(Math.atan2(6, 5) - pdir);
			double sy = Math.sqrt(61) * Math.sin(Math.atan2(5, 6) + pdir);

			dx = (Mouse.getX()) - ((Game.absWidth / 2));
			dy = (Mouse.getY()) - ((Game.absHeight / 2));
			int dist = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			int change = 3;
			if (dist >= 100) {
				if (dist <= 400 && dist > 350)
					change = 3;
				if (dist <= 350 && dist > 300)
					change = 4;
				if (dist <= 300 && dist > 250)
					change = 5;
				if (dist <= 250 && dist > 200)
					change = 6;
				if (dist <= 200 && dist > 150)
					change = 8;
				if (dist <= 150 && dist > 140)
					change = 9;
				if (dist <= 140 && dist > 130)
					change = 10;
				if (dist <= 130 && dist > 120)
					change = 11;
				if (dist <= 120 && dist > 110)
					change = 12;
				if (dist <= 110 && dist > 100)
					change = 13;
			}

			double dir = Math.atan2(dy, dx) - Math.toRadians(change);

			shoot((xPos + 8) + sx, (yPos + 8) + sy, dir);
			if (w != Weapon.ROCKET)
				fireRate = Bullet.fireRate;
			else
				fireRate = Rocket.fireRate;
		}
	}

	public void move() {
		if (xMov > 0)
			dir = Direction.RIGHT;
		if (xMov < 0)
			dir = Direction.LEFT;
		if (yMov > 0)
			dir = Direction.DOWN;
		if (yMov < 0)
			dir = Direction.UP;

		if (!collision(xMov, yMov) || givenControl) {
			hasControl = true;
			givenControl = true;
		}

		if (!hasControl) {
			while (xMov != 0) {
				this.xPos += xMov;
				xMov = 0;
			}

			while (yMov != 0) {
				this.yPos += yMov;
				yMov = 0;
			}
		} else if (hasControl) {
			if (collision(0, 0) & !collision(1, 0))
				this.xPos += 0.5;
			if (collision(0, 0) & !collision(-1, 0))
				this.xPos -= 0.5;
			if (collision(0, 0) & !collision(0, 1))
				this.yPos += 0.5;
			if (collision(0, 0) & !collision(0, -1))
				this.yPos -= 0.5;

			if (xMov != 0) {
				if (!collision(xMov, yMov)) {
					this.xPos += xMov;
				}
				xMov = 0;
			}

			if (yMov != 0) {
				if (!collision(xMov, yMov)) {
					this.yPos += yMov;
				}
				yMov = 0;
			}
		}
	}

	protected void shoot(double x, double y, double dir) {
		Projectile p = null;
		if (w == Weapon.PISTOL && pistol) {
			p = new Bullet(x, y, dir, this);
			pistolBullets--;
		}
		if (w == Weapon.MACHINE && machine) {
			p = new Bullet(x, y, dir, this);
			machineBullets--;
		}
		if (w == Weapon.SCATTER && scatter) {
			Projectile b = new Bullet(x, y, dir - Math.toRadians(20), this);
			p = new Bullet(x, y, dir, this);
			Projectile a = new Bullet(x, y, dir + Math.toRadians(20), this);
			level.add(a);
			level.add(b);
			scatterBullets -= 3;
		}
		if (w == Weapon.ROCKET && rocket) {
			p = new Rocket(x, y, dir, this);
			rockets--;
		}
		boolean shot = false;
		if (!shot) {
			SoundClip.play(shoot, -14.0f);
			shot = true;
		}
		level.add(p);
	}

	public void render(Screen screen) {
		if (animFin)
			sprite = anim.getSprite();
		else
			sprite = Sprite.pink;
		screen.renderMob((int) xPos, (int) yPos, Sprite.rotate(sprite, pdir), this);
	}
}
