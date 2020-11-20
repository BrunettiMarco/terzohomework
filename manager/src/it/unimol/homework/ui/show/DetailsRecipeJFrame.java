package it.unimol.homework.ui.show;

import it.unimol.homework.application.recipes.Recipe;
import it.unimol.homework.application.timer.NotPositiveTimerValueException;
import it.unimol.homework.application.timer.RecipeTimer;
import it.unimol.homework.application.converter.SecondsConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Finestra dei dettagli: ricapitola i dati inseriti precedentemente e premuto il tasto {@code startButton} avvia un
 * thread.
 *
 * @see it.unimol.homework.application.timer.RecipeTimer per maggiori informazioni sulle funzionalità collegate.
 *
 * La chiusura di questo JFrame non causerà la chiusura dal programma, per maggiori informazioni:
 * @see JFrame {@link #setDefaultCloseOperation(int)} intero passato: {@code JFrame.DISPOSE_ON_CLOSE}
 *
 * @author Marco Brunetti
 * @version 1.0
 */
class DetailsRecipeJFrame extends JFrame {

    private static final int windowWidth = 600;
    private static final int windowHeight = 560;

    private static final Font styleChoosedForThisPage = new Font("SourceCodePro", Font.PLAIN, 18 );

    private JPanel detailsPanel;

    private Recipe recipe;

    DetailsRecipeJFrame(Recipe recipe) {
        super( "Dettagli ricetta" );

        this.detailsPanel = new JPanel();
        this.recipe = recipe;
    }

    void run() {
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setResizable( false );

        this.buildDetailPanel();

        this.startProgram();
    }

    private void startProgram() {
        this.getContentPane().add( this.detailsPanel );

        this.setPreferredSize( new Dimension( windowWidth, windowHeight ) );

        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }

    private void buildDetailPanel() {
        SecondsConverter secondsConverter = new SecondsConverter( this.recipe );

        int fieldsStandardXParameter = 75;
        int totalSpaceLeftEmptyInTheJFrame = ( fieldsStandardXParameter * 2 );
        int fieldsStandardWidth = windowWidth - totalSpaceLeftEmptyInTheJFrame;

        JLabel titleLabel = new JLabel("Titolo" );
        JLabel ingredientsLabel = new JLabel("Ingredienti" );
        JLabel preparationLabel = new JLabel("Preparazione" );

        JTextField titleTextField = new JTextField( this.recipe.getTitle() );
        JTextField TimeTextField = new JTextField("Tempo (" + secondsConverter.getTimeFormat() + ")" );

        JTextArea ingredientsTextArea = new JTextArea( this.recipe.getIngredients() );
        JTextArea preparationTextArea = new JTextArea( this.recipe.getPreparation() );

        JButton startButton = new JButton( "Avvia Timer" );

        this.detailsPanel.setLayout( null );

        titleLabel.setFont( styleChoosedForThisPage );
        ingredientsLabel.setFont( styleChoosedForThisPage );
        preparationLabel.setFont( styleChoosedForThisPage );
        TimeTextField.setFont( styleChoosedForThisPage );
        titleTextField.setFont( styleChoosedForThisPage );
        ingredientsTextArea.setFont( styleChoosedForThisPage );
        preparationTextArea.setFont( styleChoosedForThisPage );
        startButton.setFont( styleChoosedForThisPage );

        titleTextField.setEditable( false );
        ingredientsTextArea.setEditable( false );
        preparationTextArea.setEditable( false );
        TimeTextField.setEditable( false );

        titleTextField.setBackground( Color.WHITE );
        TimeTextField.setBackground( Color.WHITE );

        titleLabel.setBounds(
                fieldsStandardXParameter,
                20,
                fieldsStandardWidth,
                20
        );
        titleTextField.setBounds(
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
        ingredientsTextArea.setBounds(
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
        preparationTextArea.setBounds(
                fieldsStandardXParameter,
                310,
                fieldsStandardWidth,
                90
        );
        TimeTextField.setBounds(
                fieldsStandardXParameter,
                420,
                fieldsStandardWidth,
                30
        );
        startButton.setBounds(
                ( ( fieldsStandardWidth / 2 ) + fieldsStandardXParameter ),
                470,
                ( fieldsStandardWidth / 2 ),
                30
        );

        startButton.addActionListener( e -> {
            this.launchTimerThread();
        });

        this.detailsPanel.add( titleLabel );
        this.detailsPanel.add( titleTextField );
        this.detailsPanel.add( ingredientsLabel );
        this.detailsPanel.add( ingredientsTextArea );
        this.detailsPanel.add( preparationLabel );
        this.detailsPanel.add( preparationTextArea );
        this.detailsPanel.add( TimeTextField );
        this.detailsPanel.add( startButton );
    }

    private void launchTimerThread() {
        try {
            RecipeTimer recipeTimer = new RecipeTimer( this.recipe );
            Thread timeOverThread = new Thread( recipeTimer );

            timeOverThread.start();

            JOptionPane.showMessageDialog(
                    this,
                    "Timer avviato correttamente"
            );

            this.dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );
        } catch ( NotPositiveTimerValueException e ) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            this.dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );
        }
    }
}
