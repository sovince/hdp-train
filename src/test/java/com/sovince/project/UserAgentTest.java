package com.sovince.project;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2018/9/24
 * Time: 14:34
 */
public class UserAgentTest {

    @Test
    public void testReadFile() throws Exception{
        String path = "/Users/vince/Study/coding-128/data/100_access.log";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path)))
        );
        Map<String,Integer> browserMap = new HashMap<String,Integer>();


        String line = "";
        int i = 0;
        UserAgentParser userAgentParser = new UserAgentParser();
        while (line !=null){
            line = reader.readLine();
            if(StringUtils.isNotBlank(line)){
                i++;
                String source = line.substring(getCharPosition(line,"\"",7)+1);
                UserAgent userAgent = userAgentParser.parse(source);

                String browser = userAgent.getBrowser();
                String engine = userAgent.getEngine();
                String engineVersion = userAgent.getEngineVersion();
                String OS = userAgent.getOs();
                String platform = userAgent.getPlatform();

                System.out.println(browser+" , "+engine+" , "+engineVersion+" , "+OS+" , "+platform);

                Integer browserValue = browserMap.get(browser);
                if(browserValue!=null){
                    browserMap.put(browser,browserValue+1);
                }else {
                    browserMap.put(browser,1);
                }
            }
        }
        System.out.println("All records: "+i);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");

        for (Map.Entry<String,Integer> entry:browserMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    @Test
    public void testGetCharPosition(){
        String source = "117.35.88.11 - - [10/Nov/2016:00:01:02 +0800] \"GET /article/ajaxcourserecommends?id=124 HTTP/1.1\" 200 2345 \"www.imooc.com\" \"http://www.imooc.com/code/1852\" - \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36\" \"-\" 10.100.136.65:80 200 0.616 0.616";
        System.out.println(getCharPosition(source,"\"",7));
    }

    private int getCharPosition(String value,String operater,int index){
        Matcher slashMatcher = Pattern.compile(operater).matcher(value);
        int mIdx = 0;
        while (slashMatcher.find()){
            mIdx++;
            if (mIdx==index) break;
        }
        return slashMatcher.start();
    }


    @Test
    public void fuck(){
//        String source = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";
        String source = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0";
        UserAgentParser userAgentParser  = new UserAgentParser();
        UserAgent agent = userAgentParser.parse(source);
        System.out.println(agent.getBrowser());
        System.out.println(agent.getEngine());
        System.out.println(agent.getEngineVersion());
        System.out.println(agent.getOs());
        System.out.println(agent.getPlatform());
        System.out.println(agent.getVersion());
        System.out.println(agent.isMobile());

    }
}
