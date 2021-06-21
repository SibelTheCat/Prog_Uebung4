package at.ac.fhcampuswien.newsanalyzer.downloader;

import at.ac.fhcampuswien.newsanalyzer.ctrl.NewsAPIException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader{

    @Override
    public int process(List<String> urls) throws NewsAPIException {
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
        List<Future<String>> allFutures = null;
        try {
            allFutures = pool.invokeAll(callables);
        } catch (InterruptedException e) {
            throw new NewsAPIException("execution got interrupted");
        }
        try {
        for(Future<String> f :allFutures) {
            String result = null;
                result = f.get();
                if (result != null)
                    count++;
            }}
             catch (InterruptedException e) {
                throw new NewsAPIException("execution got interrupted");
            } catch (ExecutionException e) {
                throw new NewsAPIException("problems with execution");
            }

        finally {
            pool.shutdown();}
        long endTime =  System.nanoTime();
        long compTime = endTime - startTime;
        System.out.println("The process with parallel downloading took: "+ compTime + " nanoseconds");
        return count;
    }


}
