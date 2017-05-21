package io.elopojo.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Player {
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private static final int INITIAL_RATING = 2000;
	private int id;
	private String name;
	private int victories;
	private int defeats;
	private int rating;
	private int score;
	private int amountMatches;
	private List<Match> matches;
	

	
	/**
	 * Constructor for class Player
	 * Create a player with <code>id</code>, <code>name</code> and <code>INITIAL_RATING</code>
	 * 
	 * @param i
	 * @param string
	 * @param name 
	 */
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
		this.rating = INITIAL_RATING;
		this.victories = 0;
		this.defeats = 0;
		this.matches = new ArrayList<Match>();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the amountMatches
	 */
	public int getAmountMatches() {
		return amountMatches;
	}

	/**
	 * @param amountMatches the amountMatches to set
	 */
	public void setAmountMatches(int amountMatches) {
		this.amountMatches = amountMatches;
	}

	/**
	 * @return the match
	 */
	public List<Match> getMatches() {
		return matches;
	}
	/**
	 * @param match the match to set
	 */
	public void setMatches(List<Match> match) {
		this.matches = match;
	}
	/**
	 * @return the victories
	 */
	public int getVictories() {
		return victories;
	}

	/**
	 * @return the defeats
	 */
	public int getDefeats() {
		return defeats;
	}

	public void addMatch(Match match) {
		matches.add(match);
		amountMatches++;
		
		if (match.getWinner().getId() == id){
			this.victories++;
		}else{
			this.defeats++;
		}
		
	}
	

	

	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", victories=" + victories + ", defeats=" + defeats + ", rating="
				+ rating + "]";
	}

	
	
	/**
	 * Implementation of Comparator to provide order by rating in descending order
	 */
	public static Comparator<Player> PlayerRatingComparator = new Comparator<Player>() {

	    public int compare(Player player1, Player player2) {
	        //descending order
	    	return  player2.getRating() - player1.getRating();
	    	
	    }

	};



	

}
