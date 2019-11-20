package cz.binaryfree.advent01.command;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class PrintHelpCommand implements Command {

    private Options options;

    public PrintHelpCommand(Options options) {
        this.options = options;
    }


    @Override
    public void doWork() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar calirator.jar", options );
    }
}
