package io.elopojo.report;

import java.util.ArrayList;
import java.util.List;

import io.elopojo.core.Championship;
import io.elopojo.core.Player;

/**
 * Implements a report. 
 * Print a list of matches thar happened in championship
 * 
 * @author ericramos
 *
 */
public class MatchesReport extends Report {

	/**
	 * Constructor for the Report Implementation for a given Championship
	 * @param championship
	 */
	public MatchesReport(Championship championship) {
		super(championship);
	}

	@Override
	public void print() {
		
		List<Player> list = new ArrayList<Player>(championship.getPlayers().values());
		list.sort((left, right) -> right.getAmountMatches() - left.getAmountMatches());
		
		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		header.append("\n\n\n");
		header.append("+======================================================================+\n");
		header.append("|MATCHES REPORT =  ALL PLAYERS ORDERED BY AMOUT OF MATCHES             |\n");
		header.append("+======================================================================+\n");
		
		header.append("ELO_RATING\tID\tWINS\tLOSSES\tMATCHES\tSPLAYER_NAME\n");
		header.append("==========\t==\t====\t======\t=======\t============\n");
		
		System.out.println(header);
		
			
		list.stream().forEach(p ->{
			body.append(p.getRating() + "\t\t");
			body.append(p.getId() + "\t");
			body.append(p.getVictories() + "\t");
			body.append(p.getDefeats() + "\t");
			body.append(p.getAmountMatches()+ "\t");
			body.append(p.getName() + "\n");
			
		});
		
		System.out.println(body);
		
	}
	

}
