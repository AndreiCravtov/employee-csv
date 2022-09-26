package com.sparta.util;

import com.sparta.exceptions.BenchmarkerIsRunning;
import com.sparta.exceptions.BenchmarkerNotRunning;

import java.util.HashMap;
import java.util.Map;

public class Benchmarker {
    private final Map<String, Long> times = new HashMap();
    private boolean running = false;
    private String testName;
    private long start;

    public void start(String testName) throws BenchmarkerIsRunning {
        if (running) throw new BenchmarkerIsRunning();
        running = true;
        this.testName = testName;
        start = System.nanoTime();
    }

    public void stop() throws BenchmarkerNotRunning {
        if (!running) throw new BenchmarkerNotRunning();
        running = false;
        times.put(testName, System.nanoTime()-start);
    }

    public void drainToConsole() {
        times.keySet().forEach((String key) -> {
            System.out.printf("%s: %s ms\n", key, ((float) times.get(key))/1_000_000 );
        });
    }
}
