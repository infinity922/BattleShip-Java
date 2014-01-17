import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class user {
	static boolean user_board[][] = new boolean[10][10];
	static int user_battleShips[] = new int[] {2,2,1,1,1};
		static int user_carrier[][] = new int[2][5];
		static int user_battleShip[][] = new int[2][4];
		static int user_cruiser[][] = new int[2][3];
		static int user_destroyer1[][] = new int[2][2];
		static int user_destroyer2[][] = new int[2][2];
		static int user_submarine1[][] = new int[2][1];
		static int user_submarine2[][] = new int[2][1];

	public static void main(String[] args) throws IOException {


		placeShips(user_board,user_carrier,1,2,false);
		placeShips(user_board,user_battleShip,1,2,false);
		placeShips(user_board,user_cruiser,1,2,false);
		placeShips(user_board,user_destroyer1,1,2,false);
		placeShips(user_board,user_destroyer2,1,2,false);
		placeShips(user_board,user_submarine1,1,2,false);
		placeShips(user_board,user_submarine2,1,2,false);
        placeAllShips();
        
        System.out.println("=====Aircraft Carrier=====");
        printShip(enemy_carrier);
        System.out.println("==========================");
        
        System.out.println("=====Battleship=====");
        printShip(enemy_battleShip);
        System.out.println("==========================");
        
        System.out.println("=====enemy_cruiser=====");
        printShip(enemy_cruiser);
        System.out.println("==========================");
        
        System.out.println("=====enemy_destroyer1=====");
        printShip(enemy_destroyer1);
        System.out.println("==========================");
        
        System.out.println("=====enemy_destroyer2=====");
        printShip(enemy_destroyer2);
        System.out.println("==========================");
        
        System.out.println("=====enemy_submarine1=====");
        printShip(enemy_submarine1);
        System.out.println("==========================");
        
        System.out.println("=====enemy_submarine2=====");
        printShip(enemy_submarine2);
        System.out.println("==========================");
	}
    public static void placeShips(boolean[][] board, int ship[][], int x, int y, boolean rotate){
    	Scanner input = new Scanner (System.in);
    	x = input.nextInt()-1;
		y = input.nextInt()-1;
		rotate = input.nextBoolean();
		
				if(rotate == true){
					for(int i = 0; i < ship[0].length ;i++){
							user_board[x+i][y] = false;
					}
					for(int i = 0; i < ship[0].length ;i++){
						if (check(x+i,y) == true){
							user_board[x][y+i] = true;
							ship[0][i] = x;
							ship[1][i] = y+i;
						}else{
							System.out.println("WARN TRUE");
						}
					}
				}else{
					for(int i = 0; i < ship[0].length ;i++){
						if (check(x+i,y) == true){
							user_board[x+i][y] = true;
							ship[0][i] = x+i;
							ship[1][i] = y;
						}else{
							System.out.println("WARN FALSE");
						}
					}
					
				}
				print();
    	}
    
public static void print(){
	for(int i = 0;i < 10;i++){
		for(int j = 0;j < 10;j++){
			if (user_board[i][j] == true){
				System.out.print("T"+ " ");
			}else {
				System.out.print("-"+ " ");
			}
		}
		System.out.println();
	}
}

public static boolean check(int x, int y){
	if (user_board[x][y] == true){
		return false;
	}else{
		return true;
	}
}

public static boolean hit(int x, int y){
	if (user_board[x][y] == false){
		user_board[x][y] = true;
		return true;
	}else{
		return false;
	}
}

    
    
    
    private static int [][] enemy_carrier = new int[2][5];
    private static int [][] enemy_battleShip = new int[2][4];
    private static int [][] enemy_cruiser = new int[2][3];
    private static int [][] enemy_destroyer1 = new int[2][2];
    private static int [][] enemy_destroyer2 = new int[2][2];
    private static int [][] enemy_submarine1 = new int[2][1];
    private static int [][] enemy_submarine2 = new int[2][1];
    
    public static void placeAllShips(){
        /////place all ships in a random position
        
        boolean [][]shipsMap = new boolean[10][10];
    /*
     * Map of the board to see if there is a ship who is taking up the spots
     * false - empty
     * true  - taken
     */
        
        placeShip(shipsMap, enemy_carrier);
        placeShip(shipsMap, enemy_battleShip);
        placeShip(shipsMap, enemy_cruiser);
        placeShip(shipsMap, enemy_destroyer1);
        placeShip(shipsMap, enemy_destroyer2);
        placeShip(shipsMap, enemy_submarine1);
        placeShip(shipsMap, enemy_submarine2);
        
        printMap(shipsMap);
        
    }
    
    public static boolean mapEmptyInCoordRange(boolean[][] shipsMap, int row1, int row2, int col1, int col2){
        //returns true if the spots in the range in shipsMap are empty, otherwise false
        
        for (int i = row1; i <= row2; i++){
            for (int j = col1; j <= col2; j++){
                if (shipsMap[i][j] == true){
                    return false;
                }
            }
            
        }
        return true;
    }
    
    public static void placeShip(boolean[][] shipsMap, int ship[][]){
        //places a single ship on the map in a randomly generated location, with
        //randomly generated direction.
        
        Random randomGenerator = new Random();
        int rowCoord;
        int colCoord;
        int direction; //0=horizontal, 1=vertical
        boolean placed = false;
        
        int maxCoord = 10 - ship[0].length;
        
        /////placing enemy_carrier
        direction = randomGenerator.nextInt(2);
        
        while (!placed){
            rowCoord = randomGenerator.nextInt(10);
            colCoord = randomGenerator.nextInt(10);
            
            if (direction==0){//ship will be placed horizontally
                if ((colCoord<=maxCoord)&&(mapEmptyInCoordRange(shipsMap, rowCoord, 
                        rowCoord, colCoord, colCoord + ship[0].length -1))){
                    for (int i = 0; i < ship[0].length; i++){
                        //set the ship's ccoordinates
                        ship[0][i] = rowCoord;
                        ship[1][i] = colCoord + i;
                        
                        //set the shipsMap to show the coordinates have been taken
                        shipsMap[rowCoord][colCoord + i] = true;
                    }
                    placed = true;
                }
            }
            
            else if (direction==1){//ship will be placed vertically
                if ((rowCoord<=maxCoord)&&(mapEmptyInCoordRange(shipsMap, rowCoord,
                        rowCoord + ship[0].length - 1, colCoord, colCoord))){
                    for (int i = 0; i< ship[0].length; i++){
                        //set the ship's coordinates
                        ship[0][i] = rowCoord+i;
                        ship[1][i] = colCoord;
                        
                        //set the shipsMap to show the coordinates have been taken
                        shipsMap[rowCoord+i][colCoord] = true;
                    }
                    placed = true;
                }
            }
        }
    }
    
    public static void printShip(int[][] ship){
        //simply for testing purposes. this method prints out the coordinates of a ship
        
        for (int i =0; i < ship.length; i++) {
            for (int j = 0; j < ship[0].length; j++) {
                System.out.print(" " + ship[i][j]);
            }
            System.out.println("");
        }
    }
    
    
    //you don't need the following crap
    //<editor-fold>
    
    public static void printMap(boolean[][] shipsMap){
        //simply for testing purposes, this method prints out the shipsMap and shows where
        //all the ships are located
        
        for (int i =0; i < shipsMap.length; i++) {
            for (int j = 0; j < shipsMap[0].length; j++) {
                if(shipsMap[i][j]==true){
                    System.out.print(" #");}
                else if(shipsMap[i][j]==false){
                    System.out.print(" -");}
            }
            System.out.println("");
        }
    }


    public static int[][] getAirCarrier(){
        return enemy_carrier;
    }
    
    public static int[][] getBattleship(){
        return enemy_battleShip;
    }
    
    public static int[][]getCruiser(){
        return enemy_cruiser;
    }
    
    public static int[][]getDestroyer1(){
        return enemy_destroyer1;
    }
    
    public static int[][]getDestroyer2(){
        return enemy_destroyer2;
    }
    
    public static int[][]getSubmarine1(){
        return enemy_submarine1;
    }
    
    public static int[][]getSubmarine2(){
        return enemy_submarine2;
    }
    //</editor-fold>
}
//System.out.println(user_carrier[0].length);
/*boolean start = false;
boolean reset = false;
boolean next = true;
boolean rotate = false;
Scanner input = new Scanner (System.in);

while(start == false && reset == false){
	int x = input.nextInt()-1;
	int y = input.nextInt()-1;
	rotate = input.nextBoolean();
	while(user_battleShips[0] != 0){
		if(x<= 6){
		if(user_battleShips[0] == 1){
			for(int i = 0; i < 5;i++){
				if (check(x+i,y) == true){
					user_board[x+i][y] = true;
					user_carrier[0][i] = x+i;
					user_carrier[1][i] = y;
				}else{
					System.out.println("WARN1");
				}
			}
			if (rotate == true){
				for(int i = 0; i < 5;i++){
					user_board[x+i][y] = false;
				}
				for(int i = 0; i < 5;i++){
					if(y<= 6){
					if (check(x,y+i) == true){
					user_board[x][y+i] = true;
					user_carrier[0][i] = x;
					user_carrier[1][i] = y+i;
					}else{
						System.out.println("WARN2");
					}
					}else{
						System.out.println("RETYPE ");
						 x = input.nextInt()-1;
						 y = input.nextInt()-1;
						rotate = input.nextBoolean();
					}
				}
				rotate = false;
			}
			if (next == true){
				user_battleShips[0] = 0;
			}
			}
	print();
	x = input.nextInt()-1;
	y = input.nextInt()-1;
	rotate = input.nextBoolean();
		}else{
			System.out.println("RETYPE 2");
			 x = input.nextInt()-1;
			 y = input.nextInt()-1;
			rotate = input.nextBoolean();
			
		}
	}

	//place size 4 ship
	while(user_battleShips[1] != 0){
	if(user_battleShips[1] == 1){
		for(int i = 0; i < 4;i++){
			if (check(x+i,y) == true){

				user_board[x+i][y] = true;
				user_battleShip[0][i] = x+i;
				user_battleShip[1][i] = y;
			}else{
				System.out.println("WARN3");
			}
		}
		if (rotate == true){
			for(int i = 0; i < 4;i++){
				user_board[x+i][y] = false;
			}
			for(int i = 0; i < 4;i++){
				if (check(x+i,y) == true){
				user_board[x][y+i] = true;
				user_battleShip[0][i] = x+i;
				user_battleShip[1][i] = y;
				}else{
					System.out.println("WARN4");
				}
			}
			rotate = false;
		}
		if (next == true){
			user_battleShips[1] = 0;
		}
		}
	
	
	print();
	x = input.nextInt()-1;
	y = input.nextInt()-1;
	rotate = input.nextBoolean();
	}
	//place size 3 ship
	while(user_battleShips[2] != 0){
	if(user_battleShips[2] == 1){
		for(int i = 0; i < 3;i++){
			if (check(x+i,y) == true){
			user_board[x+i][y] = true;
			user_cruiser[0][i] = x+i;
			user_cruiser[1][i] = y;
		}else{
			System.out.println("WARN5");
		}
		}
		if (rotate == true){
			for(int i = 0; i < 3;i++){
				user_board[x+i][y] = false;
			}
			for(int i = 0; i < 3;i++){
				if (check(x+i,y) == true){
				user_board[x][y+i] = true;
				user_cruiser[0][i] = x+i;
				user_cruiser[1][i] = y;
			}else{
				System.out.println("WARN6");
			}
			}
			rotate = false;
		}
		if (next == true){
			user_battleShips[2] = 0;
		}
		}
	
	print();
	x = input.nextInt()-1;
	y = input.nextInt()-1;
	rotate = input.nextBoolean();
	}
	//place size 2 ship
	while(user_battleShips[3] != 0){
	if(user_battleShips[3] > 0){
		for(int i = 0; i < 2;i++){
			if (check(x+i,y) == true){
			user_board[x+i][y] = true;
			if (user_battleShips[3] == 2){
				user_destroyer1[0][i] = x+i;
				user_destroyer1[1][i] = y;
			}else {
				user_destroyer2[0][i] = x+i;
				user_destroyer2[1][i] = y;
			}
		}else{
			System.out.println("WARN7");
		}
		}
		if (rotate == true){
			for(int i = 0; i < 2;i++){
				user_board[x+i][y] = false;
			}
			for(int i = 0; i < 2;i++){
				if (check(x+i,y) == true){
				user_board[x][y+i] = true;
				if (user_battleShips[3] == 2){
					user_destroyer1[0][i] = x+i;
					user_destroyer1[1][i] = y;
				}else {
					user_destroyer2[0][i] = x+i;
					user_destroyer2[1][i] = y;
				}
			}else{
				System.out.println("WARN8");
			}
			}
		}
		if (next == true){
			user_battleShips[3] --;
		}
		}
	
	print();
	x = input.nextInt()-1;
	y = input.nextInt()-1;
	rotate = input.nextBoolean();
	}
	//place size 1 ship
	while(user_battleShips[4] != 0){
	if(user_battleShips[4] > 0){
			user_board[x][y] = true;
			if (user_battleShips[4] == 2){
				user_submarine1[0][0] = x;
				user_submarine1[1][0] = y;
			}else {
				user_submarine2[0][0] = x;
				user_submarine2[1][0] = y;
			}
		if (next == true){
			user_battleShips[4] --;
		}
	}
	print();
	x = input.nextInt()-1;
	y = input.nextInt()-1;
	rotate = input.nextBoolean();
	}
	System.out.println("WARN9");
}*/
