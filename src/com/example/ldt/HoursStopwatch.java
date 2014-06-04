package com.example.ldt;



public class HoursStopwatch {
	private long startTime = 0;
    private long stopTime = 0;
    private long timeLimit;
    private boolean running = false;

    
    public void Start(long timeLimit) {
        this.startTime = System.currentTimeMillis();
        this.timeLimit = HoursData.getHours() * 3600000; 
        this.running = true;
    }

    
    public void Stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    
    
    public long getElapsedTime() {
        long elapsed;
        if (running) {
             elapsed = (System.currentTimeMillis() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }
    
    
    //elaspsed time in seconds
    public long getElapsedTimeMinutes() {
        return getElapsedTime()/60000;
    }
	
    public long getElapsedTimeHours() {
    	return getElapsedTimeMinutes()/60;
    }
	
}
