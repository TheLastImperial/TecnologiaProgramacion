//REGLAS DEL JUEGO

//1. Cualquier organismo vivo,pertenece a la pr�xima generaci�n as� en la siguiente generaci�n si tiene de vecinos a dos o tres organismos vivos.
//2. Los organismos con menos de dos vecinos mueren de aislamiento.
//3. Los organismos con m�s de tres vecinos mueren de hacinamiento.
//4. Las celdas vac�as que tienen exactamente tres organismos vecinos nacen en la siguiente generaci�n.
//Programa principal.

public class Main {

	public static void main(String[] args) {
		Game juego = new Game();// Se crea una instancia de clase juego
		juego.jugar();// Comienza el juego.
	}
}
