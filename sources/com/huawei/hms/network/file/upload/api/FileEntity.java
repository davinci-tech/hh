package com.huawei.hms.network.file.upload.api;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.network.file.core.util.FLogger;
import java.io.File;

/* loaded from: classes4.dex */
public class FileEntity {
    private static final String TAG = "FileEntity";
    File file;
    String fileName;
    String name;
    long startPos;
    long uploadSize;
    Uri uri;

    public String toString() {
        return "FileEntity{name='" + this.name + "', fileName='" + this.fileName + "', startPos=" + this.startPos + ", uploadSize=" + this.uploadSize + '}';
    }

    public Uri getUri() {
        return this.uri;
    }

    public long getUploadSize() {
        return this.uploadSize;
    }

    public long getStartPos() {
        return this.startPos;
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Deprecated
    public File getFile() {
        return this.file;
    }

    @Deprecated
    public FileEntity(String str, String str2, File file, long j, long j2) {
        this.name = "file";
        this.fileName = "default";
        if (!TextUtils.isEmpty(str)) {
            this.name = str;
        }
        this.fileName = str2;
        if (file.isDirectory()) {
            throw new IllegalArgumentException("filepath cannot be a file directory");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("no such file");
        }
        this.file = file;
        this.startPos = j;
        this.uploadSize = j2 <= 0 ? file.length() - j : j2;
        FLogger.i(TAG, "FileEntity fileName:" + str2 + ",startPos:" + this.startPos + ",uploadSize:" + this.uploadSize + ",fileSize:" + file.length(), new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FileEntity(java.lang.String r6, java.lang.String r7, android.net.Uri r8, long r9, long r11) {
        /*
            r5 = this;
            r5.<init>()
            java.lang.String r0 = "file"
            r5.name = r0
            java.lang.String r1 = "default"
            r5.fileName = r1
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L13
            r5.name = r6
        L13:
            boolean r6 = android.text.TextUtils.isEmpty(r7)
            if (r6 != 0) goto L1b
            r5.fileName = r7
        L1b:
            if (r8 == 0) goto Le7
            r5.uri = r8
            r5.startPos = r9
            java.lang.String r6 = r8.getScheme()
            r6.hashCode()
            java.lang.String r1 = "content"
            boolean r1 = r6.equals(r1)
            java.lang.String r2 = "FileEntity"
            r3 = 0
            if (r1 != 0) goto L6d
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L3d
            r8 = r3
            r0 = r8
            goto L87
        L3d:
            java.io.File r6 = new java.io.File
            java.lang.String r8 = r8.getPath()
            r6.<init>(r8)
            r5.file = r6
            boolean r6 = r6.isDirectory()
            if (r6 != 0) goto L65
            java.io.File r6 = r5.file
            boolean r6 = r6.exists()
            if (r6 == 0) goto L5d
            java.io.File r6 = r5.file
            long r0 = r6.length()
            goto L85
        L5d:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "no such file"
            r6.<init>(r7)
            throw r6
        L65:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "filepath cannot be a file directory"
            r6.<init>(r7)
            throw r6
        L6d:
            android.content.Context r6 = com.huawei.hms.network.file.api.RequestManager.getAppContext()     // Catch: java.io.FileNotFoundException -> Lc9
            if (r6 == 0) goto Lc1
            android.content.Context r6 = com.huawei.hms.network.file.api.RequestManager.getAppContext()     // Catch: java.io.FileNotFoundException -> Lc9
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch: java.io.FileNotFoundException -> Lc9
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r6 = r6.openFileDescriptor(r8, r0)     // Catch: java.io.FileNotFoundException -> Lc9
            long r0 = r6.getStatSize()     // Catch: java.io.FileNotFoundException -> Lc9
        L85:
            long r8 = r0 - r9
        L87:
            int r6 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r6 > 0) goto L8e
            r5.uploadSize = r8
            goto L90
        L8e:
            r5.uploadSize = r11
        L90:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "FileEntity uri fileName:"
            r6.<init>(r8)
            r6.append(r7)
            java.lang.String r7 = ",startPos:"
            r6.append(r7)
            long r7 = r5.startPos
            r6.append(r7)
            java.lang.String r7 = ",uploadSize:"
            r6.append(r7)
            long r7 = r5.uploadSize
            r6.append(r7)
            java.lang.String r7 = ",totalFileLength:"
            r6.append(r7)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            com.huawei.hms.network.file.core.util.FLogger.i(r2, r6, r7)
            return
        Lc1:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch: java.io.FileNotFoundException -> Lc9
            java.lang.String r7 = "context is null,you must call UploadManager.build(context) before"
            r6.<init>(r7)     // Catch: java.io.FileNotFoundException -> Lc9
            throw r6     // Catch: java.io.FileNotFoundException -> Lc9
        Lc9:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "get size for SCHEME_CONTENT error:"
            r7.<init>(r8)
            java.lang.String r6 = r6.getMessage()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.huawei.hms.network.file.core.util.FLogger.v(r2, r6)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "file not found exception"
            r6.<init>(r7)
            throw r6
        Le7:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Uri is null"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.file.upload.api.FileEntity.<init>(java.lang.String, java.lang.String, android.net.Uri, long, long):void");
    }

    @Deprecated
    public FileEntity(String str, File file) {
        this(str, file.getName(), file, 0L, -1L);
    }

    public FileEntity(String str, Uri uri) {
        this(str, (String) null, uri, 0L, -1L);
    }

    @Deprecated
    public FileEntity(File file) {
        this((String) null, file);
    }

    public FileEntity(Uri uri) {
        this((String) null, (String) null, uri, 0L, -1L);
    }
}
