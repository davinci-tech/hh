package com.huawei.hms.opendevice;

import com.huawei.hms.ads.uiengineloader.aj;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: classes4.dex */
public abstract class m {
    public static void a(File file) throws IOException {
        if (file.exists()) {
            return;
        }
        if (file.getParentFile() == null) {
            HMSLog.e(aj.f4374a, "parent file is null.");
            throw new IOException("parent file is null");
        }
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            HMSLog.e(aj.f4374a, "make parent dirs failed.");
            throw new IOException("make parent dirs failed");
        }
        if (file.createNewFile()) {
            return;
        }
        HMSLog.e(aj.f4374a, "create file failed.");
        throw new IOException("create file failed");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a(String str) {
        Reader reader;
        InputStreamReader inputStreamReader;
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader2 = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (FileNotFoundException unused) {
                            inputStreamReader2 = bufferedReader;
                            HMSLog.e(aj.f4374a, "file not exist.");
                            IOUtils.closeQuietly((Reader) inputStreamReader);
                            IOUtils.closeQuietly((Reader) inputStreamReader2);
                            return sb.toString();
                        } catch (IOException unused2) {
                            inputStreamReader2 = bufferedReader;
                            HMSLog.e(aj.f4374a, "read value IOException.");
                            IOUtils.closeQuietly((Reader) inputStreamReader);
                            IOUtils.closeQuietly((Reader) inputStreamReader2);
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            reader = bufferedReader;
                            inputStreamReader2 = inputStreamReader;
                            IOUtils.closeQuietly((Reader) inputStreamReader2);
                            IOUtils.closeQuietly(reader);
                            throw th;
                        }
                    }
                    IOUtils.closeQuietly((Reader) inputStreamReader);
                    IOUtils.closeQuietly((Reader) bufferedReader);
                } catch (FileNotFoundException unused3) {
                } catch (IOException unused4) {
                }
            } catch (Throwable th2) {
                th = th2;
                reader = null;
            }
        } catch (FileNotFoundException unused5) {
            inputStreamReader = null;
        } catch (IOException unused6) {
            inputStreamReader = null;
        } catch (Throwable th3) {
            th = th3;
            reader = null;
            IOUtils.closeQuietly((Reader) inputStreamReader2);
            IOUtils.closeQuietly(reader);
            throw th;
        }
        return sb.toString();
    }
}
