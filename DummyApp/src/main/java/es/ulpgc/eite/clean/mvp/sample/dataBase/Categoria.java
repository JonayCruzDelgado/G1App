package es.ulpgc.eite.clean.mvp.sample.dataBase;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Marta on 14/03/2017.
 */

public class Categoria extends RealmObject {

    @PrimaryKey
    private int id;
    private String categoria;
    private RealmList<Autor> autores;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public RealmList<Autor> getAutores() {
        return autores;
    }

    public void setAutores(RealmList<Autor> autores) {
        this.autores = autores;
    }
}
