package com.song.userCF;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reduce1  extends Reducer<Text, Text, Text, Text> {

    private Text outKey = new Text();
    private Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        String itemID=key.toString();

        //<userID ,score>
        Map<String,Integer> map=new HashMap<>();

        for(Text value:values){
            String userID=value.toString().split("_")[0];
            String score=value.toString().split("_")[1];
            if (map.get(userID) == null) {
                map.put(userID, Integer.valueOf(score));
            } else {
                Integer perScore = map.get(userID);
                map.put(userID, perScore + Integer.valueOf(score));
            }

        }

        StringBuilder stringBuilder=new StringBuilder();
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            String userID=entry.getKey();
            String score=String.valueOf(entry.getValue());

            stringBuilder.append(userID+"_"+score+",");

        }

        String line=null;
        //去掉行末的"，"
        if(stringBuilder.toString().endsWith(",")){
            line=stringBuilder.substring(0,stringBuilder.length()-1);
        }
        outKey.set(itemID);
        outValue.set(line);
        context.write(outKey,outValue);



    }
}
