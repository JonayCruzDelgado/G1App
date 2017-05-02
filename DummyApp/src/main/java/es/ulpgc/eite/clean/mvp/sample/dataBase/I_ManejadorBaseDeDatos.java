package es.ulpgc.eite.clean.mvp.sample.dataBase;

/**
 * Created by Marta on 02/05/2017.
 */

public interface I_ManejadorBaseDeDatos {
    String getNombreCategoria(int idCategoria);
    String getImagenCategoria(int idCategoria);

    int[] getListaIdAutores(int idCategoria);
    String[] getNombresByArrayIdsAutores(int[] ids);

    int[] getListaIdObras(int idAutor);
    String[] getNombresByArrayIdsObras(int[] ids);

    String getDescripcionAutor(int idAutor);
    String getNombreAutor(int idAutor);
    String getImagenAutor(int idAutor);
    Boolean isInAssetsAutor(int idAutor);

    String getDescripcionObra(int idObra);
    String getNombreObra(int idObra);
    String getImagenObra(int idObra);
    Double getLatitud(int idObra);
    Double getLongitud(int idObra);
    Boolean isInAssetsObra(int idObra);

    void addCategoria(String nombre, String imagen);
    void addAutor(String nombre, String descripcion, int idCategoria, String imagen, Boolean isInAssets);
    void addObra(String nombre, String descripcion, int idAutor, Double latitud, Double longitud, String imagen, Boolean isInAssets);
}
