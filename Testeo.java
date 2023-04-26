import java.util.Date;

public class Testeo {
    private long tiempo;
    /**
     * Constructor de la clase de testeo
     * La clase se encarga de Hacer pruebas unitaria a cada proceso y ver si esta bien seteado los tiempos.
     */
    public Testeo(long tiempo) {
        this.tiempo = tiempo;
        System.out.println("---Inicio del programa---");
    }
    public void test_Cargar(){
        Date d1 = new Date();
        Cargar c = new Cargar(tiempo);
        Thread hiloA = new Thread(c);
        Thread hiloB = new Thread(c);
        hiloA.start();
        hiloB.start();
        try{
            hiloA.join();
            hiloB.join();
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        Date d2 = new Date();
        int t = 0;
        if(d2.getSeconds() > d1.getSeconds()){
            t = (d2.getMinutes()-d1.getMinutes()) + (d2.getSeconds()-d1.getSeconds());
        }else{
            t = (d2.getMinutes() - d1.getMinutes()) + ((d2.getSeconds()+60) - d1.getSeconds());
        }
        System.out.printf("Tiempo de test_carga: %d" , t-1);
    }
}
