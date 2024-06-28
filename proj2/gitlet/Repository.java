package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public static final File STAGED_FILES = join(GITLET_DIR, "STAGED_FILES");
    public static final File HEAD = join(GITLET_DIR, "HEAD");

    public static Commit headCommit;

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
        STAGED_FILES.mkdir();

        StagingArea stagingArea = new StagingArea();

//        /** Create HEAD File */
//        try {
//            HEAD.createNewFile();
//            Utils.writeObject(HEAD, null);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        /** Initial Commit */
        Commit initCommit = new Commit();
        saveToFile(COMMITS, initCommit);

        /** SOMETHING WRONG HERE */
        headCommit = initCommit;
    }

    private static void saveToFile(File file, Commit commit) {
        String commitID = commit.getID();
        File commitFile = Utils.join(file, commitID);
        Utils.writeObject(commitFile, commit);
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

        /** Read staging area */
        StagingArea stagingArea = Utils.readObject(STAGING_AREA, StagingArea.class);

        /** Check for empty staging area */
        if (stagingArea.getToAdd() == null && stagingArea.getToRemove() == null) {
            System.out.print("No changes added to the commit.");
            System.exit(0);
        }

        //Commit parentCommit = Utils.readObject(HEAD, Commit.class);
        // headCommit not properly set in initial commit
        Commit parentCommit = headCommit;
        Commit commit = new Commit(message, parentCommit);

        //saveToFile(HEAD, commit);
        headCommit = commit;
        stagingArea.clear();
    }

    public static String getFirstCommitID() {
        return firstCommitID;
    }
}
