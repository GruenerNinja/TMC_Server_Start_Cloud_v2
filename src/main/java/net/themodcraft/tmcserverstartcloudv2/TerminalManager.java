package net.themodcraft.tmcserverstartcloudv2;

import java.io.IOException;

public class startServer {
    try {
        // Execute command to open a new Terminal window
        ProcessBuilder processBuilder = new ProcessBuilder("open", "-a", "Terminal");
        processBuilder.start();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
}