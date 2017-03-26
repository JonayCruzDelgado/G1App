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
        categoriaAutor="Escultura";
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

        nombreAutor ="Leonardo Da Vinci";
        categoriaAutor="Pintura";
        descripcionAutor="Leonardo da Vinci (Leonardo di ser Piero da Vinci) (Vinci, 15 de abril de 14522 -Amboise, 2 de mayo de 1519) fue " +
                "un polímata florentino del Renacimiento italiano. Fue a la vez pintor, anatomista, arquitecto, paleontólogo, artista, botánico, " +
                "científico, escritor, escultor, filósofo, ingeniero, inventor, músico, poeta y urbanista. Murió acompañado de su fiel Francesco" +
                " Melzi, a quien legó sus proyectos, diseños y pinturas. Tras pasar su infancia en su ciudad natal, Leonardo estudió con el " +
                "célebre pintor florentino Andrea de Verrocchio. Sus primeros trabajos de importancia fueron creados en Milán al servicio del" +
                " duque Ludovico Sforza. Trabajó a continuación en Roma, Bolonia y Venecia, y pasó los últimos años de su vida en Francia, " +
                "por invitación del rey Francisco I.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/leonardo_davinci"));

        nombreAutor ="Salvador Dali";
        categoriaAutor="Pintura";
        descripcionAutor="Salvador Felipe Jacinto Dalí i Domènech,1 marqués de Dalí de Púbol (Figueras, 11 de mayo de 1904, 23 de enero de 1989), " +
                "fue un pintor, escultor, grabador, escenógrafo y escritor español del siglo XX. Se le considera uno de los máximos " +
                "representantes del surrealismo.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/salvador_dali.jpg"));



        nombreAutor ="Caravaggio";
        categoriaAutor="Pintura";
        descripcionAutor="Michelangelo Merisi da Caravaggio (Milán, 29 de septiembre de 1571-Porto Ércole, 18 de julio de 1610) fue un pintor italiano " +
                "activo en Roma, Nápoles, Malta y Sicilia entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/caravaggio.jpg"));



        nombreAutor ="Vincent van Gogh";
        categoriaAutor="Pintura";
        descripcionAutor="Vincent Willem van Gogh (Vincent van Gogh) (Zundert, Países Bajos, 30 de marzo de 1853-Auvers-sur-Oise, Francia, 29 de julio de 1890) " +
                "fue un pintor neerlandés, uno de los principales exponentes del postimpresionismo.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/vicent_van_gogh.jpg"));

        nombreAutor ="Francisco de Goya";
        categoriaAutor="Pintura";
        descripcionAutor="Francisco de Goya y Lucientes (Fuendetodos, provincia de Zaragoza, 30 de marzo de 1746-Burdeos, Francia, 16 de abril de 1828) fue " +
                "un pintor y grabador español. Su obra abarca la pintura de caballete y mural, el grabado y el dibujo. En todas estas facetas desarrolló un " +
                "estilo que inaugura el Romanticismo. El arte goyesco supone, asimismo, el comienzo de la pintura contemporánea y es precursor de las " +
                "vanguardias pictóricas del siglo XX; por todo ello, se le considera uno de los artistas españoles más relevantes y uno de los grandes " +
                "maestros de la historia del arte.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/francisco_de_goya.jpg"));

        nombreAutor ="Le Corbusier";
        categoriaAutor="Arquitectura";
        descripcionAutor="Charles-Édouard Jeanneret-Gris, más conocido, a partir de la década de 1920,1 como Le Corbusier (La Chaux-de-Fonds, Cantón " +
                "de Neuchâtel, Suiza, 6 de octubre de 1887-Roquebrune-Cap-Martin, Provenza-Alpes-Costa Azul, Francia, 27 de agosto de 1965), " +
                "fue un arquitecto y teórico de la arquitectura, urbanista, decorador de interiores, pintor, escultor y hombre de letras suizo " +
                "nacionalizado francés en 1930.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/le_corbusier.jpg"));



        nombreAutor ="Ludwig Mies van der Rohe";
        categoriaAutor="Arquitectura";
        descripcionAutor="Ludwig Mies van der Rohe (Aquisgrán, Alemania, 27 de marzo de 1886 – Chicago, Illinois, 17 de agosto de 1969) " +
                "fue un arquitecto y diseñador industrial. Dirigió la escuela Bauhaus entre 1930 y 1933, año en que fue clausurada.  ";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/ludwig_mies_van_der_rohe.jpg"));

        nombreAutor ="Alexandre Gustave Eiffel";
        categoriaAutor="Arquitectura";
        descripcionAutor="Alexandre Gustave Eiffel (Dijon, 15 de diciembre de 1832-París, 27 de diciembre de 1923) fue un ingeniero " +
                "civil francés. Se graduó en la École centrale des arts et manufactures de París y adquirió renombre diseñando " +
                "varios puentes para la red francesa de ferrocarriles, de los cuales es especialmente notable el viaducto de " +
                "Garabit. Su fama actual se debe a su proyecto estrella, la mundialmente conocida torre Eiffel, construida para " +
                "la Exposición Universal de París de 1889.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/alexandre_gustave_eiffel.jpg"));

        nombreAutor ="Santiago Calatrava";
        categoriaAutor="Arquitectura";
        descripcionAutor="Santiago Calatrava Valls (Benimámet, 28 de julio de 1951) es un arquitecto, ingeniero civil y escultor " +
                "español. Entre los premios y reconocimientos que ha recibido destaca el Premio Príncipe de Asturias de las Artes " +
                "de 1999, el Premio Nacional de Arquitectura de 2005 y el Premio Europeo de Arquitectura de 2015. Actualmente, " +
                "cuenta con oficinas en Nueva York, Doha y Zúrich.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/calatrava.jpg"));

        nombreAutor ="Antoni Gaudí";
        categoriaAutor="Arquitectura";
        descripcionAutor="Antoni Gaudí i Cornet, también conocido en español como Antonio Gaudí1 (Reus o Riudoms, 25 de junio de " +
                "1852-Barcelona, 10 de junio de 1926) fue un arquitecto español, máximo representante del modernismo catalán.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,imagenToBitmap("/antoni_gaudi.jpg"));


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
        Number currentIdNum = realm.where(Categoria.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        Categoria categoria= realm.createObject(Categoria.class,nextId);
        categoria.setCategoria(nombre);
        realm.commitTransaction();
    }

// crear autor nombre, descripcion, idem que en lo anterior para las obras
    public void addAutor(String nombre,String descripcion,String categoria, Bitmap imagen){
        realm.beginTransaction();
        Number currentIdNum = realm.where(Autor.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        Autor autor= realm.createObject(Autor.class,nextId);
        autor.setNombre(nombre);
        autor.setDescripcion(descripcion);
        autor.setCategoria(categoria);
        autor.setImagen(imagenToByteArray(imagen));
        realm.commitTransaction();
    }
// crear obra
    public void addObra(String nombre,String descripcion,String autor,Double latitud, Double longitud,Bitmap imagen){
        realm.beginTransaction();

        Number currentIdNum = realm.where(Obra.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        Obra obra= realm.createObject(Obra.class,nextId);
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
