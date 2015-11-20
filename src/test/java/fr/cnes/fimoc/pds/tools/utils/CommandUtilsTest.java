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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.cnes.fimoc.pds.tools.execption.FimocToolsException;

/**
 *
 * <P>
 * Test Class of LabelReader.
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class CommandUtilsTest {

    /**
     * File path.
     */
    public static final String PATH_RAW = "src/test/resources/raw/"; //$NON-NLS-1$

    /**
     * File path.
     */
    public static final String PATH_BIN = "src/test/resources/img2png/img2png.exe"; //$NON-NLS-1$

    /**
     * Test Constructor.
     * 
     * @throws FimocToolsException
     *             : Exception if file problems
     */
    @Test
    public void test() throws FimocToolsException {
        Path bin = Paths.get(PATH_BIN);
        Path raw = Paths.get(PATH_RAW);

        Assert.assertTrue(Files.isExecutable(bin));

        List<Path> png = new ArrayList<>();

        int nbFile = 0;
        for (File i : raw.toFile().listFiles()) {
            if (i.getName().contains(".IMG")) { //$NON-NLS-1$
                png.add(Paths.get(PATH_RAW, i.getName().replace(".IMG", ".png"))); //$NON-NLS-1$ //$NON-NLS-2$
                CommandUtils.launch(PATH_BIN, i.getAbsolutePath());
                nbFile++;
            } else if (i.getName().contains(".img")) { //$NON-NLS-1$
                png.add(Paths.get(PATH_RAW, i.getName().replace(".img", ".png"))); //$NON-NLS-1$ //$NON-NLS-2$
                CommandUtils.launch(PATH_BIN, i.getAbsolutePath());
                nbFile++;
            }

        }

        Assert.assertTrue(nbFile == png.size());

        // delete all png.
        for (Path p : png) {
            p.toFile().delete();
        }

    }

}
