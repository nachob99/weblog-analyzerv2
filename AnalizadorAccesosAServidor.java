import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;
    
    
    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
    }
    
    
    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear();
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) {
                String lineaLeida = sc.nextLine();               
                String[] elementosLinea = lineaLeida.split(" ");
                Acceso accesoActual = new Acceso(lineaLeida); 
                                                               
                
                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }
    
    
    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;
        
        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];
    
            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }
            
            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }
            
            valorADevolver = horaDeAccesosMasAlto;                      
        }
        
        return valorADevolver;
    }

    
    
    /**
     * Metodo para obtener la pagina web mas visitada
     */
    public String paginaWebMasSolicitada() 
    {
        ArrayList<Acceso> acceso = new ArrayList<>(); 
        acceso.addAll(accesos);
        String paginaADevolver = null; 
        int contadorMasCoincidencias = 0; //Contador donde almacenaremos el mayor numero de coincidencias
        if(accesos.size() > 0){  
            for(int i= 0; i < acceso.size() ; i++){
                int contador = 0; //Contador donde almacenaremos las coincidencias de cada web.
                for(int j = i + 1; j < acceso.size() ; j++){ //Bucle para comprobar las coincidencias
                    if(acceso.get(j).getPaginaWeb().equals(acceso.get(i).getPaginaWeb())){
                        contador ++;
                        acceso.remove(j);
                        j--;
                    }
                }
                if(contador >= contadorMasCoincidencias){ 
                    contadorMasCoincidencias = contador;
                    paginaADevolver = acceso.get(i).getPaginaWeb();
                }
                
            }
        }
        return paginaADevolver;
    }
    
    public String clienteConMasAccesosExitosos()
    {
        return "";
    }


}
