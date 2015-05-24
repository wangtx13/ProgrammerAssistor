/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processshow;

import javabean.Topics;
import java.io.File;
import net.sf.json.JSONObject;

/**
 *
 * @author apple
 */
public class test {
    public static void main(String[] args) {
        Topics topicsBean = new Topics();
        topicsBean = GenerateTable.generateTable(new File("/Users/apple/NetBeansProjects/TopicModelingTools/test/topic_keys.txt"));
            String[][] topicsList = topicsBean.getTopics();
            
            for(int i = 0; i < topicsList.length; ++i) {
                for(int j = 0; i < topicsList[i].length; ++j) {
                    System.out.println(topicsList[i][j]);
                }
            }

    }
}
