import org.apache.commons.math3.complex.Complex;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;

public class Renderer implements Runnable{

    private double max_x;
    private double min_x;

    private double max_y;
    private double min_y;

    private int width;
    private int height;

    private double start;
    private double end;

    private BufferedImage bufferImage;

    public Renderer(double max_x, double min_x, double max_y, double min_y, int width, int height,  double start, double end, BufferedImage bufferImage){
        this.max_x = max_x;
        this.min_x = min_x;

        this.max_y = max_y;
        this.min_y = min_y;

        this.width = width;
        this.height = height;

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
        long startTime = System.nanoTime();
        double YStep;
        if(start == 0) {
             YStep = 1.0 / ((height - 1) * 1.2);
        }else {
            YStep = (1.0 / ((height - 1) * 1.2)) * (start + 1);
        }

        for (int i = (int) start; i < (int) (end - 1) ; i++) {
            double YDifference = Math.abs(max_y - min_y);
            double pointY = max_y - YDifference * YStep;
            int pointYOnBufImg = (int) (Math.abs((pointY - max_y)) * ((height - 1)/ YDifference));

            double XStep = 1.0 / ((width - 1)* 1.2);

            for (int j = 0; j < (int)((width - 1)* 1.2) ; j++) {

                double XDifference = Math.abs(max_x - min_x);
                double pointX = min_x + XDifference * XStep;
                int pointXOnBufImg = (int) (Math.abs((pointX + max_x)) * ((width - 1)/ XDifference));

                int r = z_check(new Complex(pointX, pointY));

                ColorScheme colorScheme = new ColorScheme(640);
                bufferImage.setRGB(pointXOnBufImg, pointYOnBufImg, colorScheme.getColor(r));


                XStep += 1.0 / ((width - 1)* 1.2);
            }
            YStep += 1.0 / ((height - 1)* 1.2);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        long tID = Thread.currentThread().getId();
        PrintWriter out = new PrintWriter(System.out);
        out.printf("%d id: %d", duration, tID);
        out.flush();
        out.close();
    }







        public static Complex z_iter(Complex z, Complex c) {
            Complex res = z.multiply(z).add(c);
            res = res.exp();
            return res;
        }

        public static int z_check(Complex c) {

            Complex z0 = new Complex(0.0, 0.0);

            Complex z_prev = z0;
            Complex z_i = null;

            int steps = 0;

            Double d = null;

            for(int i = 0; i < 640; i++) {

                z_i = z_iter(z_prev, c);
                z_prev = z_i;

                d = new Double(z_prev.getReal());

                if (d.isInfinite() || d.isNaN()) {
                    steps = i;
                    break;
                }

            }

            // System.out.println("s: " + steps + ", " + z_i.getReal() + ", " + z_i.getImaginary());
            // return (steps == 0);

            return steps;

        }

    }
    //do stuff


