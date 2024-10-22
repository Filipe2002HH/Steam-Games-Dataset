package casosAlgoritmosOrdenacao;

import interfaces.ComparadorAlgoritmoOrdenacao;
import interfaces.CasoAlgoritmoOrdenacao;

public class CasoMedio implements CasoAlgoritmoOrdenacao {

    @Override
    public String[] obterArray(String[] array, ComparadorAlgoritmoOrdenacao comparadorAlgoritmoOrdenacao) {
        return array;
    }

    @Override
    public String toString() {
        return "medioCaso";
    }

}
