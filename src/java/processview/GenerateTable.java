/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.Topics;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author apple
 */
public class GenerateTable {

    public static Topics generateTable(File topicsFile) {
        Topics topics = new Topics();
        topics.setFilePath(topicsFile.getPath());
        
        String[][] topicList = new String[45][];

        try {
            try(
            InputStream in = new FileInputStream(topicsFile.getPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))
            ) {
                String line;
                int lineNr = 0;
                while((line = reader.readLine()) != null) {
                    int index = 0;
                    String[] words = line.split("\t| ");
                    for(String word : words) {
                        System.out.println(word);
//                        if(!NumberUtils.isNumber(word)) {
//                            System.out.println(word);
//                            topicList[lineNr][index] = word;
//                        }
//                        ++index;
                    }
                    ++lineNr;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GenerateTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        topics.setTopics(topicList);
        return topics;
    }

}
