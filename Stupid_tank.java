package stupid_tank;
import robocode.*;
import java.awt.Color;
/**
 * Stupid_tank - a robot by Duc Tri
 */
public class Stupid_tank extends AdvancedRobot
{
	boolean back=false;
	boolean movable=true;
	public void run() {
		setColors(Color.red,Color.blue,Color.green); // body,gun,radar
	 	setAdjustRadarForGunTurn(true);
	 	setAdjustGunForRobotTurn(true);
	 	setAdjustRadarForRobotTurn(true);
	 	setInterruptible(true);
	 	setMaxVelocity(8);
		while (movable)
		{
			setTurnRadarLeft(360); 
	        
	        // Start moving (and turning)
	        if (!back)
	        	ahead(100);
	        else back(100);
	        
			System.out.println("ductri");
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		//movable=false;
		// Replace the next line with any behavior you would like
		System.out.println(e.getBearing());
		//setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
		System.out.println("Gun:"+getGunHeading());
		System.out.println("Bearing radar, head: "+e.getBearing());
		
		//double angel=getGunHeading()-(e.getBearing()-(360-getHeading()));
		double angel=getHeading()-getGunHeading()+e.getBearing();
		if (Math.abs(angel)<Math.abs(360-Math.abs(angel)))
			turnGunRight(angel);
		else {
			if (angel>0)
				turnGunRight(angel-360);
			else turnGunRight(angel+360);
		}
			
		
		
		System.out.println("360-..="+Math.abs(angel-360));
		System.out.println("..="+Math.abs(angel));
		
		fire(1);
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
		// Replace the next line with any behavior you would like
		back=!back;
		System.out.println("backing");
	}	
}
