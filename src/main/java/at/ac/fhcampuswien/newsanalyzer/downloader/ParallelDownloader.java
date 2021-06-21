package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader{

    @Override
    public int process(List<String> urls) throws InterruptedException, ExecutionException {
        long startTime =  System.nanoTime();

        int num_of_threads = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(num_of_threads);
        List<Callable<String>> callables = new ArrayList<>();

            int count = 0;
            for(int i = 0; i< urls.size(); i++){
                int idx = i;
                Callable<String> task = () -> saveUrl2File(urls.get(idx));
                callables.add(task);

        }
        List<Future<String>> allFutures = pool.invokeAll(callables);
        for(Future<String> f :allFutures){
            String result = f.get();
            if(result != null)
                count++;

        }
        long endTime =  System.nanoTime();
        long compTime = endTime - startTime;
        System.out.println("The process with parallel downloading took: "+ compTime + " nanoseconds");
        pool.shutdown();
        return count;

    }


}
