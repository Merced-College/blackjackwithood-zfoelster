package Homework;

//Adrian M, Juan R, Zack F, Daniel V,

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();
	private static ArrayList<Card> dealerHand = new ArrayList<Card>();
	private static ArrayList<Card> dealerCards = new ArrayList<Card>();


	public static void main(String[] args) {

	
		int playersHandValue = 0;

		//the code to check and see if the text exist and can be read

		Scanner input = null;
		try {
			input = new Scanner(new File("cards3.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}
	
	
	

		shuffle();
		for(int i = 0; i < 1; i++) {

			dealerHand.add(deckOfCards.remove(i));
		}
		Scanner moreInput = new Scanner(System.in);
		while(moreInput.hasNextLine()){
		
		System.out.println("dealer's cards");
		int dealersHandValue = 0;


		for(Card c: dealerHand)
		{
			c.printSuiteCard();
			dealersHandValue += c.getVal();
			
		
		}


		for(Card c: deckOfCards){
			
	}
		//deal the player 5 cards
		for(int i = 0; i < 2; i++) {

			playerCards.add(deckOfCards.remove(i));
		}
		
		System.out.println("Your cards");


		int totalValue = 0;

		for(Card c: playerCards)
		{
			//c.printSuiteCard();
			totalValue += c.getVal();
			
			
		
		}
		//This checks to see if you automatically have the value of 21
		System.out.println("Your hand value" + " " + totalValue);
		if (totalValue == 21){
			System.out.println("You win!");
			break;
		}
System.out.println("Would you like to hit?");

		

		Scanner userInput = new Scanner(System.in);

		String userInput2 = userInput.nextLine();

		int nextCard = playerCards.get(playerCards.size()-1).getVal();

		
		
		int playerHandValue = totalValue + nextCard;
		int dealerHandValue = 0;
		
		
		
	

		

	System.out.println("Your new hand value" + " " + playerHandValue);
	System.out.println("Would you like to stay?");

	if(userInput2.equalsIgnoreCase("Yes") && dealersHandValue <= 17){
		dealersHandValue = dealersHandValue + dealerCards.get(dealerCards.size()-1).getVal();
		System.out.println("The dealers Value" + " "+ dealersHandValue);

		
	}
	
	//here we have the code keep allowing the player to hit so along as the player's hand value is less than 20
	
	while (playerHandValue <= 20 ){
		//nextCard = playerCards.get(playerCards.size()-1).getVal();
		playerHandValue = totalValue + nextCard;
		System.out.println(playerHandValue);
		
		if (playerHandValue <= 21){
			System.out.println("You win");
			break;
		}



	}
	if (playerHandValue >= 22){
		System.out.println("You bust");
		break;
	}

	//here code checks to see if value of cards equals 19, 20, 21 this is here for if the dealer
	// ends up staying on 17, but your cards do not equal 21
	
	if (playerHandValue == 19||playerHandValue == 20||playerHandValue == 21 ){
		System.out.println("You win!");

}


	


				//while(userInput2.equalsIgnoreCase("yes")&& playerHandValue <= 21){
					//playerCards.add(deckOfCards.remove(0));
				//}
	}
}
		
		
		//System.out.println("pairs is " + checkFor2Kind());
		
		

	//end main

	//Changed the shuffle to shuffle better, because it wouldn't shuffle properly
	
	public static void shuffle() {
        int loopy = deckOfCards.size();
        //shuffling the cards by deleting and reinserting
        for (int i = 0; i < loopy; i++) {
            Card c = deckOfCards.remove((int)(Math.random()*deckOfCards.size()));
            //System.out.println("c is " + c + ", index is " + index);
            //Using dealer cards to temporarily hold the deck of cards. It will be randomized here, then move back to the deck
            dealerCards.add(c);
        }
        for (int i = 0; i < loopy; i++) {
            Card c = dealerCards.get(i);
            deckOfCards.add(c);
        }
    }

	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {

		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				//System.out.println(" comparing " + current);
				//System.out.println(" to " + next);
				if(current.equals(next))
					count++;
			}//end of inner for
			if(count == 1)

				return true;

		}//end outer for
		return false;
	}
}//end class
