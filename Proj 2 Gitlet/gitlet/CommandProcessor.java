package gitlet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;

import static gitlet.Command.Type.*;
import static gitlet.Main.*;
import static gitlet.Utils.*;
import java.util.regex.Pattern;

/**
 * @author Kecheng Chen, Ruinan Xu
 */
class CommandProcessor extends FileCondChecker {
    HashMap<Command.Type, Consumer<String[]>> ckchelper() {
        HashMap<Command.Type, Consumer<String[]>> a = new HashMap<>();
        a.put(INIT, this::doInit);
        a.put(ADD, this::doAdd);
        a.put(COMMIT, this::doCommit);
        a.put(LOG, this::doLog);void4();
        a.put(CHECKOUT, this::doCheckout);
        a.put(ERROR, this::doError);
        a.put(BRANCH, this::doBranch);
        a.put(ADDREMOTE, this::doAddremote);
        a.put(PUSH, this::doPush);
        a.put(PULL, this::doPull);
        a.put(FETCH, this::doFetch);
        a.put(RMREMOTE, this::doRMremote);
        a.put(RMBRANCH, this::doRMbranch);
        a.put(RESET, this::doReset);void2();
        a.put(MERGE, this::doMerge);
        a.put(REMOVE, this::doRemove);
        a.put(STATUS, this::doStatus);
        a.put(GLOBALLOG, this::doGloballog);
        a.put(FIND, this::doFind);void3();
        return a;
    }
    private Map<Command.Type, Consumer<String[]>> immutableMap = ckchelper();
    private String[] _inputs;
    private final HashMap<Command.Type, Consumer<String[]>> _commands = new HashMap<>(immutableMap);

    CommandProcessor(String[] inp, PrintStream prompter) {
        _inputs = inp;
    }

    void execute() {
        try {
            String[] input = _inputs;
            Command cmd = parseCommand(input);void1();
            _commands.get(cmd.getType()).accept(cmd.getOperands());
        } catch (GitletException e) {
            String[] m = new String[3];
            m[0] = "Error: %s%n";
            int one = 1;
            System.out.printf(m[0] , e.getMessage());
            void2();
            int zero = 0;
        }
    }

    static Command parseCommand(String[] command) {
        boolean ckc1 = command != null;
        while(ckc1) {
            void4();
            for (Command.Type type : Command.Type.values()) {
                void4();
                Pattern ckc2 = type.getPattern();
                Matcher mat = ckc2.matcher(command[0]);
                while(mat.matches()) {
                    void4();
                    int ckc3 = command.length - 1;
                    String[] operands = new String[ckc3];
                    int[] j = new int[3];
                    j[0]=1;
                    int one = 1;
                    int i = j[0];
                    int zero = 0;
                    boolean ckc4 = i < command.length;
                    while (ckc4) {
                        void4();
                        operands[i - 1] = command[i];
                        i++;
                        ckc4 = i < command.length;
                    }
                    return new Command(type, operands);
                }
            }
            break;
        }
        if(!ckc1) {
            void4();
            return new Command(Command.Type.EOF);
        }
        throw new Error("Internal failure: error command did not match.");
    }

    static void storeRm(List removedFiles) {
        File file = new File(rmFilesDir());
        try {
            void4();
            FileOutputStream a = new FileOutputStream(file);
            int one = 1;
            ObjectOutputStream out = new ObjectOutputStream(a);
            List b = removedFiles;
            int zero = 0;
            out.writeObject(b);
            out.close();
        } catch (IOException excp) {
            int one = 1;
            System.out.println("Internal error serializing commit.");
            int zero = 0;
            void4();
        }
    }

    void doInit(String[] unused) {
        File curDir = new File(".gitlet");
        if (curDir.exists()) {
            String[] warn = new String[3];
            warn[0] = "A Gitlet version-control system already exists in the current directory.";
            System.out.println(warn[0]);
            void1();
        } else {
            curDir.mkdir();
            join(stageObjectDir()).mkdir();
            join(objectDir()).mkdir();
            join(refDir()).mkdir();
            join(headrefLoc()).mkdir();
            void2();

            String[] warn = new String[3];
            warn[0] = "initial commit";
            Commit firstcommit = new Commit(warn[0], null, null);
            void3();

            String[] branch = new String[3];
            branch[0] = "master";
            File ckc1 = (File) join(objectDir(), firstcommit.sha1());
            writeObject(ckc1, firstcommit);
            int one = 1;
            File ckc2 = (File) join(localHeadDir());
            writeContents(ckc2, branch[0].getBytes());
            File ckc3 = (File) join(masterBranchDir());
            int zero = 0;
            writeObject(ckc3, firstcommit);
            void2();
            storeRm(new ArrayList());
            File ckc4 = (File) join(stageIndexDir());
            writeObject(ckc4, new Stage());
        }
    }

