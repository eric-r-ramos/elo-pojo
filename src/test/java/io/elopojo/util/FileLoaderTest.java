package io.elopojo.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

import io.elopojo.core.Championship;
import io.elopojo.core.Player;
import io.elopojo.util.FileLoader;


public class FileLoaderTest {
	Championship championship;
	
	@Test(expected=IllegalStateException.class)
	public void createFileLoaderWithEmptyChampionship() throws IllegalStateException, IOException{
		@SuppressWarnings("unused")
		FileLoader loader = new FileLoader("names.txt", "matches", championship);
	}
	
	@Test(expected=NoSuchFileException.class)
	public void createFileLoaderWithInvalidFile() throws IllegalStateException, IOException{
		championship = new Championship();
		@SuppressWarnings("unused")
		FileLoader loader = new FileLoader("wrongFile.txt", "anotherWrongFile.txt", championship);
	}
	
	
	@Test
	public void loadPlayers() throws FileNotFoundException, IOException{
		championship = new Championship();
		
		FileLoader loader = new FileLoader(championship);
		int lines = loader.loadPlayers("playersTest.txt");

		assertNotEquals(0, lines);
		assertEquals(3, lines);
	}
	
	@Test(expected=IllegalStateException.class)
	public void loadMatchesBeforePlayers() throws IllegalStateException, FileNotFoundException, IOException{
		championship = new Championship();
		
		FileLoader loader = new FileLoader(championship);
		loader.loadMatches("matchesTest.txt");
		
	}
	
	@Test
	public void loadPlayersAndMatches() throws IllegalStateException, FileNotFoundException, IOException{
		championship = new Championship();
		int lines = 0;
		
		FileLoader loader = new FileLoader(championship);
		
		lines = loader.loadPlayers("playersTest.txt");
		assertNotEquals(0, lines);
		assertEquals(3, lines);
		
		lines = loader.loadMatches("matchesTest.txt");
		assertNotEquals(0, lines);
		assertEquals(5, lines);
		
		assertEquals(3, championship.getPlayers().size());
		assertEquals(5, championship.getMatches().size());
		
		Player p1 = championship.getPlayers().get(1);
		Player p2 = championship.getPlayers().get(2);
		Player p3 = championship.getPlayers().get(3);
		
		//check victories
		assertThat(p1.getVictories(), is(equalTo(3)));
		assertThat(p2.getVictories(), is(equalTo(1)));
		assertThat(p3.getVictories(), is(equalTo(1)));
		
		//check defeats
		assertThat(p1.getDefeats(), is(equalTo(0)));
		assertThat(p2.getDefeats(), is(equalTo(3)));
		assertThat(p3.getDefeats(), is(equalTo(2)));
		
		//check the rating
		assertThat(p1.getRating(), is(equalTo(2044)));
		assertThat(p2.getRating(), is(equalTo(1969)));
		assertThat(p3.getRating(), is(equalTo(1983)));
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
	}
	

}
