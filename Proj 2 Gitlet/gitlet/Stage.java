package gitlet;

import java.io.Serializable;
import java.util.HashMap;

public class Stage implements Serializable {
    HashMap getfile() {
        return _curr;
    }
    private HashMap<String, String> _curr = new HashMap<String, String>();
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
}