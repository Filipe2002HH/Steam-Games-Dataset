package algoritimosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.AlgoritmoOrdenacao;

public class InsertionSort implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {

        for (int i = 1; i < array.length; ++i) {
            String chave = array[i];
            int j = i - 1;

            while (j >= 0 && comparadorAlgoritmoOrdenacao.comparar(array[j], chave) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = chave;
        }

        return array;
    }

    @Override
    public String toString() {
        return "insertionSort";
    }

}
