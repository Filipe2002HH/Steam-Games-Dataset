package casosAlgoritmosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.CasoAlgoritmoOrdenacao;

import java.util.Arrays;

public class CasoPior implements CasoAlgoritmoOrdenacao {

    @Override
    public String[] obterArray(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        Arrays.sort(array, (dado1, dado2) -> comparadorAlgoritmoOrdenacao.comparar(dado2, dado1));
        return array;
    }

    @Override
    public String toString() {
        return "piorCaso";
    }

}
