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

public class ManejadorBaseDeDatos {
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
    public String getImagenCategoria(int idCategoria){
        RealmResults<Categoria> result= realm.where(Categoria.class).equalTo("id",idCategoria).findAll();
        return result.get(0).getImagen();
    }

    public int[] getListaIdAutores(int idCategoria){
        RealmResults<Autor> result =realm.where(Autor.class).equalTo("idCategoria",idCategoria).findAll(); //encuentra los autores de esa categoria
        int[] array=new int[result.size()];
        int i;
        for(i=0;i< result.size();i++){
            array[i] =result.get(i).getId();
        }
        return array;

    }
    public String[] getNombresByArrayIdsAutores(int[] ids){
        String[] array= new String[ids.length];
        int i;
        for (i=0; i< ids.length;i++){
            array[i]= getNombreAutor(ids[i]);
        }
        return array;
    }
    public int[] getListaIdObras(int idAutor){
        RealmResults<Obra> result =realm.where(Obra.class).equalTo("idAutor",idAutor).findAll();
        int[] array=new int[result.size()];
        int i;
        for(i=0; i<result.size();i++){
            array[i] =result.get(i).getId();
        }
        return array;
    }

    public String[] getNombresByArrayIdsObras(int[] ids){
        String[] nombres=new String[ids.length];
        int i;
        for(i=0;i< ids.length;i++){
            nombres[i] = getNombreObra(ids[i]);
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
    public  String getImagenAutor(int idAutor){
        RealmResults<Autor> result= realm.where(Autor.class).equalTo("id",idAutor).findAll();
        return result.get(0).getImagen();
    }
    public  Boolean isInAssetsAutor(int idAutor){
        RealmResults<Autor> result= realm.where(Autor.class).equalTo("id",idAutor).findAll();
        return result.get(0).getIsInAssetsAutor();
    }

    public String getDescripcionObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getDescripcion();
    }
    public String getNombreObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getNombre();
    }
    public String getImagenObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getImagen();
    }
    public  Double getLatitud(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getLatitud();
    }
    public  Double getLongitud(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getLongitud();
    }
    public  Boolean isInAssetsObra(int idObra){
        RealmResults<Obra> result= realm.where(Obra.class).equalTo("id",idObra).findAll();
        return result.get(0).getIsInAssetsObra();
    }
    public void initBaseDeDatos(){

           if(realm.isEmpty()){
               //categoria Pintura(id=1)
            addCategoria("Pintura", "ic_cuadro.jpg");
               int idCategoria=1;
                        //autor leonardo da vinci(id=1)
                      String nombreAutor = "Leonardo Da Vinci";
                      String descripcionAutor = "Leonardo da Vinci (Leonardo di ser Piero da Vinci) (Vinci, 15 de abril de 14522 -Amboise, 2 de mayo de 1519) fue " +
                               "un polímata florentino del Renacimiento italiano. Fue a la vez pintor, anatomista, arquitecto, paleontólogo, artista, botánico, " +
                               "científico, escritor, escultor, filósofo, ingeniero, inventor, músico, poeta y urbanista. Murió acompañado de su fiel Francesco" +
                               " Melzi, a quien legó sus proyectos, diseños y pinturas. Tras pasar su infancia en su ciudad natal, Leonardo estudió con el " +
                               "célebre pintor florentino Andrea de Verrocchio. Sus primeros trabajos de importancia fueron creados en Milán al servicio del" +
                               " duque Ludovico Sforza. Trabajó a continuación en Roma, Bolonia y Venecia, y pasó los últimos años de su vida en Francia, " +
                               "por invitación del rey Francisco I.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "leonardo_davinci.jpg",true);
                        int idAutor=1;

                                               String nombreObra="La Última Cena";//id 1
                                               String descripcionObra ="La Última Cena de Leonardo da Vinci" +
                                                       " (Cenacolo Vinciano) es una de las pinturas más famosas del mundo. El mural original, creado entre 1495 y 1497, aún se puede" +
                                                       " contemplar en su primera ubicación, " +
                                                       "la pared del comedor del antiguo convento de los dominicos de Santa Maria delle Grazie." ;
                                               Double latitud=45.4659;
                                               Double longitud=9.1708;
                                               addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "la_ultima_cena.jpg",true);


                                               nombreObra="La Mona Lisa";//id 2
                                               descripcionObra ="El Retrato de Lisa Gherardini, esposa de Francesco del Giocondo, más conocido como " +
                                                       "La Gioconda, también conocida como La Mona Lisa, es una obra pictórica del pintor renacentista italiano Leonardo da Vinci." ;
                                               latitud=48.8606;
                                               longitud=2.3375;
                                               addObra(nombreObra,descripcionObra,idAutor,latitud,longitud,"la_mona_lisa.jpg",true);


                                               nombreObra="La adoración de los magos";//id 3
                                               descripcionObra ="La Adoración de los Magos fue la primera gran obra del pintor renacentista italiano Leonardo" +
                                                       " da Vinci. Está pintado al óleo sobre tabla que mide 246 cm de alto y 243 cm de ancho y data del periodo 1481-1482." ;
                                               latitud=43.7678;
                                               longitud=11.2552;
                                               addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "la_adoracion_de_los_magos.jpg",true);


               //autor Salvador Dali (id=2)
                       nombreAutor = "Salvador Dali";
                       descripcionAutor = "Salvador Felipe Jacinto Dalí i Domènech,1 marqués de Dalí de Púbol (Figueras, 11 de mayo de 1904, 23 de enero de 1989), " +
                               "fue un pintor, escultor, grabador, escenógrafo y escritor español del siglo XX. Se le considera uno de los máximos " +
                               "representantes del surrealismo.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "salvador_dali.jpg",true);
                       idAutor=2;
                                       nombreObra="";//id 4
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                       nombreObra="";//id 5
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                       nombreObra="";//id 6
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);

                      //autor Caravagio(id=3)
                       nombreAutor = "Caravaggio";
                       descripcionAutor = "Michelangelo Merisi da Caravaggio (Milán, 29 de septiembre de 1571-Porto Ércole, 18 de julio de 1610) fue un pintor italiano " +
                               "activo en Roma, Nápoles, Malta y Sicilia entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "caravaggio.jpg",true);
                       idAutor=3;
                                           nombreObra="El Santo Entierro";//id 7
                                           descripcionObra ="El Santo Entierro pintado por Caravaggio, conocido también con otros títulos como Entierro de Cristo, Preparación de Cristo muerto sobre la piedra de unción, Deposición de la cruz o " +
                                                   "Descendimiento de la cruz (en italiano conocido como Deposizione) es una de las obras maestras del citado pintor italiano." ;
                                           latitud=41.5423;
                                           longitud=12.2716;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "el_santo_entierro.jpg",true);


                                           nombreObra="El amor victorioso";//id 8
                                           descripcionObra ="El amor victorioso, es una obra de Caravaggio, pintada en 1602 para Vincenzo Giustiniani, miembro del círculo social del cardenal " +
                                                   "Del Monte. En un diario que data del siglo XVII, el modelo es llamado «Cecco», en italiano, diminutivo de Francesco." ;
                                           latitud=52.5090;
                                           longitud=13.3660;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "el_amor_victorioso.jpg",true);


                                           nombreObra="La negación de San Pedro";//id 9
                                           descripcionObra ="La negación de San Pedro es uno de los últimos cuadros de Caravaggio. Actualmente se exhibe en el Metropolitan " +
                                                   "Museum [Nueva York]. En el claroscuro, una mujer señala con sus dedos a Pedro, mientras que un soldado completa el trío" ;
                                           latitud=40.7792;
                                           longitud=-73.9634;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "la_negacion_de_san_pedro.jpg",true);

                       // autor Vincent van Gogh(id=4)
                       nombreAutor = "Vincent van Gogh";
                       descripcionAutor = "Vincent Willem van Gogh (Vincent van Gogh) (Zundert, Países Bajos, 30 de marzo de 1853-Auvers-sur-Oise, Francia, 29 de julio de 1890) " +
                               "fue un pintor neerlandés, uno de los principales exponentes del postimpresionismo.";
                       addAutor(nombreAutor, descripcionAutor,idCategoria, "vincent_van_gogh.jpg",true);
                       idAutor=4;
                                               nombreObra="Los comederos de patatas";//id 10
                                               descripcionObra ="Los comedores de patatas, Los comedores de papa o Los campesinos comiendo patatas es un cuadro del pintor Vincent " +
                                                       "van Gogh, que creó en abril de 1885 mientras residía en Nuenen, Países Bajos. Se encuentra en el Museo Van Gogh de Ámsterdam. " ;
                                               latitud=52.3583;
                                               longitud=4.8809;
                                               addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "los_comedores_de_patatas.jpg",true);


                                               nombreObra="Los girasoles";//id 11
                                               descripcionObra ="Los girasoles (en francés, Les Tournesols, en neerlandés, Zonnebloemen) es una " +
                                                       "serie de cuadros al óleo realizados por el pintor holandés Vincent van Gogh. " +
                                                       "De la serie hay tres cuadros similares con catorce girasoles en un jarrón, dos con doce" +
                                                       " girasoles, uno con tres y otro con cinco." ;
                                               latitud=48.859;
                                               longitud=11.3416;
                                               addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "los_girasoles.jpg",true);


                                               nombreObra="Autorretrato con la oreja vendada";//id 12
                                               descripcionObra ="Esta imagen muestra a Van Gogh con el fuerte vendaje que le colocaron para curar la autolesión que se produjo en diciembre de 1888" ;
                                               latitud=52.3583;
                                               longitud=4.8809;
                                               addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "autorretrato_con_oreja_vendada.jpg",true);

                        // autor Francisco de goya (id=5)
                       nombreAutor = "Francisco de Goya";
                       descripcionAutor = "Francisco de Goya y Lucientes (Fuendetodos, provincia de Zaragoza, 30 de marzo de 1746-Burdeos, Francia, 16 de abril de 1828) fue " +
                               "un pintor y grabador español. Su obra abarca la pintura de caballete y mural, el grabado y el dibujo. En todas estas facetas desarrolló un " +
                               "estilo que inaugura el Romanticismo. El arte goyesco supone, asimismo, el comienzo de la pintura contemporánea y es precursor de las " +
                               "vanguardias pictóricas del siglo XX; por todo ello, se le considera uno de los artistas españoles más relevantes y uno de los grandes " +
                               "maestros de la historia del arte.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "francisco_de_goya.jpg",true);
                       idAutor=5;
                                           nombreObra="";//id 13
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                           nombreObra="";//id 14
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                           nombreObra="";//id 15
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);

               //categoria Arquitectura(id=2)
            addCategoria("Arquitectura", "ic_arqui.jpg");
               idCategoria=2;
                        //autor LeCorbusier (id=6)
                       nombreAutor = "Le Corbusier";
                       descripcionAutor = "Charles-Édouard Jeanneret-Gris, más conocido, a partir de la década de 1920,1 como Le Corbusier (La Chaux-de-Fonds, Cantón " +
                               "de Neuchâtel, Suiza, 6 de octubre de 1887-Roquebrune-Cap-Martin, Provenza-Alpes-Costa Azul, Francia, 27 de agosto de 1965), " +
                               "fue un arquitecto y teórico de la arquitectura, urbanista, decorador de interiores, pintor, escultor y hombre de letras suizo " +
                               "nacionalizado francés en 1930.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "le_corbusier.jpg",true);
                        idAutor=6;
                                       nombreObra=""; //id 16
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                       nombreObra=""; //id 17
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                       nombreObra=""; //id 18
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);

                        //autor Ludwig...(id=7)
                       nombreAutor = "Ludwig Mies van der Rohe";
                       descripcionAutor = "Ludwig Mies van der Rohe (Aquisgrán, Alemania, 27 de marzo de 1886 – Chicago, Illinois, 17 de agosto de 1969) " +
                               "fue un arquitecto y diseñador industrial. Dirigió la escuela Bauhaus entre 1930 y 1933, año en que fue clausurada.  ";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "ludwig_mies_van_der_rohe.jpg",true);
                       idAutor=7;
                                       nombreObra=""; //id 19
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                       nombreObra=""; //id 20
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                       nombreObra=""; //id 21
                                       descripcionObra ="" ;
                                       latitud=0.0;
                                       longitud=0.0;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);
                        //autor Alexandre ... (id=8)
                       nombreAutor = "Alexandre Gustave Eiffel";
                       descripcionAutor = "Alexandre Gustave Eiffel (Dijon, 15 de diciembre de 1832-París, 27 de diciembre de 1923) fue un ingeniero " +
                               "civil francés. Se graduó en la École centrale des arts et manufactures de París y adquirió renombre diseñando " +
                               "varios puentes para la red francesa de ferrocarriles, de los cuales es especialmente notable el viaducto de " +
                               "Garabit. Su fama actual se debe a su proyecto estrella, la mundialmente conocida torre Eiffel, construida para " +
                               "la Exposición Universal de París de 1889.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "alexandre_gustave_eiffel.jpg",true);
                       idAutor=8;
                                           nombreObra=""; //id 22
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);

                                           nombreObra="";//id 23
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);

                                           nombreObra=""; //id 24
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);
                       //autor Santiago Calatrava (id=9)
                       nombreAutor = "Santiago Calatrava";
                       descripcionAutor = "Santiago Calatrava Valls (Benimámet, 28 de julio de 1951) es un arquitecto, ingeniero civil y escultor " +
                               "español. Entre los premios y reconocimientos que ha recibido destaca el Premio Príncipe de Asturias de las Artes " +
                               "de 1999, el Premio Nacional de Arquitectura de 2005 y el Premio Europeo de Arquitectura de 2015. Actualmente, " +
                               "cuenta con oficinas en Nueva York, Doha y Zúrich.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "calatrava.jpg",true);
                       idAutor=9;
                                           nombreObra=""; //id 25
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                           nombreObra=""; //id 26
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                           nombreObra=""; //id 27
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);
                       // autor Santiago gaudi (id=10)
                       nombreAutor = "Antoni Gaudí";
                       descripcionAutor = "Antoni Gaudí i Cornet, también conocido en español como Antonio Gaudí1 (Reus o Riudoms, 25 de junio de " +
                               "1852-Barcelona, 10 de junio de 1926) fue un arquitecto español, máximo representante del modernismo catalán.";
                       addAutor(nombreAutor, descripcionAutor, idCategoria, "antoni_gaudi.jpg",true);
                       idAutor=10;
                                           nombreObra=""; //id 28
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                           nombreObra=""; //id 29
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


                                           nombreObra=""; //id 30
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "",true);


               //categoria Escultura(id=3)
            addCategoria("Escultura", "ic_escultura.png");
               idCategoria=3;
                        //autor miguel angel(id=11)
                        nombreAutor = "Miguel Ángel";
                        descripcionAutor = "Michelangelo Buonarroti (Caprese, 6 de marzo de 1475-Roma, " +
                                "18 de febrero de 1564), conocido en español como Miguel Ángel, fue un arquitecto, " +
                                "escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia tanto por " +
                                "sus esculturas como por sus pinturas y obra arquitectónica. Desarrolló su labor artística a lo largo de más de " +
                                "setenta años entre Florencia y Roma, que era donde vivían sus grandes mecenas, la familia Médici de Florencia y " +
                                "los diferentes papas romanos.";
                        addAutor(nombreAutor, descripcionAutor, idCategoria, "miguel_angel.jpg",true);
                        idAutor=11;
                                       nombreObra="El David"; //id 31
                                       descripcionObra ="El David es una escultura de mármol blanco de 5,17 metros de altura y 5572 kilogramos de" +
                                               " masa, realizada por Miguel Ángel Buonarroti entre 1501 y 1504 por encargo de la Opera del Duomo de la " +
                                               "catedral de Santa María del Fiore de Florencia. La escultura representa al rey David bíblico en el momento" +
                                               " previo a enfrentarse con Goliat, y fue acogida como un símbolo de la República de Florencia frente a la" +
                                               " hegemonía de sus derrocados dirigentes, los Médici, y la amenaza de los estados adyacentes, especialmente" +
                                               " los Estados Pontificios." ;
                                       latitud=43.4636;
                                       longitud=11.1534;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "el_david.jpg",true);


                                       nombreObra="La Madonna de Brujas"; //id 32
                                       descripcionObra ="La Madonna de Brujas es una escultura realizada en mármol por Miguel Ángel en el año 1504." +
                                               " De 1,23 metros de altura, se encuentra en la iglesia de Nuestra Señora de Brujas (ciudad de la actual Bélgica)." ;
                                       latitud=51.1217;
                                       longitud=3.1328;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "la_madonna_de_brujas.jpg",true);

                                       nombreObra="El Cristo de Minerva";//id 33
                                       descripcionObra ="Cristo de la Minerva es la denominación de una escultura de mármol, obra de Miguel " +
                                               "Ángel, finalizada en 1521, que representa a un Cristo redentor, desnudo, abrazando la Cruz." ;
                                       latitud=41.5353;
                                       longitud=12.2842;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "el_cristo_de_minerva.jpg",true);

                         //autor Verrochio(id =12)
                        nombreAutor = "Verrocchio";
                        descripcionAutor = "Andrea del Verrocchio, nacido Andrea di Michele di Francesco de Cioni, " +
                                "conocido simplemente como Verrocchio (Florencia 1435 - Venecia 1488) fue un pintor, escultor " +
                                "y orfebre cuatrocentista italiano. Trabajó en la corte de Lorenzo de' Medici en Florencia. Entre sus " +
                                "alumnos estuvieron Leonardo da Vinci, Perugino, Ghirlandaio y Sandro Botticelli, pero también influyó en Miguel Ángel. " +
                                "Trabajó en el estilo serenamente clásico del primer renacimiento florentino.";
                        addAutor(nombreAutor, descripcionAutor, idCategoria, "verrocchio.jpg",true);
                        idAutor=12;

                                           nombreObra="Cristo y Santo Tomás"; //id 34
                                           descripcionObra ="blablablablablablablablablablablalblablblablablablablablablablablablablalblablblablablablablablablablablablablalblablblablablablablablablablablablablalblabl" +
                                                   "blablablablablablablablablablablalblablblablablablablablablablablablablalblablblablablablablablablablablablablalblablblablablablablablablablablablablalblabl  " +
                                                   "blablablablablablablablablablablalblablv" ;
                                           latitud=43.7707;
                                           longitud=11.2549;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud,"cristo_y_santo_tomas.jpg",true);

                                           nombreObra="Niño alado con delfín"; //id 35
                                           descripcionObra ="blablablablablablablablablablablalblablblablablablablablablablablablablalblabl" +
                                                   "blablablablablablablablablablablalblablblablablablablablablablablablablalblablblablablablablablablablablablablalblablblablablablablablablablablablablalblabl" +
                                                   "blablablablablablablablablablablalblablblablablablablablablablablablablalblabl" ;
                                           latitud=43.7693;
                                           longitud=11.2560;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "ninyo_alado_con_delfin",true);

                                           nombreObra=""; //id 36
                                           descripcionObra ="" ;
                                           latitud=0.0;
                                           longitud=0.0;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud,"",true);
                        //autor Gian....(id=13)
                        nombreAutor = "Gian Lorenzo Bernini";
                        descripcionAutor = "Gian Lorenzo Bernini (Nápoles, 7 de diciembre de 1598 - Roma, 28 de noviembre de 1680) fue un escultor, arquitecto " +
                                "y pintor italiano. Trabajó principalmente en Roma " +
                                "y es considerado el más destacado escultor de su generación, creador del estilo escultórico barroco.";
                        addAutor(nombreAutor, descripcionAutor, idCategoria, "gian_lorenzo_bernini.jpg",true);
                        idAutor=13;


                                       nombreObra="Apolo y Dafne";//id 37
                                       descripcionObra ="Apolo y Dafne es un relato perteneciente a la mitología griega que a través del " +
                                               "tiempo ha sido narrado por autores helenísticos y romanos en forma de viñeta literaria. " +
                                               "Ovidio relata el mito en el poema Las metamorfosis." ;
                                       latitud=41.9142;
                                       longitud=12.4921;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "apolo_y_dafne.jpg",true);

                                       nombreObra="Busto del rey Luis XIV";//id 38
                                       descripcionObra ="El busto del monarca Luis XIV realizado por el " +
                                               "escultor del barroco Bernini es una de las obras más destacadas del artista; con esta pieza se incorpora al mundo del" +
                                               " arte un nuevo concepto escultórico y resurge la " +
                                               "tipología del busto realista que se había perdido en la etapa renacentista anterior." ;
                                       latitud=48.4816;
                                       longitud=2.0723;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "busto_del_rey_luis_14.jpg",true);

                                       nombreObra="Extásis de Santa Teresa";//id 39
                                       descripcionObra ="El Éxtasis de Santa Teresa también conocido como la Transverberación de Santa " +
                                               "Teresa (en italiano: L'Estasi di Santa Teresa o Santa Teresa in estasi o Transverberazione " +
                                               "di santa Teresa) es un grupo escultórico en mármol obra del escultor y pintor Gian Lorenzo " +
                                               "Bernini, de estilo barroco. " ;
                                       latitud=41.9047;
                                       longitud=12.4942;
                                       addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "extasis_de_santa_teresa.jpg",true);
                        // autor Auguste Rodin(id=14)
                        nombreAutor = "Auguste Rodin";
                        descripcionAutor = "François-Auguste-René Rodin (París, 12 de noviembre de 1840 - Meudon, 17 de noviembre de 1917) fue un escultor " +
                                "francés contemporáneo del impresionismo, y considerado como uno de los \"padres de la escultura moderna\".";
                        addAutor(nombreAutor, descripcionAutor,idCategoria, "auguste_rodin.png",true);
                        idAutor=14;

                                           nombreObra="Le baiser";//id 40
                                           descripcionObra ="El beso de Auguste Rodin representa a Paolo y Francesca da Rimini, personajes históricos " +
                                                   "que vivieron durante el Medioevo. La tragedia que les tocó vivir está narrada por Dante Alighieri " +
                                                   "en la Divina Comedia. Cuñados en vida, los dos fueron asesinados por Gianciotto Malatesta " +
                                                   "(esposo de Francesca y hermano de Paolo), quien los descubrió en un beso adultero." +
                                                   " Rodin decidió representarlos en el momento en que, leyendo las aventuras de Lanzarote del Lago," +
                                                   " se enamoraron y se besaron." ;
                                           latitud=48.8555;
                                           longitud=2.3154;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "le_basier.jpg",true);


                                           nombreObra="El pensador";//id 41
                                           descripcionObra ="El pensador, en su origen, buscaba representar a Dante en La puerta del Infierno. Rodin deseaba mostrar" +
                                                   " en el desnudo de esta escultura a una figura heroica al estilo de Miguel Ángel " +
                                                   "para representar tanto el pensar como la poesía." ;
                                           latitud=48.8555;
                                           longitud=2.3154;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "el_pensador.jpg",true);


                                           nombreObra="La puerta del Infierno"; //id 42
                                           descripcionObra ="La puerta del Infierno es un grupo escultórico monumental creado por e" +
                                                   "l artista francés Auguste Rodin, con la colaboración de la escultura francesa Camille Claudel, entre 1880 y 1917." ;
                                           latitud=48.8555;
                                           longitud=2.3154;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "la_puerta_del_infierno.jpg",true);
                        // autor Donatello (id=15)
                        nombreAutor = "Donatello";
                        descripcionAutor = "Donato di Niccolò di Betto Bardi, conocido como Donatello (Florencia, Italia, 1386-Ibídem, 13 de diciembre de 1466), " +
                                "fue un artista y escultor italiano de principios del Renacimiento, uno de los padres del periodo junto con Leon Battista " +
                                "Alberti, Brunelleschi y Masaccio. Donatello se convirtió en una fuerza innovadora en el campo de la escultura monumental y " +
                                "en el tratamiento de los relieves, donde logró representar una gran profundidad " +
                                "dentro de un mínimo plano, denominándose con el nombre de stiacciato, es decir «relieve aplanado o aplastado».";
                        addAutor(nombreAutor, descripcionAutor, idCategoria, "donatello.jpg",true);
                        idAutor=15;

                                           nombreObra="Anunciación Cavalcanti";//id 43
                                           descripcionObra ="La Anunciación Cavalcanti de Donatello es una escultura en piedra arenisca" +
                                                   " gris, en algunas partes policromada y dorada. Tiene una medida de 218 x 168 cm." +
                                                   " Se encuentra en la nave derecha de la basílica de Santa Cruz (Florencia)." +
                                                   " Datada alrededor de 1435 es una de las raras obras del gran escultor que se encuentra todavía en su lugar original." ;
                                           latitud=43.7685;
                                           longitud=11.2622;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "anunciacion_cavalcanti.jpg",true);

                                           nombreObra="San Juan Evangelista"; //id 44
                                           descripcionObra ="San Juan Evangelista es una escultura de mármol de Donatello " +
                                                   "(210x88x54 cm) esculpida para la antigua fachada del Duomo de Florencia y en la actualidad " +
                                                   "se conserva en el Museo dell'Opera del Duomo. Data de 1409-1411 y fue el modelo más directo para el Moisés de Miguel Ángel." ;
                                           latitud=43.7732;
                                           longitud=11.2579;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "san_juan_evangelista.jpg",true);

                                           nombreObra="San Marcos"; //id 45
                                           descripcionObra ="La escultura de San Marcos de Donatello forma parte del ciclo de catorce " +
                                                   "estatuas de los protectores de las Artes de Florencia, " +
                                                   "colocadas en nichos en el exterior de la Iglesia de Orsanmichele." ;
                                           latitud=43.7707;
                                           longitud=11.2549;
                                           addObra(nombreObra,descripcionObra,idAutor,latitud,longitud, "san_marcos.jpg",true);


           }
        }


  //  }

