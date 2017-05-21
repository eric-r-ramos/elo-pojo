package io.elopojo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import io.elopojo.core.Championship;
import io.elopojo.core.Match;
import io.elopojo.core.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileLoader {
	
	private Championship championship;
	private int lineReaded;
	private boolean playersLoaded = false;
	
	
	public FileLoader(Championship championship) throws IllegalStateException{
		if (championship == null){
			throw new IllegalStateException("Championship should be initilized before load data files");
		}
		this.championship = championship;
	}
	
	
	/**
	 * Build new <code>FileLoader</code> using 2 files to load the data needed
	 * 
	 * @param filenamePlayers file with a list of players. Should use <code>id name</code> format where 
	 * 		<code>id</code> is an integer to representes a unique identification of the player and
	 * 		<code>name</code> is an String to representes the player name
	 * 
	 * @param filenameMatches file with a list of matches. Should use <code>id1 id2</code> format where
	 * 		<code>id1</code> is the winner of the match 
	 * @throws IOException 
	 *  
	 * @throws Exception
	 */
	public FileLoader(String filenamePlayers, String filenameMatches, Championship championship) throws FileNotFoundException, IOException, IllegalStateException{
		
		if (championship == null){
			throw new IllegalStateException("Championship should be initilized before load data files");
		}
		this.championship = championship;
		
		
		loadPlayers(filenamePlayers);
		loadMatches(filenameMatches);
	}
	
	
	

	/**
	 * Load a set of data with players names
	 * 
	 * @param filenamePlayers file with a list of players. Should use <code>id name</code> format where 
	 * 		<code>id</code> is an integer to representes a unique identification of the player and
	 * 		<code>name</code> is an String to representes the player name
	 * 
	 * @return amount of line readed;
	 * 
	 * @throws IOException
	 */
	public int loadPlayers(String filenamePlayers) throws FileNotFoundException, IOException  {
		log.info("Loading players from the file: " + filenamePlayers);
		
		lineReaded = 0;
		
        String delimiter = " ";
        try(Stream<String> lines = Files.lines(Paths.get(filenamePlayers))){
 
        	lines.filter(line -> line.contains(delimiter))
            	.forEach(line -> {
            			championship.addPlayer(
            				new Player(Integer.valueOf(line.split(delimiter)[0]), line.split(delimiter)[1] ));
            			
            			lineReaded ++;
            		}
	
            	);
            
        }catch (FileNotFoundException e) {
			log.error("File was not found: " + filenamePlayers);
			throw e;
        }catch (IOException e) {
			log.error("Error reading a file");
			throw e;
		}
        
        playersLoaded = true;
	    return lineReaded;
	    
	}

	
	
	/**
	 * Load a set of matches between the players
	 * 
	 * @param filenameMatches file with a list of matches. Should use <code>id1 id2</code> format where
	 * 		<code>id1</code> is the winner of the match 
	 * 
	 * @return
	 * @throws IOException
	 */
	public int loadMatches(String filenameMatches) throws IllegalStateException, FileNotFoundException, IOException  {
		if (!playersLoaded){
			throw new IllegalStateException("Players file should be loaded before matches file");
			
		}
		log.info("Loading matches from the file: " + filenameMatches);
		
	    lineReaded = 0;
        String delimiter = " ";
        
        try(Stream<String> lines = Files.lines(Paths.get(filenameMatches))){
        	
        	lines
        		.filter(line -> line.contains(delimiter))
        		.forEach(p -> 
        			{
        				Player p1 = championship.getPlayer(Integer.valueOf(p.split(delimiter)[0]));
        				Player p2 = championship.getPlayer(Integer.valueOf(p.split(delimiter)[1]));
        	
        				championship.addMatch(new Match(p1, p2, p1));
        				lineReaded++;
        			});
        	
 
        }catch (FileNotFoundException e) {
			log.error("File was not found: " + filenameMatches);
			throw e;
        }catch (IOException e) {
			log.error("Error reading a file");
			throw e;
		}
        
	    return lineReaded;
	    
	}


}
