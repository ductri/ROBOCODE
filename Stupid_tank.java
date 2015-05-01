package stupid_tank;
import robocode.*;

import java.awt.Color;
import java.awt.Point;
/**
 * Stupid_tank - a robot by Duc Tri
 */
public class Stupid_tank extends AdvancedRobot
{
	boolean back=false;
	boolean movable=true;
	
	TrackInfo[] tracks=new TrackInfo[2];
	
	public void run() {
		for (int i=0;i<2;i++)
			tracks[i]=new TrackInfo();
		
		setColors(Color.red,Color.blue,Color.green); // body,gun,radar
	 	setAdjustRadarForGunTurn(true);
	 	setAdjustGunForRobotTurn(true);
	 	setAdjustRadarForRobotTurn(true);
	 	setInterruptible(true);
	 	 
	 	 double speed=8.0;
		while (movable)
		{
			setMaxVelocity(speed);
			
			setTurnRadarLeft(36000);
			//execute();
			
			setTurnRight(10000); 

			
	        if (!back)
	        	ahead(1000); 
	        else back(1000);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	
	
	
	int index=1;
	public void onScannedRobot(ScannedRobotEvent e) {
		index++;
		double enemy_world=getHeadingRadians()+e.getBearingRadians();
		tracks[index%2].pos.x=(int)(e.getDistance()*Math.cos((-enemy_world+Math.PI/2))+getX());
		tracks[index%2].pos.y=(int)(e.getDistance()*Math.sin((-enemy_world+Math.PI/2))+getY());
		tracks[index%2].time=e.getTime();
		tracks[index%2].time=e.getVelocity();
		
		
		System.out.println(tracks[index%2].pos.x);
		System.out.println(tracks[index%2].pos.y);
		System.out.println("%%%%%%%%%%%%%%%%");
		kill_it(0.1,tracks[index%2].pos.x,tracks[index%2].pos.y);
	}
	
	/*
	 * x,y is position of enemy in world
	 */
	public void kill_it(double size_bullet, int x, int y)
	{
		System.out.println("x="+x);
		System.out.println("y="+y);
		double enemy_world=0;
		if (Math.abs(y-getY())<0.001)
			if ((x-getX())>0)
				enemy_world=Math.PI/2.0;
			else enemy_world=-Math.PI/2.0;
		else
		{
			enemy_world=Math.atan(((float)(x-getX()))/((float)(y-getY())));
			int factor=(x-getX())>0?1:-1;
			if ((y-getY())<0)
			{
				enemy_world+=factor*Math.PI;
			}
		}
		
		System.out.println("enemy="+enemy_world);
		double angel=enemy_world-getGunHeadingRadians();
		if (Math.abs(angel)<(Math.PI*2-Math.abs(angel)))
			turnGunRightRadians(angel);
		else {
			if (angel>0)
				turnGunRightRadians(angel-Math.PI*2);
			else turnGunRightRadians(angel+Math.PI*2);
		}
		fire(size_bullet);
	}
	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10000);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		back=!back;
	}	
}
