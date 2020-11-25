package kmeans.main;

import java.io.IOException;
import kmeans.cluster.ClusterList;
import kmeans.cluster.Clusterer;
import kmeans.cluster.DistanceMetric;
import kmeans.cluster.DataList;
import kmeans.cluster.EuclideanDistance;
import kmeans.cluster.KMeansClusterer;
import kmeans.utils.OutPutFile;

/**
 * @Author: gaoyk
 * @Date: 2020/11/20 9:07
 */
public class Main {
    private static final int iter = 500; //设置总迭代次数
    private static final int feature_number = 2; //设置特征维度
    private static final int k = 3;  //确定簇的数目
    public static void main(String[] args) throws IOException {
        //文档目录
        String fileinput = "E:\\java第一行代码demo\\复习\\src\\kmeans\\data";
        //读取文件,将其转化成向量形式
        DataList documentList = new DataList(fileinput,feature_number);
    //定义距离公式,这里使用欧几里得距离
        DistanceMetric distance = new EuclideanDistance();
        Clusterer clusterer = new KMeansClusterer(distance, iter);
        //聚类
        ClusterList clusterList = clusterer.runKMeansClustering(documentList, k);
        System.out.println(clusterList);
        //输出结果
        OutPutFile.outputClusterAndContent("result/cluster"+k,clusterList);
    }
}