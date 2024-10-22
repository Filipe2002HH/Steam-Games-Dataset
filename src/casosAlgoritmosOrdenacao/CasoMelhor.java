package casosAlgoritmosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.CasoAlgoritmoOrdenacao;

import java.util.Arrays;

public class CasoMelhor implements CasoAlgoritmoOrdenacao {

    @Override
    public String[] obterArray(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        Arrays.sort(array, comparadorAlgoritmoOrdenacao::comparar);

        return array;
    }

    @Override
    public String toString() {
        return "melhorCaso";
    }

}
