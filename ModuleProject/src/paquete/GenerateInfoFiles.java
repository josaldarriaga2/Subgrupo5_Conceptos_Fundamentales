package paquete;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    // Método principal para generar los archivos planos pseudoaleatorios
    public static void main(String[] args) {
        // Llamar a los métodos para generar los archivos de vendedores y productos
        boolean vendedoresGenerated = generateVendedoresFile();
        boolean productosGenerated = generateProductosFile();
        
        // Mostrar mensaje de finalización exitosa o mensaje de error
        if (vendedoresGenerated && productosGenerated) {
            System.out.println("Archivos planos pseudoaleatorios generados exitosamente.");
        } else {
            System.out.println("Error al generar los archivos planos pseudoaleatorios.");
        }
    }

    // Método para generar el archivo de vendedores
    private static boolean generateVendedoresFile() {
        try {
            FileWriter writer = new FileWriter("vendedores.txt");
            // Generar información de vendedores
            for (int i = 1; i <= 10; i++) {
                String tipoDocumento = "CC";
                long numeroDocumento = 1000000000 + i;
                String nombres = "Vendedor" + i;
                String apellidos = "Apellido" + i;
                // Escribir en el archivo
                writer.write(tipoDocumento + ";" + numeroDocumento + ";" + nombres + ";" + apellidos + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para generar el archivo de productos
    private static boolean generateProductosFile() {
        try {
            FileWriter writer = new FileWriter("productos.txt");
            Random random = new Random();
            // Generar información de productos
            for (int i = 1; i <= 20; i++) {
                String nombreProducto = "Producto" + i;
                double precioPorUnidadProducto = 10 + random.nextInt(91); // Precio entre 10 y 100
                // Escribir en el archivo
                writer.write(i + ";" + nombreProducto + ";" + precioPorUnidadProducto + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
