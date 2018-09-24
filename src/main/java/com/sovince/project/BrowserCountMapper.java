package com.sovince.project;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2018/9/20
 * Time: 11:30
 */
public class BrowserCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    
    public IntWritable one;
    public UserAgentParser userAgentParser;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
//        super.setup(context);
        userAgentParser = new UserAgentParser();
        one = new IntWritable(1);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        String line = value.toString();
        String source = line.substring(getCharPosition(line,"\"",7));
        UserAgent agent = userAgentParser.parse(source);
        String browser = agent.getBrowser();
        context.write(new Text(browser),one);
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
}
