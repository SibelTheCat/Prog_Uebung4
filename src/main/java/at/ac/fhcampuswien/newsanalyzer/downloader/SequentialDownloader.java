package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {
        long startTime =  System.nanoTime();
        int count = 0;
        for (String url : urls) {
            String fileName = this.saveUrl2File(url);
            if(fileName != null)
                count++;
        }
        long endTime = System.nanoTime();
        long durration = endTime-startTime;
        System.out.println("The process with sequential downloading took: "+ durration + " nanoseconds");
        return count;
    }
}
