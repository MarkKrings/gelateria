import java.text.DecimalFormat;
import java.util.Scanner;

public class Gelato {

    DecimalFormat kS = new DecimalFormat("#0.00");          //objekt (DezimalFormat) kS wird neu angelegt - alle Zahlen mit kS. werden auf 2 Kommastellen gekürzt

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    float fehlendesGeld = 0f;                                                                        //wenn der Betrag das Budget übersteigt (Kasse)
    int entfernen = 0;                                                                               //Scannereingabe wird an Methode löschen weitergegeben
    int bezahlenZurueck = 0;                                                                         //Scannereingabe für Methode Kasse
    int auswahl = 0;                                                                                 //Scannereingabe für Methode Menü
    float budget = 0;                                                                                //Scannereingabe Budget
    float rueckgeld;                                                                                 //Budget-EisPreisGesamt[3]
    boolean checkFloat = false;                                                                      //sobald true wird checkFloat beendet

    public static float[] eisPreis = {2.50f, 1.20f, 0.90f};                                           //Preis
    public static float[] eisPreisGesamt = {0f, 0f, 0f, 0f};                                          //Gesamtpreise
    public static int[] eisMenge = {0, 0, 0};                                                         //Menge
    public static String[] eisSorte = {"Ed von Schleck", "Kaktuseis", "Flutschfinger"};               //Sorte


    public void pruefen() {                                                                          //Prüfmethode

        Scanner sc = new Scanner(System.in);                                                         //es wird ein Scanner zur Eingabe aufgerufen
        while (checkFloat != true) {                                                                 //solange checkFloat unwahr ist if Schleife durchlaufen

            System.out.println(ANSI_BLUE + "Bitte geben Sie Ihr Budget ein.");

            if (sc.hasNextFloat()) {                                                                //wenn budget ein Float ist...
                budget = sc.nextFloat();                                                            //float in budget speichern
                checkFloat = true;                                                                  //abbruchbedingung
            } else {                                                                                //wenn budget kein Float ist...
                System.out.println(ANSI_RED + "\nEingabefehler...\n" + ANSI_RESET);
                pruefen();                                                                          //Methode prüfen von vorne
            }
        }

        while (checkFloat == true) {                                                                //solange checkFloat wahr ist folgendes Anzeigen

            warnung();

            System.out.println(ANSI_YELLOW + "\nWir haben 3 Eissorten im Sortiment \n \n" + ANSI_RESET +               //Anzeigemenü
                    "1| " + eisSorte[0] + " " + kS.format(eisPreis[0]) + "€\n" +
                    "2| " + eisSorte[1] + " " + kS.format(eisPreis[1]) + "€\n" +
                    "3| " + eisSorte[2] + " " + kS.format(eisPreis[2]) + "€\n" +
                    "4| Zum Warenkorb\n" +
                    "5| Programm beenden");


            if (sc.hasNextInt()) {                                                                  //Scannereingabe auf Int prüfen
                auswahl = sc.nextInt();                                                             //wenn es ein Int ist in auswahl speichern
                menue(auswahl);                                                                     //auswahl wird an menü übergeben
                break;                                                                              //wenn die bedingung erfüllt ist abbruch
            } else {
                System.out.println(ANSI_RED + "Eingabefehler, wählen Sie bitte neu" +
                        "\n" + ANSI_RESET);
                pruefen();                                                                          //sonst wieder die Methode prüfen aufrufen
            }
        }

        Scanner bc = new Scanner(System.in);                                                        //neues Eingabeobjekt für das Untermenü
        System.out.println(ANSI_GREEN + "\n6| Bezahlen" + ANSI_RESET + ANSI_YELLOW +
                "\n7| Zurück\n" + ANSI_RED + "8| Entfernen" + ANSI_RESET);                          //Untermenü Warenkorb bezahlen oder zurück

        if (bc.hasNextInt()) {                                                                      //Eingabe muss ein Int sein
            bezahlenZurueck = bc.nextInt();                                                         //Speichert die Eingabe in bezahlenZurueck
            kasse(bezahlenZurueck);                                                                 //Gibt die Eingabe an die Methode Kasse weiter
        } else {
            pruefen();                                                                              //Wenn es kein Int ist, Methode prüfen wiederholen
        }

        Scanner cc = new Scanner(System.in);                                                        //Eingabe um Artikel zu entfernen

        if (cc.hasNextInt()) {                                                                      //Eingabe muss wieder ein int sein
            entfernen = cc.nextInt();                                                               //speichert die Eingabe in entfernen
            loeschen(entfernen);                                                                    //gibt den Int an Methode löschen

        } else {
            pruefen();                                                                              //Wenn es kein int ist - prüfen wiederholen
        }
    }

