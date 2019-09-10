import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("---------------------------------------------");
		System.out.println("------- BIENVENIDO AL JUEGO DE BARAJA -------");

		try {
			int option = -1;
			while(option > 1 || option < 0) {
				System.out.println("Juegos: ");
				System.out.println("1-. 21 ( Black Jack) ");
				System.out.println(" ");
				System.out.println("0-. Salir ");
				System.out.println("Seleccione una opción -> ");
			    while(true){
			    	try{
						option = scan.nextInt();
					}catch(Exception e){
						System.out.println("Favor de solo ingresar valores numericos.");
						scan.nextLine();
						continue;
					}
			    	break;
				}
				switch (option) {
					case 1:
						new BlackJack();
						break;
					case 0:
						System.out.println("Gracias por su visita!");
						//System.exit(0);
						break;
					default:
					    break;
				}
			}

		} catch(NumberFormatException e) {
			System.out.println("Ingrese un número válido");
		}
	}

}
