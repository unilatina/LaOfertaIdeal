package xyz.camiloarguello.laofertaideal;

public class Persona {

    // Atributos
    private int ID;
    private String nombre;
    private int edad;
    private int imagen;

    // Constructor
    public Persona(int ID, String nombre, int edad, int imagen) {
        this.ID = ID;
        this.nombre = nombre;
        this.edad = edad;
        this.imagen = imagen;
    }


    // Métodos
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", imagen=" + imagen +
                '}';
    }

    public void cargarAds(){

    }
    public void cargarOferta(){

    }
}
