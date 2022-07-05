public class Main {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {

        System.out.println("\n"+ANSI_BLUE+" ____                                _\n" +
                "|  _ \\                              (_)\n" +
                "| |_) | _   _   ___   _ __     __ _  _   ___   _ __  _ __    ___\n" +
                "|  _ < | | | | / _ \\ | '_ \\   / _` || | / _ \\ | '__|| '_ \\  / _ \\\n" +
                "| |_) || |_| || (_) || | | | | (_| || || (_) || |   | | | || (_) |\n" +
                "|____/  \\__,_| \\___/ |_| |_|  \\__, ||_| \\___/ |_|   |_| |_| \\___/\n" +
                "                               __/ |\n" +
                "                              |___/!\n\n" +
                "\n"+ANSI_BLUE+"Willkommen bei Gelato Marko."+ANSI_RESET);

        Gelato gelato = new Gelato();
        gelato.pruefen();


    }
}
