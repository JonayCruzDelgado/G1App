package es.ulpgc.eite.clean.mvp.sample.dataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.nio.ByteBuffer;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Marta on 14/03/2017.
 */

class ManejadorBaseDeDatos {
    private static ManejadorBaseDeDatos ourInstance;
    private Realm realm;

    public static synchronized ManejadorBaseDeDatos getInstance() {
        if(ourInstance == null){
            ourInstance = new ManejadorBaseDeDatos();
        }
        return ourInstance;
    }

    private ManejadorBaseDeDatos() {
        realm = Realm.getDefaultInstance();
        initBaseDeDatos();
    }

    public String getNombreCategoria(int idCategoria){
        RealmResults<Categoria> result= realm.where(Categoria.class).equalTo("id",idCategoria).findAll();
        return result.get(0).getCategoria();
    }

    public String[] getListaAutores(int idCategoria){
        RealmResults<Autor> result =realm.where(Autor.class).equalTo("id",idCategoria).findAll(); //encuentra los autores de esa categoria

        String[] array={""};
        int i;
        for(i=0;i<= result.size();i++){
            array[i] =result.get(i).getNombre();
        }
        return array;
    }
    public String[] getListaObras(int idAutor){
        RealmResults<Obra> result =realm.where(Obra.class).equalTo("id",idAutor).findAll();

        String[] array={""};
        int i;
        for(i=0; i<=result.size();i++){
            array[i] =result.get(i).getNombre();
        }
        return array;
    }
    public String getDescripcionAutor(int idAutor){
        RealmResults<Autor> result= realm.where(Autor.class).equalTo("id",idAutor).findAll();
        return result.get(0).getDescripcion();
    }
    public String getNombreAutor(int idAutor){
        RealmResults<Autor> result= realm.where(Autor.class).equalTo("id",idAutor).findAll();
        return result.get(0).getNombre();
    }
    public  Bitmap getImagenAutor(int idAutor){
        RealmResults<Autor> result= realm.where(Autor.class).equalTo("id",idAutor).findAll();
        return byteArrayToImagen(result.get(0).getImagen());
    }

    public String getDescripcionObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getDescripcion();
    }
    public String getNombreObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getNombre();
    }
    public  Bitmap getImagenObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return byteArrayToImagen(result.get(0).getImagen());
    }
    public  Double getLatitud(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getLatitud();
    }
    public  Double getLongitud(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getLongitud();
    }

    public void initBaseDeDatos(){
        realm.beginTransaction();
        // toca rellener a saco aqui
        addCategoria("Escultura");
        addCategoria("Arquitectura");
        addCategoria("Pintura");


        realm.commitTransaction();
    }
// crear categoria, no se añaden autores por que voy a hacer que un autor solo pueda pertenecer a una categoria
// por lo que se especifica al crear el autor, es mucho mas simple asi, si lo quieren hacer que un autor pertenezca a varias categoria es mas lio
    public void addCategoria(String nombre){
        realm.beginTransaction();
        Categoria categoria= realm.createObject(Categoria.class);
        categoria.setCategoria(nombre);
        realm.commitTransaction();
    }

// crear autor nombre, descripcion, idem que en lo anterior para las obras
    public void addAutor(String nombre,String descripcion,String categoria, Bitmap imagen){
        realm.beginTransaction();
        Autor autor= realm.createObject(Autor.class);
        autor.setNombre(nombre);
        autor.setDescripcion(descripcion);
        autor.setCategoria(categoria);
        autor.setImagen(imagenToByteArray(imagen));
        realm.commitTransaction();
    }
// crear obra
    public void addObra(String nombre,String descripcion,String autor,Double latitud, Double longitud,Bitmap imagen){
        realm.beginTransaction();
        Obra obra= realm.createObject(Obra.class);
        obra.setNombre(nombre);
        obra.setDescripcion(descripcion);
        obra.setAutor(autor);
        obra.setLatitud(latitud);
        obra.setLongitud(longitud);
        obra.setImagen(imagenToByteArray(imagen));
        realm.commitTransaction();
    }
// dejo este metodo comentado tal cual lo copie y lo pegue, lo de las imagenes es un lio.
    public byte[] imagenToByteArray(Bitmap b){
//b is the Bitmap
//calculate how many bytes our image consists of.
        int bytes = b.getByteCount();
//or we can calculate bytes this way. Use a different value than 4 if you don't use 32bit images.
//int bytes = b.getWidth()*b.getHeight()*4;

        ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
        b.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

        byte[] array = buffer.array(); //Get the underlying array containing the data.
        return array;
    }
    public Bitmap byteArrayToImagen(byte[] array){
        Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
        return bitmap;

    }

}
