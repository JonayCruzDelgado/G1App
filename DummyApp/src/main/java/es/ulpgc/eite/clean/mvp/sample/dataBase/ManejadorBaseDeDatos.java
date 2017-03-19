package es.ulpgc.eite.clean.mvp.sample.dataBase;

/**
 * Created by Marta on 14/03/2017.
 */

class ManejadorBaseDeDatos {
    private static ManejadorBaseDeDatos ourInstance;

    public static synchronized ManejadorBaseDeDatos getInstance() {
        if(ourInstance == null){
            ourInstance = new ManejadorBaseDeDatos();
        }
        return ourInstance;
    }

    private ManejadorBaseDeDatos() {
    }

    public void initBaseDeDatos(){

    }

}
