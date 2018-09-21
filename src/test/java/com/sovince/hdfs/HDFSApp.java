package com.sovince.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2018/9/20
 * Time: 19:32
 */
public class HDFSApp {
    private static final String HDFS_PATH="hdfs://sw2:8020";
    private static final String USER="hdfs";

    private FileSystem fileSystem=null;
    private Configuration configuration=null;

    @Before
    public void setUp() throws Exception{
        System.out.println("Starting...");
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH),configuration,USER);
    }

    @After
    public void tearDown() throws Exception{
        configuration = null;
        fileSystem = null;
        System.out.println();
        System.out.println("Ending...");
    }

    @Test
    public void mkdir() throws Exception{
        fileSystem.mkdirs(new Path("/mbp/hdp"));
        System.out.println("made a dir");
    }
}
