package gitlet;

import java.io.File;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Gio R
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0) {
            System.out.print("Please enter a command.");
            System.exit(0);
        }

        Repository repo = new Repository();
        String firstArg = args[0];

        switch (firstArg) {
            case "init":
                repo.init();
                // TODO: handle the `init` command
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                validateNumArgs("add", args, 2);
                break;
            case "commit":
                // TODO: handle the `commit [message]` command
                break;
            case "rm":
                // TODO: handle the `rm [filename]` command
                break;
            case "log":
                // TODO: handle the `log` command
                break;
            case "global-log":
                // TODO: handle the `global-log` command
                break;
            case "find":
                // TODO: handle the `find [commit message]` command
                break;
            case "status":
                // TODO: handle the `status` command
                break;
            case "checkout":
                // TODO: handle the `checkout -- [file name]`, 'checkout [commit id] -- [file name]', or 'checkout [branch name]' commands
                break;
            case "branch":
                // TODO: handle the `branch [branch name]` command
                break;
            case "rm-branch":
                // TODO: handle the `rm-branch [branch name]` command
                break;
            case "reset":
                // TODO: handle the `reset [commit id]' command
                break;
            case "merge":
                // TODO: handle the `merge [branch name]` command
                break;
            default:
                System.out.print("No command with that name exists.");
                System.exit(0);
        }
    }

    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
