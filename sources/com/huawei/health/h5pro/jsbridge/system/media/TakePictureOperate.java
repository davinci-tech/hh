package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback;
import com.huawei.health.h5pro.jsbridge.ErrorEnum;
import com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils;
import com.huawei.health.h5pro.jsbridge.system.media.H5ProMediaScanner;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.io.File;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class TakePictureOperate extends MediaOperate {

    /* renamed from: a, reason: collision with root package name */
    public H5ProMediaScanner f2409a;
    public long b;
    public OnTakePictureCallback c;
    public final H5ProInstance e;
    public File h;
    public String j;

    public interface OnTakePictureCallback {
        void onFailure(int i, String str);

        void onSuccess(String str);
    }

    public void takePicture(String str, long j, OnTakePictureCallback onTakePictureCallback) {
        this.j = str;
        if (j == 0) {
            j = 1024000;
        }
        this.b = j;
        this.c = onTakePictureCallback;
        a();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 10001) {
            if (PermissionUtil.getInstance().isAllPermitted(iArr)) {
                e();
            } else {
                d();
            }
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10001) {
            return;
        }
        if (i2 != -1) {
            LogUtil.i("H5PRO_MediaOperate", "onActivityResult: Failed to take photos -> " + i2);
            File file = this.h;
            if (file != null) {
                deleteFile(file);
                b(this.h);
            }
            this.c.onFailure(-1, "failed to take the picture, resultCode is " + i2);
            return;
        }
        File file2 = this.h;
        if (file2 == null) {
            this.c.onFailure(-1, "failed to take the picture, file is null");
            return;
        }
        if (!file2.exists()) {
            this.c.onFailure(-1, "failed to take the picture, file does not exist");
            return;
        }
        if (this.b >= 0) {
            long length = this.h.length();
            long j = this.b;
            if (length > j) {
                BitmapUtils.compress(this.h, j, new BitmapUtils.OnCompressPictureCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.2
                    @Override // com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils.OnCompressPictureCallback
                    public void onSuccess(File file3, String str) {
                        TakePictureOperate.this.b(file3);
                        TakePictureOperate.this.e(file3);
                    }

                    @Override // com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils.OnCompressPictureCallback
                    public void onFailure(String str) {
                        TakePictureOperate.this.c.onFailure(-1, str);
                    }
                });
                return;
            }
        }
        b(this.h);
        e(this.h);
    }

    public void cleanup() {
        H5ProMediaScanner h5ProMediaScanner = this.f2409a;
        if (h5ProMediaScanner != null) {
            h5ProMediaScanner.disconnect();
            this.f2409a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.c.onFailure(ErrorEnum.NO_PERMISSION_SPECIAL.getCode(), ErrorEnum.NO_PERMISSION_SPECIAL.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(File file) {
        if (file == null) {
            return;
        }
        if (this.f2409a == null) {
            this.f2409a = new H5ProMediaScanner();
        }
        this.f2409a.scanFile(this.d, file.getAbsolutePath(), new String[0], new H5ProMediaScanner.OnScanFileCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.3
            @Override // com.huawei.health.h5pro.jsbridge.system.media.H5ProMediaScanner.OnScanFileCallback
            public void onScanFailure(String str) {
                LogUtil.i("H5PRO_MediaOperate", "refreshMedia: onScanFailure");
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str, Uri uri) {
                LogUtil.i("H5PRO_MediaOperate", "refreshMedia: onScanCompleted");
                LogUtil.d("H5PRO_MediaOperate", "refreshMedia: onScanCompleted - path -> " + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(File file) {
        String absolutePath = file.getAbsolutePath();
        if ("BASE64".equalsIgnoreCase(this.j)) {
            absolutePath = MediaUtils.fileToBase64(absolutePath);
        }
        if (TextUtils.isEmpty(absolutePath)) {
            this.c.onFailure(-1, "failed to encrypt the file");
        } else {
            this.c.onSuccess(absolutePath);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        File mediaStorageDir = getMediaStorageDir(CommonUtil.getAppId(this.e), "takePicture");
        if (mediaStorageDir == null || !(mediaStorageDir.exists() || StorageUtil.ensureDirExists(mediaStorageDir))) {
            this.c.onFailure(-1, "Failed to create the folder");
            return;
        }
        this.h = new File(mediaStorageDir, String.format(Locale.ENGLISH, "%s.jpg", Long.valueOf(System.currentTimeMillis())));
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", StorageUtil.getUriFromFile(this.d, this.h));
            intent.addFlags(3);
            this.d.startActivityForResult(intent, 10001);
        } catch (ActivityNotFoundException e) {
            LogUtil.w("H5PRO_MediaOperate", "doTakePicture: exception -> " + e.getMessage());
        }
    }

    private void a() {
        PermissionUtil.getInstance().checkAndRequestPermissions(this.d, PermissionUtil.c, 10001, new H5ProPermissionCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.1
            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onGranted() {
                TakePictureOperate.this.e();
            }

            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onForeverDenied(String[] strArr) {
                TakePictureOperate.this.d();
            }

            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onDenied(String str) {
                TakePictureOperate.this.d();
            }
        });
    }

    public TakePictureOperate(Activity activity, H5ProInstance h5ProInstance) {
        super(activity);
        this.b = 1024000L;
        this.e = h5ProInstance;
    }
}
