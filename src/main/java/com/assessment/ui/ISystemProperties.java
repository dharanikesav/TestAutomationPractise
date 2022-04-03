package com.assessment.ui;

public interface ISystemProperties {
    String OS = System.getProperty("os.name");
    String currentDir = System.getProperty("user.dir");
    String pathSeperator = OS.contains("Mac") ? "/" : "\\";

}
