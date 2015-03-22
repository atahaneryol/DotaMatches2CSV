package trial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Result {
	
	String status;
	Result result;
	ArrayList<Match> matches;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result getResult() {
		return result;
	}
	
	public void setResult(Result result) {
		this.result = result;
	}
	
	public ArrayList<Match> getMatches() {
		return matches;
	}
	
	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

}
