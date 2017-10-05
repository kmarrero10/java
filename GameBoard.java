package com.kylemarrero;

import java.io.*;
import java.util.*;

/**
 * Created by kylemarrero on 7/18/17.
 */
public class GameBoard {
    private char[][] gameBoard;
    private boolean gameGoing = true;
    static String machineMoves;
    StringBuilder moves = new StringBuilder();
    ArrayList<Integer> prevMoves = readFile();

    int intMoves;

    //Constructor to initialize game board and assign empty spaces
    public GameBoard() {
        gameBoard = new char[3][3];

        for (int row = 0; row < gameBoard.length; row++) {
            Arrays.fill(gameBoard[row], ' ');
        }
    }

    //Return deep string representation of board data
    public void getBoard() {
        System.out.println(Arrays.deepToString(gameBoard));
    }

    public void displayBoard() {

        for (int row=0; row <gameBoard.length; row++) {
            for (int col =0; col < gameBoard[0].length; col++) {
                System.out.print("\t" + gameBoard[row][col]);
                if (col ==0 || col== 1)
                    System.out.print(" | ");
            }
            if (row == 0 || row == 1)
            System.out.print("\n---------------------\n");
        }
        System.out.println();
    }

    public boolean makeMove(char player, int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (gameBoard[row][col] != ' ') {
                System.out.println("Index ["+ row +", "+ col + "] has already been chosen!");
                return false;
            }
            else {
                gameBoard[row][col] = player;
                return true;
            }
        } else
            return false;
    } //make move


    //This method will return if the game is still active

    public boolean gameIsGoing() {
        return gameGoing;
    }

    //This will ask the user to choose a row and column

    public void askPlayer(char player) {

        Scanner scan = new Scanner(System.in);
        int row, col;
        do {
            System.out.printf("Player %s please enter a row (1-3): ", player);
            row = scan.nextInt();

            System.out.printf("Player %s please enter a column (1-3): ", player);
            col = scan.nextInt();

        }
        while (notValid(row, col));

        makeMove(player, row - 1, col - 1);
    }

    public boolean isEmpty(int row, int col) {

        if (gameBoard[row - 1][col - 1] == ' ')
                return true;

        else {
            System.out.println("That position is taken.");
            return false;
        }
    }

    public boolean notValid(int row, int col) {
        if (row > 3 || row < 1 || col > 3 || col < 1 || !isEmpty(row, col))
            return true;

        return false;

    }

    public boolean checkWinner() {

        for(int row =0; row < gameBoard.length; row++) {

            if (gameBoard[row][0] == gameBoard[row][1] &&  gameBoard[row][1] == gameBoard[row][2]
                    && gameBoard[row][0] != ' ') {
                System.out.println("The winner is " + gameBoard[row][0]);
                gameGoing = false;
            }
        }

        for (int col = 0; col <gameBoard[0].length; col++) {
            if (gameBoard[0][col] == gameBoard[1][col] &&  gameBoard[1][col] == gameBoard[2][col]
                    && gameBoard[0][col] != ' '){
                System.out.println("The winner is " + gameBoard[0][col]);
                gameGoing = false;
            }
        }

        //Check diagonal

        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != ' ')
        {
            System.out.println("The winner is " + gameBoard[0][0]);
            gameGoing = false;
        }

        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && gameBoard[2][0] != ' ')
        {
            System.out.println("The winner is " + gameBoard[1][1]);
            gameGoing = false;
        }

        return false;
    }

