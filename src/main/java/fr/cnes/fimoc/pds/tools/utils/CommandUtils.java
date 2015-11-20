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
package fr.cnes.fimoc.pds.tools.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import fr.cnes.fimoc.pds.tools.execption.FimocToolsException;

/**
 *
 * <P>
 * Tools to run command line in java.
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class CommandUtils {

    /**
     * Launch bin with arguments.
     * 
     * @param args
     *            : argument.
     * @throws FimocToolsException
     *             : Throw when process problem;
     */
    public static void launch(String... args) throws FimocToolsException {

        try {
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.inheritIO();
            Process p = pb.start();
            int code = p.waitFor();
            if (code != 0) {
                throw new FimocToolsException("Problem to execute " + args[0] + " code :" + code); //$NON-NLS-1$ //$NON-NLS-2$
            }
        } catch (IOException | InterruptedException e) {
            throw new FimocToolsException("Problem to execute " + args[0] + " : " + e.getMessage(), e.getCause()); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * @param prefix
     *            : prefix.
     * @return : the path.
     * @throws FimocToolsException
     *             : problem with file directory.
     */
    public static Path createTempDir(String prefix) throws FimocToolsException {
        // create temp directory.
        Path pathExec = null;
        try {
            pathExec = Files.createTempDirectory(prefix);
        } catch (IOException e1) {
            throw new FimocToolsException("Problem to creation temp directory"); //$NON-NLS-1$
        }

        return pathExec;
    }

}
