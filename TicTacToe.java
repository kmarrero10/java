package com.kylemarrero;

/**
 * Created by kylemarrero on 7/18/17.
 */
public class TicTacToe {
    public static void main(String[] args) {
        GameBoard myGame = new GameBoard();
       int counter = 1;

//       while (myGame.gameIsGoing() && counter < 10) {
//           if (counter % 2 == 0) {
//            myGame.askPlayer('O');
//           }
//           else myGame.askPlayer('X');
//           counter++;
//
//           System.out.println("\n");
//           myGame.displayBoard();
//           myGame.checkWinner();
//
//           if (counter == 10) {
//               System.out.println("It's a draw!");
//           }
//       }


        while (myGame.gameIsGoing() && counter < 10) {
            if (counter % 2 == 0) {
                myGame.machineMove(counter);
            }
            else myGame.askPlayer('X');
            counter++;

            System.out.println("\n");
            myGame.displayBoard();
            myGame.checkWinner();


            if (counter == 10) {
                System.out.println("It's a draw!");
            }
        }

        if (counter % 2 == 0) {
            myGame.writeFile();
        }


    }
}
