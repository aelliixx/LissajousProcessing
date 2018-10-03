import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Lissajous extends PApplet {

float d = 40;
float angle = 0;

int cols, rows;

Curve[][] curves;


public void setup()
{
  
  cols = PApplet.parseInt(width / d);
  rows = PApplet.parseInt(height / d);

  curves = new Curve[rows][cols];

  for (int i = 0; i < rows; i++)
  {
    for (int j = 0; j < cols; j++)
    {
      curves[i][j] = new Curve()      ;
    }
  }
}

public void draw()
{
  background(0);

  float off = d - 10;
  float r = off / 2;





  for (int i = 0; i < cols - 1; i++)
  {    
    float xr = d + i * d + d / 2;
    float yr = d / 2;

    noFill();
    stroke(255);
    strokeWeight(2);
    ellipse(xr, yr, off, off);   


    float xp = r * cos(angle * (i + 1) - HALF_PI);
    float yp = r * sin(angle * (i + 1) - HALF_PI);

    strokeWeight(6);
    point(xr + xp, yr + yp);


    strokeWeight(1);
    stroke(255, 150);
    line(xr + xp, yr + yp, xr + xp, height);

    for (int j = 0; j < rows; j ++)
    {
      curves[j][i].setX(xr + xp);
    }
  }
  for (int i = 0; i < rows - 1; i++)
  {
    float xc = d / 2;
    float yc = d + i * d + d / 2;

    noFill();
    stroke(255);
    strokeWeight(2);
    ellipse(xc, yc, off, off);   


    float xp = r * cos(angle * (i + 1) - HALF_PI);
    float yp = r * sin(angle * (i + 1) - HALF_PI);

    strokeWeight(6);
    point(xc + xp, yc + yp);


    strokeWeight(1);
    stroke(255, 150);
    line(xc + xp, yc + yp, width, yc + yp);

    for (int j = 0; j < cols; j ++)
    {
      curves[i][j].setY(yc + yp);
    }
  }

  for (int i = 0; i < rows; i++)
  {
    for (int j = 0; j < cols; j++)
    {
      curves[j][i].addPoint();
      curves[j][i].displayCurve();
    }
  }  
  angle += 0.05f;
  println("FPS " + frameRate);
}
class Curve
{
  ArrayList<PVector> path;
  PVector current;

  Curve() 
  { 
    path = new ArrayList<PVector>();
    current = new PVector(0, 0);
  }
  
  public void setX(float x)
  {
    current.x = x;
  }
  
  public void setY(float y)
  {
    current.y = y;
  }
  
  public void addPoint()
  {
    path.add(current);
    current = new PVector();
  }
  
  public void displayCurve()
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
  public void settings() {  size(640, 640, P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Lissajous" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
