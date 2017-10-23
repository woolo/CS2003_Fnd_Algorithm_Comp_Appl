
public class Inheritance_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sphere s = new Sphere();
		Ball b = new Ball();
		s.display();
		s = b;
		b.display();
		s.display();
	}

}
