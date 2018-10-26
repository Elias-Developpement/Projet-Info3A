public class Collisions {
  public static boolean collisionRect(Point p, Rectangle r) {
    if(p.getX() >= r.getLeft() && p.getX() < r.getRight() && p.getY() >= r.getTop() && p.getY() < r.getBottom()) {
      return true;
    }

    return false;
  }

  public static boolean collisionOval(Point p, Circle c) {
    int distanceX = p.getX() - c.getX();
    int distanceY = p.getY() - c.getY();
    int distance = (int)(Math.sqrt((distanceX * distanceX) + (distanceY * distanceY)));

    if(distance <= c.getRadius()) {
      return true;
    }

    return false;
  }
}
