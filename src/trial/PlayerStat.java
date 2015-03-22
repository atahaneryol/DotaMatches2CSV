package trial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerStat {
	
	int winsAgainst;
	int lossAgainst;
	Long id;
	
	public PlayerStat()
	{
		winsAgainst = 0;
		lossAgainst = 0;
		id = -1L;
	}
	
	public PlayerStat(Long givenId)
	{
		id = givenId;
		winsAgainst = 0;
		lossAgainst = 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWinsAgainst()
	{
		return winsAgainst;
	}
	
	public int getLossAgainst()
	{
		return lossAgainst;
	}
	
	public void incWinsAgainst()
	{
		winsAgainst++;
	}
	
	public void incLossAgainst()
	{
		lossAgainst++;
	}
	
	public String toString()
	{
		String s = "ID: " + id + " Wins Against: " + winsAgainst + " Loss Against: " + lossAgainst;
		return s;
	}
	
	public static void updatePlayerStat(HashMap<Long, PlayerStat> givenMap, ArrayList<Player> givenPlayers, Boolean radWin)
	{
		int start = 0;
		int end = 5;
		boolean weWin = false;
		if(getSide(givenPlayers))
		{
			weWin = radWin;
			start = 5;
			end = 10;
		}
		else
		{
			weWin = !radWin;
		}
		for(int i = start; i < end; i++)
		{
			Long accId = givenPlayers.get(i).getAccount_id();
			updateMap(givenMap,accId,weWin);
		}
	}
	
	private static void updateMap(HashMap<Long, PlayerStat> givenMap, Long accId, boolean weWin) 
	{
		PlayerStat temp = givenMap.get(accId);
		if(temp == null) //This is a new player
		{
			PlayerStat addt = new PlayerStat(accId); //Create new stats for the new player
			if(weWin)
			{
				addt.incWinsAgainst();
			}
			else
			{
				addt.incLossAgainst();
			}
			givenMap.put(accId, addt);
		}
		else //Have played with this player before
		{
			if(weWin)
			{
				temp.incWinsAgainst();
			}
			else
			{
				temp.incLossAgainst();
			}
		}
		
	}

	public static boolean getSide( ArrayList<Player> givenPlayers ) // Returns true if on radiant side
	{
		for(int i = 0; i < givenPlayers.size(); i++)
		{
			if(givenPlayers.get(i).getAccount_id() == 35202280)
			{
				if(i > 4)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void writeToCSV(String filename, HashMap<Long, PlayerStat> givenMap) throws FileNotFoundException
	{
		ArrayList<PlayerStat> lst = new ArrayList(givenMap.values());
		PrintWriter writer = new PrintWriter("PlayerStatOut.csv");
		writer.println("AccountId,WinsAgainst,LossAgainst");
		for(int i = 0; i < lst.size(); i++)
		{
			String line = "" + lst.get(i).getId() + "," + lst.get(i).getWinsAgainst() + "," + lst.get(i).getLossAgainst();
			writer.println(line);
		}
		writer.close();
	}
	
	

}
