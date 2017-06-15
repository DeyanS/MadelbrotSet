import org.apache.commons.math3.complex.Complex;

public class PointChecker {
    public static Complex calculate(Complex z, Complex c) {
        Complex res = z.multiply(z).add(c);
        res = res.exp();
        return res;
    }

    public static int checkPoint(Complex c) {

        Complex z_prev = new Complex(0.0, 0.0);;
        Complex z_i;

        int steps = 0;

        Double d;

        for(int i = 0; i < 100; i++) {

            z_i = calculate(z_prev, c);
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
