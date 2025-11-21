import java.io.*;
import java.util.*;

public class BusquedaBinariaSimple {
    
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            // Directorio donde buscar archivos
            File directorio = new File("C:/Archivos/");
            
            if (!directorio.exists() || !directorio.isDirectory()) {
                System.out.println("El directorio C:/Archivos/ no existe.");
                return;
            }
            
            // Listar archivos disponibles
            File[] archivos = directorio.listFiles();
            if (archivos == null || archivos.length == 0) {
                System.out.println("No hay archivos en C:/Archivos/");
                return;
            }
            
            System.out.println("ARCHIVOS DISPONIBLES en C:/Archivos/:");
            for (int i = 0; i < archivos.length; i++) {
                if (archivos[i].isFile()) {
                    System.out.println((i + 1) + ". " + archivos[i].getName());
                }
            }
            
            // Seleccionar archivo
            System.out.print("\nSelecciona el numero del archivo ordenado: ");
            String inputSeleccion = reader.readLine();
            int seleccion = Integer.parseInt(inputSeleccion);
            
            if (seleccion < 1 || seleccion > archivos.length) {
                System.out.println("Seleccion invalida.");
                return;
            }
            
            File archivoEntrada = archivos[seleccion - 1];
            System.out.println("Archivo seleccionado: " + archivoEntrada.getName());
            
            // Cargar numeros ordenados
            int[] array = cargarArchivoOrdenado(archivoEntrada.getAbsolutePath());
            if (array == null) {
                return;
            }
            
            System.out.println("Numeros cargados: " + array.length);
            System.out.println("Rango: [" + array[0] + " - " + array[array.length-1] + "]");
            
            // Buscar numero
            System.out.print("\nIngresa el numero a buscar: ");
            String inputNumero = reader.readLine();
            int objetivo = Integer.parseInt(inputNumero);
            
            // Ejecutar busqueda binaria
            ejecutarBusquedaBinaria(array, objetivo);
            
        } catch (NumberFormatException e) {
            System.err.println("Error: Debes ingresar un numero valido.");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar BufferedReader: " + e.getMessage());
            }
        }
    }
    
    // Cargar archivo y verificar que este ordenado
    private static int[] cargarArchivoOrdenado(String rutaArchivo) throws IOException {
        List<Integer> numeros = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        int anterior = Integer.MIN_VALUE;
        boolean ordenado = true;
        
        while ((linea = fileReader.readLine()) != null) {
            linea = linea.trim();
            if (!linea.isEmpty()) {
                try {
                    int numero = Integer.parseInt(linea);
                    
                    // Verificar orden
                    if (numero < anterior) {
                        ordenado = false;
                    }
                    anterior = numero;
                    
                    numeros.add(numero);
                } catch (NumberFormatException e) {
                    System.out.println("Numero invalido ignorado: " + linea);
                }
            }
        }
        fileReader.close();
        
        if (!ordenado) {
            System.out.println("Error: El archivo no esta ordenado. La busqueda binaria requiere datos ordenados.");
            return null;
        }
        
        return numeros.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // Ejecutar busqueda binaria con visualizacion
    private static void ejecutarBusquedaBinaria(int[] array, int objetivo) {
        System.out.println("\n=== BUSQUEDA BINARIA ===");
        System.out.println("Buscando: " + objetivo);
        System.out.println("En array de " + array.length + " elementos");
        
        int izquierda = 0;
        int derecha = array.length - 1;
        int iteraciones = 0;
        boolean encontrado = false;
        int posicion = -1;
        
        System.out.println("\nProceso de busqueda:");
        
        while (izquierda <= derecha) {
            iteraciones++;
            int medio = izquierda + (derecha - izquierda) / 2;
            
            System.out.println("Iteracion " + iteraciones + ":");
            System.out.println("  Rango [" + izquierda + "-" + derecha + "]");
            System.out.println("  Medio: " + medio + " -> Valor: " + array[medio]);
            
            if (array[medio] == objetivo) {
                encontrado = true;
                posicion = medio;
                System.out.println("  -> ENCONTRADO!");
                break;
            }
            
            if (array[medio] < objetivo) {
                System.out.println("  -> Buscar en mitad derecha");
                izquierda = medio + 1;
            } else {
                System.out.println("  -> Buscar en mitad izquierda");
                derecha = medio - 1;
            }
        }
        
        // Mostrar resultado final
        System.out.println("\n=== RESULTADO ===");
        if (encontrado) {
            System.out.println("Numero " + objetivo + " ENCONTRADO");
            System.out.println("Posicion: " + posicion);
            System.out.println("Valor verificado: " + array[posicion]);
        } else {
            System.out.println("Numero " + objetivo + " NO ENCONTRADO");
        }
        System.out.println("Iteraciones realizadas: " + iteraciones);
        System.out.println("Maximo teorico: " + (int)(Math.log(array.length) / Math.log(2)) + " iteraciones");
    }
}