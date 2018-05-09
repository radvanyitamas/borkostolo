package com.example.olahgabormihaly.borkostolo_hits;

import com.example.olahgabormihaly.borkostolo_hits.algorithm.HITSAlgorithm;
import com.example.olahgabormihaly.borkostolo_hits.view.KostolokFragment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        List<Float> kostolok = new ArrayList<Float>() {{
            add((float) 1);
            add((float) 1);
            add((float) 1);
        }};
        List<Float> borok = new ArrayList<Float>() {{
            add((float) 60);
            add((float) 40);
            add((float) 55);
        }};
        HITSAlgorithm hitsAlgorithm = new HITSAlgorithm(kostolok, borok);
        try {
            List<Float> floats = Executors.newSingleThreadExecutor().submit(hitsAlgorithm).get();
            for (Float f : floats) {
                System.out.println("rank = " + f + "\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}