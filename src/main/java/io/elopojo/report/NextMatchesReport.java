package io.elopojo.report;

import io.elopojo.core.Championship;

/**
 * Implements a Report
 * Print a list of suggested next matches
 * 
 * @author ericramos
 *
 */
public class NextMatchesReport extends Report {

	/**
	 * Constructor for the Report Implementation for a given Championship
	 * @param championship
	 */
	public NextMatchesReport(Championship championship) {
		super(championship);
	}

	public void print() {
		
		StringBuffer header = new StringBuffer();
		StringBuffer body = new StringBuffer();
		

		
		header.append("\n\n\n");
		header.append("+======================================================================+");
		header.append("|SUGGESTED NEXT MATCHES REPORT                                         |");
		header.append("+======================================================================+");

		System.out.println(header);


		
		championship.nextMatch().forEach(m -> {
			body.append(m.getPlayer1().getName());
			body.append(" [matches: " + m.getPlayer1().getAmountMatches() + "]");
			body.append(" [rating: "  + m.getPlayer1().getRating() + "]");
			body.append(" versus ");
			body.append(m.getPlayer2().getName());
			body.append(" [matches: "  + m.getPlayer1().getAmountMatches() + "]");
			body.append(" [rating: " + m.getPlayer1().getRating() + "]\n");
			
			
			});
		System.out.println(header);

	}
}
