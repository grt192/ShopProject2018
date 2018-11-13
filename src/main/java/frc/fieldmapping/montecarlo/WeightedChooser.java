package frc.fieldmapping.montecarlo;

public class WeightedChooser {

    private double[] weights;
    private int index;

    public WeightedChooser(int size) {
        weights = new double[size];
        index = 0;
    }

    public void add(double weight) {
        if (index > 0) {
            weight += weights[index - 1];
        }
        weights[index] = weight;
        index++;
    }

    public void clear() {
        index = 0;
    }

    public int choose() {
        double maxWeight = weights[index - 1];
        double x = Math.random() * maxWeight;
        int i = 0;
        while (weights[i] < x) {
            i++;
        }
        return i;
    }
}