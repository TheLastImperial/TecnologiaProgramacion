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
    }
    public void lost(){
        credit -= bet;
        bet = 0;
    }
    public void draw(){
        credit =+ bet;
        bet=0;
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

    public int getCredit(){
        return this.credit;
    }
}
