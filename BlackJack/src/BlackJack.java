import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    private ArrayList<Player> players;
    private Croupier croupier;
    private Scanner scanner;
    public BlackJack(){
        players = new ArrayList<Player>();
        scanner = new Scanner(System.in);
        croupier = new Croupier();
        createPlayers();
        play();
    }
    public void initGame(){
        int bet = 0;
        // Obtenemos las apuestas de los jugadores.
        for (Player p: players){
            System.out.print(p.getName() + ". Tienes $"+ p.getCredit() + " Cuanto quieres apostar ?");
            while (true){
                try{
                    bet = scanner.nextInt();
                }catch(Exception e){
                    System.out.println("Debe ingresar una cantidad");
                    scanner.nextLine();
                    continue;
                }
                if(p.getCredit() == 0){
                    System.out.println(p.getName() + " no tienes saldo. Bye");
                    players.remove(p);
                    if(players.isEmpty()){
                        return;
                    }
                    break;
                }
                if(!p.bet(bet)){
                    System.out.println(p.getName() + " no tienes suficiente credito para aportar " + bet + ". Intenta de nuevo.");
                    scanner.nextLine();
                }else{
                    break;
                }
            }
        }

    }
    public void play() {
        boolean flag = true;
        while(flag){
            initGame();
            if(players.isEmpty()){
                System.out.println("Ya no hay juagadores con credito. Se termina el juego");
                return;
            }
            scanner.nextLine();
            distributeCards();
            System.out.println("_____________________Siguiente juego________________________");
        };
    }

    public void distributeCards(){
        croupier.getDeck().shuffle();
        croupier.firstHand(players);
        System.out.println("------Primeras cartas-----------");
        System.out.println(printAll(false));
        if(croupier.getHand().isBlackJack()){
            whoWin();
            return;
        }
        for (Player p: players){
            int opt = 0;
            boolean flag = true;
            while(flag){
                System.out.println(croupier);
                System.out.println(p +" "+ p.getHand().getPoints());
                if(!p.getHand().canPlay()){
                    System.out.println(p.getName() + " te pasaste de 21.");
                    flag = false;
                    continue;
                }
                System.out.print(p.getName() + " te plantas(1) o pides otra carta(2) ?");
                try{
                    opt = scanner.nextInt();
                }catch(Exception e){
                    opt = 0;
                }
                System.out.println("__________________________________________________________");
                switch(opt){
                    case 1:
                        flag = false;
                        break;
                    case 2:
                        croupier.distribute(p);
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        scanner.nextLine();
                }
            }
        }
        croupier.playCroupier();
        whoWin();
        croupier.resetDeck();
        croupier.getHand().resetHand();
    }
    /*
    * Este metodo se encarga de ver quienes
    * son los ganadores y pagar las apuestas.
    * */
    private void whoWin(){
        System.out.println(printAll(true));
        int croupierPoint = croupier.getHand().maxPoint();
        // Establece quien gana por cada jugador.
        for(Player p: players){
            int pPoint = p.getHand().maxPoint();
            // Croupier gana
            if((croupierPoint <= 21 &&  croupierPoint > pPoint) || (croupierPoint <= 21 && pPoint>21)){
                System.out.println("Croupier le gana a " + p.getName());
                p.lost();
                // Gana jugador
            }else if((pPoint<= 21 && croupierPoint<= 21 && pPoint > croupierPoint) || ( croupierPoint> 21 && pPoint <= 21)){
                System.out.println(p.getName() + " le gana al croupier");
                p.win();
            }else {
                System.out.println("Croupier empata con " + p.getName());
                p.draw();
            }
        }
    }
    private void createPlayers(){
        int numPlayers = 0;
        String name = "";
        int credit = 0;
        while(true){
            System.out.print("Ingrese el numero de jugadores: ");
            try{
                numPlayers = scanner.nextInt();
            }catch(Exception e){
                System.out.println("Debe ingresar solo numeros.");
                scanner.nextLine();
                continue;
            }
            if(numPlayers >0 && numPlayers <= 3){
                break;
            }else{
                System.out.println("Solo pueden jugar de 1 a 3 jugadores.");
            }
        }
        for (int i = 1; i<= numPlayers; i++){
            System.out.println("Ingrese los datos del jugador: " + i);
            scanner.nextLine();
            while(true){
                System.out.print("Nombre: ");
                name = scanner.nextLine();
                if(name.trim().isEmpty()){
                    System.out.println("Debe ingresar un nombre para el jugador: " + i);
                }else{
                    break;
                }
            }
            while(true){
                System.out.print("Credito: ");
                try {
                    credit = scanner.nextInt();
                }catch(Exception e){
                    System.out.println("Debes ingresar solo numeros");
                    scanner.nextLine();
                    continue;
                }
                if(credit<= 0){
                    System.out.println("Debe ingresar credito mayor a cero.");
                }else{
                    break;
                }
            }

            players.add(new Player(name, credit));
        }
    }

    private String printAll(boolean showCroupier){
        String result = croupier.toString();
        if(showCroupier)
            result += " " +croupier.getHand().getPoints() + "\n";
        else
            result += "\n";
        for (Player p: players)
            result += p.toString() + p.getHand().getPoints() + "\n";
        return result;
    }
}
