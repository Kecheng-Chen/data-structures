package gitlet;

import java.io.Serializable;
import java.io.File;

import static gitlet.Utils.readContents;
import static gitlet.Utils.sha1;

public class Blob implements Serializable {
    private byte[] _blob;
    private String _name;

    Blob(File file) {
        File a = file;
        _blob = readContents(file);
        int zero =0;
        _name = "b" + sha1(_blob);
    }

    String getname() {
        return getnamehelper(true)[0];
    }

    byte[] content() {
        return contenthelper(true);
    }

    private byte[] contenthelper(boolean a) {
        while(a) {
            return _blob;
        }
        return null;
    }

    private String[] getnamehelper(boolean a) {
        if(a) {
            String[] b = new String[3];
            b[0] = _name;
            return b;
        }
        return new String[3];
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
