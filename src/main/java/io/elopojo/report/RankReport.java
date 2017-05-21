package io.elopojo.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.elopojo.core.Championship;
import io.elopojo.core.Player;

public class RankReport extends Report {

	public RankReport(Championship championship) {
		super(championship);
	}

	@Override
	public void print() {
		
		
		List<Player> list = new ArrayList<Player>(championship.getPlayers().values());
		Collections.sort(list, Player.PlayerRatingComparator);
		list.sort((left, right) -> right.getRating() - left.getRating());
		//list.sort((left, right) -> right.getAmountMatches() - left.getAmountMatches());
		
		
		System.out.println("\n\n\n");
		System.out.println("+======================================================================+");
		System.out.println("|RANK REPORT =  ALL PLAYERS RANKED BY ELO RATING                       |");
		System.out.println("+======================================================================+");
		
		System.out.println("RANK\tELO_RATING\tWINS\tLOSSES\tMATCHES\tID\tPLAYER_NAME\t");
		System.out.println("====\t==========\t====\t======\t=======\t==\t============");
		
		int rank = 1;
		for (Player player : list) {
			System.out.print("#"+ rank++ + "\t");
			System.out.print(player.getRating() + "\t\t");
			System.out.print(player.getVictories() + "\t");
			System.out.print(player.getDefeats() + "\t");
			System.out.print(player.getAmountMatches()+ "\t");
			System.out.print(player.getId() + "\t");
			System.out.print(player.getName() + "\n");
			
		}


		
	}
	
	
	

}
