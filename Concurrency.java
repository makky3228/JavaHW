import java.util.Random;
import java.lang.Math;
class Concurrency {
    double randRange(double min, double max) {
        double range = (max - min) + 1;
        return (double) (Math.random() * range) + min;
    }

    public static void main(String args[]) {
        Random rd = new Random(); // creating Random object
        double[] arr = new double[200000000];
        for (double i = 0; i < arr.length; i++) {
            Concurrency value = new Concurrency();
            double rand = value.randRange(1.0, 9.0);
            System.out.println(rand);
        }
    }
}
