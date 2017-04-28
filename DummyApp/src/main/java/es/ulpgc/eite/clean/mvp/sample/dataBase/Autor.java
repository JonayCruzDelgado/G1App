package es.ulpgc.eite.clean.mvp.sample.dataBase;

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
    private int idCategoria;
    private String imagen;
    private Boolean isInAssetsAutor;

    public Boolean getIsInAssetsAutor() {
        return isInAssetsAutor;
    }

    public void setIsInAssetsAutor(Boolean inAssetAutor) {
        isInAssetsAutor = inAssetAutor;
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


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
