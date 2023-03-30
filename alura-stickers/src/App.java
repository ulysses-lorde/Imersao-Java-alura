import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //String variavelAmbiente = System.getenv("VAR_AMBIENTE");
        
        // Fazer conexão HTTP e requisitar os dados do json
        // IMDB
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        //NASA
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);



        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 2; i++) {

            Conteudo conteudo = conteudos.get(i);

            // Pega a imagem e gera uma figurinha
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            String fraseDaFigurinha = conteudo.getTitulo();

            geradora.cria(inputStream, nomeArquivo, fraseDaFigurinha);

            System.out.println(conteudo.getTitulo());
            
            System.out.println();
        }
    }
}
