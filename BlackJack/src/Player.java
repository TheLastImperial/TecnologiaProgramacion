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
        //getHand().resetHand();
    }
    public void lost(){
        bet = 0;
        //getHand().resetHand();
    }
    public void draw(){
        credit =+ bet;
        bet=0;
        //getHand().resetHand();
    }
    public int getBet(){
        return bet;
    }
    public boolean bet(int quantity){
        boolean flag = false;
        if(this.credit > 0 && quantity <= credit){
            credit -= quantity;
            bet = quantity;
            flag = true;
        }
        return flag;
    }

    public int getCredit(){
        return this.credit;
    }
}
