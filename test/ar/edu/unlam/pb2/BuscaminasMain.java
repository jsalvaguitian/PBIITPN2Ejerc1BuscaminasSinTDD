package ar.edu.unlam.pb2;

import java.util.InputMismatchException;
import java.util.Scanner;


public class BuscaminasMain {
	private static boolean fueExitoso = true; 

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Nivel nivel;
		int opcion;
		double casillero;
		boolean inicio = true;
		boolean isWinner = false;
		char continuar = 's';
		int fila=0, columna=0;
		boolean estaGanando = true;
		
		
		do {
			nivel = seleccionarNivel(teclado);
			
			Buscaminas buscaminas = new Buscaminas(nivel);
			//------------------------------------------------
			
			
			System.out.println(buscaminas.mostrarTableroFront());
			
			do {
				
				do {
					try {
						System.out.print("Seleccione un casillero, por ej: [fila=0 | columna=0] es 0,0: ");
						casillero = teclado.nextDouble();
						
						String casilleroTexto = String.valueOf(casillero);
						
						fila = Integer.parseInt(casilleroTexto.substring(0, casilleroTexto.indexOf('.') ));
						columna = Integer.parseInt(casilleroTexto.substring((casilleroTexto.indexOf('.')+1)));
						
						if(!(buscaminas.validarFilaColumna(fila, columna))) {
							System.out.println("Ingresaste un valor incorrecto.Intente de nuevo");
							fueExitoso = false;
							
						}else {
							fueExitoso = true;
						}	
						
					}catch(InputMismatchException exception) {
						System.err.println("Error. Por favor ingrese un número decimal segun fila y columna corresponda. ");
						fueExitoso = false;
						teclado.next();
					}
					
				}while(!fueExitoso);
				
				
				
				
				if(inicio == true) {
					buscaminas.generarLosCasillerosConMinas(fila, columna);
					buscaminas.guardarLaCantidadDeBombasQueHayAlrededor(fila, columna);
					inicio = false;
				}else {
					if(!buscaminas.consultarHayMinaEn(fila,columna)) {
						buscaminas.guardarLaCantidadDeBombasQueHayAlrededor(fila, columna);
						isWinner = buscaminas.consultarElEstadoDelJugador();

					}else {
						estaGanando = false;
					}
				}
				
				
				System.out.println(buscaminas.mostrarTableroFront(estaGanando,fila, columna));
				System.out.println(buscaminas.mostrarTableroBack());
				
				
			}while(!buscaminas.consultarHayMinaEn(fila,columna) && !isWinner);
			
			if(buscaminas.consultarHayMinaEn(fila,columna))
				System.out.println("Perdiste, la proxima será ¯\\_(ツ)_/¯");
			if(isWinner)
				System.out.println("Maravilloso! Ganaste genio/a ＼(^o^)／!!!");
			
			//-----------FIN? -------------------------
			inicio = true;
			estaGanando = true;
			isWinner = false;
			System.out.println("¿Desea continuar? (s/n)");
			continuar = teclado.next().toLowerCase().charAt(0);
			
		}while(continuar == 's');
		
		

	}

	private static Nivel seleccionarNivel(Scanner teclado) {
		Integer opcion = 0;
		Nivel[]niveles = Nivel.values();
		
		do {
			mostrarMenuDeNiveles(niveles);
			try {
				opcion = teclado.nextInt();
				
				if(opcion <= 0 || opcion > niveles.length) {
					System.out.println("Opción incorrecta. Intente de nuevo.");
					fueExitoso = false;
				}else
					fueExitoso = true;
				
			}catch(InputMismatchException exception) {
				System.err.println("Ingresaste un caracter inválido, solo números por favor desde 1 hasta 3.");
				fueExitoso = false;
				teclado.next();
			}
			
			
			
		}while(!fueExitoso);
		
		return niveles[opcion-1];
	}

	private static void mostrarMenuDeNiveles(Nivel[] niveles) {
		System.out.println("\nNIVELES: ");
		System.out.println("*************************");
		for(int i=0; i<niveles.length; i++) {
			System.out.println("["+(i+1)+"] "+ niveles[i]);	
		}
		System.out.print("\nSeleccione su nivel de juego [1-3]: ");		
	}

}
