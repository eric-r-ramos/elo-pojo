package io.elopojo.report;

import java.util.List;

import io.elopojo.core.Championship;
import io.elopojo.core.Match;
import io.elopojo.core.Player;

public class PlayerReport extends Report {

	Player player;
	public PlayerReport(Championship championship, String player) {
		super(championship);
		
		this.player = championship.getPlayer(player);
		System.out.println(this.player);
		

	}

	@Override
	public void print() {
		
		
		System.out.println("\n\n\n");
		System.out.println("+======================================================================+");
		System.out.println("|PLAYER REPORT                                                         |");
		System.out.println("+======================================================================+");
		
		System.out.println("ID:\t" + player.getId());
		System.out.println("NAME:\t" + player.getName());
		System.out.println("RATING:\t" + player.getRating());
		System.out.println("VICTORIES:\t" + player.getVictories());
		System.out.println("DEFEATS:\t" + player.getDefeats());
		System.out.println("+======================================================================+");
		
		
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
