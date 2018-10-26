/*
 *    Projet Info 3A : Guillaume Lortet et Corentin Mansuy
 *    Dernière modification 26/10/18
 *
 *
 *    Le fichier Collisions contient les méthodes permettant de savoir si un point se trouve dans un des obstacles
 *    Les fichiers Cirlce, Point, Rectangle permettent de modéliser les éléments graphiques du programme (obstacles, trajectoires)
 */

import java.awt.*;
import java.math.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Main extends Frame {
  int width, height;
  List<Rectangle> rectangles = new ArrayList<Rectangle>();
  List<Circle> circles = new ArrayList<Circle>();
  List<Point> points = new ArrayList<Point>();

  public void windowsInitialization() {
    width = getSize().width;
    height = getSize().height;
    setBackground(Color.white);
  }

  public void generateObstacles(Graphics g, int obstaclesCount) {
    g.setColor(Color.black);
    for(int i = obstaclesCount; i > 0; i--) {
      int randomX = (int)(Math.random() * 600 + 100);
      int randomY = (int)(Math.random() * 560 + 100);
      int randomWidth = (int)(Math.random() * 200 + 1);
      int randomHeight = (int)(Math.random() * 140 + 1);

      Rectangle r = new Rectangle(randomX, randomY, randomWidth, randomHeight);
      rectangles.add(r);

      // Draw this rectangle
      g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    for(int i = obstaclesCount; i > 0; i--) {
      int randomX = (int)(Math.random() * 600 + 100);
      int randomY = (int)(Math.random() * 560 + 100);
      int randomRadius = (int)(Math.random() * 60 + 20);

      Circle c = new Circle(randomX, randomY, randomRadius);
      circles.add(c);

      // Draw this rectangle
      g.fillOval(c.getX(), c.getY(), c.getRadius(), c.getRadius());
    }
  }

  public void generatePoints(Graphics g, int pointsNumber) {
    for(int i = pointsNumber; i > 0; i--) {
      int randomX = (int)(Math.random() * 750 + 60);
      int randomY = (int)(Math.random() * 550 + 60);

      Point p = new Point(randomX, randomY);
      points.add(p);
    }
  }

  public void generateQuickestWay(Graphics g) {
    g.setColor(Color.red);

    // Drawable points list
    List<Point> tempPoints = new ArrayList<Point>();

    for(int i = 0; i < points.size(); i++) {
      for(int j = 0; j < rectangles.size(); j++) {
        for(int k = 0; k < circles.size(); k++) {
          if(!Collisions.collisionRect(points.get(i), rectangles.get(j)) && !Collisions.collisionOval(points.get(i), circles.get(k))) {
            tempPoints.add(points.get(i));
          }
        }
      }
    }

    for(int i = 0; i < tempPoints.size() - 1; i++) {
      g.drawLine(tempPoints.get(i).getX(), tempPoints.get(i).getY(), tempPoints.get(i + 1).getX(), tempPoints.get(i + 1).getY());
    }
  }

  public void paint(Graphics g) {
    g.setColor(Color.black);

    generateObstacles(g, 5);
    generatePoints(g, 20);
    generateQuickestWay(g);
  }

  public static void main(String args[]) {
    Main main = new Main();
    main.windowsInitialization();

    main.setBounds(0, 0, 1000, 900);
    main.setVisible(true);
  }
}
