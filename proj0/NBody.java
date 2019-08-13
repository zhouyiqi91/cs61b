public class NBody
{

	public static double readRadius(String filePath) 
	{
        In in = new In(filePath);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    
    }

        /** Read planets info based on given file */
    public static Body[] readBodies(String filePath) {
        In in = new In(filePath);
        int number = in.readInt();
        double radius = in.readDouble();
        /* build planets based on given info */
        Body[] Bodies = new Body[number];
        for (int i = 0; i < number; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            Body b = new Body(xPos, yPos, xVel, yVel, mass, img);
            Bodies[i] = b;
        }
        return Bodies;
    }

    /* main */

    public static void main(String[] args)
    {
    	// read
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String filename = args[2];
    	double Radius = readRadius(filename);
    	Body[] Bodies = readBodies(filename);

    	//draw background
    	String imgPath = "./images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imgPath);
        StdDraw.show();

        //draw planet
        for (Body b : Bodies)
        {
        	b.draw();
        }

        //animation
        StdDraw.pause(10);
        for (double time = 0; time <= T; time += dt) 
        {
        int number = Bodies.length;
        double[] xForces = new double[number];
        double[] yForces = new double[number];

        for (int i=0;i<number;i++)
        {
        	xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);
        	yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);
        }

        for (int i=0;i<number;i++)
        {
        	Bodies[i].update(dt,xForces[i],yForces[i]);
        }

        StdDraw.picture(0, 0, imgPath);

        for (Body b:Bodies)
        {
        	b.draw();
        }

        StdDraw.show();
        int pauseTime = 10;
        StdDraw.pause(pauseTime);
    	}
    }


}