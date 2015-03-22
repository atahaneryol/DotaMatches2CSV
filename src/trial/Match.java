package trial;

import java.util.ArrayList;
import java.util.List;

public class Match {
	
	Long match_id;
	ArrayList<Player> players;
	
	public Long getMatch_id() {
		return match_id;
	}

	public void setMatch_id(Long match_id) {
		this.match_id = match_id;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public String toString()
	{
		return "Match Id: " + match_id;
	}

}
