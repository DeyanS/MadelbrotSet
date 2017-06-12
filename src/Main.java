import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int width = 640;
        int height = 480;

        double max_x = 2;
        double min_x = -2;

        double max_y = 2;
        double min_y = -2;

        String imageName = "zad19.png";

        int numThreads = 1;

         for(int i = 0; i < args.length; i += 2){
            String[] tokens;
            if(args[i].equals("-s")){
                tokens = args[i + 1].split("[xX]");
                width = Integer.parseInt(tokens[0]);
                height = Integer.parseInt(tokens[1]);
                continue;
            }

            if(args[i].equals("-r") || args[i].equals("-rect")){
                tokens = args[i + 1].split(":");
                min_x = Double.parseDouble(tokens[0]);
                max_x = Double.parseDouble(tokens[1]);
                min_y = Double.parseDouble(tokens[2]);
                max_y = Double.parseDouble(tokens[3]);
                continue;
            }

            if(args[i].equals("-o")){
                imageName = args[i + 1];
                continue;
            }

            if(args[i].equals("-t")){
                numThreads = Integer.parseInt(args[i + 1]);
                continue;
            }
        }

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Parallelism prl = new Parallelism(max_x, min_x, max_y, min_y, width, height, bi);
        prl.setNumberOfThreads(numThreads);

        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        prl.startThreads();

        PrintWriter out = new PrintWriter(System.out);

        g2d.setColor(Color.GRAY);
        g2d.drawRect(0, 0, width - 2, height - 2);

        try {
            ImageIO.write(bi, "PNG", new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.printf("done.\n");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        out.printf("%d", duration);

    }

}

