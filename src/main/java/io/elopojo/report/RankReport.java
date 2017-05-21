package io.elopojo.report;

import java.util.ArrayList;
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
		list.sort((left, right) -> right.getRating() - left.getRating());
		
		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		header.append("\n\n\n");
		header.append("+======================================================================+");
		header.append("| RANK REPORT =  ALL PLAYERS RANKED BY ELO RATING                      |                                        |");
		header.append("+======================================================================+");

		header.append("RANK\tELO_RATING\tWINS\tLOSSES\tMATCHES\tID\tPLAYER_NAME\t");
		header.append("====\t==========\t====\t======\t=======\t==\t============");
		
		System.out.println(header);
		
		int rank = 1;
		for (Player player : list) {
			body.append("#"+ rank++ + "\t");
			body.append(player.getRating() + "\t\t");
			body.append(player.getVictories() + "\t");
			body.append(player.getDefeats() + "\t");
			body.append(player.getAmountMatches()+ "\t");
			body.append(player.getId() + "\t");
			body.append(player.getName() + "\n");
		}

		System.out.println(body);
		
	}
	

}
