package stupid_tank;

import java.awt.Point;

public class TrackInfo {
	public Point pos;
	public long time;
	public double velocity;
	public TrackInfo(Point p, long t, double v)
	{
		pos=p;
		time=t;
		velocity=v;
	}
	public TrackInfo()
	{
		pos=new Point();
	}
}
