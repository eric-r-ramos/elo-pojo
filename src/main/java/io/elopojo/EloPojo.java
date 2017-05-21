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


/**
 * Main Class Application
 * 
 * @author ericramos
 *
 */
public class EloPojo {
	
	static Championship championship;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if(args.length < 3){
			System.out.println("Illegal arguments were found. "
					+ "\nUse java EloPojo <file_players> <file_matches> <[rank] | [player <player_name> | [next_matches] | [matches] >"
					+ "\nwhere:"
					+ "\n\tfile_players: \tplain file where each line has an ID number and a name for said ID"
					+ "\n\tfile_matches: \tplain file where each line contains the ID of the two players of a match and the first one is the winner of said match. with id matches"
					+ "\n\treport_type: \tuse the string:"
					+ "\n\t\trank --> for list players ordered for thei rating"
					+ "\n\t\tplayer <player_name>"
					+ "\n\t\tnext_matches for list of suggested next matches"
					+ "\n\t\tmatches for list of matches"
	
					);
			System.exit(0);
		}
		 
		
		championship = new Championship();
		FileLoader fileLoader = new FileLoader(championship);
		fileLoader.loadPlayers(args[0]);
		fileLoader.loadMatches(args[1]);
		
		Report report = reportBuilder(args);
		report.print();
		 
	}

	private static Report reportBuilder(String args[]) {
		switch (args[2]) {
			case "rank":
				return new RankReport(championship);
			case "player":
				return new PlayerReport(championship, args[3]);
			case "next_matches":
				return new NextMatchesReport (championship);
			case "matches":
				return new MatchesReport (championship);
			default:
				return new RankReport(championship);
		}

	}
	
	

}
