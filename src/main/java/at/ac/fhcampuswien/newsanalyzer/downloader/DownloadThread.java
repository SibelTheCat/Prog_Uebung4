package at.ac.fhcampuswien.newsanalyzer.downloader;

import at.ac.fhcampuswien.newsanalyzer.ui.UserInterface;

public class DownloadThread implements Runnable {



        private int index;
        ParallelDownloader paral;
        String url;

        public DownloadThread(int index, ParallelDownloader paral, String url){
            this.index = index;
            this.paral = paral;
            this.url = url;
        }

        @Override
        public void run() {
         //   System.out.println(Thread.currentThread().getName() + " Start. Index = " + index + paral+ url);

               paral.saveUrl2File(url);

           // System.out.println(Thread.currentThread().getName() + " End.");
        }
    }

