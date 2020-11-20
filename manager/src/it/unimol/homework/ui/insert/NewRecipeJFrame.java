package it.unimol.homework.ui.insert;

import it.unimol.homework.application.recipes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Costruisce una pagina dove è possibile raccogliere i dati della nuova ricetta, passa i dati
 * raccolti a {@link RecipesManager}
 *
 * La chiusura di questo JFrame non causerà la chiusura dal programma, per maggiori informazioni:
 * @see JFrame {@link #setDefaultCloseOperation(int)} intero passato: {@code JFrame.DISPOSE_ON_CLOSE}
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class NewRecipeJFrame extends JFrame {

    private static NewRecipeJFrame ourInstance = new NewRecipeJFrame();

    public static NewRecipeJFrame getInstance() {
        return ourInstance;
    }

    private static final int windowWidth = 600;
    private static final int windowHeight = 600;

    private static final Insets standardInsetsValues = new Insets( 5, 5, 5, 5 );

    private JPanel nuovaRicetta;

    private JTextField titleTextField;

    private JTextArea ingredientsTextArea;
    private JTextArea preparationTextArea;

    private JSpinner timeSpinner;

    private NewRecipeJFrame(){
        super( "Nuova ricetta" );

        this.nuovaRicetta = new JPanel();

        this.titleTextField = new JTextField();

        this.ingredientsTextArea = new JTextArea();
        this.preparationTextArea = new JTextArea();

        this.timeSpinner = new JSpinner(
                new SpinnerNumberModel(
                        0,
                        0,
                        null,
                        1
                )
        );

        this.buildNewRecipePanel();
    }

    public void run() {
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setResizable( false );

        this.resetAllFields();

        this.startProgram();
    }

    private void startProgram() {
        this.getContentPane().add( this.nuovaRicetta );

        this.setPreferredSize( new Dimension( windowWidth, windowHeight ) );

        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }

    private void buildNewRecipePanel() {
        int fieldsStandardXParameter = 75;
        int totalSpaceLeftEmptyInTheJFrame = ( fieldsStandardXParameter * 2 );
        int fieldsStandardWidth = windowWidth - totalSpaceLeftEmptyInTheJFrame;

        this.nuovaRicetta.setLayout( null );

        JLabel titleLabel = new JLabel("Titolo" );
        JLabel ingredientsLabel = new JLabel("Ingredienti" );
        JLabel preparationLabel = new JLabel("Preparazione" );
        JLabel timeNecessaryLabel = new JLabel("Tempo (in secondi)" );

        JButton saveButton = new JButton( "Save Recipe" );

        saveButton.addActionListener( e -> {
            try {
                this.tryToAddNewRecipe();
            } catch ( TimeRecipeInvalidException exception ) {
                JOptionPane.showMessageDialog(
                        this,
                        exception.getMessage()
                );
            }
        });

        titleLabel.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        ingredientsLabel.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        preparationLabel.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        timeNecessaryLabel.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        this.titleTextField.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        this.ingredientsTextArea.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        this.preparationTextArea.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        this.timeSpinner.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
        saveButton.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );

        this.titleTextField.setMargin( standardInsetsValues );
        this.ingredientsTextArea.setMargin( standardInsetsValues );
        this.preparationTextArea.setMargin( standardInsetsValues );

        titleLabel.setBounds(
                fieldsStandardXParameter,
                20,
                 fieldsStandardWidth,
                20
        );
        this.titleTextField.setBounds(
                fieldsStandardXParameter,
                60,
                fieldsStandardWidth,
                40
        );
        ingredientsLabel.setBounds(
                fieldsStandardXParameter,
                120,
                fieldsStandardWidth,
                20
        );
        this.ingredientsTextArea.setBounds(
                fieldsStandardXParameter,
                160,
                fieldsStandardWidth,
                90
        );
        preparationLabel.setBounds(
                fieldsStandardXParameter,
                270,
                fieldsStandardWidth,
                20
        );
        this.preparationTextArea.setBounds(
                fieldsStandardXParameter,
                310,
                fieldsStandardWidth,
                90
        );
        timeNecessaryLabel.setBounds(
                fieldsStandardXParameter,
                420,
                fieldsStandardWidth,
                20
        );
        this.timeSpinner.setBounds(
                fieldsStandardXParameter,
                460,
                fieldsStandardWidth,
                30
        );
        saveButton.setBounds(
                ( ( fieldsStandardWidth / 2 ) + fieldsStandardXParameter ),
                510,
                ( fieldsStandardWidth / 2 ),
                30
        );

        this.nuovaRicetta.add( titleLabel );
        this.nuovaRicetta.add( this.titleTextField );
        this.nuovaRicetta.add( ingredientsLabel );
        this.nuovaRicetta.add( this.ingredientsTextArea );
        this.nuovaRicetta.add( preparationLabel );
        this.nuovaRicetta.add( this.preparationTextArea );
        this.nuovaRicetta.add( timeNecessaryLabel );
        this.nuovaRicetta.add( this.timeSpinner );
        this.nuovaRicetta.add( saveButton );
    }

    private void tryToAddNewRecipe() throws TimeRecipeInvalidException {

        String title = this.titleTextField.getText();
        String istruzioni = this.preparationTextArea.getText();
        String listaIngredienti = this.ingredientsTextArea.getText();

        int timeNecessary = (Integer) this.timeSpinner.getValue();

        Recipe recipe = new Recipe(
                title,
                listaIngredienti,
                istruzioni,
                timeNecessary
        );

        boolean isAdded = RecipesManager.getInstance().addRecipe( recipe );
        if( isAdded ) {
            try {
                RecipesManager.getInstance().saveStoredRecipes();

                JOptionPane.showMessageDialog(
                        this,
                        "Ricetta salvata correttamente"
                );

                this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING ) );
            } catch ( InterruptedException e ) {
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage()
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Non è stato possibile aggiungere la ricetta alla lista, riprova cambiando il titolo"
            );
        }
    }

    private void resetAllFields() {
        this.titleTextField.setText("");
        this.ingredientsTextArea.setText("");
        this.preparationTextArea.setText("");
        this.timeSpinner.setValue(0);
    }
}
