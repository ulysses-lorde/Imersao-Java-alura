import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //String variavelAmbiente = System.getenv("VAR_AMBIENTE");
        
        // Fazer conexão HTTP e requisitar os dados do json
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        //var client = HttpClient.newHttpClient();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // Extrair dados exatos (titulo, poster, classificao)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.get(0));

        // Exibir e manipular os dados
        var geradora = new GeradoraDeStickers();
        for (Map<String,String> filme : listaDeFilmes) {
            String rank = filme.get("rank");
            String title = filme.get("title");
            String rating = filme.get("imDbRating");
            String urlImagem = filme.get("image");

            // Pega a imagem e gera uma figurinha
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + title + ".png";

            geradora.cria(inputStream, nomeArquivo);

            /*Transformar a nota em float e 
            arredondar para o menor inteiro*/  
            double ratingFloat = Double.parseDouble(rating);
            var ratinground = Math.round(ratingFloat - 0.5);
            
            /*Exibir (rank, title e rating)*/
            System.out.println("\u001b[1mRank: \u001b[m" + rank + " - " + "\u001b[1mTítulo: \u001b[m" + title);
            System.out.println("\u001b[1mNota: \u001b[m" + ratingFloat);
            
            /**/
            for (int n = 1; n <= ratinground; n++) {
                System.out.print("⭐");
            }
            
            System.out.println("\n");
        }
    }
}
