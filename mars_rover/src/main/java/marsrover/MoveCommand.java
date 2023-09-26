package mars_rover.src.main.java.marsrover;

public class MoveCommand implements Command {

    private Rover rover;
    private Grid grid;

    public MoveCommand(Rover rover, Grid grid){
        this.rover=rover;
        this.grid=grid;
    }
    public void execute() {
        int nextPosition[] = rover.calulateNextPosition();
        int posX = nextPosition[0];
        int posY = nextPosition[1];
        if (posX < 0 || posX >= grid.getSize() || posY < 0 || posY >= grid.getSize()) {
            throw new RuntimeException("Error: The Rover is Out of Bounds!");
        }
        if (grid.hasObstacles(posX, posY)) {
            throw new RuntimeException("Alert! Obstacle Ahead. Rover cannot move.");
        }
        rover.setX(posX);
        rover.setY(posY);
    }
}