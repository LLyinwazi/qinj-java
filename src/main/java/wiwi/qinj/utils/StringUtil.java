package wiwi.qinj.utils;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-21 13:57
 */
public class StringUtil {


    public static void main(String[] args) {
        String kkx = StringUtil.leftPed("kkx", 6, '0');
        System.out.println("kkx:" + kkx);
    }

    public static String leftPed(String rec, int length, char c) {
        int recLen = rec.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length - recLen; i++) {
            builder.append(c);
        }
        builder.append(rec);
        return builder.toString();
    }

}
