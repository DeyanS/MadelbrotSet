import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Main {

    public static void main(String[] args) {


        long startTime = System.nanoTime();
        int width = 320;
        int height = 240;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Parallelism prl = new Parallelism(2, 0, 2, 1, width, height, bi);
        prl.setNumberOfThreads(1);

        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        prl.startThreads();

        PrintWriter out = new PrintWriter(System.out);

        g2d.setColor(Color.GRAY);
        g2d.drawRect(0, 0, width - 2, height - 2);

        try {
            ImageIO.write(bi, "PNG", new File("SeeMe4-2x2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.printf("done.\n");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        out.printf("%d", duration);


    }

}

