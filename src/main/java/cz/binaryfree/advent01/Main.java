package cz.binaryfree.advent01;

import cz.binaryfree.advent01.command.Command;
import cz.binaryfree.advent01.command.DefaultInputCommand;
import cz.binaryfree.advent01.command.PrintHelpCommand;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Options options = defineOptions();
        Command helpCommand = new PrintHelpCommand(options);
        Command command = helpCommand;
        CommandLineParser commandLineParser = new DefaultParser();
        String fileInput = null;
        String freqInput = null;
        try {
            CommandLine cmdLine = commandLineParser.parse(options, args);
            freqInput = cmdLine.getOptionValue("s", "0");
            if (cmdLine.hasOption("h")) {
                helpCommand.doWork();
                return;
            }
            if (cmdLine.hasOption("f")) {
                fileInput = cmdLine.getOptionValue("f");
                File file = Path.of(".").resolve(fileInput).toFile();
                command = new DefaultInputCommand(Integer.parseInt(freqInput), file);
            }
            else {
                command = new DefaultInputCommand(Integer.parseInt(freqInput));
            }
            command.doWork();
        }
        catch (ParseException ex) {
            System.err.format(ex.getMessage());
            helpCommand.doWork();
        }
    }

    private static Options defineOptions() {
        return new Options().addOption(
                Option.builder("f")
                        .argName("file")
                        .longOpt("file")
                        .hasArg(true)
                        .desc("use specific file as input")
                        .build()
                )
                .addOption(
                        Option.builder("s")
                                .argName("starting frequency")
                                .longOpt(null)
                                .hasArg(true)
                                .desc("set starting frequency")
                                .type(Integer.class)
                                .build()
                )
                .addOption(
                        Option.builder("h")
                                .argName("help")
                                .longOpt("help")
                                .hasArg(false)
                                .desc("print help")
                                .build()
                );
    }
}
