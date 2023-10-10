public class NBodySimulator {
  private double timeStep;
  private int pauseTime;
  private boolean trace;
  private Universe universe;

  public NBodySimulator(Universe universe, double dt, int pt, boolean doTrace) {
    this.universe = universe;
    this.timeStep = dt;
    this.pauseTime = pt;
    this.trace = doTrace;
  }

  /*TODO: to simulate, first create a canvas and then iterate forever: clear the canvas with StdDraw.clear(), update the universe (which means the position of its bodies), draw the universe (which means the position of its bodies), do StdDraw  .show(), wait for some time that can be 0 with StdDraw.pause(pauseTime)*/
  public void simulate() {
    createCanvas();
    while (true) {
      StdDraw.clear();
      universe.update(timeStep);
      drawUniverse();
      StdDraw.show();
      StdDraw.pause(pauseTime);
    }
  }

  private void createCanvas() {
    //StdDraw.setCanvasSize(700, 700);
    StdDraw.enableDoubleBuffering();
    StdDraw.setPenRadius(0.025);
    double radius = universe.getRadius();
    StdDraw.setXscale(-radius, +radius);
    StdDraw.setYscale(-radius, +radius);
  }

  /*TODO : get the position (a Vector) of each body in universe and plot it with StdDraw.point(x,y). A Vector v returns its coordinates with v.cartesian(0) and v.cartesian(1). For this we need to ask to a Universe its number of bodies n and the position of the i-th body, i=0...n-1. Also, a Body must have a getPosition() method.*/
  private void drawUniverse() {
    Body[] planets = universe.getBodyPosition();
    for (Body planet : planets) {
      Vector pos = planet.getPosition();

      StdDraw.point(pos.cartesian(0), pos.cartesian(1));
    }
  }
}
