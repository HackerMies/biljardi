package org.example.biljardi;
import java.io.*;

/** Luokka toteuttaa biljardipöydän varauksen, jolla on
 * pöydän numero, varauksen päivämäärä ja aika sekä varaajan nimi.
 * @author Olli-Kalle Sarlund
 * @version 1.0 2024/03/12
 */
public class biljardivarausolio implements Serializable {
    /**
     * Pöydän numero kokonaislukuna.
     */
    private int poytaNumero;
    /**
     * Pöydän varaajan nimi merkkijonona.
     */
    private String varaajanNimi;
    /**
     * Pöydän varauksen päivämäärä.
     */
    private String pvm;
    /**
     * Pöydän varauksen aika.
     */
    private String aika;


    /**
     * Pöydän varaus perustiedoilla.
     *
     * @param poytaNumero  int pöydän numero
     * @param pvm String varauksen pvm
     * @param aika String varauksen aika
     * @param varaajanNimi String varaajan nimi
     */
    public biljardivarausolio(int poytaNumero, String pvm, String aika, String varaajanNimi) {
        this.poytaNumero = poytaNumero;
        this.pvm = pvm;
        this.aika = aika;
        this.varaajanNimi = varaajanNimi;
    }

    /**
     * Palauttaa pöydän numeron
     * @return int poytaNumero
     */
    public int getPoytaNumero() {
        return poytaNumero;
    }

    /**
     * Asettaa pöydän numeron
     * @param poytaNumero int
     */
    public void setPoytaNumero(int poytaNumero) {
        this.poytaNumero = poytaNumero;
    }

    /**
     * Palauttaa varauksen päivämäärän
     * @return String pvm
     */
    public String getPvm() {
        return pvm;
    }

    /**
     * Asettaa varauksen päivämäärän
     * @param pvm String
     */
    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

    /**
     * Palauttaa varauksen ajan
     * @return String aika
     */
    public String getAika() {
        return aika;
    }

    /**
     * Asettaa varauksen ajan
     * @param aika String
     */
    public void setAika(String aika) {
        this.aika = aika;
    }

    /**
     * Palauttaa varaajan nimen
     * @return string varaajanNimi
     */
    public String getVaraajanNimi() {
        return varaajanNimi;
    }

    /**
     * Asettaa varaajan nimen
     * @param varaajanNimi string
     */
    public void setVaraajanNimi(String varaajanNimi) {
        this.varaajanNimi = varaajanNimi;
    }

    public String toString() {
        return "Pöydän numero: " + getPoytaNumero() + " Päivämäärä: " + getPvm() + " Aika: " + getAika() + " Varaajan nimi: " + getVaraajanNimi();
    }

    /**
     * Testipääohjelma
     * @param args ei parametreja käytössä.
     */
    public static void main(String[] args) {
        try {
            FileOutputStream fstream = new FileOutputStream("C:/Users/o-k/Desktop/OhjelmointiJava/varaukset.txt");
            ObjectOutputStream outputFile = new ObjectOutputStream(fstream);

            biljardivarausolio varaus1 = new biljardivarausolio(1, "2024-03-15", "16:30", "Olli");

                outputFile.writeObject(varaus1);

                outputFile.close();

                boolean tiedostoLoppu = false;

                FileInputStream fstream2 = new FileInputStream("C:/Users/o-k/Desktop/OhjelmointiJava/varaukset.txt");
                ObjectInputStream inputFile = new ObjectInputStream(fstream2);

                File varaustiedosto = new File("C:/Users/o-k/Desktop/OhjelmointiJava/varaukset.txt");

                Object tiedostoOlio;

                if (varaustiedosto.exists()) {
                    while (!tiedostoLoppu) {
                        try {
                            tiedostoOlio = inputFile.readObject();
                            System.out.println(tiedostoOlio);
                        } catch (EOFException e) {
                            tiedostoLoppu = true;
                        }
                    }
                }
                inputFile.close();

        } catch (IOException e) {
            System.out.println("Virhe tiedostoa käsiteltäessä.");
        } catch (ClassNotFoundException e) {
            System.out.println("Luokkaa ei löydetty.");
        }
    }
}