public class Person {
    private String name;
    private Hand hand;
    public Person(String name){
        this.name = name;
        hand = new Hand();
    }
    public Hand getHand() {
        return hand;
    }
    public String getName(){
        return this.name;
    }
}
