package ar.edu.unlam.pb2;

public enum Nivel {
	FACIL(8,8,10,20),
	MEDIO(16,16,40,77),
	DIFICIL(16,30,99,144);
	
	private final Integer FILAS, COLUMNAS, MINAS, CANT_MIN_CASILLEROS_LIBERADOS;

	private Nivel(Integer filas, Integer columnas, Integer minas, Integer casillerosLiberados) {
		this.FILAS = filas;
		this.COLUMNAS = columnas;
		this.MINAS = minas;
		this.CANT_MIN_CASILLEROS_LIBERADOS = casillerosLiberados;
	}

	public Integer getFilas() {
		return FILAS;
	}

	public Integer getColumnas() {
		return COLUMNAS;
	}

	public Integer getMinas() {
		return MINAS;
	}

	public Integer getCantidadMinimaDeCasillerosLiberados() {
		return CANT_MIN_CASILLEROS_LIBERADOS;
	}
	
	
	
	

}
