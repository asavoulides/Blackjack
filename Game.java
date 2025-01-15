import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game{
static ArrayList<Card> playerCards = new ArrayList<Card>();
static ArrayList<Card> dealerCards = new ArrayList<Card>();


//Copied from stack overflow; simple function to clear the terminal. See Sources
public static void clearScreen() {   
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  

/*
Takes in paramater inputList and returns totalValue (what the hand's blackjack value is)
Uses logic assuming aces are 11 until player exceeds 21, then iterates through each ace until:
a) score is below 21 or b) no more aces left

*/ 
public static int cardsValue(ArrayList<Card> inputList){
    int aces = 0;
    int totalValue = 0;
    for(Card card : inputList){
        if(card.getRank() == 1){
            aces += 1;
        }
        totalValue += card.getValue();
    }
    while(totalValue > 21 && aces > 0){
        totalValue -= 10;
        aces--;
    }
    return totalValue;
}

// Takes in BetSize and Stack and returns it in an organized fashion
public static void printStatus(int betSize, int stack, boolean revealDealerCards){
    clearScreen();
    String newLine = "=======================================";
    System.out.println(newLine);
    System.out.println("Current Stack: $" + (stack - betSize) + " | Bet Amount $ " + betSize);
    System.out.println(newLine);

    System.out.println("---- Your Cards ----");
    for(Card card : playerCards){
        System.out.println(card);    
    }
    System.out.println("\nValue: "+ cardsValue(playerCards));
    
    
    System.out.println("---- Dealer Cards ----");
    if(revealDealerCards == true){
        for(Card card: dealerCards){
            System.out.println(card);    
        }
        System.out.println("\nValue: " + cardsValue(dealerCards));
    }
    else{
        System.out.println(dealerCards.get(0));
        System.out.println("Hidden Card");
        System.out.println("\nValue: " + dealerCards.get(0).getValue());
    }
    
    
    System.out.println(newLine);
    }

public static boolean testBlackjack(){ // Tests if a player has blackjack
    if (cardsValue(playerCards) == 21){
        return true;
    }
    else{
        return false;
    }
}

public static boolean userWon(){ // Tests if player won/lost
    int playerValue = cardsValue(playerCards);
    int dealerValue = cardsValue(dealerCards);

    if(playerValue > 21){
        return false;
    }
    if(dealerValue > 21){
        return true;
    }

    if(playerValue > dealerValue){
        return true;
    }
    else{
        return false;
    }
    }

public static void waitFor(int seconds){
    try{
        TimeUnit.SECONDS.sleep(seconds);
    }
    catch(Exception e){
        //no exception needed; code runs as usual w/o delay
    }
}

public static void Blackjack(){
    Scanner myScanner = new Scanner(System.in);
    int stack = 1000;
    clearScreen();

    System.out.println("Welcome to Blackjack.");
    
    while(true){
        Deck.init();
        playerCards.clear();
        dealerCards.clear();
        boolean losing = false;
        boolean validBet = false;
        int betSize = 0;

        while(validBet == false){
            System.out.println("What is your desired bet? You have a stack of $" + stack + ".");
            betSize = myScanner.nextInt();
        if(betSize > stack){
            System.out.println("Bet Exceeds Your Stack!");
            }
         else{
            validBet = true;
    }
    }
    
    myScanner.nextLine(); // Debugging solution to "scannernextline leaving leftover character" see Sources

    playerCards.add(Deck.getCard());
    dealerCards.add(Deck.getCard());

    playerCards.add(Deck.getCard());
    dealerCards.add(Deck.getCard());
    
    

    printStatus(betSize, stack, false);


    while(testBlackjack() == false){
        System.out.println("Would you like to double down for an extra $" +betSize + "? (y/n)");
        String doubleDown = myScanner.nextLine();
        if(doubleDown.equalsIgnoreCase("y")){
            betSize *= 2;
            playerCards.add(Deck.getCard());
            printStatus(betSize, stack, false);
            if(cardsValue(playerCards) > 21){
                losing = true;}
            break;
            
        }
        printStatus(betSize, stack, false);

        System.out.println("Would you like to hit or stand (h/s)");
        String hitStand = myScanner.nextLine();

        if(hitStand.equalsIgnoreCase("h")){
            System.out.println("*drumroll*");
            waitFor(1);
            playerCards.add(Deck.getCard());
            printStatus(betSize, stack, false);
            if(cardsValue(playerCards) > 21){
                losing = true;
                break;
            }
        }
        else if(hitStand.equalsIgnoreCase("s")){
            break;
        }
    } 
    printStatus(betSize, stack, true);
      
    if(losing == true){
        System.out.println("You Busted!");
    }

    boolean hasAce = false;
    while(cardsValue(dealerCards) < 17 && losing == false && testBlackjack() == false){
        printStatus(betSize, stack, true);
        System.out.println("*Dealers Getting Card*");
        waitFor(2);
        dealerCards.add(Deck.getCard());
        printStatus(betSize, stack, true);

        for(Card card : dealerCards){
            if(card.getValue() == 11){
                hasAce = true;
            }
        }
    }
    if(hasAce == true){
        dealerCards.add(Deck.getCard());
        printStatus(betSize, stack, true);
    }
    if(cardsValue(dealerCards) == cardsValue(playerCards) && losing == false){
        System.out.println("You tied, winning $" + betSize);
    }
    else if(testBlackjack() == true){
        System.out.println("You Hit Blackjack winning $" + (betSize * 1.5 + betSize));
    }
    else if(userWon() == true && losing == false){
        System.out.println("Congratulations! You have won $" + (betSize*2));
        stack += betSize;
    }
    else{
        System.out.println("You lost $" + betSize + ". Better luck next time");
        stack -= betSize;
    }
    if(stack <= 0){
        System.out.println("You are out of money!");
        waitFor(3);
        clearScreen();
        break;
    }

    
}
}
public static void main(String[] args){
   Blackjack();
}

}


/* Sources

Clear Terminal 
https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java 

Ignore Equals
https://www.w3schools.com/java/ref_string_equalsignorecase.asp

Time Delay (as of 1/10 not used)
https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java 

Used this for debugging input issues
https://stackoverflow.com/questions/36108691/scannernextline-leaves-a-leftover-newline-character 

Clear ArrayList
https://www.geeksforgeeks.org/arraylist-clear-method-in-java-with-examples/

catch except
https://www.w3schools.com/java/java_try_catch.asp
*/