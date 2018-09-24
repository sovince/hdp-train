package com.sovince.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2018/9/20
 * Time: 11:43
 */
public class WordCountDriver {

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"wordcount000");

        //设置jar位置
        job.setJarByClass(WordCountDriver.class);

        //设置mapper和reducer的位置
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //额外设置combiner
        job.setCombinerClass(WordCountReducer.class);

        //设置map阶段的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce阶段的最终输出
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置数据来源的路径和结果的输出路径
        Path input = new Path("hdfs://sw2.test.com:8020/mbp/input");
        Path output = new Path("hdfs://sw2.test.com:8020/mbp/output");
        FileInputFormat.setInputPaths(job,input);
        FileOutputFormat.setOutputPath(job,output);

        //提交任务
        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);

    }
}
