package it.cioscos.dndmapbe.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Common {

    public boolean isStringNullOrBlank(String s) {
        return s == null || s.trim().equals("");
    }

    public String createLog(String message) {
        var methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        var logString = methodName;
        if (!Common.isStringNullOrBlank(message)) {
            logString += ": " + message;
        }

        return logString;
    }
}
