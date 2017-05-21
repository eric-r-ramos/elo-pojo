package io.elopojo.report;

import java.util.ArrayList;
import java.util.List;

import io.elopojo.core.Championship;
import io.elopojo.core.Match;
import io.elopojo.core.Player;

public class MatchesReport extends Report {

	public MatchesReport(Championship championship) {
		super(championship);
	}

	@Override
	public void print() {
		
		
		List<Player> list = new ArrayList<Player>(championship.getPlayers().values());
		list.sort((left, right) -> right.getAmountMatches() - left.getAmountMatches());
		
		
	
		
		System.out.println("\n\n\n");
		System.out.println("+======================================================================+");
		System.out.println("|MATCHES REPORT =  ALL PLAYERS ORDERED BY AMOUT OF MATCHES             |");
		System.out.println("+======================================================================+");
		
		System.out.println("ELO_RATING\tID\tWINS\tLOSSES\tMATCHES\tSPLAYER_NAME\t");
		System.out.println("==========\t==\t====\t======\t=======\t============");
		
		for (Player player : list) {
			System.out.print(player.getRating() + "\t\t");
			System.out.print(player.getId() + "\t");
			System.out.print(player.getVictories() + "\t");
			System.out.print(player.getDefeats() + "\t");
			System.out.print(player.getAmountMatches()+ "\t");
			System.out.print(player.getName() + "\n");
			
		}
		
		
		//new order
		list.sort((left, right) ->  {
			int i=0;
			
			i = left.getAmountMatches() - right.getAmountMatches();
			if (i==0){
				i = left.getRating() - right.getRating();	
			}
			return i;
			
		});
		
		
	
		
		System.out.println("\n\n\n");
		System.out.println("+======================================================================+");
		System.out.println("|MATCHES REPORT =  ALL PLAYERS ORDERED BY AMOUT OF MATCHES             |");
		System.out.println("+======================================================================+");
		
		System.out.println("ELO_RATING\tID\tWINS\tLOSSES\tMATCHES\tSPLAYER_NAME\t");
		System.out.println("==========\t==\t====\t======\t=======\t============");
		
		for (Player player : list) {
			System.out.print(player.getRating() + "\t\t");
			System.out.print(player.getId() + "\t");
			System.out.print(player.getVictories() + "\t");
			System.out.print(player.getDefeats() + "\t");
			System.out.print(player.getAmountMatches()+ "\t");
			System.out.print(player.getName() + "\n");
			
		}
		
		
		
		List<Match> matches = championship.nextMatch();
		for (Match match : matches) {
			System.out.println(match.getPlayer1().getName() + " versus " + match.getPlayer2().getName() );
		}
		
	}
	
	
	

}
