package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManuseadorCSV {

    public static final int INDICE_COLUNA_CONQUISTAS = 6;
    public static final int INDICE_COLUNA_PRECO = 4;
    public static final int INDICE_COLUNA_DATA = 2;
    public static final int INDICE_COLUNA_LINUX = 5;
    public static final int INDICE_COLUNA_LINGUAGENS = 3;
    public static final String CHAR_DELIMITADOR = ",";
    public static final String STRING_DATA_INVALIDA = "Data Inválida";
    public static final String PATH_ARQUIVOS_CSV = "./src/dataset/arquivosCsv";
    public static final String PATH_PASTA_RESULTADO = "./src/dataset/resultados";
    public static final String PATH_ARQUIVO_PRINCIPAL = Paths.get(PATH_ARQUIVOS_CSV, "games.csv").toString();
    public static final String PATH_DATAS_FORMATADAS = Paths.get(PATH_ARQUIVOS_CSV, "games_formated_release_data.csv").toString();
    public static final String PATH_GAMES_LINUX = Paths.get(PATH_ARQUIVOS_CSV, "games_linux.csv").toString();
    public static final String PATH_GAMES_PORTUGUES = Paths.get(PATH_ARQUIVOS_CSV, "portuguese_supported_games.csv").toString();
    public static final SimpleDateFormat FORMATO_DATA_SAIDA = new SimpleDateFormat("dd/MM/yyyy");

    public String[] dadosOrdenacao;

    public String[] lerCSV(String caminhoArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        StringBuilder conteudo = new StringBuilder();

        // Ler o arquivo e armazenar todas as linhas em uma única string, separadas por "\n"
        while ((linha = br.readLine()) != null) {
            conteudo.append(linha).append("\n");
        }

        br.close();

        // Quebrar o conteúdo em um array de linhas
        return conteudo.toString().split("\n");
    }

    public void escreverCSV(String caminhoArquivo, String[] dados) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo));

        // Escrever cada string do array em uma nova linha no arquivo
        for (String linha : dados) {
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
    }

    public Date converterData(String data) throws ParseException {
        String[] padroes = {"MMM dd yyyy", "MMM yyyy"};

        for (String padrao : padroes) {
            try {
                SimpleDateFormat formato = new SimpleDateFormat(padrao, Locale.US);
                return formato.parse(data);
            } catch (ParseException ignored) {}
        }

        throw new ParseException("Nenhum formato de data foi reconhecido: " + data, 0);
    }

    private String[] converterDatas(String[] dados) throws ParseException {
        Queue<String> filaLinhas = new LinkedList<>();
        Collections.addAll(filaLinhas, dados);

        String[] linhasConvertidas = new String[dados.length];
        linhasConvertidas[0] = filaLinhas.poll(); // Cabeçalho

        int index = 1;

        while (!filaLinhas.isEmpty()) {
            String linha = filaLinhas.poll();
            String[] campos = linha.split(CHAR_DELIMITADOR);

            if (!campos[INDICE_COLUNA_DATA].isEmpty()) {
                String campoData = campos[INDICE_COLUNA_DATA];

                try {
                    String dataFormatada = FORMATO_DATA_SAIDA.format(converterData(campoData));
                    campos[INDICE_COLUNA_DATA] = dataFormatada;
                } catch (ParseException e) {
                    campos[INDICE_COLUNA_DATA] = STRING_DATA_INVALIDA;
                }
            }

            linhasConvertidas[index++] = String.join(CHAR_DELIMITADOR, campos);
        }

        return linhasConvertidas;
    }

    private String[] filtrarLinux(String[] dados) {
        Map<Integer, String> jogosLinuxMap = new HashMap<>();

        for (int i = 1; i < dados.length; i++) {
            String[] campos = dados[i].split(CHAR_DELIMITADOR);
            if (campos[INDICE_COLUNA_LINUX].equalsIgnoreCase("True")) {
                jogosLinuxMap.put(i, dados[i]);
            }
        }

        String[] jogosLinux = new String[jogosLinuxMap.size() + 1];
        jogosLinux[0] = dados[0];

        System.arraycopy(Arrays.copyOfRange(jogosLinuxMap.values().toArray(String[]::new), 0, jogosLinuxMap.size()), 0, jogosLinux, 1, jogosLinuxMap.size());

        return jogosLinux;
    }


    private String[] filtrarPortugues(String[] dados) {
        int count = 0;

        for (int i = 1; i < dados.length; i++) {
            String[] campos = dados[i].split(CHAR_DELIMITADOR);
            if (campos[INDICE_COLUNA_LINGUAGENS].contains("Portuguese")) {
                count++;
            }
        }

        String[] jogosPortugues = new String[count + 1];
        jogosPortugues[0] = dados[0];

        int index = 1;

        for (int i = 1; i < dados.length; i++) {
            String[] campos = dados[i].split(CHAR_DELIMITADOR);

            if (campos[INDICE_COLUNA_LINGUAGENS].contains("Portuguese")) {
                jogosPortugues[index++] = dados[i];
            }
        }

        return jogosPortugues;
    }

    public void escreverCSVComCabecalho(String caminhoArquivo, String[] dados) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo));

        bw.write("AppID,Name,Release date,Supported languages,Price,Linux,Achievements");
        bw.newLine();

        for (String linha : dados) {
            bw.write(linha);
            bw.newLine();
        }

        bw.close();
    }


    public void executar() throws IOException, ParseException {
        String[] dados = lerCSV(PATH_ARQUIVO_PRINCIPAL);
        String[] dadosComDatasConvertidas = converterDatas(dados);

        this.dadosOrdenacao = dadosComDatasConvertidas;

        escreverCSV(
            PATH_DATAS_FORMATADAS,
            dadosComDatasConvertidas
        );

        String[] jogosLinux = filtrarLinux(dadosComDatasConvertidas);

        escreverCSV(
            PATH_GAMES_LINUX,
            jogosLinux
        );

        String[] jogosPortugues = filtrarPortugues(dadosComDatasConvertidas);

        escreverCSV(
            PATH_GAMES_PORTUGUES,
            jogosPortugues
        );

    }

    public String[] obterDadosOrdenacao() {
        // Serve para limitar tanto de elementos de dados a serem ordenados (Manter em 0) , modificar para testar com quantidade de dados menores
        int removerElemento = 0;
        String[] copia = new String[dadosOrdenacao.length - 1 - removerElemento];
        System.arraycopy(dadosOrdenacao, 1, copia, 0, dadosOrdenacao.length - 1 - removerElemento);
        return copia;
    }

}
