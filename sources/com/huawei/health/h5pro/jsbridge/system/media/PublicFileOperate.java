package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Size;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback;
import com.huawei.health.h5pro.jsbridge.ErrorEnum;
import com.huawei.health.h5pro.jsbridge.system.media.H5ProMediaScanner;
import com.huawei.health.h5pro.jsbridge.system.storage.FileMeta;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class PublicFileOperate extends MediaOperate {

    /* renamed from: a, reason: collision with root package name */
    public FileQueryObj f2405a;
    public OnQueryFileCallback b;
    public String[] c;
    public H5ProMediaScanner e;

    public static class FileQueryObj {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("resultType")
        public String f2408a;

        @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
        public int b;

        @SerializedName("mimeType")
        public String c;

        @SerializedName("pageIndex")
        public int e;
    }

    public interface OnQueryFileCallback {
        void onQueryFailure(int i, String str);

        void onQuerySuccess(List<JSONObject> list);
    }

    public void scanFile(String str, String[] strArr, H5ProMediaScanner.OnScanFileCallback onScanFileCallback) {
        if (onScanFileCallback == null) {
            LogUtil.w("H5PRO_MediaOperate", "scanPublicFile: onScannerCallback is null");
            return;
        }
        if (strArr == null || strArr.length == 0) {
            onScanFileCallback.onScanFailure("mimeTypes is empty");
            return;
        }
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            onScanFileCallback.onScanFailure("the file directory does not exist");
            return;
        }
        if (this.e == null) {
            this.e = new H5ProMediaScanner();
        }
        this.e.scanFile(this.d, str, strArr, onScanFileCallback);
    }

    public void queryFiles(FileQueryObj fileQueryObj, OnQueryFileCallback onQueryFileCallback) {
        checkPermissionAndQueryFiles(fileQueryObj, onQueryFileCallback);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.MediaOperate
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 10002) {
            if (PermissionUtil.getInstance().isAllPermitted(iArr)) {
                d(this.f2405a, this.b);
                return;
            }
            OnQueryFileCallback onQueryFileCallback = this.b;
            if (onQueryFileCallback != null) {
                onQueryFileCallback.onQueryFailure(ErrorEnum.NO_PERMISSION.getCode(), "no read/write permission on the storage card");
            }
        }
    }

    public void cleanup() {
        H5ProMediaScanner h5ProMediaScanner = this.e;
        if (h5ProMediaScanner != null) {
            h5ProMediaScanner.disconnect();
            this.e = null;
        }
    }

    public void checkPermissionAndQueryFiles(final FileQueryObj fileQueryObj, final OnQueryFileCallback onQueryFileCallback) {
        this.f2405a = fileQueryObj;
        this.b = onQueryFileCallback;
        PermissionUtil.getInstance().checkAndRequestPermissions(this.d, PermissionUtil.e, 10002, new H5ProPermissionCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.1
            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onGranted() {
                PublicFileOperate.this.d(fileQueryObj, onQueryFileCallback);
            }

            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onForeverDenied(String[] strArr) {
                onQueryFileCallback.onQueryFailure(ErrorEnum.NO_PERMISSION.getCode(), "no read/write permission on the storage card");
            }

            @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
            public void onDenied(String str) {
                onQueryFileCallback.onQueryFailure(ErrorEnum.NO_PERMISSION.getCode(), "no read/write permission on the storage card");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.FileQueryObj r17, java.lang.String[] r18, com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.OnQueryFileCallback r19) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.d(com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate$FileQueryObj, java.lang.String[], com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate$OnQueryFileCallback):void");
    }

    private void XF_(Cursor cursor, Uri uri, String str, JSONObject jSONObject, String str2) {
        long j = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
        if (TextUtils.isEmpty(str2) || !str2.toUpperCase(Locale.ENGLISH).startsWith("IMAGE/")) {
            return;
        }
        Uri withAppendedId = ContentUris.withAppendedId(uri, j);
        ContentResolver contentResolver = this.d.getContentResolver();
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT >= 30) {
            FileMeta parseUriToFileMeta = StorageUtil.parseUriToFileMeta(withAppendedId, this.d);
            if (parseUriToFileMeta.isExist()) {
                jSONObject.put("uri", parseUriToFileMeta.getAbsolutePath());
                bitmap = contentResolver.loadThumbnail(withAppendedId, new Size(GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, GlMapUtil.DEVICE_DISPLAY_DPI_HIGH), null);
            }
        } else {
            bitmap = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, j, 1, null);
        }
        jSONObject.put("thumbnail", TextUtils.concat("data:", str2, ";base64,", BitmapUtils.parseBitmapToBase64(bitmap == null ? BitmapUtils.compress(new File(str), 100000L) : BitmapUtils.compress(bitmap, 100000L))).toString());
    }

    private List<JSONObject> XE_(Cursor cursor, String str, Uri uri) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject = new JSONObject();
                    if ("BASE64".equalsIgnoreCase(str)) {
                        jSONObject.put("uri", MediaUtils.fileToBase64(string));
                    } else {
                        jSONObject.put("uri", string);
                    }
                    jSONObject.put(ContentResource.FILE_NAME, cursor.getString(cursor.getColumnIndexOrThrow("title")));
                    jSONObject.put("size", cursor.getLong(cursor.getColumnIndexOrThrow("_size")));
                    jSONObject.put("dateAdded", cursor.getLong(cursor.getColumnIndexOrThrow("date_added")) * 1000);
                    String string2 = cursor.getString(cursor.getColumnIndexOrThrow("mime_type"));
                    jSONObject.put("mimeType", string2);
                    XF_(cursor, uri, string, jSONObject, string2);
                    arrayList.add(jSONObject);
                }
            } catch (SQLiteException | IOException | IllegalArgumentException | JSONException e) {
                LogUtil.e("H5PRO_MediaOperate", "initResultData: exception -> " + e.getMessage());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final FileQueryObj fileQueryObj, final OnQueryFileCallback onQueryFileCallback) {
        if (TextUtils.isEmpty(fileQueryObj.c)) {
            onQueryFileCallback.onQueryFailure(-1, "mimeType is empty");
            return;
        }
        final String[] split = fileQueryObj.c.split(",");
        if (split.length == 0) {
            onQueryFileCallback.onQueryFailure(-1, "Invalid parameter mimeType: " + fileQueryObj.c);
        } else {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.2
                @Override // java.lang.Runnable
                public void run() {
                    PublicFileOperate.this.d(fileQueryObj, split, onQueryFileCallback);
                }
            });
            newSingleThreadExecutor.shutdown();
        }
    }

    public PublicFileOperate(Activity activity) {
        super(activity);
    }
}
