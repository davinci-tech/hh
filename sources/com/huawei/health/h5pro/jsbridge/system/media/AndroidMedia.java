package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.huawei.health.h5pro.core.H5ProWebViewActivity;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback;
import com.huawei.health.h5pro.jsbridge.ErrorEnum;
import com.huawei.health.h5pro.jsbridge.system.storage.FileMeta;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.huawei.openalliance.ad.constant.IntentFailError;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AndroidMedia implements Media {

    /* renamed from: a, reason: collision with root package name */
    public Context f2389a;
    public com.huawei.health.h5pro.jsbridge.Callback b;
    public Callback c;
    public String d;
    public boolean e = false;

    public interface Callback {
        void onFailure(String str);

        void onSuccess(Object obj);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void uploadPicture(String str) {
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void scanQrCode(Callback callback) {
        if (callback == null) {
            return;
        }
        b(callback, PermissionUtil.c);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 86) {
            if (PermissionUtil.getInstance().isAllPermitted(iArr)) {
                d();
                return;
            } else {
                this.e = false;
                d(IntentFailError.NO_PERMISSION);
                return;
            }
        }
        if (i == 12) {
            if (PermissionUtil.getInstance().isAllPermitted(iArr)) {
                e((H5ProWebViewActivity) this.f2389a);
                return;
            }
            com.huawei.health.h5pro.jsbridge.Callback callback = this.b;
            if (callback != null) {
                callback.onFailure(ErrorEnum.NO_PERMISSION);
            }
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void onMount(Context context) {
        this.f2389a = context;
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.i("H5PRO_AndroidMedia", "requestCode=" + i + " resultCode=" + i2 + " data=" + intent);
        if (i != 120) {
            if (i == 12) {
                Xy_(i2, intent);
                return;
            }
            return;
        }
        this.e = false;
        if (intent == null) {
            d("scanner data is null");
            return;
        }
        if (intent.getIntExtra(ScanUtil.RESULT_CODE, 0) == 2) {
            b(this.c, PermissionUtil.e);
            return;
        }
        HmsScan hmsScan = (HmsScan) intent.getParcelableExtra(ScanUtil.RESULT);
        if (hmsScan == null) {
            d("scanner result is null");
        } else {
            b(hmsScan.getScanType(), hmsScan.getOriginalValue());
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void destroy() {
        if (this.f2389a == null) {
            return;
        }
        this.f2389a = null;
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.media.Media
    public void chooseFile(String str, com.huawei.health.h5pro.jsbridge.Callback callback) {
        if (callback == null) {
            LogUtil.w("H5PRO_AndroidMedia", "chooseFile: chooseFileCallback is null");
            return;
        }
        this.b = callback;
        this.d = str;
        Context context = this.f2389a;
        if (context == null) {
            LogUtil.e("H5PRO_AndroidMedia", "chooseFile: mContext is null");
            callback.onFailure(ErrorEnum.DEFAULT_ERROR.getCode(), "chooseFile: mContext is null");
        } else {
            final H5ProWebViewActivity h5ProWebViewActivity = (H5ProWebViewActivity) context;
            PermissionUtil.getInstance().checkAndRequestPermissions(h5ProWebViewActivity, PermissionUtil.e, 12, new H5ProPermissionCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.AndroidMedia.2
                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onGranted() {
                    AndroidMedia.this.e(h5ProWebViewActivity);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onForeverDenied(String[] strArr) {
                    if (AndroidMedia.this.b != null) {
                        AndroidMedia.this.b.onFailure(ErrorEnum.NO_PERMISSION);
                    }
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onDenied(String str2) {
                    if (AndroidMedia.this.b != null) {
                        AndroidMedia.this.b.onFailure(ErrorEnum.NO_PERMISSION);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!(this.f2389a instanceof Activity)) {
            LogUtil.w("H5PRO_AndroidMedia", "startScan: Illegal mH5proContext");
            return;
        }
        try {
            ScanUtil.startScan((Activity) this.f2389a, 120, new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE).setErrorCheck(true).setShowGuide(true).create());
        } catch (NullPointerException e) {
            LogUtil.e("H5PRO_AndroidMedia", "startScan: exception -> " + e.getMessage());
        }
    }

    private void b(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", i);
            jSONObject.put("qrcode", str);
            Callback callback = this.c;
            if (callback == null) {
                LogUtil.w("H5PRO_AndroidMedia", "scanSuccessCallback: mScanCallback is null");
            } else {
                callback.onSuccess(jSONObject);
                this.c = null;
            }
        } catch (JSONException unused) {
            d("build result json exception");
        }
    }

    private void d(String str) {
        Callback callback = this.c;
        if (callback == null) {
            LogUtil.w("H5PRO_AndroidMedia", "scanFailCallback: mScanCallback is null");
        } else {
            callback.onFailure(str);
            this.c = null;
        }
    }

    private void b(Callback callback, final String[] strArr) {
        if (this.e) {
            return;
        }
        synchronized (AndroidMedia.class) {
            this.c = callback;
            boolean z = true;
            this.e = true;
            if (strArr == PermissionUtil.c) {
                z = false;
            }
            PermissionUtil.getInstance().checkAndRequestPermissions((Activity) this.f2389a, strArr, 86, z, new H5ProPermissionCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.media.AndroidMedia.1
                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onGranted() {
                    AndroidMedia.this.d();
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onForeverDenied(String[] strArr2) {
                    AndroidMedia.this.e(strArr2);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onDenied(String str) {
                    AndroidMedia.this.e(strArr);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String[] strArr) {
        if (strArr == PermissionUtil.c) {
            d();
        } else {
            this.e = false;
            d(IntentFailError.NO_PERMISSION);
        }
    }

    private void Xy_(int i, Intent intent) {
        com.huawei.health.h5pro.jsbridge.Callback callback = this.b;
        if (callback == null) {
            LogUtil.w("H5PRO_AndroidMedia", "chooseFileCallback: mChooseFileCallback is null");
            return;
        }
        if (i == 0) {
            callback.onFailure(ErrorEnum.CANCELED);
            return;
        }
        if (i != -1 || intent == null) {
            callback.onFailure("choose file fail");
            return;
        }
        try {
            Uri data = intent.getData();
            if (data != null) {
                FileMeta parseUriToFileMeta = StorageUtil.parseUriToFileMeta(data, this.f2389a);
                if (!parseUriToFileMeta.isExist()) {
                    LogUtil.e("H5PRO_AndroidMedia", "chooseFileCallback: chosen file not exit. ");
                    this.b.onFailure("chosen file not exit. ");
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("uri", parseUriToFileMeta.getAbsolutePath());
                jSONObject.put(ContentResource.FILE_NAME, parseUriToFileMeta.getName());
                jSONObject.put("size", parseUriToFileMeta.getSize());
                jSONObject.put("dateAdded", parseUriToFileMeta.getDateAdded());
                jSONObject.put("mimeType", parseUriToFileMeta.getMimeType());
                this.b.onSuccess(jSONObject);
            } else {
                this.b.onFailure("get file path fail");
            }
        } catch (JSONException e) {
            LogUtil.e("H5PRO_AndroidMedia", "chooseFileCallback: " + e.getMessage());
            this.b.onFailure(e.getMessage());
        }
        this.b = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(H5ProWebViewActivity h5ProWebViewActivity) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(this.d);
        h5ProWebViewActivity.startActivityForResult(intent, 12);
    }
}
