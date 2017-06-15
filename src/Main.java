import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        Parameters params = new Parameters();

         for(int i = 0; i < args.length; i += 2){
            String[] tokens;

            if(args[i].equals("-q")) {
                params.isGuiActive = false;
            }

            if(args[i].equals("-s")){
                tokens = args[i + 1].split("[xX]");
                params.width = Integer.parseInt(tokens[0]);
                params.height = Integer.parseInt(tokens[1]);
                continue;
            }


            if(args[i].equals("-r") || args[i].equals("-rect")){
                tokens = args[i + 1].split(":");
                params.min_x = Double.parseDouble(tokens[0]);
                params.max_x = Double.parseDouble(tokens[1]);
                params.min_y = Double.parseDouble(tokens[2]);
                params.max_y = Double.parseDouble(tokens[3]);
                continue;
            }

            if(args[i].equals("-o")){
                params.imageName = args[i + 1];
                continue;
            }

            if(args[i].equals("-t")){
                params.numThreads = Integer.parseInt(args[i + 1]);
            }
        }

        if(params.isGuiActive){
            UserInterface gui = UserInterface.getInstance();
            gui.run();
        }else {
            Main.runMain(params);
        }

    }

    public static void runMain(Parameters params){
        Calendar cal = Calendar.getInstance();
        long timeStart = cal.getTimeInMillis();

        BufferedImage bi = new BufferedImage( params.width,  params.height, BufferedImage.TYPE_3BYTE_BGR);

        Parallelism prl = new Parallelism(params, bi);

        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, params.width, params.height);

        prl.startThreads();

        g2d.setColor(Color.GRAY);
        g2d.drawRect(0, 0, params.width - 2, params.height - 2);

        try {
            ImageIO.write(bi, "PNG", new File(params.imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cal = Calendar.getInstance();
        long timeEnd = cal.getTimeInMillis();
        timeEnd = timeEnd - timeStart;

        if(params.isGuiActive){
            String text = params.area.getText() + "Threads used in current runMain: " + params.numThreads + " \nTotal execution time for current run (millis): " + timeEnd;
            params.area.setText(text);
        } else {
            System.out.printf("Threads used in current runMain: %d %n", params.numThreads);
            System.out.printf("Total execution time for current run (millis): %d %n", timeEnd);
        }

    }

}

