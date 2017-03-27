package es.ulpgc.eite.clean.mvp.sample.dataBase;

import android.graphics.Bitmap;

import es.ulpgc.eite.clean.mvp.sample.obra.*;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Marta on 14/03/2017.
 */

public class Autor extends RealmObject {

    @PrimaryKey
    private int id;
    private String nombre;
    private String descripcion;
    private int idImagen;
    private String categoria;
    private RealmList<Obra> obras;

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

    public int getImagen() {
        return idImagen;
    }

    public void setImagen(int idImagen) {
        this.idImagen = idImagen;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public RealmList<Obra> getObras() {
        return obras;
    }

    public void setObras(RealmList<Obra> obras) {
        this.obras = obras;
    }
}
