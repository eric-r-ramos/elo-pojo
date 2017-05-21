package io.elopojo.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChampionshipTest {
	
	@Test
	public void addAndGetPayers(){
		Championship championship = new Championship();
		
		Player p1  = new Player(1, "Kasparov");
		championship.addPlayer(p1);
		
		assertThat(championship.getPlayers().size(), is(equalTo(1)));
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void addMatchWithoutPlayer(){
		Championship championship = new Championship();
		
		Player p1 = new Player(1, "Kasparov");
		Player p2 = new Player(1, "Fischer");
		
		Match match = new Match(p1, p2, p1);
		
		championship.addMatch(match);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addMatchWithNonPlayerAdded(){
		
		Championship championship = new Championship();
		
		Player p1 = new Player(1, "Kasparov");
		Player p2 = new Player(2, "Fischer");
		
		Match match = new Match(p1, p2, p1);
		
		//add only p1
		championship.addPlayer(p1);
		//without p2, the addMatch shoud get a exception
		championship.addMatch(match);
		
	}
	
	@Test
	public void nextMatchSugestion(){
		
		
		Championship championship = new Championship();
		
		Player p1 = new Player(1, "Kasparov");
		Player p2 = new Player(2, "Fischer");
		Player p3 = new Player(3, "DeepBlue");
		
	
		Match m1 = new Match(p1, p2, p1);
		Match m2 = new Match(p1, p3, p1);
		Match m3 = new Match(p1, p2, p1);
		Match m4 = new Match(p2, p3, p2);
		Match m5 = new Match(p3, p2, p3);
		
		
		championship.addPlayer(p1);
		championship.addPlayer(p2);
		championship.addPlayer(p3);
		
		championship.addMatch(m1);
		championship.addMatch(m2);
		championship.addMatch(m3);
		championship.addMatch(m4);
		championship.addMatch(m5);
		
		
		//player p1 and p3 have only 3 matches and shoud get another one
		List<Match> matches = championship.nextMatch();
		
		assertEquals(1, matches.size());
		
		assertTrue(
				(matches.get(0).getPlayer1().equals(p1) && matches.get(0).getPlayer2().equals(p3))
			||  (matches.get(0).getPlayer1().equals(p3) && matches.get(0).getPlayer2().equals(p1))
		);
		
	
				
	
		
	}
	

	

}
