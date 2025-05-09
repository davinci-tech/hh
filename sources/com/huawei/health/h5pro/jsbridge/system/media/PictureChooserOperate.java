package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.h5pro.dialog.BaseDialog;
import com.huawei.health.h5pro.jsbridge.Callback;
import com.huawei.health.h5pro.jsbridge.system.media.AlbumChooserOperate;
import com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils;
import com.huawei.health.h5pro.jsbridge.system.media.CropPictureOperate;
import com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import java.io.File;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class PictureChooserOperate extends MediaOperate {

    /* renamed from: a, reason: collision with root package name */
    public AlbumChooserOperate f2398a;
    public TakePictureOperate b;
    public final H5ProInstance c;
    public CropPictureOperate e;

    public void pictureChooser(String str, String str2, long j, boolean z, Callback callback) {
        if (callback == null) {
            LogUtil.w("H5PRO_MediaOperate", "pictureChooser: callback is null");
            return;
        }
        if ("camera".equalsIgnoreCase(str)) {
            d(str2, j, callback);
        } else if (MusicSong.SORT_TYPE_ALBUM.equalsIgnoreCase(str) || "album-multiple".equalsIgnoreCase(str)) {
            c("album-multiple".equalsIgnoreCase(str), str2, j, z, callback);
        } else {
            c(str2, j, z, callback);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        TakePictureOperate takePictureOperate = this.b;
        if (takePictureOperate != null) {
            takePictureOperate.onRequestPermissionsResult(i, strArr, iArr);
            return;
        }
        AlbumChooserOperate albumChooserOperate = this.f2398a;
        if (albumChooserOperate != null) {
            albumChooserOperate.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        TakePictureOperate takePictureOperate = this.b;
        if (takePictureOperate != null) {
            takePictureOperate.onActivityResult(i, i2, intent);
            this.b = null;
            return;
        }
        AlbumChooserOperate albumChooserOperate = this.f2398a;
        if (albumChooserOperate != null) {
            albumChooserOperate.onActivityResult(i, i2, intent);
            this.f2398a = null;
            return;
        }
        CropPictureOperate cropPictureOperate = this.e;
        if (cropPictureOperate != null) {
            cropPictureOperate.onActivityResult(i, i2, intent);
            this.e = null;
        }
    }

    private void d(final String str, long j, final Callback callback) {
        this.f2398a = null;
        this.e = null;
        this.b = new TakePictureOperate(this.d, this.c);
        final boolean z = "none".equalsIgnoreCase(str) || TextUtils.isEmpty(str);
        if (!z) {
            j = -1;
        }
        this.b.takePicture("uri", j, new TakePictureOperate.OnTakePictureCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.4
            @Override // com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.OnTakePictureCallback
            public void onSuccess(String str2) {
                if (z) {
                    callback.onSuccess(MediaUtils.fileToBase64(str2));
                    PictureChooserOperate.this.deleteFile(str2);
                } else {
                    PictureChooserOperate.this.XC_(str, StorageUtil.getUriFromFile(PictureChooserOperate.this.d, new File(str2)), str2, false, callback);
                }
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.OnTakePictureCallback
            public void onFailure(int i, String str2) {
                LogUtil.w("H5PRO_MediaOperate", String.format(Locale.ENGLISH, "takePicture: onFailure -> %d - %s", Integer.valueOf(i), str2));
                callback.onFailure(i, str2);
            }
        });
    }

    private void c(final String str, final long j, final boolean z, final Callback callback) {
        final BaseDialog baseDialog = new BaseDialog(this.d);
        baseDialog.contentView(R.layout.dialog_photo).gravity(80).canceledOnTouchOutside(false).show();
        baseDialog.findViewById(R.id.tv_camera).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PictureChooserOperate.this.pictureChooser("camera", str, j, z, callback);
                baseDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        baseDialog.findViewById(R.id.tv_album).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PictureChooserOperate.this.pictureChooser(MusicSong.SORT_TYPE_ALBUM, str, j, z, callback);
                baseDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        baseDialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                baseDialog.dismiss();
                callback.onFailure("user cancel");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray e(JSONArray jSONArray, String str, String str2) {
        File file;
        try {
            file = new File(str);
        } catch (JSONException e) {
            LogUtil.e("H5PRO_MediaOperate", "putFileInfo: exception -> " + e.getMessage());
        }
        if (!file.exists()) {
            LogUtil.w("H5PRO_MediaOperate", "putFileInfo: not exist -> " + str);
            return jSONArray;
        }
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str2)) {
            str2 = MediaUtils.fileToBase64(str);
        }
        jSONObject.put("content", str2);
        jSONObject.put("name", file.getName());
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray b(JSONArray jSONArray, String str) {
        return e(jSONArray, str, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XD_(boolean z, Callback callback, Uri... uriArr) {
        int i = 0;
        if (z) {
            JSONArray jSONArray = new JSONArray();
            while (i < uriArr.length) {
                b(jSONArray, StorageUtil.getFilePathFromUri(this.d, uriArr[i]));
                i++;
            }
            callback.onSuccess(jSONArray);
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (i < uriArr.length) {
            if (i != 0) {
                sb.append("_");
            }
            sb.append(MediaUtils.fileToBase64(StorageUtil.getFilePathFromUri(this.d, uriArr[i])));
            i++;
        }
        callback.onSuccess(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XC_(String str, Uri uri, final String str2, final boolean z, final Callback callback) {
        this.b = null;
        this.f2398a = null;
        CropPictureOperate cropPictureOperate = new CropPictureOperate(this.d, this.c);
        this.e = cropPictureOperate;
        cropPictureOperate.cropPicture(str, uri, new CropPictureOperate.OnCropPictureCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.7
            @Override // com.huawei.health.h5pro.jsbridge.system.media.CropPictureOperate.OnCropPictureCallback
            public void onSuccess(boolean z2, String str3) {
                Callback callback2;
                String str4;
                if (!z2) {
                    PictureChooserOperate.this.deleteFile(str2);
                }
                if (TextUtils.isEmpty(str3)) {
                    LogUtil.w("H5PRO_MediaOperate", "onSuccess(crop): picturePath is empty");
                    callback2 = callback;
                    str4 = "picturePath is empty";
                } else {
                    File file = new File(str3);
                    if (file.exists()) {
                        LogUtil.i("H5PRO_MediaOperate", "onSuccess(crop): length -> " + file.length());
                        callback.onSuccess(z ? PictureChooserOperate.this.b(new JSONArray(), str3) : MediaUtils.fileToBase64(str3));
                        if (z2) {
                            PictureChooserOperate.this.deleteFile(str2);
                            return;
                        } else {
                            PictureChooserOperate.this.deleteFile(file);
                            return;
                        }
                    }
                    LogUtil.w("H5PRO_MediaOperate", "onSuccess(crop): pictureFile does not exist");
                    callback2 = callback;
                    str4 = "pictureFile does not exist";
                }
                callback2.onFailure(-1, str4);
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.media.CropPictureOperate.OnCropPictureCallback
            public void onFailure(int i, String str3) {
                LogUtil.w("H5PRO_MediaOperate", String.format(Locale.ENGLISH, "cropPicture: onFailure -> %d - %s", Integer.valueOf(i), str3));
                callback.onFailure(i, str3);
                PictureChooserOperate.this.deleteFile(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XB_(long j, final boolean z, Uri uri, final Callback callback) {
        String filePathFromUri = StorageUtil.getFilePathFromUri(this.d, uri);
        File file = new File(filePathFromUri);
        if (j > 0 && file.length() > j) {
            BitmapUtils.compress(new File(filePathFromUri), j, false, new BitmapUtils.OnCompressPictureCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.6
                @Override // com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils.OnCompressPictureCallback
                public void onSuccess(File file2, String str) {
                    if (z) {
                        callback.onSuccess(PictureChooserOperate.this.e(new JSONArray(), file2.getAbsolutePath(), str));
                    } else {
                        callback.onSuccess(str);
                    }
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils.OnCompressPictureCallback
                public void onFailure(String str) {
                    callback.onFailure(-1, str);
                }
            });
        } else if (z) {
            callback.onSuccess(b(new JSONArray(), filePathFromUri));
        } else {
            callback.onSuccess(MediaUtils.fileToBase64(filePathFromUri));
        }
    }

    private void c(final boolean z, final String str, final long j, final boolean z2, final Callback callback) {
        this.b = null;
        this.e = null;
        AlbumChooserOperate albumChooserOperate = new AlbumChooserOperate(this.d);
        this.f2398a = albumChooserOperate;
        albumChooserOperate.albumChooser(z, new AlbumChooserOperate.OnAlbumChooserCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PictureChooserOperate.5
            @Override // com.huawei.health.h5pro.jsbridge.system.media.AlbumChooserOperate.OnAlbumChooserCallback
            public void onSuccess(Uri... uriArr) {
                if (z) {
                    PictureChooserOperate.this.XD_(z2, callback, uriArr);
                } else if ("none".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                    PictureChooserOperate.this.XB_(j, z2, uriArr[0], callback);
                } else {
                    PictureChooserOperate.this.XC_(str, uriArr[0], null, z2, callback);
                }
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.media.AlbumChooserOperate.OnAlbumChooserCallback
            public void onFailure(int i, String str2) {
                LogUtil.w("H5PRO_MediaOperate", String.format(Locale.ENGLISH, "albumChooser: onFailure -> %d - %s", Integer.valueOf(i), str2));
                callback.onFailure(i, str2);
            }
        });
    }

    public PictureChooserOperate(Activity activity, H5ProInstance h5ProInstance) {
        super(activity);
        this.c = h5ProInstance;
    }
}
