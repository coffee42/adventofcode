package cz.binaryfree.advent01.calibrator;

import java.util.*;
import java.util.stream.Stream;

public class IterationDeviceCalibrator extends DeviceCalibrator {

    public IterationDeviceCalibrator(List<Integer> input) {
        super(input);
    }

    public IterationDeviceCalibrator(List<Integer> input, int startFreq) {
        super(input, startFreq);
    }

    @Override
    public int calibrate() {

        int currentFreq = freq;
        Set<Integer> knownFrequencies = new HashSet<>();
        Iterator<Integer> inputIterator = Stream.iterate(0, i -> (i + 1) %  input.size()).iterator();

        while(inputIterator.hasNext()) {
            int change = input.get(inputIterator.next());
            knownFrequencies.add(currentFreq);
            currentFreq = compute(currentFreq, change);
            if (knownFrequencies.contains(currentFreq)) {
                return currentFreq;
            }
        }
        return currentFreq;
    }

}
