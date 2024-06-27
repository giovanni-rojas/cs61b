package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.sha1;

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

    /** Mapping of files Commit points to */
    private Map<String, String> fileMap;

    /** Sha1 of commit */
    private String ID;


    /* TODO: fill in the rest of this class. */
    public Commit(String message, String parentID) {
        this.message = message;
        this.parentID = parentID;
        this.timestamp = new Date();

        /** Link to parent? */

        /** Save commit to memory? */
        
    }

    public Commit() {
        message = "initial commit";
        parentID = null;
        timestamp = new Date(0);
        fileMap = new HashMap<>();
        ID = Utils.sha1(Utils.serialize(this));
        /** set head pointer? */
    }

    public String getMessage() {
        return message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getParent() {
        return parentID;
    }
    public String getID() {
        return ID;
    }
}
