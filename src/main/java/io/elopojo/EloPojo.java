package io.elopojo;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.elopojo.core.Championship;
import io.elopojo.report.MatchesReport;
import io.elopojo.report.NextMatchesReport;
import io.elopojo.report.PlayerReport;
import io.elopojo.report.RankReport;
import io.elopojo.report.Report;
import io.elopojo.util.FileLoader;

public class EloPojo {
	
	static Championship championship;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if(args.length < 3){
			System.out.println("Illegal arguments were found. "
					+ "\nUse java EloPojo <file_players> <file_matches> <[RANK] | [PLAYER <player_name> | [NEXT_MATCHES]>"
					+ "\nwhere:"
					+ "\n\tfile_players: \tplain file where each line has an ID number and a name for said ID"
					+ "\n\tfile_matches: \tplain file where each line contains the ID of the two players of a match and the first one is the winner of said match. with id matches"
					+ "\n\treport_type: \tuse the string:"
					+ "\n\t\tRANK --> for list players ordered for thei rating"
					+ "\n\t\tPLAYER <player_name>"
					+ "\n\t\tNEXT_MATCHES for list of suggested next matches");
			System.exit(0);
		}
		 
		
		championship = new Championship();
		FileLoader fileLoader = new FileLoader(args[0], args[1], championship);

		Report report = reportBuilder(args);
		report.print();
		
		 
	}

	private static Report reportBuilder(String args[]) {
		switch (args[2]) {
			case "RANK":
				return new RankReport(championship);
			case "PLAYER":
				return new PlayerReport(championship, args[3]);
			case "NEXT_MATCHES":
				return new NextMatchesReport (championship);
			case "MATCHES":
				return new MatchesReport (championship);
			default:
				return new RankReport(championship);
		}

	}
	
	

}
