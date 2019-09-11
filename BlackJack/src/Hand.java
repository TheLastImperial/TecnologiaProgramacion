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
        ArrayList<Integer> points = getPoints();
        points.removeIf(ele-> ele> 21);
        Collections.sort(points);
        int result = 22;
        if(!points.isEmpty())
            result = points.get(points.size()-1);
        return result;
    }
    public Card getCard(int index){
        return cards.get(index);
    }
    public boolean isBlackJack(){
        return cards.size() == 2 &&
            (cards.get(0).getValue() == CardValue.AS || cards.get(0).getValue().getCardValue() == 10) &&
            (cards.get(1).getValue() == CardValue.AS || cards.get(1).getValue().getCardValue() == 10);
    }
}