    public void menue(int x) {                                                                      //int x (welcher case)  wird an menü übergeben

        switch (x) {

            case 1:     //ed von Schleck
                eisPreisGesamt[0] = eisPreis[0] * eisMenge[0]; //EisPreis ed von Schleck
                eisMenge[0]++;                                 //EisMenge ed von Schleck
                System.out.println(ANSI_PURPLE + "\n*********************************\n* " + eisMenge[0] + " " + eisSorte[0] +
                        " im Warenkorb " + "*\n*********************************" + ANSI_RESET);
                pruefen();
                break;

            case 2:     //Kaktuseis
                eisPreisGesamt[1] = eisPreis[1] * eisMenge[1];  //EisPreis Kaktuseis
                eisMenge[1]++;                                  //EisMenge Kaktuseis
                System.out.println(ANSI_BLUE + "\n****************************\n* " + eisMenge[1] + " " + eisSorte[1] +
                        " im Warenkorb " + "*\n****************************" + ANSI_RESET);
                pruefen();
                break;

            case 3:    //Flutschfinger
                eisPreisGesamt[2] = eisPreis[2] * eisMenge[2];  //EisPreis Flutschfinger
                eisMenge[2]++;                                  //EisMenge Flutschfinger
                System.out.println(ANSI_CYAN + "\n********************************\n* " + eisMenge[2] + " " + eisSorte[2] +
                        " im Warenkorb " + "*\n********************************" + ANSI_RESET);
                pruefen();
                break;

            case 4:
                warenkorb();                                    //Übersicht Warenkorb
                break;

            case 5:
                System.out.println("Auf Wiedersehen!");         //Vorgang beenden!
                System.exit(110);
                break;
            default:
                System.out.println("Ihre Auswahl ist leider nicht korrekt");
        }
    }

    public void warenkorb() {                                   //Methode Warenkorb / EisPreisGesamt wird durch switch case im Menü ermittelt

        System.out.println(ANSI_PURPLE + "\n" + ANSI_RESET + ANSI_YELLOW + "Warenkorb\n" + ANSI_PURPLE + "*********\n" + ANSI_RESET +
                "\nSorte| " + eisSorte[0] + "\nMenge| " + eisMenge[0] + "\nPreis| " + kS.format(eisPreisGesamt[0]) +
                "€\n" + "\nSorte| " + eisSorte[1] + "\nMenge| " + eisMenge[1] + "\nPreis| " + kS.format(eisPreisGesamt[1]) +
                "€\n" + "\nSorte| " + eisSorte[2] + "\nMenge| " + eisMenge[2] + "\nPreis| " + kS.format(eisPreisGesamt[2]) +
                "€\n\n" + ANSI_GREEN + "*****************\n*" + ANSI_BLUE + " Gesamt| " + kS.format(eisPreisGesamt[3]) +
                "€" + ANSI_GREEN + " *\n* " + ANSI_BLUE + "Budget| " + kS.format(budget) + "€" + ANSI_GREEN + " *\n" +
                ANSI_GREEN + "*****************" + ANSI_RESET);
    }

    public void kasse(int y) {

        rueckgeld=budget-eisPreisGesamt[3];

        switch (y) {

            case 6:  //Kasse
                if (budget >= eisPreisGesamt[3]) {      //Man kann nur zur Kasse, wenn das Budget >= eisPreisGesamt ist.
                    System.out.println(ANSI_PURPLE + "\n******************************" + "\n" + ANSI_YELLOW + "Kassenbon"
                            + "\n\nSorte| " + eisSorte[0] + "\nMenge| " + eisMenge[0] + "\nPreis| " + kS.format(eisPreisGesamt[0])
                            + "\n\nSorte| " + eisSorte[1] + "\nMenge| " + eisMenge[1] + "\nPreis| " + kS.format(eisPreisGesamt[1])
                            + "\n\nSorte| " + eisSorte[2] + "\nMenge| " + eisMenge[2] + "\nPreis| " + kS.format(eisPreisGesamt[2])
                            + "\n\nDas kostet insgesamt " + kS.format(eisPreisGesamt[3]) + "€\nIhr Rückgeld beträgt "
                            + kS.format(rueckgeld) + "€\n\nVielen Dank für Ihren Einkauf \nund einen schönen Tag.\n"
                            + ANSI_PURPLE + "******************************" + ANSI_RESET);
                    System.exit(110);
                } else {              // Hier wird das fehlende Geld ermittelt und ausgegeben.
                    fehlendesGeld = eisPreisGesamt[3] - budget;
                    System.out.println("\n" + ANSI_PURPLE + "********************\n" + ANSI_RESET + ANSI_RED_BACKGROUND + ANSI_BLACK +
                            "Es Fehlen noch " + kS.format(fehlendesGeld) + "€" + ANSI_RESET + ANSI_PURPLE + "\n********************\n" + ANSI_RESET);
                    pruefen();
                    kasse(y);
                    break;
                }

            case 7:                 //zurück
                System.out.println("\n\n" + ANSI_PURPLE + "***************\n" + ANSI_RESET + ANSI_RED_BACKGROUND + "Zurück zum Menü" +
                        ANSI_RESET + ANSI_PURPLE + "\n***************" + ANSI_RESET);
                pruefen();
                kasse(y);

            case 8:                 //löschen
                warenkorb();
                System.out.println("\n1| 2,50€ Ed von Schleck entfernen\n2| 1,20€ Kaktuseis entfernen" +
                        "\n3| 0,90€ Flutschfinger entfernen");

                break;
                default:

        }
    }

