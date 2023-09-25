package mars_rover.src.main.java.marsrover;

public class Cell {
    private boolean hasObstacles;
    // Default Constructor
    public Cell(){this.hasObstacles=false;}

    public boolean hasObstacles(){return hasObstacles;}

    public void setObstacle(boolean hasObstacles){this.hasObstacles=hasObstacles;}

}