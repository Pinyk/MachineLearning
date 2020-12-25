package id3tree;

/**
 * @Author: gaoyk
 * @Date: 2020/12/22 18:59
 */
import java.util.*;

public class UtilID3 {
    TreeNode root;
    private boolean[] flag;
    //训练集
    private Object[] trainArrays;
    //节点索引
    private int nodeIndex;
    //创建
    public void create(Object[] arrays, int index) {
        this.trainArrays = arrays;
        initial(arrays, index);
        createDTree(arrays);
        printDTree(root);
    }

    //初始化
    public void initial(Object[] dataArray, int index) {
        this.nodeIndex = index;

        //数据初始化
        this.flag = new boolean[((String[])dataArray[0]).length];
        for (int i = 0; i<this.flag.length; i++)
        {
            if (i == index)
            {
                this.flag[i] = true;
            }
            else
            {
                this.flag[i] = false;
            }
        }
    }

    //创建决策树
    public void createDTree(Object[] arrays) {
        Object[] ob = getMaxGain(arrays);
        if (this.root == null) {
            this.root = new TreeNode();
            root.parent = null;
            root.parentAttribute = null;
            root.attributes = getAttributes(((Integer)ob[1]).intValue());  //取属性名
            root.nodeName = getNodeName(((Integer)ob[1]).intValue());    //取节点名
            root.childNodes = new TreeNode[root.attributes.length];   //构造孩子节点
            insert(arrays, root);
        }
    }

    //插入决策树
    public void insert(Object[] arrays, TreeNode parentNode){
        String[] attributes = parentNode.attributes;
        for (int i = 0; i < attributes.length; i++){
            Object[] Arrays = pickUpAndCreateArray(arrays, attributes[i],getNodeIndex(parentNode.nodeName));
            Object[] info = getMaxGain(Arrays);      //对新的数据集进行计算信息增益
            double gain = ((Double)info[0]).doubleValue();
            if (gain != 0) {
                int index = ((Integer)info[1]).intValue();
                TreeNode currentNode = new TreeNode();
                currentNode.parent = parentNode;
                currentNode.parentAttribute = attributes[i];    //设置父属性
                currentNode.attributes = getAttributes(index);   //获取属性名字
                currentNode.nodeName = getNodeName(index);    //获得节点名称
                currentNode.childNodes = new TreeNode[currentNode.attributes.length];
                parentNode.childNodes[i] = currentNode;
                insert(Arrays, currentNode);
            } else {
                TreeNode leafNode = new TreeNode();
                leafNode.parent = parentNode;
                leafNode.parentAttribute = attributes[i];
                leafNode.attributes = new String[0];
                leafNode.nodeName = getLeafNodeName(Arrays);    //信息增益为0 该数组对应的属性唯一值
                leafNode.childNodes = new TreeNode[0];
                parentNode.childNodes[i] = leafNode;
            }
        }
    }

    //输出
    public void printDTree(TreeNode node) {
        System.out.println("节点->" + node.nodeName);
        TreeNode[] childs = node.childNodes;
        for (int i = 0; i < childs.length; i++) {
            if (childs[i] != null)
            {
                System.out.println("如果" + childs[i].parent.nodeName + childs[i].parentAttribute);
                printDTree(childs[i]);
            }
        }
    }

    //剪取数组  获取节点各个属性对应的数组  新的数据集
    public Object[] pickUpAndCreateArray(Object[] arrays, String attribute, int index) {
        List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < arrays.length; i++) {
            String[] strs = (String[])arrays[i];
            if (strs[index].equals(attribute)) {
                list.add(strs);
            }
        }
        return list.toArray();
    }

    //取得节点名
    public String getNodeName(int index) {
        String[] strs = new String[]{"头痛","肌肉痛","体温","患流感"};
        for (int i = 0; i < strs.length; i++) {
            if (i == index) {
                return strs[i];
            }
        }
        return null;
    }

    //取得叶子节点名
    public String getLeafNodeName(Object[] arrays) {
        if (arrays != null && arrays.length > 0) {
            String[] strs = (String[])arrays[0];
            return strs[nodeIndex];
        }
        return null;
    }

    //取得节点索引
    public int getNodeIndex(String name) {
        String[] strs = new String[]{"头痛","肌肉痛","体温","患流感"};
        for (int i = 0; i < strs.length; i++) {
            if (name.equals(strs[i])) {
                return i;
            }
        }
        return -1;
    }



    //得到最大信息增益
    public Object[] getMaxGain(Object[] arrays) {
        Object[] result = new Object[2];
        double gain = 0;
        int index = -1;
        for (int i = 0; i<this.flag.length; i++) {
            if (!this.flag[i]) {
                double value = gain(arrays, i);
                if (gain < value) {
                    gain = value;
                    index = i;
                }
            }
        }
        result[0] = gain;

        //信息增益最大的属性
        result[1] = index;
        if (index != -1) {
            this.flag[index] = true;
        }
        return result;
    }

    //取得属性数组
    public String[] getAttributes(int index){
        @SuppressWarnings("unchecked")
        TreeSet<String> set = new TreeSet<String>(new Comparisons());
        for (int i = 0; i<this.trainArrays.length; i++)
        {
            String[] strs = (String[])this.trainArrays[i];
            set.add(strs[index]);
        }
        String[] result = new String[set.size()];
        return set.toArray(result);



    }

    //计算信息增益
    public double gain(Object[] arrays, int index) {
        //play中存放属性值  第一次nodeindex=3 play中存放的为 是 否
        String[] playBalls = getAttributes(this.nodeIndex);
        int[] counts = new int[playBalls.length];
        for (int i = 0; i<counts.length; i++) {
            counts[i] = 0;
        }

        //counts用于记录每个类别个数  起始值为0
        for (int i = 0; i<arrays.length; i++) {
            String[] strs = (String[])arrays[i];
            for (int j = 0; j<playBalls.length; j++) {
                if (strs[this.nodeIndex].equals(playBalls[j])) {
                    counts[j]++;
                }
            }
        }

        double entropyS = 0;
        for (int i = 0;i <counts.length; i++) {
            //第一次counts[i]=3 4
            entropyS = entropyS + Entropy.getEntropy(counts[i], arrays.length);
        }

        //第一次entropy中的值为信息熵

        String[] attributes = getAttributes(index);   //第一次得到第一个属性的三个值
        double total = 0;    //total存放各个属性的信息增益
        for (int i = 0; i<attributes.length; i++){
            total = total + entropy(arrays, index, attributes[i], arrays.length);
        }
        //此时total为某个属性几个值信息增益和
        return entropyS - total;
    }

    //计算每个属性信息增益   返回每个属性求信息增益的权重乘信息增益
    public double entropy(Object[] arrays, int index, String attribute, int totals)
    {
        String[] playBalls = getAttributes(this.nodeIndex);
        int[] counts = new int[playBalls.length];
        for (int i = 0; i < counts.length; i++)
        {
            counts[i] = 0;
        }

        for (int i = 0; i < arrays.length; i++) {
            String[] strs = (String[])arrays[i];
            if (strs[index].equals(attribute)) {
                for (int k = 0; k<playBalls.length; k++) {
                    if (strs[this.nodeIndex].equals(playBalls[k]))
                    {
                        counts[k]++;
                    }
                }
            }
        }

        int total = 0;
        double entropy = 0;
        for (int i = 0; i < counts.length; i++)
        {
            total = total +counts[i];
        }

        for (int i = 0; i < counts.length; i++)
        {
            entropy = entropy + Entropy.getEntropy(counts[i], total);
        }
        return Entropy.getShang(total, totals)*entropy;

    }
}
