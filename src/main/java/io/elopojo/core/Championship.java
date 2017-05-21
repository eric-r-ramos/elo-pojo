package io.elopojo.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * The championship class is used to store all players at that championship and it responsible to 
 * adjust the scoreborad and rank for every match tha happens
 * 
 * @author ericramos
 *
 */
@Slf4j
public class Championship {
	
	
	/**
	 * store players
	 */
	private Map<Integer, Player> players;
	
	
	/**
	 * stor matches 
	 */
	private List<Match> matches;
	
	
	/**
	 *  Constructor for Championship
	 */
	public Championship(){
		players = new HashMap<Integer, Player>();
		matches = new ArrayList<Match>();
		log.info("A new Championship has been started");
	}
	
	/**
	 * Add new player for the Championship 
	 * @param player
	 */
	public void addPlayer(Player player) {
		Player p = players.get(player.getId());
		if (p == null){
			players.put(new Integer(player.getId()), player);			
		}	
	}

	
	/**
	 * Get a player for a given name. If there is more than one player 
	 * with the same name, the fist one is returned
	 * 
	 * @param name of player you want to get
	 * @return the Player
	 */
	public Player getPlayer(String name){
		Player player;
		try{
			player = players.values().stream().filter(p -> p.getName().equals(name)).findFirst().get();
		}catch (NoSuchElementException e) {
			
			throw new NoSuchElementException("Could not find player with given name");
		}
		
		return player;
		
	}
	
	/**
	 * Get a player for a given id.
	 *  
	 * @param id Id of player you want to get
	 * @return the Player
	 */
	public Player getPlayer(int id) {
		return players.get(new Integer(id));
	}

	
	/**
	 * Add a Match for the Championship adjusting the scoreboad and the rating for the
	 * players on that match
	 * 
	 * @param match A Match with 2 players and the winner expressed
	 * @throws IllegalStateException If the players list had no element. Add players before add matches
	 */
	public void addMatch(Match match) {
		validateMatchArguments(match);
		
		Player p1 = getPlayer(match.getPlayer1().getId());
		Player p2 = getPlayer(match.getPlayer2().getId());
		
		matches.add(match);
		adjustRating(match);
		
		p1.addMatch(match);
		p2.addMatch(match);

		
	}

	
	
	/**
	 *  validate if there is players in the player list and if those players from this match is 
	 *  alredy added on the Championship
	 *   
	 * @param match Match with players
	 */
	private void validateMatchArguments(Match match) {
		if((players==null || players.size()==0)){
			throw new IllegalStateException(
				"There is no player listed on Championship. Please add players before adding matches");
		}
		
		Player p1 = getPlayer(match.getPlayer1().getId());
		if(p1 == null){
			throw new IllegalArgumentException("Could not find player with id: " + match.getPlayer1().getId());
		}
		
		Player p2 = getPlayer(match.getPlayer2().getId());
		if(p2 == null){
			throw new IllegalArgumentException("Could not find player with id: " + match.getPlayer1().getId());
		}
		
	}

	
	/**
	 * Adjust rating (the ELO-RATING) for the both players on the Match
	 * @param match The match with both players and the winner
	 */
	private void adjustRating(Match match) {
		Player p1 = getPlayer(match.getPlayer1().getId());
		Player p2 = getPlayer(match.getPlayer2().getId());
		
		int ratingP1 = p1.getRating();
		int ratingP2 = p2.getRating();
		
		log.debug("Adding new match: {}(rating: {}) versus {}(rating{})",
				p1.getName(), ratingP1, p2.getName(), ratingP2);
		
		p1.setRating(EloRating.getRating(ratingP1, ratingP2, match.getScore(p1)));
		p2.setRating(EloRating.getRating(ratingP2, ratingP1, match.getScore(p2)));
		
		log.debug("After match: {} has rating {} and {} has rating {}.",
				p1.getName(), p1.getRating(), p2.getName(), p2.getRating());

		
	}

	/**
	 * Get the Players added to the Championship
	 * 
	 * @return
	 */
	public Map<Integer, Player> getPlayers() {
		return players;
	}

	
	/**
	 * Get the Matches added to the Championship 
	 * @return
	 */
	public List<Match> getMatches() {
		return matches;
	}
	
	
	
	/**
	 * The list os suggested matches is using the pairing algorithm with some modifications
	 * Pairing algorithm - our current algorithm recognizes two imperatives:
	 *	  Give more duels to entries that have had less duels so far
     *    Match people with similar ratings with higher probability
     *    
     * To solve it, the list of players is ordered by matches of each player, followed by rating in ascending order
     * Then it is organized in plairs following this order
	 * 
	 * @return a list of matchs with two players 
	 */
	public List<Match> nextMatch(){
		
		List<Match> matches = new ArrayList<Match>();
		
		//return empty list in case that there is less than 2 players
		if(getPlayers().size()<2){
			return matches;
		}
		
		List<Player> players = new ArrayList<Player>( getPlayers().values());
		
		sortPlayerByMatchesRating(players);
		
		//organize in pairs
		Iterator<Player> iterator = players.iterator(); 
		while(iterator.hasNext()){
			Player p1 = iterator.next();
			if (iterator.hasNext()){
				matches.add(new Match(p1, iterator.next(), null));
			}else{
				break;
			}
		}
		
		return matches;
		

		
	}

	/**
	 *  Sort players by amount of matches and their rating
	 * @param players
	 */
	private void sortPlayerByMatchesRating(List<Player> players) {
		//order by  matches, rating 
		players.sort((left, right) ->  {
			int i=0;
			
			i = left.getAmountMatches() - right.getAmountMatches();
			if (i==0){
				i = left.getRating() - right.getRating();	
			}
			return i;
			
		});
	}

	

}
