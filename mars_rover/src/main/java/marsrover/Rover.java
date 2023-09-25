package mars_rover.src.main.java.marsrover;

public class Rover {
    private int x;
    private int y;
    private char direction;

    // Default Constructor if no parameters are declared then the rover will start the default co-ordinates
    // The Default positions are (0,0) facing the North direction
    public Rover(){
        x=0;
        y=0;
        direction='N';
    }

    // Constructor for (x,y) co-ordinates along with Direction is mentioned
    public Rover(int x, int y, char direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
    }

    // Setters
    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setDirection(char direction){
        this.direction=direction;
    }

    // Getteres
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public char getDirection(){
        return direction;
    }

    public void showPosition(Grid grid){
        int gridSize=grid.getSize();
        for(int row=gridSize-1;row>=0;row--){
            for(int col=0;col<gridSize;col++){
                char symbol = ' ';
                if(x==col&&y==row){
                    symbol='R';
                }
                else if(grid.hasObstacles(col, row)){
                    symbol='X';
                }
                System.out.print("["+symbol+"]");
            }
            System.out.println();
        }
        System.out.println("Direction : "+direction);
    }
}