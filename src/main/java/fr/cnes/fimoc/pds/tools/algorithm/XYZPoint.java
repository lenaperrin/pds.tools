package fr.cnes.fimoc.pds.tools.algorithm;

import java.util.Comparator;

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
public class XYZPoint implements Comparable<XYZPoint> {

    /**
     * X comparator.
     */
    public static final Comparator<XYZPoint> X_COMPARATOR = new Comparator<XYZPoint>() {

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(XYZPoint o1, XYZPoint o2) {
            int val = 0;
            if (o1.x < o2.x) {
                val = -1;
            }
            if (o1.x > o2.x) {
                val = 1;
            }
            return val;
        }
    };

    /**
     * Y comparator.
     */
    public static final Comparator<XYZPoint> Y_COMPARATOR = new Comparator<XYZPoint>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public int compare(XYZPoint o1, XYZPoint o2) {
            int val = 0;
            if (o1.y < o2.y) {
                val = -1;
            }
            if (o1.y > o2.y) {
                val = 1;
            }
            return val;
        }
    };

    /**
     * Z comparator.
     */
    public static final Comparator<XYZPoint> Z_COMPARATOR = new Comparator<XYZPoint>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public int compare(XYZPoint o1, XYZPoint o2) {
            int val = 0;
            if (o1.z < o2.z) {
                val = -1;
            }
            if (o1.z > o2.z) {
                val = 1;
            }
            return val;
        }
    };

    /**
     * X.
     */
    private double x = Double.NEGATIVE_INFINITY;
    /**
     * Y.
     */
    private double y = Double.NEGATIVE_INFINITY;
    /**
     * Z.
     */
    private double z = Double.NEGATIVE_INFINITY;

    /**
     * XY.
     * 
     * @param x
     *            : value x.
     * @param y
     *            : value y.
     */
    public XYZPoint(double x, double y) {
        this.x = x;
        this.y = y;
        z = 0;
    }

    /**
     * XY.
     * 
     * @param x
     *            : value x.
     * @param y
     *            : value y.
     * @param z
     *            : value z.
     */
    public XYZPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * Computes the Euclidean distance from this point to the other.
     * 
     * @param o1
     *            other point.
     * @return euclidean distance.
     */
    public double euclideanDistance(XYZPoint o1) {
        return euclideanDistance(o1, this);
    }

    /**
     * Computes the Euclidean distance from one point to the other.
     * 
     * @param o1
     *            first point.
     * @param o2
     *            second point.
     * @return euclidean distance.
     */
    private static final double euclideanDistance(XYZPoint o1, XYZPoint o2) {
        return Math.sqrt(Math.pow(o1.x - o2.x, 2) + Math.pow(o1.y - o2.y, 2) + Math.pow(o1.z - o2.z, 2));
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof XYZPoint)) {
            return false;
        }

        XYZPoint xyzPoint = (XYZPoint) obj;
        return compareTo(xyzPoint) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(XYZPoint o) {
        int xComp = X_COMPARATOR.compare(this, o);
        if (xComp != 0) {
            return xComp;
        }
        int yComp = Y_COMPARATOR.compare(this, o);
        if (yComp != 0) {
            return yComp;
        }
        int zComp = Z_COMPARATOR.compare(this, o);
        return zComp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("("); //$NON-NLS-1$
        builder.append(x).append(", "); //$NON-NLS-1$
        builder.append(y).append(", "); //$NON-NLS-1$
        builder.append(z);
        builder.append(")"); //$NON-NLS-1$
        return builder.toString();
    }
}