import java.awt.image.BufferedImage;

public class Parallelism {

    private Parameters params;

    private BufferedImage bufferImage;

    public Parallelism(Parameters param, BufferedImage bufferImage){
        this.params = param;
        this.bufferImage = bufferImage;
    }

    public void startThreads(){
         int whole = (int)(params.height * 1.2);
         int[] dividedHeight = splitIntoParts(whole, params.numThreads);
         Thread[] threads = new Thread[params.numThreads];

         for(int i = 0; i < params.numThreads; i++){
             threads[i] = new Thread(new Renderer(params, dividedHeight[i], dividedHeight[i + 1], bufferImage));
         }

         for (Thread thread : threads) {
             thread.start();
         }

         for (Thread thread : threads) {
             try {
                 thread.join();
             }

             catch (Exception e) {

                 int a = 2;
             }
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
}
