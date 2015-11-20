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
package fr.cnes.fimoc.pds.tools.execption;

/**
 *
 * <P>
 * Generique Exception of fimoc tools.
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class FimocToolsException extends Exception {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public FimocToolsException() {
    }

    /**
     * @param msg
     *            : Exception message.
     */
    public FimocToolsException(String msg) {
        super(msg);
    }

    /**
     * @param msg
     *            : Exception message.
     * @param exception
     *            : exeption.
     */
    public FimocToolsException(String msg, Throwable exception) {
        super(msg, exception);
    }

}
