/**
 * @author AmanpreetG
 */
public class Data {
	String Key;
	int Score, Lvl;
	public Data(String key, int score, int level) {
		this.Key = key;
		this.Score = score;
		this.Lvl = level;
	}
	
	 public String getKey() {return Key;}
	
	 public int getScore() {return Score;}

	 public int getLevel() {return Lvl;}
}