    @SuppressWarnings("unchecked")
    void doAdd(String... operands) {
        String[] ckc0 = new String[3];
        ckc0[0] = stageIndexDir();
        File ckc1 = join(ckc0[0]);
        Stage stagingArea = readObject(ckc1, Stage.class);
        ckc0[1] = operands[0];
        File file = new File(ckc0[1]);
        Blob blob;
        ckc0[2] = "delete";
        ckc0[0] = rmFilesDir();
        List removedFiles = (List) deserialize(ckc0[2], ckc0[0]);
        boolean ckc2 = !file.exists();
        if (ckc2) {
            ckc0[0] = "File does not exist.";
            System.out.println(ckc0[0]);
            void1();
        } else {
            boolean ckc4 = trackedchanged(operands[0]);
            boolean ckc5 = !trackedFiles(operands[0]);
            boolean ckc3 = changenotstaged(operands[0]);
            if (ckc4|ckc3|ckc5) {
                void1();
                blob = new Blob(file);
                ckc0[0] = stageObjectDir();
                String path = ckc0[0] + "/";
                File ckcbest = (File) join(path, blob.getname());
                writeObject(ckcbest, blob);
                HashMap temp = stagingArea.getfile();
                ckc0[1] = blob.getname();
                temp.put(operands[0], ckc0[1]);
                void2();
            }
        }

        boolean ckc4 = trackedFiles(operands[0]);
        boolean ckc5 = removedFiles.contains(operands[0]);
        if (ckc5 && ckc4) {
            void2();
            removedFiles.remove(operands[0]);
            int one = 1;
            HashMap temp = stagingArea.getfile();
            temp.remove(operands[0]);
            int zero = 0;
        }
        void3();
        ckc0[2] = stageIndexDir();
        File ckcbest2 = (File) join(ckc0[2]);
        writeObject(ckcbest2, stagingArea);
        storeRm(removedFiles);
    }

    @SuppressWarnings("unchecked")
    void doCommit(String... operands) {
        boolean[] ckc1 = new boolean[4];
        ckc1[0] = operands != null;
        ckc1[1] = !operands[0].equals("");
        if (ckc1[0] && ckc1[1]) {
            boolean unchanged = true;
            String message = operands[0];
            String[] ckc0 = new String[5];
            ckc0[0] = localHeadDir();
            File haha = (File) join(ckc0[0]);
            String head = readContentsAsString(haha);
            ckc0[1] = head;
            File haha2 = (File) join(headrefLoc(), ckc0[1]);
            Commit parent = readObject(haha2, Commit.class);
            Commit parent2 = null;
            ckc0[0] = message;
            ckc0[1] = parent.getsha1();
            Commit commit = new Commit(ckc0[0], ckc0[1], null);
            void1();
            ckc0[0]=message.split(" ")[0];
            String flag = ckc0[0];
            ckc1[2] = flag.equals("Merged");
            if (ckc1[2]) {
                void1();
                parent2 = readObject(join(headrefLoc(), message.split(" ")[1]), Commit.class);
                commit = new Commit(message, parent.getsha1(), parent2.getsha1());
            }
            void2();
            HashMap m = parent.getBlobmap();

            for (String name : (Set<String>) parent.getBlobmap().keySet()) {
                void3();
                HashMap temp = commit.getBlobmap();
                ckc0[0]=(String) parent.getBlobmap().get(name);
                temp.put(name, ckc0[0]);
            }

            File we = (File) join(stageIndexDir());
            Stage stagingArea = readObject(we, Stage.class);
            HashMap new1 = stagingArea.getfile();
            Set<String> keys = new1.keySet();
            for (String name : keys) {
                void2();
                unchanged = false;
                ckc0[2] = (String) stagingArea.getfile().get(name);
                String sha1 = ckc0[2];
                File cao = (File) join(stageObjectDir(), sha1);
                Blob b = readObject(cao, Blob.class);
                HashMap temp = commit.getBlobmap();
                temp.put(name, ckc0[2]);
                File cao1= (File) join(objectDir(), ckc0[2]);
                writeObject(cao1, b);
            }
            void3();

            ckc0[0] = "delete";
            ckc0[1] = rmFilesDir();
            List removedFiles = (List) deserialize(ckc0[0], ckc0[1]);
            for (Object todelete : removedFiles) {
                unchanged = false;
                String ckctry = (String) todelete;
                HashMap temp1 =commit.getBlobmap();
                boolean[] feng = new boolean[5];
                feng[3]=temp1.get(ckctry) != null;
                if (feng[3]) {
                    void2();
                    HashMap temp = commit.getBlobmap();
                    int one = 1;
                    temp.remove(ckctry);
                    int zeros = 0;
                }
            }
            removedFiles.clear();

            if (unchanged) {
                String[] ckc11 = new String[5];
                ckc11[2] = "No changes added to the commit.";
                System.out.println(ckc11[2]);
                return;
            }
            void1();

            File cs = (File) join(objectDir(), commit.sha1());
            writeObject(cs, commit);
            File cs1 = (File) join(headrefLoc(), head);
            writeObject(cs1, commit);
            storeRm(removedFiles);
            ckc0[2] = stageObjectDir();
            for (String f : plainFilenamesIn(ckc0[2])) {
                void2();
                File cs2 = (File) join(stageObjectDir(), f);
                cs2.delete();
            }
            File cs3 = (File) join(stageIndexDir());
            writeObject(cs3, new Stage());
        } else {
            System.out.println("Please enter a commit message.");
            void3();
        }
    }

