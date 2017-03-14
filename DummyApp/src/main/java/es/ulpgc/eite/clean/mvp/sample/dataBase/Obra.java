package es.ulpgc.eite.clean.mvp.sample.dataBase;

import android.graphics.Bitmap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Marta on 14/03/2017.
 */

public class Obra extends RealmObject {

    @PrimaryKey
    private String id;
    private String nombre;
    private String descripcion;
    private Bitmap imagen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
