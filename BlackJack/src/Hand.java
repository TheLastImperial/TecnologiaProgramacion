import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private ArrayList<Card> cards;
    public Hand(){
        cards = new ArrayList<Card>();
    }
    public String toString(){
        String result = "";
        for(Card c:cards) {
            result += c + "|";
        }
        return result;
    }
    public void setCard(Card card){
        cards.add(card);
    }
    public ArrayList<Integer> getPoints(){
        ArrayList<Integer> points = new ArrayList<Integer>();
        ArrayList<Integer> auxList = new ArrayList<Integer>();
        int tot = 0;
        for (Card c:cards){
            if(c.getValue().getCardValue() == CardValue.AS.getValue()){
                points.add(1);
                auxList.add(11);
            }
            else
                tot+= c.getValue().getCardValue();
        }
        if(points.isEmpty()){
            points.add(tot);
        }
        else{
            points.addAll(auxList);
            for (int i = 0; i< points.size();i++){
                int aux = points.get(i) + tot;
                points.set(i, aux);
            }
        }
        return points;
    }
    public int maxPoint(){
        int ases = 0;
        int points = 0;
        for (Card card: cards) {
            if(card.getValue() == CardValue.AS){
                ases ++;
            }else{
                points += card.getValue().getCardValue();
            }
        }
        for(int i = 0; i < ases; i++){
            if(points > 10){
                points++;
            }else{
                points += 11;
            }
        }
        return points;
    }

    public boolean canPlay(){
        ArrayList<Integer>  point = getPoints();
        point.removeIf(ele -> ele > 21);
        return !point.isEmpty();
    }

    public Card getCard(int index){
        return cards.get(index);
    }
    public boolean isBlackJack(){
        return maxPoint() == 21;
    }

    public void resetHand(){
        cards = new ArrayList<Card>();
    }

    public int handLength() {
        return cards.size();
    }
}
