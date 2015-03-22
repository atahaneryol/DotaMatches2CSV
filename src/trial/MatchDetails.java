package trial;

public class MatchDetails {
	
	Long match_id;
	Boolean radiant_win;
	MatchDetails result;
	Integer human_players;
	
	
	public Integer getHuman_players() {
		return human_players;
	}

	public void setHuman_players(Integer human_players) {
		this.human_players = human_players;
	}

	public Long getMatch_id() {
		return match_id;
	}
	
	public void setMatch_id(Long match_id) {
		this.match_id = match_id;
	}
	
	public Boolean getRadiant_win() {
		return radiant_win;
	}
	
	public void setRadiant_win(Boolean radiant_win) {
		this.radiant_win = radiant_win;
	}

	public MatchDetails getResult() {
		return result;
	}

	public void setResult(MatchDetails result) {
		this.result = result;
	}
	
	
	

}
