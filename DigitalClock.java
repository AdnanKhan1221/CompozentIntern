package com;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DigitalClock {
    public static void main(String[] args) {
        while (true) {
            // Get the current time
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String currentTime = formatter.format(calendar.getTime());
            
            // Display the current time
            System.out.println(currentTime);
            
            try {
                // Wait for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}