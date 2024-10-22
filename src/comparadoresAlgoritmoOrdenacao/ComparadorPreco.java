package comparadoresAlgoritmoOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;

import java.util.Arrays;

import static csv.ManuseadorCSV.CHAR_DELIMITADOR;
import static csv.ManuseadorCSV.INDICE_COLUNA_PRECO;

public class ComparadorPreco implements ComparadorAlgoritmoOrdenacao {

    @Override
    public int comparar(String a, String b) {
        String[] dadosA = a.split(CHAR_DELIMITADOR);
        String[] dadosB = b.split(CHAR_DELIMITADOR);

        double precoA = Double.parseDouble(dadosA[INDICE_COLUNA_PRECO]);
        double precoB = Double.parseDouble(dadosB[INDICE_COLUNA_PRECO]);

        return Double.compare(precoA, precoB);
    }

    @Override
    public String toString() {
        return "games_price";
    }

}
