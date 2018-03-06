import java.util.Arrays;

public class Acceso
{
    private String[] arrayDatos;
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    private String ip;
    private String web;
    private int codigo;

    public Acceso(String dato)
    {
        String[] arrayDatos = dato.split(" ");
        ano = Integer.parseInt(arrayDatos[1].substring(1,5));
        mes = Integer.parseInt(arrayDatos[2]);
        dia = Integer.parseInt(arrayDatos[3]);
        hora = Integer.parseInt(arrayDatos[4]);
        minutos = Integer.parseInt(arrayDatos[5].substring(0,2));
        web = arrayDatos[6];
        ip = arrayDatos[0];
        codigo = Integer.parseInt(arrayDatos[7]);
    }
     /**
     * Devuelve el año en la que el cliente ha accedido al servidor.
     * @return Devuelve un int que es el año .
     */    
    public int getAno() 
    {
        return ano;
    }
     /**
     * Devuelve el mes en la que el cliente ha accedido al servidor.
     * @return Devuelve un int que es el mes.
     */    
    public int getMes()
    {
        return mes;
    }
     /**
     * Devuelve el dia en la que el cliente ha accedido al servidor.
     * @return Devuelve un int que es el dia.
     */    
    public int getDia()
    {
        return dia;
    }

    /**
     * Devuelve la hora en la que el cliente ha accedido al servidor.
     * @return Devuelve un int que es la hora.
     */    
    public int getHora()
    {
        return hora;
    }

    /**
     * Devuelve el minuto en el que el cliente ha accedido al servidor.
     * @return Devuelve un int que es el minuto.
     */    
    public int getMinutos()
    {
        return minutos;
    }

    /**
     * Devuelve la pagina web a la que ha accedido el usuario.
     * @return Devuelve un String que es la pagina web.
     */
    public String getPaginaWeb() {
        return web;
    }

    /**
     * Devuelve la direccion IP con la que ha accedido el cliente.
     * @return Devuelve un String que es la direccion IP.
     */
    public String getDireccionIP() {
        return ip;
    }

    /**
     * Devuelve el codigo HTTP con el que responde el servidor.
     * @return Devuelve un String que es el codigo.
     */
    public int getCodigo() {
        return codigo;
    }    
    
   
}