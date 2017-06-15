import com.jcraft.jsch.Buffer;
import org.apache.commons.math3.complex.Complex;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.Calendar;

public class Renderer implements Runnable{


    private double start;
    private double end;
    private BufferedImage bufferImage;

    private Parameters params;

    public Renderer(Parameters params, double start, double end, BufferedImage bufferImage){
        this.params = params;

        this.start = start;
        this.end =  end;
        this.bufferImage = bufferImage;
    }

    public Renderer()
    {

    }

    @Override
    public void run(){
      this.render();
    }

    public void render() {
        Calendar cal;
        long timeStart = 0;
        long threadId = Thread.currentThread().getId();
        String text;

        if(params.isGuiActive) {
            cal = Calendar.getInstance();
            timeStart = cal.getTimeInMillis();

            text = params.area.getText() + timeStart + "-> Thread-" + threadId + " started. \n";
            params.area.setText(text);
        }
        double YStep;
        if(start == 0) {
             YStep = 1.0 / ((params.height - 1) * 1.2);
        }else {
            YStep = (1.0 / ((params.height - 1) * 1.2)) * (start + 1);
        }

        for (int i = (int) start; i < (int) (end - 1) ; i++) {
            double YDifference = Math.abs(params.max_y - params.min_y);
            double pointY = params.max_y - YDifference * YStep;
            int pointYOnBufImg = (int) (Math.abs((pointY - params.max_y)) * ((params.height - 1)/ YDifference));

            double XStep = 1.0 / ((params.width - 1)* 1.2);

            for (int j = 0; j < (int)((params.width - 1)* 1.2) ; j++) {

                double XDifference = Math.abs(params.max_x - params.min_x);
                double pointX = params.min_x + XDifference * XStep;
                int pointXOnBufImg = (int)Math.abs(((pointX  - params.min_x)) * ((params.width - 1)/ XDifference));

                int r = PointChecker.checkPoint(new Complex(pointX, pointY));

                ColorScheme colorScheme = new ColorScheme(640);
                bufferImage.setRGB(pointXOnBufImg, pointYOnBufImg, colorScheme.getColor(r));


                XStep += 1.0 / ((params.width - 1)* 1.2);
            }
            YStep += 1.0 / ((params.height - 1)* 1.2);
        }

        if(params.isGuiActive) {
            cal = Calendar.getInstance();
            long timeEnd = cal.getTimeInMillis();
            timeStart = timeEnd - timeStart;

            text = params.area.getText() + timeEnd + "-> Thread-" + threadId + " stopped. \n Thread-" + threadId + " execution time was (millis): " + timeStart + "\n";
            params.area.setText(text);
        }
    }
}
    //do stuff


