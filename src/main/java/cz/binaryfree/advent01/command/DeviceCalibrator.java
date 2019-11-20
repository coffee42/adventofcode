package cz.binaryfree.advent01.command;

import java.util.stream.Stream;

public class DeviceCalibrator {

    private int freq = 0;
    private Stream<String> input;

    private DeviceCalibrator(Stream<String> input) {
        this.input = input;
    }

    private DeviceCalibrator(Stream<String> input, int startFreq) {
        this(input);
        this.freq = startFreq;
    }

    public int calibrate() {
       return input.map(this::convertToInt)
               .reduce(freq, this::compute);
    }

    public int convertToInt(String input) {
        return Integer.parseInt(input, 10);
    }

    public int compute(int currentFreq, int change) {
        return currentFreq + change;
    }

    public static Builder builder() {
        return new Builder();
    }
    static class Builder {
        private int freq = 0;
        private Stream<String> input;

        public Builder freq(int freq) {
            this.freq = freq;
            return this;
        }

        public Builder input(Stream input) {
            this.input = input;
            return this;
        }

        public DeviceCalibrator build() {
            if (input == null) {
                throw new IllegalStateException("Input must not be null");
            }
            return new DeviceCalibrator(input, freq);
        }
    }
}
