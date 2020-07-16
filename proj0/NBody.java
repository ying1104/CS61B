public class NBody {
	/**Read and return the radius from given file*/
	public static double readRadius(String fileName) {
		In file = new In(fileName);
		file.readInt();
		double r = file.readDouble();
		return r;
	}


	/**Read and return array of Planets*/
	public static Planet[] readPlanets(String fileName) {
		In file = new In(fileName);
		int N = file.readInt();
		file.readDouble();

		Planet[] planets = new Planet[N];
		int row = 0;

		while (row < N) {
			double xP = file.readDouble();
			double yP = file.readDouble();

			double xV = file.readDouble();
			double yV = file.readDouble();

			double m = file.readDouble();
			String i = file.readString();

			Planet planet = new Planet(xP, yP, xV, yV, m, i);
			planets[row] = planet;
			row++;

		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		// set the scale to match the radius of universe
		double r = NBody.readRadius(filename);
		In file = new In(filename);
		int N = file.readInt();
		StdDraw.setScale(-r, r);

		//set image as background
		StdDraw.picture(0, 0, "images/starfield.jpg", 2 * r, 2 * r);

		Planet[] planets = readPlanets(filename);
		for (Planet p : planets) {
			p.draw();
		}

		//Enable double buffering to make the animation look good.
		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time < T) {
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			int row = 0;
			while (row < N) {
				double sumNetX = 0;
				double sumNetY = 0;
				sumNetX += planets[row].calcNetForceExertedByX(planets);
				sumNetY += planets[row].calcNetForceExertedByY(planets);	
				xForces[row] = sumNetX;
				yForces[row] = sumNetY;

				row++;
				
			}

			for (int num = 0; num < N; num++) {
				planets[num].update(dt, xForces[num], yForces[num]);
			}
			//Draw the background image
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg", 2 * r, 2 * r);
			StdDraw.setScale(-r, r);

			//Draw all the planets
			for (Planet p : planets) {
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}
		StdOut.printf("%d\n", planets.length);
  		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
   			 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
             planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
             planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}








		}


	

}

	