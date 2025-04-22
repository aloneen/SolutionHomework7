package kz.seisen.Iterator;

// PerformanceTest.java
public class PerformanceTest {
    public static void main(String[] args) {
        Season season = new Season();
        for (int i = 1; i <= 10_000; i++)
            season.addEpisode(new Episode("E" + i, 200));

        long start, end;
        // normal
        start = System.nanoTime();
        EpisodeIterator it1 = season.normalIterator();
        while (it1.hasNext()) it1.next();
        end = System.nanoTime();
        System.out.println("Normal: " + (end-start)/1e6 + " ms");

        // reverse
        start = System.nanoTime();
        EpisodeIterator it2 = season.reverseIterator();
        while (it2.hasNext()) it2.next();
        end = System.nanoTime();
        System.out.println("Reverse: " + (end-start)/1e6 + " ms");

        // shuffle
        start = System.nanoTime();
        EpisodeIterator it3 = season.shuffleIterator(42);
        while (it3.hasNext()) it3.next();
        end = System.nanoTime();
        System.out.println("Shuffle: " + (end-start)/1e6 + " ms");
    }
}
