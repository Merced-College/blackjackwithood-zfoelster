//Adrian M, Juan R, Zack F, Daniel V

//I stayed up a while to try and make an extended version of the group code. I don't know if we have to turn in the same code as our group though So I'm turning in both.

package package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();
	//Added dealer deck
	private static ArrayList<Card> dealerCards = new ArrayList<Card>();
	public static void main(String[] args) {
		//So we can see who wins
		int playerTotal = 0;
		int dealerTotal = 0;
		String userAnswer;


		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 2 cards
		//ADDED '=' SO PLAYER WOULD BE DEALT 5 CARDS
		for(int i = 0; i <= 1; i++) { 
			//index used to be i, but now since shuffle is more random, we  can start index at 0
			//Adds cards from the deck to dealer and player to start off with, counts them towards the dealer and player's total
			dealerCards.add(deckOfCards.remove(0));
			playerCards.add(deckOfCards.remove(0));
			playerTotal += playerCards.get(i).getVal();
			dealerTotal += dealerCards.get(i).getVal();
		}

		//To reveal one of the dealer's cards
		Card dealerReveal = dealerCards.get(0);
		//System.out.println(playerTotal);

		
		System.out.println("players cards");
		for(Card c: playerCards){
			//Changed to print a more useful name for the user.
			System.out.println(c.printSuiteCard());
		}


		System.out.println("pairs is " + checkFor2Kind());
		System.out.println("Dealer's revealed card is a " + dealerReveal.printSuiteCard() + "\n");

		//Boolean to go through the loop initially
		boolean hit = true;
		Scanner userInput = new Scanner(System.in);

		//While loop, the user keeps going through this loop until the decide not to hit. Adds the cards that the dealer and player draw each time to their total. 
		while(hit){

			System.out.println("Would you like to hit? (Type 'yes' to hit)\n");
			userAnswer = userInput.nextLine();
			
			if (userAnswer.equalsIgnoreCase("yes")){
				playerCards.add(deckOfCards.remove(0));
				playerTotal += playerCards.get(playerCards.size()-1).getVal();
				
				System.out.println("player cards");
				for(Card c: playerCards){
					//Changed to print a more useful name for the user.
					c.printSuiteCard();
				}
				if(playerTotal > 21){
					hit = false;
				}
				hit = true;
			}
			else{
				hit = false;
			}
		if(dealerTotal < 17){
			dealerCards.add(deckOfCards.remove(0));
			dealerTotal += dealerCards.get(playerCards.size()-1).getVal();
			System.out.println("The dealer has drawn a card\n!");
		}
		else{
			System.out.println("The dealer has not drawn a card.\n");
		}
	//System.out.println(playerTotal + " " + dealerTotal);
	}

	//If the dealer doesn't have a total > 17 by the end of the while loop, this insures that they will get a high total soon enough.
	while(dealerTotal < 17){
		if(dealerTotal < 17){
			dealerCards.add(deckOfCards.remove(0));
			dealerTotal += dealerCards.get(playerCards.size()-1).getVal();
			System.out.println("The dealer has drawn a card\n!");
		}
	}

	if(playerTotal > 21 && dealerTotal > 21){
		System.out.println("Nobody one! Both busted");
	}
	else if(playerTotal == dealerTotal){
		System.out.println("Nobody one! it's a tie");
	}
	else if(playerTotal > 21){
		System.out.println("You busted...");
	}
	else if(dealerTotal > 21){
		System.out.println("Dealer busted! You win.");
	}
	else if(dealerTotal > playerTotal){
		System.out.println("Dealer won...");
	}
	else{
		System.out.println("You won!");
	}


}

	//end main






	
	//Shuffle was not very random, so I improved it. Previously it would get a random card from the deck of cards and re-place it at the bottom of the deck into the same deck of cards for the size of the deck of cards, meaning 52 times. This meant that
	//The front of the deck of cards would remain in order, with random ones taken from the their place and put into the back. Leading to the front of the deck to still be in a pretty predictable order.
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
//end class



}