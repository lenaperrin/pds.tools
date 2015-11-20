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
package fr.cnes.fimoc.pds.tools.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A k-d tree (short for k-dimensional tree) is a space-partitioning data
 * structure for organizing points in a k-dimensional space. k-d trees are a
 * useful data structure for several applications, such as searches involving a
 * multidimensional search key (e.g. range searches and nearest neighbor
 * searches). k-d trees are a special case of binary space partitioning trees.
 * 
 * http://en.wikipedia.org/wiki/K-d_tree
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 * @param <T>
 *            : node.
 */
public class KdTree<T extends XYZPoint> {

    /**
     * K level.
     */
    private int k = 3;
    /**
     * Root.
     */
    private KdNode root = null;

    /**
     * Index axis.
     */
    public static final int X_AXIS = 0;
    /**
     * Index axis.
     */
    public static final int Y_AXIS = 1;
    /**
     * Index axis.
     */
    public static final int Z_AXIS = 2;

    /**
     * Default constructor.
     */
    public KdTree() {
    }

    /**
     * Constructor for creating a more balanced tree. It uses the
     * "median of points" algorithm.
     * 
     * @param list
     *            of XYZPoints.
     */
    public KdTree(List<XYZPoint> list) {
        root = createNode(list, k, 0);
    }

    /**
     * Create node from list of XYZPoints.
     * 
     * @param list
     *            of XYZPoints.
     * @param k
     *            of the tree.
     * @param depth
     *            depth of the node.
     * @return node created.
     */
    private KdNode createNode(List<XYZPoint> list, int k, int depth) {
        if (list == null || list.size() == 0) {
            return null;
        }

        int axis = depth % k;
        if (axis == X_AXIS) {
            Collections.sort(list, XYZPoint.X_COMPARATOR);
        } else if (axis == Y_AXIS) {
            Collections.sort(list, XYZPoint.Y_COMPARATOR);
        } else {
            Collections.sort(list, XYZPoint.Z_COMPARATOR);
        }

        KdNode node = null;
        if (list.size() > 0) {
            int medianIndex = list.size() / 2;
            node = new KdNode(k, depth, list.get(medianIndex));
            List<XYZPoint> less = new ArrayList<XYZPoint>(list.size() - 1);
            List<XYZPoint> more = new ArrayList<XYZPoint>(list.size() - 1);
            // Process list to see where each non-median point lies
            for (int i = 0; i < list.size(); i++) {
                if (i == medianIndex) {
                    continue;
                }
                XYZPoint p = list.get(i);
                if (KdNode.compareTo(depth, k, p, node.getId()) <= 0) {
                    less.add(p);
                } else {
                    more.add(p);
                }
            }
            if (medianIndex - 1 >= 0) {
                // Cannot assume points before the median are less since they
                // could be equal
                // List<XYZPoint> less = list.subList(0, mediaIndex);
                if (less.size() > 0) {
                    node.setLesser(createNode(less, k, depth + 1));
                    node.getLesser().setParent(node);
                }
            }
            if (medianIndex + 1 <= list.size() - 1) {
                // Cannot assume points after the median are less since they
                // could be equal
                // List<XYZPoint> more = list.subList(mediaIndex + 1,
                // list.size());
                if (more.size() > 0) {
                    node.setGreater(createNode(more, k, depth + 1));
                    node.getGreater().setParent(node);
                }
            }
        }
        return node;
    }

    /**
     * Add value to the tree. Tree can contain multiple equal values.
     * 
     * @param value
     *            T to add to the tree.
     * @return True if successfully added to tree.
     */
    public boolean add(T value) {
        if (value == null) {
            return false;
        }

        if (root == null) {
            root = new KdNode(value);
            return true;
        }

        KdNode node = root;
        while (true) {
            if (KdNode.compareTo(node.getDepth(), node.getK(), value, node.getId()) <= 0) {
                // Lesser
                if (node.getLesser() == null) {
                    KdNode newNode = new KdNode(k, node.getDepth() + 1, value);
                    newNode.setParent(node);
                    node.setLesser(newNode);
                    break;
                }
                node = node.getLesser();
            } else {
                // Greater
                if (node.getGreater() == null) {
                    KdNode newNode = new KdNode(k, node.getDepth() + 1, value);
                    newNode.setParent(node);
                    node.setGreater(newNode);
                    break;
                }
                node = node.getGreater();
            }
        }

        return true;
    }

