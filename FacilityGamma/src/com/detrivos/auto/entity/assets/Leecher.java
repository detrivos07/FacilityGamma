package com.detrivos.auto.entity.assets;

import java.util.List;

import com.detrivos.auto.entity.Entity;
import com.detrivos.auto.entity.utils.HealthBar;
import com.detrivos.auto.graphics.Screen;
import com.detrivos.auto.graphics.Sprite;
import com.detrivos.auto.projectile.Projectile;

public class Leecher extends Entity {
	
	private int rot = 0;
	private int rotSpeed = 2;
	
	double dx = 0, dy = 0;
	double angle = 0;
	
	boolean posx = false;
	boolean negx = false;
	boolean posy = false;
	boolean negy = false;
	
	private double tang = 0;
	private double oSpeed = 1.4;

	public Leecher(int x, int y) {
		this.xPos = x << 4;
		this.yPos = y << 4;
		this.health = 100;
		speed = oSpeed;
		sprite = Sprite.leecher; 
		if (level != null) {
			level.add(this);
		}
	}
	
	public void tick() {
		if (level != null && !this.barAdd) {
			bar = new HealthBar(this, (int) this.xPos, (int) this.yPos);
			level.add(bar);
			this.barAdd = true;
		}
		if (this.health <= 0) {
			bar.setRemove(true);
			setRemove(true);
		}
		
		List<Player> players = level.getPlayers(this, 10 * 16);
		if (players.size() > 0) {
			Player player = players.get(0);
			if (!player.box) {
				dx = player.getXPosition() - this.xPos;
				dy = player.getYPosition() - this.yPos;
				if ((int) Math.toDegrees(Math.atan2(dy, dx)) != rot && !locked) {
					if (((int) Math.toDegrees(Math.atan2(dy, dx)) >= 0 && rot >= 0) || ((int) Math.toDegrees(Math.atan2(dy, dx)) <= 0 && rot <= 0)) {
						if ((int) Math.toDegrees(Math.atan2(dy, dx)) > rot &&  (int) Math.toDegrees(Math.atan2(dy, dx)) - rot < rotSpeed) rotSpeed = (int) Math.toDegrees(Math.atan2(dy, dx)) - rot;
						else rotSpeed = 3;
						if ((int) Math.toDegrees(Math.atan2(dy, dx)) > rot) rot += rotSpeed;
						
						if ((int) Math.toDegrees(Math.atan2(dy, dx)) < rot && rot - (int) Math.toDegrees(Math.atan2(dy, dx)) < rotSpeed) rotSpeed = rot - (int) Math.toDegrees(Math.atan2(dy, dx));
						else rotSpeed = 3;
						if ((int) Math.toDegrees(Math.atan2(dy, dx)) < rot) rot -= rotSpeed;
						rotSpeed = 3;
						
					} else if (((int) Math.toDegrees(Math.atan2(dy, dx)) > 0 && rot < 0)) {
						if (Math.abs((int) Math.toDegrees(Math.atan2(dy, dx))) + Math.abs(rot) < 180) rot += rotSpeed;
						else rot -= rotSpeed;
					} else if (((int) Math.toDegrees(Math.atan2(dy, dx)) < 0 && rot > 0)) {
						if (Math.abs((int) Math.toDegrees(Math.atan2(dy, dx))) + Math.abs(rot) < 180) rot -= rotSpeed;
						else rot += rotSpeed;
					}
				} else {
					angle = Math.atan2(dy, dx);
					rot = (int) Math.toDegrees(Math.atan2(dy, dx));
					if (xPos < player.getXPosition() && player.getXPosition() - xPos < oSpeed) speed = player.getXPosition() - xPos;
					else speed = oSpeed;
					if (xPos < player.getXPosition()) xMov += speed;
					
					if (xPos > player.getXPosition() && xPos - player.getXPosition() < oSpeed) speed = xPos - player.getXPosition();
					else speed = oSpeed;
					if (xPos > player.getXPosition()) xMov -= speed;
					
					if (yPos < player.getYPosition() && player.getYPosition() - yPos < oSpeed) speed = player.getYPosition() - yPos;
					else speed = oSpeed;
					if (yPos < player.getYPosition()) yMov += speed;
					
					if (yPos > player.getYPosition() && yPos - player.getYPosition() < oSpeed) speed = yPos - player.getYPosition();
					else speed = oSpeed;
					if (yPos > player.getYPosition()) yMov -= speed;
					locked = true;
					
					if (collision(0, yMov) && !posx && !negx) {
						if (player.getXPosition() < this.xPos) {
							posx = true;
							negx = false;
						} else {
							negx = true;
							posx = false;
						}
					}
					
					if (collision(xMov, 0) && !posy && !negy) {
						if (player.getYPosition() < this.yPos) {
							posy = true;
							negy = false;
						} else {
							negy = true;
							posy = false;
						}
					}
				}
			} else {
				xMov = 0;
				yMov = 0;
			}
		} else {
			locked = false;
		}
		
		if (posx) {
			xMov += speed;
			if (!collision(0, yMov)) {
				posx = false;
			}
		}
		if (negx) {
			xMov -= speed;
			if (!collision(0, yMov)) {
				negx = false;
			}
		}
		if (posy) {
			yMov += speed;
			if (!collision(xMov, 0)) {
				posy = false;
			}
		}
		if (negy) {
			yMov -= speed;
			if (!collision(xMov, 0)) {
				negy = false;
			}
		}
		
		List<Projectile> projectiles = level.getProjectiles(this, 6);
		if (projectiles.size() > 0) {
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = projectiles.get(i);
				p.setRemove(true);
				this.health -= p.damage;
			}
		}
		
