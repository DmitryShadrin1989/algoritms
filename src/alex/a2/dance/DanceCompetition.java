package alex.a2.dance;

import java.util.List;

/**
 * Put your name and student id here
 */

/*
 * The DanceCompetition class represents a dance competition that involves
 * multiple competitors. Two types of dances have been planned: popping dance
 * and hip-hop dance. The final score for each competitor is computed using a
 * weighted average. This class stores the weights as set in the constructors.
 */

public class DanceCompetition {
    // Do not modify or add the member variables
    private List<Competitor> competitors;  // a list of competitors
    private double hiphopFraction;         // weight of hip-hop dance
    private double poppingDanceFraction;   // weight of popping dance
    // -----------------------------------------


    // DanceCompetition's constructor that allows for customizing the weights
    // of popping dance and hip-hop dance.
    public DanceCompetition(double poppingDance, double hipHop) {
        //CODE HERE
    }

    // DanceCompetition's constructor that will set the default weights to
    // 0.7 for popping dance and 0.3 to hip-hop dance.
    // (Hint: Use this(...) to call the other constructor instead of rewriting the
    // same logic)
    public DanceCompetition() {
        //CODE HERE
        //HINT: this(0.7, 0.3);
    }

    // Return a list of competitors whose total dance score (as given by
    // getTotalDanceScore) is at least 8.0
    public List<Competitor> getGoldMedal(){
        //CODE HERE
        return null; // TODO: Change me
    }

    // Return the weight (i.e., fraction) of hip-hop dance
    public double getHipHopFraction() {
        //CODE HERE
        return 0.0; // TODO: Change me
    }

    // Return the weight (i.e., fraction) of popping dance
    public double getPoppingDanceFraction() {
        //CODE HERE
        return 0.0; // TODO: Change me
    }

    // Add Competitor c into the list of competitors and also
    // inform the competitor c that c belongs to this competition
    // by invoking c.setDanceCompetition suitably.
    // Did you already inform the competitor?
    public void addCompetitor(Competitor c) {
        //CODE HERE
    }

    public static void main(String[] args) {
        DanceCompetition season1 = new DanceCompetition();
        Competitor c0 = new Competitor();
        Competitor c1 = new Competitor();
        Competitor c2 = new Competitor();

        c0.setAlias("Locking J");
        c0.setPoppingDanceScore(new double[]{6.0,8.5,9.0,9.5,9.7,8.5});
        c0.setHipHopDanceScore(new double[]{8.0,8.5,9.0,7.5,6.7,8.0});
        season1.addCompetitor(c0);

        c1.setAlias("Breaking B");
        c1.setPoppingDanceScore(new double[]{2.0,2.5,3.0,3.5,4.5,3.0});
        c1.setHipHopDanceScore(new double[]{8.0,8.5,9.0,9.5,9.5,10.0});
        season1.addCompetitor(c1);

        c2.setAlias("Popping C");
        c2.setPoppingDanceScore(new double[]{10.0,5.5,10.0,9.5,8.5,10.0});
        c2.setHipHopDanceScore(new double[]{9.0,9.5,9.0,8.5,10,9.5});
        season1.addCompetitor(c2);

        System.out.println("== ALL Competition's Scores ==");
        for(Competitor e : season1.competitors)
            System.out.println("'"+e.getAlias() + "': " + e.getTotalDanceScore());
        System.out.println("==============================");

        for(Competitor e : season1.getGoldMedal())
            System.out.println("'"+e.getAlias() + "' get a gold medal with score = "+ e.getTotalDanceScore());
    }
}
