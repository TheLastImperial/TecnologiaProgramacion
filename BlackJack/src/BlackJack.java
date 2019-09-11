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
        initGame();
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
        croupier.firstHand(players);
        System.out.println("------Primeras cartas-----------");
        System.out.println(printAll(false));
        System.out.println("--------------------------------");
        for (Player p: players){
            int opt = 0;
            boolean flag = true;
            while(flag){
                System.out.println(p +" "+ p.getHand().getPoints());
                System.out.println(p.getName() + " te plantas(1) o pides otra carta(2) ?");
                try{
                    opt = scanner.nextInt();
                }catch(Exception e){
                    opt = 0;
                }
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
        System.out.println(printAll(true));
    }
    public void play(){
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
