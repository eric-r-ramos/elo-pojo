package io.elopojo.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import io.elopojo.core.EloRating;
import io.elopojo.core.Match;
import io.elopojo.core.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EloRankingTest {
	
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
		
	}
	
	
	@Test
	public void getRatingSingleMatchTest() {
		
		assertThat(eloRating.getRating(p1.getRating(), p2.getRating(), match.getScore(p1)), is(equalTo(2016)));
		assertThat(eloRating.getRating(p2.getRating(), p1.getRating(), match.getScore(p2)), is(equalTo(1984)));
	}
	


	@Test
	public void getRatingSingleMatchDiferentRatingsTest() {
		p1.setRating(2016);
		p2.setRating(1984);
		
		assertThat(eloRating.getRating(p1.getRating(), p2.getRating(), match.getScore(p1)), is(equalTo(2030)));
		assertThat(eloRating.getRating(p2.getRating(), p1.getRating(), match.getScore(p2)), is(equalTo(1969)));
	}
	
	@Test
	public void getRatingAfter3Matchs() {
		int ratingP1;
		int ratingP2;
		
		p1.setRating(2000);
		p2.setRating(2000);
		
		//Fisrt Match
		ratingP1 = eloRating.getRating(p1.getRating(), p2.getRating(), match.getScore(p1));
		ratingP2 = eloRating.getRating(p2.getRating(), p1.getRating(), match.getScore(p2));
		
		p1.setRating(ratingP1);
		p2.setRating(ratingP2);
		log.info("MATCH 1");
		log.info("P1 rating:{}, P2 rating:{} ", ratingP1, ratingP2 );
		
		
		//Second Match
		ratingP1 = eloRating.getRating(p1.getRating(), p2.getRating(), match.getScore(p1));
		ratingP2 = eloRating.getRating(p2.getRating(), p1.getRating(), match.getScore(p2));
		p1.setRating(ratingP1);
		p2.setRating(ratingP2);
		log.info("MATCH 2");
		log.info("P1 ratin:{}, P2 rating:{} ", ratingP1, ratingP2 );

		//Third Match
		ratingP1 = eloRating.getRating(p1.getRating(), p2.getRating(), match.getScore(p1));
		ratingP2 = eloRating.getRating(p2.getRating(), p1.getRating(), match.getScore(p2));
		p1.setRating(ratingP1);
		p2.setRating(ratingP2);
		log.info("MATCH 3");
		log.info("P1 ratin:{}, P2 rating:{} ", ratingP1, ratingP2 );
	
		assertThat(ratingP1, is(equalTo(2043)));
		assertThat(ratingP2, is(equalTo(1955)));
		
	}
	

}
