package tw.jcconf;

import org.apache.commons.cli.*;

public class MyHello {
    public static void main(String[] args) {
        Options options = new Options()
                .addOption(new Option("h", false, "Print this help"))
                .addOption(new Option(
                        "n",
                        true,
                        "Number of hello to print"));

        CommandLine commandLine = null;
        try {
            commandLine = new GnuParser().parse(options, args);
        } catch(ParseException e) {
            System.out.println("Can't parse arguments: " + e.getMessage());
            System.exit(1);
        }

        if(commandLine.hasOption("h")) {
            HelpFormatter f = new HelpFormatter();
            f.printHelp("myhello [options] [name]", options);
            return;
        }

        int times = commandLine.hasOption("n") ?
                Integer.parseInt(commandLine.getOptionValue("n")) :
                1;

        String name = commandLine.getArgs().length > 0  ?
                commandLine.getArgs()[0] :
                "world";

        for (int i=0; i<times; i++) {
            System.out.println("hello " + name);
        }
    }
}
