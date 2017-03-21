package es.ulpgc.eite.clean.mvp.sample.dataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
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

    public int[] getListaIdAutores(String nombreCategoria){
        RealmResults<Autor> result =realm.where(Autor.class).equalTo("categoria",nombreCategoria).findAll(); //encuentra los autores de esa categoria

        int[] array={-1};
        int i;
        for(i=0;i<= result.size();i++){
            array[i] =result.get(i).getId();
        }
        return array;
    }
    public int[] getListaIdObras(String nombreAutor){
        RealmResults<Obra> result =realm.where(Obra.class).equalTo("nombre",nombreAutor).findAll();

        int[] array={-1};
        int i;
        for(i=0; i<=result.size();i++){
            array[i] =result.get(i).getId();
        }
        return array;
    }

    public String [] getNombresByArrayIdsAutores(int[] ids){
        String[] nombres={""};
        int i;
        for(i=0;i<= ids.length;i++){
            nombres[i] =getNombreAutor(ids[i]);
        }
        return nombres;
    }

    public String [] getNombresByArrayIdsObras(int[] ids){
        String[] nombres={""};
        int i;
        for(i=0;i<= ids.length;i++){
            nombres[i] =getNombreObra(ids[i]);
        }
        return nombres;
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

        // toca rellener a saco aqui
        addCategoria("Escultura");
        addCategoria("Arquitectura");
        addCategoria("Pintura");

        String nombreAutor ="Miguel Ángel";
        String categoriaAutor ="Escultura";
        String descripcionAutor ="Michelangelo Buonarroti (Caprese, 6 de marzo de 1475-Roma, " +
                "18 de febrero de 1564), conocido en español como Miguel Ángel, fue un arquitecto, " +
                "escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia tanto por " +
                "sus esculturas como por sus pinturas y obra arquitectónica. Desarrolló su labor artística a lo largo de más de " +
                "setenta años entre Florencia y Roma, que era donde vivían sus grandes mecenas, la familia Médici de Florencia y " +
                "los diferentes papas romanos.";
        String fileName = "/miguel_anguel.jpg";
        File file1 = new File(fileName);
        Bitmap imagenAutor = BitmapFactory.decodeFile(file1.getAbsolutePath());

        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenAutor);

        nombreAutor ="";
        categoriaAutor="";
        descripcionAutor="";
        fileName = "/miguel_anguel.jpg";
        File file2 = new File(fileName);
        imagenAutor = BitmapFactory.decodeFile(file2.getAbsolutePath());

        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenAutor);

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
