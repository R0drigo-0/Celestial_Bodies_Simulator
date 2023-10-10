import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Universe {
  private int numBodies;
  private double radius;
  private Body[] bodies;

  public Universe(String fname) {
    try {
      Scanner in = new Scanner(new FileReader(fname));
      numBodies = Integer.parseInt(in.next());
      System.out.println("Number of bodies = " + numBodies);

      // the set scale to draw on the canvas
      radius = Double.parseDouble(in.next());
      System.out.println("Radius = " + radius);

      // read and make the n bodies
      bodies = new Body[numBodies];
      for (int i = 0; i < numBodies; i++) {
        double rx = Double.parseDouble(in.next());
        double ry = Double.parseDouble(in.next());
        double vx = Double.parseDouble(in.next());
        double vy = Double.parseDouble(in.next());
        double mass = Double.parseDouble(in.next());
        double[] position = {rx, ry};
        double[] velocity = {vx, vy};
        Vector r = new Vector(position);
        Vector v = new Vector(velocity);
        bodies[i] = new Body(r, v, mass);
        System.out.println(bodies[i]);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /* TODO: first compute the force applied to each body as a result of the sum of gravitational forces by each other body. Then tell each body to move according to the force we have computed for it. Use plus() of Vector and forceFrom(), move() of Body  */
  void update(double dt) {
    for (Body body : bodies) {
      Vector sum = new Vector(2);
      for (Body auxBody : bodies) {
        if(body != auxBody)
        {
          sum = sum.plus(body.forceFrom(auxBody));
        }
      }
      body.move(sum, dt);
    }
  }

  public Body[] getBodyPosition(){
    return bodies;
  }

  public double getRadius() {
    return this.radius;
  }
}
