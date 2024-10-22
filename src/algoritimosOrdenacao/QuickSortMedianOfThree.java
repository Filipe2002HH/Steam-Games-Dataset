package algoritimosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.AlgoritmoOrdenacao;

public class QuickSortMedianOfThree implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        quickSort(array, 0, array.length - 1, comparadorAlgoritmoOrdenacao);
        return array;
    }

    private void medianOfThree(String[] array, int low, int high, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        int mid = (low + high) / 2;

        if (comparadorAlgoritmoOrdenacao.comparar(array[low], array[mid]) > 0) {
            swap(array, low, mid);
        }
        if (comparadorAlgoritmoOrdenacao.comparar(array[low], array[high]) > 0) {
            swap(array, low, high);
        }
        if (comparadorAlgoritmoOrdenacao.comparar(array[mid], array[high]) > 0) {
            swap(array, mid, high);
        }

        swap(array, mid, high);
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void quickSort(String[] arr, int low, int high, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            medianOfThree(arr, low, high, comparadorAlgoritmoOrdenacao);

            int pi = partition(arr, low, high, comparadorAlgoritmoOrdenacao);

            if (pi - 1 > low) {
                stack[++top] = low;
                stack[++top] = pi - 1;
            }

            if (pi + 1 < high) {
                stack[++top] = pi + 1;
                stack[++top] = high;
            }
        }
    }

    private static int partition(String[] arr, int low, int high, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        String pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparadorAlgoritmoOrdenacao.comparar(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    @Override
    public String toString() {
        return "quickSortMedianOfThree";
    }

}
