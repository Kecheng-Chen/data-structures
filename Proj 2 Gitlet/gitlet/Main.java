package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import static gitlet.Utils.join;

/** @author Kecheng Chen, Ruinan Xu */
public class Main {
    static String objectDir() {
        return helper(1);
    }

    static String stageObjectDir() {
        return helper(2);
    }

    static String refDir() {
        return helper(3);
    }

    static String headrefLoc() {
        return helper(4);
    }

    static String localHeadDir() {
        return helper(5);
    }

    static String workingDir() {
        return helper(6);
    }

    static String masterBranchDir() {
        return helper(7);
    }

    static String rmFilesDir() {
        return helper(8);
    }

    static String stageIndexDir() {
        return helper(9);
    }

    static String remoteRefDir() {
        return helper(10);
    }

    static String headsLoc() {
        return helper(11);
    }

    static String objectsLoc() {
        return helper(12);
    }

    static String localhead() {
        return helper(13);
    }

    private static String helper(int a) {
        String ckc = "ckc";
        switch(a) {
            case 1:
                ckc = ".gitlet/objects";
                break;
            case 2:
                ckc = ".gitlet/stage";
                break;
            case 3:
                ckc = ".gitlet/refs";
                break;
            case 4:
                ckc = ".gitlet/refs/heads";
                break;
            case 5:
                ckc = ".gitlet/HEAD";
                break;
            case 6:
                ckc = System.getProperty("user.dir");
                break;
            case 7:
                ckc = ".gitlet/refs/heads/master";
                break;
            case 8:
                ckc = ".gitlet/delete";
                break;
            case 9:
                ckc = ".gitlet/stage/index";
                break;
            case 10:
                ckc = ".gitlet/refs/remotes";
                break;
            case 11:
                ckc = "refs/heads";
                break;
            case 12:
                ckc = "objects";
                break;
            case 13:
                ckc = Utils.readContentsAsString(Utils.join(".gitlet/HEAD"));
                break;
            default:
                ckc = "null";
        }
        if(true) {
            return ckc;
        } else {
            return "weird";
        }
    }

    public void void3() {
        int[] array = new int[]{1,2,3,4,5};
        int n = 0;
        for(int i = 0; i < array.length; i++) {
            n++;
        }
    }
    public static void main(String... args) {
        boolean b = args.length == 0;
        int d = 0;
        if (b) {
            d = 3;
        }
        boolean q=false;
        boolean c=false;
        if (d!=3) {
            c = !args[0].equals("init");
            q = !join(workingDir(), ".gitlet").exists();
        }
        if (q&&c){
            d = 4;
        }
        if (d>0) {
            switch(d) {
                case 3:
                    System.out.println("Please enter a command.");
                    return;
                case 4:
                    System.out.println("Not in an initialized Gitlet directory.");
                    return;
            }
        }
        CommandProcessor processor = new CommandProcessor(args, System.out);
        processor.execute();
    }

}
