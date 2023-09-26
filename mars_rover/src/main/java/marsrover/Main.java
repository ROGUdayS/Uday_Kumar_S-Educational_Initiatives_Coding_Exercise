package mars_rover.src.main.java.marsrover;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an Option");
        System.out.println("1. Execute Code with Default Parameters");
        System.out.println("2. Execute Code with Custom Parameters");
        int option=sc.nextInt();
        Rover rover=null;
        switch(option){
            case 1:
                int[][] defaultObstacles={{2,2},{3,5}};
                rover=createGrid(10, 0, 0, 'N',defaultObstacles, sc);
                char defaultCommands[]={'M','M','R','M','L','M'};
                for(char command:defaultCommands){
                    try{    
                        executeCommand(command, rover, rover.getGrid());
                        rover.showPosition(rover.getGrid());
                    }catch(RuntimeException e){
                        System.out.println("Error : "+e.getMessage());
                        break;
                    }
                }
                System.out.println("Final Position :");
                System.out.println("Status Report: Rover is at (" + rover.getX() + "), " + rover.getY()
                    + "), facing " + rover.getDirection() + (rover.hasObstacleInFront(rover.getGrid()) ? " (Obstacle found)" : " (No Obstacles found)"));
                break;
            case 2:
                System.out.println("Input the grid size :");
                int gridSize=sc.nextInt();
                int startX, startY;
                char startDirection;
                int obstacleCount;
                do{
                    System.out.println("Enter starting X Position : ");
                    startX=sc.nextInt();
                }while(startX<0||startX>=gridSize);
                do{
                    System.out.println("Enter starting Y Position : ");
                    startY=sc.nextInt();
                }while(startY<0||startY>=gridSize);
                do{
                    System.out.println("Enter starting Direction ( N, S, E, W ) : ");
                    startDirection=sc.next().charAt(0);
                }while(startDirection!='N' && startDirection!='S' && startDirection!='W' && startDirection!='E');
                do{
                    System.out.println("Enter number of obstacles ");
                    obstacleCount=sc.nextInt();
                }while(obstacleCount<0||obstacleCount>gridSize*gridSize);
                int obstacles[][]=new int [obstacleCount][2];
                for(int i=0;i<obstacleCount;i++){
                    do{
                        System.out.println("Enter obstacle X : ");
                        obstacles[i][0]=sc.nextInt();
                    }while(obstacles[i][0]<0||obstacles[i][0]>=gridSize);
                    do{
                        System.out.println("Enter obstacle Y : ");
                        obstacles[i][1]=sc.nextInt();
                    }while(obstacles[i][1]<0||obstacles[i][1]>=gridSize);
                }
                rover=createGrid(gridSize, startX, startY, startDirection, obstacles, sc);
                while(true){
                    System.out.println("Enter a set of commands or type (exit) :");
                    String input =sc.next();
                    if(input.equalsIgnoreCase("exit")){
                        System.out.println("Final Position :");
                        System.out.println("Status Report: Rover is at (" + rover.getX() + "), " + rover.getY()+ "), facing " + rover.getDirection() + (rover.hasObstacleInFront(rover.getGrid()) ? " (Obstacle found)" : " (No Obstacles found)"));
                        break;
                    }
                    char commands[]=input.toCharArray();
                    for(char command:commands){
                        try{    
                            executeCommand(command, rover, rover.getGrid());
                            rover.showPosition(rover.getGrid());
                        }catch(RuntimeException e){
                            System.out.println("Error : "+e.getMessage());
                            break;
                    }
                }

                }
                break;
            default:
                System.out.println("Invalid Option Exiting Program Adios!");
                System.exit(0);
                break;
        }
    }

    public static Rover createGrid(int gridSize, int startX, int startY, char startdirection, int[][] obstacles ,Scanner sc){
        Grid grid = new Grid(gridSize);
        for(int[] obstcale:obstacles){
            grid.setObstacle(obstcale[0], obstcale[1], true);
        }
        Rover rover = new Rover(startX, startY, startdirection, grid);
        System.out.println("Initial Position : ");
        rover.showPosition(grid);
        return rover;
    }

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
}