import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

  // Constantes de control para la creación de cada dígito.
  private static final String CARACTER_VERTICAL = "|";
  private static final String CARACTER_HORIZONTAL = "-";
  private static final String POSICION_X = "X";
  private static final String POSICION_Y = "Y";

  // Puntos fijos para la construcción de un dígito en formato LCD.
  // Ej. para un tamaño de 3, los puntos están distribuidos de la
  // manera presente en el cuadro inferior.
  // ------------------
  // | p1 ** ** ** p4 |
  // | ** ** ** ** ** |
  // | ** ** ** ** ** |
  // | ** ** ** ** ** |
  // | p2 ** ** ** p5 |
  // | ** ** ** ** ** |
  // | ** ** ** ** ** |
  // | ** ** ** ** ** |
  // | p3 ** ** ** ** |
  // ------------------
  private final int[] pf1;
  private final int[] pf2;
  private final int[] pf3;
  private final int[] pf4;
  private final int[] pf5;

  private int tamano; // Tamaño que tendrá el número en formato LCD.
  private int espacio; // Cantidad de espacio entre cada dígito del número.
  private int filasDig; // Cantidad de filas de cada dígito en la matriz de impresión.
  private int columDig; // Cantidad de columnas de cada dígito en la matriz de impresión.
  private int totalFilas; // Total de filas que tendrá la matriz de impresión.
  private int totalColum; // Total de columnas que tendrá la matriz de impresión.

  // Matriz de almacenamiento que contiene el número a imprimir en formato LCD.
  private String[][] matrizImpr;

  public ImpresorLCD() {
    // Inicialición de variables.
    this.pf1 = new int[2];
    this.pf2 = new int[2];
    this.pf3 = new int[2];
    this.pf4 = new int[2];
    this.pf5 = new int[2];
  }

  /**
  *
  * Método encargado de añadir una linea a la matriz de almacenamiento
  *
  * @param punto Punto pivote a partir del cual se añadiran caracteres.
  * @param posFija Posicion hacia la cual se añadiran los caracteres.
  */
  private void adicionarLinea(int[] punto, String posFija) {
    int valor = 0;

    if (posFija.equalsIgnoreCase(POSICION_X)) {
      for (int i = 1; i <= tamano; i++) {
        valor = punto[1] + i;
        matrizImpr[punto[0]][valor] = CARACTER_HORIZONTAL;
      }
    }
    else {
      for (int i = 1; i <= tamano; i++) {
        valor = punto[0] + i;
        matrizImpr[valor][punto[1]] = CARACTER_VERTICAL;
      }
    }
  }

  /**
  *
  * Método encargado de añadir un segmento a la matriz de almacenamiento
  *
  * @param segmento Segmento a adicionar a la matriz
  */
  private void adicionarSegmento(int segmento) {

    switch (segmento) {
      case 1:
      adicionarLinea(pf1, POSICION_Y);
      break;
      case 2:
      adicionarLinea(pf2, POSICION_Y);
      break;
      case 3:
      adicionarLinea(pf5, POSICION_Y);
      break;
      case 4:
      adicionarLinea(pf4, POSICION_Y);
      break;
      case 5:
      adicionarLinea(pf1, POSICION_X);
      break;
      case 6:
      adicionarLinea(pf2, POSICION_X);
      break;
      case 7:
      adicionarLinea(pf3, POSICION_X);
      break;
      default:
      break;
    }
  }

  /**
  *
  * Método encargado de definir los segmentos que componen un dígito y
  * a partir de los segmentos adicionar la representacion del dígito a la matriz.
  *
  * @param digito Dígito que se va a adicionar a la matriz de almacenamiento.
  */
  private void adicionarDigito(int digito) {

    // Establece el patron de segmentos de cada número para su construcción.
    List<Integer> segList = new ArrayList<>();

    switch (digito) {
      case 1:
      segList.add(3);
      segList.add(4);
      break;
      case 2:
      segList.add(5);
      segList.add(3);
      segList.add(6);
      segList.add(2);
      segList.add(7);
      break;
      case 3:
      segList.add(5);
      segList.add(3);
      segList.add(6);
      segList.add(4);
      segList.add(7);
      break;
      case 4:
      segList.add(1);
      segList.add(6);
      segList.add(3);
      segList.add(4);
      break;
      case 5:
      segList.add(5);
      segList.add(1);
      segList.add(6);
      segList.add(4);
      segList.add(7);
      break;
      case 6:
      segList.add(5);
      segList.add(1);
      segList.add(6);
      segList.add(2);
      segList.add(7);
      segList.add(4);
      break;
      case 7:
      segList.add(5);
      segList.add(3);
      segList.add(4);
      break;
      case 8:
      segList.add(1);
      segList.add(2);
      segList.add(3);
      segList.add(4);
      segList.add(5);
      segList.add(6);
      segList.add(7);
      break;
      case 9:
      segList.add(1);
      segList.add(3);
      segList.add(4);
      segList.add(5);
      segList.add(6);
      segList.add(7);
      break;
      case 0:
      segList.add(1);
      segList.add(2);
      segList.add(3);
      segList.add(4);
      segList.add(5);
      segList.add(7);
      break;
      default:
      break;
    }

    // Se crea el número basado en el patron de segmentos.
    Iterator<Integer> iterator = segList.iterator();
    while (iterator.hasNext()) {
      adicionarSegmento(iterator.next());
    }
  }

  /**
  *
  * Método encargado de imprimir un número
  *
  * @param numeroImp Número a imprimir
  */
  private String imprimirNumero(String numeroImp) {
    String numeroLCD = ""; // Contiene la cadena del numero en formato LCD.
    int numero = 0; // variable de control para añadir cada dígito.
    int pivotX = 0; // Variable de control para desplazar horizontalmente los puntos fijos.
    char[] digitos; // Arreglo en el cual se separará el numero en digitos.

    filasDig = (2 * tamano) + 3; // Calcula la cantidad de filas cada dígito.
    columDig = tamano + 2; // Calcula la cantidad de columnas de cada dígito.

    // Calcula el total de filas de la matriz en la que se almacenaran los dígitos
    totalFilas = filasDig;

    // Calcula el total de columnas de la matriz en la que se almacenaran los dígitos
    totalColum = (columDig * numeroImp.length())
    + (espacio * numeroImp.length());

    // Crea la matriz para almacenar los números a imprimir
    matrizImpr = new String[totalFilas][totalColum];

    // Separa cada dígito en un arreglo.
    digitos = numeroImp.toCharArray();

    // Inicializa matriz
    for (int i = 0; i < totalFilas; i++) {
      for (int j = 0; j < totalColum; j++) {
        matrizImpr[i][j] = " ";
      }
    }

    for (char digito : digitos) {

      //Valida que el caracter sea un dígito
      if( ! Character.isDigit(digito))
      {
        throw new IllegalArgumentException("Caracter " + digito
        + " no es un digito");
      }

      numero = Integer.parseInt(String.valueOf(digito));

      //Calcula la posición de los puntos fijos para cada dígito.
      pf1[0] = 0;
      pf1[1] = 0 + pivotX;

      pf2[0] = (filasDig / 2);
      pf2[1] = 0 + pivotX;

      pf3[0] = (filasDig - 1);
      pf3[1] = 0 + pivotX;

      pf4[0] = (columDig - 1);
      pf4[1] = (filasDig / 2) + pivotX;

      pf5[0] = 0;
      pf5[1] = (columDig - 1) + pivotX;

      pivotX = pivotX + columDig + espacio;

      adicionarDigito(numero);
    }

    // Se genera la codificación LCD del número en una variable y se imprime.
    for (int i = 0; i < totalFilas; i++) {
      for (int j = 0; j < totalColum; j++) {
        numeroLCD += matrizImpr[i][j]; // Se escribe cada caracter de linea.
      }
      numeroLCD += "\n"; // Se agrega un salto de linea.
    }
    return numeroLCD;
  }

  /**
  *
  * Método encargado de procesar la entrada que contiene el tamaño del segmento
  * de los dígitos y los dígitos a imprimir
  *
  * @param comando Entrada que contiene el tamaño del segmento de los dígitos
  * y el número a imprimir
  * @param espacioDig Espacio entre dígitos
  */
  public String procesar(String comando, int espacioDig) {

    String[] parametros;
    int tempTamano;

    if (!comando.contains(",")) {
      throw new IllegalArgumentException("Cadena " + comando
      + " no contiene caracter ,");
    }
    // Se separa la cadena ingresada en un arreglo.
    parametros = comando.split(",");

    if (parametros.length != 2) {
      throw new IllegalArgumentException("La cadena " + comando
      + " no contiene los parametros requeridos");
    }

    //Valida que el parametro tamaño sea un numerico.
    if(esNumero(parametros[0])) {
      tempTamano = Integer.parseInt(parametros[0]);

      // se valida que el tamaño esté entre 1 y 10.
      if(tempTamano < 1 || tempTamano > 10) {
        throw new IllegalArgumentException("El parametro tamaño ["+tempTamano
        + "] debe estar entre 1 y 10.");
      }
    }
    else {
      throw new IllegalArgumentException("El parametro tamaño [" + parametros[0]
      + "] no es un numero.");
    }

    // Realiza la impresión del número.
    tamano = tempTamano;
    espacio = espacioDig;
    return imprimirNumero(parametros[1]);
  }

  /**
  *
  * Metodo encargado de validar si una cadena es numerica
  *
  * @param cadena Cadena que se va a validar
  */
  public static boolean esNumero(String cadena) {
    try {
      Integer.parseInt(cadena);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
}