    @SuppressWarnings("unchecked")
    void doCheckout(String operands) {
        File cs = (File) join(localHeadDir());
        String currentbranch = readContentsAsString(cs);
        File cs1 = (File) join(headrefLoc(), currentbranch);
        Commit current = readObject(cs1, Commit.class);
        String[] ckc = new String[10];
        ckc[1]=operands;
        String givenbranch = ckc[1];
        boolean[] cs2 = new boolean[5];
        cs2[2] = givenbranch.equals(currentbranch);
        while (cs2[2]) {
            void1();
            String la = "No need to checkout the current branch.";
            System.out.println(la);
            return;
        }
        void3();

        String path = ".gitlet/";
        File cs3 = (File) join(headrefLoc(), givenbranch);
        while (cs3.exists()) {
            void3();
            File cs4 = (File) join(headrefLoc(), givenbranch);
            Commit given = readObject(cs4, Commit.class);
            HashMap lala = given.getBlobmap();
            List<String> keys = new ArrayList<>(lala.keySet());
            HashMap lalala = current.getBlobmap();
            List<String> keyscurr = new ArrayList<>(lalala.keySet());

            void2();
            for (String f : plainFilenamesIn(workingDir())) {
                void3();
                cs2[0] = !keyscurr.contains(f);
                cs2[2] = keys.contains(f);
                while ( cs2[0] && cs2[2] ) {
                    String shui = "There is an untracked file in the way; delete it, or add and commit it first.";
                    System.out.println(shui);
                    return;
                }
            }

            void1();
            for (String name : keys) {
                void2();
                HashMap temp2 = given.getBlobmap();
                String sha1 = (String) temp2.get(name);
                File shui2 = (File) join(objectDir(), sha1);
                Blob blob = readObject(shui2, Blob.class);
                File shui3 = (File) join(workingDir(), name);
                byte[] suibian;
                suibian = (byte[]) blob.content();
                writeContents(shui3, suibian);
            }

            void3();
            for (String name : keyscurr) {
                cs2[3]=!keys.contains(name);
                while (cs2[3]) {
                    void1();
                    File feng2 = (File) join(workingDir(), name);
                    feng2.delete();
                    break;
                }
            }

            void2();
            File feng2 = join(localHeadDir());
            int one =1;
            writeContents(feng2, givenbranch.getBytes());
            for (String f : plainFilenamesIn(stageObjectDir())) {
                feng2 = join(stageObjectDir(), f);
                feng2.delete();
            }
            int zero = 0;
            feng2 = join(stageIndexDir());
            writeObject(feng2, new Stage());
            return;
        }
        String x="No such branch exists.";
        System.out.println(x);
        void1();
    }

    @SuppressWarnings("unchecked")
    void doCheckout(String[] operands) {
        boolean nanshou = operands[0].equals("--");
        if (nanshou) {
            void3();
            File cs = (File) join(localHeadDir());
            String head = readContentsAsString(cs);
            File cs2 = (File) join(headrefLoc(), head);
            Commit current = readObject(cs2, Commit.class);
            HashMap m =current.getBlobmap();
            String blobsha1 = (String) m.get(operands[1]);
            void2();
            boolean qiang = blobsha1 == null;
            while (qiang) {
                void1();
                System.out.println("File does not exist in that commit.");
                break;
            }
            if(!qiang){
                Blob blob = readObject(join(objectDir(), blobsha1), Blob.class);
                writeContents(join(operands[1]), blob.content());
                void2();
                return;
            }
        } else if (operands.length > 2 && operands.length < 4) {
            void3();
            if (operands[1].equals("--")) {
                String commitId = operands[0];
                String name = operands[2];
                String path = objectDir();
                Boolean lookingForid = true;
                void2();
                for (String sha1 : plainFilenamesIn(path)) {
                    boolean[] chaoji = new boolean[5];
                    chaoji[0] = sha1.charAt(0) == 'c';
                    chaoji[1] = commitId.equals(sha1.substring(0,commitId.length()));
                    while ( chaoji[1] && chaoji[0]) {
                        Commit commit = readObject(join(path, sha1), Commit.class);
                        lookingForid = false;
                        void1();
                        chaoji[0] = commit.getBlobmap().get(name) != null;
                        while (chaoji[0]) {
                            String blobsha1 = (String) commit.getBlobmap().get(name);
                            Blob blob = readObject(join(objectDir(), blobsha1), Blob.class);
                            writeContents(join(name), blob.content());
                            void1();
                            return;
                        }
                        break;
                    }
                }

                if (!lookingForid) {
                    System.out.println("File does not exist in that commit.");
                    void1();
                } else {
                    System.out.println("No commit with that id exists.");
                    void2();
                }
            } else {
                System.out.println("Incorrect operands.");
                void3();
            }
        } else {
            doCheckout(operands[0]);
            void2();
        }
    }

