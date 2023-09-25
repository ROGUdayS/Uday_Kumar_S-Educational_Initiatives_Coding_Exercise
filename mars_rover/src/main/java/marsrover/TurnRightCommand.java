package mars_rover.src.main.java.marsrover;

public class TurnRightCommand implements Command{
    private Rover rover;
    
    public TurnRightCommand(Rover rover){ this.rover = rover; }

    public void execute(){
        char directions[]={'N', 'E', 'S', 'W'};
        char currentDirection=rover.getDirection();
        int currentIndex=new String(directions).indexOf(currentDirection);

        if(currentIndex==-1){
            throw new RuntimeException("Error : Invalid Direction!");
        }

        char newDirection=directions[(currentIndex+1)%4];
        rover.setDirection(newDirection);
    }   
}