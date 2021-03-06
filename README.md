# LCD refactor
<pre>
<p>Para efectos practicos se exporta las librerias necesarias para las pruebas y se mantiene la jerarquía de archivos.</p>
<p><b>Compilar:</b> javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar ./*.java</p>
<p><b>Ejecutar:</b> java LCDTester</p>
<p><b>Pruebas:</b>  java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ImpresorLCDTest</p>
</pre>

Objetivo: Crear un programa que imprime números en estilo de una pantalla LCD

Entrada: La entrada contiene varias líneas. Cada línea contiene 2 números separados por coma. El primer número representa el tamaño de la impresión (entre 1 y 10, esta variable se llama “size”). El segundo número es el número a mostrar en la pantalla. Para terminar, se debe digitar 0,0. Esto terminará la aplicación.

Salida: Imprimir los números especificados usando el caracter “-“ para los caracteres horizontales, y “|” para los verticales. Cada dígito debe ocupar exactamente size+2 columnas y 2*size + 3 filas.

Entre cada impresión debe haber una línea blanca.

Ejemplo:
Entrada:
2,12345
3,67890
0,0

Salida:   
 <pre>  
   _ _  _ _        _ _
|     |    | |  | |
|  _ _| _ _| |__| |_ _
| |        |    |     |
| |_ _  _ _|    |  _ _|

 _ _ _  _ _ _   _ _ _   _ _ _   _ _ _
|            | |     | |     | |     |
|            | |     | |     | |     |
|_ _ _       | |_ _ _| |_ _ _| |     |
|     |      | |     |       | |     |
|     |      | |     |       | |     |
|_ _ _|      | |_ _ _|  _ _ _| |_ _ _|
