import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    private ArrayList<Player> players;
    private Deck deck;
    private Player croupier;
    private Scanner scanner;
    public BlackJack(){
        players = new ArrayList<Player>();
        scanner = new Scanner(System.in);
        deck = new Deck(false);
        croupier = new Player("Croupier", true);
        initGame();
        play();
    }
    public void initGame(){
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
    }
}