    /**
     * Does the tree contain the value.
     * 
     * @param value
     *            T to locate in the tree.
     * @return True if tree contains value.
     */
    public boolean contains(T value) {
        if (value == null) {
            return false;
        }

        KdNode node = getNode(this, value);
        return node != null;
    }

    /**
     * Locate T in the tree.
     * 
     * @param <T>
     *            type.
     * 
     * @param tree
     *            to search.
     * @param value
     *            to search for.
     * @return KdNode or NULL if not found
     */
    private <T extends XYZPoint> KdNode getNode(KdTree<T> tree, T value) {
        if (tree == null || tree.root == null || value == null) {
            return null;
        }

        KdNode node = tree.root;
        while (true) {
            if (node.getId().equals(value)) {
                return node;
            } else if (KdNode.compareTo(node.getDepth(), node.getK(), value, node.getId()) <= 0) {
                // Lesser
                if (node.getLesser() == null) {
                    return null;
                }
                node = node.getLesser();
            } else {
                // Greater
                if (node.getGreater() == null) {
                    return null;
                }
                node = node.getGreater();
            }
        }
    }

    /**
     * Remove first occurrence of value in the tree.
     * 
     * @param value
     *            T to remove from the tree.
     * @return True if value was removed from the tree.
     */
    public boolean remove(T value) {
        if (value == null) {
            return false;
        }

        KdNode node = getNode(this, value);
        if (node == null) {
            return false;
        }

        KdNode parent = node.getParent();
        if (parent != null) {
            if (parent.getLesser() != null && node.equals(parent.getLesser())) {
                List<XYZPoint> nodes = getTree(node);
                if (nodes.size() > 0) {
                    parent.setLesser(createNode(nodes, node.getK(), node.getDepth()));
                    if (parent.getLesser() != null) {
                        parent.getLesser().setParent(parent);
                    }
                } else {
                    parent.setLesser(null);
                }
            } else {
                List<XYZPoint> nodes = getTree(node);
                if (nodes.size() > 0) {
                    parent.setGreater(createNode(nodes, node.getK(), node.getDepth()));
                    if (parent.getGreater() != null) {
                        parent.getGreater().setParent(parent);
                    }
                } else {
                    parent.setGreater(null);
                }
            }
        } else {
            // root
            List<XYZPoint> nodes = getTree(node);
            if (nodes.size() > 0) {
                root = createNode(nodes, node.getK(), node.getDepth());
            } else {
                root = null;
            }
        }

        return true;
    }

    /**
     * Get the (sub) tree rooted at root.
     * 
     * @param root
     *            of tree to get nodes for.
     * @return points in (sub) tree, not including root.
     */
    private final List<XYZPoint> getTree(KdNode root) {
        List<XYZPoint> list = new ArrayList<XYZPoint>();
        if (root == null) {
            return list;
        }

        if (root.getLesser() != null) {
            list.add(root.getLesser().getId());
            list.addAll(getTree(root.getLesser()));
        }
        if (root.getGreater() != null) {
            list.add(root.getGreater().getId());
            list.addAll(getTree(root.getGreater()));
        }

        return list;
    }

