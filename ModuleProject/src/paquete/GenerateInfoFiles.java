package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateInfoFiles {
    
    // Método para generar ventas reales para cada vendedor
    public static void generateRealSales(String vendedoresFile, String productosFile) {
        try {
            // Leer la información de productos
            List<String> productos = new ArrayList<>();
            FileReader productosReader = new FileReader(productosFile);
            BufferedReader productosBufferedReader = new BufferedReader(productosReader);
            String lineaProducto;
            while ((lineaProducto = productosBufferedReader.readLine()) != null) {
                productos.add(lineaProducto);
            }
            productosBufferedReader.close();

            // Leer la información de vendedores
            FileReader vendedoresReader = new FileReader(vendedoresFile);
            BufferedReader vendedoresBufferedReader = new BufferedReader(vendedoresReader);
            String lineaVendedor;
            while ((lineaVendedor = vendedoresBufferedReader.readLine()) != null) {
                // Obtener información del vendedor
                String[] partesVendedor = lineaVendedor.split(";");
                String TipoDocumentoVendedor = partesVendedor[0];
                long NúmeroDocumentoVendedor = Long.parseLong(partesVendedor[1]);
                //String nombres = partesVendedor[2];
                //String apellidos = partesVendedor[3];

                // Seleccionar 6 productos aleatoriamente
                List<String> productosVendedor = selectRandomProducts(productos);

                // Generar archivo de ventas para el vendedor con los productos seleccionados
                createSalesFile(TipoDocumentoVendedor, NúmeroDocumentoVendedor, productosVendedor);
            }
            vendedoresBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 // Método para seleccionar aleatoriamente 6 productos con cantidad y calcular el valor total de la venta
    private static List<String> selectRandomProducts(List<String> productos) {
        Random random = new Random();
        List<String> productosSeleccionados = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(productos.size());
            String producto = productos.get(index);
            // Obtener nombre y precio del producto
            String[] partesProducto = producto.split(";");
            String IDProducto = partesProducto[0];
            double precioPorUnidadProducto = Double.parseDouble(partesProducto[2]);
            // Generar cantidad aleatoria entre 1 y 10
            int cantidad = random.nextInt(10) + 1;
            // Calcular el valor total de la venta por este producto
            double valorTotal = cantidad * precioPorUnidadProducto;
            // Agregar el producto con cantidad y valor total a la lista
            productosSeleccionados.add(IDProducto + ";" + cantidad + ";" + valorTotal);
        }
        return productosSeleccionados;
    }

    // Método para generar el archivo de ventas para un vendedor
    private static void createSalesFile(String name, long id, List<String> productos) {
        try {
            FileWriter writer = new FileWriter(name + "_" + id + "_Ventas.txt");
            writer.write("TipoDocumentoVendedor;" + name + "\n");
            writer.write("NúmeroDocumentoVendedor;" + id + "\n");
            for (String producto : productos) {
                writer.write(producto + "\n");
            }
            writer.close();
            System.out.println("Archivo de ventas para " + name + " creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Nombre de los archivos de vendedores y productos
        String vendedoresFile = "vendedores.txt";
        String productosFile = "productos.txt";

        // Generar ventas para cada vendedor
        generateRealSales(vendedoresFile, productosFile);
    }
}
