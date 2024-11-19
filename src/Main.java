import algoritimosOrdenacao.*;
import comparadoresAlgoritmoOrdenacao.*;
import csv.ManuseadorCSV;
import interfaces.AlgoritmoOrdenacao;
import interfaces.CasoAlgoritmoOrdenacao;
import casosAlgoritmosOrdenacao.CasoMedio;
import casosAlgoritmosOrdenacao.CasoMelhor;
import casosAlgoritmosOrdenacao.CasoPior;
import interfaces.ComparadorAlgoritmoOrdenacao;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        ManuseadorCSV gerenciadorCSV = new ManuseadorCSV();

        List<AlgoritmoOrdenacao> algoritmos = List.of(
                new MergeSort(),
                new HeapSort(),
                new QuickSort(),
                new QuickSortMedianOfThree(),
                new SelectionSort(),
                new InsertionSort(),
                new CountingSortConquista()
        );

        List<ComparadorAlgoritmoOrdenacao> comparadores = List.of(
                new ComparadorDataLacamento(),
                new ComparadorPreco(),
                new ComparadorConquista()
        );

        List<CasoAlgoritmoOrdenacao> casosTeste = List.of(
                new CasoPior(),
                new CasoMedio(),
                new CasoMelhor()
        );

        System.out.println("=======================================================================");
        System.out.println("------ Iniciando a Execução das Transformações nos Arquivos CSV -------");
        System.out.println();

        gerenciadorCSV.executar();

        System.out.println("|- Transformações Concluídas");

        for (AlgoritmoOrdenacao algoritmo : algoritmos) {

            System.out.println("=======================================================================");
            System.out.printf("|----------------- Algoritmo utilizado: [%s] ------\n", algoritmo);
            System.out.println();

            for (CasoAlgoritmoOrdenacao caso : casosTeste) {

                System.out.printf("|--------- Executando o Caso de Teste: [%s] -------\n", caso);
                System.out.println();

                for (ComparadorAlgoritmoOrdenacao comparador : comparadores) {

                    if (algoritmo instanceof CountingSortConquista && !comparador.toString().equals("games_achievements")) {
                        continue;
                    }

                    String[] dadosOriginais = gerenciadorCSV.obterDadosOrdenacao();
                    String[] dadosDoCaso = caso.obterArray(dadosOriginais, comparador);

                    long tempoInicio = System.currentTimeMillis();
                    String[] dadosOrdenados = algoritmo.sort(dadosDoCaso, comparador);
                    long tempoFim = System.currentTimeMillis();

                    String nomeDoArquivo = String.format(
                            "%s_%s_%s.csv",
                            comparador, algoritmo, caso
                    );

                    gerenciadorCSV.escreverCSVComCabecalho(
                            "%s/%s".formatted(ManuseadorCSV.PATH_PASTA_RESULTADO, nomeDoArquivo),
                            dadosOrdenados
                    );

                    System.out.printf(
                            "||- Arquivo [%s] ordenado com tempo de execução: %.3f segundos\n",
                            nomeDoArquivo, (double) (tempoFim - tempoInicio) / (1 * 1000)
                    );

                }

                System.out.println();
            }

            System.out.println();
        }

    }

}
