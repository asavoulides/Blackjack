public class Card{

private String suit;
private int rank;

public Card(String suit, int rank){
  this.suit = suit;
  this.rank = rank;
}

public String getSuit(){
  return suit;
}

public int getRank(){
  return rank;
}

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
public String getSuitName(){
  if(suit == "s"){
    return "Spades";
  }
  else if(suit == "d"){
    return "Diamonds";
  }
  else if(suit == "c"){
    return "Clubs";
  }
  else{
    return "Hearts";
  }
}

public int getValue(){
    if(rank >= 10){
        return 10;
    }
    else if(rank == 1){
        return 11;
    }
    return rank;

}

public String toString(){
  return getRankName() + " Of " + getSuitName();
}

}

