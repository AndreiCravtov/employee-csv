package com.sparta.exceptions;

public class BenchmarkerNotRunning extends Exception {
    public BenchmarkerNotRunning() {
        super("The benchmarker is not currently running");
    }
}
