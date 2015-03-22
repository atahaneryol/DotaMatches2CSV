package trial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class HeroReader {
	
	private ArrayList<Hero> heroes;
	private HeroReader result;
	
	public HeroReader()
	{
		heroes = new ArrayList<Hero>();
	}
	
	public HeroReader getResult() {
		return result;
	}

	public void setResult(HeroReader result) {
		this.result = result;
	}

	public ArrayList<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(ArrayList<Hero> heroes) {
		this.heroes = heroes;
	}
	
	public void print()
	{
		for(int i = 0; i < 4; i++)
		{
			System.out.println(heroes.get(i).getName() + " " + heroes.get(i).getId());
		}
	}
	
	public HashMap<Long,String> getHeroMap()
	{
		HashMap<Long,String> res = new HashMap<Long,String>();
		for(int i = 0; i < heroes.size(); i++)
		{
			res.put(heroes.get(i).getId(), heroes.get(i).getLocalized_name());
		}
		
		return res;
	}
	
	public static void addToMatrix(ArrayList<String[]> mat, ArrayList<Player> players, Boolean radWin, String heroName )
	{
		String[] singleMatch = new String[113];
		for(int i = 0; i < singleMatch.length; i++)
		{
			singleMatch[i] = "n"; // to indicate not in this game 
		}
		boolean side = getSide(players);
		if(side == true) //Side is radiant
		{
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getAccount_id() != 35202280)
				{
					if(i < 5)
					{
						int temp = players.get(i).getHero_id().intValue();
						if(temp != 0 ) // Might be a leaver
							singleMatch[temp] = "a"; // as in ally		
					}
					else
					{
						int temp = players.get(i).getHero_id().intValue();
						if(temp != 0 )
							singleMatch[temp] = "e"; // as in enemy		
					}
				}
				else
				{
					singleMatch[0] = heroName; // our hero name not id in the last column of matrix
				}
			}
		}
		if(side == false)
		{
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).getAccount_id() != 35202280)
				{
					if(i < 5)
					{
						int temp = players.get(i).getHero_id().intValue();
						if(temp != 0 )
							singleMatch[temp] = "e"; // as in enemy		
					}
					else
					{
						int temp = players.get(i).getHero_id().intValue();
						if(temp != 0 )
							singleMatch[temp] = "a"; // as in ally		
					}
				}
				else
				{
					singleMatch[0] = heroName; // our hero name not id in the last column of matrix
				}
			}
		}
		
		mat.add(singleMatch);
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
	
	public static void writeMatToCSV(String filename, ArrayList<String[]> mat, HashMap<Long,String> list) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter("Matout.csv");
		String frst = "myHero";
		for(int i = 0; i < list.size(); i++)
		{
			frst = frst + "," + list.get(new Long(i+1)); 
		}
		writer.println(frst);
		for(int i = 0; i < mat.size(); i++)
		{
			String line = mat.get(i)[0];
			for(int k = 1; k < mat.get(i).length; k++)
			{
				line = line + "," + mat.get(i)[k];
			}
			writer.println(line);
		}
		writer.close();
	}
}

