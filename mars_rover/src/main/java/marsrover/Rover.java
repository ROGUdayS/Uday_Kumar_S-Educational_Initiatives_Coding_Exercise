package mars_rover.src.main.java.marsrover;

public class Rover {
    private int x;
    private int y;
    private char direction;
    private Grid grid;

    // Constructor for (x,y) co-ordinates along with Direction is mentioned
    public Rover(int x, int y, char direction, Grid grid){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.grid=grid;
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
    public Grid getGrid(){
        return grid;
    }

    public int [] calulateNextPosition(){
        int posX=x;
        int posY=y;
        char direction=getDirection();

        int dx[] = {0,0,1,-1}; // Change in x co-ordinates for E, W
        int dy[] = {1,-1,0,0}; // Change in y co-ordinates for N, S

        int newDirectionIndex = "NSEW".indexOf(direction);
        if(newDirectionIndex == -1){
            throw new RuntimeException("Error : Invalid Direction!");
        }

        posX+=dx[newDirectionIndex];
        posY+=dy[newDirectionIndex];
        return new int[]{posX, posY};
    }

    public boolean hasObstacleInFront(Grid grid){
        int nextPosition[]=calulateNextPosition();
        int posX=nextPosition[0];
        int posY=nextPosition[1];
        if(posX<0||posX>=grid.getSize()||posY<0||posY>=grid.getSize()){
            throw new RuntimeException("Error : The Rover is Out of Bounds!");
        }
        return grid.hasObstacles(posX, posY);
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