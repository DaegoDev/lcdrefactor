import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LCDTester {

  public static final String CADENA_FINAL = "0,0";

  public static void main(String[] args) {
    String numeroLCD; // Variable que guardará un numero en formato LCD.

    // Establece los segmentos de cada numero
    List<String> listaComando = new ArrayList<>();
    String comando;
    int espacioDig = 0;

    try {

      try (Scanner lector = new Scanner(System.in)) {

        System.out.print("Espacio entre Digitos (0 a 5): ");
        comando = lector.next();

        // Valida si es un número
        if(ImpresorLCD.esNumero(comando)) {
          espacioDig = Integer.parseInt(comando);

          // se valida que el espaciado este entre 0 y 5
          if(espacioDig < 0 || espacioDig > 5) {
            throw new IllegalArgumentException("El espacio entre "
            + "digitos debe estar entre 0 y 5");
          }
        }
        else {
          throw new IllegalArgumentException("Cadena " + comando
          + " no es un entero");
        }

        // Se recibe una cantidad indefinida de numeros hasta que se ingrese
        // la cadena final que inicia el proceso de transformar los números
        // a formato LCD.
        do {
          System.out.print("Entrada: ");
          comando = lector.next();
          if(!comando.equalsIgnoreCase(CADENA_FINAL)) {
            listaComando.add(comando);
          }
        }
        while (!comando.equalsIgnoreCase(CADENA_FINAL));
      }
      catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
      }

      // Se crea la instancia del ImpresorLCD
      ImpresorLCD impresorLCD = new ImpresorLCD();

      // Se procesa cada número ingresado y se imprime en pantalla.
      Iterator<String> iterator = listaComando.iterator();
      while (iterator.hasNext()) {
        try {
          numeroLCD = impresorLCD.procesar(iterator.next(), espacioDig);
          System.out.println(numeroLCD);
        }
        catch (Exception ex) {
          System.out.println("Error: " + ex.getMessage());
        }
      }
    }
    catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
  }
}
