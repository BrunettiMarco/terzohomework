package it.unimol.homework.ui.show;

import it.unimol.homework.application.recipes.Recipe;
import it.unimol.homework.application.recipes.RecipesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Set;

/**
 * Mostra i titoli di una lista di ricette generata da {@link RecipesManager}, reindirizza su {@link DetailsRecipeJFrame}
 * in seguito al click su uno dei titoli visualizzati; si chiude automaticamente dopo la scelta della ricetta
 *
 *  La chiusura di questo JFrame non causerà la chiusura dal programma, per maggiori informazioni:
 *  @see JFrame {@link #setDefaultCloseOperation(int)} intero passato: {@code JFrame.DISPOSE_ON_CLOSE}
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class ShowRecipesJFrame extends JFrame {

    private static ShowRecipesJFrame ourInstance = new ShowRecipesJFrame();

    public static ShowRecipesJFrame getInstance() {
        return ourInstance;
    }

    private static final int windowWidth = 600;
    private static final int windowHeight = 420;

    private static final Insets standardInsetsValues = new Insets( 5, 5, 5, 5 );

    private Set<Recipe> recipeSet;

    private JPanel ricetteInserite;
    private JPanel chosenRecipe;

    private JLabel chosenLabel;

    private ShowRecipesJFrame(){
        super( "Visualizza ricette" );

        this.recipeSet = RecipesManager.getInstance().getRecipeSet();
        this.chosenLabel = new JLabel("Clicca su un elemento per vedere la ricetta" );
    }

    public void run() {
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setResizable( false );

        this.buildShowRecipesPanel();

        this.startProgram();
    }

    private void startProgram() {
        this.getContentPane().add( this.ricetteInserite );
        this.setPreferredSize( new Dimension( windowWidth, windowHeight ) );

        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }

    private void buildShowRecipesPanel() {
        this.chosenRecipe = new JPanel();
        this.ricetteInserite = new JPanel();

        this.getContentPane().removeAll();

        this.ricetteInserite.setLayout( null );

        int y = 0;
        int fieldsStandardXParameter = 75;
        int totalSpaceLeftEmptyInTheJFrame = ( fieldsStandardXParameter * 2 );
        int fieldsStandardWidth = windowWidth - totalSpaceLeftEmptyInTheJFrame;

        this.chosenRecipe.setLayout( null );
        this.chosenRecipe.setBackground( Color.WHITE );
        this.chosenLabel.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );

        this.chosenLabel.setBounds(
                fieldsStandardXParameter,
                20,
                fieldsStandardWidth,
                20
        );
        this.chosenRecipe.setBounds(
                fieldsStandardXParameter,
                60,
                fieldsStandardWidth,
                300
        );

        for ( Recipe recipe : this.recipeSet ) {
            JButton titleButton = new JButton( recipe.getTitle() );

            titleButton.setBackground( Color.WHITE );
            titleButton.setFont( new Font("SourceCodePro", Font.PLAIN, 18 ) );
            titleButton.setMargin( standardInsetsValues );
            titleButton.setHorizontalAlignment( SwingConstants.LEFT );

            titleButton.setBounds(
                    0,
                     y,
                     fieldsStandardWidth,
                    30
            );

            titleButton.addActionListener( e -> {
                this.openThisRecipe( recipe );

                this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING ) );
            });

            this.chosenRecipe.add( titleButton );

            y += 30;
        }

        this.ricetteInserite.add( this.chosenLabel );
        this.ricetteInserite.add( this.chosenRecipe );
    }

    private void openThisRecipe( Recipe recipe ) {
        DetailsRecipeJFrame detailsRecipeJFrame = new DetailsRecipeJFrame( recipe );
        detailsRecipeJFrame.run();
    }
}
