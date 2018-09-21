package com.sovince.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2018/9/20
 * Time: 11:30
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    
    public IntWritable one = new IntWritable(1);
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        String line = value.toString();
        String[] words = line.split(" ");
        for(String word:words){
            context.write(new Text(word),one);
        }
    }
}
