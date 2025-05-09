package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

/* loaded from: classes8.dex */
public class ProcessUtils {
    private static String zzhf;
    private static int zzhg;

    private ProcessUtils() {
    }

    @Nullable
    public static String getMyProcessName() {
        if (zzhf == null) {
            if (zzhg == 0) {
                zzhg = Process.myPid();
            }
            zzhf = zzd(zzhg);
        }
        return zzhf;
    }

    @Nullable
    private static String zzd(int i) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        String str = null;
        if (i <= 0) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i);
            sb.append("/cmdline");
            bufferedReader = zzk(sb.toString());
        } catch (IOException unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            str = bufferedReader.readLine().trim();
            IOUtils.closeQuietly(bufferedReader);
        } catch (IOException unused2) {
            IOUtils.closeQuietly(bufferedReader);
            return str;
        } catch (Throwable th2) {
            bufferedReader2 = bufferedReader;
            th = th2;
            IOUtils.closeQuietly(bufferedReader2);
            throw th;
        }
        return str;
    }

    private static BufferedReader zzk(String str) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
