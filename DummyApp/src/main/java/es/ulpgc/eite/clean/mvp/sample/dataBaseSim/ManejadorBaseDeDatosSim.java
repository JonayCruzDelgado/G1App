package es.ulpgc.eite.clean.mvp.sample.dataBaseSim;


import android.util.Log;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.dataBase.Categoria;

/**
 * Created by Marta on 24/03/2017.
 */

public class ManejadorBaseDeDatosSim {
    private static ManejadorBaseDeDatosSim ourInstance = new ManejadorBaseDeDatosSim();
    private ArrayList<CategoriaSim> listaCategorias;
    private ArrayList<AutorSim> listaAutores;
    private ArrayList<ObraSim> listaObras;


    public static ManejadorBaseDeDatosSim getInstance() {
        if(ourInstance == null){
            ourInstance = new ManejadorBaseDeDatosSim();
        }
        return ourInstance;
    }

    private ManejadorBaseDeDatosSim() {
        listaCategorias =new ArrayList<CategoriaSim>();
        listaAutores =new ArrayList<AutorSim>();
        listaObras =new ArrayList<ObraSim>();

        initBaseDeDatos();//inicializabas la base de datos dos veces una aqui y otra en el modelos de autores
        //tras pedir el getinstance.

    }


    public void initBaseDeDatos() {
        addCategoria("Pintura",R.mipmap.ic_cuadro);
        addCategoria("Arquitectura",R.mipmap.ic_arqui);//error cuidado con el orden.
        //estaba escultura antes que arquitectura y provocaba que se cargara mal la imagen y el texto
        //en el layaout.
        addCategoria("Escultura",R.mipmap.ic_escultura);


        String nombreAutor ="Miguel Ángel";
        String categoriaAutor ="Escultura";
        String descripcionAutor ="Michelangelo Buonarroti (Caprese, 6 de marzo de 1475-Roma, " +
                "18 de febrero de 1564), conocido en español como Miguel Ángel, fue un arquitecto, " +
                "escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia tanto por " +
                "sus esculturas como por sus pinturas y obra arquitectónica. Desarrolló su labor artística a lo largo de más de " +
                "setenta años entre Florencia y Roma, que era donde vivían sus grandes mecenas, la familia Médici de Florencia y " +
                "los diferentes papas romanos.";


        addAutor(nombreAutor,descripcionAutor,categoriaAutor, R.mipmap.miguel_angel);

        nombreAutor ="Verrocchio";
        categoriaAutor="Escultura";
        descripcionAutor="Andrea del Verrocchio, nacido Andrea di Michele di Francesco de Cioni, " +
                "conocido simplemente como Verrocchio (Florencia 1435 - Venecia 1488) fue un pintor, escultor " +
                "y orfebre cuatrocentista italiano. Trabajó en la corte de Lorenzo de' Medici en Florencia. Entre sus " +
                "alumnos estuvieron Leonardo da Vinci, Perugino, Ghirlandaio y Sandro Botticelli, pero también influyó en Miguel Ángel. " +
                "Trabajó en el estilo serenamente clásico del primer renacimiento florentino.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.verrocchio);

        nombreAutor ="Gian Lorenzo Bernini";
        categoriaAutor="Escultura";
        descripcionAutor="Gian Lorenzo Bernini (Nápoles, 7 de diciembre de 1598 - Roma, 28 de noviembre de 1680) fue un escultor, arquitecto " +
                "y pintor italiano. Trabajó principalmente en Roma " +
                "y es considerado el más destacado escultor de su generación, creador del estilo escultórico barroco.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.gian_lorenzo_bernini);

        nombreAutor ="Auguste Rodin";
        categoriaAutor="Escultura";
        descripcionAutor="François-Auguste-René Rodin (París, 12 de noviembre de 1840 - Meudon, 17 de noviembre de 1917) fue un escultor " +
                "francés contemporáneo del impresionismo, y considerado como uno de los \"padres de la escultura moderna\".";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.auguste_rodin);

