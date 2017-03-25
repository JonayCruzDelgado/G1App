package es.ulpgc.eite.clean.mvp.sample.dataBaseSim;

/**
 * Created by Marta on 24/03/2017.
 */

public class CategoriaSim {

    private int id;
    private String Categoria;
    private int idResImagen;

    public CategoriaSim(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
    public int getIdResImagen() {
        return idResImagen;
    }

    public void setIdResImagen(int idResImagen) {
        this.idResImagen = idResImagen;
    }
}
