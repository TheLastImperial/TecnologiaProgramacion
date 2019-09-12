public class Player extends Person{
    private int credit;
    private int bet;

    public Player(String name, int credit){
       super(name);
       this.credit = credit;
    }
    public void win(){
        credit += (bet*2);
        bet = 0;
        getHand().resetHand();
    }
    public void lost(){
        bet = 0;
        getHand().resetHand();
    }
    public void draw(){
        credit =+ bet;
        bet=0;
        getHand().resetHand();
    }
    public boolean bet(int quantity){
        boolean flag = false;
        if(quantity <= credit){
            credit -= quantity;
            bet = quantity;
            flag = true;
        }
        return flag;
    }

    public int getBet() {
        return bet;
    }

    public int getCredit(){
        return this.credit;
    }
}
