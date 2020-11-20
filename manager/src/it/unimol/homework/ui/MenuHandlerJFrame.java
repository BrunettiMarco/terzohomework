package it.unimol.homework.ui;

import it.unimol.homework.ui.insert.NewRecipeJFrame;
import it.unimol.homework.ui.show.ShowRecipesJFrame;

import javax.swing.*;
import java.awt.*;

/**
 * E' la prima pagina del programma, un menu con 2 scelte possibili può indirizzare su {@link NewRecipeJFrame}
 * o su {@link ShowRecipesJFrame} aprendoli un un nuovo JFrame, conservando questa finestra aperta.
 *
 * La chiusura di questo JFrame causerà la chiusura dal programma, per maggiori informazioni:
 * @see JFrame {@link #setDefaultCloseOperation(int)} intero passato: {@code JFrame.EXIT_ON_CLOSE}
 *
 * @author Marco Brunetti
 * @version 1.0
 */
public class MenuHandlerJFrame extends JFrame {

    private static MenuHandlerJFrame ourInstance = new MenuHandlerJFrame();

    public static MenuHandlerJFrame getInstance() {
        return ourInstance;
    }

    private JPanel principalMenu;

    private MenuHandlerJFrame(){
        super( "Ricette" );

        this.principalMenu = new JPanel();
    }

    public void run() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setResizable( false );

        this.buildMenu();

        this.startProgram();
    }

    private void startProgram() {
        this.getContentPane().add( this.principalMenu );

        this.setPreferredSize( new Dimension(400, 400 ) );

        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }

    private void buildMenu() {
        this.principalMenu.setLayout( null );

        JButton addNewRecipeButton = new JButton( "Nuova ricetta" );
        JButton showAddedRecipesButton = new JButton( "Visualizza ricette" );

        addNewRecipeButton.addActionListener( e -> {
            this.loadJPanelNewRecipe();
        });
        showAddedRecipesButton.addActionListener( e -> {
            this.loadJPanelRicetteInserite();
        });

        addNewRecipeButton.setBounds(
                100,
                90,
                200,
                50
        );
        showAddedRecipesButton.setBounds(
                100,
                180,
                200,
                50
        );

        this.principalMenu.add( addNewRecipeButton );
        this.principalMenu.add( showAddedRecipesButton );
    }

    private void loadJPanelNewRecipe(){
        NewRecipeJFrame.getInstance().run();
    }

    private void loadJPanelRicetteInserite(){
        ShowRecipesJFrame.getInstance().run();
    }
}
