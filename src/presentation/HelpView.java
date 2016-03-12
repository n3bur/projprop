package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Frame containing a simple help view.
 * @author Pau Oliver
 */
public class HelpView extends JFrame {

    private JFrame frameVista = new JFrame("Ajuda");
    private JLabel title = new JLabel("Ajuda", SwingConstants.CENTER);
    private JPanel contentsPanel = new JPanel();
    private JPanel informationPanel = new JPanel();
    private JTextArea informationTextArea = new JTextArea(32, 50);


    public HelpView() {
        initializeComponents();
    }

    /**
     * Make the frame visible
     */
    public void makeVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    /**
     * Make the frame invisible
     */
    public void makeInvisible() {
        frameVista.setVisible(false);
    }

    /**
     * Initialize the main components
     */
    private void initializeComponents() {
        initializeFrameView();
        initializeContentsPanel();
        initializeInformationPanel();
    }

    /**
     * Initialize the view
     */
    private void initializeFrameView() {
        frameVista.setMinimumSize(new Dimension(900, 700));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(contentsPanel);
    }

    /**
     * Initialize the ContentsPanel (main container)
     */
    private void initializeContentsPanel() {
        // Layout
        contentsPanel.setLayout(new BorderLayout());
        // Paneles
        contentsPanel.add(informationPanel, BorderLayout.CENTER);
    }

    /**
     * Initialize InformationPanel, the panel inside the ContentsPanel
     */
    private void initializeInformationPanel() {

        JTextArea textArea = informationTextArea;
        textArea.setText(text);
        textArea.setEditable(false);
        textArea.setFont(new Font(null, Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        informationPanel.add(areaScrollPane);
    }

    private final static String sep = "____________\n\n";
    private final static String text =
                    "\n1 ) Gestionar Wikipedia\n\n" +
                    "A la finestra de gestió de Wikipedia podràs:\n" +
                    "   - Crear una nova Wikipedia en blanc. Després de posar-li nom, s'haurà creat una wikipedia en blanc.\n" +
                    "   - Carregar una Wikipedia des de fitxer.\n" +
                    "   - Canviar l'id de la Wikipedia sobre la que estàs treballant.\n" +
                    "   - Modificar la wikipedia manualment. S'obre la pantalla de modificació manual, que funciona de la següent manera:\n" +
                            "         o Tipus de modificació. Escull quina modificació vols fer. En l'ordre mostrat, són: Afegir categoria, Eliminar categoria, Afegir una pàgina a una categoria, Eliminar una pàgina d'una categoria, Afegir una relació 'A és subcategoria de B', Eliminar una relació AsubB, Afegir una relació 'A és supercategoria de B', Eliminar una relació AsupB.\n" +
                            "         o Node origen. Cal escriure el nom de la categoria sobre la qual es vol realitzar la modificació.\n" +
                            "         o Node destí. En aquelles modificacions que siguin de relació entre dos elements, cal escriure el nom del segon element (una categoria o pàgina).\n" +
                    "   - Modificar la wikipedia des de fitxer. Permet afegir noves relacions i categories a la wikipedia carregada, a partir d'un fitxer en el format vàlid.\n" +
                    "   - Consultar tota la wikipedia. A l'esquerra es mostren totes les categories que conté la Wikipedia que hi ha carregada. A la barra inferior es pot escriure el nom d'una categoria a cercar." +
                    " Si se selecciona una categoria, a la meitat dreta es poden veure els elements amb què està relacionada: les seves subcategories, supercategories i les pàgines que conté. La barra de cerca de cada secció funciona igual que la de categories. Els botons d'afegir i treure permeten fer l'acció que el seu nom indica sobre l'element seleccionat o escrit a la barra." +
                    "   - Consulta avançada de la Wikipedia. Permet realitzar totes les accions de consulta possibles dels elements que formen la wikipedia que es té carregada.\n" +
                    "   - Esborrar una Wikipedia de disc. Elimina alguna wikipedia que tinguis guardada a l'ordinador.\n" +
                    "   - Guardar la Wikipedia. Desa la wikipedia carregada per a poder seguir-hi treballant en el futur.\n" +
                    sep +
                    "2) Generar Solució\n\n" +
                    " En aquesta finestra es pot posar en marxa una cerca de categories semblants sobre la wikipedia.\n" +
                    "Per a definir què significa 'ser semblants', es pot escollir quins criteris tenir en compte i en quina mesura. La importància d'un criteri és relativa a la dels altres criteris.\n" +
                    "És a dir, el pes d'un criteri es calcula com Valor_slider/Valor_total_sliders.\n" +
                    "A continuació cal escollir quin algorisme es vol utilitzar per al càlcul: Girvan-Newman, Clique o Louvain. Finalment, el botó Cerca començarà l'execució.\n\n" +
                    sep +
                    "3) Gestionar solucions.\n\n" +
                    " Permet: veure les solucions generades, importar-ne d'altres que estiguin al disc, canviar el nom a una solució, veure solucions gràficament i modificar-les.\n " +
                    " La visualització gràfica genera una imatge de les categories agrupades en comunitats. Les arestes mostrades són orientatives i no corresponen a les trobades durant l'execució." +
                    " Modificar una solució consisteix en decidir a quina comunitat es vol traslladar una categoria.\n\n" +
                    sep +
                    "4) Estadístiques. \n\n" +
                    " Permet veure estadístiques sobre les solucions generades. La gràfica indica, per als algorismes seleccionats, quant s'ha trigat en obtenir les solucions en funció del nombre de categories trobades. \n\n" +
                    sep + " PROP QP 2015. Crèdits: \n Rubén Marías, Oriol Muñoz, Pau Oliver, Aleix Pellisa\n";
}
