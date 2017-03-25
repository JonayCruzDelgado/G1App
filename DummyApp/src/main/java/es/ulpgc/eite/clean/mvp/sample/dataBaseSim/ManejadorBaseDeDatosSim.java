package es.ulpgc.eite.clean.mvp.sample.dataBaseSim;


import java.util.ArrayList;

/**
 * Created by Marta on 24/03/2017.
 */

public class ManejadorBaseDeDatosSim {
    private static ManejadorBaseDeDatosSim ourInstance = new ManejadorBaseDeDatosSim();
    private ArrayList<CategoriaSim> listaCategorias;
    private ArrayList<AutorSim> listaAutores;
    private ArrayList<ObraSim> listaObrass;


    public static ManejadorBaseDeDatosSim getInstance() {
        if(ourInstance == null){
            ourInstance = new ManejadorBaseDeDatosSim();
        }
        return ourInstance;
    }

    private ManejadorBaseDeDatosSim() {

    }

    public  String getNombreCategoria(int id){
        return listaCategorias.get(id).getCategoria();
    }

    public String[] getArrayNombresCategorias(){
        String[] nombres={""};
        int i;
        for(i=0;i<= listaCategorias.size();i++){
            nombres[i] =listaCategorias.get(i).getCategoria();
        }
        return nombres;
    }

    public void initBaseDeDatos() {
        addCategoria("Pintura");
        addCategoria("Escultura");
        addCategoria("Arquitectura");
    }

    public void addCategoria(String nombreCategoria){
        CategoriaSim categoria = new CategoriaSim();
        categoria.setCategoria(nombreCategoria);
        listaCategorias.add(categoria);
        categoria.setId(listaCategorias.indexOf(categoria)+1);
    }
}
