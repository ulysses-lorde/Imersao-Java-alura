import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class GeradoraDeStickers {
    public void cria(InputStream inputStream, String nomeArquivo) throws IOException {
        // Leitura da imagem
        /*
        File file = new File("entrada/filme.jpg");
        BufferedImage imagemOriginal = ImageIO.read(file);
        
        InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"))
        */
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        
        // Cria noca imagem em memória com transparência e com tamanho novo
        int lagura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(lagura, novaAltura, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Congifurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.BLUE);
        graphics.setFont(fonte);

        // Escrever uma frase na nova imagem
        graphics.drawString("Top One", 100, novaAltura - 100);

        // Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}
