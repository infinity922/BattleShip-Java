import java.util.Random;
import java.util.Scanner;

public class Battleship {

static int battleShip[][] = new int[][] {
{2,3 , 4, 5},
{2, 2, 2, 2}
};
static int carrier[][] = new int[][] {
{2,2,2,2,2},
{5,6,7,8,9}

};
static int cruiser[][] = new int[][] {
{5,6,7},
{4,4,4}
};
static int destroyer1[][] = new int[][] {
{9,9},
{7,8}
};
static int destroyer2[][] = new int[][] {
{4,5},
{9,9}
};
static int submarine1[][] = new int[][] {
{3},
{5}
};
static int submarine2[][] = new int[][] {
{7},
{7}
};

static boolean board[][] = new boolean[10][10];
static boolean sunk[] = new boolean[7];
static int directionHit = 0; // 0:right 1:up 2:left 3:down after a hit
static int prevHit[] = new int[2];
static boolean lastShot = false; //false is last comp shot was miss, true if hit
static boolean userTurn = false; //switch between user and comp turn

public static void main(String[] args) {

Random r = new Random();

int x ;
int y ;


Scanner in = new Scanner(System.in);
while(gameOver() == false){

while (userTurn == false){
if (lastShot == false){

x = r.nextInt(10) + 1; //computers random shot x-cor
y = r.nextInt(10) + 1; //computers random shot y-cor


if (Hit(x,y) && board[x][y] == true){ //comp hit the same ship twice in the same spot, shouldnt be possible, comp generate a new shot
x = r.nextInt(10) + 1;
y = r.nextInt(10) + 1;
}
if (Hit(x,y) && board[x][y] == false){ // comp hit a ship for the first time
System.out.println("HIT!!!");
board[x][y] = true;
prevHit[0] = x;
prevHit[1] = y;
userTurn = true;
lastShot = true;
if (sunkShip()){
lastShot = false;
System.out.println("Sunk");
}

}
else if (Hit(x,y) == false){ // comp missed
System.out.println("MISS");
userTurn = true;
lastShot = false;
}
else{
//do nothing
}
}
else if (lastShot == true) {

// x = r.nextInt(10) + 1; //computers random shot x-cor
//y = r.nextInt(10) + 1; //computers random shot y-cor

switch(directionHit) {
case 0: if ( Hit(prevHit[0], prevHit[1] + 1) == true && board[prevHit[0]][prevHit[1] + 1] == false){ //hit right after hitting once
System.out.println("Hit");
lastShot = true;
prevHit[0] = prevHit[0];
prevHit[1] = prevHit[1] + 1;
directionHit = 0; //shoot right next time
userTurn = true;
board[prevHit[0]][ prevHit[1]] = true;
if (sunkShip()){
lastShot = false;
System.out.println("sunk");
};

}
else if ( Hit(prevHit[0], prevHit[1] +1 ) == false){ // miss right after a hit
lastShot = true;
userTurn = true;
System.out.println("MISS");
directionHit = 1; //shoot left time
}
else if ( Hit(prevHit[0], prevHit[1] +1 ) == true && board[prevHit[0]][prevHit[1] +1] == true){ //hit the same spot twice, ******
userTurn = false;
lastShot = true;

directionHit = 1;
}
break;

case 1: if ( Hit(prevHit[0] - 1 , prevHit[1]) == true && board[prevHit[0] - 1][prevHit[1]] == false){ //hit above after hitting once
System.out.println("HIT!!!");
lastShot = true;
prevHit[0] = prevHit[0] - 1;
prevHit[1] = prevHit[1] ;
directionHit = 1; //shoot up next time
userTurn = true;
board[prevHit[0]][prevHit[1]] = true;
if (sunkShip()){
lastShot = false;
System.out.println("sunk");
};
}
else if ( Hit(prevHit[0] - 1 , prevHit[1]) == false){ //miss above after a hit
lastShot = true;
userTurn = true;
directionHit = 2; //shoot left of hit next time
System.out.println("MISS");
}
else if ( Hit(prevHit[0] - 1 , prevHit[1]) == true && board[prevHit[0] - 1][prevHit[1]] == true){ //hit the same spot twice, ******
userTurn = false;
lastShot = true;
directionHit = 2;
}
break;
case 2: if ( Hit(prevHit[0] , prevHit[1] - 1) == true && board[prevHit[0]][prevHit[1] - 1] == false){// hit left of initial hit
System.out.println("HIT!!!");
lastShot = true;
prevHit[0] = prevHit[0];
prevHit[1] = prevHit[1] -1 ;
directionHit = 2; //shoot up next time
userTurn = true;
board[prevHit[0]][prevHit[1]] = true;
if (sunkShip()){
lastShot = false;
System.out.println("sunk");
};
}
else if ( Hit(prevHit[0] , prevHit[1] - 1) == false){ //miss left after a hit
lastShot = true;
userTurn = true;
directionHit = 3; //shoot below hit next time
System.out.println("MISS");
}
else if ( Hit(prevHit[0] , prevHit[1] - 1) == true && board[prevHit[0]][prevHit[1] - 1] == true){ //hit the same spot twice, ******
userTurn = false;
lastShot = true;
directionHit = 3;
}
break;
case 3: if ( Hit(prevHit[0] + 1 , prevHit[1]) == true && board[prevHit[0] + 1][prevHit[1]] == false){// hit below initial hit
System.out.println("HIT!!!");
lastShot = true;
prevHit[0] = prevHit[0] + 1;
prevHit[1] = prevHit[1] ;
directionHit = 3; //shoot below next time
userTurn = true;
board[prevHit[0]][prevHit[1]] = true;
if (sunkShip()){
lastShot = false;
System.out.println("sunk");
};
}
else if ( Hit(prevHit[0] + 1 , prevHit[1]) == false){ //miss left after a hit
lastShot = true;
userTurn = true;
directionHit = 0; //shoot below hit next time
System.out.println("MISS");
}
else if ( Hit(prevHit[0] + 1 , prevHit[1]) == true && board[prevHit[0] + 1][prevHit[1]] == true){ //hit the same spot twice, ******
userTurn = false;
lastShot = true;
directionHit = 0;
}
break;
}


}
userTurn = false;
}


}
}
static boolean sunkCarr = false; 
static boolean sunkBat = false ;
static boolean sunkCru = false;
static boolean sunkDes1 = false ;
static boolean sunkDes2 = false;
static boolean sunkSub1 = false ;
static boolean sunkSub2 = false;

public static boolean Hit(int xcor, int ycor){

boolean posHit = false;

for (int i = 0; i < 4; i++){ //battleship
if (xcor == battleShip[0][i] && ycor == battleShip[1][i]){

posHit = true;

}
}
for (int i = 0; i < 5; i++){ //carrier
if (xcor == carrier[0][i] && ycor == carrier[1][i]){

posHit = true;
}
}
for ( int i = 0; i < 3; i++){
if (xcor == cruiser[0][i] && ycor == cruiser[1][i]){

posHit = true;
}
}
for (int i = 0; i < 2; i++){
if (xcor == destroyer1[0][i] && ycor == destroyer1[1][i]){

posHit = true;
}
}
for (int i = 0; i < 2; i++){
if (xcor == destroyer2[0][i] && ycor == destroyer2[1][i]){

posHit = true;
}
}
if (xcor == submarine1[0][0] && ycor == submarine1[1][0]){
posHit = true;
}
if (xcor == submarine2[0][0] && ycor == submarine2[1][0]){
posHit = true;
}

return posHit;
}

public static boolean sunkShip(){
int sunkk = 99;
int hits = 0;
boolean done = false;

for (int i = 0; i < 5; i++){ //Carrier
if ( board[carrier[0][i]][carrier[1][i]] == true && sunkCarr == false){
hits++;
}
if (hits == 5){ //if carrier is hit 5 times its sunk
sunkCarr = true;
sunkk = 1;
done = true;

}
}
hits = 0;
for (int i = 0; i < 4; i++){ //battleship
if ( board[battleShip[0][i]][battleShip[1][i]] == true && sunkBat == false){
hits++;
}
else
hits = hits;
if (hits == 4){ //if battleship is hit 4 times its sunk
sunkBat = true;
sunkk = 2;
done = true;
}
}
hits = 0;
for (int i = 0; i < 3; i++){ //cruiser
if ( board[cruiser[0][i]][cruiser[1][i]] == true && sunkCru == false){
hits++;
}
if (hits == 3){ //if cruiser is hit 3 times its sunk
sunkCru = true;
sunkk = 3;
done = true;
}
}
hits = 0;
for (int i = 0; i < 2; i++) {
if ( board[destroyer1[0][i]][destroyer1[1][i]] == true && sunkDes1 == false){
hits++;
}
if (hits == 2){ //if destoyer1 is hit 2 times its sunk
sunkDes1 = true;
sunkk = 4;
done = true;
}
}
hits = 0;
for (int i = 0; i < 2; i++){
if ( board[destroyer2[0][i]][destroyer2[1][i]] == true && sunkDes2 == false){
hits++;
if (hits == 2) //if destoyer1 is hit 2 times its sunk
sunkDes2 = true;
sunkk = 5;
done = true;
}
}
hits = 0;
for (int i = 0; i < 1; i++){
if ( board[submarine1[0][i]][submarine1[1][i]] == true && sunkSub1 == false){
hits++;
}
if (hits == 1){ //if submarine1 is hit once its sunk
sunkSub1 = true;
sunkk = 6;
done = true;
}
}
hits = 0;
for (int i = 0; i < 1; i++){
if ( board[submarine2[0][i]][submarine2[1][i]] == true && sunkSub2 == false){
hits++;
}
if (hits == 1){ //if submarine1 is hit once its sunk
sunkSub2 = true;
sunkk = 7;
done = true;
}
}
switch(sunkk){
case 1: sunk[0]=true;
break;
case 2: sunk[1]=true;
break;
case 3: sunk[2]=true;
break;
case 4: sunk[3]=true;
break;
case 5: sunk[4]=true;
break;
case 6: sunk[5]=true;
break;
case 7: sunk[6]=true;
break;
default: 
break;
}
return done;
}
public static boolean gameOver(){
boolean over = false;
if (sunk[0]== true && sunk[1]==true && sunk[1]==true && sunk[2]==true &&
sunk[3]==true && sunk[4]==true && sunk[5]== true && sunk[6]==true){
over = true;
System.out.println("YOU LOSE");
}

return over;
}
}