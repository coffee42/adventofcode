package cz.binaryfree.advent01.command;

import cz.binaryfree.advent01.Main;

import java.io.*;
import java.util.stream.Stream;

public class DefaultInputCommand implements Command{

    private DeviceCalibrator deviceCalibrator;

    public DefaultInputCommand(int freq) throws FileNotFoundException {
        InputStream is = DefaultInputCommand.class.getClassLoader().getResourceAsStream("input.txt");
        Stream<String> input = new BufferedReader(new InputStreamReader(is))
                                    .lines();
        initDeviceCalibrator(freq, input);
    }

    public DefaultInputCommand(File file) throws FileNotFoundException {
        if (file.exists() || file.isFile()) {
            Stream<String> input = new BufferedReader(new FileReader(file))
                    .lines();
            initDeviceCalibrator(0, input);
        }
        else throw new FileNotFoundException(file.getAbsolutePath());
    }
    public DefaultInputCommand(int freq, File file) throws FileNotFoundException {
        if (file.exists() || file.isFile()) {
            Stream<String> input = new BufferedReader(new FileReader(file))
                    .lines();
            initDeviceCalibrator(freq, input);
        }
        else throw new FileNotFoundException(file.getAbsolutePath());
    }
    private void initDeviceCalibrator(int freq, Stream<String> input) {
        deviceCalibrator = DeviceCalibrator.builder()
                            .freq(freq)
                            .input(input)
                            .build();
    }

    @Override
    public void doWork() {
        int result = deviceCalibrator.calibrate();
        System.out.format("Result frequency: %d", result);
    }
}
