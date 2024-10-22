package algoritimosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.AlgoritmoOrdenacao;

public class HeapSort implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparadorAlgoritmoOrdenacao);
        }

        for (int i = n - 1; i > 0; i--) {
            String temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparadorAlgoritmoOrdenacao);
        }

        return array;
    }

    @Override
    public String toString() {
        return "heapSort";
    }

    private static void heapify(String[] arr, int n, int i, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparadorAlgoritmoOrdenacao.comparar(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && comparadorAlgoritmoOrdenacao.comparar(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest, comparadorAlgoritmoOrdenacao);
        }
    }

}
