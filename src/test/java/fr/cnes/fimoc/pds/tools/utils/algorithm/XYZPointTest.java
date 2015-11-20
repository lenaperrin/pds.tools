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
package fr.cnes.fimoc.pds.tools.utils.algorithm;

import org.junit.Assert;
import org.junit.Test;

import fr.cnes.fimoc.pds.tools.algorithm.XYZPoint;

/**
 *
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class XYZPointTest {

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.XYZPoint#XYZPoint(double, double)}
     * .
     */
    @Test
    public void testXYZPointDoubleDouble() {
        XYZPoint point = new XYZPoint(10.0, 11.0);

        Assert.assertNotNull(point);
        Assert.assertTrue(point.getX() == 10.0);
        Assert.assertTrue(point.getY() == 11.0);
        Assert.assertTrue(point.getZ() == 0.0);
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.XYZPoint#XYZPoint(double, double, double)}
     * .
     */
    @Test
    public void testXYZPointDoubleDoubleDouble() {
        XYZPoint point = new XYZPoint(10.0, 11.0, 12.0);

        Assert.assertNotNull(point);
        Assert.assertTrue(point.getX() == 10.0);
        Assert.assertTrue(point.getY() == 11.0);
        Assert.assertTrue(point.getZ() == 12.0);
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.XYZPoint#euclideanDistance(fr.cnes.fimoc.pds.tools.algorithm.XYZPoint)}
     * .
     */
    @Test
    public void testEuclideanDistance() {

        XYZPoint point1 = new XYZPoint(0.0, 0.0);
        XYZPoint point2 = new XYZPoint(0.0, 0.0);
        XYZPoint point3 = new XYZPoint(3.0, 0.0);
        XYZPoint point4 = new XYZPoint(0.0, 3.0);
        XYZPoint point5 = new XYZPoint(3.0, 3.0);
        XYZPoint point6 = new XYZPoint(2.0, 6.0);
        XYZPoint point7 = new XYZPoint(4.0, 5.0);

        Assert.assertTrue(point1.euclideanDistance(point2) == 0.0);
        Assert.assertTrue(point1.euclideanDistance(point3) == 3.0);
        Assert.assertTrue(point1.euclideanDistance(point4) == 3.0);
        Assert.assertTrue(point1.euclideanDistance(point5) == 4.242640687119285);
        Assert.assertTrue(point6.euclideanDistance(point7) == 2.23606797749979);

    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.XYZPoint#equals(java.lang.Object)}
     * .
     */
    @Test
    public void testEqualsObject() {
        XYZPoint point1 = new XYZPoint(0.0, 0.0);
        XYZPoint point2 = new XYZPoint(0.0, 0.0);
        XYZPoint point3 = new XYZPoint(3.0, 0.0);
        XYZPoint point5 = new XYZPoint(4.0, 5.0);
        XYZPoint point7 = new XYZPoint(4.0, 5.0);

        Assert.assertEquals(point1, point2);
        Assert.assertNotEquals(point1, point3);
        Assert.assertEquals(point5, point7);
        Assert.assertEquals(point7, point5);
        Assert.assertNotEquals(point3, point5);
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.XYZPoint#compareTo(fr.cnes.fimoc.pds.tools.algorithm.XYZPoint)}
     * .
     */
    @Test
    public void testCompareTo() {
        XYZPoint point1 = new XYZPoint(0.0, 0.0);
        XYZPoint point2 = new XYZPoint(0.0, 0.0);
        XYZPoint point3 = new XYZPoint(3.0, 0.0);
        XYZPoint point5 = new XYZPoint(4.0, 5.0);
        XYZPoint point6 = new XYZPoint(2.0, 6.0);
        XYZPoint point7 = new XYZPoint(6.0, 2.0);

        Assert.assertTrue(point1.compareTo(point2) == 0);
        Assert.assertTrue(point3.compareTo(point5) == -1);
        Assert.assertTrue(point5.compareTo(point3) == 1);
        Assert.assertTrue(point6.compareTo(point5) == -1);
        Assert.assertTrue(point5.compareTo(point7) == -1);
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.XYZPoint#toString()}.
     */
    @Test
    public void testToString() {
        XYZPoint point7 = new XYZPoint(4.0, 5.0);
        Assert.assertTrue(point7.toString().equals("(4.0, 5.0, 0.0)")); //$NON-NLS-1$
    }

}
