package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.Callback;
import com.huawei.health.h5pro.jsbridge.ErrorEnum;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.media.AndroidMedia;
import com.huawei.health.h5pro.jsbridge.system.media.H5ProMediaScanner;
import com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate;
import com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class MediaEntry extends JsBaseModule {

    /* renamed from: a, reason: collision with root package name */
    public PictureChooserOperate f2394a;
    public Media c;
    public PublicFileOperate e;
    public TakePictureOperate i;
    public final Set<Long> d = new HashSet();
    public long b = 0;

    @JavascriptInterface
    public void uploadPicture(final long j, String str) {
        try {
            this.c.uploadPicture(new JSONObject(str).optString("img"));
            new Timer().schedule(new TimerTask() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    MediaEntry.this.onSuccessCallback(j, "upload success");
                    cancel();
                }
            }, 1000L, 1000L);
        } catch (JSONException e) {
            onFailureCallback(j, "upload picture fail:" + e.getMessage());
        }
    }

    @JavascriptInterface
    public void takePicture(final long j, String str) {
        LogUtil.i(this.TAG, "takePicture");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultType");
            long optLong = jSONObject.optLong("maxSize");
            if (this.i == null) {
                this.i = new TakePictureOperate((Activity) this.mContext, this.mH5ProInstance);
            }
            this.i.takePicture(optString, optLong, new TakePictureOperate.OnTakePictureCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.6
                @Override // com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.OnTakePictureCallback
                public void onSuccess(String str2) {
                    LogUtil.d(MediaEntry.this.TAG, "takePicture: onSuccess -> " + str2);
                    MediaEntry.this.i = null;
                    MediaEntry.this.onSuccessCallback(j, str2);
                }

                @Override // com.huawei.health.h5pro.jsbridge.system.media.TakePictureOperate.OnTakePictureCallback
                public void onFailure(int i, String str2) {
                    LogUtil.i(MediaEntry.this.TAG, "takePicture: onFailure -> " + i + " - " + str2);
                    MediaEntry.this.i = null;
                    MediaEntry.this.onFailureCallback(j, str2, i);
                }
            });
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void scanQrCode(long j) {
        Media media;
        if (!this.d.add(Long.valueOf(j)) || (media = this.c) == null) {
            return;
        }
        media.scanQrCode(new AndroidMedia.Callback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.3
            @Override // com.huawei.health.h5pro.jsbridge.system.media.AndroidMedia.Callback
            public void onSuccess(Object obj) {
                Iterator it = MediaEntry.this.d.iterator();
                while (it.hasNext()) {
                    MediaEntry.this.onSuccessCallback(((Long) it.next()).longValue(), obj);
                }
                MediaEntry.this.d.clear();
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.media.AndroidMedia.Callback
            public void onFailure(String str) {
                Iterator it = MediaEntry.this.d.iterator();
                while (it.hasNext()) {
                    MediaEntry.this.onFailureCallback(((Long) it.next()).longValue(), str, ErrorCodeUtil.getErrorCode(str));
                }
                MediaEntry.this.d.clear();
            }
        });
    }

    @JavascriptInterface
    public void scanPublicFile(final long j, String str) {
        LogUtil.i(this.TAG, "scanPublicFile");
        try {
            String optString = new JSONObject(str).optString("mimeType");
            if (this.e == null) {
                this.e = new PublicFileOperate((Activity) this.mContext);
            }
            this.e.scanFile(Environment.getExternalStorageDirectory().getAbsolutePath(), TextUtils.isEmpty(optString) ? new String[]{"*/*"} : optString.split(","), new H5ProMediaScanner.OnScanFileCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.4
                @Override // com.huawei.health.h5pro.jsbridge.system.media.H5ProMediaScanner.OnScanFileCallback
                public void onScanFailure(String str2) {
                    LogUtil.i(MediaEntry.this.TAG, "scanPublicFile: onScanFailure");
                    MediaEntry.this.onFailureCallback(j, str2);
                }

                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str2, Uri uri) {
                    LogUtil.i(MediaEntry.this.TAG, "scanPublicFile: onScanCompleted");
                    MediaEntry.this.onSuccessCallback(j, Boolean.TRUE);
                }
            });
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void pictureChooser(final long j, String str) {
        LogUtil.i(this.TAG, "pictureChooser");
        if (EnvironmentHelper.getInstance().isMobileAppEngine()) {
            Toast.makeText(this.mContext, R.string._2130848996_res_0x7f022ce4, 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("cropType");
            boolean optBoolean = jSONObject.optBoolean("isNeedInfo", false);
            long optLong = jSONObject.optLong("maxSize", -1L);
            if (this.f2394a == null) {
                this.f2394a = new PictureChooserOperate((Activity) this.mContext, this.mH5ProInstance);
            }
            this.f2394a.pictureChooser(optString, optString2, optLong, optBoolean, new Callback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.1
                @Override // com.huawei.health.h5pro.jsbridge.Callback
                public void onSuccess(Object obj) {
                    MediaEntry.this.f2394a = null;
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("imgData", obj);
                        MediaEntry.this.onSuccessCallback(j, jSONObject2);
                    } catch (JSONException e) {
                        MediaEntry.this.onFailureCallback(j, e.getMessage());
                    }
                }

                @Override // com.huawei.health.h5pro.jsbridge.Callback
                public void onFailure(int i, String str2) {
                    MediaEntry.this.f2394a = null;
                    MediaEntry.this.onFailureCallback(j, str2, i);
                }
            });
        } catch (JSONException e) {
            this.f2394a = null;
            onFailureCallback(j, e.getMessage());
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PictureChooserOperate pictureChooserOperate = this.f2394a;
        if (pictureChooserOperate != null) {
            pictureChooserOperate.onRequestPermissionsResult(i, strArr, iArr);
            return;
        }
        PublicFileOperate publicFileOperate = this.e;
        if (publicFileOperate != null) {
            publicFileOperate.onRequestPermissionsResult(i, strArr, iArr);
            this.e = null;
        }
        TakePictureOperate takePictureOperate = this.i;
        if (takePictureOperate != null) {
            takePictureOperate.onRequestPermissionsResult(i, strArr, iArr);
            return;
        }
        Media media = this.c;
        if (media != null) {
            media.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        Media media = this.c;
        if (media != null) {
            media.destroy();
            this.c = null;
        }
        TakePictureOperate takePictureOperate = this.i;
        if (takePictureOperate != null) {
            takePictureOperate.cleanup();
            this.i = null;
        }
        PublicFileOperate publicFileOperate = this.e;
        if (publicFileOperate != null) {
            publicFileOperate.cleanup();
        }
        this.f2394a = null;
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        if (this.c == null) {
            this.c = new AndroidMedia();
        }
        this.c.onMount(this.mContext);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        TakePictureOperate takePictureOperate = this.i;
        if (takePictureOperate != null) {
            takePictureOperate.onActivityResult(i, i2, intent);
            this.i = null;
            return;
        }
        PictureChooserOperate pictureChooserOperate = this.f2394a;
        if (pictureChooserOperate != null) {
            pictureChooserOperate.onActivityResult(i, i2, intent);
            return;
        }
        Media media = this.c;
        if (media != null) {
            media.onActivityResult(i, i2, intent);
        }
    }

    @JavascriptInterface
    public void getPublicFiles(final long j, String str) {
        LogUtil.i(this.TAG, "getPublicFiles");
        if (!H5ProTrustListChecker.isTrusted(this.mH5ProInstance)) {
            LogUtil.w(this.TAG, "getPublicFiles: untrusted");
            onFailureCallback(j, "untrusted");
            return;
        }
        PublicFileOperate.FileQueryObj fileQueryObj = (PublicFileOperate.FileQueryObj) GsonUtil.parseJson(str, PublicFileOperate.FileQueryObj.class);
        if (fileQueryObj == null) {
            onFailureCallback(j, "invalid parameter");
            return;
        }
        if (this.e == null) {
            this.e = new PublicFileOperate((Activity) this.mContext);
        }
        this.e.queryFiles(fileQueryObj, new PublicFileOperate.OnQueryFileCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.5
            @Override // com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.OnQueryFileCallback
            public void onQuerySuccess(List<JSONObject> list) {
                LogUtil.i(MediaEntry.this.TAG, "getPublicFiles: size -> " + list.size());
                MediaEntry.this.onSuccessCallback(j, list);
            }

            @Override // com.huawei.health.h5pro.jsbridge.system.media.PublicFileOperate.OnQueryFileCallback
            public void onQueryFailure(int i, String str2) {
                LogUtil.i(MediaEntry.this.TAG, "getPublicFiles: onQueryFailure -> " + i + " - " + str2);
                MediaEntry.this.onFailureCallback(j, str2, i);
            }
        });
    }

    @JavascriptInterface
    public void chooseFile(final long j, String str) {
        LogUtil.i(this.TAG, "chooseFile: " + j);
        if (!H5ProTrustListChecker.isTrusted(this.mH5ProInstance)) {
            LogUtil.w(this.TAG, "chooseFile: untrusted");
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.b < 200) {
                LogUtil.i(this.TAG, "chooseFile: fail-> " + ErrorEnum.REQ_FAST_REPEAT.getMsg());
                onFailureCallback(j, "chooseFile fail: " + ErrorEnum.REQ_FAST_REPEAT.getMsg(), ErrorEnum.REQ_FAST_REPEAT.getCode());
                this.b = currentTimeMillis;
                return;
            }
            this.b = currentTimeMillis;
            String optString = new JSONObject(str).optString("mimeType");
            if (TextUtils.isEmpty(optString)) {
                optString = "*/*";
            }
            Media media = this.c;
            if (media != null) {
                media.chooseFile(optString, new Callback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.MediaEntry.7
                    @Override // com.huawei.health.h5pro.jsbridge.Callback
                    public void onSuccess(Object obj) {
                        LogUtil.i(MediaEntry.this.TAG, "chooseFile: onSuccess-> " + obj);
                        MediaEntry.this.onSuccessCallback(j, obj);
                    }

                    @Override // com.huawei.health.h5pro.jsbridge.Callback
                    public void onFailure(int i, String str2) {
                        LogUtil.i(MediaEntry.this.TAG, "chooseFile: onFailure-> " + str2);
                        MediaEntry.this.onFailureCallback(j, "chooseFile fail: " + str2, i);
                    }
                });
            }
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "chooseFile: catch-> " + e.getMessage());
            onFailureCallback(j, "chooseFile fail:" + e.getMessage());
        }
    }
}
