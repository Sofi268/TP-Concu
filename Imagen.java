public class Imagen {
    private String nombre;
    private int largo;
    private int ancho;
    private int tamanio;
    private int iluminacion;
    private int improvements;
    private boolean iluminacionMejorada;
    private boolean tamanioAjustado;
    //matriz encargada de las interraciones con los hilos y procesos.
    private int[][] contador;
    private long id1, id2, id3; //cuantas veces toca la imagen cada hilo.
    public Imagen() {
        improvements = 0;
        iluminacionMejorada = false;
        tamanioAjustado = false;
        id1 = 0;
        id2 = 0;
        id3 = 0;
        //dimesiones default
        largo = 10;
        ancho = 10;
        contador = new int[][] {  //fila:proceso , columna:hilo.
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

    public int getImprovements() {
        return improvements;
    }

    /**
     * Este metodo lleva el control de cuntos hilos modificaron la Iluminacion de la Imagen.
     * No permite que el mismo hilo modifique la iluminacion mas de una vez.
     */
    public void setImprovements() {
        if(id1 == 0){    //ya lo toco el h1
            id1 = Thread.currentThread().getId();
            //improvements++;
        }
        else{
            if(id2 == 0 && id1 != Thread.currentThread().getId()){
                id2 = Thread.currentThread().getId();
                //improvements++;
            }
            else{
                if(id3 == 0 && id1 != Thread.currentThread().getId() && id2 != Thread.currentThread().getId()){
                    id3 = Thread.currentThread().getId();
                    //improvements++;
                    setIluminacion(); //iluminacionMejorada a true
                }
            }
        }

    }
    //sincronizados para que el valor que se obtiene de contador sea lo mas preciso posible.
    public synchronized void setContador(int proceso, int hilo){
        contador[proceso][hilo] ++;
    }
    public synchronized int[][] getContador(){
        return contador;
    }
}
