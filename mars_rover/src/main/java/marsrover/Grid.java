package mars_rover.src.main.java.marsrover;

public class Grid {
    private Cell[][] cells;

    public Grid(int size){
        if(size<=0){
            throw new IllegalArgumentException("Grid size must be greater than zero!");
        }
        cells = new Cell[size][size];
        // Lets Initialize the grid cells
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                cells[x][y]=new Cell();
            }
        }
    }

    public int getSize(){return cells.length;}

    // Checking for obstacles
    public boolean hasObstacles(int x, int y){
        if(x<0||x>=getSize()||y<0||y>=getSize()){
            throw new IllegalArgumentException("Invalid Co-Ordinates : (" + x + ", " + y + ")");
        }
        return cells[x][y].hasObstacles();
    }

    // Creating Obstacles in the grid
    public void setObstacle(int x, int y, boolean hasObstacles){
        if(x<0||x>=getSize()||y<0||y>=getSize()){
            throw new IllegalArgumentException("Invalid Co-Ordinates : (" + x + ", " + y + ")");
        }
        cells[x][y].setObstacle(hasObstacles);
    }
}