package es.ulpgc.eite.clean.mvp.sample.dataBaseSim;

/**
 * Created by Marta on 24/03/2017.
 */

public class AutorSim {

    private int id;
    private String nombre;
    private String descripcion;
    private int idResImagen;
    private String categoria;

    public AutorSim(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdResImagen() {
        return idResImagen;
    }

    public void setIdResImagen(int idResImagen) {
        this.idResImagen = idResImagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
