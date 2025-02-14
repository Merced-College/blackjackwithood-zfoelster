package package1;
public class Card {
private String cSuit;
private String cName;
private int cValue;
private String cPicture;

    public Card(String cardSuit, String cardName, int cardValue, String cardPicture)
    {
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
    public String printSuiteCard(){
        String suiteCard = (cName + " of " + cSuit + "s");
        return suiteCard;
    }
}