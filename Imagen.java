public class Imagen {
    private String nombre;
    private int tamanio;
    private int iluminacion;
    private int improvements;
    private boolean iluminacionMejorada;
    private boolean tamanioAjustado;
    private long id1, id2, id3; //cuantas veces toca la imagen cada hilo.
    public Imagen() {
        improvements = 0;
        iluminacionMejorada = false;
        tamanioAjustado = false;
        id1 = 0;
        id2 = 0;
        id3 = 0;
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
            improvements++;
        }
        else{
            if(id2 == 0 && id1 != Thread.currentThread().getId()){
                id2 = Thread.currentThread().getId();
                improvements++;
            }
            else{
                if(id3 == 0 && id1 != Thread.currentThread().getId() && id2 != Thread.currentThread().getId()){
                    id3 = Thread.currentThread().getId();
                    improvements++;
                    setIluminacion();
                }
            }
        }
    }
}
