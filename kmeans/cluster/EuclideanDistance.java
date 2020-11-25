package kmeans.cluster;
import kmeans.utils.Vector;
/**
 * @Author: gaoyk
 * @Date: 2020/11/20 9:39
 * 计算两个向量之间的欧几里得距离
 */
public class EuclideanDistance extends DistanceMetric {
    protected double calcDistance(Vector vector1, Vector vector2) {
        return vector1.getEuclideanDistance(vector2);
    }
}
