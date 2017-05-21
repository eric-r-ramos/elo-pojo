package io.elopojo.report;

import io.elopojo.core.Championship;

public class NextMatchesReport extends Report {

	public NextMatchesReport(Championship championship) {
		super(championship);
	}

	public void print() {
		
		System.out.println("\n\n\n");
		System.out.println("+======================================================================+");
		System.out.println("|SUGGEST NEXT MATCHES REPORT                                           |");
		System.out.println("+======================================================================+");
		
		
		championship.nextMatch().forEach(m -> {
			System.out.print(m.getPlayer1().getName());
			System.out.print(" [matches: " + m.getPlayer1().getAmountMatches() + "]");
			System.out.print(" [rating: "  + m.getPlayer1().getRating() + "]");
			System.out.print(" versus ");
			System.out.print(m.getPlayer2().getName());
			System.out.print(" [matches: "  + m.getPlayer1().getAmountMatches() + "]");
			System.out.print(" [rating: " + m.getPlayer1().getRating() + "]\n");
			
			
			});

	}
}
