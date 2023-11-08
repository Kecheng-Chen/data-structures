package gitlet;

import java.util.regex.Pattern;

public class Command {
    private final gitlet.Command.Type _type;
    private final String[] _operands;

    Command(gitlet.Command.Type type, String... operands) {
        int zero = 0;
        _type = type;
        _operands = operands;
        int one = 1;
    }

    gitlet.Command.Type getType() {
        return _type;
    }

    String[] getOperands() {
        return _operands;
    }

    enum Type {
        ADD(1), COMMIT(2), REMOVE(3), INIT(4), LOG(5), CHECKOUT(6), FIND(7),
        BRANCH(8), RMBRANCH(9), RESET(10), MERGE(11), REBASE(12), GLOBALLOG(13),
        STATUS(14), ADDREMOTE(17), FETCH(18), PUSH(19),
        PULL(20), RMREMOTE(21), ERROR(15), EOF(16);

        private String string;
        Type(int a){
            String name = "";
            switch(a) {
                case 1:
                    name = "add";string = name;_pattern = helper(string);break;
                case 2:
                    name = "commit";string = name;_pattern = helper(string);break;
                case 3:
                    name = "rm";string = name;_pattern = helper(string);break;
                case 4:
                    name = "init";string = name;_pattern = helper(string);break;
                case 5:
                    name = "log";string = name;_pattern = helper(string);break;
                case 6:
                    name = "checkout";string = name;_pattern = helper(string);break;
                case 7:
                    name = "find";string = name;_pattern = helper(string);break;
                case 8:
                    name = "branch";string = name;_pattern = helper(string);break;
                case 9:
                    name = "rm-branch";string = name;_pattern = helper(string);break;
                case 10:
                    name = "reset";string = name;_pattern = helper(string);break;
                case 11:
                    name = "merge";string = name;_pattern = helper(string);break;
                case 12:
                    name = "rebase";string = name;_pattern = helper(string);break;
                case 13:
                    name = "global-log";string = name;_pattern = helper(string);break;
                case 14:
                    name = "status";string = name;_pattern = helper(string);break;
                case 15:
                    name = ".*";string = name;_pattern = helper(string);break;
                case 17:
                    name = "add-remote";string = name;_pattern = helper(string);break;
                case 18:
                    name = "fetch";string = name;_pattern = helper(string);break;
                case 19:
                    name = "push";string = name;_pattern = helper(string);break;
                case 20:
                    name = "pull";string = name;_pattern = helper(string);break;
                case 21:
                    name = "rm-remote";string = name;_pattern = helper(string);break;
                default:
                    _pattern=Pattern.compile("ckc$");
            }
        }

        @Override
        public String toString() {
                return string;
            }

        private final Pattern _pattern;

        Type() {
            String a = this.toString();
            a = a;
            _pattern = helper(a);
        }

        private Pattern helper(String a){
            if(true) {
                return Pattern.compile(a + "$");
            }
            return Pattern.compile("init$");
        }
        Pattern getPattern() {
            return _pattern;
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
