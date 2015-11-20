package fr.cnes.fimoc.pds.tools.algorithm;

import java.util.Comparator;

/**
 *
 * Euclidean Comparator. Calcul dist between 2 points.
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class EuclideanComparator implements Comparator<KdNode> {

    /**
     * Point.
     */
    private XYZPoint point = null;

    /**
     * @param point
     *            : point.
     */
    public EuclideanComparator(XYZPoint point) {
        this.point = point;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(KdNode o1, KdNode o2) {
        Double d1 = point.euclideanDistance(o1.getId());
        Double d2 = point.euclideanDistance(o2.getId());
        if (d1.compareTo(d2) < 0) {
            return -1;
        } else if (d2.compareTo(d1) < 0) {
            return 1;
        }
        return o1.getId().compareTo(o2.getId());
    }
};