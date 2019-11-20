package cz.binaryfree.advent01.command;

import cz.binaryfree.advent01.calibrator.DeviceCalibrator;
import cz.binaryfree.advent01.calibrator.IterationDeviceCalibrator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultInputCommand implements Command{

    private DeviceCalibrator deviceCalibrator;

    public DefaultInputCommand(int freq) throws IOException {
        InputStream is = DefaultInputCommand.class.getClassLoader().getResourceAsStream("input.txt");
        List<Integer> input = readInput(is);
        initDeviceCalibrator(freq, input);
    }

    public DefaultInputCommand(File file) throws IOException {
        List<Integer> input = readInput(file);
        initDeviceCalibrator(0, input);
    }

    public DefaultInputCommand(int freq, File file) throws IOException {
        List<Integer> input = readInput(file);
        initDeviceCalibrator(freq, input);
    }
    private void initDeviceCalibrator(int freq, List<Integer> input) {
        deviceCalibrator = new IterationDeviceCalibrator(input, freq);
    }

    private List<Integer> readInput (File file) throws IOException {
        if (file.exists() && file.isFile()) {
            return readInput(new FileInputStream(file));
        }
        else throw new FileNotFoundException(file.getAbsolutePath());
    }

    private List<Integer> readInput (InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map( i -> Integer.parseInt(i, 10))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    @Override
    public void doWork() {
        int result = deviceCalibrator.calibrate();
        System.out.format("Resulting frequency: %d", result);
    }
}
