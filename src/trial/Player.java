package trial;

public class Player {
	
	Long account_id;
	Long player_slot;
	Long hero_id;
	
	public Long getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	
	public Long getPlayer_slot() {
		return player_slot;
	}
	
	public void setPlayer_slot(Long player_slot) {
		this.player_slot = player_slot;
	}
	
	public Long getHero_id() {
		return hero_id;
	}
	
	public void setHero_id(Long hero_id) {
		this.hero_id = hero_id;
	}
	
	public String toString()
	{
		return "Account Id: " + account_id;
	}

}
