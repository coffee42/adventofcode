package cz.binaryfree.advent01.command;

import java.util.List;
import java.util.stream.Stream;

public class DeviceCalibrator {

    protected int freq = 0;
    protected final List<Integer> input;

    public DeviceCalibrator(List<Integer> input) {
        this.input = input;
        if (input == null) {
            throw new IllegalStateException("Input must not be null");
        }
    }

    protected DeviceCalibrator(List<Integer> input, int startFreq) {
        this(input);
        this.freq = startFreq;
    }

    public int calibrate() {
       return input.stream()
                    .reduce(freq, this::compute);
    }

    public int compute(int currentFreq, int change) {
        return currentFreq + change;
    }

}
