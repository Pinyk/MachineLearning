package id3tree;

/**
 * @Author: gaoyk
 * @Date: 2020/12/22 20:32
 * 主程序类
 */
public class Main {
    public static void main(String[] args) {
        //初始化训练集数组
        String[] strs = new String[]{"头痛","肌肉痛","体温","患流感"};
        Object[] arrays = new Object[]{
                new String[]{"是","是","正常","否"},
                new String[]{"是","是","高","是"},
                new String[]{"是","是","很高","是"},
                new String[]{"否","是","正常","否"},
                new String[]{"否","否","高","否"},
                new String[]{"否","是","很高","是"},
                new String[]{"是","否","高","是"}};
        UtilID3 ID3Tree = new UtilID3();
        ID3Tree.create(arrays, 3);
    }
}
