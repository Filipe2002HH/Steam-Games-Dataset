package algoritimosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.AlgoritmoOrdenacao;

import java.util.Arrays;

public class MergeSort implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
         String[] arrayOrdenado = mergeSort(array, comparadorAlgoritmoOrdenacao);

         return arrayOrdenado;
    }

    private String[] mergeSort(String[] arr, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        if (arr.length <= 1) {
            return arr;
        }

        int meio = arr.length / 2;

        String[] esquerda = Arrays.copyOfRange(arr, 0, meio);
        String[] direita = Arrays.copyOfRange(arr, meio, arr.length);

        mergeSort(esquerda, comparadorAlgoritmoOrdenacao);
        mergeSort(direita, comparadorAlgoritmoOrdenacao);

        return merge(arr, esquerda, direita, comparadorAlgoritmoOrdenacao);
    }

    private String[] merge(String[] arr, String[] esquerda, String[] direita, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        int i = 0, j = 0, k = 0;

        while (i < esquerda.length && j < direita.length) {
            if (comparadorAlgoritmoOrdenacao.comparar(esquerda[i], direita[j]) <= 0) {
                arr[k++] = esquerda[i++];
            } else {
                arr[k++] = direita[j++];
            }
        }

        while (i < esquerda.length) {
            arr[k++] = esquerda[i++];
        }

        while (j < direita.length) {
            arr[k++] = direita[j++];
        }

        return arr;
    }

    @Override
    public String toString() {
        return "mergeSort";
    }

}
