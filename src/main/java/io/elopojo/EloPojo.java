package io.elopojo;

import io.elopojo.core.Championship;
import io.elopojo.core.EloRating;
import io.elopojo.report.*;
import io.elopojo.util.FileLoader;
import org.apache.commons.cli.*;



/**
 * Main Class Application
 * 
 * @author ericramos
 *
 */
public class EloPojo {
	
	private static Championship championship;
	private static CommandLine line;




	public static void main(String[] args) throws Exception  {

		int executionStatus = 0;

		try {
			validateParameters(args);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid arguments to run Elo-Pojo Application");
			System.out.println("Error" + e.getMessage());

			throw e;
		}

		try {

			championship = new Championship();

			if (line.hasOption("k")){
				EloRating.setKFacttor(Integer.valueOf(line.getOptionValue("k")));
			}

			FileLoader fileLoader = new FileLoader(championship);
			fileLoader.loadPlayers(line.getOptionValue("p"));
			fileLoader.loadMatches(line.getOptionValue("m"));


			Report report = reportFactory();
			report.print();

		}catch (Exception e) {
			System.out.println("An Error has occured while executing the appication");
			System.out.println("Error Message: \t" + e.getMessage());

			System.exit(1);
		}
			 
	}



	public static void validateParameters(String[] args) throws IllegalArgumentException {
		
		Option playersFile   = Option
				.builder("p")
				.argName("playersFile")
                .hasArg()
                .desc("(required argument) Name of the file with the players list")
                .required()
                .build();
		
		Option matchesFile   = Option
				.builder("m")
				.argName( "matchesFile" )
                .hasArg()
                .desc("(required argument) Name of the file with the matches list" )
                .required()
                .build();
		
		Option reportType = Option
				.builder("r")
				.argName("reportType" )
                .hasArg()
                .desc("(required argument) Type of report to be generated."
                		+ "\nUse\t \"rank\" to generate the ranking of players;"
                		+ "\n\t \"next_matches\" to generate a list of sugested next players;"
                		+ "\n\t \"matches\" to show a list of matches happened;"
                		+ "\n\t \"player <player_name>\" to show details from <player_name> " )
                .required()
                .build();
		Option kFactor = Option
				.builder("k")
				.argName("k-factor")
				.hasArg()
				.desc("(optional argument) The k-factor used at the Elo Rating")
				.build();

		Option playerName = Option
				.builder("player_name")
				.argName("playerName")
				.hasArg()
				.desc("(required argument when report type is  \"-r player\"). The player_name is the name of player who you want to " +
						"show the details")
				.build();

		Options options = new Options();
		options.addOption(playersFile);
		options.addOption(matchesFile);
		options.addOption(reportType);
		options.addOption(kFactor);
		options.addOption(playerName);


	    CommandLineParser parser = new DefaultParser();
	    try {
			line = parser.parse(options, args);
			if (line.getOptionValue("r").equals("player") && (!line.hasOption("player_name"))) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "EloPojo", options );

				throw new IllegalArgumentException("Player name must be specified for report type \"player\" ");
			}
		} catch (ParseException  e) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "EloPojo", options );

			throw new IllegalArgumentException("Error parsing arguments");
		}
		
	}

	private static Report reportFactory() throws IllegalArgumentException {


		switch (line.getOptionValue("r"))
		{
			case "rank":
				return new RankReport(championship);
			case "player":
				return new PlayerReport(championship, line.getOptionValue("player_name"));
			case "next_matches":
				return new NextMatchesReport (championship);
			case "matches":
				return new MatchesReport (championship);
			default:
				return new RankReport(championship);
		}


	}


}
