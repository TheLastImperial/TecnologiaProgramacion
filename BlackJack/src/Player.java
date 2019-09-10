public class Player {
    private String name;
    private int credit;
    private int bet;
    private boolean isPlaying;
    private boolean isCroupier;
    private Hand hand;

    public Player(String name, int credit){
       this.name = name;
       this.credit = credit;
    }
    public Player(String name, boolean isCroupier){
        this.name = name;
        this.credit = credit;
        this.isCroupier = isCroupier;
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
    public boolean isPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isCroupier() {
        return isCroupier;
    }

    public Hand getHand() {
        return hand;
    }
    public String getName(){
        return this.name;
    }
    public int getCredit(){
        return this.credit;
    }
}