		List<Entity> ents = level.getEntities(this, 6);
		if (ents.size() > 0) {
			for (int i = 0; i < ents.size(); i++) {
				Entity e = ents.get(i);
				if (xPos < e.getXPosition()) xMov -= speed;
				if (xPos > e.getXPosition()) xMov += speed;
				if (yPos < e.getYPosition()) yMov -= speed;
				if (yPos > e.getYPosition()) yMov += speed;
			}
		}
		
		if (yMov < 0) {
			dir = Direction.UP;
		} else if (yMov > 0) {
			dir = Direction.DOWN;
		}
		if (xMov < 0) {
			dir = Direction.LEFT;
		} else if (xMov > 0) {
			dir = Direction.RIGHT;
		}
		
		move();
	}
	
	public void move() {
		if (xMov > 0) dir = Direction.RIGHT;
		if (xMov < 0) dir = Direction.LEFT;
		if (yMov > 0) dir = Direction.DOWN;
		if (yMov < 0) dir = Direction.UP;
		
		while (xMov != 0) {
			if (collision(0, 0) & !collision(1, 0)) this.xPos += 0.5;
			if (collision(0, 0) & !collision(-1, 0)) this.xPos -= 0.5;
			if (collision(0, 0) & !collision(0, 1)) this.yPos += 0.5;
			if (collision(0, 0) & !collision(0, -1)) this.yPos -= 0.5;
			if (Math.abs(xMov) > 1) {
				if (!collision(xMov, yMov)) {
					this.xPos += xMov;
				}
				if (xMov > 0) {
					xMov -= Math.abs(xMov);
				} else {
					xMov += Math.abs(xMov);
				}
			} else {
				if (!collision(xMov, yMov)) {
					this.xPos += xMov;
				}
				xMov = 0;
			}
		}

		while (yMov != 0) {
			if (Math.abs(yMov) > 1) {
				if (!collision(xMov, yMov)) {
					this.yPos += yMov;
				}
				if (yMov > 0) {
					yMov -= Math.abs(yMov);
				} else {
					yMov += Math.abs(yMov);
				}
			} else {
				if (!collision(xMov, yMov)) {
					this.yPos += yMov;
				}
				yMov = 0;
			}
		}
	}
	
	/*protected boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 * -6 + 3) / 16;
			double yt = ((y + ya) + c / 2 * -6 + 3) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt); 
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}*/

	public void render(Screen screen) {
		screen.renderMob((int) xPos, (int) yPos, Sprite.rotate(sprite, angle), this);
	}
}
