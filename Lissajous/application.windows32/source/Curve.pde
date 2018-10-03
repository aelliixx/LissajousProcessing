class Curve
{
  ArrayList<PVector> path;
  PVector current;

  Curve() 
  { 
    path = new ArrayList<PVector>();
    current = new PVector();
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
    
    stroke(255, 0, 255);
    strokeWeight(6);
    point(current.x, current.y);
    
    current = new PVector();
  }
  
  void displayCurve(int row, int column, int rows, int columns)
  {
    colorMode(HSB);
    stroke(map(row * column, 1, rows * columns, 0, 255), 255, 255);
    noFill();
    strokeWeight(2);
    
    beginShape();
    for(PVector v : path)
    {
      vertex(v.x, v.y);
    }
    endShape();
  }
    
  void reset()
  {
    path.clear();
  }
  
}
