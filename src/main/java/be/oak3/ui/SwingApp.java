package be.oak3.ui;

import be.oak3.model.Product;
import be.oak3.persistence.Data;

import javax.swing.*;
import java.awt.*;

public class SwingApp extends JFrame {

    private JLabel lblTitel, lblResultaatInvoer, lblResultaatButton;
//    Een JLabel is een simpel tekstje, vb voor een titel
    private JList<Product> lstProducten;
//    private JTextField txtInvoer;
    private JButton btnResultaat;


    public SwingApp() {
        initComponents();
//        add(new JLabel("Hello World"), BorderLayout.NORTH);
//        zal default links in het midden staan, met 2de parameter (BorderLayout) verplaatsen we het
        layoutComponents();
        initListeners();
    }

//    volgende methode dient om enkele componenten te initialiseren
    private void initComponents(){
        setTitle("Eerste Swing Applicatie");
        setSize(500, 500);
//        hangt af van je scherem (wij hebben full HD)
        setLocation(100, 100);
//        plaats waar het venster zich zal openen

        lblTitel = new JLabel("Swing applicatie");
//       check de opties van lblTitel.set()!
//        txtInvoer = new JTextField(10);
//        txtInvoer.setBackground(Color.BLUE);


        lblResultaatInvoer = new JLabel();
        lblResultaatButton = new JLabel();

        lstProducten = new JList<>();
        lstProducten.setListData(Data.artikels);
//        voor de opgave ga je data.artikels moeten veranderen door andere dingen
//        de JList gaat telkens weer vervangen moeten worden door nieuwe data met setListData
//
//        Volgende code kan helpen bij het maken van de Swing app, maar is niet correct (staat dooreen)
//        BestellingImpl bestelling = new BestellingImpl();
//        bestelling.sorteer();
//        lstProducten.setListData(bestelling.lijstVanParfums().toArray(new Product[0]));
//        bestelling.sorteerOpMerk();
//        lstProducten.setListData(bestelling.getProducten().toArray(new Product[0]));
//        getProducten() is een method die toegevoegd moet worden in BestellingImpl (en in de interface Bestelling), die een List terug geeft.
//        Dit is al nodig in het deel van de testen.


        btnResultaat = new JButton("Doe iets!");


        setVisible(true);
//        zal default altijd op false staan, dus als je dit niet doet, zie je niets
    }

    private void layoutComponents(){
//      Als je de layout wil veranderen (dus geen gebruik wil maken van de standaard BorderLayout):
//        setLayout(new FlowLayout());

        add(lblTitel, BorderLayout.NORTH);
//        add(txtInvoer, BorderLayout.CENTER);
        add(lstProducten, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(btnResultaat);
        southPanel.add(lblResultaatButton);
        southPanel.add(lblResultaatInvoer);
//                de standaard layout voor een JPanel is flowlayout


        add(southPanel, BorderLayout.SOUTH);
    }

    private void initListeners(){
        btnResultaat.addActionListener(e -> {
            lblResultaatButton.setText("Ik heb iets gedaan");
            lblResultaatInvoer.setText("Hello");

            lblResultaatInvoer.setOpaque(true);
            lblResultaatButton.setOpaque(true);

            lblResultaatInvoer.setBackground(Color.GRAY);
            lblResultaatButton.setBackground(Color.RED);
        });
    }
//    Listeners zijn ideaal om te gebruiken met lamda-expressies

    public static void main(String[] args) {
        new SwingApp();
    }

}
