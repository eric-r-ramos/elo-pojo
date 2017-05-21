package io.elopojo.report;

import java.util.List;

import io.elopojo.core.Championship;
import io.elopojo.core.Match;
import io.elopojo.core.Player;

public class PlayerReport extends Report {

	Player player;
	
	/**
	 * Constructor for the Report Implementation for a given Championship
	 * @param championship
	 * @param player the Name of the player for the report
	 */
	public PlayerReport(Championship championship, String player) {
		super(championship);
		this.player = championship.getPlayer(player);
	}

	@Override
	public void print() {
		
		StringBuffer header = new StringBuffer();
				
		header.append("\n\n\n");
		header.append("+======================================================================+");
		header.append("|   PLAYER REPORT                                     |");
		header.append("+======================================================================+");

		header.append("\n  ID:\t" + player.getId());
		header.append("\n  NAME:\t" + player.getName());
		header.append("\n  RATING:\t" + player.getRating());
		header.append("\n  VICTORIES:\t" + player.getVictories());
		header.append("\n  DEFEATS:\t" + player.getDefeats());
		header.append("\n+======================================================================+");
		
		System.out.println(header);
		
		System.out.println("MATCHES:");
		
		int line = 1;
		List<Match> matches = player.getMatches();	
		

		for (Match match : matches) {
			System.out.print("Match #"+ line++ + " - ");
			
			String  result= match.getWinner().equals(player)==true?"WON":"LOST";
			
			System.out.println(player.getName() + " had match versus " 
			+ match.getAdversary(player).getName() + " and " + result );
			
		}

	}

}
