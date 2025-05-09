package com.huawei.agconnect.apms;

import android.os.Process;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/* loaded from: classes2.dex */
public class b0 {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final int[] bcd = {Process.myPid()};
    public static final FileFilter cde = new abc();

    public class abc implements FileFilter {
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file == null) {
                return false;
            }
            try {
                String name = file.getName();
                if (name.startsWith("cpu")) {
                    for (int i = 3; i < name.length(); i++) {
                        if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable unused) {
            }
            return false;
        }
    }

    public static int abc(String str, BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (readLine.contains(str)) {
                            for (int i = 0; i < readLine.length(); i++) {
                                if (readLine.charAt(i) >= '0' && readLine.charAt(i) <= '9') {
                                    sb.append(readLine.charAt(i));
                                }
                            }
                        }
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            abc.warn("failed to close /proc/meminfo file: " + e.getMessage());
                        }
                    }
                } catch (Throwable th) {
                    abc.warn("failed to read /proc/meminfo file: " + th.getMessage());
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        abc.warn("failed to close /proc/meminfo file: " + e2.getMessage());
                    }
                    return -1;
                }
            } catch (NumberFormatException e3) {
                abc.warn("failed to parse mem info: " + e3.getMessage());
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    abc.warn("failed to close /proc/meminfo file: " + e4.getMessage());
                }
                return -1;
            }
        }
        if (sb.length() == 0) {
            return -1;
        }
        int parseInt = Integer.parseInt(sb.toString());
        try {
            bufferedReader.close();
        } catch (IOException e5) {
            abc.warn("failed to close /proc/meminfo file: " + e5.getMessage());
        }
        return parseInt;
    }
}
