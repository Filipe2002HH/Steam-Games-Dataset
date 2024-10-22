package comparadoresAlgoritmoOrdenacao;
import interfaces.ComparadorAlgoritmoOrdenacao;

import static csv.ManuseadorCSV.CHAR_DELIMITADOR;
import static csv.ManuseadorCSV.INDICE_COLUNA_CONQUISTAS;

public class ComparadorConquista implements ComparadorAlgoritmoOrdenacao {

    @Override
    public int comparar(String a, String b) {
        String[] dadosA = a.split(CHAR_DELIMITADOR);
        String[] dadosB = b.split(CHAR_DELIMITADOR);

        int conquistasA = dadosA[INDICE_COLUNA_CONQUISTAS].isEmpty() ? 0 : Integer.parseInt(dadosA[INDICE_COLUNA_CONQUISTAS]);
        int conquistasB = dadosB[INDICE_COLUNA_CONQUISTAS].isEmpty() ? 0 : Integer.parseInt(dadosB[INDICE_COLUNA_CONQUISTAS]);

        return Integer.compare(conquistasB, conquistasA);
    }

    @Override
    public String toString() {
        return "games_achievements";
    }

}



