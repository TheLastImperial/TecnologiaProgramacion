
public class Card {
	CardType type;
	CardValue value;

	public Card(Enum type, Enum value) {
		this.type = (CardType) type;
		this.value = (CardValue) value;
	}

	public Enum getCardValue() {
		return value;
	}

	public Enum getCardType() {
		return type;
	}

}
