package algoritimosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.AlgoritmoOrdenacao;

public class SelectionSort implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {

        for (int i = 0; i < array.length - 1; i++) {
            int indexMinimo = i;

            for (int j = i + 1; j < array.length; j++) {
                if (comparadorAlgoritmoOrdenacao.comparar(array[j], array[indexMinimo]) < 0) {
                    indexMinimo = j;
                }
            }

            String temp = array[indexMinimo];
            array[indexMinimo] = array[i];
            array[i] = temp;
        }

        return array;
    }

    @Override
    public String toString() {
        return "selectionSort";
    }

}
