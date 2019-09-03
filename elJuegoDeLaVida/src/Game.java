import java.util.Scanner; //libreria para ingresar datos del usuario

public class Game {
	private Table tablero;
	private int filas;
	private int columnas;
	private int gen;
	private int numEle;
	private Scanner scanner;

	public Game() {
		scanner = new Scanner(System.in);
		obtenerValoresUsuario();
	}

	public void obtenerValoresUsuario() {

		while (true) {
			System.out.println("Ingrese el numero de filas: ");
			try {
				this.filas = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Debes ingresar un solo numeros. Intente de nuevo.");
				scanner.nextLine();
				continue;
			}

			if (this.filas >= 2 && this.filas <= 20) {
				break;
			} else {
				System.out.println("El numero ingresado  de filas es menor o mayor al limite permitido");
			}
		}

		while (true) {
			System.out.println("Ingrese el numero de columnas: ");
			try {
				this.columnas = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Debes ingresar un solo numeros. Intente de nuevo.");
				scanner.nextLine();
				continue;
			}
			if (this.columnas >= 2 && this.columnas <= 20) {
				break;
			} else {
				System.out.println("El numero ingresado  de columnas es menor o mayor al limite permitido");
			}
		}

		while (true) {
			System.out.println("Ingrese el maximo de generaciones: ");
			try {
				this.gen = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Debes ingresar un solo numeros. Intente de nuevo.");
				scanner.nextLine();
				continue;
			}
			if (this.gen > 0 && this.gen <= 50) {
				break;
			} else {
				System.out
						.println("El numero ingresado es menor o mayor al limite de generaciones permitidas generadas");
			}
		}

		while (true) {
			System.out.println("Numero de elementos");
			try {
				this.numEle = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Debes ingresar un solo numeros. Intente de nuevo.");
				scanner.nextLine();
				continue;
			}
			if (numEle > ((filas * columnas) / 2)) {
				System.out.println("Favor de ingresar un numero de elementos menor al 50% del total del tablero");
			} else {
				break;
			}

		}
		
		tablero = new Table(filas, columnas);
		
		while(true) {
			System.out.print("Deseas generar los organismos de manera aleatoria ? (1: automatico/0: manual)");
			scanner.nextLine();
			int res = 0;
			try {
				res = scanner.nextInt();	
			}catch(Exception e) {
				System.out.println("Solo se aceptan las opciones (1/0)");
				continue;
			}
			if(res == 0) {
				obtenerOrganismosUsuario();
				break;
			}else if(res == 1) {
				this.tablero.generarOrganismos();
				break;
			}else {
				System.out.println("Solo se aceptan las opciones (1/0)");
			}
		}

	}
	private void obtenerOrganismosUsuario() {
		int x=0, y=0;
		for(int i =0; i< this.gen; i++) {
			System.out.println("Ingrese las cordenadas del organismo "  +(i+1));
			while(true) {
				try {
					System.out.print("X = ");
					x = scanner.nextInt();
					if(x >=0 && x < filas)
						break;
					else {
						System.out.println("Debe ingresar x dentro del rago. intente de nuevo.");
						scanner.nextLine();
						continue;
					}
				}catch(Exception e) {
					System.out.println("Debe ingresar solo digitos.");
					scanner.nextLine();
				}
			}
			while(true) {
				try {
					System.out.print("Y = ");
					y = scanner.nextInt();
					if(y >=0 && y < columnas)
						break;
					else {
						System.out.println("Debe ingresar y dentro del rago. intente de nuevo.");
						scanner.nextLine();
						continue;
					}
				}catch(Exception e) {
					System.out.println("Debe ingresar solo digitos.");
					scanner.nextLine();
				}
			}
			if(tablero.getTablero()[x][y] == 1) {
				System.out.println("Ya existe un organismo vivo en las coordenadoras ("+ x + "," + y + ")");
				scanner.nextLine();
				i--;
			}else
				tablero.getTablero()[x][y] = 1;
		}
	}
	public void jugar() {// comienza el juego
							// this.tablero = new Table(this.filas, this.columnas);
							// this.tablero.generarOrganismos();//Generamos los organismos aleatoriamente en
							// el tablero.
		System.out.println(tablero.toString());
		scanner.nextLine();
		// Generamos el numero de generaciones ingresadas por el usuario
		Table siguienteTablero = null;
		for (int i = 0; i < gen; i++) {
			System.out.println("---------------------------------------");
			// Obtenemos el siguente tablero y lo imprimimos
			siguienteTablero = this.tablero.getSiguiente();
			System.out.println(siguienteTablero.toString());
			// Checamos si no hay organismos vivos para saber si sigue el juego
			if (siguienteTablero.getVivos() == 0) {
				System.out.println("El tablero ya no cuenta con organismos vivos");
				break;
			}
			// Si el tablero es igual en la generacion siguiente, entonces el juego termina.
			if (this.tablero.equals(siguienteTablero)) {
				System.out.println("El nuevo tablero es igual al anterior... se termina el juego.");
				break;
			}
			scanner.nextLine();
			this.tablero = siguienteTablero;
			siguienteTablero = null;
		}
	}
}
