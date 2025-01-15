import java.util.ArrayList;
import java.util.Collections;

public class Deck{ 

static int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
static String[] suits = {"s", "d", "c", "h"};
static ArrayList<Card> deck = new ArrayList<Card>();

public static void init(){
    makeDeck();
    shuffleDeck();
}

public static void makeDeck(){
    deck.clear();
    for(int i = 0; i < ranks.length; i++){
        for(int y = 0; y < suits.length; y++){
            deck.add(new Card(suits[y], ranks[i]));
      

    }
  }
}

public static void shuffleDeck(){
  Collections.shuffle(deck);
}

public static Card getCard(){
  Card nextCard = deck.get(deck.size()-1);
  deck.remove(deck.size()-1);
  return nextCard;

}
}




/* Used for most primitive level of design; 
gave inspiration for MakeDeck Function; same idea as discussed in class.

https://medium.com/@ankitviddya/designing-deck-of-cards-data-structure-in-java-using-object-oriented-principles-606f1e100d06

*/
