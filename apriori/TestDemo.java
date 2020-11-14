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
        List<String> srcData=apriori.readDataFile("E:/test/data.txt");
		/*源数据
		 * 记录条数：9
			1;2;5;
			2;4;
			2;3;
			1;2;4;
			1;3;
			2;3;
			1;3;
			1;2;3;5;
			1;2;3;
		 */
        Map<String,Integer> map=apriori.apriori(srcData);
        for(Map.Entry<String, Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
		/*频繁项集
		 * 2;1;5;:2
		2;1;3;:2
		4;2;:2
		*/
        System.out.println("**********************************************");
        Map<String,Double> resuults=apriori.getRelation(map, srcData);
        for(Map.Entry<String, Double> entry:resuults.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
