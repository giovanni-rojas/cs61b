package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static gitlet.Utils.*;

/** Area where files are marked to be added/removed to/from CommitTree
 *
 *  @author Gio R
 */
public class StagingArea implements Serializable {

    /** In format of <fileName, blobID> */
    private Map<String, String> toAdd;
    private Set<String> toRemove;

    public StagingArea() {
        toAdd = new TreeMap<String, String>();
        toRemove = new TreeSet<String>();
    }

    public void save() {
        File STAGING_AREA = Repository.STAGING_AREA;
        try {
            STAGING_AREA.createNewFile();
            writeObject(STAGING_AREA, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
