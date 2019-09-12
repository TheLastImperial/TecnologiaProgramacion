import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    private ArrayList<Player> players;
    private Deck deck;
    private Croupier croupier;
    private Scanner scanner;
    public BlackJack(){
        players = new ArrayList<Player>();
        scanner = new Scanner(System.in);
        deck = new Deck(false);
        croupier = new Croupier();
        createPlayers();
        play();
    }
9    public void createPlayers(){
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
    public void play(){
        Boolean keepPlaying = true;
        while(keepPlaying && players.size() > 0) {
            initGame();
            croupier.firstHand(players);
            imprimeJuego();
            Boolean endGame = false;
            System.out.println(" ");
            System.out.println("Empieza el juego ");
            int playersLost = 0;
            for (Player p: players){
                int opt = 0;
                boolean flag = true;
                System.out.println("-----------------------------------");
                while(flag){
                    System.out.println(" ");
                    System.out.println(p.getName());
                    System.out.println("Tu mano es " + p.getHand().toString());
                    System.out.println("En puntos "+ p.getHand().getPoints());
                    if(p.getHand().maxPoint() > 21) {
                        flag = false;
                        System.out.println(p.getName() + " te has pasado del 21, HAS PERDIDO ");
                        playersLost++;
                    }
                    if(flag) {
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
            }
            System.out.println("Player losts  " + playersLost);
            if(playersLost != players.size()) {
                System.out.println(" ");
                System.out.println(" Turno del croupier ");
                croupier.playCroupier();
            }

            endGame();
            System.out.println(" ");
            System.out.println("Continuar jugando (1)");
            System.out.println("Salir (0)");
            int keepPlayingInt = scanner.nextInt();
            if(keepPlayingInt == 0) {
                keepPlaying = false;
            }
            croupier.resetDeck();
            croupier.getHand().resetHand();
        }
        if(players.size() == 0) {
            System.out.println("¡NO HAY JUGADORES DISPONIBLES!");
        }
    }

    public void initGame() {
        checkPlayersWithOutCredit();
        players.forEach((player ->  {
            Boolean flag = false;
            while(!flag) {
                System.out.println(player.getName() + ", cuánto deseas apostar (Máx "+ player.getCredit() + "). ->");
                int bet = scanner.nextInt();
                flag = player.bet(bet);
            }
        }));
        players.forEach((player ->  {
            System.out.println(player.getName() +  " " + player.getBet());
        }));
    }

    public void imprimeJuego() {
        System.out.println("------------ Juego al momento");
        System.out.println(" ");
        System.out.println("Croupier: " + croupier.getHand().toString());
        System.out.println(" ");
        players.forEach((player -> {
            System.out.println(player.getName() + ": " + player.getHand().toString() + "  puntos: " + player.getHand().getPoints());
        }));
    }
    private void endGame() {
        System.out.println(" ");
        System.out.println("-------   FINAL DEL JUEGO  ---------");
        System.out.println("Croupier " + croupier.getHand().toString() + " - " + croupier.getHand().maxPoint());
        int croupierPoints = croupier.getHand().maxPoint();
        System.out.println(" ");
        players.forEach(player -> {
            System.out.println(player.getName() + " " + player.getHand().toString() + " - " + player.getHand().maxPoint());
            int playerPoints = player.getHand().maxPoint();
            if(playerPoints > 21) {
                System.out.println(player.getName() + " PIERDE!!");
                player.lost();
            } else if (croupierPoints > 21) {
                System.out.println(player.getName() + " GANA!!");
                player.win();
            } else if (playerPoints > croupierPoints){
                System.out.println(player.getName() + " GANA!!");
                player.win();
            } else if( playerPoints == croupierPoints){
                System.out.println(player.getName() + " EMPATA!!");
                player.draw();
            } else if( playerPoints < croupierPoints){
                System.out.println(player.getName() + " PIERDE!!");
                player.lost();
            }
        });
        System.out.println(" ");
        System.out.println("CREDITO AL MOMENTO...");
        players.forEach(player -> {
            System.out.println(player.getName() + " tiene " + player.getCredit());
        });
    }

    private void checkPlayersWithOutCredit() {
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getCredit() == 0) {
                players.remove(i);
                i--;
            }
        }
    }
}
