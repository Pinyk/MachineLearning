package kmeans.cluster;

/**
 * @Author: gaoyk
 * @Date: 2020/11/25 22:47
 * 聚类接口
 */
public interface Clusterer {
    public ClusterList runKMeansClustering(DataList documentList, int k);
}