    /**
     * K Nearest Neighbor search
     * 
     * @param K
     *            Number of neighbors to retrieve. Can return more than K, if
     *            last nodes are equal distances.
     * @param value
     *            to find neighbors of.
     * @return collection of T neighbors.
     */
    @SuppressWarnings("unchecked")
    public Collection<T> nearestNeighbourSearch(int K, T value) {
        if (value == null) {
            return null;
        }

        // Map used for results
        TreeSet<KdNode> results = new TreeSet<KdNode>(new EuclideanComparator(value));

        // Find the closest leaf node
        KdNode prev = null;
        KdNode node = root;
        while (node != null) {
            if (KdNode.compareTo(node.getDepth(), node.getK(), value, node.getId()) <= 0) {
                // Lesser
                prev = node;
                node = node.getLesser();
            } else {
                // Greater
                prev = node;
                node = node.getGreater();
            }
        }
        KdNode leaf = prev;

        if (leaf != null) {
            // Used to not re-examine nodes
            Set<KdNode> examined = new HashSet<KdNode>();

            // Go up the tree, looking for better solutions
            node = leaf;
            while (node != null) {
                // Search node
                searchNode(value, node, K, results, examined);
                node = node.getParent();
            }
        }

        // Load up the collection of the results
        Collection<T> collection = new ArrayList<T>(K);
        for (KdNode kdNode : results) {
            collection.add((T) kdNode.getId());
        }
        return collection;
    }

    /**
     * Searche node.
     * 
     * @param <T>
     *            : ???.
     * @param value
     *            : ???.
     * @param node
     *            : ???.
     * @param K
     *            : ???.
     * @param results
     *            : ???.
     * @param examined
     *            : ???.
     */
    private <T extends XYZPoint> void searchNode(T value, KdNode node, int K, TreeSet<KdNode> results,
            Set<KdNode> examined) {
        examined.add(node);

        // Search node
        KdNode lastNode = null;
        Double lastDistance = Double.MAX_VALUE;
        if (results.size() > 0) {
            lastNode = results.last();
            lastDistance = lastNode.getId().euclideanDistance(value);
        }
        Double nodeDistance = node.getId().euclideanDistance(value);
        if (nodeDistance.compareTo(lastDistance) < 0) {
            if (results.size() == K && lastNode != null) {
                results.remove(lastNode);
            }
            results.add(node);
        } else if (nodeDistance.equals(lastDistance)) {
            results.add(node);
        } else if (results.size() < K) {
            results.add(node);
        }
        lastNode = results.last();
        lastDistance = lastNode.getId().euclideanDistance(value);

        int axis = node.getDepth() % node.getK();
        KdNode lesser = node.getLesser();
        KdNode greater = node.getGreater();

        // Search children branches, if axis aligned distance is less than
        // current distance
        if (lesser != null && !examined.contains(lesser)) {
            examined.add(lesser);

            double nodePoint = Double.MIN_VALUE;
            double valuePlusDistance = Double.MIN_VALUE;
            if (axis == X_AXIS) {
                nodePoint = node.getId().getX();
                valuePlusDistance = value.getX() - lastDistance;
            } else if (axis == Y_AXIS) {
                nodePoint = node.getId().getY();
                valuePlusDistance = value.getY() - lastDistance;
            } else {
                nodePoint = node.getId().getZ();
                valuePlusDistance = value.getZ() - lastDistance;
            }
            boolean lineIntersectsCube = valuePlusDistance <= nodePoint ? true : false;

            // Continue down lesser branch
            if (lineIntersectsCube) {
                searchNode(value, lesser, K, results, examined);
            }
        }
        if (greater != null && !examined.contains(greater)) {
            examined.add(greater);

            double nodePoint = Double.MIN_VALUE;
            double valuePlusDistance = Double.MIN_VALUE;
            if (axis == X_AXIS) {
                nodePoint = node.getId().getX();
                valuePlusDistance = value.getX() + lastDistance;
            } else if (axis == Y_AXIS) {
                nodePoint = node.getId().getY();
                valuePlusDistance = value.getY() + lastDistance;
            } else {
                nodePoint = node.getId().getZ();
                valuePlusDistance = value.getZ() + lastDistance;
            }
            boolean lineIntersectsCube = valuePlusDistance >= nodePoint ? true : false;

            // Continue down greater branch
            if (lineIntersectsCube) {
                searchNode(value, greater, K, results, examined);
            }
        }
    }

}