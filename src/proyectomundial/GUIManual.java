package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {

    private String paginaVisitada;

    SeleccionDAO seleccionDAO = new SeleccionDAO();

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    private JPanel JPanelMenuAuditoria;
    private JLabel btnAuditoria;

    private JButton btnreiniciarVisitas;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {
        paginaVisitada = null;

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        JPanelMenuAuditoria = new JPanel();
        btnAuditoria = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();

        pintarMenuAuditoria();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        paginaVisitada = "Home";
        seleccionDAO.ActualizarVistas(paginaVisitada);
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void pintarMenuAuditoria() {
        btnAuditoria.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnAuditoria.setText("Auditoria");
        btnAuditoria.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioAuditoria = new JLabel();
        JPanelMenuAuditoria.setBackground(new java.awt.Color(17, 41, 63));
        JPanelMenuAuditoria.setPreferredSize((new java.awt.Dimension(220, 35)));
        JPanelMenuAuditoria.setLayout(new BorderLayout(15, 0));
        JPanelMenuAuditoria.add(vacioAuditoria, BorderLayout.WEST);
        JPanelMenuAuditoria.add(btnAuditoria, BorderLayout.CENTER);
        jPanelMenu.add(JPanelMenuAuditoria);

        btnAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Auditoria");
                accionAuditoria();
            }
        });
    }

    private void accionAuditoria() {
        jLabelTop.setText("Auditoria");
        List<Seleccion> selecciones = seleccionDAO.getSelecciones();

        String Datos[][] = seleccionDAO.getVisitasMatriz();

        String Columnas[] = {"Pagina", "Veces Visitada"};

        JTable visitasOpciones = new JTable(tablasNOeditables(Datos, Columnas));
        
        colorearTablas(visitasOpciones);

        visitasOpciones.setRowHeight(30);

        JButton btnreiniciarVisitas = new JButton();
        btnreiniciarVisitas.setText("Reiniciar Visitas");

        btnreiniciarVisitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                seleccionDAO.reiniciarVisitas();
                accionAuditoria();
            }
        });

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 199)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(visitasOpciones);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        seleccionesPanel.add(btnreiniciarVisitas);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        paginaVisitada = "Selecciones";
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();
        seleccionDAO.ActualizarVistas(paginaVisitada);

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        paginaVisitada = "Resultados";
        jLabelTop.setText("Resultados");
        resultados = seleccionDAO.getResultadosMatriz();
        seleccionDAO.ActualizarVistas(paginaVisitada);
        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    
   

    private void accionDashboardSel() {
        paginaVisitada = "Dash Selecciones";
        jLabelTop.setText("Dash Selecciones");
        List<Seleccion> selecciones = seleccionDAO.getSelecciones();
        seleccionDAO.ActualizarVistas(paginaVisitada);

        int TotalSelecciones = selecciones.size();
        int nacionalidades = seleccionDAO.getNacionalidades().size();

        String Datos[][] = seleccionDAO.CantidadSelecciones(selecciones);
        String Datos2[][] = seleccionDAO.getNacionalidadesMatriz();

        JLabel num1 = new JLabel();
        JLabel num2 = new JLabel();
        JLabel num3 = new JLabel();
        num1.setText("Total de selecciones:  " + TotalSelecciones);
        num2.setText("Numero de Selecciones por continente:");
        num3.setText("Cantidad Nacionalidades de los directores: " + nacionalidades);

        String Columnas[] = {"America del sur", "America del norte", "America Central", "África", "Asia", "Europa"};
        String Columnas2[] = {"Técnico", "Nacionalidades"};
        JTable CantidadSelecciones = new JTable(tablasNOeditables(Datos, Columnas));
        //pinta las tablas
        colorearTablas(CantidadSelecciones);

        JTable CantidadNacionalidades = new JTable(tablasNOeditables(Datos2, Columnas2));
        //pinta las tablas
        colorearTablas(CantidadNacionalidades);

        CantidadSelecciones.setRowHeight(30);
        CantidadNacionalidades.setRowHeight(30);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 100)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JPanel rankingPanel = new JPanel();
        rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.X_AXIS));
        rankingPanel.setPreferredSize((new java.awt.Dimension(620, 300)));
        rankingPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(CantidadSelecciones);
        JScrollPane scrollPane2 = new JScrollPane(CantidadNacionalidades);

        seleccionesPanel.add(num1);
        seleccionesPanel.add(num2);
        seleccionesPanel.add(scrollPane);
        seleccionesPanel.add(num3);
        rankingPanel.add(scrollPane2);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.add(rankingPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        paginaVisitada = "Dash Resultados";
        btnDashboardRes.setText("Dash Resultados");
        jLabelTop.setText("Dash Resultados");
        seleccionDAO.ActualizarVistas(paginaVisitada);

        //Se organizan los diferentes datos que se piden ya sean en Jlabels o en tablas.
        int partidosCargados = seleccionDAO.getResultados().size();
        String[] mastchMasGoles = new String[3];
        String[] mastchMenosGoles = new String[3];

        int golesTotales = 0;
        int golesLocalTotales = 0;
        int golesVisitanteTotales = 0;

        int partidoMasgoles = 0;
        int partidoMenosgoles = 0;
        int auxMaxGoles = 0;
        int numeroPartidosEmpatados = 0;
        int numeroPartidosGanador = 0;

        String Datos[][] = seleccionDAO.getMatrizRankingGoles();

        String Datos3[][] = seleccionDAO.getMatrizRankingEquipos();

        String Datos4[][] = seleccionDAO.getMatrizContinentesGoles();

        String Datos2[][] = seleccionDAO.getResultadosMatriz();

        String Datos5[][] = seleccionDAO.getMatrizClasificados();

        String Columnas[] = {"Equipos", "Goles Totales"};

        String Columnas2[] = {"Equipos", "Puntos"};

        String Columnas3[] = {"Continentes", "Goles"};

        String Columnas4[] = {"Equipo Clasificado", "Grupo", "Puntos"};

        //Colores para que se vea mejor.
        Color blanco = new Color(239, 239, 239);
        Color azulOscuro = new Color(0, 24, 47);
        Color azulClarito = new Color(18, 119, 217);

        JTable table = new JTable(tablasNOeditables(Datos, Columnas));
        //Pinta como tal a las diferentes tablas
        colorearTablas(table);

        JTable table2 = new JTable(tablasNOeditables(Datos3, Columnas2));
        //Pinta como tal a las diferentes tablas
        colorearTablas(table2);
        
        JTable table3 = new JTable(tablasNOeditables(Datos4, Columnas3));
        //Pinta como tal a las diferentes tablas
        colorearTablas(table3);

        JTable table4 = new JTable(tablasNOeditables(Datos5, Columnas4));
        //Pinta como tal a las diferentes tablas
        colorearTablas(table4);
        
        
        //tablas se convierten a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        JScrollPane scrollPane3 = new JScrollPane(table3);
        JScrollPane scrollPane4 = new JScrollPane(table4);

        for (int i = 0; i < Datos2.length; i++) {
            golesLocalTotales += Integer.parseInt(Datos2[i][5]);

            golesVisitanteTotales += Integer.parseInt(Datos2[i][6]);

            int partidoActual = Integer.parseInt(Datos2[i][5]) + Integer.parseInt(Datos2[i][6]);

            int golesLocal = Integer.parseInt(Datos2[i][5]);
            int golesVisitante = Integer.parseInt(Datos2[i][6]);

            auxMaxGoles = partidoMasgoles;

            //Calcula el partido con mas goles
            if (partidoActual >= (auxMaxGoles)) {

                partidoMasgoles = partidoActual;
                mastchMasGoles[0] = Datos2[i][0];
                mastchMasGoles[1] = Datos2[i][1];
                mastchMasGoles[2] = Datos2[i][2];

            }
            //Calcula el partido con menos goles
            int auxMinGoles = partidoMenosgoles;

            if (partidoActual <= (auxMinGoles)) {

                partidoMenosgoles = partidoActual;
                mastchMenosGoles[0] = Datos2[i][0];
                mastchMenosGoles[1] = Datos2[i][1];
                mastchMenosGoles[2] = Datos2[i][2];

            }

            //Calcula partidos con ganador y empatados
            if (golesLocal != golesVisitante) {
                numeroPartidosGanador++;
            }
            if (golesLocal == golesVisitante) {
                numeroPartidosEmpatados++;
            }

        }

        golesTotales = golesLocalTotales + golesVisitanteTotales;

        int promedioGoles = golesTotales / partidosCargados;
        String equipoMasgoles[] = new String[2];
        equipoMasgoles[0] = Datos[0][0];

        String continenteMasgoles[] = new String[2];
        continenteMasgoles[0] = Datos4[0][0];

        //Se ponen todos los datos en Jlabels para luego mostrarlos en JPanels (derecho e izquierdo)
        JLabel PartidosCargados = new JLabel("Partidos cargados: " + partidosCargados);
        JLabel PromedioGoles = new JLabel("El promedio de goles es: " + promedioGoles);
        JLabel PartidoMasGoles = new JLabel("Partido mas goles: " + mastchMasGoles[1] + " vs " + mastchMasGoles[2]);
        JLabel PartidoMenosGoles = new JLabel("Partido menos goles: " + mastchMenosGoles[1] + " vs " + mastchMenosGoles[2]);
        JLabel PartidoGanados = new JLabel("Cantidad de partidos ganados: " + numeroPartidosGanador);
        JLabel PartidoEmpate = new JLabel("Cantidad de partidos empatados es: " + numeroPartidosEmpatados);
        JLabel EquipoMasGoles = new JLabel("El equipo con mas goles fue: " + equipoMasgoles[0]);
        JLabel ContinenteMasGoles = new JLabel("El continente con mas goles fue: " + continenteMasgoles[0]);

        //Se les asigna el color blanco los JLabel
        PartidosCargados.setForeground(blanco);
        PartidoMasGoles.setForeground(blanco);
        PartidoEmpate.setForeground(blanco);
        EquipoMasGoles.setForeground(blanco);
        PromedioGoles.setForeground(blanco);
        PartidoMenosGoles.setForeground(blanco);
        PartidoGanados.setForeground(blanco);
        ContinenteMasGoles.setForeground(blanco);

        //Se crean Jpanels para contener las diferentes tablas.
        JPanel seleccionesPanel = new JPanel();
        GridLayout gridlayout = new GridLayout(1, 2);
        gridlayout.setVgap(10);
        gridlayout.setHgap(10);
        seleccionesPanel.setLayout(gridlayout);
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 125)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());
        Border borderseleccionesPanel = BorderFactory.createLineBorder(azulClarito, 2);
        seleccionesPanel.setBackground(azulOscuro);
        seleccionesPanel.setBorder(borderseleccionesPanel);
        seleccionesPanel.add(scrollPane);
        seleccionesPanel.add(scrollPane2);
        seleccionesPanel.add(scrollPane3);

        JPanel seleccionesPanel2 = new JPanel();
        seleccionesPanel2.setLayout(new BoxLayout(seleccionesPanel2, BoxLayout.X_AXIS));
        seleccionesPanel2.setPreferredSize((new java.awt.Dimension(620, 190)));
        seleccionesPanel2.setMaximumSize(jPanelRight.getPreferredSize());
        Border borderseleccionesPanel2 = BorderFactory.createLineBorder(azulClarito, 2);
        seleccionesPanel2.setBackground(azulOscuro);
        seleccionesPanel2.setBorder(borderseleccionesPanel2);
        seleccionesPanel2.add(scrollPane4);

        JPanel labelsizq = new JPanel();
        labelsizq.setPreferredSize((new java.awt.Dimension(310, 90)));
        labelsizq.setMaximumSize(jPanelRight.getPreferredSize());
        Border border = BorderFactory.createLineBorder(azulClarito, 2);

        //Se le agrega color al panel izquierdo y al borde
        labelsizq.setBackground(azulOscuro);
        labelsizq.setBorder(border);

        labelsizq.add(PartidosCargados);
        labelsizq.add(PartidoMasGoles);
        labelsizq.add(PartidoMenosGoles);
        labelsizq.add(PartidoEmpate);
        labelsizq.add(EquipoMasGoles);

        JPanel labelsder = new JPanel();
        labelsder.setPreferredSize((new java.awt.Dimension(310, 90)));
        labelsder.setMaximumSize(jPanelRight.getPreferredSize());

        //Se le agrega color al borde y al panel derecho
        labelsder.setBackground(azulOscuro);
        Border border2 = BorderFactory.createLineBorder(azulClarito, 2);
        labelsder.setBorder(border2);
        labelsder.add(PromedioGoles);
        labelsder.add(PartidoMenosGoles);
        labelsder.add(PartidoGanados);
        labelsder.add(ContinenteMasGoles);

        //Se crea el panel q contiene los paneles de los Jlabels y se le da color
        JPanel containsLabels = new JPanel();
        containsLabels.setPreferredSize((new java.awt.Dimension(620, 90)));
        GridLayout layoutgrid = new GridLayout(1, 1, 0, 0);
        layoutgrid.setHgap(15);
        containsLabels.setLayout(layoutgrid);
        containsLabels.add(labelsizq);
        containsLabels.add(labelsder);

        jPanelMain.removeAll();
        jPanelMain.add(containsLabels, BorderLayout.PAGE_START);
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.add(seleccionesPanel2, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();
            ArrayList<Seleccion> listaSelecciones = new ArrayList<>();
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");
                Seleccion nuevaSeleccion = new Seleccion(columns[0], columns[1], columns[2], columns[3]);
                listaSelecciones.add(nuevaSeleccion);
                System.out.println("Seleccion " + nuevaSeleccion.getNombre() + " registrada");
            }

            selecciones = seleccionDAO.getSeleccionesMatrizCSV(listaSelecciones);
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    private String text = "";
    private boolean isFocus = false;

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(tablasNOeditables(selecciones, columnNames));
        colorearTablas(table);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                text = field.getText();

                String[][] resultado = new String[0][0];

                resultado = seleccionDAO.getMatrizBusquedaSelecciones(field);
                System.out.println("si funciona la busqueda");

                if (resultado != null) {
                    if (resultado.length > 0) {
                        selecciones = resultado;
                    }
                } else {
                    selecciones = new String[0][0];
                    System.out.println("No hay nada.");
                }

                pintarTablaSelecciones();

            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                selecciones = seleccionDAO.getSeleccionesMatriz();

                pintarTablaSelecciones();

            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            System.out.println(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[49][8];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                System.out.println(i);
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                for (int j = 0; j < columns.length; j++) {
                    resultados[i][j] = columns[j];
                }
                i++;
            }

            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(tablasNOeditables(resultados, columnNames));
        colorearTablas(table);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                text = field.getText();

                String[][] resultado = new String[0][0];

                resultado = seleccionDAO.getBusquedaResultadosMatriz(field);

                if (resultado != null) {
                    if (resultado.length > 0) {
                        resultados = resultado;
                    }
                } else {
                    resultados = new String[0][0];
                    System.out.println("No hay nada.");
                }

                pintarTablaResultados();

            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                resultados = seleccionDAO.getResultadosMatriz();

                pintarTablaResultados();

            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }
    
    //Este metodo hace que las diferentes tablas del proyecto no se puedan editar
    private DefaultTableModel tablasNOeditables(String Dato[][],String Columnas[]) {
        
        DefaultTableModel tabla = new DefaultTableModel(Dato, Columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return tabla;
        
    }
    
     //Este metodo colorea las tablas
    private void colorearTablas(JTable tabla) {

        //Colores para que se vea mejor.
        Color blanco = new Color(239, 239, 239);
        Color azulOscuro = new Color(0, 24, 47);
        Color azulClarito = new Color(18, 119, 217);
        Color azulpoquitoClarito = new Color(17, 41, 63);


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                // Llamar al método padre para obtener el componente por defecto
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Establecer colores personalizados para las celdas
                if (row % 2 == 0) { // Fila par
                    component.setBackground(azulpoquitoClarito);
                } else { // Fila impar
                    component.setBackground(azulOscuro);
                }

                // Establecer el color de texto
                component.setForeground(blanco);

                return component;
            }
        };

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
