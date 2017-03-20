package es.ulpgc.eite.clean.mvp.sample.dataBase;

import android.graphics.Bitmap;

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
    private byte[] imagen;
    private RealmList<Obra> obras;
    private String categoria;

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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public RealmList<Obra> getObras() {
        return obras;
    }

    public void setObras(RealmList<Obra> obras) {
        this.obras = obras;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
