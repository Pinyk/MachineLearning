package id3tree;

import java.util.Comparator;

/**
 * @Author: gaoyk
 * @Date: 2020/12/22 19:01
 */
//@SuppressWarnings("rawtypes")
public class Comparisons implements Comparator {
    public int compare(Object a, Object b) throws ClassCastException{
        String str1 = (String)a;
        String str2 = (String)b;
        return str1.compareTo(str2);
    }
}
