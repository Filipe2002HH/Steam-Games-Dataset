package algoritimosOrdenacao;

import interfaces.AlgoritmoOrdenacao;
import interfaces.ComparadorAlgoritmoOrdenacao;
import static csv.ManuseadorCSV.CHAR_DELIMITADOR;
import static csv.ManuseadorCSV.INDICE_COLUNA_CONQUISTAS;

public class CountingSortConquista implements AlgoritmoOrdenacao {

    @Override
    public String[] sort(String[] array, ComparadorAlgoritmoOrdenacao comparador) {
        int[] conquistasArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            String[] split = array[i].split(CHAR_DELIMITADOR);
            conquistasArray[i] = Integer.parseInt(split[INDICE_COLUNA_CONQUISTAS]);
        }

        int[] conquistasOrdenadas = countingSortDecrescente(conquistasArray);

        String[] arrayOrdenado = new String[array.length];

        for (int i = 0; i < conquistasOrdenadas.length; i++) {
            for (int j = 0; j < array.length; j++) {
                String[] split = array[j].split(CHAR_DELIMITADOR);
                if (Integer.parseInt(split[INDICE_COLUNA_CONQUISTAS]) == conquistasOrdenadas[i]) {
                    arrayOrdenado[i] = array[j];
                    break;
                }
            }
        }

        return arrayOrdenado;
    }

    private int[] countingSortDecrescente(int[] array) {
        int max = array[0];
        int min = array[0];

        for (int value : array) {
            if (value > max) max = value;
            if (value < min) min = value;
        }

        int[] count = new int[max - min + 1];
        int[] saida = new int[array.length];

        for (int value : array) {
            count[value - min]++;
        }

        for (int i = count.length - 2; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            saida[--count[array[i] - min]] = array[i];
        }

        return saida;
    }

    @Override
    public String toString() {
        return "countingSortConquista";
    }

}
