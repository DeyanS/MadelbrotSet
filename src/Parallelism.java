import java.awt.image.BufferedImage;

public class Parallelism {

    private int numberOfThreads;
    private double max_x;
    private double min_x;

    private double max_y;
    private double min_y;

    private int width;
    private int height;

    private BufferedImage bufferImage;

    public Parallelism(double max_x, double min_x, double max_y, double min_y, int width, int height, BufferedImage bufferImage){
        this.max_x = max_x;
        this.min_x = min_x;

        this.max_y = max_y;
        this.min_y = min_y;

        this.width = width;
        this.height = height;


        this.bufferImage = bufferImage;
    }


    public void startThreads(){
         int whole = (int)(height * 1.2);
         int[] dividedHeight = splitIntoParts(height, numberOfThreads);
         Thread[] threads = new Thread[numberOfThreads];

         for(int i = 0; i < numberOfThreads; i++){
             threads[i] = new Thread(new Renderer(max_x, min_x, max_y, min_y, width, height, dividedHeight[i], dividedHeight[i + 1], bufferImage));
         }

         for (Thread thread : threads) {
             thread.start();
         }

         for (Thread thread : threads) {
             try {
                 thread.join();
             }
             catch (Exception e) {}
         }
     }



    public static int[] splitIntoParts(int whole, int parts) {
        int[] arr = new int[parts + 1];
        int diff = whole/parts;
        for (int i = 0; i <= parts; i++) {
            arr[i] = diff*i;
        }
        return arr;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public double getMax_x() {
        return max_x;
    }

    public void setMax_x(double max_x) {
        this.max_x = max_x;
    }

    public double getMin_x() {
        return min_x;
    }

    public void setMin_x(double min_x) {
        this.min_x = min_x;
    }

    public double getMax_y() {
        return max_y;
    }

    public void setMax_y(double max_y) {
        this.max_y = max_y;
    }

    public double getMin_y() {
        return min_y;
    }

    public void setMin_y(double min_y) {
        this.min_y = min_y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getBufferImage() {
        return bufferImage;
    }

    public void setBufferImage(BufferedImage bufferImage) {
        this.bufferImage = bufferImage;
    }
}
