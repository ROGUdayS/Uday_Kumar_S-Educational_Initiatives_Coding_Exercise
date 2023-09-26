package mars_rover.src.main.java.marsrover;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
                rover=createGridWithCustomParameters(sc);
                executeCustomCommands(sc,rover);
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

//Hey this is my new logic I want the commands to stop executing if an obstacle is found I want the the rover to report Status Report : Rover is at (x, y), facing 'Direction' (Obstacle found or No Obstacles found)