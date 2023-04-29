/**
 * CLase testeo se encarga de hacer pruebas unitarias a cada proceso del sistema.
 * para verificar que los procesos se efectuan correctamente. y en el tiempo establecido.
 */
public class Testeo {
    private long tiempo;
    /**
     * Constructor de la clase de testeo
     * La clase se encarga de Hacer pruebas unitaria a cada proceso y ver si esta bien seteado los tiempos.
     */
    public Testeo(long tiempo) {
        this.tiempo = tiempo;
        System.out.println("------------------------------------");
        System.out.println("---Inicio del Testeo del programa---");
    }

    /**
     * Testeo del proceso 1, de cargar imagenes a un contenedor incial.
     */
    public void test_Cargar(){
        boolean paso = true;
        long startTime = System.currentTimeMillis();
        System.out.printf("Test_carga: \n");

        ContenedorInicial ci = new ContenedorInicial();
        Cargar c = new Cargar(tiempo,ci);
        Thread hiloA = new Thread(c);
        Thread hiloB = new Thread(c);
        hiloA.start();
        hiloB.start();
        try{
            hiloA.join();
            hiloB.join();
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        long endTime = System.currentTimeMillis();
        long t = endTime - startTime; //tiempo en miliSegundos.

        if( (t >= tiempo) && ( t <= (tiempo+1000)) ){ System.out.printf("|->Tiempo: (%dms) - True\n",t);}else{ System.out.printf("|-> tiempo: (%dms) - False \n",t); paso = false; }     //tiempo del proceso.1 min de mas de
        if(c.getImagenesCargadas() == 100){ System.out.printf("|->Imagenes Cargadas: True \n"); }else{ System.out.printf("|->ImagenesCargadas: False \n"); paso = false; }  //Imagenes cargadas entre los hilos.
        if(ci.getContenedorInicial().size() == 100 ){ System.out.printf("|->Cantidad correcta en el contenedor : True \n");}else{System.out.printf("|->Cantidad correcta en el contenedor : False \n"); paso = false;}  //si existen 100 imagenes cargadas en el contendor
        if( imagenes_sin_repetir(ci) == 100 ){ System.out.printf("|->No se repiten los nombres: true \n"); }else{System.out.printf("|->No se repiten los nombres: False \n");paso = false; } //si los nombre no se repiten
        if(paso){ System.out.printf("--> TRUE <-- Paso el Test de la carga \n"); }else{ System.out.printf("--> FALSE <-- NO paso el Test de la carga\n");paso = false;}
        System.out.println("------------------------------------");
    }

    /**
     * Testeo del proceso 2, Mejorar iluminacion a imagenes de un contenedor incial.
     */
    public void test_Mejorar(){

    }

    /**
     * Testeo del proceso 3, Ajustar tamaño de imagenes de un contenedor incial.
     */
    public void test_Ajustar(){

    }

    /**
     * Testeo del proceso 4, CargarCopia de las imagenes desde el contenedor inicial, al contenedor final.
     */
    public void test_CargarCopia(){
        System.out.println("------------------------------------");
    }
    //-------------------------------------------------------------------------------------------------------------------------
    //metodos comunes de uso.
    /**
     *
     * @param ci
     * @return numero de imagenes que estan una sola vez.
     */
    public int imagenes_sin_repetir(ContenedorInicial ci){
        int imagenSinRepetir = 0;
        for(Imagen aux1 : ci.getContenedorInicial()){
            for(Imagen aux2 : ci.getContenedorInicial()){
                if( aux1.getNombre().equals(aux2.getNombre())){  //true: son iguales los nombres
                    imagenSinRepetir ++;
                }
            }
        }
        return imagenSinRepetir;
    }
}
