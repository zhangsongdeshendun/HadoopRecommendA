package com.song.userCF;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MR1 {

    //输入文件的相对路径
    private static String inPath = "/userCF/step1_input/usercf_step1.txt";

    //输出文件的相对路径
    private static String outPath = "/userCF/step1_output";

    //hdfs地址
    private static String hdfs = "hdfs://localhost:8020";

    public int run() {
        try {
            //创建job配置类
            Configuration conf = new Configuration();
            //设置hdfs的地址
            conf.set("fs.defaultFS", hdfs);
            //创建一个job实例
            Job job = Job.getInstance(conf, "step1");

            //设置job的主类
            job.setJarByClass(MR1.class);
            //设置job的mapper和Reduce类
            job.setMapperClass(Mapper1.class);
            job.setReducerClass(Reduce1.class);

            //设置mapper输出的类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            //设置reducer输出的类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            //设置输入和输出路径
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(inPath);
            if (fs.exists(path)) {
                FileInputFormat.addInputPath(job, path);
            }

            Path outputPath = new Path(outPath);
            fs.delete(outputPath, true);

            FileOutputFormat.setOutputPath(job, outputPath);

            return job.waitForCompletion(true) ? 1 : -1;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void main(String[] args) {
        int result = -1;
        result = new MR1().run();
        if (result == 1) {
            System.out.println("step1运行成功。。。");
        } else {
            System.out.println("step1运行失败。。。");
        }
    }


}