// crear categoria, no se añaden autores por que voy a hacer que un autor solo pueda pertenecer a una categoria
// por lo que se especifica al crear el autor, es mucho mas simple asi, si lo quieren hacer que un autor pertenezca a varias categoria es mas lio
    public void addCategoria(String nombre,String imagen){
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
        categoria.setImagen(imagen);
        realm.commitTransaction();
    }

// crear autor nombre, descripcion, idem que en lo anterior para las obras
    public void addAutor(String nombre,String descripcion,int idCategoria, String imagen,Boolean isInAssets){
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
        autor.setImagen(imagen);
        autor.setIdCategoria(idCategoria);
        autor.setIsInAssetsAutor(isInAssets);
        realm.commitTransaction();
    }
// crear obra
    public void addObra(String nombre,String descripcion,int idAutor,Double latitud, Double longitud,String imagen,Boolean isInAssets){
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
        obra.setLatitud(latitud);
        obra.setLongitud(longitud);
        obra.setImagen(imagen);
        obra.setIdAutor(idAutor);
        obra.setIsInAssetsObra(isInAssets);
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

    //pasar el archivo que entra a un bitmap
    public Bitmap imagenToBitmap (String fileName){
        File file = new File(fileName);
        Bitmap imagen = BitmapFactory.decodeFile(file.getAbsolutePath());
        return imagen;

    }

}
