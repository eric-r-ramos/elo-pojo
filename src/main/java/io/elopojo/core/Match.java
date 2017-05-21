package io.elopojo.core;



public class Match {
	
	private Player player1;
	private Player player2;
	private Player winner;
	
	public Match( Player p1, Player p2, Player w){
		this.player1 = p1; 
		this.player2 = p2;
		this.winner = w;
		
	}
	
	/**
	 * Get the score for the given player
	 * Winner gets 1
	 * Looser gets 0
	 * 
	 * @param p
	 * @return 1 if the giver player is the winner; 0 otherwiser
	 */
	public int getScore(Player p) {
		if (this.winner == p)
			return 1;
		return 0;
	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	/**
	 * @return the winner
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Player getAdversary(Player player) {
        if (player1.equals(player)) {
        	return player2;
        }else if (player2.equals(player)){
        	return player1;
        }else {
          throw new IllegalArgumentException(
            "This player was not present in this match.");
        }
	}



}
