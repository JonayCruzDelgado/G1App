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


        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/miguel_angel.jpg"));

        nombreAutor ="Verrocchio";
        categoriaAutor="Escultura";
        descripcionAutor="Andrea del Verrocchio, nacido Andrea di Michele di Francesco de Cioni, " +
                "conocido simplemente como Verrocchio (Florencia 1435 - Venecia 1488) fue un pintor, escultor " +
                "y orfebre cuatrocentista italiano. Trabajó en la corte de Lorenzo de' Medici en Florencia. Entre sus " +
                "alumnos estuvieron Leonardo da Vinci, Perugino, Ghirlandaio y Sandro Botticelli, pero también influyó en Miguel Ángel. " +
                "Trabajó en el estilo serenamente clásico del primer renacimiento florentino.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/verrocchio.jpg"));

        nombreAutor ="Gian Lorenzo Bernini";
        categoriaAutor="Escultor";
        descripcionAutor="Gian Lorenzo Bernini (Nápoles, 7 de diciembre de 1598 - Roma, 28 de noviembre de 1680) fue un escultor, arquitecto " +
                "y pintor italiano. Trabajó principalmente en Roma " +
                "y es considerado el más destacado escultor de su generación, creador del estilo escultórico barroco.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/gian_lorenzo_bernini.jpg"));

        nombreAutor ="Auguste Rodin";
        categoriaAutor="Escultura";
        descripcionAutor="François-Auguste-René Rodin (París, 12 de noviembre de 1840 - Meudon, 17 de noviembre de 1917) fue un escultor " +
                "francés contemporáneo del impresionismo, y considerado como uno de los \"padres de la escultura moderna\".";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/auguste_rodin.png"));

        nombreAutor ="Donatello";
        categoriaAutor="Escultura";
        descripcionAutor="Donato di Niccolò di Betto Bardi, conocido como Donatello (Florencia, Italia, 1386-Ibídem, 13 de diciembre de 1466), " +
                "fue un artista y escultor italiano de principios del Renacimiento, uno de los padres del periodo junto con Leon Battista " +
                "Alberti, Brunelleschi y Masaccio. Donatello se convirtió en una fuerza innovadora en el campo de la escultura monumental y " +
                "en el tratamiento de los relieves, donde logró representar una gran profundidad " +
                "dentro de un mínimo plano, denominándose con el nombre de stiacciato, es decir «relieve aplanado o aplastado».";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/donatello.jpg"));

        nombreAutor ="";
        categoriaAutor="";
        descripcionAutor="";
        //addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap());


    }
    //pasar el archivo que entra ha un bitmap
    public Bitmap imagenToBitmap( String fileName){
        File file1 = new File(fileName);
        Bitmap imagen = BitmapFactory.decodeFile(file1.getAbsolutePath());
        return imagen;

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
