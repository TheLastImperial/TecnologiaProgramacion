import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();
    Boolean acceptSpecialCards;

    public Deck(Boolean acceptSpecialCards) {
        // Crear una baraja con todos los valores de cada palo si acepta especiales se agregan 2 joker.
        ArrayList<Card> hearts = createCardsByType(CardType.HEART);
        ArrayList<Card> spades = createCardsByType(CardType.SPADE);
        ArrayList<Card> diamonds = createCardsByType(CardType.DIAMOND);
        ArrayList<Card> clovers = createCardsByType(CardType.CLOVER);
        cards.addAll(hearts);
        cards.addAll(spades);
        cards.addAll(diamonds);
        cards.addAll(clovers);

        if(acceptSpecialCards){
            cards.add(new Card(CardType.HEART, CardValue.JOKER));
            cards.add(new Card(CardType.DIAMOND, CardValue.JOKER));
        }
    }

    public ArrayList<Card> getCards () {
        return cards;
    }

    //Método para mezclar la baraja con Random.
    // Se utiliza una lista auxiliar de cartas que se llenan aleatoriamente con las cartas que van quedando de la baraja.
    public void shuffle () {
        ArrayList<Card> cardsShuffled = new ArrayList<Card>();
        Random rand = new Random();
        System.out.println("Cards tiene " + cards.size());
        while(cardsShuffled.size() < 47) {
            System.out.println("Cards tiene " + cards.size());
            Card randomItem = cards.get(rand.nextInt(cards.size() - 1));
            cardsShuffled.add(randomItem);
            cards.remove(randomItem);
        }
        cards = cardsShuffled;
    }


    //Método para partir la baraja por la mitad.
    // Se crea una lista auxiliar para ir guardando la posición de las cartas de la baraja.
    public void breakIn() {
        ArrayList<Card> newCards = new ArrayList<Card>();
        int middlePosition = (int) Math.floor(cards.size() / 2);
        for(int i = middlePosition; i < cards.size(); i++){
            newCards.add(cards.get(i));
        }
        for(int i = 0; i < middlePosition; i++){
            newCards.add(cards.get(i));
        }
        cards = newCards;
    }


    //Método que devuelve la siguiente carta en el juego.
    public Card nextCard() {
        Card nextCard = cards.get(0);
        cards.remove(0);
        return nextCard;
    }

    //Método privado para imprimir la baraja para testear que los métodos de la baraja se ejecuten correctamente.
    private void printDeck () {
        cards.forEach((n) -> System.out.println(n));
    }

    private ArrayList<Card> createCardsByType(CardType type){
        ArrayList<Card> cardsByType = new ArrayList<Card>();
        Card card1 = new Card(type, CardValue.AS);
        Card card2 = new Card(type, CardValue.TWO);
        Card card3 = new Card(type, CardValue.THREE);
        Card card4 = new Card(type, CardValue.FOUR);
        Card card5 = new Card(type, CardValue.FIVE);
        Card card6 = new Card(type, CardValue.SIX);
        Card card7 = new Card(type, CardValue.SEVEN);
        Card card8 = new Card(type, CardValue.NINE);
        Card card9 = new Card(type, CardValue.TEN);
        Card card10 = new Card(type, CardValue.JACK);
        Card card11 = new Card(type, CardValue.QUEEN);
        Card card12 = new Card(type, CardValue.KING);
        cardsByType.add(card1);
        cardsByType.add(card2);
        cardsByType.add(card3);
        cardsByType.add(card4);
        cardsByType.add(card5);
        cardsByType.add(card6);
        cardsByType.add(card7);
        cardsByType.add(card8);
        cardsByType.add(card9);
        cardsByType.add(card10);
        cardsByType.add(card11);
        cardsByType.add(card12);
        return cardsByType;
    }
}
