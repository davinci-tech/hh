package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hms.kit.awareness.barrier.internal.d.c;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class CropPictureOperate extends MediaOperate {
    public final H5ProInstance b;
    public OnCropPictureCallback c;
    public String e;

    public interface OnCropPictureCallback {
        void onFailure(int i, String str);

        void onSuccess(boolean z, String str);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10004) {
            return;
        }
        if (i2 == -1 && intent != null) {
            OnCropPictureCallback onCropPictureCallback = this.c;
            if (onCropPictureCallback != null) {
                onCropPictureCallback.onSuccess(false, this.e);
                return;
            } else {
                LogUtil.w("H5PRO_MediaOperate", "onActivityResult: callback is null");
                return;
            }
        }
        String[] strArr = new String[1];
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i2);
        objArr[1] = Boolean.valueOf(intent == null);
        strArr[0] = String.format(locale, "onActivityResult: failed to crop the picture -> %d - %b", objArr);
        LogUtil.w("H5PRO_MediaOperate", strArr);
        OnCropPictureCallback onCropPictureCallback2 = this.c;
        if (onCropPictureCallback2 != null) {
            onCropPictureCallback2.onFailure(-1, "failed to crop the picture");
        }
    }

    public void cropPicture(String str, Uri uri, OnCropPictureCallback onCropPictureCallback) {
        if (onCropPictureCallback == null) {
            LogUtil.w("H5PRO_MediaOperate", "cropPicture: onCropPictureCallback is null");
            return;
        }
        if (uri == null) {
            LogUtil.w("H5PRO_MediaOperate", "cropPicture: pictureUri is null");
            onCropPictureCallback.onFailure(-1, "pictureUri is null");
            return;
        }
        if (!"poster".equalsIgnoreCase(str) && !"circle".equalsIgnoreCase(str) && !"square".equalsIgnoreCase(str)) {
            LogUtil.w("H5PRO_MediaOperate", "cropPicture: unknown cropping type");
            onCropPictureCallback.onSuccess(true, StorageUtil.getFilePathFromUri(this.d, uri));
            return;
        }
        try {
            File mediaStorageDir = getMediaStorageDir(CommonUtil.getAppId(this.b), "crop");
            if (mediaStorageDir != null && (mediaStorageDir.exists() || StorageUtil.ensureDirExists(mediaStorageDir))) {
                File file = new File(mediaStorageDir, String.format(Locale.ENGLISH, "%s.jpg", Long.valueOf(System.currentTimeMillis())));
                this.e = file.getCanonicalPath();
                Xz_(str, uri, file, onCropPictureCallback);
                return;
            }
            onCropPictureCallback.onFailure(-1, "Failed to create the folder");
        } catch (IOException e) {
            this.e = null;
            onCropPictureCallback.onFailure(-1, e.getMessage());
        }
    }

    private void XA_(Intent intent, Uri uri, OnCropPictureCallback onCropPictureCallback) {
        PackageManager packageManager = this.d.getPackageManager();
        if (packageManager == null) {
            return;
        }
        this.c = onCropPictureCallback;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        LogUtil.i("H5PRO_MediaOperate", "resolveInfoList -> " + queryIntentActivities.size());
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo == null) {
                LogUtil.w("H5PRO_MediaOperate", "resolveInfo is null");
            } else {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo == null || TextUtils.isEmpty(activityInfo.packageName) || TextUtils.isEmpty(activityInfo.name)) {
                    LogUtil.w("H5PRO_MediaOperate", "activityInfo, activityInfo#packageName, or activityInfo#name is null");
                } else {
                    intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
                    try {
                        if (intent.resolveActivity(packageManager) != null) {
                            this.d.grantUriPermission(activityInfo.packageName, uri, CommonUtil.isPixel() ? 3 : 2);
                            this.d.startActivityForResult(intent, 10004);
                            return;
                        }
                        LogUtil.w("H5PRO_MediaOperate", "cannot start the crop activity");
                    } catch (Exception e) {
                        LogUtil.e("H5PRO_MediaOperate", "startCropActivityForResult: exception -> ", e);
                    }
                }
            }
        }
        LogUtil.i("H5PRO_MediaOperate", "startCropActivityForResult: the image is not cropped");
        onCropPictureCallback.onSuccess(true, StorageUtil.getFilePathFromUri(this.d, intent.getData()));
        this.c = null;
    }

    private void Xz_(String str, Uri uri, File file, OnCropPictureCallback onCropPictureCallback) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        int i = 1;
        intent.addFlags(1);
        intent.setDataAndType(uri, Constants.IMAGE_TYPE);
        intent.putExtra("crop", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scale", true);
        int i2 = 1080;
        intent.putExtra("aspectX", "poster".equalsIgnoreCase(str) ? 1080 : "square".equalsIgnoreCase(str) ? 400 : 1);
        if ("poster".equalsIgnoreCase(str)) {
            i = 607;
        } else if ("square".equalsIgnoreCase(str)) {
            i = c.b;
        }
        intent.putExtra("aspectY", i);
        intent.putExtra("outputX", (!"poster".equalsIgnoreCase(str) && "square".equalsIgnoreCase(str)) ? 400 : 1080);
        if ("poster".equalsIgnoreCase(str)) {
            i2 = 607;
        } else if ("square".equalsIgnoreCase(str)) {
            i2 = 400;
        }
        intent.putExtra("outputY", i2);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("return-data", false);
        Uri uriFromFile = StorageUtil.getUriFromFile(this.d, file);
        intent.putExtra("output", uriFromFile);
        XA_(intent, uriFromFile, onCropPictureCallback);
    }

    public CropPictureOperate(Activity activity, H5ProInstance h5ProInstance) {
        super(activity);
        this.b = h5ProInstance;
    }
}