    public void loeschen(int z) {                           //int z vom switch case wird an Methode löschen weitergegeben

        switch (z) {
            case 1:
                if (eisPreisGesamt[0]>0) {      //If case damit die Menge nicht <= 0 wird
                    System.out.println(ANSI_PURPLE + "\n*************************************\n" + ANSI_RED_BACKGROUND + ANSI_BLACK +
                            "Sie haben ein " + eisSorte[0] + " " + "entfernt" + ANSI_RESET + "\n" + ANSI_PURPLE +
                            "*************************************\n" + ANSI_RESET);
                    kasse(entfernen);                                         //Scannereingabe
                    eisMenge[0]--;                                            //Menge wird abgezogen
                    eisPreisGesamt[0] = eisPreisGesamt[0] - eisPreis[0];      //einzelPreis wird abgezogen
                    eisPreisGesamt[3] = eisPreisGesamt[3] - eisPreis[0];      //GesamtPreis wird angepasst
                    rueckgeld=budget-eisPreisGesamt[3];
                    kasse(z);                                                 //Wert wird an Methode Kasse übergeben
                } else {
                    System.out.println(ANSI_RED + "\nSie haben kein Ed von Schleck mehr im Warenkorb" + ANSI_RESET);
                }
                warenkorb();                                                   //Sonst wieder zurück zum Warenkorb
                break;

            case 2:
                if (eisPreisGesamt[1]>0) {
                System.out.println(ANSI_PURPLE + "\n********************************\n" + ANSI_RED_BACKGROUND + ANSI_BLACK +
                        "Sie haben ein " + eisSorte[1] + " entfernt" + ANSI_RESET + "\n" + ANSI_PURPLE +
                        "********************************\n" + ANSI_RESET);
                        kasse(entfernen);                                       //Scannereingabe
                        eisMenge[1]--;                                          //Menge wird abgezogen
                        eisPreisGesamt[1] = eisPreisGesamt[1] - eisPreis[1];    //einzelPreis wird abgezogen
                        eisPreisGesamt[3] = eisPreisGesamt[3] - eisPreis[1];    //GesamtPreis wird angepasst
                        rueckgeld = budget - eisPreisGesamt[3];                 //Rückgeld wird ermittelt
                        kasse(z);                                               //Wert wird an Methode Kasse übergeben
                    } else {
                    System.out.println(ANSI_RED + "\nSie haben kein Kaktuseis mehr im Warenkorb" + ANSI_RESET);
                }
                warenkorb();
                break;

            case 3:
                if (eisPreisGesamt[2]>0) {
                    System.out.println(ANSI_PURPLE + "\n*****************************\n" + ANSI_RED_BACKGROUND + ANSI_BLACK +
                            "Sie haben ein " + eisSorte[2] + " entfernt" + ANSI_RESET + "\n" + ANSI_PURPLE +
                            "*****************************\n" + ANSI_RESET);
                    kasse(entfernen);                                           //Scannereingabe
                    eisMenge[2]--;                                              //Menge wird abgezogen
                    eisPreisGesamt[2] = eisPreisGesamt[2] - eisPreis[2];        //einzelPreis wird abgezogen
                    eisPreisGesamt[3] = eisPreisGesamt[3] - eisPreis[2];        //GesamtPreis wird angepasst
                    rueckgeld = budget - eisPreisGesamt[3];                     //Rückgeld wird ermittelt
                    kasse(z);                                                   //Wert wird an Methode Kasse übergeben
                }else {
                    System.out.println(ANSI_RED + "\nSie haben kein Flutschfinger mehr im Warenkorb" + ANSI_RESET);
                }
                warenkorb();
                break;
            default:

        }
    }

    public void warnung() {

        eisPreisGesamt[0] = eisMenge[0] * eisPreis[0];
        eisPreisGesamt[1] = eisMenge[1] * eisPreis[1];
        eisPreisGesamt[2] = eisMenge[2] * eisPreis[2];
        eisPreisGesamt[3] = eisPreisGesamt[0] + eisPreisGesamt[1] + eisPreisGesamt[2];  //GesamtPreis wird ermittelt

        if (budget < eisPreisGesamt[3]) {                                               //Warnung wenn der GesamtPreis das Budget übersteigt
            System.out.println("\n" + ANSI_PURPLE + "**********************************\n"
                    + ANSI_RESET + ANSI_RED_BACKGROUND + ANSI_BLACK + "Sie haben Ihr Budget überschritten" + ANSI_RESET + ANSI_PURPLE +
                    "\n**********************************\n" + ANSI_RESET);
        }
    }
}

