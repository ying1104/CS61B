import java.lang.Math;

public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	static final double g = 6.67e-11;
	

	/**First constructor for the class.*/
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**Second constructor for the class.*/
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/**Calulate the distance between two planets.*/
	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		
		return r;
	}

	/**Return the force exerted on this planet.*/
	public double calcForceExertedBy(Planet p) {
		
		double force = (g * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
		return force;
	}

	/**Return force exerted in the X direction.*/
	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);
		double totalForce = this.calcForceExertedBy(p);
		double xForce = totalForce * dx / r;
		return xForce;
	}

	/**Return force exerted in the Y direction.*/
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);
		double totalForce = this.calcForceExertedBy(p);
		double yForce = totalForce * dy / r;
		return yForce;
	}

	/**Return the net X force exerted by all planets.*/
	public double calcNetForceExertedByX(Planet[] p) {
		double sumX = 0.0;
		for (Planet i : p) {
			if (!this.equals(i)) {
				sumX += this.calcForceExertedByX(i);
			}	
		}
		return sumX;
	}

	/**Return the net Y force exerted by all planets.*/
	public double calcNetForceExertedByY(Planet[] p) {
		double sumY = 0.0;
		for (Planet i : p) {
			if (!this.equals(i)) {
				sumY += this.calcForceExertedByY(i);
			}
			
		}
		return sumY;
	}

	/**Update planet's position and velocity instance variables*/
	public void update(double dt, double fX, double fY) {
		/*Calculate acceleration*/
		double aX = fX / this.mass;
		double aY = fY / this.mass;

		xxVel += dt * aX;
		yyVel += dt * aY;

		xxPos += dt * xxVel;
		yyPos += dt * yyVel;


	}

	/**Draw the planet at its appropriate position*/
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}


}