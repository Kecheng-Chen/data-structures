import java.lang.Math;

public class Body {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	private static double grav=6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;
	}

	public Body(Body p) {
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}

	public double calcDistance(Body p) {
		double dx, dy;
		dx=p.xxPos-this.xxPos;
		dy=p.yyPos-this.yyPos;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public double calcForceExertedBy(Body p) {
		return (grav*this.mass*p.mass)/(this.calcDistance(p)*this.calcDistance(p));
	}

	public double calcForceExertedByX(Body p) {
		double dx;
		dx=p.xxPos-this.xxPos;
		return (this.calcForceExertedBy(p)*dx)/this.calcDistance(p);
	}

	public double calcForceExertedByY(Body p) {
		double dy;
		dy=p.yyPos-this.yyPos;
		return (this.calcForceExertedBy(p)*dy)/this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Body[] allBodies) {
		double netx=0;
		for (int i=0; i<allBodies.length; i++) {
			if (this.equals(allBodies[i])) {
				continue;
			} else {
				netx+=this.calcForceExertedByX(allBodies[i]);
			}
		}
		return netx;
	}

	public double calcNetForceExertedByY(Body[] allBodies) {
		double nety=0;
		for (int i=0; i<allBodies.length; i++) {
			if (this.equals(allBodies[i])) {
				continue;
			} else {
				nety+=this.calcForceExertedByY(allBodies[i]);
			}
		}
		return nety;
	}

	public void update(double dt, double fx, double fy) {
		this.xxVel+=fx/this.mass*dt;
		this.yyVel+=fy/this.mass*dt;
		this.xxPos+=this.xxVel*dt;
		this.yyPos+=this.yyVel*dt;
	}

	public void draw() {
		String imageToDraw = "images/"+this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
	}
}