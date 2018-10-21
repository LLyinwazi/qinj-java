package wiwi.qinj.utils;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-21 13:54
 */
public class IDGenerator {

    private static int count = 0;

    private IDGenerator() {

    }

    private static synchronized int getCount() {
        if (count == 98)
            count = 0;
        return count++;
    }


    /**
     * 11位id
     * @return
     */
    public static String nextId() {
        return String.valueOf(System.currentTimeMillis()).substring(2, 11) + StringUtil.leftPed(String.valueOf(count), 2, '0');
    }


    /**
     * (11+prefix.length)位id
     * @param prefix 截取前面四位长度
     * @return
     */
    public static String nextId(String prefix) {
        if (prefix.length() > 4) {
            prefix = prefix.substring(0, 4);
        }
        prefix = prefix.toUpperCase();
        return prefix + String.valueOf(System.currentTimeMillis()).substring(2, 11) + StringUtil.leftPed(String.valueOf(count), 2, '0');
    }


}
