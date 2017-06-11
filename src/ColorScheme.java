
public class ColorScheme {


    private int maxIterations;

    public ColorScheme(int maxIterations){
        this.maxIterations = maxIterations;
    }

    public int getColor(int i) {

        int a = (int) (255 * ((double) i) / (maxIterations / 4));
        return
                // Red & black with fade, a classic!
                ( (2*a<<16) );
                // Other options of varying qualities...
                // Hot pink bar & black
                // ( (255 * (i/15)) << 16 | (255 * (i/15)) );
                // Red bars & black
                // ((255 * (i/20)) << 16 | 0 | 0 );
                // The cow level! Black & white bars
                // ((255 * (i/10)) << 16 | (255 * (i/10)) << 8 | (255 * (i/10)) );
                // Blue, blue-green fade, and black
                // (65536 + i*256 + i/2+128);
                // Black & purple/pink fade
               // ( (0) | (2*a<<16) | (a<<8) | ((a*2)<<0) );
    }

}