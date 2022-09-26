package com.sparta.exceptions;

public class BenchmarkerIsRunning extends Exception {
    public BenchmarkerIsRunning() {
        super("The benchmarker is currently running");
    }
}
