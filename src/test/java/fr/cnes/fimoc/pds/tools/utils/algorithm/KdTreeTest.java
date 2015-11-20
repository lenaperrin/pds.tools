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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.cnes.fimoc.pds.tools.algorithm.KdTree;
import fr.cnes.fimoc.pds.tools.algorithm.XYZPoint;

/**
 *
 * <P>
 * SDD component:
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class KdTreeTest {

    /**
     * Test method for {@link fr.cnes.fimoc.pds.tools.algorithm.KdTree#KdTree()}
     * .
     */
    @Test
    public void testKdTree() {
        KdTree<XYZPoint> tree = new KdTree<XYZPoint>();
        Assert.assertNotNull(tree);
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.KdTree#KdTree(java.util.List)}.
     */
    @Test
    public void testKdTreeListOfXYZPoint() {

        List<XYZPoint> points = new ArrayList<XYZPoint>();

        for (int i = 0; i < 10; i++) {
            points.add(new XYZPoint(i * i + 10, i * i + 1));
        }

        KdTree<XYZPoint> tree = new KdTree<XYZPoint>(points);
        Assert.assertNotNull(tree);
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.KdTree#contains(fr.cnes.fimoc.pds.tools.algorithm.XYZPoint)}
     * .
     */
    @Test
    public void testContains() {
        KdTree<XYZPoint> tree = new KdTree<XYZPoint>();
        XYZPoint point1 = new XYZPoint(10.0, 11.0);
        XYZPoint point2 = new XYZPoint(11.0, 10.0);
        XYZPoint point3 = new XYZPoint(0.0, 0.0);

        tree.add(point1);
        tree.add(point2);
        tree.add(point3);

        XYZPoint point11 = new XYZPoint(10.0, 11.0);
        XYZPoint point22 = new XYZPoint(11.0, 10.0);
        XYZPoint point33 = new XYZPoint(0.0, 0.0);
        XYZPoint point44 = new XYZPoint(10.0, 32.0);

        Assert.assertTrue(tree.contains(point11));
        Assert.assertTrue(tree.contains(point22));
        Assert.assertTrue(tree.contains(point33));
        Assert.assertFalse(tree.contains(point44));

    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.KdTree#remove(fr.cnes.fimoc.pds.tools.algorithm.XYZPoint)}
     * .
     */
    @Test
    public void testRemove() {
        KdTree<XYZPoint> tree = new KdTree<XYZPoint>();
        XYZPoint point1 = new XYZPoint(10.0, 11.0);
        XYZPoint point2 = new XYZPoint(11.0, 10.0);
        XYZPoint point3 = new XYZPoint(0.0, 0.0);

        tree.add(point1);
        tree.add(point2);
        tree.add(point3);

        Assert.assertTrue(tree.contains(point2));
        tree.remove(point2);
        Assert.assertFalse(tree.contains(point2));
    }

    /**
     * Test method for
     * {@link fr.cnes.fimoc.pds.tools.algorithm.KdTree#nearestNeighbourSearch(int, fr.cnes.fimoc.pds.tools.algorithm.XYZPoint)}
     * .
     */
    @Test
    public void testNearestNeighbourSearch() {

        KdTree<XYZPoint> tree = new KdTree<XYZPoint>();
        XYZPoint point1 = new XYZPoint(10.0, 11.0);
        XYZPoint point2 = new XYZPoint(11.0, 10.0);
        XYZPoint point3 = new XYZPoint(13.0, 10.0);
        XYZPoint point4 = new XYZPoint(0.0, 0.0);
        XYZPoint point5 = new XYZPoint(1.0, 0.0);
        XYZPoint point6 = new XYZPoint(1.0, 1.0);
        XYZPoint point7 = new XYZPoint(1.4, 1.0);

        XYZPoint point8 = new XYZPoint(1.3, 1.1);

        tree.add(point1);
        tree.add(point2);
        tree.add(point3);
        tree.add(point4);
        tree.add(point5);
        tree.add(point6);
        tree.add(point7);

        Collection<XYZPoint> neighbours = tree.nearestNeighbourSearch(1, point8);
        Assert.assertTrue(neighbours.size() == 1);
        for (XYZPoint i : neighbours) {
            Assert.assertTrue(i.equals(point7));
        }

        neighbours = tree.nearestNeighbourSearch(3, point4);
        Assert.assertTrue(neighbours.size() == 3);
        int c = 0;
        for (XYZPoint i : neighbours) {
            if (c == 0) {
                Assert.assertTrue(i.equals(point4));
            } else if (c == 1) {
                Assert.assertTrue(i.equals(point5));
            } else if (c == 3) {
                Assert.assertTrue(i.equals(point6));
            }
            c++;
        }

    }

}
