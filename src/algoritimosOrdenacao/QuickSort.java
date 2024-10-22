package algoritimosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.AlgoritmoOrdenacao;

public class QuickSort implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        quickSort(array, 0, array.length - 1, comparadorAlgoritmoOrdenacao);
        return array;
    }

    private void quickSort(String[] arr, int low, int high, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

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
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public String toString() {
        return "quickSort";
    }

}
