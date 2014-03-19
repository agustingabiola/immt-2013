package immt.algorithms.structures;

public class Vector2D {
	
	private double x;
	private double y;
	
	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2D getOrthogonal(){
		return new Vector2D(-y,x);
	}
	
	public Vector2D getOrthonormal(){
		Vector2D v = getOrthogonal();
		v.normalize();
		return v;
	}

	public void normalize(){
		double module = Math.sqrt(x*x+y*y);
		x = x / module;
		y = y / module;
	}
	
	public Vector2D minus(Vector2D v2){
		Vector2D v = new Vector2D(this.x-v2.getX(),this.y-v2.getY());
		return v;
	}
	
	public double prodInterno(Vector2D v2){
		double interno = this.x*v2.getX()+this.y*v2.getY();
		return interno;
	}
	
	public Vector2D add(Vector2D v2){
		Vector2D v = new Vector2D(this.x+v2.getX(),this.y+v2.getY());
		return v;
	}
	
	public double getModule(){
		double interno = this.prodInterno(this);
		interno = Math.sqrt(interno);
		return interno;
	}
	
	public double getX() {return x;}
	public void setX(double x) {this.x = x;}
	public double getY() {return y;}
	public void setY(double y) {this.y = y;}

	
}
