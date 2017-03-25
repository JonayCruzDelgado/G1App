package es.ulpgc.eite.clean.mvp.sample.dataBaseSim;

/**
 * Created by Marta on 24/03/2017.
 */

public class ObraSim {


    private int id;
    private String nombre;
    private String descripcion;
    private int idResImagen;
    private String autor;
    private Double latidud;
    private Double longitud;

    public ObraSim(){}


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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getLatidud() {
        return latidud;
    }

    public void setLatidud(Double latidud) {
        this.latidud = latidud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

}
