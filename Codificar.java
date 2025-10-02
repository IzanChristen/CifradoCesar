import java.io.*;
import java.util.Scanner;

public class Codificar {
    public static void main(String[] args) {

        //Declaro las variables de los archivos
        String archivoEntrada = "msg.txt";
        String archivoCifrado = "msgC.txt";
        String archivoDescifrado = "msgD.txt";

        //Explicacion
        System.out.println("Se generarás 2 archivos: msgC (Mensaje Cifrado), msgD (Mensaje Descifrado)\nEl mensaje a cifrar ha de estar en un archivo llamado msg.txt.");

        //Seleccion desplazamientos cesar
        Scanner sc = new Scanner(System.in);
        System.out.print("Numero desplazamientos cesar: ");
        int desp = sc.nextInt();


        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCifrado))){

            //Invertir texto
            String texto;
            while((texto = br.readLine()) != null){
                System.out.println("Texto entrada: "+texto);
                String invertido = "";
                for (char c : texto.toCharArray()){
                    invertido = c + invertido;
                }
                System.out.println("Texto invertido: "+invertido);

                //Cifrado cesar
                StringBuilder cesar = new StringBuilder();
                for (char c : invertido.toCharArray()){
                    char nuevo = (char)(c + desp);
                    cesar.append(nuevo);
                }
                System.out.println("Texto cifrado: " + cesar);
                //Escribir archivo cifrado
                bw.write(cesar.toString());
                bw.newLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Archivo de entrada no encontrado");
        }
        catch (IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        //Llamada para descifrar
        desxifrar(archivoCifrado, archivoDescifrado, desp);
    }

    public static void desxifrar(String archivoCifrado, String archivoDescifrado, int desp){
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCifrado));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDescifrado))){

            //Descifrar cifrado cesar
            String texto;
            while((texto = br.readLine()) != null){
                StringBuilder descifrado = new StringBuilder();
                for (char c : texto.toCharArray()){
                    char original = (char)(c - desp);
                    descifrado.append(original);
                }
                //Volver a invitar el texto
                String invertido = "";
                for (char c : descifrado.toString().toCharArray()){
                    invertido = c + invertido;
                }
                System.out.println("Texto descifrado: "+invertido);
                //Escribir archivo descifrado
                bw.write(invertido);
                bw.newLine();
            }
        }
        //Control de errores
        catch (FileNotFoundException e) {
            System.out.println("Archivo cifrado no encontrado");
        }
        catch (IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
