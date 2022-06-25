import java.util.Random;

/**
 * RandomPlayer can be called with a int seed, or with no seed.  
 * 
 * If the constructor that takes an int the play() method generates 
 * a random integer between 1 and 3 inclusive.  
 * 
 * If no seed is sent then the program finds a seed that produces a
 *  fixed, predictable sequence of move. 
 * In this case {1, 2, 3, 2, 3, 1, 1} which corresponds to
 * {ROCK, PAPER, SCISSORS, PAPER, SCISSORS, ROCK, ROCK}
 *
 */
public class RandomPlayer {

    private static final int NUM_CHOICES = 3;
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;
    
    // fixed rotating sequence of plays for computer
    private static final int [] PLAYS = {ROCK, PAPER, SCISSORS, 
            PAPER, SCISSORS, ROCK, ROCK}; 

    private Random rand;
    private int roundNumber;
    private boolean useFixedSequence;

    /**
     * Construct a RandomPlayer object based on the given seed.
     * @param seed The initial seed for the random number generator
     */
    RandomPlayer (int seed) {
        useFixedSequence = false;
        rand = new Random(seed);
    }

    /**
     * Construct a RandomPlayer object that follows a predictable sequence.
     * The sequence is ROCK, PAPER, SCISSORS, PAPER, SCISSORS, ROCK, ROCK.
     */
    RandomPlayer () {
        useFixedSequence = true;
        roundNumber = 0;
    }


    /**
     * Return the computer's choice. 1 represents ROCK, 2 represents Paper,
     * and 3 represents Scissors.
     * @return The computer's next choice.
     */
    public int getComputerChoice() {
        int result;
        if (useFixedSequence) {
            result = PLAYS[roundNumber];
            roundNumber = (roundNumber + 1) % PLAYS.length;
        } else {
            result = rand.nextInt(NUM_CHOICES) + 1;
        }
        return result;
    }
}
