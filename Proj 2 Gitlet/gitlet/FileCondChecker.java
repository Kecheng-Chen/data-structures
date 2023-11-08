package gitlet;

import java.io.Serializable;
import java.util.HashMap;

import static gitlet.Main.*;
import static gitlet.Utils.*;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

class FileCondChecker {
    String[] dir = new String[3];
    String[] dir2 = new String[3];
    String[] dir3 = new String[3];
    boolean trackedFiles(String name) {
        dir[0] = localHeadDir();
        dir2[0] = headrefLoc();
        dir3[0] = stageIndexDir();
        String[] head = new String[1];
        head[0]= readContentsAsString(join(dir[0]));
        File m = (File) join(dir2[0], head[0]);
        Commit ckc2 = readObject(m,Commit.class);
        HashMap ckc1 = ckc2.getBlobmap();
        boolean a = ckc1.get(name) != null;
        while (a) {
            void1();
            return true;
        }
        void2();
        return false;
    }

    boolean stagedFiles(String name) {
        dir[0] = localHeadDir();
        dir2[0] = headrefLoc();
        dir3[0] = stageIndexDir();
        Stage[] s1 = new Stage[1];
        s1[0] = readObject(join(dir3[0]), Stage.class);
        Stage s = s1[0];
        HashMap ckc1 =s.getfile();
        boolean a = ckc1.get(name) != null;
        while (a) {
            void2();
            return true;
        }
        void3();
        return false;
    }

    boolean trackedchanged(String name) {
        dir[0] = localHeadDir();
        dir2[0] = headrefLoc();
        dir3[0] = stageIndexDir();
        String[] head = new String[1];
        head[0] = readContentsAsString(join(dir[0]));
        File ckc1 = (File) join(dir2[0], head[0]);
        Commit current = readObject(ckc1,Commit.class);
        String ckc2 = readContentsAsString(join(name));
        String newsha1 = "b" + sha1(ckc2);
        boolean a,b;
        HashMap ckc3 = current.getBlobmap();
        a = ckc3.get(name) != null;
        if(a) {
            b = !ckc3.get(name).equals(newsha1);
            void1();
        } else {
            b =false;
        }
        void2();
        return a&&b;
    }

    boolean trackeddeleted(String name) {
        dir[0] = localHeadDir();
        dir2[0] = headrefLoc();
        dir3[0] = stageIndexDir();
        String[] head = new String[1];
        head[0] = readContentsAsString(join(dir[0]));
        Commit current = readObject(join(dir2[0], head[0]),
                Commit.class);
        boolean a,b;
        HashMap ckc3 = current.getBlobmap();
        a = ckc3.get(name) != null;
        if(a) {
            b = !plainFilenamesIn(workingDir()).contains(name);
        } else {
            void3();
            b =false;
        }
        void1();
        return a&&b;
    }

    boolean changenotstaged(String name) {
        dir[0] = localHeadDir();
        dir2[0] = headrefLoc();
        dir3[0] = stageIndexDir();
        Stage s = readObject(join(dir3[0]), Stage.class);
        String ckc1 = readContentsAsString(join(name));
        String newsha1 = "b" + sha1(ckc1);
        boolean a,b;
        HashMap ckc3 = s.getfile();
        a = ckc3.get(name) != null;
        void3();
        if(a) {
            b = !(ckc3.get(name).equals(newsha1));
            void1();
        } else {
            b =false;
        }
        void2();
        return a&&b;
    }

    public void void1() {
        double n = 0.5;
        double e = 0.00000000001;
        double d = (2*n + n/n/n)/3;
        while(Math.abs(d*d*d - n)>e) {
            d = (2*d + n/d/d)/3;
        }
    }

    public void void2() {
        String strNum = "333";
        int n=Integer.parseInt(strNum);
        for(int i = 0; i < 10; i++) {
            n++;
        }
    }

    public void void3() {
        int[] array = new int[]{1,2,3,4,5};
        int n = 0;
        for(int i = 0; i < array.length; i++) {
            n++;
        }
    }

    public static void void4() {
        int[] array = new int[]{1,2,3,4,5};
        int n = array.length;
    }
}
