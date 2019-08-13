public class Body
{
	public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
	public Body(double xP,double yP,double xV,double yV,double m,String img)
	{
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	
	public Body(Body b)
	{
		this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
	}
	
	public double calcDistance(Body b)
	{
		double distance = Math.sqrt(Math.pow(this.xxPos-b.xxPos,2) + Math.pow(this.yyPos-b.yyPos,2) );
		return distance;
	}

	public double calcForceExertedBy(Body b) 
	{
        double distance = this.calcDistance(b);
        double G = 6.67e-11;
        double force = G * this.mass * b.mass / Math.pow(distance, 2);
        return force;
    }


    public double calcForceExertedByX(Body b)
    {
    	double NetForce = calcForceExertedBy(b);
    	double Distance = calcDistance(b);
    	double dx = b.xxPos - this.xxPos;
    	double ReturnVal;
    	if (Distance==0)
    	{
    		ReturnVal = 0;
    	} else {
    		ReturnVal = NetForce * dx/Distance;
    	}
    	return ReturnVal;
    }


    public double calcForceExertedByY(Body b)
    {
    	double NetForce = calcForceExertedBy(b);
    	double Distance = calcDistance(b);
    	double dy = b.yyPos - this.yyPos;
    	double ReturnVal;
    	if (Distance==0)
    	{
    		ReturnVal = 0;
    	} else {
    		ReturnVal = NetForce * dy/Distance;
    	}
    	return ReturnVal;
    }

    public double calcNetForceExertedByX(Body [] ba)
    {
    	double NetForceByX = 0;
    	for (Body b:ba)
    	{
    		NetForceByX += calcForceExertedByX(b);

    	}
    	return NetForceByX;
    }

    public double calcNetForceExertedByY(Body [] ba)
    {
    	double NetForceByY = 0;
    	for (Body b:ba)
    	{
    		NetForceByY += calcForceExertedByY(b);

    	}
    	return NetForceByY;
    }

    /* Update the position and velocity info based on give time and force*/
    public void update(double dt, double fX, double fY) 
    {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw()
    {
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
		StdDraw.show();
    }
}	