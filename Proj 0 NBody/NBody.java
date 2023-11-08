public class NBody {
	public static double readRadius(String address) {
		In in = new In(address);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String address) {
		In in =new In(address);
		int num = in.readInt();
		in.readDouble();
		Body[] nbody = new Body[num];

		for (int i=0; i<num; i++) {
			double posx = in.readDouble();
			double posy = in.readDouble();
			double velx = in.readDouble();
			double vely = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			nbody[i] = new Body(posx,posy,velx,vely,mass,name); 
		}
		return nbody;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] nbody = readBodies(filename);
		double radius = readRadius(filename);
		String imageToDraw = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		double[] xForces = new double[nbody.length];
		double[] yForces = new double[nbody.length];

		for (int time=0; time<T; time+=dt) {
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);

			for (int i=0; i<nbody.length; i++) {
			xForces[i]=nbody[i].calcNetForceExertedByX(nbody);
			yForces[i]=nbody[i].calcNetForceExertedByY(nbody);
			nbody[i].update(dt,xForces[i],yForces[i]);
			nbody[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", nbody.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < nbody.length; i += 1) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	nbody[i].xxPos, nbody[i].yyPos, nbody[i].xxVel,
                nbody[i].yyVel, nbody[i].mass, nbody[i].imgFileName);
    	}
	}
} 