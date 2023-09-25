package mars_rover.src.main.java.marsrover;

public class MoveCommand implements Command {

    private Rover rover;
    private Grid grid;

    public MoveCommand(Rover rover, Grid grid){
        this.rover=rover;
        this.grid=grid;
    }
    public void execute(){
        int posX=rover.getX();
        int posY=rover.getY();
        char direction=rover.getDirection();

        int dx[] = {0,0,1,-1}; // Change in x co-ordinates for N, S, E, W
        int dy[] = {1,-1,0,0}; // Change in y co-ordinates for N, S, E, W

        int newDirectionIndex = "NSEW".indexOf(direction);
        if(newDirectionIndex == -1){
            throw new RuntimeException("Error : Invalid Direction!");
        }

        posX+=dx[newDirectionIndex];
        posY+=dy[newDirectionIndex];

        if(posX<0||posX>=grid.getSize()||posY<0||posY>=grid.getSize()){
            throw new RuntimeException("Error : The Rover is Out of Bounds!");
        }

        if(grid.hasObstacles(posX, posY)){
            throw new RuntimeException("Alert! Obstacle Ahead Rover not Safe to Move Ahead");
        }

        rover.setX(posX);
        rover.setY(posY);
    }
}