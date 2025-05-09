package com.huawei.hms.network.file.core.util;

import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.hms.framework.common.CreateFileUtil;
import com.huawei.hms.framework.common.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/* loaded from: classes4.dex */
public class c {
    public static FileInputStream b(String str) {
        try {
            return CreateFileUtil.newFileInputStream(str);
        } catch (FileNotFoundException unused) {
            FLogger.w(FileUtil.TAG, "newFileInputStream is fileNotFoundException", new Object[0]);
            return new FileInputStream(str);
        } catch (RuntimeException unused2) {
            FLogger.w(FileUtil.TAG, "newFileInputStream is runtimeException", new Object[0]);
            return new FileInputStream(str);
        } catch (Throwable unused3) {
            FLogger.w(FileUtil.TAG, "newFileInputStream is Throwable", new Object[0]);
            return new FileInputStream(str);
        }
    }

    public static RandomAccessFile a(String str, String str2) {
        if (str != null) {
            try {
                return CreateFileUtil.newRandomAccessFile(str, str2);
            } catch (FileNotFoundException unused) {
                FLogger.w(FileUtil.TAG, "newRandomAccessFile is fileNotFoundException", new Object[0]);
                return new RandomAccessFile(str, str2);
            } catch (RuntimeException unused2) {
                FLogger.w(FileUtil.TAG, "newRandomAccessFile is runtimeException", new Object[0]);
                return new RandomAccessFile(str, str2);
            } catch (Throwable unused3) {
                FLogger.w(FileUtil.TAG, "newRandomAccessFile is Throwable", new Object[0]);
                return new RandomAccessFile(str, str2);
            }
        }
        Logger.w(FileUtil.TAG, "newRandomAccessFile  file is null");
        throw new FileNotFoundException("file is null");
    }

    public static File a(String str) {
        if (str == null) {
            return null;
        }
        try {
            File newFile = CreateFileUtil.newFile(str);
            return !newFile.exists() ? new File(str) : newFile;
        } catch (RuntimeException unused) {
            FLogger.w(FileUtil.TAG, "newFile is runtimeException", new Object[0]);
            return new File(str);
        } catch (Throwable unused2) {
            FLogger.w(FileUtil.TAG, "newFile is Throwable", new Object[0]);
            return new File(str);
        }
    }
}
