/**@author AmanpreetG
  *config class used to make data and key storage convenient*/
public class Configuration {
		//private variables
			String config;
			int score;
			
		// this constructor initializes an object and stores a configuration and its associated score
		public Configuration(String config, int score) {
			this.config = config;
			this.score = score;
		}
		
		//getter config
		public String getConfig() {
			return config;
		}
		
		//getter score
		public int getScore() {
			return score;
		}
}