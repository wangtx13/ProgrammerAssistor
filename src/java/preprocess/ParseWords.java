/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author apple
 */
public class ParseWords {

    private StringBuffer originalWords;
    private boolean ifGeneral;
    private Map<String, Boolean> libraryTypeCondition;

    public ParseWords(StringBuffer originalWords, boolean ifGeneral, Map<String, Boolean> libraryTypeCondition) {
        this.originalWords = originalWords;
        this.ifGeneral = ifGeneral;
        this.libraryTypeCondition = libraryTypeCondition;
    }

    public StringBuffer parseAllWords() {
        StringBuffer outputWords = new StringBuffer();

        /*分隔符：空格、引号"、左小括号(、右小括号)、左中括号[、有中括号]、点.、&、冒号:、分号;、换行符号\r\n、逗号*/
        String[] allWords = originalWords.toString().split(" |\"|\\(|\\)|\\[|\\]|\\.|&|:|;|\r\n|,|-|//");
        
        for (String word : allWords) {
            if (!word.equals("")) {
                String[] splitWords = splitCamelWords(word);
                /*若word被拆分，将原词也加入*/
                if (splitWords.length > 1) {
                    String parsedWords = removeStopWords(word.toLowerCase());
                    if(parsedWords != null)
                        parsedWords = removeClassLibrary(parsedWords.toLowerCase());
                    if (parsedWords != null) {
                        outputWords.append(word.toLowerCase());
                        outputWords.append(" ");
                    }
                }
                
                for (String aSplitWord : splitWords) {
                    String parsedWords = removeStopWords(aSplitWord);
                    if(parsedWords != null)
                        parsedWords = removeClassLibrary(parsedWords.toLowerCase());
                    if (parsedWords != null) {
                        outputWords.append(parsedWords);
                        outputWords.append(" ");
                    }
                }
            }
        }

        return outputWords;
    }

    private String[] splitCamelWords(String word) {
        if (!word.contains("_")) {
            /*XML、DOM、JHotDraw、ID不分割*/
            word = word.replace("XML", "Xml");
            word = word.replace("DOM", "Dom");
            word = word.replace("JHotDraw", "Jhotdraw");
            word = word.replace("ID", "id");

            /*正则表达式：分隔符为大写字母*/
            String regEx = "[A-Z]";
            Pattern p1 = Pattern.compile(regEx);
            Matcher m1 = p1.matcher(word);

            /*判断首字母是否大写*/
            boolean startWithUpper = false;
            startWithUpper = Pattern.matches("[A-Z].*", word);

            /*按照句子结束符分割句子，并存入list*/
            String[] words = p1.split(word);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                list.add(words[i]);
            }

            /*将句子结束符连接到相应的句子后*/
            int count = 0;
            while (m1.find()) {
                if (count + 1 < words.length) {
                    list.set(count + 1, m1.group() + list.get(count + 1));
                    ++count;
                } else {
                    list.add(m1.group());
                }
            }

            /*首字母大写且所有字符并非全部大写，去掉list中第一个空字符串*/
            if (startWithUpper && words.length != 0) {
                list.remove(0);
            }
            
            /*将list中所有字符串转为小写*/
            for(int i = 0; i < list.size(); ++i) {
                list.set(i, list.get(i).toLowerCase());
            }
            
            /*拷贝list到一个新数组*/
            String[] result = list.toArray(new String[1]);
            return result;
        } else {
            String[] result = new String[1];
            result[0] = word;
            return result;
        }
    }

    private String removeStopWords(String word) {
        String stopList = "abstract array boolean br class code dd ddouble dl "
                + "don double dt error exception exist exists extends false file "
                + "final float gt id implementation implemented import int "
                + "integer interface interfaces invoke invokes java lead li long "
                + "main method methodname methods nbsp null object objects "
                + "overrides package packages param parameters precison println "
                + "private protected public quot return returned returns static "
                + "string system throws tilocblob true ul version void";
        String[] stopwords = stopList.split(" ");
        for(String s : stopwords) {
            if(s.equals(word)) {
                word = null;
                break;
            }
        }
        
        return word;
    }
    
    private String removeClassLibrary(String word) {

        String stopList_common = "util lang";
        boolean general = ifGeneral;
        System.out.println();
        String stopList_draw = "javax swing awt org jhotdraw";
        boolean draw = libraryTypeCondition.get("Drawing");

        if (general) {
            String[] stopwords = stopList_common.split(" ");
            for (String s : stopwords) {
                if (s.equals(word)) {
                    word = null;
                    break;
                }
            }
        }
        
        if (draw && (word != null)) {
            String[] stopwords = stopList_draw.split(" ");
            for (String s : stopwords) {
                if (s.equals(word)) {
                    word = null;
                    break;
                }
            }
        }

        return word;
    }
}
