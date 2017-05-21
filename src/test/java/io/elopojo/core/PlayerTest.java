package io.elopojo.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerTest {

	EloRating eloRating;
	Player p1;
	Player p2;
	Match match;
	
	@Before
	public void init(){
		eloRating  = new EloRating();
		
		p1 = new Player(1, "Kasparov");
		p2 = new Player(2, "Deep Blue");
		match = new Match(p1, p2, p1);
		
		log.info (p1.toString());
		
	}
	

	@Test
	public void addMatch(){
		Match m1 = new Match(p1, p2, p1);
		Match m2 = new Match(p1, p2, p1);

		
		p1.addMatch(m1);
		p2.addMatch(m1);
		assertThat(p1.getMatches().size(),is(equalTo(1)));
		assertThat(p2.getMatches().size(),is(equalTo(1)));

		p2.addMatch(m2);
		p1.addMatch(m2);
		assertThat(p1.getMatches().size(),is(equalTo(2)));
		assertThat(p2.getMatches().size(),is(equalTo(2)));
		
	}
}
