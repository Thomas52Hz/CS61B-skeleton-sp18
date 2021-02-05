public class NBody{
	/**function that read the radius of the space*/
	public static double readRadius(String filename){
		In in=new In(filename);
		in.readInt();
		double radius=in.readDouble();
		return radius;
	}

	/**function that read data of all planets*/
	public static Planet[] readPlanets(String filename){
		In in =new In(filename);
		int numberOfPlanets=in.readInt();
		in.readDouble();
		Planet[] pArr=new Planet[numberOfPlanets];
		for (int i=0;i<numberOfPlanets ;i++ ) {
			double xxPos=in.readDouble();
			double yyPos=in.readDouble();
			double xxVel=in.readDouble();
			double yyVel=in.readDouble();
			double mass=in.readDouble();
			String imgFileName=in.readString();
			Planet p=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
			pArr[i]=p;
		}
		return pArr;
	}

	/**this is main function*/
	public static void main (String args[]){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] allPlanets=readPlanets(filename);
		double radius=readRadius(filename);

		//draw backgound
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        //draw planets
        for (Planet planet : allPlanets) {
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();
                
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            // Calculate the net forces for every planet            
            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }

            // Update positions and velocities of each planet         
            for (int i = 0; i < allPlanets.length; i++) {
            	allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            // Draw the background        
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // Draw all planets
            for (Planet planet : allPlanets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}