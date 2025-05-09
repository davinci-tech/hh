package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback;
import com.huawei.health.h5pro.jsbridge.ErrorEnum;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.operation.utils.Constants;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class AlbumChooserOperate extends MediaOperate {
    public boolean c;
    public OnAlbumChooserCallback e;

    public interface OnAlbumChooserCallback {
        void onFailure(int i, String str);

        void onSuccess(Uri... uriArr);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 10003) {
            if (PermissionUtil.getInstance().isAllPermitted(iArr)) {
                a();
            } else {
                e();
            }
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri[] uriArr;
        super.onActivityResult(i, i2, intent);
        if (i != 10003) {
            return;
        }
        if (i2 != -1 || intent == null) {
            String[] strArr = new String[1];
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i2);
            objArr[1] = Boolean.valueOf(intent == null);
            strArr[0] = String.format(locale, "onActivityResult: album selection failed -> %d - %b", objArr);
            LogUtil.w("H5PRO_MediaOperate", strArr);
            OnAlbumChooserCallback onAlbumChooserCallback = this.e;
            if (onAlbumChooserCallback != null) {
                onAlbumChooserCallback.onFailure(-1, String.format(Locale.ENGLISH, "album selection failed, resultCode is %d", Integer.valueOf(i2)));
                return;
            }
            return;
        }
        ClipData clipData = intent.getClipData();
        if (clipData == null || clipData.getItemCount() <= 0) {
            Uri data = intent.getData();
            uriArr = data != null ? new Uri[]{data} : null;
        } else {
            uriArr = new Uri[clipData.getItemCount()];
            for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                uriArr[i3] = clipData.getItemAt(i3).getUri();
            }
        }
        if (uriArr == null) {
            LogUtil.w("H5PRO_MediaOperate", "onActivityResult: album selection failed -> uris is null");
            OnAlbumChooserCallback onAlbumChooserCallback2 = this.e;
            if (onAlbumChooserCallback2 != null) {
                onAlbumChooserCallback2.onFailure(-1, "album selection failed");
                return;
            }
            return;
        }
        OnAlbumChooserCallback onAlbumChooserCallback3 = this.e;
        if (onAlbumChooserCallback3 != null) {
            onAlbumChooserCallback3.onSuccess(uriArr);
        } else {
            LogUtil.w("H5PRO_MediaOperate", "onActivityResult: album selection failed -> callback is null");
        }
    }

    public void albumChooser(boolean z, OnAlbumChooserCallback onAlbumChooserCallback) {
        this.c = z;
        this.e = onAlbumChooserCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            a();
        } else {
            PermissionUtil.getInstance().checkAndRequestPermissions(this.d, PermissionUtil.e, 10003, new H5ProPermissionCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.AlbumChooserOperate.1
                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onGranted() {
                    AlbumChooserOperate.this.a();
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onForeverDenied(String[] strArr) {
                    AlbumChooserOperate.this.e();
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onDenied(String str) {
                    AlbumChooserOperate.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_TYPE);
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.c);
            this.d.startActivityForResult(intent, 10003);
        } catch (ActivityNotFoundException e) {
            LogUtil.w("H5PRO_MediaOperate", "openAlbum: exception -> " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        OnAlbumChooserCallback onAlbumChooserCallback = this.e;
        if (onAlbumChooserCallback == null) {
            LogUtil.w("H5PRO_MediaOperate", "noPermissionOpenCallback: mOnAlbumChooserCallback is null");
        } else {
            onAlbumChooserCallback.onFailure(ErrorEnum.NO_PERMISSION.getCode(), "no permission to open album");
        }
    }

    public AlbumChooserOperate(Activity activity) {
        super(activity);
    }
}
