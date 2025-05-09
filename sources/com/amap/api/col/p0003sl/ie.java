package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* loaded from: classes2.dex */
public final class ie {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1175a = ia.c("SYmFja3Vwcw");
    public static final String b = ia.c("SLmFkaXU");
    public static final String c = ia.c("JIw");

    public static void a(Context context, String str, String str2) {
        FileChannel fileChannel;
        RandomAccessFile randomAccessFile;
        synchronized (ie.class) {
            if (context != null) {
                if (context.checkCallingOrSelfPermission(ia.c("EYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX0VYVEVSTkFMX1NUT1JBR0U=")) == 0 && context.checkCallingOrSelfPermission(ia.c("EYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfRVhURVJOQUxfU1RPUkFHRQ==")) == 0) {
                    String a2 = a(context);
                    if (TextUtils.isEmpty(a2)) {
                        return;
                    }
                    String str3 = str + c + str2;
                    File file = new File(a2 + File.separator + f1175a);
                    File file2 = new File(file, b);
                    FileLock fileLock = null;
                    try {
                        if (!file.exists() || file.isDirectory()) {
                            file.mkdirs();
                        }
                        file2.createNewFile();
                        randomAccessFile = new RandomAccessFile(file2, "rws");
                        try {
                            fileChannel = randomAccessFile.getChannel();
                        } catch (Throwable unused) {
                            fileChannel = null;
                        }
                    } catch (Throwable unused2) {
                        fileChannel = null;
                        randomAccessFile = null;
                    }
                    try {
                        fileLock = fileChannel.tryLock();
                        if (fileLock != null) {
                            fileChannel.write(ByteBuffer.wrap(str3.getBytes("UTF-8")));
                        }
                        if (fileLock != null) {
                            try {
                                fileLock.release();
                            } catch (IOException unused3) {
                            }
                        }
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (IOException unused4) {
                            }
                        }
                        try {
                            randomAccessFile.close();
                        } catch (Throwable unused5) {
                        }
                    } catch (Throwable unused6) {
                        if (fileLock != null) {
                            try {
                                fileLock.release();
                            } catch (IOException unused7) {
                            }
                        }
                        if (fileChannel != null) {
                            try {
                                fileChannel.close();
                            } catch (IOException unused8) {
                            }
                        }
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Throwable unused9) {
                            }
                        }
                    }
                }
            }
        }
    }

    private static String a(Context context) {
        if (Build.VERSION.SDK_INT >= 31 || (context.getApplicationInfo().targetSdkVersion >= 30 && Build.VERSION.SDK_INT >= 30)) {
            return context.getApplicationContext().getExternalFilesDir("").getAbsolutePath();
        }
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName(ia.c("SYW5kcm9pZC5vcy5zdG9yYWdlLlN0b3JhZ2VWb2x1bWU"));
            Method method = storageManager.getClass().getMethod(ia.c("FZ2V0Vm9sdW1lTGlzdA"), new Class[0]);
            Method method2 = cls.getMethod(ia.c("ZZ2V0UGF0aA"), new Class[0]);
            Method method3 = cls.getMethod(ia.c("AaXNSZW1vdmFibGUK"), new Class[0]);
            Object invoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(invoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(invoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (!((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
            return null;
        } catch (Throwable unused) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }
}
