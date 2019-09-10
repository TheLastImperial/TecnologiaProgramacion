
public class Card {
	private CardType type;
	private CardValue value;
	public Card(CardType type, CardValue value){
		this.type = type;
		this.value = value;
	}
	public String toString(){
		return type + " " + value;
	}
	public CardValue getValue(){
	    return value;
    }
}
