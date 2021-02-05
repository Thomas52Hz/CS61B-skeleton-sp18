
public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/**constouctor A*/
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;
	}

	/**constouctor B*/
	public Planet(Planet p){
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}

	/**function that can calculate the distance to a planet*/
	public double calcDistance(Planet p){
		double dx=p.xxPos-this.xxPos;
		double dy=p.yyPos-this.yyPos;
		double distance=Math.sqrt(dx*dx+dy*dy);
		return distance;
	}

	/**function that can calculate the force exert by a planet*/
	public double calcForceExertedBy(Planet p){
		double distance=this.calcDistance(p);
		double force=6.67e-11*this.mass*p.mass/(distance*distance);
		return force;
	}

	/**function that can calculate the force in x direction exerted by a planet */
	public double calcForceExertedByX(Planet p){
		double force=this.calcForceExertedBy(p);
		double distance=this.calcDistance(p);
		double forceX=(p.xxPos-this.xxPos)/distance*force;
		return forceX;
	}

	/**function that can calculate the force in y direction exerted by a planet */
	public double calcForceExertedByY(Planet p){
		double force=this.calcForceExertedBy(p);
		double distance=this.calcDistance(p);
		double forceY=(p.yyPos-this.yyPos)/distance*force;
		return forceY;
	}

	/**function that can calculate the force in x direction exerted by all planets */
	public double calcNetForceExertedByX(Planet[] pArr){
		double netForceX=0;
		for (Planet p: pArr) {
			if(this.equals(p)){
				continue;
			}
			netForceX+=this.calcForceExertedByX(p);
		}
		return netForceX;
	}

	/**function that can calculate the force in y direction exerted by all planets */
	public double calcNetForceExertedByY(Planet[] pArr){
		double netForceY=0;
		for (Planet p: pArr) {
			if(this.equals(p)){
				continue;
			}
			netForceY+=this.calcForceExertedByY(p);
		}
		return netForceY;
	}	

	/**update the position of the planet*/
	public void update(double dt, double xNetForce, double yNetForce){
		//calculate the acceleration
		double xAcceleration=xNetForce/this.mass;
		double yAcceleration=yNetForce/this.mass;

		//calculate new velocity
		this.xxVel=this.xxVel+xAcceleration*dt;
		this.yyVel=this.yyVel+yAcceleration*dt;

		//update the poistion
		this.xxPos=this.xxPos+this.xxVel*dt;
		this.yyPos=this.yyPos+this.yyVel*dt;
	}

	/**draw planet itself*/
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}