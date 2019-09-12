import java.util.ArrayList;

public class Croupier extends Person {
    private Deck deck;

    public Croupier() {
        super("Croupier");
        deck = new Deck(false);
    }

    public void distribute(Person player) {
        player.getHand().setCard(deck.nextCard());
    }

    public void firstHand(ArrayList<Player> players) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < players.size(); j++) {
                Player aux = players.get(j);
                distribute(aux);
            }
            distribute(this);
        }
        Card lastCard = getHand().getCard(1);
        lastCard.setVisible(false);
    }

    public Deck getDeck(){
        return deck;
    }

    public void resetDeck(){
        deck = new Deck(false);
    }
    /*
     * El croupier toma cartas hasta que tenga mas
     * de 17 y menos de 21 o que se pase de 21.
     * */
    public void playCroupier() {
        Card lastCard = getHand().getCard(1);
        lastCard.setVisible(true);
        if (!getHand().isBlackJack()) {
            while (true) {
                ArrayList<Integer> points = getHand().getPoints();
                // Solo tiene un valor en el arreglo.
                if (points.size() == 1) {
                    int point = points.get(0);
                    // Toma una carta si no esta en el rango de 17 a 21,
                    // en caso contrario termina el ciclo y el metodo.
                    if (point < 17 && !(point > 21)) {
                        distribute(this);
                    } else {
                        break;
                    }
                } else {
                    points.removeIf(ele -> ele > 21);
                    // Si no tiene puntos significa que solo tenia valores mayores a 21.
                    if (points.isEmpty()) {
                        break;
                    }
                    points.removeIf(ele -> ele < 17);
                    // Ya tiene una cantidad mayor a 17
                    if (!points.isEmpty()) {
                        break;
                    } else {
                        distribute(this);
                    }
                }
            }
        }
    }
}
