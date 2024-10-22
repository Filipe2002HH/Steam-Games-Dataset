package comparadoresAlgoritmoOrdenacao;

import csv.ManuseadorCSV;
import interfaces.ComparadorAlgoritmoOrdenacao;
import static csv.ManuseadorCSV.*;
import java.text.ParseException;

public class ComparadorDataLacamento implements ComparadorAlgoritmoOrdenacao {

    @Override
    public int comparar(String a, String b) {

        try {
            String[] dadosA = a.split(CHAR_DELIMITADOR);
            String[] dadosB = b.split(CHAR_DELIMITADOR);

            var data1String = dadosA[INDICE_COLUNA_DATA];
            var data2String = dadosB[INDICE_COLUNA_DATA];

            if (data1String.equals(STRING_DATA_INVALIDA) ) {
                return data2String.equals(STRING_DATA_INVALIDA) ? 0 : 1;
            }

            if (data2String.equals(STRING_DATA_INVALIDA)) {
                return -1;
            }

            var data1 = FORMATO_DATA_SAIDA.parse(data1String);
            var data2 = FORMATO_DATA_SAIDA.parse(data2String);

            return data1.compareTo(data2);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public String toString() {
        return "games_release_date";
    }

}
