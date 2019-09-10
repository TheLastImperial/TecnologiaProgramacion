
public class Card {
	private CardType type;
	private CardValue value;
	private Boolean visible;
	public Card(CardType type, CardValue value){
		this.type = type;
		this.value = value;
		this.visible = true;
	}
	public String toString(){
		String result = "";
		if(this.visible)
			result = type + "-" + value;
		return result;
	}
	public CardValue getValue(){
	    return value;
    }

    public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}
