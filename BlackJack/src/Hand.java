import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
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
                int aux = points.get(i);
                points.set(i, aux);
            }

            points.removeIf(ele -> ele > 21);
        }
        return points;
    }
}
