
public class Table {
	private byte[][] tablero;
	private int filas;
	private int columnas;

	public Table(int filas, int columnas) {
		tablero = new byte[filas][columnas];
		this.filas = filas;
		this.columnas = columnas;
	}

	public Table getSiguiente() {
		// Se crea el siguente tablero y se recorre en todos los elementos del tablero
		// original para guardar la siguiente generacion
		Table siguiente = new Table(this.filas, this.columnas);
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				siguiente.getTablero()[i][j] = valorCasilla(i, j);
			}
		}
		return siguiente;
	}

	// Con este metodo se generan los organismos de manera aleatoria
	public void generarOrganismos() {
		// Se establece el 50% de los organismos que
		// caben en el tablero
		int totalOrg = ((tablero.length * tablero[0].length) / 2);

		// Recorremos el tablero
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				// En caso de que el random sea menor a 0.5
				// entonces colocamos un organismo en la
				// casilla actual
				if (Math.random() < 0.5) {
					tablero[i][j] = 1;
					totalOrg--;
				}
				// Nos salimos del ciclo cuando llegamos al 50%
				if (totalOrg == 0)
					break;
			}
			// Nos salimos del ciclo cuando llegamos al 50%
			if (totalOrg == 0)
				break;
		}
	}

	// Este metodo se encarga de asignar el valor de la casilla para la siguiente
	// generación.
	private byte valorCasilla(int i, int j) {
		int vivos = 0;
		byte result = 0;
		// Se validan todos los vecinos
		try {
			vivos+= tablero[i][j + 1];
		} catch (Exception e) {
		}

		try {
			vivos+= tablero[i][j - 1];
		} catch (Exception e) {
		}

		try {
			vivos+= tablero[i + 1][j];
		} catch (Exception e) {
		}

		try {
				vivos+= tablero[i - 1][j];
		} catch (Exception e) {
		}

		try {
			vivos+= tablero[i + 1][j + 1];
		} catch (Exception e) {
		}

		try {
			vivos+= tablero[i - 1][j - 1];
		} catch (Exception e) {
		}
		try {
			vivos+= tablero[i + 1][j - 1];
		} catch (Exception e) {
		}

		try {
			vivos+= tablero[i - 1][j + 1];
		} catch (Exception e) {
		}

		// Los organismos vivos con 2 o 3 organismos
		// como vecinos continuan en la siguiente generacion.
		if (tablero[i][j] == 1 && (vivos == 2 || vivos == 3))
			result = 1;
		// Los organismos vivos con menos de 2 vecinos mueren de aislamientos
		if (tablero[i][j] == 1 && vivos < 2)
			result = 0;
		// Los organismos con mas de 3 organismos mueren de hacinamiento.
		if (tablero[i][j] == 1 && vivos > 3)
			result = 0;
		// Las celdas vacias con exactamente 3 vecinos. Generan un nuevo organismo para
		// la siguiente generacion.
		if (tablero[i][j] == 0 && vivos == 3)
			result = 1;
		return result;
	}

	// Este metodo calcula el total de casillas que tiene el tablero
	public int getTotalCasillas() {
		return this.filas * this.columnas;
	}

	// Este metodo retorna el tablero
	public byte[][] getTablero() {
		return tablero;
	}

	// Convierte el tablero en un string
	public String toString() {
		String result = "";
		// Clase para crear Tablero
		for (int i = 0; i < tablero.length; i++) { // For para que recorra todo el eje x
			for (int j = 0; j < tablero[i].length; j++) { // For para que recorra todo el eje y
				result += tablero[i][j] + " | ";
			}
			result += "\n";
		}
		return result;
	}

	// calcula el total de organismos vivos
	public int getVivos() {
		int result = 0;
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero[0].length; j++)
				if (tablero[i][j] == 1)
					result++;
		return result;
	}

	// Calcula el total de organismos muertos
	public int getMuertos() {
		int result = 0;
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero[0].length; j++)
				if (tablero[i][j] == 0)
					result++;
		return result;
	}

	// Compara la tabla actual con la tabla que se ingrese.
	public boolean equals(Table compare) {
		boolean result = true;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (tablero[i][j] != compare.getTablero()[i][j]) {
					result = false;
					break;
				}
			}
			if (!result)
				break;
		}
		return result;
	}
}