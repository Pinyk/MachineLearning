package apriori;

import java.util.List;
import java.util.Map;

/**
 * @Author: gaoyk
 * @Date: 2020/11/13 8:50
 */
public class TestDemo {
    public static void main(String[] args) throws Exception{
        Apriori apriori=new Apriori();
        List<String> srcData=apriori.readDataFile("D:\\学习笔记\\机器学习与数据挖掘\\实验二Apriori\\data.txt");

        Map<String,Integer> map=apriori.apriori(srcData);
        for(Map.Entry<String, Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("**********************************************");
        Map<String,Double> resuults=apriori.getRelation(map, srcData);
        for(Map.Entry<String, Double> entry:resuults.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
