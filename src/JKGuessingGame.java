// Joseph "Joey" Krueger
// 02/27/22
// CS 141
// Guessing Game Lab 4
//
// This program will play a guessing game with the user
//
//      You can manually adjust the difficulty
//      by changing the value of constant "MAX"
//
//      It will not crash if you enter invalid charecters
//
//      It also shows you your high score!

import java.util.*;

    public class JKGuessingGame
    {
        public static void main(String[] args)
        {
            int totalGames = 0;
            int totalGuesses = 0;
            int highScore = 0;
            int bestGame = 0;
            double averageScore = 0;
            final int MAX = 100;     //you can change this number!

            instructions(MAX);
            playGame(totalGames, totalGuesses, highScore, bestGame, averageScore, MAX);
            // summary method is inside of playgame method
        }// end of main method



        public static void instructions(final int MAX) //This method prints instructions
        {
            System.out.printf("I want to play a game.\n\n");
            System.out.println("I'm thinking of a number between 1 and " + MAX);
            System.out.println("Each time you guess a number, I'll tell you");
            System.out.printf("whether the right answer is higher or lower\n\n");
            try
            {                           //timer here gives the game more "flow"
                Thread.sleep(1500);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } // end instructions method



        public static void playGame(int totalGames,    // this method runs the game and runs with methods
                                    int totalGuesses,  // inputTest and summary
                                    int highScore,
                                    int bestGame,
                                    double averageScore,
                                    final int MAX)
        {
            System.out.println("Would you like to play?");
            Scanner input = new Scanner(System.in);
            String desire = input.next();

            while(desire.equals("y") || desire.equals("Y") || desire.equals("yes") || desire.equals("Yes"))
            {
                Random rand = new Random();
                int answer = rand.nextInt(MAX) + 1;
                System.out.println("I'm thinking of a number, what do you think it is?");
                //answer = 42;         <--- SAMPLE LOG!!
                int guessValue = inputTest(MAX); // this method checks to see if the input is valid
                int numTries = 1; //numTries is reset each playthrough, total guesses is not
                while (answer != guessValue)
                {
                    if(guessValue > answer)
                    {
                        System.out.println("It's lower");
                    } else {
                        System.out.println("It's higher");
                    }
                    numTries++;
                    guessValue = inputTest(MAX);
                }

                totalGames++;
                totalGuesses += numTries;
                if(totalGames == 1)         //this if statement finds the precedented highScore
                {
                    highScore = numTries;
                }
                if(numTries <= highScore)
                {                           //and uses it to determine the number of the bestGame
                    highScore = numTries;
                    bestGame = totalGames;
                }

                if(numTries == 1)
                {
                    System.out.println("You got it right in 1 try!!! You should invest in the stock market!");
                } else {
                    System.out.printf("You got it right in %d tries. ", numTries);
                }
                System.out.printf("\nDo you want to play again?\n");
                desire = input.next();
                averageScore = (double) totalGuesses / (double) totalGames;
            } // end while loop
            summary(totalGames, totalGuesses, averageScore, bestGame, highScore);
        } // end playGame method



        public static int inputTest(final int MAX) // this method determines if the input is a valid answer
        {                                          // and returns the value entered as an integer to playGame
            int guessValue = 0;                    // if it is
            Scanner input = new Scanner(System.in);
            String guessString = input.nextLine();
            boolean isInteger = isInteger(guessString); //isInteger is also a method, see below
            if(isInteger)
            {
                guessValue = Integer.parseInt(guessString);
            }
            while(guessValue < 1 || guessValue > MAX) // as long as the entered value is invalid
            {                                         // this while loop will remain active
                if(!isInteger)                        // and the answers will not be returned or
                {                                     // counted as "tries"
                    System.out.println("Type a number only");
                } else {
                    System.out.println("That is not a number between 1 and " + MAX);
                }
                guessString = input.nextLine();
                isInteger = isInteger(guessString);
                if(isInteger)
                {
                    guessValue = Integer.parseInt(guessString);
                }
            }
            return guessValue;
        } // end inputTest method



        public static boolean isInteger(String guessString) // this method determines if the entered string
        {                                                   // can be converted into an integer
            try
            {
                Integer.parseInt(guessString);
                return true;
            } catch(Exception ex) {
                return false;
            }
        } // end isInteger method



        public static void summary(int totalGames, // this method prints a summary of the users results
                                   int totalGuesses,
                                   double averageScore,
                                   int bestGame,
                                   int highScore)
        {
            if(totalGames == 0)
            {
                System.out.printf("\nLame\n");
                try
                {                           //timer here gives the game more "flow"
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.printf("\nOverall results\n");
            System.out.printf("\tTotal Games:      %d\n", totalGames);
            System.out.printf("\tTotal Guesses:    %d\n", totalGuesses);
            System.out.printf("\tGuesses per game: %.2f\n", averageScore);
            System.out.printf("\tBest game:        %d\n", bestGame);
            if(highScore == 1)
            {
                System.out.printf("\tHigh Score:       %d guess! Perfect game!\n", highScore);
            } else {
                System.out.printf("\tHigh Score:       %d guesses\n", highScore); // english plurality syntax
            }
        } // end of summary method
    } // end of class

