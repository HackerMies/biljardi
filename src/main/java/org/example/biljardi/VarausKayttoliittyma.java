package org.example.biljardi;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.*;

/**
 * Biljardipöydän varausta käsittelevän luokan käyttöliittymä, jolla voidaan
 * tallentaa ja hakea varauksia.
 * @author Olli-Kalle Sarlund
 * @version 1.0 2024/04/03
 */
public class VarausKayttoliittyma extends Application {
    /**
     * Pöydän valitsemisen teksti
     */
    Label teksti1 = new Label("Valitse pöytä");
    /**
     * Pöydän varauksen tiputusvalikko
     */
    ChoiceBox<Integer> poydannumeroValinta = new ChoiceBox<>();

    /**
     * Päivämäärän valitsemis teksti
     */
    Label teksti2 = new Label("Kirjoita päivämäärä muodossa YYYY-MM-DD");
    /**
     * Päivämäärän tekstikenttä
     */
    TextField paivamaaraKentta = new TextField();
    /**
     * Kellonajan valitsemis teksti
     */
    Label teksti3 = new Label("Kirjoita kellonaika muodossa HH:MM-HH:MM");
    /**
     * Kellonajan tekstikenttä
     */
    TextField kellonaikaKentta = new TextField();
    /**
     * Varaajan nimi teksti
     */
    Label teksti4 = new Label("Varaajan nimi");
    /**
     * Varaajan nimen tekstikenttä
     */
    TextField varaajanNimiKentta = new TextField();
    /**
     * Pöydän varaus nappi
     */
    Button varausNappi = new Button("Vahvista varaus");
    /**
     * Varausten näyttämis nappi
     */
    Button varauslistaNappi = new Button("Näytä varaukset");
    /**
     * Ohjelmaikkunan käynnistyksen ja toiminnallisuuden määrittely
     */
    @Override
    public void start(Stage primaryStage) {
        // Luodaan paneeli
        AnchorPane Paneeli = new AnchorPane();
        Scene scene = new Scene(Paneeli, 500, 500);
        primaryStage.setTitle("Biljardipöydän varaus");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Määritellään asettelua
        AnchorPane.setTopAnchor(teksti1, 100.0);
        AnchorPane.setLeftAnchor(teksti1, 100.0);
        poydannumeroValinta.getItems().addAll(1, 2, 3, 4, 5, 6);
        AnchorPane.setTopAnchor(poydannumeroValinta, 120.0);
        AnchorPane.setLeftAnchor(poydannumeroValinta, 115.0);
        AnchorPane.setTopAnchor(teksti2, 180.0);
        AnchorPane.setLeftAnchor(teksti2, 30.0);
        AnchorPane.setTopAnchor(paivamaaraKentta,200.0);
        AnchorPane.setLeftAnchor(paivamaaraKentta, 65.0);
        AnchorPane.setTopAnchor(teksti3, 260.0);
        AnchorPane.setLeftAnchor(teksti3, 30.0);
        AnchorPane.setTopAnchor(kellonaikaKentta, 280.0);
        AnchorPane.setLeftAnchor(kellonaikaKentta, 65.0);
        AnchorPane.setTopAnchor(teksti4, 340.0);
        AnchorPane.setLeftAnchor(teksti4, 100.0);
        AnchorPane.setTopAnchor(varaajanNimiKentta,360.0);
        AnchorPane.setLeftAnchor(varaajanNimiKentta, 65.0);
        AnchorPane.setTopAnchor(varausNappi,420.0);
        AnchorPane.setLeftAnchor(varausNappi, 90.0);
        AnchorPane.setTopAnchor(varauslistaNappi,420.0);
        AnchorPane.setLeftAnchor(varauslistaNappi, 300.0);
        Paneeli.setStyle("-fx-background-image: url('file:c:/users/o-k/desktop/ohjelmointijava/biljardi/image/biljardipoyta.jpeg')");

        /**
         * Luodaan uusi varaus ja tallennetaan se tiedostoon.
         */
        varausNappi.setOnAction(event -> {
            int poydanNumero = poydannumeroValinta.getValue();
            String paivamaara = paivamaaraKentta.getText();
            String kellonaika = kellonaikaKentta.getText();
            String varaajanNimi = varaajanNimiKentta.getText();

            Biljardivarausolio varausolio = new Biljardivarausolio(poydanNumero, paivamaara, kellonaika, varaajanNimi);
            // Kirjoitetaan tiedostoon varaus
            try (BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter("C:/Users/o-k/Desktop/OhjelmointiJava/biljardi/varaukset/varaukset1.txt", true))) {
                kirjoittaja.write(varausolio.getPoytaNumero() + ", " + varausolio.getPvm() + ", " + varausolio.getAika() + ", " + varausolio.getVaraajanNimi());
                kirjoittaja.newLine();
            } catch (IOException e) {
                System.out.println("Virhe tiedostoon kirjoittamisessa.");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varaus tallennettu");
            alert.setHeaderText("Biljardipöydän varaus on tallennettu onnistuneesti!");
            alert.showAndWait();
        });

        /**
         * Haetaan varaukset tiedostosta ja näytetään ne.
         */
        varauslistaNappi.setOnAction(event -> {
            VBox laatikko = new VBox();
            laatikko.setStyle("-fx-border-color: black; -fx-padding: 50px;");

            // Luetaan tiedostosta varaukset
            try (BufferedReader lukija = new BufferedReader(new FileReader("C:/Users/o-k/Desktop/OhjelmointiJava/biljardi/varaukset/varaukset1.txt"))) {
                String rivi1;
                while ((rivi1 = lukija.readLine()) != null) {
                    String[] osat = rivi1.split(",");
                    // Luodaan uusi tekstikenttä jokaiselle varaukselle
                    TextField tekstikentta = new TextField();
                    tekstikentta.setText(osat[0] + " " + osat[1] + " " + osat[2] + " " + osat[3]);
                    tekstikentta.setEditable(false);
                    laatikko.getChildren().add(tekstikentta);
                }
            } catch (IOException e) {
                System.err.println("Virhe. Tiedosto löytyi, mutta lukeminen päättyi virheeseen.");
            }

            Scene scene2 = new Scene(laatikko, 400, 300);
            Stage stage = new Stage();
            stage.setTitle("Varaukset");
            stage.setScene(scene2);
            stage.show();
        });

        // Lisätään solmut
        Paneeli.getChildren().add(teksti1);
        Paneeli.getChildren().add(poydannumeroValinta);
        Paneeli.getChildren().add(teksti2);
        Paneeli.getChildren().add(paivamaaraKentta);
        Paneeli.getChildren().add(teksti3);
        Paneeli.getChildren().add(kellonaikaKentta);
        Paneeli.getChildren().add(teksti4);
        Paneeli.getChildren().add(varaajanNimiKentta);
        Paneeli.getChildren().add(varausNappi);
        Paneeli.getChildren().add(varauslistaNappi);
    }

    /**
     * Ohjelman käynnistävä metodi
     * @param args kutsuparametreja ei käytetä
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    }