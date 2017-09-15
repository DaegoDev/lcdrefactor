import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImpresorLCDTest {
  private ImpresorLCD impresorLCD;

  /**
  *
  * Prueba para validar que el número en formato LCD retornado es correcto.
  *
  */
  @Test
  public void procesarTest() {
    // Variables de control para cada número
    String numeroLCD;
    String comando;
    int espacio;

    // Se crea la instancia del ImpresorLCD con el que se procesarán los numeros.
    impresorLCD = new ImpresorLCD();

    // Se valida un primer número (12345) con una separación de 0 y un tamaño de 2
    numeroLCD =
    "     --  --      -- \n" +
    "   |   |   ||  ||   \n" +
    "   |   |   ||  ||   \n" +
    "     --  --  --  -- \n" +
    "   ||      |   |   |\n" +
    "   ||      |   |   |\n" +
    "     --  --      -- \n";
    espacio = 0;
    comando = "2,12345";
    assertEquals(numeroLCD, impresorLCD.procesar(comando, espacio));

    // Se valida un segundo número (67890) con una separación de 2 y un tamaño de 3
    numeroLCD =
    " ---    ---    ---    ---    ---   \n" +
    "|          |  |   |  |   |  |   |  \n" +
    "|          |  |   |  |   |  |   |  \n" +
    "|          |  |   |  |   |  |   |  \n" +
    " ---           ---    ---          \n" +
    "|   |      |  |   |      |  |   |  \n" +
    "|   |      |  |   |      |  |   |  \n" +
    "|   |      |  |   |      |  |   |  \n" +
    " ---           ---    ---    ---   \n";
    espacio = 2;
    comando = "3,67890";
    assertEquals(numeroLCD, impresorLCD.procesar(comando, espacio));
  }
}
