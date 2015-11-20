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
package fr.cnes.fimoc.pds.tools.model;

/**
 *
 * <P>
 * Represente Azimuth / Elevation object.
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class Coordinate {

    /**
     * Azimuth.
     */
    private double azimuth;

    /**
     * Elevation.
     */
    private double elevation;

    /**
     * Constructor.
     */
    public Coordinate() {
    }

    /**
     * @return the azimuth
     */
    public double getAzimuth() {
        return azimuth;
    }

    /**
     * @return the elevation
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * 
     * @param azimuth
     *            the azimuth to set in degree.
     */
    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }
    /**
     * @param elevation
     *            the elevation to set in degree
     */
    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    /**
     * @return string.
     */
    public String toStringDegree() {
        return "Deg(" + azimuth + "," + elevation + ") "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * @return string.
     */
    public String toStringRadian() {
        return "Rad(" + Math.PI / 180 * azimuth + "," + Math.PI / 180 * elevation + ") "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toStringDegree() + toStringRadian();

    }

    /**
     * @return coordinate to array.
     */
    public double[] toArray() {
        return new double[]{azimuth, elevation};
    }

    /**
     * @return coordinate to array in radian.
     */
    public double[] toArrayRadian() {
        return new double[]{Math.PI / 180 * azimuth, Math.PI / 180 * elevation};
    }
}
