import java.util.Scanner;


//Plays rock paper scissors
public class RockPaperScissors {

    /* A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;
    public static final int DRAWS = 100;
    public static final int YOU_WIN = 200;
    public static final int COMPUTER_WIN = 300;


    //Playing a game of rock, paper, and scissor with the computer via input of integer
    //with the result displaying how many total draws and wins
    //along with the final rock paper scissor master!
    public static void main(String[] args) {
        RandomPlayer computerPlayer = buildRandomPlayer(args);
        
        Scanner keyboard = new Scanner(System.in);
        String name = welcome(keyboard);
        int round = rounds(keyboard, name);
        game (keyboard, name, round, computerPlayer);

        keyboard.close();
    }


    /*
     * Build the random player. If args is length 0 then the
     * default RandomPlayer is built that follows a predictable
     * sequence. If args.length > 0 then we assume we can
     * convert the first element to an int and build the
     * RandomPlayer with that initial value.
     */

    public static RandomPlayer buildRandomPlayer(String[] args) {
        if (args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }

    //The opening to great the players by asking them for their name
    public static String welcome(Scanner keyboard) {
        System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
        System.out.print("Please type in your name and press return: ");
        String userName = keyboard.nextLine();
        System.out.println();
        System.out.println("Welcome " + userName + ".");
        System.out.println();
        return userName;

    }

    // Asking for how many rounds of game do they want to play
    // including the statement with their name
    public static int rounds(Scanner keyboard, String name) {
        System.out.println("All right " + name + ". " + "How many rounds would you like to play?");
        System.out.print("Enter the number of rounds you want to play and press return: ");
        int round = keyboard.nextInt();
        return round;

    }

    // Converting each string into a number that the player can select
    // by entering in a value from 1 to 3
    public static String rockPaperScissors(int choice) {
        String tools = "SCISSORS";
        if (choice == 1) {
            tools = "ROCK";
        } else if (choice == 2) {
            tools = "PAPER";
        }
        return tools;
    }

    // Decide if either the human won, computer won, or end a draws
    // that return a number correspond to the winner.
    public static int computerOrHuman (int choice, int computerChoice) {
        if (computerChoice == choice) {
            return DRAWS;
        } else if ((computerChoice == ROCK && choice == PAPER) ||
                (computerChoice == PAPER && choice == SCISSORS) ||
                (computerChoice == SCISSORS && choice == ROCK)) {
            return YOU_WIN;
        } else {
            return COMPUTER_WIN;


        }
    }

    //Decide that which hand wins by only needing half of the outcome
    //as the half will is considered winner if it doesn't fit in the
    // if statement
    public static String winCondition (int choice, int computerChoice) {
        String winCon = "SCISSORS cut PAPER. ";
        if ((computerChoice == PAPER && choice == ROCK) || (computerChoice == ROCK && choice == PAPER)) {
            return ("PAPER covers ROCK. ");
        } else if ((computerChoice == ROCK && choice == SCISSORS) || (computerChoice == SCISSORS && choice == ROCK)) {
            return ("ROCK breaks SCISSORS. ");
        } else {
            return winCon;
        }
    }

    //Asking the player what they want to pick for each round
    //this method will be later looped in game method.
    public static int userInput(Scanner keyboard, String name, int i){
        System.out.println();
        System.out.println("Round " + i + ".");
        System.out.println(name + ", please enter your choice for this round.");
        System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
        return keyboard.nextInt();
    }


    // The end itself where it displays who pick what base on the player choice
    // it loops the userInput method for each of the round desired
    public static void game (Scanner keyboard, String name, int round, RandomPlayer computerPlayer) {
        int draws = 0;
        int computerWon = 0;
        int humanWon = 0;

        for ( int i = 1; i <= round; i++) {
            int choice = userInput(keyboard, name, i);
            String tools = rockPaperScissors(choice);
            int computerChoice = computerPlayer.getComputerChoice();
            String winCon = winCondition(choice, computerChoice);
            String computerTool = rockPaperScissors(computerChoice);
            System.out.println("Computer picked " + computerTool + ", " + name + " picked " + tools + ".");
            System.out.println();
            int winResult = computerOrHuman (choice, computerChoice);
            if (winResult == DRAWS) {
                System.out.println("We picked the same thing! This round is a draw.");
                draws++;
            } else if (winResult == YOU_WIN) {
                System.out.println(winCon + "You win.");
                humanWon++;
            } else if (winResult == COMPUTER_WIN) {
                System.out.println(winCon + "I win.");
                computerWon++;
            }
        }
            gameResult(name, round, draws, computerWon, humanWon);
    }

    // The ending result showing the winners of each game
    public static void winner (String name, int computerWon, int humanWon) {
        if (computerWon > humanWon) {
            System.out.print("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
        } else if (humanWon > computerWon) {
            System.out.print("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
        } else {
            System.out.print("We are evenly matched.");
        }


    }

    // Printing out the stats of each game using the date
    // from previous methods
    public static void gameResult (String name, int round, int draws, int computerWon, int humanWon) {
        System.out.println ();
        System.out.println ();
        System.out.println("Number of games of ROCK PAPER SCISSORS: " + round);
        System.out.println("Number of times Computer won: " + computerWon);
        System.out.println("Number of times " + name + " won: " + humanWon );
        System.out.println("Number of draws: " + draws);
        winner(name, computerWon, humanWon);

    }

}

