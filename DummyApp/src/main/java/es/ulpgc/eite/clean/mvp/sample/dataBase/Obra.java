package es.ulpgc.eite.clean.mvp.sample.dataBase;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Marta on 14/03/2017.
 */

public class Obra extends RealmObject {

    @PrimaryKey
    private int id;
    private String nombre;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String imagen;
    private int idAutor;
    private Boolean isInAssetsObra;

    public Boolean getIsInAssetsObra() {
        return isInAssetsObra;
    }

    public void setIsInAssetsObra(Boolean isInAssetsObra) {
        this.isInAssetsObra = isInAssetsObra;
    }

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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}



