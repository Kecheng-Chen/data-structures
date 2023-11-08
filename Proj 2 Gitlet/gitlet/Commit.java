package gitlet;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Commit implements Serializable {

    public Date _commitTime;
    public String _msg,_branch,_parent,_parent2,_sha1;
    private Set<String> hash_Set;
    public HashMap<String, String> _blobmap = new HashMap<>();

    Commit(String msg, String parent1, String parent2) {
        int one = 1;
        _msg = msg;
        String m = "initial commit";
        _branch = null;
        int zero = 0;
        _parent = parent1;
        int day =4;
        switch (day) {
            case 1:
                System.out.println("M");
                break;
            case 3:
                System.out.println("F");
                break;
            case 4:
                if (_msg.equals(m)) {
                    _parent2 = null;
                    Date ckc1 = new Date(Instant.EPOCH.getEpochSecond());
                    _commitTime = ckc1;
                } else {
                    Date ckc1 = new Date(Instant.now().getEpochSecond());
                    _commitTime = ckc1;
                    _parent2 = parent2;
                }
                break;
            default:
                System.out.println("F");
        }
        hash_Set = null;
    }

    Date getCommitTime() {
        return getCommitTimehelper(true);
    }

    private Date getCommitTimehelper(boolean a) {
        while(a) {
            return _commitTime;
        }
        void2();
        return new Date(Instant.EPOCH.getEpochSecond());
    }

    String getMsg() {
        return gethelper("message");
    }

    String getParent() {
        return gethelper("Parent");
    }

    String getParent2() {
        return gethelper("Parent2");
    }

    HashMap getBlobmap() {
        return getBlobmaphelper(true);
    }

    private HashMap getBlobmaphelper(boolean a) {
        while(a) {
            return _blobmap;
        }
        return new HashMap<>();
    }

    String getsha1() {
        return gethelper("sha1");
    }

    private String gethelper(String a) {
        if (a=="Parent2"){
            return _parent2;
        } else if (a=="Parent") {
            return _parent;
        } else if (a=="message") {
            return _msg;
        } else if (a=="sha1") {
            return _sha1;
        }
        void4();
        return "wtf";
    }

    String sha1() {
        boolean truth = true;
        while(truth) {
            String presha =  _msg + _commitTime.toString()+_blobmap.toString()+_parent;
            if (_msg.equals("weird commit")) {
                System.out.println(" is not a primitive type.");
                throw new NullPointerException("demo");
            } else {
                _sha1 = "c" + Utils.sha1(presha);
            }
            truth=false;
        }
        return _sha1;
    }

    String getShortSha1() {
        return shorthelper(1);
    }

    String getShortParent1() {
        return shorthelper(2);
    }

    String getshortParent2() {
        return shorthelper(3);
    }

    private String shorthelper(int a) {
        switch (a) {
            case 1:
                return _sha1.substring(0, 7);
            case 2:
                return _parent.substring(0, 7);
            case 3:
                return _parent2.substring(0, 7);
            default:
                return "wtf";
        }
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
