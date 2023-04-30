import java.util.ArrayList;

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
    public void test_Cargar(int cantidad_de_hilos){
        boolean paso = true;
        long startTime = System.currentTimeMillis();
        int cantidad_hilos = cantidad_de_hilos;  //--> Default: 2 hilos
        System.out.printf("Test_carga: (cantidad de hilos: %d) \n",cantidad_hilos);
        ContenedorInicial ci = new ContenedorInicial();
        Cargar c = new Cargar(tiempo,ci,cantidad_hilos);

        Thread hilos[] = new Thread[cantidad_hilos];
        for(int i=0;i<cantidad_hilos;i++){ //creamos los hilos
            Thread hilo_aux = new Thread(c);
            hilos[i] = hilo_aux;
        }

        for(int i=0;i<cantidad_hilos;i++){ //arrancamos los hilos.
            hilos[i].start();
        }

        try{
            for(int i=0;i<cantidad_hilos;i++){ //paramos el codigo hasta que se termine de ejecutar todos los procesos.
                hilos[i].join();
            }
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        long endTime = System.currentTimeMillis();
        long t = endTime - startTime; //tiempo en miliSegundos.

        if( (t >= tiempo) && ( t <= (tiempo+1000)) ){ System.out.printf("|->Tiempo: (%dms) - True\n",t);}else{ System.out.printf("|-> tiempo: (%dms) - False \n",t); paso = false; }     //tiempo del proceso.1 min de mas de
        if(c.getImagenesCargadas() == 100){ System.out.printf("|->Imagenes Cargadas: True \n"); }else{ System.out.printf("|->Imagenes Cargadas: False \n"); paso = false; }  //Imagenes cargadas entre los hilos.
        if(ci.getContenedorInicial().size() == 100 ){ System.out.printf("|->Cantidad correcta en el contenedor : True \n");}else{System.out.printf("|->Cantidad correcta en el contenedor : False \n"); paso = false;}  //si existen 100 imagenes cargadas en el contendor
        if( imagenes_sin_repetir(ci) == 100 ){ System.out.printf("|->No se repiten los nombres: true \n"); }else{System.out.printf("|->No se repiten los nombres: False \n");paso = false; } //si los nombre no se repiten
        if(paso){ System.out.printf("--> TRUE <-- Paso el Test de la carga \n"); }else{ System.out.printf("--> FALSE <-- NO paso el Test de la carga\n");}
        System.out.println("------------------------------------");
    }

    /**
     * Testeo del proceso 2, Mejorar iluminacion a imagenes de un contenedor incial.
     */
    public void test_Mejorar(int cantidad_de_hilos){
        boolean paso = true;
        long startTime = System.currentTimeMillis();
        int cantidad_hilos = cantidad_de_hilos;  //--> Default: 3 hilos
        System.out.printf("Test_Mejora: (cantidad de hilos: %d) \n",cantidad_de_hilos);

        //creamos un conenedor con 100 imagenes  y cargamos 100 imagenes con diferentes nombres.
        ContenedorInicial ci = new ContenedorInicial();
        for(int j=0;j<100;j++){ Imagen ImAux = new Imagen(); ImAux.setNombre(ImAux.getNombre()+(j+1)); ci.carga(ImAux); }

        Mejorar  m = new Mejorar(tiempo,ci,cantidad_de_hilos);
        Thread hilos[] = new Thread[cantidad_hilos];

        for(int i=0;i<cantidad_hilos;i++){ Thread hilo_aux = new Thread(m); hilos[i] = hilo_aux; } //Creamos los hilos
        for(int i=0;i<cantidad_hilos;i++){ hilos[i].start(); } //iniciamos los hilos
        try{ for(int i=0;i<cantidad_hilos;i++){ hilos[i].join(); } } catch (InterruptedException e) { throw new RuntimeException(e); } //esperamos que se completen.

        long endTime = System.currentTimeMillis();
        long t = endTime - startTime; //tiempo en miliSegundos.

        //tiempo:
        if( (t >= tiempo) && ( t <= (tiempo+1000)) ){ System.out.printf("|->Tiempo: (%dms) - True\n",t);}else{ System.out.printf("|->Tiempo: (%dms) - False \n",t); paso = false; }     //tiempo del proceso.1 min de mas de

        //si se mejoraron 100 imagenes en el proceso de Mejoramiento de iluminacion.
        int control = 0;
        try{ control = m.getImagenesMejoradas(); }catch (IndexOutOfBoundsException e){ control = 0;}
        if( control == 100){ System.out.printf("|->Imagenes Mejoradas en el proceso: True \n"); }else{ System.out.printf("|->Imagenes Mejoradas en el proceso: False \n"); paso = false; }  //Imagenes cargadas entre los hilos.

        //si todas las imagenes tienen la mejora de iluminacion y por tres hilos.
        control = 0;
        try{for (Imagen imagen_aux : ci.getContenedorInicial()) {
                if (!imagen_aux.isIluminacionOriginal() && (imagen_aux.getImprovement().size() == 3)) { control++; } // true si no tiene la iluminacion original.
            }
        }catch (IndexOutOfBoundsException e){ control = 0;}
        if(control == 100){ System.out.printf("|->Cantidad I.M. en contenedor inicial:  True\n"); }else{System.out.printf("|->Cantidad I.M. en contenedor inicial:  False\n"); paso = false;}

        //si fueron mejoradas las 3 veces por hilos DISTINTOS.
        control = 0;
        try{for (Imagen imagen_aux : ci.getContenedorInicial()) {
                ArrayList<String> aux_improvement = new ArrayList<>(); aux_improvement = imagen_aux.getImprovement();
                if (!((aux_improvement.get(0).equals(aux_improvement.get(1))) || (aux_improvement.get(0).equals(aux_improvement.get(2))) || (aux_improvement.get(1).equals(aux_improvement.get(1))))) { control++; } // true si no tiene la iluminacion original.
            }
        }catch (IndexOutOfBoundsException e){ control = 0;} //si esta vacia.
        if(control == 100){ System.out.printf("|->I.M. por distintos hilos : True\n"); }else{System.out.printf("|->I.M. por distintos hilos : False\n"); paso = false;}
        //paso todos los test:
        if(paso){ System.out.printf("--> TRUE <-- Paso el Test de Mejorar \n"); }else{ System.out.printf("--> FALSE <-- NO paso el Test de Mejorar \n");}
        System.out.println("------------------------------------");
    }

    /**
     * Testeo del proceso 3, Ajustar tamaño de imagenes de un contenedor incial.
     */
    public void test_Ajustar(int cantidad_de_hilos){
        boolean paso = true;
        long startTime = System.currentTimeMillis();
        int cantidad_hilos = cantidad_de_hilos;  //--> Default: 3 hilos
        System.out.printf("Test_Ajuste: (cantidad de hilos: %d) \n",cantidad_de_hilos);

        //creamos un conenedor con 100 imagenes  y cargamos 100 imagenes con diferentes nombres. (ademas seteamos sus mejoramientos de iluminacion como REALIZADOS)
        ContenedorInicial ci = new ContenedorInicial();
        for(int j=0;j<100;j++){ Imagen ImAux = new Imagen(); ImAux.setNombre(ImAux.getNombre()+(j+1)); ImAux.setIluminacionOriginal(false); ci.carga(ImAux); }

        Ajustar  aj = new Ajustar(tiempo,ci,cantidad_de_hilos);
        Thread hilos[] = new Thread[cantidad_hilos];

        for(int i=0;i<cantidad_hilos;i++){ Thread hilo_aux = new Thread(aj); hilos[i] = hilo_aux; } //Creamos los hilos
        for(int i=0;i<cantidad_hilos;i++){ hilos[i].start(); } //iniciamos los hilos
        try{ for(int i=0;i<cantidad_hilos;i++){ hilos[i].join(); } } catch (InterruptedException e) { throw new RuntimeException(e); } //esperamos que se completen.

        long endTime = System.currentTimeMillis();
        long t = endTime - startTime; //tiempo en miliSegundos.

        //tiempo:
        if( (t >= tiempo) && ( t <= (tiempo+1000)) ){ System.out.printf("|->Tiempo: (%dms) - True\n",t);}else{ System.out.printf("|->Tiempo: (%dms) - False \n",t); paso = false; }     //tiempo del proceso.1 min de mas de

        //si se Ajustaron 100 imagenes en el proceso de Mejoramiento de iluminacion.
        int control = 0;
        try{ control = aj.getImagenesAjustadas(); }catch (IndexOutOfBoundsException e){ control = 0;}
        if( control == 100){ System.out.printf("|->Imagenes Ajustadas en el proceso: True \n"); }else{ System.out.printf("|->Imagenes Ajustadas en el proceso: False \n"); paso = false; }  //Imagenes cargadas entre los hilos.

        //si todas las imagenes tienen el ajuste de tamaño.
        control = 0;
        try{for (Imagen imagen_aux : ci.getContenedorInicial()) {
            if (!imagen_aux.isTamanioOriginal()) { control++; } // true si no tiene tamaño original.
        }
        }catch (IndexOutOfBoundsException e){ control = 0;}
        if(control == 100){ System.out.printf("|->Cantidad I.A. en contenedor inicial:  True \n"); }else{System.out.printf("|->Cantidad I.A. en contenedor inicial:  False \n"); paso = false;}

        //paso todos los test:
        if(paso){ System.out.printf("--> TRUE <-- Paso el Test de Mejorar \n"); }else{ System.out.printf("--> FALSE <-- NO paso el Test de Mejorar \n");}
        System.out.println("------------------------------------");
    }

    /**
     * Testeo del proceso 4, CargarCopia de las imagenes desde el contenedor inicial, al contenedor final.
     */
    public void test_CargarCopia(int cantidad_de_hilos){
        boolean paso = true;
        long startTime = System.currentTimeMillis();
        int cantidad_hilos = cantidad_de_hilos;  //--> Default: 2 hilos
        System.out.printf("Test_CargarCopia: (cantidad de hilos: %d) \n",cantidad_de_hilos);

        //creamos un conenedor con 100 imagenes  y cargamos 100 imagenes con diferentes nombres. (ademas seteamos sus mejoramientos de iluminacion y ajuste de tamaño como REALIZADOS)
        ContenedorInicial ci = new ContenedorInicial();
        ContenedorFinal cf = new ContenedorFinal();
        for(int k=0;k<100;k++){ Imagen ImAux = new Imagen(); ImAux.setNombre(ImAux.getNombre()+(k+1)); ImAux.setIluminacionOriginal(false); ImAux.setTamanioOriginal(false); ci.carga(ImAux); }

        CargarCopia cp  = new CargarCopia(tiempo,ci,cf,cantidad_de_hilos);
        Thread hilos[] = new Thread[cantidad_hilos];

        for(int i=0;i<cantidad_hilos;i++){ Thread hilo_aux = new Thread(cp); hilos[i] = hilo_aux; } //Creamos los hilos
        for(int i=0;i<cantidad_hilos;i++){ hilos[i].start(); } //iniciamos los hilos
        try{ for(int i=0;i<cantidad_hilos;i++){ hilos[i].join(); } } catch (InterruptedException e) { throw new RuntimeException(e); } //esperamos que se completen.

        long endTime = System.currentTimeMillis();
        long t = endTime - startTime; //tiempo en miliSegundos.

        //tiempo:
        if( (t >= tiempo) && ( t <= (tiempo+1000)) ){ System.out.printf("|->Tiempo: (%dms) - True\n",t);}else{ System.out.printf("|->Tiempo: (%dms) - False \n",t); paso = false; }     //tiempo del proceso.1 min de mas de

        //si se Copiaron 100 imagenes del ci en el proceso de CargarCopia
        int control = 0;
        try{ control = cp.getImagenesCopiadas(); }catch (IndexOutOfBoundsException e){ control = 0;}
        if( control == 100){ System.out.printf("|->Imagenes Copiadas en el proceso: True \n"); }else{ System.out.printf("|->Imagenes Copiadas en el proceso: False \n"); paso = false; }  //Imagenes Copiadas en el proceso entre los hilos.
        //si se se eliminaron las 100 imagenes del contenedor incial.
        if( ci.getContenedorInicial().size() == 0 ){ System.out.printf("|->Se Eliminaron todas del CI:  True\n");}else{ System.out.printf("|->Se Eliminaron todas del CI: False \n",t); paso = false; }
        //si se se pegaron las 100 imagenes del contenedor final.
        if( cf.getContenedorFinal().size() == 100 ){ System.out.printf("|->Se Pegaron todas en el CF:  True\n");}else{ System.out.printf("|->Se Pegaron todas en el CF: False \n",t); paso = false; }

        //si todas las imagenes copiadas tienen mejorada la iluminacion y el ajuste de tamaño.
        control = 0;
        try{for (Imagen imagen_aux : cf.getContenedorFinal()) {
            if (!imagen_aux.isIluminacionOriginal() && !imagen_aux.isTamanioOriginal() ) { control++; } // true si no tiene la iluminacion original ni tamaño original.
        }
        }catch (IndexOutOfBoundsException e){ control = 0;}
        if(control == 100){ System.out.printf("|->Las I copiadas tienen M.I. y A.T: True \n"); }else{System.out.printf("|->Las I copiadas tienen M.I. y A.T:  False \n"); paso = false;}
        //paso todos los test:
        if(paso){ System.out.printf("--> TRUE <-- Paso el Test de Cargar \n"); }else{ System.out.printf("--> FALSE <-- NO paso el Test de Mejorar \n");}
        System.out.println("------------------------------------");
    }

    public void test_COMPLETO(){
        System.out.println("------------------------------------");
        System.out.printf("-->Test Completo del programa: -->");
    }
    //-------------------------------------------------------------------------------------------------------------------------
    //metodos comunes de uso.
    /**
     *  Usado en test Cargar.
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
