# Blackjack in Java

### Installation
1. Download this repository
```bash
git Clone https://github.com/asavoulides/Blackjack
```
2. Navigate to the directory where this repository is hosted

3. Run game.Java
```bash
java Game
```

### Debugging
1. Ensure [Java](https://www.java.com/en/download/help/download_options.html) is correctly installed on your system. 
2. Ensure all files have properly compiled
```bash
javac Card.java Deck.java Game.java
```

### Functionality
#### Card.java
Initiates Card Class

Each Card is defined by a suit and a rank
```bash
public Card(String suit, int rank){
  this.suit = suit;
  this.rank = rank;
}
```
Face Cards exceed 10 in rank requiring additional processing
``` bash
public String getRankName(){
  if(rank <= 10 && !(rank == 1)){
    return String.valueOf(rank);
  }
  else{
    if(rank == 11){
      return "Jack";
      }
    else if(rank == 12){
      return "Queen";
      }
    else if(rank == 13){
      return "King";
    }
    else{
      return "Ace";
    }
    
    }
  }
```
*When a card exceeds a value of 10 or is an ace (1) additional processing is required*

Rank is not reflective of BlackJack value but defaults to rank unless rank exceeds 10 or is equal to 1.
```bash
public int getValue(){
    if(rank >= 10){
        return 10;
    }
    else if(rank == 1){
        return 11;
    }
    return rank;

}
```

#### Deck.java
Creates Deck  working with Card class

Define all possible suits/ranks 
```bash
static int[] ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
static String[] suits = {"s", "d", "c", "h"};
static ArrayList<Card> deck = new ArrayList<Card>();
```

Initiates ArrayList containing the Deck using values created above
```bash
public static void makeDeck(){
    deck.clear();
    for(int i = 0; i < ranks.length; i++){
        for(int y = 0; y < suits.length; y++){
            deck.add(new Card(suits[y], ranks[i]));
    }
  }
}
```
*Assumes Each Card has 4 suits*

Enables shuffleDeck() functionality through collections library
```bash
public static void shuffleDeck(){
  Collections.shuffle(deck);
}
```

Enables GetCard, returning the card "on top" of the deck. Removes card afterwards.
```bash
public static Card getCard(){
  Card nextCard = deck.get(deck.size()-1);
  deck.remove(deck.size()-1);
  return nextCard;
}
```

Basic toString method allowing display of Cards
```bash
public String toString(){
  return getRankName() + " Of " + getSuitName();
}
}
```

init method calling all relavent and necessary methods
```bash
public static void init(){
    makeDeck();
    shuffleDeck();
}
```

#### Game.java
Combines functionality between Deck.java and Card.java to create Blackjack.

Blackjack Method. Runs immediately when main method is called. Contains numerous functions.



#### Sources
*Used [Tabby](https://raw.githubusercontent.com/TabbyML/tabby/refs/heads/main/README.md) README as inspiration*
*Used [Markdown Guide](https://github.com/mattcone/markdown-guide/tree/master) to aid writting*
