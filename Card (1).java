 package Homework;
 public class Card {
	private String cSuit;
	private String cName;
	private int cValue;
	private String cPicture;
    
    //The object that recieves the text file 
   
    public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
        
        cSuit = cardSuit;
        cName = cardName;
        cValue = cardValue;
        cPicture = cardPicture;
    }
    public String getSuit(){
        return cSuit;
    }

    public String getName(){
        return cName;
    }

    public String getPic(){
        return cPicture;
    }

    public int getVal(){
        return cValue;
    }

    //To let the user know exactly what cards they have.
    public void printSuiteCard(){    
        System.out.println(cName + " of " + cSuit + "s" );

    }

    //returns back the player hand value, but first pulls from the text file
    public void printPlayersHandValue(){
        int totalValue = cValue+cValue;
         
    }
    //this allows the player to recieve one card at a time and add to their overall hand value
    public void getOneMoreCard(){
        int additionCard = cValue;
    }
}


    