//    public void machineMove() {
//
//        Random random = new Random();
//
//        int row, col;
//        int counter = 1;
//
//        do {
//             row = random.nextInt(3);
//             col = random.nextInt(3);
//
//
//        } while (notValid(row + 1, col + 1) && !lastMove(row, col, counter));
//
//        gameBoard[row][col] = 'O';
//
//        machineMoves = moves.append("-(" + row + ", " + col + ")").toString();
//        counter ++;
//
//    }

    public void machineMove(int counter) {

        int row, col;
        do {

         row = prevMoves.get(counter - 2);
         col = prevMoves.get(counter - 1);


         if(notValid(row + 1, col +1)) {
             ArrayList<Integer> moves = readFile();

             row = moves.get(counter - 2);
             col = moves.get(counter - 1);
         }

            } while (notValid(row + 1, col + 1));

        gameBoard[row][col] = 'O';

        machineMoves = moves.append("-(" + row + ", " + col + ")").toString();

    }


       public void writeFile() {
           String path = "/Users/kylemarrero/Desktop/ticTacToe/machineMoves.txt";
           String file = "machineMoves.txt";
           Scanner scan = null;

           try {
               scan = new Scanner(new File(path));
           } catch (FileNotFoundException e) {
               System.out.println("Error opening file " + file);
               System.exit(0);
           }

           try {

               PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter("/Users/kylemarrero/Desktop/ticTacToe/machineMoves.txt", true)));
               print.println(machineMoves);
               print.close();

           } catch (IOException e) {
               e.printStackTrace();
               System.out.println("IO Exception");
           }
       }


//    public static ArrayList<Integer> readFile() {
//
//        String path = "/Users/kylemarrero/Desktop/ticTacToe/machineMoves.txt";
//        String file = "machineMoves.txt";
//        String line = null, tmp;
//        ArrayList<Integer> arrayList = new ArrayList<Integer>();
//        Random random = new Random();
//
//
//        try {
//            FileInputStream in = new FileInputStream(path);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//            while ((tmp = br.readLine()) != null) {
//
//                line = tmp;
//            }
//
//            String moves = line;
//
//            moves = moves.replaceAll("[^0-9]+", "");
//
//            for (int i = 0; i < moves.length(); i++) {
//                char a = moves.charAt(i);
//                Integer x = Character.getNumericValue(a);
//                arrayList.add(x);
//            }
//            in.close();
//            br.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Error opening file: " + file);
//            //e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("IO Exception");
//            //e.printStackTrace();
//        } catch (NullPointerException e) {
//            System.out.println("No lines to read.");
//        }
//
//        return arrayList;
//    }

    //Method meant to do a final check on random index generation, to make sure
    //indices from previous combo was not used

    public static boolean lastMove(int row, int col, int counter) {


       try {

           int i = readFile().size();
           int x = readFile().get(i - 2);
           int y = readFile().get(i - 1);

           if (counter > 2 && (row != x || col != y)) {
               return true;
           }
       } catch (NullPointerException e) {
           System.out.println("No lines to read");
       } catch (ArrayIndexOutOfBoundsException e) {
           try {
               Random rand = new Random();
               int x = rand.nextInt(3);
               int y = rand.nextInt(3);
               PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter("/Users/kylemarrero/Desktop/ticTacToe/machineMoves.txt", true)));
               print.print(x);
               print.print(y);
               print.close();
           } catch (IOException ex) {
               System.out.println("IO Exception w/in lastMove catch");
               ex.printStackTrace();
           }
       }
        return false;
    }

    public static ArrayList<Integer> readFile() {

        String path = "/Users/kylemarrero/Desktop/ticTacToe/machineMoves.txt";
        String file = "machineMoves.txt";
        ArrayList<Integer> comp = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
        Random random = new Random();


        try {
            FileInputStream in = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            while((line = br.readLine()) != null) {
                String moves = line;
                ArrayList<Integer> arrayList = new ArrayList<Integer>();
                moves = moves.replaceAll("[^0-9]+", "");

                for (int i = 0; i < moves.length(); i++) {
                    char a = moves.charAt(i);
                    Integer x = Character.getNumericValue(a);
                    arrayList.add(x);
                }

                arrays.add(arrayList);
            }

            for (int i = 0; i < 9; i++) {
                int x = random.nextInt(3);
                comp.add(x);
            }

            for (int i = 0; i < arrays.size(); i++) {
                ArrayList<Integer> x =  arrays.get(i);
                if (x == comp) continue;
            }

            in.close();
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + file);
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("No lines to read.");
        }

        return comp;
    }

}














