package fr.cnes.fimoc.pds.tools.algorithm;

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
public class KdNode implements Comparable<KdNode> {

    /**
     * ???.
     */
    private int k = 3;
    /**
     * ???.
     */
    private int depth = 0;
    /**
     * ???.
     */
    private XYZPoint id = null;
    /**
     * ???.
     */
    private KdNode parent = null;
    /**
     * ???.
     */
    private KdNode lesser = null;
    /**
     * ???.
     */
    private KdNode greater = null;

    /**
     * @param id
     *            : point.
     */
    public KdNode(XYZPoint id) {
        this.id = id;
    }

    /**
     * @param k
     *            :???.
     * @param depth
     *            :???.
     * @param id
     *            :???.
     */
    public KdNode(int k, int depth, XYZPoint id) {
        this(id);
        this.k = k;
        this.depth = depth;
    }

    /**
     * @param depth
     *            :???.
     * @param k
     *            :???.
     * @param o1
     *            :???.
     * @param o2
     *            :???.
     * @return :???.
     */
    public static int compareTo(int depth, int k, XYZPoint o1, XYZPoint o2) {
        int axis = depth % k;
        if (axis == KdTree.X_AXIS) {
            return XYZPoint.X_COMPARATOR.compare(o1, o2);
        }
        if (axis == KdTree.Y_AXIS) {
            return XYZPoint.Y_COMPARATOR.compare(o1, o2);
        }
        return XYZPoint.Z_COMPARATOR.compare(o1, o2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof KdNode)) {
            return false;
        }

        KdNode kdNode = (KdNode) obj;
        if (this.compareTo(kdNode) == 0) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(KdNode o) {
        return compareTo(depth, k, id, o.id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("k=").append(k); //$NON-NLS-1$
        builder.append(" depth=").append(depth); //$NON-NLS-1$
        builder.append(" id=").append(id.toString()); //$NON-NLS-1$
        return builder.toString();
    }

    /**
     * @return the id
     */
    public XYZPoint getId() {
        return id;
    }

    /**
     * @return the lesser
     */
    public KdNode getLesser() {
        return lesser;
    }

    /**
     * @return the greater
     */
    public KdNode getGreater() {
        return greater;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @return the parent
     */
    public KdNode getParent() {
        return parent;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @param depth
     *            the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    /**
     * @param greater
     *            the greater to set
     */
    public void setGreater(KdNode greater) {
        this.greater = greater;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(XYZPoint id) {
        this.id = id;
    }
    /**
     * @param k
     *            the k to set
     */
    public void setK(int k) {
        this.k = k;
    }
    /**
     * @param lesser
     *            the lesser to set
     */
    public void setLesser(KdNode lesser) {
        this.lesser = lesser;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(KdNode parent) {
        this.parent = parent;
    }

}