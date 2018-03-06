import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

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
     /**
     * Devuelve la hora con mas accesos. Si hay empate devuelve la mas alta.
     * @return Devuelve un int que equivale a la hora con mas accesos.
     * Si hay empate, devuelve la hora mas alta, o si no hay datos devuelve -1.
     */    
    
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
      /**
     * Devuelve la direccion IP del cliente que ha realizado el mayor numero de 
     * accesos exitosos al servidor. Si no hay datos de acceso al servidor, 
     * muestra por pantalla mensaje
     * @return Devuelve un String que contiene la direccion IP del cliente que
     * mas ha accedido al servidor. Si no hay datos de acceso, devuelve null. 
     * En caso de empate se muestra el cliente con la IP mas alta.
     */    
    public String clienteConMasAccesosExitosos()
    {
         String direccionIp = null;
        ArrayList<Acceso> accesosIP = new ArrayList<Acceso>();
        HashMap<String, Integer> listaDeIps = new HashMap<>();
        int cantidadDeVecesQueMasSeRepiteLaIp = 0;
        int ipAComparar = 0;
        for(Acceso conexionesConExitos : accesos){
            
            if(conexionesConExitos.getCodigo() == 200){
                accesosIP.add(conexionesConExitos); 
            }
        }
        if (accesos.size() > 0){
            for(Acceso conexionesConExitos : accesosIP){
                String ipActual = conexionesConExitos.getDireccionIP();

                if(listaDeIps.get(ipActual) == null){
                    listaDeIps.put(ipActual, 1);
                }
                else{
                    listaDeIps.replace(ipActual, listaDeIps.get(ipActual) + 1);
                }
                
                int ipNueva = Integer.parseInt(ipActual.split("\\.")[3]);
                
                int conexionesExitosas = listaDeIps.get(ipActual);
                if(conexionesExitosas > cantidadDeVecesQueMasSeRepiteLaIp || (conexionesExitosas == cantidadDeVecesQueMasSeRepiteLaIp && ipNueva > ipAComparar)){
                    cantidadDeVecesQueMasSeRepiteLaIp = conexionesExitosas;
                    ipAComparar = ipNueva;
                    direccionIp = ipActual;
                }
            }
        }
        else{
            System.out.println("Error al leer el archivo.");
        }

        return direccionIp;
    }


}
