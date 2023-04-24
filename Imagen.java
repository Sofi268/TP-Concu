public class Imagen {
    private String nombre;
    private int largo;
    private int ancho;
    private int tamanio;
    private int iluminacion;
    private boolean iluminacionMejorada;
    private boolean tamanioAjustado;
    //matriz encargada de las interraciones con los hilos y procesos.
    private int[][] improvements;  //contador es nuestro improvements.
    private long id1, id2, id3; //cuantas veces toca la imagen cada hilo para mejorar la iluminacion.
    public Imagen() {
        iluminacionMejorada = false;
        tamanioAjustado = false;
        id1 = 0;
        id2 = 0;
        id3 = 0;
        //dimesiones default
        largo = 10;
        ancho = 10;
        improvements = new int[][] {  //fila:proceso , columna:hilo.
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}
        };
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getIluminacion() {
        return iluminacionMejorada;
    }

    private void setIluminacion() {
        iluminacionMejorada = true;
    }
    public boolean getTamanio(){
        return tamanioAjustado;
    }
    public void setTamanio(int l, int a){
        //metodos de ajuste de tamanio.
        largo = l;
        ancho = a;
        tamanioAjustado = true;
    }

    /**
     * Este metodo lleva el control de cuntos hilos modificaron la Iluminacion de la Imagen.
     * No permite que el mismo hilo modifique la iluminacion mas de una vez.
     */
    public void setImprovements() {
        String aux = Thread.currentThread().getName();
        if(id1 == 0){    //ya lo toco el h1
            id1 = Thread.currentThread().getId();
            setImprovements(1, aux);
        }
        else{
            if(id2 == 0 && id1 != Thread.currentThread().getId()){
                id2 = Thread.currentThread().getId();
                setImprovements(1, aux);
            }
            else{
                if(id3 == 0 && id1 != Thread.currentThread().getId() && id2 != Thread.currentThread().getId()){
                    id3 = Thread.currentThread().getId();
                    setImprovements(1, aux);
                    setIluminacion(); //iluminacionMejorada a true
                }
            }
        }
    }
    //sincronizados para que el valor que se obtiene de contador sea lo mas preciso posible.
    public synchronized void setImprovements(int proceso,String nombreHilo){
        switch (nombreHilo){
            case "Thread-0":improvements[proceso][0] ++;
                break;
            case "Thread-1":improvements[proceso][1] ++;
                break;
            case "Thread-2":improvements[proceso][2] ++;
                break;
            case "Thread-3":improvements[proceso][3] ++;
                break;
            case "Thread-4":improvements[proceso][4] ++;
                break;
            case "Thread-5":improvements[proceso][5] ++;
                break;
            case "Thread-6":improvements[proceso][6] ++;
                break;
            case "Thread-7":improvements[proceso][7] ++;
                break;
            case "Thread-8":improvements[proceso][8] ++;
                break;
            case "Thread-9":improvements[proceso][9] ++;
                break;
            default: break;
        }
    }
    public synchronized int[][] getImprovements(){
        return improvements;
    }
}
