package fr.cnes.fimoc.pds.tools.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.cnes.fimoc.pds.tools.execption.FimocToolsException;
import fr.cnes.fimoc.pds.tools.model.Coordinate;

/**
 *
 * <P>
 * Singleton which provides access to data from LBL file.
 * </P>
 *
 * @author Capgemini
 * @version $Rev$
 *
 */
public class LabelReader {

    /**
     * Start tag.
     */
    private static final String GROUP = "GROUP"; //$NON-NLS-1$
    /**
     * End tag.
     */
    private static final String END_GROUP = "END_GROUP"; //$NON-NLS-1$

    /**
     * RSM Group.
     */
    public static final String GROUP_IMAGE = "DERIVED_IMAGE_PARMS"; //$NON-NLS-1$

    /**
     * AZIMUTH PARAM
     */
    public static final String AZIMUTH = "FIXED_INSTRUMENT_AZIMUTH"; //$NON-NLS-1$

    /**
     * ELEVATION PARAM
     */
    public static final String ELEVATION = "FIXED_INSTRUMENT_ELEVATION"; //$NON-NLS-1$

    /**
     * HGA Group.
     */
    public static final String GROUP_HGA = "HGA_ARTICULATION_STATE_PARMS"; //$NON-NLS-1$

    /**
     * RSM azimuth / elevation
     */
    public static final String RSM_ARTICULATION = "ARTICULATION_DEVICE_ANGLE"; //$NON-NLS-1$

    /**
     * All lines;
     */
    private List<String> lines;

    /**
     * Each group.
     */
    Map<String, List<String>> groups;
    /**
     * Constructor.
     * 
     * @param labelFile
     *            : file's path.
     * 
     * @throws FimocToolsException
     *             : throw an exception if there are trouble with file.
     */
    public LabelReader(String labelFile) throws FimocToolsException {
        Path path = Paths.get(labelFile);
        try {
            lines = Files.readAllLines(path);
            findGroups();
        } catch (IOException e) {
            throw new FimocToolsException("Impossible to read file {0}", e); //$NON-NLS-1$
        }
    }

    /**
     * @return the azimuth and elevation from label file.
     */
    public Coordinate getAzimuthElevation() {

        Coordinate coord = new Coordinate();

        for (String i : getGroups(GROUP_IMAGE)) {
            String[] splits = i.split("="); //$NON-NLS-1$
            if (splits.length > 1) {
                if (splits[0].trim().equals(AZIMUTH)) {
                    List<Double> doubles = extraxtDoubleFromString(i);
                    coord.setAzimuth(doubles.get(0));
                } else if (splits[0].trim().equals(ELEVATION)) {
                    List<Double> doubles = extraxtDoubleFromString(i);
                    coord.setElevation(doubles.get(0));
                }
            }

        }

        return coord;
    }

    /**
     * @param line
     *            : line to read.
     * @return : all double in this string.
     */
    public List<Double> extraxtDoubleFromString(String line) {
        List<Double> doubles = new ArrayList<>();

        Pattern pattern = Pattern.compile("[-]?[0-9]+.[0-9]+"); //$NON-NLS-1$
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            doubles.add(Double.valueOf(matcher.group(0)));
        }

        return doubles;

    }
    /**
     * 
     * @param key
     *            : group's name.
     * @return : structure group.
     */
    public List<String> getGroups(String key) {
        return groups.get(key);
    }

    /**
     * Find groups.
     */
    private void findGroups() {
        boolean isInGroup = false;
        String groupName = ""; //$NON-NLS-1$
        List<String> group = new ArrayList<>();
        groups = new HashMap<>();
        for (String i : lines) {
            // detect group tag
            if (i.startsWith(GROUP)) {
                isInGroup = true;
                group = new ArrayList<>();
                try {
                    // get the group name.
                    groupName = i.split("=")[1].trim(); //$NON-NLS-1$
                } catch (IndexOutOfBoundsException e) {
                    groupName = "noname"; //$NON-NLS-1$
                }
            }

            if (isInGroup) {
                group.add(i);
            }

            // detect end group tag
            if (i.startsWith(END_GROUP)) {
                isInGroup = false;
                // if group name already exists we add in the same group.
                if (groups.containsKey(groupName)) {
                    groups.get(groupName).addAll(group);
                } else {
                    groups.put(groupName, group);
                }
            }
        }
    }
}
