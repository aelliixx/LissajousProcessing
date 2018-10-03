class Curve
{
  ArrayList<PVector> path;
  PVector current;

  Curve() 
  { 
    path = new ArrayList<PVector>();
    current = new PVector(0, 0);
  }
  
  void setX(float x)
  {
    current.x = x;
  }
  
  void setY(float y)
  {
    current.y = y;
  }
  
  void addPoint()
  {
    path.add(current);
    current = new PVector();
  }
  
  void displayCurve()
  {
    stroke(255);
    noFill();
    strokeWeight(2);
    
    beginShape();
    for(PVector v : path)
    {
      vertex(v.x, v.y);
    }
    endShape();
  }
  
}
