package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.*;

/** Area where files are marked to be added/removed to/from CommitTree
 *
 *  @author Gio R
 */
public class StagingArea implements Serializable {

    /** In format of <fileName, blobID> */
    private Map<String, String> toAdd;
    private Set<String> toRemove;

    private static final File STAGING_AREA = Repository.STAGING_AREA;

    public StagingArea() {
        toAdd = new TreeMap<String, String>();
        toRemove = new TreeSet<String>();

        try {
            STAGING_AREA.createNewFile();
            writeObject(STAGING_AREA, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String fileName) {

        File fileToAdd = Utils.join(Repository.CWD, fileName);
        byte[] contents = Utils.readContents(fileToAdd);
        String blob_sha = Utils.sha1(Utils.serialize(contents));
        toAdd.put(fileName, blob_sha);
        writeObject(STAGING_AREA, this);

    }

}
