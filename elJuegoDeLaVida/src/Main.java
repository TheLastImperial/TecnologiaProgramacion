//REGLAS DEL JUEGO

//1. Cualquier organismo vivo,pertenece a la próxima generación así en la siguiente generación si tiene de vecinos a dos o tres organismos vivos.
//2. Los organismos con menos de dos vecinos mueren de aislamiento.
//3. Los organismos con más de tres vecinos mueren de hacinamiento.
//4. Las celdas vacías que tienen exactamente tres organismos vecinos nacen en la siguiente generación.
//Programa principal.

public class Main {

	public static void main(String[] args) {
		Game juego = new Game();// Se crea una instancia de clase juego
		juego.jugar();// Comienza el juego.
	}
}
