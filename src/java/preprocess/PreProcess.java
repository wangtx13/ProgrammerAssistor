/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package preprocess;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author apple
 */
public class PreProcess {
    
    public static boolean createDir(String dirPath) {
        File dir = new File(dirPath);
        if(dir.exists()) {
            System.out.println("The folder has existed");
            return false;
        }
        if(!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        if(dir.mkdirs()) {
            System.out.println("create successful: " + dirPath);
            return true;
        } else {
            System.out.println("create fail...");
            return false;
        }
    }
    
    /**
     * @param inputRootFilePath
     * @param timeStampStr
     * @param ifGeneral
     * @param libraryTypeCondition
     * @param args the command line arguments
     */
    public static void preProcess(String inputRootFilePath,String timeStampStr, boolean ifGeneral, Map<String, Boolean> libraryTypeCondition) {
        
        //Create a new folder
        String folderPath = "/Users/apple/NetBeansProjects/ProgrammerAssistor/output/PreProcess-" + timeStampStr + "/";
        createDir(folderPath);
           
        File inputRootFile = new File(inputRootFilePath);
        ArrayList<String> path = new ArrayList<>();
        if(!inputRootFile.isDirectory()) {
            System.out.println("Please input a extisted directory.");
        } else {
            TraversalFiles.fileList(inputRootFile, 0, path, folderPath, ifGeneral, libraryTypeCondition);
        }
        
        
    }
}
