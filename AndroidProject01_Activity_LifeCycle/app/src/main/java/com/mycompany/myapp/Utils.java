package com.mycompany.myapp;

public class Utils {
    public static String getTag() {
        String tag = "";
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for (int i = 0; i < ste.length; i++) {
            if (ste[i].getMethodName().equals("getTag")) {
                tag = "("+ste[i + 1].getFileName() + ":" + ste[i + 1].getLineNumber()+"): " + ste[i + 1].getMethodName() + "()";
            }
        }
        return tag;
    }
}
