package io.elopojo.core;

import java.math.BigDecimal;
import java.math.MathContext;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * This static class implements the Elo Rating Algorothm
 * @see https://en.wikipedia.org/wiki/Elo_rating_system
 * 
 * @author ericramos
 *
 */
@Slf4j
public class EloRating {
	
	public static int kFactor = 0;

	/**
	 * This method represents the core equation of ELO-Rating. 
	 * Given a a player and a match, this function returns the elo rating for this player 
	 * after the match
	 * 
	 * 
	 * R' = R + (K * (S(p1) – E(p1,p2)))
	 * 
	 * where:
	 * p1 is the player
	 * p2 is the adversary
	 * K is the adjust factor
	 * 
	 * S(p1) = Score of the player in this match --> 1 for winner, 0 for loser
	 * E(p1, p2) = Expected rating for player versus his adversary 
	 * 
	 * @param player the player we want to calc the rating
	 * @param match with 2 players and the results (winner)
	 */
	public static int getRating(int ratingP1, int ratingP2, int score){
		log.debug("Calc rating for match: player with rating {} "
				+ "versus player with rating {} ",  ratingP1, ratingP2);
		
	    int k = getKFactor();
	    
	    return (int) (ratingP1 + ( k * (score - getExpectedRating(ratingP1, ratingP2))));
	    
	}




	/**
	 * Return the expected rating agains a given adversary
	 * 
	 * @param ratingP1 the player rating that we want to calc the expected rating
	 * @param adversaryRating the rating of the player who is on an opposing side in a game
	 * @return the expected rating
	 */
	public static double getExpectedRating(int ratingP1, int adversaryRating) {


		BigDecimal exp = new BigDecimal(adversaryRating - ratingP1)
				.divide(new BigDecimal(400), MathContext.DECIMAL32);

		BigDecimal divisor = new BigDecimal(1 + Math.pow(10, exp.doubleValue()));

		double result = new BigDecimal(1.0).divide(divisor, MathContext.DECIMAL32).doubleValue();

		log.debug("Expected rating {} versus {} = {}", 
				ratingP1, adversaryRating, result);

		return result;
	}




	/**
	 * Return K factor
	 * Assume K=32 as default
	 * 
	 * @return the K factor
	 */
	private static int getKFactor(){
		if (0 == kFactor){
			return 32;
		}
		return kFactor;
	}
	
	/**
	 * set diferent K
	 */
	public static void setKFacttor(int k){
		kFactor = k;
	}

	
	
}
