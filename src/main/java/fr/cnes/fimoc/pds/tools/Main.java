/*
* $Id$
* ======================================================
*
* Projet : MUSE ORBITO
* Produit par Capgemini.
*
* ======================================================
* HISTORIQUE
* FIN-HISTORIQUE
* ======================================================
*/
package fr.cnes.fimoc.pds.tools;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * <P>
 * Main function.
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class Main {

    /**
     * Logger.
     */
    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * Argument in directory.
     */
    private static final String IN = "in"; //$NON-NLS-1$

    /**
     * Argument out directory.
     */
    private static final String OUT = "out"; //$NON-NLS-1$

    /**
     * Argument img2png bin.
     */
    private static final String IMG2PNG = "img2png"; //$NON-NLS-1$

    /**
     * @param args
     *            : Arguments.
     */
    public static void main(String[] args) {

        String in = System.getProperty(IN);
        String out = System.getProperty(OUT);
        String img2png = System.getProperty(IMG2PNG);

        if (in == null || out == null || img2png == null) {
            logger.error(
                    "System properties needed : -Din=\"path directory to raw data\" -Dout=\"path directory for outputs\" -Dimg2png=\"path to bin img2png\""); //$NON-NLS-1$
            System.exit(1);
        }

        if (checkArgumentDirectory(IN, in)) {
            System.exit(2);
        }

        if (checkArgumentDirectory(OUT, out)) {
            System.exit(3);
        }

        if (checkArgumentFile(IMG2PNG, img2png)) {
            System.exit(4);
        }

        // booleanExpression ? value1 : value2

    }

    /**
     * Check Argument.
     * 
     * @param key
     *            : key property.
     * 
     * @param directory
     *            : path tested.
     * @return true if directory is Ok.
     */
    private static boolean checkArgumentDirectory(String key, String directory) {
        Path path = Paths.get(directory);
        boolean isOk = true;

        if (!Files.exists(path)) {
            isOk = false;
            logger.error("The argument <" + key + "> <" + directory + "> path doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else if (!Files.isDirectory(path)) {
            isOk = false;
            logger.error("The argument <" + key + "> <" + directory + "> is not a directory"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

        return isOk;
    }

    /**
     * Check Argument.
     * 
     * @param key
     *            : key argument.
     * 
     * @param file
     *            : path tested.
     * @return true if file exist.
     */
    private static boolean checkArgumentFile(String key, String file) {
        Path path = Paths.get(file);
        boolean isOk = true;
        if (Files.exists(path)) {
            isOk = false;
            logger.error("The argument <" + key + "> <" + file + "> is not a file"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return isOk;
    }

}
