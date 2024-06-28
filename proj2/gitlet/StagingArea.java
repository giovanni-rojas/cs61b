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
public class StagingArea implements Serializable, Dumpable {

    /** In format of <fileName, blobID> */
    private Map<String, String> toAdd;
    private Set<String> toRemove;

    private static final File STAGING_AREA = Repository.STAGING_AREA;
    private static final File STAGED_FILES = Repository.STAGED_FILES;

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
        String blobID = Utils.sha1(Utils.serialize(contents));
        toAdd.put(fileName, blobID);
        writeObject(STAGING_AREA, this);


        /** Create Blob file in STAGED_FILES */
        File newFileAdded = Utils.join(STAGED_FILES, blobID);
        try {
            newFileAdded.createNewFile();
            writeObject(newFileAdded, Utils.serialize(contents));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
     public void dump() {
        System.out.printf("staging area: %s%n", toAdd);
     }

    public void clear() {

        toAdd.clear();
        toRemove.clear();

        List<String> blobIDs = plainFilenamesIn(STAGED_FILES);

        for (String blobID : blobIDs) {
            File f = Utils.join(STAGED_FILES, blobID);
            f.delete();
        }

        writeObject(STAGING_AREA, this);

    }

    public Map<String, String> getToAdd() {
        return toAdd;
    }

    public Set<String> getToRemove() {
        return toRemove;
    }

}
