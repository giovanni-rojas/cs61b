package gitlet;

import java.io.File;
import java.io.IOException;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Gio R
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));

    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File BLOBS = join(GITLET_DIR, "BLOBS");
    public static final File COMMITS = join(GITLET_DIR, "COMMITS");
    public static final File STAGING_AREA = join(GITLET_DIR, "STAGING_AREA");
    public static final File HEAD = join(GITLET_DIR, "HEAD");

    private static String firstCommitID;

    /** Current Commit that Repository points to */

    /* TODO: fill in the rest of this class. */

    public static void init() {

        /** Exit if .gitlet directory already exists */
        if (GITLET_DIR.exists()) {
            System.out.print("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        /** Set up directories */
        GITLET_DIR.mkdir();
        BLOBS.mkdir();
        COMMITS.mkdir();

        StagingArea stagingArea = new StagingArea();

        /** Create HEAD directory */
        try {
            HEAD.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Initial Commit */
        Commit initCommit = new Commit();
        File commitFile = Utils.join(COMMITS, initCommit.getID());
        Utils.writeObject(commitFile, initCommit);
        firstCommitID = initCommit.getID();
    }

    public static void add(String fileName) {

        /** Check .gitlet exists */
        Utils.checkGitlet();

        File file = Utils.join(CWD, fileName);

        /** Check if we're adding a nonexistant file */
        if (!file.exists()) {
            System.out.print("File does not exist.");
            System.exit(0);
        }

        /** Get Staging Area */
        StagingArea stagingArea = Utils.readObject(STAGING_AREA, StagingArea.class);
        stagingArea.add(fileName);

        /** TODO might be done?*/

    }

    public static void status() {
        /** Check .gitlet exists */
        Utils.checkGitlet();

        /** TODO */

    }

    public static void commit(String message) {
        /** Check .gitlet exists */
        Utils.checkGitlet();

        /** TODO */
        /** Get current commits? From parent? */

        /** For each file in staging area (make them blobs?), either add it to commit tree,
         *  or update existing file in commit tree */

        /** Move head pointer? */

        /** Clear staging area? */
    }

    public static String getFirstCommitID() {
        return firstCommitID;
    }
}
