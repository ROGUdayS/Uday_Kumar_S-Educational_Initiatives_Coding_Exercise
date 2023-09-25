package mars_rover.src.main.java.marsrover;

import java.util.Scanner;
public class Main {

    private static void executeCommand(char command, Rover rover, Grid grid){
        switch(command){
            case 'M':
                new MoveCommand(rover, grid).execute();;
                break;
            case 'L':
                new TurnLeftCommand(rover).execute();;
                break;
            case 'R':
                new TurnRightCommand(rover).execute();;
                break;
            default:
                throw new IllegalArgumentException("Invalid command : "+ command);
            }
    }

    private static void executeWithParameters(int gridSize,int startX,int startY,char startDirection,String commands,int[][] obstacles){
        Grid grid = new Grid(gridSize);
        for(int[] obstcale: obstacles){
            grid.setObstacle(obstcale[0], obstcale[1], true);
        }
        Rover rover = new Rover(startX, startY, startDirection);
        System.out.println("Initital Position :");
        rover.showPosition(grid);
        for(char command:commands.toCharArray()){
            try{
                executeCommand(command, rover, grid);
                System.out.println("After Command : "+ command);
                rover.showPosition(grid);
            }catch(RuntimeException e){
                System.out.println("Error : " + e.getMessage());
            }
            }
            System.out.println("Final Position :");
            rover.showPosition(grid);
            System.out.println("Status Report : Rover is at (" +rover.getX()+ ", " +rover.getY()+ ", facing " +rover.getDirection()+ ". No Obstacles detected.");
    }

    private static void executeWithGivenParameters(){
        int gridSize=10;
            Grid grid=new Grid(gridSize);
            grid.setObstacle(2,2,true);
            grid.setObstacle(3,5,true);
            Rover rover=new Rover(0,0,'N');
            char commands[]={'M','M','R','M','L','M'};
            System.out.println("Initital Position :");
            rover.showPosition(grid);

            for(char command:commands){
                try{
                    executeCommand(command, rover, grid);
                    System.out.println("After Command : "+ command);
                    rover.showPosition(grid);
                }catch(RuntimeException e){
                    System.out.println("Error : " + e.getMessage());
                }
            }
            System.out.println("Final Position :");
            rover.showPosition(grid);
            System.out.println("Status Report : Rover is at (" +rover.getX()+ ", " +rover.getY()+ "), facing " +rover.getDirection());
    }

    private static void executeWithUserParameters(Scanner sc){
        System.out.println("Enter the grid size : ");
        int gridSize=sc.nextInt();
        System.out.println("Enter starting position(x, y, direction) :");
        int startX=sc.nextInt();
        int startY=sc.nextInt();
        char startDirection=sc.next().charAt(0);
        sc.nextLine();

        System.out.println("Enter Commands (ex:'MMRMLM'):");
        String commands=sc.nextLine();

        System.out.println("Enter the number of onstacles :");
        int obstacleCount = sc.nextInt();
        int obstacles[][]=new int[obstacleCount][2];

        for(int i=0;i<obstacleCount;i++){
            System.out.print("Enter obstcale "+(i+1)+" Co-ordinates (x,y): ");
            obstacles[i][0]=sc.nextInt();
            obstacles[i][1]=sc.nextInt();
        }

        executeWithParameters(gridSize, startX, startY, startDirection, commands, obstacles);
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an Option :");
        System.out.println("1. Execute with given parameters");
        System.out.println("2. Enter Custom parameters with randomly generated obstacles");
        int option=sc.nextInt();
        switch(option){
            case 1:
                executeWithGivenParameters();
                break;
            case 2:
                executeWithUserParameters(sc);
                break;
            default:
                System.out.println("Invalid Option!");
                break;
        }
    }
}