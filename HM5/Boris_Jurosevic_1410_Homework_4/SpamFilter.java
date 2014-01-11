

public class SpamFilter {

	static String[] wordsToBlock = {"Free", "Bank", "Click", "Holiday", "$$$", "###", "Offer"};
	static String[] messages = new String[5];
	static int scoreBound = 3;
	
	public static void main(String[] args) {
		messages[0] = "Offer you great holidays for almost no $$$, Click here!";
		messages[1] = "Please confirm your debit card";
		messages[2] = "If you stop breathing you will not live";
		messages[3] = "Give us an offer and we will put money in your bank, free of charge. ";
		messages[4] = "";
		
		System.out.println("Your spam-free messages:");
		for(int i = 0; i < messages.length; i++){
			int score = 0;
			for(int j = 0; j < wordsToBlock.length; j++){
				if(messages[i].toLowerCase().contains(wordsToBlock[j].toLowerCase())){
					score++;
				}
			}
			if(score < scoreBound){
				System.out.println(messages[i]);
			}
		}
	}
}