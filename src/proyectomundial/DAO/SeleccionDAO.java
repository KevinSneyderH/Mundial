/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import proyectomundial.model.Seleccion;

import proyectomundial.util.BasedeDatos;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO k_hernandez8.seleccion (nombre, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public boolean registrarResultado(Seleccion resultado) {

        String sql = "INSERT INTO k_hernandez8.partidos (nombre, continente, dt, nacionalidad) values("
                + "'" + resultado.getGrupo() + "', "
                + "'" + resultado.getLocal() + "', "
                + "'" + resultado.getVisitante() + "', "
                + "'" + resultado.getContinente_l() + "')"
                + "'" + resultado.getContinente_v() + "')"
                + "'" + resultado.getGoles_l() + "')"
                + "'" + resultado.getGoles_v() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public boolean ActualizarVistas(String pagina) {

        String sql = "update k_hernandez8.auditoria set contador = contador + 1 where pagina = '" + pagina + "'";
        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public boolean reiniciarVisitas() {

        String sql = "update k_hernandez8.auditoria set contador = 0 where pagina in ('Home', 'Selecciones', 'Dash Resultados', 'Dash Selecciones', 'Resultados')";
        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> getRankingGoles() {

        String sql = "SELECT equipo, SUM(total_goles) AS goles_totales\n"
                + "FROM\n"
                + "(\n"
                + "    SELECT \"local\" AS equipo, goles_local AS total_goles\n"
                + "    FROM k_hernandez8.partidos\n"
                + "    \n"
                + "    UNION ALL\n"
                + "    \n"
                + "    SELECT visitante AS equipo, goles_visitante AS total_goles\n"
                + "    FROM k_hernandez8.partidos\n"
                + ") subquery\n"
                + "GROUP BY equipo\n"
                + "ORDER BY goles_totales DESC;";

        List<Seleccion> equipos = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion equipoGoles = new Seleccion(result.getString("equipo"), Integer.parseInt(result.getString("goles_totales")));
                    equipos.add(equipoGoles);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return equipos;
    }

    public List<Seleccion> getRankingEquipos() {

        String sql = "SELECT equipo, SUM(puntos) AS total_puntos\n"
                + "FROM\n"
                + "(\n"
                + "    SELECT \"local\" AS equipo,\n"
                + "           CASE\n"
                + "               WHEN goles_local > goles_visitante THEN 3\n"
                + "               WHEN goles_local = goles_visitante THEN 1\n"
                + "               ELSE 0\n"
                + "           END AS puntos\n"
                + "    FROM k_hernandez8.partidos\n"
                + "    \n"
                + "    UNION ALL\n"
                + "    \n"
                + "    SELECT visitante AS equipo,\n"
                + "           CASE\n"
                + "               WHEN goles_visitante > goles_local THEN 3\n"
                + "               WHEN goles_visitante = goles_local THEN 1\n"
                + "               ELSE 0\n"
                + "           END AS puntos\n"
                + "    FROM k_hernandez8.partidos\n"
                + ") AS equipos_puntos\n"
                + "GROUP BY equipo\n"
                + "ORDER BY total_puntos DESC;";

        List<Seleccion> equipos = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion equipoGoles = new Seleccion(result.getString("equipo"), Integer.parseInt(result.getString("total_puntos")));
                    equipos.add(equipoGoles);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return equipos;
    }

    public List<Seleccion> getClasificados() {

        String sql = "WITH equipos_puntos_ranked AS (\n"
                + "    SELECT equipo, grupo, total_puntos,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY grupo ORDER BY total_puntos DESC) AS ranking\n"
                + "    FROM (\n"
                + "        SELECT equipo, grupo, SUM(puntos) AS total_puntos\n"
                + "        FROM (\n"
                + "            SELECT \"local\" AS equipo, grupo,\n"
                + "                   CASE\n"
                + "                       WHEN goles_local > goles_visitante THEN 3\n"
                + "                       WHEN goles_local = goles_visitante THEN 1\n"
                + "                       ELSE 0\n"
                + "                   END AS puntos\n"
                + "            FROM k_hernandez8.partidos\n"
                + "\n"
                + "            UNION ALL\n"
                + "\n"
                + "            SELECT visitante AS equipo, grupo,\n"
                + "                   CASE\n"
                + "                       WHEN goles_visitante > goles_local THEN 3\n"
                + "                       WHEN goles_visitante = goles_local THEN 1\n"
                + "                       ELSE 0\n"
                + "                   END AS puntos\n"
                + "            FROM k_hernandez8.partidos\n"
                + "        ) AS equipos_puntos\n"
                + "        GROUP BY equipo, grupo\n"
                + "    ) AS equipos_puntos_total\n"
                + ")\n"
                + "SELECT equipo, grupo, total_puntos\n"
                + "FROM equipos_puntos_ranked\n"
                + "WHERE ranking <= 2\n"
                + "ORDER BY grupo, total_puntos DESC;";

        List<Seleccion> equipos = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {

                    Seleccion equipoGoles = new Seleccion(result.getString("equipo"), result.getString("grupo"), Integer.parseInt(result.getString("total_puntos")));
                    equipos.add(equipoGoles);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return equipos;
    }

    public List<Seleccion> getContinentesGoles() {

        String sql = "SELECT continente, SUM(total_goles) AS goles_totales\n"
                + "FROM\n"
                + "(\n"
                + "    SELECT continente_local AS continente, goles_local AS total_goles\n"
                + "    FROM k_hernandez8.partidos\n"
                + "    \n"
                + "    UNION ALL\n"
                + "    \n"
                + "    SELECT continente_visitante AS continente, goles_visitante AS total_goles\n"
                + "    FROM k_hernandez8.partidos\n"
                + ") AS goles_continentes\n"
                + "GROUP BY continente;";

        List<Seleccion> equipos = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion equipoGoles = new Seleccion(result.getString("continente"), Integer.parseInt(result.getString("goles_totales")));
                    equipos.add(equipoGoles);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return equipos;
    }

    public List<Seleccion> getNacionalidades() {

        String sql = "select distinct nacionalidad, dt from k_hernandez8.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("dt"), result.getString("nacionalidad"), result.getString("dt"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getVisitas() {

        String sql = "select distinct pagina, contador  from k_hernandez8.auditoria";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("pagina"), result.getString("contador"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT distinct nombre, continente, dt, nacionalidad FROM k_hernandez8.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getResultados() {

        String sql = "SELECT grupo, \"local\", visitante, continente_local, continente_visitante, goles_local, goles_visitante FROM k_hernandez8.partidos ";

        List<Seleccion> busqueda = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    busqueda.add(seleccion);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return busqueda;

    }

    public List<Seleccion> getBusquedaSelecciones(JTextField field) {

        String sql = "SELECT DISTINCT s.* FROM k_hernandez8.seleccion s WHERE s.nombre ILIKE '" + field.getText() + "%';";

        List<Seleccion> busqueda = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    busqueda.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return busqueda;

    }

    public List<Seleccion> getBusquedaResultados(JTextField field) {
        String sql = null;

        if (field.getText().length() <= 1) {
            sql = "select *from k_hernandez8.partidos s where grupo ilike '" + field.getText() + "%';";
        } else {
            sql = "select *from k_hernandez8.partidos s where \"local\" ilike '" + field.getText() + "%' or visitante ilike '" + field.getText() + "%'";
        }

        List<Seleccion> busqueda = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    busqueda.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return busqueda;

    }

    public String[][] getMatrizBusquedaSelecciones(JTextField field) {

        String[][] matrizbusqueda = null;
        List<Seleccion> selecciones = getBusquedaSelecciones(field);

        if (selecciones != null && selecciones.size() > 0) {

            matrizbusqueda = new String[selecciones.size()][4];

            try {
                int x = 0;
                for (Seleccion seleccion : selecciones) {

                    matrizbusqueda[x][0] = seleccion.getNombre();
                    matrizbusqueda[x][1] = seleccion.getContinente();
                    matrizbusqueda[x][2] = seleccion.getDt();
                    matrizbusqueda[x][3] = seleccion.getNacionalidad();
                    x++;
                }
            } catch (Exception e) {

                System.out.println("ERROR");
                String[][] empty = new String[0][0];
                return empty;
            }
        }

        return matrizbusqueda;
    }

    public String[][] getMatrizRankingGoles() {
        List<Seleccion> selecciones = getRankingGoles();

        String[][] RankingGoles = null;

        if (selecciones != null && selecciones.size() > 0) {
            RankingGoles = new String[selecciones.size()][2];

            try {
                int x = 0;
                for (Seleccion seleccion : selecciones) {
                    RankingGoles[x][0] = seleccion.getEquipo();
                    RankingGoles[x][1] = String.valueOf(seleccion.getGoles());
                    x++;
                }
            } catch (Exception e) {
                System.out.println("ERROR");
                RankingGoles = new String[0][0];
            }
        }

        return RankingGoles;
    }

    public String[][] getMatrizClasificados() {
        List<Seleccion> selecciones = getClasificados();

        String[][] RankingGoles = null;

        if (selecciones != null && selecciones.size() > 0) {
            RankingGoles = new String[selecciones.size()][3];

            try {
                int x = 0;
                for (Seleccion seleccion : selecciones) {
                    RankingGoles[x][0] = seleccion.getEquipo();
                    RankingGoles[x][1] = seleccion.getGrupo();
                    RankingGoles[x][2] = String.valueOf(seleccion.getGoles());
                    x++;
                }
            } catch (Exception e) {
                System.out.println("ERROR");
                RankingGoles = new String[0][0];
            }
        }

        return RankingGoles;
    }

    public String[][] getMatrizRankingEquipos() {
        List<Seleccion> selecciones = getRankingEquipos();

        String[][] RankingGoles = null;

        if (selecciones != null && selecciones.size() > 0) {
            RankingGoles = new String[selecciones.size()][2];

            try {
                int x = 0;
                for (Seleccion seleccion : selecciones) {
                    RankingGoles[x][0] = seleccion.getEquipo();
                    RankingGoles[x][1] = String.valueOf(seleccion.getGoles());
                    x++;
                }
            } catch (Exception e) {
                System.out.println("ERROR");
                RankingGoles = new String[0][0];
            }
        }

        return RankingGoles;
    }

    public String[][] getMatrizContinentesGoles() {
        List<Seleccion> selecciones = getContinentesGoles();

        String[][] RankingGoles = null;

        if (selecciones != null && selecciones.size() > 0) {
            RankingGoles = new String[selecciones.size()][2];

            try {
                int x = 0;
                for (Seleccion seleccion : selecciones) {
                    RankingGoles[x][0] = seleccion.getEquipo();
                    RankingGoles[x][1] = String.valueOf(seleccion.getGoles());
                    x++;
                }
            } catch (Exception e) {
                System.out.println("ERROR");
                RankingGoles = new String[0][0];
            }
        }

        return RankingGoles;
    }

    public String[][] getBusquedaResultadosMatriz(JTextField field) {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getBusquedaResultados(field);

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][7];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getGrupo();
                matrizSelecciones[x][1] = seleccion.getLocal();
                matrizSelecciones[x][2] = seleccion.getVisitante();
                matrizSelecciones[x][3] = seleccion.getContinente_l();
                matrizSelecciones[x][4] = seleccion.getContinente_v();
                matrizSelecciones[x][5] = seleccion.getGoles_l();
                matrizSelecciones[x][6] = seleccion.getGoles_v();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] getVisitasMatriz() {

        String[][] matrizVisitas = null;
        List<Seleccion> selecciones = getVisitas();

        if (selecciones != null && selecciones.size() > 0) {

            matrizVisitas = new String[selecciones.size()][2];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizVisitas[x][0] = seleccion.getPagina();
                matrizVisitas[x][1] = seleccion.getContador();
                x++;
            }
        }

        return matrizVisitas;
    }

    public String[][] getNacionalidadesMatriz() {

        String[][] matrizNaciaonalidades = null;
        List<Seleccion> selecciones = getNacionalidades();

        if (selecciones != null && selecciones.size() > 0) {

            matrizNaciaonalidades = new String[selecciones.size()][2];

            int x = 0;
            for (Seleccion seleccion : selecciones) {
                matrizNaciaonalidades[x][0] = seleccion.getDt();
                matrizNaciaonalidades[x][1] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizNaciaonalidades;
    }

    public String[][] getSeleccionesMatrizCSV(ArrayList<Seleccion> seleccions) {

        String[][] matrizSelecciones = null;

        if (seleccions != null && seleccions.size() > 0) {

            matrizSelecciones = new String[seleccions.size()][4];

            int x = 0;
            for (Seleccion seleccion : seleccions) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] getResultadosMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getResultados();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][7];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getGrupo();
                matrizSelecciones[x][1] = seleccion.getLocal();
                matrizSelecciones[x][2] = seleccion.getVisitante();
                matrizSelecciones[x][3] = seleccion.getContinente_l();
                matrizSelecciones[x][4] = seleccion.getContinente_v();
                matrizSelecciones[x][5] = seleccion.getGoles_l();
                matrizSelecciones[x][6] = seleccion.getGoles_v();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] CantidadSelecciones(List<Seleccion> selecciones) {

        int totalAmericaSur = 0;
        int totalAmericaNorte = 0;
        int totalAmericaCentral = 0;
        int totalAsia = 0;
        int totalAfrica = 0;
        int totalEuropa = 0;

        for (Seleccion s : selecciones) {
            if (s.getContinente().equalsIgnoreCase("América del Sur")) {
                totalAmericaSur++;
            }
            if (s.getContinente().equalsIgnoreCase("América del Norte")) {
                totalAmericaNorte++;
            }
            if (s.getContinente().equalsIgnoreCase("América Central")) {
                totalAmericaCentral++;
            }
            if (s.getContinente().equalsIgnoreCase("Asia")) {
                totalAsia++;
            }
            if (s.getContinente().equalsIgnoreCase("África")) {
                totalAfrica++;
            }
            if (s.getContinente().equalsIgnoreCase("Europa")) {
                totalEuropa++;
            }
        }
        String Datos[][] = new String[1][6];
        Datos[0][0] = Integer.toString(totalAmericaSur);
        Datos[0][1] = Integer.toString(totalAmericaNorte);
        Datos[0][2] = Integer.toString(totalAmericaCentral);
        Datos[0][3] = Integer.toString(totalAfrica);
        Datos[0][4] = Integer.toString(totalAsia);
        Datos[0][5] = Integer.toString(totalEuropa);

        return Datos;
    }

}
