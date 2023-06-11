package alex.a2.dance;

import java.util.Arrays;

/**
 * Put your name and student id here
 */

/*
 * The ScoreKeeper class stores scores and answers basic statistical average
 * about the scores. It is intended to be use as part of another class for
 * score storage.
 */

public class ScoreKeeper {
    // TODO: Declare private member variables as needed

    public ScoreKeeper() {
        // TODO: Create an instance for this.scores
    }

    /*
     * Record the scores given by double[] scores into your instance variable
     * scores. If the member variable already has existing data, clear it before
     * putting in the new scores.
     */
    public void setScores(double[] scores) {
        // TODO: Write me
    }

    // Return an array double[] of scores as was previously set
    public double[] getScores() {
        // TODO: Write me
        return null; // TODO: REMOVE ME
    }

    /*
     * Return the average of the scores after excluding the minimum and maximum
     * value. In short, it should return
     *   (SUM(this.scores) - MIN(this.scores) - MAX(this.scores))/(n-2), where
     * n is the length of the score list. This function will return Double.NaN if
     * n is <= 2 because no meaningful average can be computed.
     */
    public double getCalibratedAverage() {
        // TODO: Write me
        return 0.0; // TODO: REMOVE ME
    }

    public static void main(String[] args) {
        System.out.println("=== ScoreKeeper ===");
        double[] sampleScores0 = {2.5, 1.0, 9.8, 5.4, 3.3, 0.25, 4.25};
        double[] sampleScores1 = {0.5, 9};

        ScoreKeeper scoreKeeper0 = new ScoreKeeper();
        scoreKeeper0.setScores(sampleScores0);
        System.out.println("scoreKeeper0: " + Arrays.toString(scoreKeeper0.getScores()));
        System.out.println("Calibrated Average: " + scoreKeeper0.getCalibratedAverage());

        System.out.printf("\n");

        ScoreKeeper scoreKeeper1 = new ScoreKeeper();
        scoreKeeper1.setScores(sampleScores1);
        System.out.println("scoreKeeper1: " + Arrays.toString(scoreKeeper1.getScores()));
        System.out.println("Calibrated Average: " + scoreKeeper1.getCalibratedAverage());
    }
}
