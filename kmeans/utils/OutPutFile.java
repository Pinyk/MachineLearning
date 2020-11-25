package kmeans.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import kmeans.cluster.Cluster;
import kmeans.cluster.ClusterList;
import kmeans.cluster.Data;
/**
 * @Author: gaoyk
 * @Date: 2020/11/20 9:24
 */
public class OutPutFile {
    /** write the results to a file */
    public static void outputCluster(String strDir,ClusterList clusterList) throws IOException{
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(strDir)),"gbk"));
        int i = 0;
        for (Cluster cluster : clusterList) {
            writer.write("Cluster" + i + ":" + cluster.getCentroid() + "\n");
            for (Data doc: cluster.getDatas()) {
                writer.write("\t" + doc.getTitle() + "\n");
            }
            i++;
        }
        writer.close();
    }
    public static void outputClusterAndContent(String strDir,ClusterList clusterList) throws IOException{
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(strDir)),"gbk"));
        int i = 0;
        for (Cluster cluster : clusterList) {
            writer.write("Cluster" + i + ":" + cluster.getCentroid() + "\n");
            for (Data doc: cluster.getDatas()) {
                writer.write("\t" + doc.getTitle() + "\t" + doc.getVector() + "\n");
            }
            i++;
        }
        writer.close();
    }
}