        nombreAutor ="Donatello";
        categoriaAutor="Escultura";
        descripcionAutor="Donato di Niccolò di Betto Bardi, conocido como Donatello (Florencia, Italia, 1386-Ibídem, 13 de diciembre de 1466), " +
                "fue un artista y escultor italiano de principios del Renacimiento, uno de los padres del periodo junto con Leon Battista " +
                "Alberti, Brunelleschi y Masaccio. Donatello se convirtió en una fuerza innovadora en el campo de la escultura monumental y " +
                "en el tratamiento de los relieves, donde logró representar una gran profundidad " +
                "dentro de un mínimo plano, denominándose con el nombre de stiacciato, es decir «relieve aplanado o aplastado».";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.donatello);

        nombreAutor ="Leonardo Da Vinci";
        categoriaAutor="Pintura";
        descripcionAutor="Leonardo da Vinci (Leonardo di ser Piero da Vinci) (Vinci, 15 de abril de 14522 -Amboise, 2 de mayo de 1519) fue " +
                "un polímata florentino del Renacimiento italiano. Fue a la vez pintor, anatomista, arquitecto, paleontólogo, artista, botánico, " +
                "científico, escritor, escultor, filósofo, ingeniero, inventor, músico, poeta y urbanista. Murió acompañado de su fiel Francesco" +
                " Melzi, a quien legó sus proyectos, diseños y pinturas. Tras pasar su infancia en su ciudad natal, Leonardo estudió con el " +
                "célebre pintor florentino Andrea de Verrocchio. Sus primeros trabajos de importancia fueron creados en Milán al servicio del" +
                " duque Ludovico Sforza. Trabajó a continuación en Roma, Bolonia y Venecia, y pasó los últimos años de su vida en Francia, " +
                "por invitación del rey Francisco I.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.leonardo_davinci);

        nombreAutor ="Salvador Dali";
        categoriaAutor="Pintura";
        descripcionAutor="Salvador Felipe Jacinto Dalí i Domènech,1 marqués de Dalí de Púbol (Figueras, 11 de mayo de 1904, 23 de enero de 1989), " +
                "fue un pintor, escultor, grabador, escenógrafo y escritor español del siglo XX. Se le considera uno de los máximos " +
                "representantes del surrealismo.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.salvador_dali);



        nombreAutor ="Caravaggio";
        categoriaAutor="Pintura";
        descripcionAutor="Michelangelo Merisi da Caravaggio (Milán, 29 de septiembre de 1571-Porto Ércole, 18 de julio de 1610) fue un pintor italiano " +
                "activo en Roma, Nápoles, Malta y Sicilia entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.caravaggio);



        nombreAutor ="Vincent van Gogh";
        categoriaAutor="Pintura";
        descripcionAutor="Vincent Willem van Gogh (Vincent van Gogh) (Zundert, Países Bajos, 30 de marzo de 1853-Auvers-sur-Oise, Francia, 29 de julio de 1890) " +
                "fue un pintor neerlandés, uno de los principales exponentes del postimpresionismo.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.vincent_van_gogh);

        nombreAutor ="Francisco de Goya";
        categoriaAutor="Pintura";
        descripcionAutor="Francisco de Goya y Lucientes (Fuendetodos, provincia de Zaragoza, 30 de marzo de 1746-Burdeos, Francia, 16 de abril de 1828) fue " +
                "un pintor y grabador español. Su obra abarca la pintura de caballete y mural, el grabado y el dibujo. En todas estas facetas desarrolló un " +
                "estilo que inaugura el Romanticismo. El arte goyesco supone, asimismo, el comienzo de la pintura contemporánea y es precursor de las " +
                "vanguardias pictóricas del siglo XX; por todo ello, se le considera uno de los artistas españoles más relevantes y uno de los grandes " +
                "maestros de la historia del arte.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.francisco_de_goya);

        nombreAutor ="Le Corbusier";
        categoriaAutor="Arquitectura";
        descripcionAutor="Charles-Édouard Jeanneret-Gris, más conocido, a partir de la década de 1920,1 como Le Corbusier (La Chaux-de-Fonds, Cantón " +
                "de Neuchâtel, Suiza, 6 de octubre de 1887-Roquebrune-Cap-Martin, Provenza-Alpes-Costa Azul, Francia, 27 de agosto de 1965), " +
                "fue un arquitecto y teórico de la arquitectura, urbanista, decorador de interiores, pintor, escultor y hombre de letras suizo " +
                "nacionalizado francés en 1930.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.le_corbusier);



        nombreAutor ="Ludwig Mies van der Rohe";
        categoriaAutor="Arquitectura";
        descripcionAutor="Ludwig Mies van der Rohe (Aquisgrán, Alemania, 27 de marzo de 1886 – Chicago, Illinois, 17 de agosto de 1969) " +
                "fue un arquitecto y diseñador industrial. Dirigió la escuela Bauhaus entre 1930 y 1933, año en que fue clausurada.  ";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.ludwig_mies_van_der_rohe);

        nombreAutor ="Alexandre Gustave Eiffel";
        categoriaAutor="Arquitectura";
        descripcionAutor="Alexandre Gustave Eiffel (Dijon, 15 de diciembre de 1832-París, 27 de diciembre de 1923) fue un ingeniero " +
                "civil francés. Se graduó en la École centrale des arts et manufactures de París y adquirió renombre diseñando " +
                "varios puentes para la red francesa de ferrocarriles, de los cuales es especialmente notable el viaducto de " +
                "Garabit. Su fama actual se debe a su proyecto estrella, la mundialmente conocida torre Eiffel, construida para " +
                "la Exposición Universal de París de 1889.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.alexandre_gustave_eiffel);

        nombreAutor ="Santiago Calatrava";
        categoriaAutor="Arquitectura";
        descripcionAutor="Santiago Calatrava Valls (Benimámet, 28 de julio de 1951) es un arquitecto, ingeniero civil y escultor " +
                "español. Entre los premios y reconocimientos que ha recibido destaca el Premio Príncipe de Asturias de las Artes " +
                "de 1999, el Premio Nacional de Arquitectura de 2005 y el Premio Europeo de Arquitectura de 2015. Actualmente, " +
                "cuenta con oficinas en Nueva York, Doha y Zúrich.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.calatrava);

        nombreAutor ="Antoni Gaudí";
        categoriaAutor="Arquitectura";
        descripcionAutor="Antoni Gaudí i Cornet, también conocido en español como Antonio Gaudí1 (Reus o Riudoms, 25 de junio de " +
                "1852-Barcelona, 10 de junio de 1926) fue un arquitecto español, máximo representante del modernismo catalán.";
        addAutor(nombreAutor,descripcionAutor,categoriaAutor,R.mipmap.antoni_gaudi);

        String nombreObra="El David";
        String autor="Miguel Ángel";
        String descripcionObra ="El David es una escultura de mármol blanco de 5,17 metros de altura y 5572 kilogramos de" +
                " masa, realizada por Miguel Ángel Buonarroti entre 1501 y 1504 por encargo de la Opera del Duomo de la " +
                "catedral de Santa María del Fiore de Florencia. La escultura representa al rey David bíblico en el momento" +
                " previo a enfrentarse con Goliat, y fue acogida como un símbolo de la República de Florencia frente a la" +
                " hegemonía de sus derrocados dirigentes, los Médici, y la amenaza de los estados adyacentes, especialmente" +
                " los Estados Pontificios." ;
        Double latitud=43.4636;
        Double longitud=11.1534;
        addObra(nombreObra,descripcionObra,autor,latitud,longitud, R.mipmap.el_david);



        nombreObra="La Madonna de Brujas";
        autor="Miguel Ángel";
        descripcionObra ="La Madonna de Brujas es una escultura realizada en mármol por Miguel Ángel en el año 1504." +
                " De 1,23 metros de altura, se encuentra en la iglesia de Nuestra Señora de Brujas (ciudad de la actual Bélgica)." ;
        latitud=51.1217;
        longitud=3.1328;
        addObra(nombreObra,descripcionObra,autor,latitud,longitud, R.mipmap.la_madonna_de_brujas);

        nombreObra="El Cristo de Minerva";
        autor="Miguel Ángel";
        descripcionObra ="Cristo de la Minerva es la denominación de una escultura de mármol, obra de Miguel " +
                "Ángel, finalizada en 1521, que representa a un Cristo redentor, desnudo, abrazando la Cruz." ;
        latitud=41.5353;
        longitud=12.2842;
        addObra(nombreObra,descripcionObra,autor,latitud,longitud, R.mipmap.el_cristo_de_minerva);

        nombreObra="";
        autor="";
        descripcionObra ="" ;
        latitud=0.0;
        longitud=0.0;
        addObra(nombreObra,descripcionObra,autor,latitud,longitud, R.mipmap.ic_cuadro);

        nombreObra="";
        autor="";
        descripcionObra ="" ;
        latitud=0.0;
        longitud=0.0;
        addObra(nombreObra,descripcionObra,autor,latitud,longitud, R.mipmap.ic_cuadro);


    }

    public void addCategoria(String nombreCategoria,int idResImagen){
        CategoriaSim categoria = new CategoriaSim();
        categoria.setCategoria(nombreCategoria);
        categoria.setIdResImagen(idResImagen);
        listaCategorias.add(categoria);
        categoria.setId(listaCategorias.indexOf(categoria));
    }
    public void addAutor(String nombreAutor, String descripcion, String categoria,int idResImagen){
        AutorSim autor = new AutorSim();
        autor.setNombre(nombreAutor);
        autor.setCategoria(categoria);
        autor.setDescripcion(descripcion);
        autor.setIdResImagen(idResImagen);
        autor.setId(listaAutores.size());
        listaAutores.add(autor);

    }

    public void addObra(String nombreObra, String descripcion, String autor,Double latitud, Double longitud,int idResImagen){
        ObraSim obra = new ObraSim();
        obra.setNombre(nombreObra);
        obra.setAutor(autor);
        obra.setDescripcion(descripcion);
        obra.setIdResImagen(idResImagen);
        obra.setLatidud(latitud);
        obra.setLongitud(longitud);
        listaObras.add(obra);
        obra.setId(listaObras.indexOf(obra));
    }

    public String[] arrayNombresCategorias(){
        String[] array= new String[listaCategorias.size()];
        int i;
        for(i=0;i< listaCategorias.size();i++){
            array[i] =listaCategorias.get(i).getCategoria();
        }
        return array;
    }

    public int[] arrayIdsAutorByCategoria (String categoria){
        int[] array= new int[listaAutores.size()];//se crea un string donde sobran valores, pues
        //tiene el tamaño completo de la lista de autores
        int i;
        int k=0;
        Log.v("arrayIdsAutorByCat",categoria);

        for (i=0; i< listaAutores.size();i++){
            if(listaAutores.get(i).getCategoria().equals(categoria)){
                array[k] = listaAutores.get(i).getId();
                k++;
            }
        }
        int[] result= new int[k];
        for(i=0; i<k;i++){// se crea un string con los autores correctos y se eliminan los valores sobrantes.
            result[i]=array[i];
        }
        return result;
    }

    public int[] arrayIdsObraByAutor (String autor){
        int[] array= new int[listaObras.size()];//se crea un string donde sobran valores, pues t
        //tiene el tamaño completo de la lista de obras
        int i;
        int k = 0;
        for (i=0; i< listaObras.size();i++){
            if(listaObras.get(i).getAutor().equals(autor)){
                array[k] = listaObras.get(i).getId();
                k++;
            }
        }
        int[] result= new int[k];
        for(i=0; i<k;i++){ // se crea un string con las obras correctas y se eliminan los valores sobrantes.
            result[i]=array[i];
        }
        return result;
    }

    public String[] arrayNombresByIdsAutores(int[] ids){
        String[] array= new String[ids.length];//tamaño necesario el valor del vector ids
        int i;
        for (i=0; i< ids.length;i++){//estaba puesto listaAutor.lenght, el tamaño del bucle debe de ser
            //del tamaño del vector ids de los autores de la categoria seleccionada.
            array[i]= nombreAutor(ids[i]);//estaba puesto nombreAutor(i), con esto rellenas con los
            //primeros autores del array se debe poner nombreAutor(ids[i]) para rellenar con los que pasa el
            //parametro
        }
        return array;
    }

    public String[] arrayNombresByIdsObras(int[] ids){
        String[] array= new String[ids.length];
        int i;
        for (i=0; i< ids.length;i++){
            array[i]= nombreObra(ids[i]);
        }
        return array;
    }

    public  String nombreCategoria(int id){
        return listaCategorias.get(id).getCategoria();
    }
    public  int idImagenCategoria(int id){
        return listaCategorias.get(id).getIdResImagen();
    }
    public String nombreAutor(int id){
        return listaAutores.get(id).getNombre();
    }
    public String nombreObra(int id){
        return listaObras.get(id).getNombre();
    }
    public String descripcionAutor(int id){
        return listaAutores.get(id).getDescripcion();
    }
    public String descripcionObra(int id){
        return listaObras.get(id).getDescripcion();
    }
    public int idImagenAutor(int id){
        return  listaAutores.get(id).getIdResImagen();
    }
    public int idImagenObra(int id){
        return  listaObras.get(id).getIdResImagen();
    }
    public Double latitudObra(int id){
        return  listaObras.get(id).getLatidud();
    }
    public Double longitudObra(int id){
        return  listaObras.get(id).getLongitud();
    }


}
