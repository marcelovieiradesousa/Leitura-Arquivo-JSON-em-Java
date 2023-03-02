package roation;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DiaUtil {
    private int dia;
    private double valor;

    public DiaUtil(int dia, double valor) {
        this.dia = dia;
        this.valor = valor;
    }

    public static void main(String[] args) {
        List<DiaUtil> diasUteis = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray arquivo = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream("dados.json")));
            for (Object o : arquivo) {
                JSONObject umDia = (JSONObject) o;
                int dia = Integer.parseInt(umDia.get("dia").toString());
                double valor = Double.parseDouble(umDia.get("valor").toString());

                diasUteis.add(new DiaUtil(dia, valor));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        double valorMaior = 0.0;
        for (DiaUtil dia : diasUteis) {
            valorMaior = (valorMaior < dia.valor) ? dia.valor : valorMaior;
        }
        double valorMenor = valorMaior;
        for (DiaUtil dia : diasUteis) {
            valorMenor = (valorMenor > dia.valor) ? dia.valor : valorMenor;
        }

        double media;
        double soma = 0.0;
        int qtd = 0;
        for (DiaUtil dia : diasUteis) {
            if (dia.valor > 0) {
                soma += dia.valor;
                qtd++;
            }
        }
        media = soma / qtd;

        int maiorQueAMedia = 0;
        for (DiaUtil dia : diasUteis) {
            if(dia.valor > media) maiorQueAMedia++;
        }
        System.out.println("O menor valor de faturamento ocorrido em um dia do mês: " + valorMenor);
        System.out.println("O maior valor de faturamento ocorrido em um dia do mês: " + valorMaior);
        System.out.println(
                "Número de dias no mês em que o valor de faturamento diário foi superior à média mensal: " + maiorQueAMedia);

    }
}
