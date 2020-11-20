package it.unimol.homework.application.recipes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe di Test delle varie funzioni di {@link RecipesManager}.
 * Per preservare la persistenza dei dati già inseriti se il file "recipers.sr" esiste già verrà creato un nuovo file
 * "preserve_recipes_for_testing.sr" contente una copia delle ricette già immesse, successivamente il test partirà con
 * il test generale, alla fine del test il file "recipers.sr" originale verrà ripristinato e il file di appoggio
 * "preserve_recipes_for_testing.sr" eliminato
 *
 * @author Marco Brunetti
 * @version 1.0
 */
class RecipesManagerTest {

    @BeforeAll
    static void testBeforeAll() {
        File f1 = new File("recipes.sr" );
        if ( f1.exists() ) {
            File f2 = new File("preserve_recipes_for_testing.sr" );

            boolean isRenamed = f1.renameTo( f2 );
            if ( !isRenamed )
                fail("impossibile eseguire il test perchè non si può rinominare il file di testing");
        }
    }

    @AfterAll
    static void testAfterAll() {
        File fileOfTesting = new File("recipes.sr" );
        if ( fileOfTesting.exists() ) {
            boolean isDeleted = fileOfTesting.delete();
            if ( !isDeleted )
                fail( "Impossibile cancellare il file con i dati di testing" );
        }

        File f1 = new File("preserve_recipes_for_testing.sr" );
        if ( f1.exists() ) {
            File f2 = new File("recipes.sr" );

            boolean isRenamed = f1.renameTo( f2 );
            if ( !isRenamed )
                fail( "Errore nella fase finale del test, impossibile rinominare nuovamente il file" );
        }
    }

    @Test
    void testLaunchTimeRecipeInvalidException(){
        try {
            Recipe recipe = new Recipe(
                    "testException",
                    "",
                    "",
                    0
            );
            fail( "Non lancia l'eccezione sul valore tempo non valido" );
        } catch (TimeRecipeInvalidException ignored) {
        }
    }

    @Test
    void testSingleAdditionRecipe() throws TimeRecipeInvalidException {
        Recipe recipe = new Recipe(
                "test1",
                "",
                "",
                1
        );

        boolean isAdded = RecipesManager.getInstance().addRecipe( recipe );
        if ( !isAdded )
            fail( "Non aggiunto" );
    }

    @Test
    void testMultipleAdditionRecipe() throws TimeRecipeInvalidException {
        Recipe recipe1 = new Recipe(
                 "test2",
                "",
                "",
                1
        );
        Recipe recipe2 = new Recipe(
                "test2",
                "",
                "",
                1
        );
        Recipe recipe3 = new Recipe(
                "test3",
                "",
                "",
                1
        );

        RecipesManager.getInstance().addRecipe( recipe1 );

        boolean isAdded = RecipesManager.getInstance().addRecipe( recipe2 );
        if ( isAdded )
            fail( "Aggiunto di nuovo lo stesso elemento" );

        isAdded = RecipesManager.getInstance().addRecipe( recipe3 );
        if ( !isAdded )
            fail( "Impossibile aggiungere un nuovo elemento" );
    }

    @Test
    void testSaveIntoFile() throws TimeRecipeInvalidException {
        Recipe recipe = new Recipe(
                "test4",
                "",
                "",
                1
        );

        RecipesManager.getInstance().addRecipe( recipe );

        try {
            RecipesManager.getInstance().saveStoredRecipes();
        } catch ( InterruptedException e ) {
            fail( e.getMessage() );
        }
    }

    @Test
    void testLoadRecipesStored() throws TimeRecipeInvalidException {
        Recipe recipe1 = new Recipe(
                "test5",
                "",
                "",
                1
        );
        Recipe recipe2 = new Recipe(
                "test6",
                "",
                "",
                1
        );

        RecipesManager.getInstance().addRecipe( recipe1 );
        RecipesManager.getInstance().addRecipe( recipe2 );

        try {
            RecipesManager.getInstance().saveStoredRecipes();
        } catch ( InterruptedException e ) {
            fail( e.getMessage() );
        }

        RecipesManager.getInstance().loadStoredRecipes();
    }
}