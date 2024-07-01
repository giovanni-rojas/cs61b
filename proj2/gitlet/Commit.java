package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static gitlet.Repository.*;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Gio R
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;

    /** SHA1 of parent Commit. */
    private String parentID;

    /** Commit timestamp */
    private Date timestamp;

    /** Mapping of files Commit points to: [FileName -> FileID] ----> [BlobID -> Blob] */
    private Map<String, String> trackedFiles;

    /** Sha1 of commit */
    private String ID;


    /* TODO: fill in the rest of this class. */
    public Commit(String message, String parentID) {
        this.message = message;
        this.parentID = parentID;
        this.timestamp = new Date();

        Commit parentCommit = Utils.readObject(HEAD, Commit.class);
        this.trackedFiles = parentCommit.getTrackedFiles();

        /** Read staging area file names */
        StagingArea stagingArea = Utils.readObject(STAGING_AREA, StagingArea.class);

        List<String> fileNames = plainFilenamesIn(STAGED_FILES);
        addStagedFiles(fileNames);

        this.ID = Utils.sha1(Utils.serialize(this));

        /** Save commit to memory? */
        File newCommitFile = Utils.join(COMMITS, this.ID);
        try {
            newCommitFile.createNewFile();
            writeObject(newCommitFile, Utils.serialize(this));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Clear staging area */
        stagingArea.clear();

    }

    public Commit() {
        this.message = "initial commit";
        this.parentID = "";
        this.timestamp = new Date(0);
        this.ID = Utils.sha1(Utils.serialize(this));
        this.trackedFiles = new TreeMap<>();

        File newCommitFile = Utils.join(COMMITS, this.ID);
        try {
            newCommitFile.createNewFile();
            writeObject(newCommitFile, Utils.serialize(this));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addStagedFiles(List<String> fileNames) {

        for (String fileName : fileNames) {
            /** Read contents of file via fileName in STAGED_FILES */
            File f = Utils.join(STAGED_FILES, fileName);
            byte[] contents = Utils.readContents(f);
            String blobID = Utils.sha1(Utils.serialize(contents));
            trackedFiles.put(fileName, blobID);

            /** Create Blob file in BLOBS */
            File newBlobAdded = Utils.join(BLOBS, blobID);
            try {
                newBlobAdded.createNewFile();
                writeObject(newBlobAdded, Utils.serialize(contents));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getMessage() {
        return message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public Map<String, String> getTrackedFiles() {
        return trackedFiles;
    }
    public String getID() {
        return ID;
    }
}
