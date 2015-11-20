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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.cnes.fimoc.pds.tools.execption.FimocToolsException;
import fr.cnes.fimoc.pds.tools.model.Coordinate;

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
public class LabelReaderTest {

    /**
     * File path.
     */
    public static final String PATH = "src/test/resources/0580ML0024070000300011E01_DRCL.LBL"; //$NON-NLS-1$

    /**
     * File path.
     */
    public static final String PATH2 = "src/test/resources/0900MR0039440030501311C00_DRCL.LBL"; //$NON-NLS-1$
    /**
     * Test Constructor.
     * 
     * @throws FimocToolsException
     *             : Exception if file problems
     */
    @Test
    public void test() throws FimocToolsException {
        LabelReader labelR = new LabelReader(PATH);

        List<String> lines = labelR.getGroups(LabelReader.GROUP_IMAGE);

        Assert.assertTrue(lines.size() == 12);
        Assert.assertTrue(lines.get(3).equals(" MSL:MINIMUM_FOCUS_DISTANCE          = 3.9 <m>")); //$NON-NLS-1$
        Assert.assertTrue(lines.get(10).equals(" SOLAR_ELEVATION                     = 39.2011")); //$NON-NLS-1$

        Assert.assertNull(labelR.getGroups("OKOK")); //$NON-NLS-1$

        lines = labelR.getGroups(LabelReader.GROUP_HGA);
        Assert.assertTrue(lines.size() == 7);
    }

    /**
     * Test Extractor.
     * 
     * @throws FimocToolsException
     *             : Exception if file problems
     */
    @Test
    public void extraxtDouble() throws FimocToolsException {
        LabelReader labelR = new LabelReader(PATH);
        List<Double> doubles = labelR
                .extraxtDoubleFromString(" ARTICULATION_DEVICE_ANGLE           = ( 1.703018 <rad>, 1.580993 <rad>,"); //$NON-NLS-1$

        Assert.assertTrue(doubles.size() == 2);

        List<Double> doubles2 = labelR
                .extraxtDoubleFromString(" ARTICULATION_DEVICE_ANGLE           = ( -1.703018 <rad>, 1.580993 <rad>,"); //$NON-NLS-1$

        Assert.assertTrue(doubles2.size() == 2);
        Assert.assertTrue(doubles2.get(0) == -1.703018);
    }

    /**
     * Test Extractor.
     * 
     * @throws FimocToolsException
     *             : Exception if file problems
     */
    @Test
    public void getAzimuthElevation() throws FimocToolsException {
        LabelReader labelR = new LabelReader(PATH);
        Coordinate coord = labelR.getAzimuthElevation();

        Assert.assertTrue(coord.getAzimuth() == 88.3804);
        Assert.assertTrue(coord.getElevation() == 6.4103);
    }

}
