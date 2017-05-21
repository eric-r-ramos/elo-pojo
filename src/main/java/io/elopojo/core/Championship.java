package io.elopojo.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Championship {
	
	
	private Map<Integer, Player> players;
	private List<Match> matches;
	
	
	public Championship(){
		players = new HashMap<Integer, Player>();
		matches = new ArrayList<Match>();
		log.info("A new Championship has been started");
	}
	
	public void addPlayer(Player player) {
		Player p = players.get(player.getId());
		if (p == null){
			players.put(new Integer(player.getId()), player);			
		}	
	}

	
	public Player getPlayer(String name){
		return players.values().stream().filter(p -> p.getName().equals(name)).findFirst().get();
		
	}
	
	public Player getPlayer(int id) {
		return players.get(new Integer(id));
	}

	public void addMatch(Match match) {
		validateMatchArguments(match);
		
		Player p1 = getPlayer(match.getPlayer1().getId());
		Player p2 = getPlayer(match.getPlayer2().getId());
		
		matches.add(match);
		adjustRating(match);
		
		p1.addMatch(match);
		p2.addMatch(match);

		
	}

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

	
	public Map<Integer, Player> getPlayers() {
		return players;
	}

	public List<Match> getMatches() {
		return matches;
	}
	
	
	
	/**
	 * The next match shoud be between the players that has less matches in this championship
	 * 
	 * @return a list with two players for the next match
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