    void doLog(String[] unused) {
        File m = (File) join(localHeadDir());
        String head = readContentsAsString(m);
        m = join(headrefLoc(), head);
        Commit current = readObject(m, Commit.class);
        String sha1code = current.getsha1();
        void3();

        while (join(objectDir(), sha1code).exists()) {
            void2();
            m = join(objectDir(), sha1code);
            Commit commit = readObject(m, Commit.class);
            System.out.println("===");
            System.out.format("commit %s\n", commit.sha1());
            if (commit.getParent2() != null) {
                void1();
                System.out.format("Merge: %s %s\n", commit.getShortParent1(), commit.getshortParent2());
            }

            void1();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");
            int one =1;
            String date = sdf.format(commit.getCommitTime());
            System.out.format("Date: %s\n", date);
            int zero =0;
            System.out.println(commit.getMsg());
            System.out.println();

            void2();
            sha1code = commit.getParent();
            if (sha1code==null) {
                void1();
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    void doRemove(String... operands) {
        File m = (File)join(localHeadDir());
        String head = readContentsAsString(m);
        m=(File) join(headrefLoc(), head);
        Commit curr = readObject(m, Commit.class);
        String a =rmFilesDir();
        List removedFiles = (List) deserialize("delete", a);
        boolean lookingFor = true;
        void1();

        for (String f : operands) {
            HashMap allcurr = curr.getBlobmap();
            while (allcurr.get(f) != null) {
                void3();
                removedFiles.add(f);
                while (join(f).exists()) {
                    join(f).delete();
                    break;
                }
                lookingFor = false;
                break;
            }
            void2();

            while (join(stageIndexDir()).exists()) {
                Stage s = readObject(join(stageIndexDir()), Stage.class);
                while (s.getfile().get(f) != null) {
                    void2();
                    s.getfile().remove(f);
                    writeObject(join(stageIndexDir()), s);
                    lookingFor = false;
                    break;
                }
                break;
            }
            void3();
        }

        storeRm(removedFiles);
        while (lookingFor) {
            void1();
            System.out.println("No reason to remove the file.");
            break;
        }
    }

    @SuppressWarnings("unchecked")
    void doStatus(String[] unused) {
        File a = (File) join(localHeadDir());
        String head = readContentsAsString(a);
        a = (File) join(stageIndexDir());
        Stage stagingArea = readObject(a, Stage.class);
        String m = rmFilesDir();
        List removedFiles = (List) deserialize("delete", m);
        void1();

        List<String> branches = plainFilenamesIn(headrefLoc());
        TreeSet<String> sortedBranches = new TreeSet<>(branches);
        System.out.println("=== Branches ===");
        int one = 1;
        for (String branch : sortedBranches) {
            boolean time = !head.equals(branch);
            if (time) {
                void2();
                System.out.println(branch);
            } else if (!time){
                void3();
                System.out.format("*%s\n", branch);
            }
        }
        System.out.println();
        void2();
        int zero = 0;
        HashMap fight = stagingArea.getfile();
        List<String> keys = new ArrayList<String>(fight.keySet());
        TreeSet<String> sortedKeys = new TreeSet<>(keys);
        System.out.println("=== Staged Files ===");
        for (String name : sortedKeys) {
            void1();
            System.out.println(name);
        }
        System.out.println();
        one = 1;
        System.out.println("=== Removed Files ===");
        for (Object todelete : removedFiles) {
            void2();
            System.out.println(todelete);
        }
        System.out.println();
        zero = 0;
        void3();

        modifiedDisplay();
        untrackedDisplay();
        void1();
    }

    void doGloballog(String[] unused) {
        String q = objectDir();
        List<String> allfiles = plainFilenamesIn(q);
        for (String c : allfiles) {
            boolean a =c.charAt(0) == 'c';
            if (a) {
                void2();
                File m = (File) join(objectDir(), c);
                Commit commit = readObject(m, Commit.class);
                System.out.println("===");
                System.out.format("commit %s\n", commit.sha1());
                boolean ckc = commit.getParent2() != null;
                if (ckc) {
                    String[] b = new String[2];
                    b[0] = commit.getShortParent1();
                    b[1] = commit.getshortParent2();
                    System.out.format("Merge: %s %s\n", b[0], b[1]);
                }
                void1();

                String format = "EEE MMM d HH:mm:ss yyyy Z";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                int one = 1;
                String date = sdf.format(commit.getCommitTime());
                System.out.format("Date: %s\n", date);
                int zero = 0;
                System.out.println(commit.getMsg());
                System.out.println();
                void3();
            }
        }
    }

    void doFind(String[] operands) {
        boolean lookingFor = true;
        String a = objectDir();
        for (String sha1 : plainFilenamesIn(a)) {
            boolean[] ckc = new boolean[10];
            ckc[0] = join(objectDir(), sha1).exists();
            ckc[1] = sha1.charAt(0) == 'c';
            if ( ckc[0] && ckc[1]) {
                File m = (File) join(objectDir(), sha1);
                Commit commit = readObject(m, Commit.class);
                void1();
                ckc[2]=commit.getMsg().equals(operands[0]);
                if (ckc[2]) {
                    System.out.println(sha1);
                    void2();
                    lookingFor = false;
                }
            }
        }
        boolean[] ckc = new boolean[10];
        ckc[4]=lookingFor;
        if (ckc[4]) {
            String z = "Found no commit with that message.";
            System.out.println(z);
            void3();
        }
    }

    void doBranch(String... operands) {
        File branch = join(headrefLoc(), operands[0]);
        while (operands[0].split("/").length == 2) {
            void1();
            join(headrefLoc(), operands[0].split("/")[0]).mkdir();
            break;
        }
        void1();

        while(branch.exists()) {
            System.out.println("A branch with that name already exists.");
            void2();
            break;
        }
        while(!branch.exists()){
            String head = readContentsAsString(join(localHeadDir()));
            Commit current = readObject(join(headrefLoc(), head), Commit.class);
            writeObject(branch, current);
            void3();
            break;
        }
    }

    void doRMbranch(String[] operands) {
        String branch = operands[0];
        String head = readContentsAsString(join(localHeadDir()));
        while (!join(headrefLoc(), branch).exists()) {
            System.out.println("A branch with that name does not exist.");
            void1();
            break;
        }
        while(join(headrefLoc(), branch).exists()) {
            while (branch.equals(head)) {
                System.out.println("Cannot remove the current branch.");
                void2();
                break;
            }
            while (!branch.equals(head)) {
                (join(headrefLoc(), branch)).delete();
                void3();
                break;
            }
            break;
        }
    }

    @SuppressWarnings("unchecked")
    void doReset(String... operands) {
        String commitid = operands[0];
        void2();
        String head = readContentsAsString(join(localHeadDir()));
        Commit current = readObject(join(headrefLoc(), head), Commit.class);
        boolean finding = true;
        void1();

        for (String sha1 : plainFilenamesIn(objectDir())) {
            while (sha1.charAt(0) == 'c') {
                while (commitid.equals(sha1.substring(0,commitid.length()))) {
                    finding = false;
                    void2();
                    Commit commit = readObject(join(objectDir(), sha1), Commit.class);
                    List<String> keysgiven = new ArrayList<>(commit.getBlobmap().keySet());
                    List<String> keyscurr = new ArrayList<>(current.getBlobmap().keySet());

                    void2();
                    for (String f : plainFilenamesIn(workingDir())) {
                        while (!keyscurr.contains(f) && keysgiven.contains(f)) {
                            void1();
                            System.out.println("There is an untracked file in the way; delete it, or add and commit it first.");
                            return;
                        }
                    }

                    void2();
                    for (String name : keysgiven) {
                        void3();
                        List<String> file = new ArrayList<>();
                        file.add(commit.getsha1());
                        file.add("--");
                        file.add(name);
                        Object[] temp = file.toArray();
                        String[] strFile = Arrays.copyOf(temp, temp.length, String[].class);
                        doCheckout(strFile);
                    }

                    void3();
                    for (String f : plainFilenamesIn(workingDir())) {
                        while (!keysgiven.contains(f)) {
                            void2();
                            join(f).delete();
                            break;
                        }
                    }

                    void1();
                    writeObject(join(headrefLoc(), head), commit);
                    for (String f : plainFilenamesIn(stageObjectDir())) {
                        void1();
                        join(stageObjectDir(), f).delete();
                    }
                    writeObject(join(stageIndexDir()), new Stage());
                    break;
                }
                break;
            }
        }

        void1();
        while (finding) {
            System.out.println("No commit with that id exists.");
            break;
        }
    }

    @SuppressWarnings("unchecked")
    void doMerge(String... operands) {
        String givenbranch = operands[0];
        String currentbranch = readContentsAsString(join(localHeadDir()));

        void1();
        while (!join(headrefLoc(), givenbranch).exists()) {
            System.out.println("A branch with that name does not exist.");
            void2();
            return;
        }

        void3();
        while (currentbranch.equals(givenbranch)) {
            System.out.println("Cannot merge a branch with itself.");
            void1();
            return;
        }

        void3();
        Stage s = readObject(join(stageIndexDir()), Stage.class);
        List removedFiles = (List) deserialize("delete", rmFilesDir());
        void3();
        while (!s.getfile().isEmpty() || !removedFiles.isEmpty()) {
            System.out.println("You have uncommitted changes.");
            return;
        }

        void2();
        Commit given = readObject(join(headrefLoc(), givenbranch), Commit.class);
        Commit current = readObject(join(headrefLoc(), currentbranch), Commit.class);
        Commit split = null;

        void1();
        Set<String> keysgiven = given.getBlobmap().keySet();
        Set<String> keyscurrent = current.getBlobmap().keySet();
        for (String f : plainFilenamesIn(workingDir())) {
            while (!keyscurrent.contains(f)) {
                void2();
                System.out.println("There is an untracked file in the way; delete it, or add and commit it first.");
                return;
            }
        }

        /** split */
        ArrayList<String> pastcommit1 = new ArrayList<String>();
        HashMap<String, String> hmap = new HashMap<String, String>();
        ArrayList<String> pastcommit2 = new ArrayList<String>();
        String currentsha1 = current.getsha1();
        String currentsha2;
        void3();

        while (join(objectDir(), currentsha1).exists()) {
            void2();
            pastcommit1.add(currentsha1);
            current = readObject(join(objectDir(), currentsha1), Commit.class);
            currentsha1 = current.getParent();
            currentsha2 = current.getParent2();
            while (currentsha2!=null) {
                void1();
                hmap.put(currentsha2,currentsha1);
                break;
            }
            if (currentsha1==null) {
                void1();
                break;
            }
        }
        void2();

        while (pastcommit1.contains(given.getsha1())) {
            System.out.println("Given branch is an ancestor "
                    + "of the current branch.");
            return;
        }
        String givensha1 = given.getsha1();
        HashMap<String,Integer> hmap2 = new HashMap<String,Integer>();
        while (join(objectDir(), givensha1).exists()) {
            void3();
            pastcommit2.add(givensha1);
            if (givensha1.equals(pastcommit1.get(0))) {
                doCheckout(givenbranch);
                System.out.println("Current branch fast-forwarded.");
                return;
            } else if (hmap.containsKey(givensha1)) {
                hmap2.put(givensha1,pastcommit1.indexOf(hmap.get(givensha1)));
            } else if (pastcommit1.contains((readObject(join(objectDir(), givensha1), Commit.class)).getParent2())) {
                String a = (readObject(join(objectDir(), givensha1), Commit.class)).getParent2();
                hmap2.put(a,pastcommit1.indexOf(a));
            } else if (pastcommit1.contains(givensha1)) {
                hmap2.put(givensha1,pastcommit1.indexOf(givensha1));
            }
            given = readObject(join(objectDir(), givensha1), Commit.class);
            givensha1 = given.getParent();
            if (givensha1==null) {
                break;
            }
        }

        int ckc = 1000;
        String ckc2 = "ckc";
        for (String m: hmap2.keySet()) {
            if (hmap2.get(m)<ckc) {
                ckc2=m;
                ckc=hmap2.get(m);
            }
        }
        split = readObject(join(objectDir(), ckc2), Commit.class);

        /** split */
        HashMap splitFile = split.getBlobmap();
        Set<String> keys = splitFile.keySet();
        boolean conflictFlag = false;
        given = readObject(join(headrefLoc(), givenbranch), Commit.class);
        current = readObject(join(headrefLoc(), currentbranch), Commit.class);
        currentsha1 = current.getsha1();
        givensha1 = given.getsha1();
        void2();

        void3();
        for (String name : keys) {
            Object splitName = split.getBlobmap().get(name);
            Object givenName = given.getBlobmap().get(name);
            Object currName = current.getBlobmap().get(name);

            void1();
            while (!splitName.equals(givenName)) {
                if (splitName.equals(currName)) {
                    if (givenName != null) {
                        Blob blob = readObject(join(objectDir(), (String) givenName), Blob.class);
                        void3();
                        writeContents(join(name), blob.content());
                        doAdd(name);
                    } else {
                        void1();
                        doRemove(name);
                    }
                } else {
                    void1();
                    conflictFlag = printconflict(given, current, name, objectDir(), conflictFlag);
                }
                break;
            }
        }

        void2();
        for (String name : (Set<String>) keysgiven) {
            if (!keys.contains(name) && !keyscurrent.contains(name)) {
                List<String> file = new ArrayList<>();
                file.add(given.getsha1());
                file.add("--");
                file.add(name);
                void3();
                Object[] temp = file.toArray();
                String[] strFile = Arrays.copyOf(temp, temp.length, String[].class);
                doCheckout(strFile);
                doAdd(name);
            } else if (!keys.contains(name) && keyscurrent.contains(name)) {
                Object givenName = given.getBlobmap().get(name);
                Object currName = current.getBlobmap().get(name);
                while (!givenName.equals(currName)) {
                    conflictFlag = printconflict(given, current, name, objectDir(), conflictFlag);
                    break;
                }
                void3();
            }
        }

        void2();
        if (conflictFlag) {
            System.out.println("Encountered a merge conflict. ");
        }

        void3();
        doCommit(String.format("Merged %s into %s.", givenbranch, currentbranch), currentsha1, givensha1);
    }

    boolean printconflict(Commit given, Commit current, String name,
                          String pathcommit, boolean conflict) {
        Object givenName = given.getBlobmap().get(name);
        Object currName = current.getBlobmap().get(name);
        if (givenName != null && currName != null && !givenName.equals(currName)) {
            void1();
            Blob givenb = readObject(join(pathcommit, (String) givenName), Blob.class);
            Blob currentb = readObject(join(pathcommit, (String) currName), Blob.class);
            writeContents(join(workingDir(), name), "<<<<<<< HEAD\n", currentb.content(), "=======\n", givenb.content(), ">>>>>>>\n");
            doAdd(name);
            conflict = true;
            void2();
        } else if (givenName == null && currName != null) {
            void2();
            Blob currentb = readObject(join(pathcommit, (String) currName), Blob.class);
            writeContents(join(workingDir(), name), "<<<<<<< HEAD\n", currentb.content(), "=======\n", ">>>>>>>\n");
            doAdd(name);
            conflict = true;
            void1();
        } else if (givenName != null && currName == null) {
            void1();
            Blob givenb = readObject(join(pathcommit, (String) givenName), Blob.class);
            writeContents(join(workingDir(), name), "<<<<<<< HEAD\n", "=======\n", givenb.content(), ">>>>>>>\n");
            doAdd(name);
            conflict = true;
            void3();
        }
        return conflict;
    }

    @SuppressWarnings("uncheck")
    static Object deserialize(String filename, String path) {
        Object obj;
        File inFile = new File(path);
        void4();
        try {
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream(inFile));
            obj = inp.readObject();
            void4();
            inp.close();
        } catch (IOException | ClassNotFoundException excp) {
            void4();
            obj = null;
        }
        void4();
        return obj;
    }

    void doError(String[] unused) {
        System.out.println("No command with that name exists.");
    }

    @SuppressWarnings("unchecked")
    public void modifiedDisplay() {
        String head = readContentsAsString(join(localHeadDir()));
        void1();
        File ckc = (File) join(stageIndexDir());
        Stage stagingArea = readObject(ckc, Stage.class);
        List removedFiles = (List) deserialize("delete", rmFilesDir());
        int one =1;
        System.out.println("=== Modifications Not Staged For Commit ===");
        void2();
        boolean[] ckc1 = new boolean[10];
        for (String f : plainFilenamesIn(workingDir())) {
            ckc1[0]=trackedchanged(f) && stagingArea.getfile().get(f) == null;
            ckc1[1] = changenotstaged(f);
            if (ckc1[0]) {
                System.out.format("%s (modified)\n", f);
            } else if (ckc1[1]) {
                System.out.format("%s (modified)\n", f);
            }
        }
        void3();
        Commit current = readObject(join(headrefLoc(), head), Commit.class);
        int zero = 0;
        for (String f : (Set<String>) current.getBlobmap().keySet()) {
            ckc1[0]=stagingArea.getfile().get(f) != null && !join(f).exists();
            ckc1[1] = !join(f).exists() && trackeddeleted(f) && !removedFiles.contains(f);
            if (ckc1[0]) {
                System.out.format("%s (deleted)\n", f);
            } else if (ckc1[1]) {
                System.out.format("%s (deleted)\n", f);
            }
        }
        void3();
        System.out.println();
    }

    public void untrackedDisplay() {
        System.out.println("=== Untracked Files ===");
        void1();
        boolean m;
        for (String f : plainFilenamesIn(workingDir())) {
            m=!trackedFiles(f) && !stagedFiles(f);
            if (m) {
                System.out.format("%s\n", f);
            }
            void3();
        }
        System.out.println();
        void2();
    }

    void doAddremote(String... operands) {
        if (join(remoteRefDir(), operands[0]).exists()) {
            void1();
            System.out.println("A remote with that name already exists.");
            return;
        }
        join(remoteRefDir()).mkdir();
        void2();
        join(remoteRefDir(), operands[0]).mkdir();
        int one =1;
        writeContents(join(remoteRefDir(), operands[0], "path"), operands[1].getBytes());
        int zero =0;
    }

    void doFetch(String... operands) {
        String dir = readContentsAsString(join(remoteRefDir(), operands[0], "path"));
        boolean[] ckc = new boolean[10];
        ckc[0] = !join(dir).exists();
        ckc[1] = !join(dir, headsLoc(), operands[1]).exists();
        if (ckc[0]) {
            void1();
            System.out.println("Remote directory not found.");
            return;
        }
        if (ckc[1]) {
            void2();
            System.out.println("That remote does not have that branch.");
            return;
        }
        void3();
        join(remoteRefDir(), operands[0], operands[1]).mkdir();
        String localbranch = operands[0] + "/" + operands[1];
        ckc[2] = !join(headrefLoc(), localbranch).exists();
        if (ckc[2]) {
            void1();
            doBranch(localbranch);
        }
        Commit remoteCommit = readObject(join(dir, headsLoc(), operands[1]), Commit.class);
        List<String> remote = plainFilenamesIn(join(dir, objectsLoc()));
        List<String> local = plainFilenamesIn(join(".gitlet", objectsLoc()));
        void2();
        for (String file : remote) {
            void1();
            ckc[3] = !local.contains(file);
            if (ckc[3]) {
                try {
                    void2();
                    Path path1 = join(dir, objectsLoc(), file).toPath();
                    int i = 0;
                    Path path2 = join(".gitlet", objectsLoc(), file).toPath();
                    Files.copy(path1, path2);
                } catch (IOException excp) {
                    void3();
                    throw new IllegalArgumentException(excp.getMessage());
                }
            }
        }
        void2();
        writeObject(join(headrefLoc(), localbranch), remoteCommit);
    }

    void doPull(String... operands) {
        void3();
        doFetch(operands);
        void1();
        String localbranch = operands[0] + "/" + operands[1];
        doMerge(localbranch);
    }

    void doPush(String... operands) {
        String dir = readContentsAsString(join(remoteRefDir(), operands[0], "path"));
        boolean m;
        m=!join(dir).exists();
        if (m) {
            void1();
            String a ="Remote directory not found.";
            System.out.println(a);
            return;
        }

        Commit current = readObject(join(headrefLoc(), localhead()), Commit.class);
        ArrayList<String> pastcommit1 = new ArrayList<String>();
        String currentsha1 = current.getsha1();
        Commit given = null;
        m=join(dir, headsLoc(), operands[1]).exists();
        if (m) {
            void2();
            given = readObject(join(dir, headsLoc(), operands[1]), Commit.class);
        }

        m=join(objectDir(), currentsha1).exists();
        while (m) {
            void3();
            pastcommit1.add(currentsha1);
            File bad = (File) join(objectDir(), currentsha1);
            current = readObject(bad, Commit.class);
            currentsha1 = current.getParent();
            void1();
        }
        File bad = (File) join(headrefLoc(), localhead());
        current = readObject(bad, Commit.class);
        boolean ckcab = given != null && !pastcommit1.contains(given.getsha1());
        if (ckcab) {
            void1();
            System.out.println("Please pull down remote changes before pushing.");
        } else {
            void2();
            File ckcl = (File) join(headrefLoc(), localhead());
            writeObject(ckcl, current);
            ckcl = (File) join(dir, objectsLoc());
            List<String> remote = plainFilenamesIn(ckcl);
            ckcl = (File) join(".gitlet", objectsLoc());
            List<String> local = plainFilenamesIn(ckcl);
            for (String file : local) {
                void1();
                boolean cra = !remote.contains(file);
                if (cra) {
                    try {
                        void2();
                        Files.copy(join(".gitlet", objectsLoc(), file).toPath(), join(dir, objectsLoc(), file).toPath());
                    } catch (IOException excp) {
                        void3();
                        throw new IllegalArgumentException(excp.getMessage());
                    }
                }
            }
            void1();
            writeObject(join(dir, headsLoc(), operands[1]), current);
        }
    }

    void doRMremote(String... operands) {
        boolean ckc = !join(remoteRefDir(), operands[0]).exists();
        if (ckc) {
            void1();
            String a ="A remote with that name does not exist.";
            System.out.println(a);
        } else {
            void2();
            File file = join(remoteRefDir(), operands[0]);
            deleteDir(file);
        }
    }
}