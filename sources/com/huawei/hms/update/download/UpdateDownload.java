package com.huawei.hms.update.download;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.download.api.IOtaUpdate;
import com.huawei.hms.update.download.api.IUpdateCallback;
import com.huawei.hms.update.download.api.UpdateInfo;
import com.huawei.hms.update.http.CanceledException;
import com.huawei.hms.update.http.HttpRequestHelper;
import com.huawei.hms.update.http.IHttpRequestHelper;
import com.huawei.hms.update.provider.UpdateProvider;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.FileUtil;
import com.huawei.hms.utils.IOUtils;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes9.dex */
public class UpdateDownload implements IOtaUpdate {

    /* renamed from: a, reason: collision with root package name */
    private final Context f6048a;
    private final IHttpRequestHelper b = new HttpRequestHelper();
    private final DownloadRecord c = new DownloadRecord();
    private IUpdateCallback d;
    private File e;

    public UpdateDownload(Context context) {
        this.f6048a = context.getApplicationContext();
    }

    @Override // com.huawei.hms.update.download.api.IOtaUpdate
    public void cancel() {
        HMSLog.i("UpdateDownload", "Enter cancel.");
        a((IUpdateCallback) null);
        this.b.cancel();
    }

    @Override // com.huawei.hms.update.download.api.IOtaUpdate
    public void downloadPackage(IUpdateCallback iUpdateCallback, UpdateInfo updateInfo) {
        Checker.checkNonNull(iUpdateCallback, "callback must not be null.");
        HMSLog.i("UpdateDownload", "Enter downloadPackage.");
        a(iUpdateCallback);
        if (updateInfo == null || !updateInfo.isValid()) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Invalid update info.");
            a(2201, 0, 0);
            return;
        }
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Invalid external storage for downloading file.");
            a(2204, 0, 0);
            return;
        }
        String str = updateInfo.mPackageName;
        if (TextUtils.isEmpty(str)) {
            HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Download the package,  packageName is null: ");
            a(2201, 0, 0);
            return;
        }
        File localFile = UpdateProvider.getLocalFile(this.f6048a, str + ".apk");
        this.e = localFile;
        if (localFile == null) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Failed to get local file for downloading.");
            a(2204, 0, 0);
            return;
        }
        File parentFile = localFile.getParentFile();
        if (parentFile == null || !(parentFile.mkdirs() || parentFile.isDirectory())) {
            HMSLog.e("UpdateDownload", "In downloadPackage, Failed to create directory for downloading file.");
            a(2201, 0, 0);
        } else if (parentFile.getUsableSpace() < updateInfo.mSize * 3) {
            HMSLog.e("UpdateDownload", "In downloadPackage, No space for downloading file.");
            a(2203, 0, 0);
        } else {
            try {
                a(updateInfo);
            } catch (CanceledException unused) {
                HMSLog.w("UpdateDownload", "In downloadPackage, Canceled to download the update file.");
                a(2101, 0, 0);
            }
        }
    }

    @Override // com.huawei.hms.update.download.api.IOtaUpdate
    public Context getContext() {
        return this.f6048a;
    }

    private void a(IUpdateCallback iUpdateCallback) {
        synchronized (this) {
            this.d = iUpdateCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3) {
        synchronized (this) {
            IUpdateCallback iUpdateCallback = this.d;
            if (iUpdateCallback != null) {
                iUpdateCallback.onDownloadPackage(i, i2, i3, this.e);
            }
        }
    }

    void a(UpdateInfo updateInfo) throws CanceledException {
        String str;
        HMSLog.i("UpdateDownload", "Enter downloadPackage.");
        RandomFileOutputStream randomFileOutputStream = null;
        try {
            try {
                str = updateInfo.mPackageName;
            } catch (IOException e) {
                HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Failed to download." + e.getMessage());
                a(2201, 0, 0);
            }
            if (TextUtils.isEmpty(str)) {
                HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Download the package,  packageName is null: ");
                a(2201, 0, 0);
                return;
            }
            this.c.load(getContext(), str);
            if (this.c.isValid(updateInfo.mUri, updateInfo.mSize, updateInfo.mHash)) {
                if (this.c.a() == this.c.getSize()) {
                    if (FileUtil.verifyHash(updateInfo.mHash, this.e)) {
                        a(2000, 0, 0);
                        return;
                    } else {
                        this.c.init(updateInfo.mUri, updateInfo.mSize, updateInfo.mHash);
                        randomFileOutputStream = a(this.e, updateInfo.mSize, str);
                    }
                } else {
                    randomFileOutputStream = a(this.e, updateInfo.mSize, str);
                    randomFileOutputStream.seek(this.c.a());
                }
            } else {
                this.c.init(updateInfo.mUri, updateInfo.mSize, updateInfo.mHash);
                randomFileOutputStream = a(this.e, updateInfo.mSize, str);
            }
            int i = this.b.get(updateInfo.mUri, randomFileOutputStream, this.c.a(), this.c.getSize(), this.f6048a);
            if (i != 200 && i != 206) {
                HMSLog.e("UpdateDownload", "In DownloadHelper.downloadPackage, Download the package, HTTP code: " + i);
                a(2201, 0, 0);
                return;
            }
            if (!FileUtil.verifyHash(updateInfo.mHash, this.e)) {
                a(2202, 0, 0);
            } else {
                a(2000, 0, 0);
            }
        } finally {
            this.b.close();
            IOUtils.closeQuietly((OutputStream) null);
        }
    }

    private RandomFileOutputStream a(File file, final int i, final String str) throws IOException {
        return new RandomFileOutputStream(file, i) { // from class: com.huawei.hms.update.download.UpdateDownload.1
            private long b = 0;
            private int c;

            {
                this.c = UpdateDownload.this.c.a();
            }

            private void a(int i2) {
                UpdateDownload.this.c.update(UpdateDownload.this.getContext(), i2, str);
                UpdateDownload.this.a(2100, i2, i);
            }

            @Override // com.huawei.hms.update.download.RandomFileOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) throws IOException {
                super.write(bArr, i2, i3);
                int i4 = this.c + i3;
                this.c = i4;
                if (i4 > 209715200) {
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (Math.abs(currentTimeMillis - this.b) > 1000) {
                    this.b = currentTimeMillis;
                    a(this.c);
                }
                int i5 = this.c;
                if (i5 == i) {
                    a(i5);
                }
            }
        };
    }
}
