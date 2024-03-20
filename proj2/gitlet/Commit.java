package gitlet;

// TODO: any imports you need here

import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timestamp;
    private String parent;

    private String sha1;

    /* TODO: fill in the rest of this class. */
    public Commit(String message, String parent) {
        this.message = message;
        this.parent = parent;
        this.timestamp = new Date(0);
    }

    public String getMessage() {
        return message;
    }
    public String getTimestamp() {
        return this.timestamp;
    }
    public String getParent() {
        return parent;
    }
}
