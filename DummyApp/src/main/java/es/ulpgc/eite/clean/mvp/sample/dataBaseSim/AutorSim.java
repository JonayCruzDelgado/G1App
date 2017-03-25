package es.ulpgc.eite.clean.mvp.sample.dataBaseSim;

import es.ulpgc.eite.clean.mvp.sample.dataBase.Obra;
import io.realm.RealmList;

/**
 * Created by Marta on 24/03/2017.
 */

public class AutorSim {

    private int id;
    private String nombre;
    private String descripcion;
    private String uriImagen;
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

    public String getUriImagen() {
        return uriImagen;
    }

    public void setUriImagen(String uriImagen) {
        this.uriImagen = uriImagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
