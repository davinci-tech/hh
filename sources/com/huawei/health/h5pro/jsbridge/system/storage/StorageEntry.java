package com.huawei.health.h5pro.jsbridge.system.storage;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class StorageEntry extends JsBaseModule {
    public Storage d;

    @JavascriptInterface
    public void writeText(long j, String str) {
        LogUtil.i(this.TAG, "writeText");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("uri");
            String optString2 = jSONObject.optString(Constant.TEXT);
            boolean optBoolean = jSONObject.optBoolean("isAppend");
            this.d.writeText(StorageUtil.webAppUriToNativePath(this.mContext, a(), optString), optString2, optBoolean);
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void writeArrayBuffer(long j, String str) {
        LogUtil.i(this.TAG, "writeArrayBuffer");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("uri");
            String optString2 = jSONObject.optString("buffer");
            this.d.writeArrayBuffer(StorageUtil.webAppUriToNativePath(this.mContext, a(), optString), optString2);
            onSuccessCallback(j, str);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void setValueByKey(long j, String str) {
        LogUtil.i(this.TAG, "setValueByKey");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.d.setValueByKey(a(), jSONObject.optString(MedalConstants.EVENT_KEY), jSONObject.optString("value"));
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void saveImageToAlbum(final long j, String str) {
        LogUtil.i(this.TAG, "saveImageToAlbum");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "param is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("resource");
            String optString3 = jSONObject.optString("name");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                onFailureCallback(j, "type or content is null");
            } else {
                this.d.saveImageToAlbum(optString, optString2, optString3, new AndroidStorage.Callback() { // from class: com.huawei.health.h5pro.jsbridge.system.storage.StorageEntry.2
                    @Override // com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage.Callback
                    public <T> void onSuccess(T t) {
                        LogUtil.i(StorageEntry.this.TAG, "saveImageToAlbum: onSuccess");
                        StorageEntry.this.onSuccessCallback(j);
                    }

                    @Override // com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage.Callback
                    public void onFailure(String str2) {
                        LogUtil.e(StorageEntry.this.TAG, "saveImageToAlbum: onFailure-> " + str2);
                        StorageEntry.this.onFailureCallback(j, str2);
                    }
                });
            }
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "saveImageToAlbum: JSONException");
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void saveFile(long j, String str) {
        H5ProInstance h5ProInstance;
        if (this.mContext == null || (h5ProInstance = this.mH5ProInstance) == null) {
            LogUtil.w(this.TAG, "saveFile: context or instance is null");
        } else if (H5ProTrustListChecker.isTrusted(h5ProInstance)) {
            new FileOperateApi(this.mContext, this.mH5ProInstance).saveFile(j, str);
        } else {
            LogUtil.w(this.TAG, "saveFile: untrusted");
        }
    }

    @JavascriptInterface
    public void rmdir(long j, String str) {
        LogUtil.i(this.TAG, "rmdir");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            this.d.rmdir(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri")));
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void readText(long j, String str) {
        LogUtil.i(this.TAG, "readText");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            onSuccessCallback(j, this.d.readText(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri"))));
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void readArrayBuffer(long j, String str) {
        LogUtil.i(this.TAG, "readArrayBuffer");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            onSuccessCallback(j, this.d.readArrayBuffer(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri"))));
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.d.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.d = new AndroidStorage(this.mContext);
    }

    @JavascriptInterface
    public void move(long j, String str) {
        LogUtil.i(this.TAG, "move");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("srcUri");
            String optString2 = jSONObject.optString("dstUri");
            this.d.move(StorageUtil.webAppUriToNativePath(this.mContext, a(), optString), StorageUtil.webAppUriToNativePath(this.mContext, a(), optString2));
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void mkdir(long j, String str) {
        LogUtil.i(this.TAG, "mkdir");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            this.d.mkdir(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri")));
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void list(long j, String str) {
        LogUtil.i(this.TAG, "list");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            onSuccessCallback(j, new JSONArray(this.d.list(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri")))).toString());
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void getValueByKey(long j, String str) {
        LogUtil.i(this.TAG, "getValueByKey");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            onSuccessCallback(j, this.d.getValueByKey(a(), new JSONObject(str).optString(MedalConstants.EVENT_KEY)));
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void getFile(long j, String str) {
        H5ProInstance h5ProInstance;
        if (this.mContext == null || (h5ProInstance = this.mH5ProInstance) == null) {
            LogUtil.w(this.TAG, "getFile: context or instance is null");
        } else if (H5ProTrustListChecker.isTrusted(h5ProInstance)) {
            new FileOperateApi(this.mContext, this.mH5ProInstance).getFile(j, str);
        } else {
            LogUtil.w(this.TAG, "getFile: untrusted");
        }
    }

    @JavascriptInterface
    public void get(long j, String str) {
        LogUtil.i(this.TAG, "get");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            onSuccessCallback(j, this.d.get(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri"))));
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void delete(long j, String str) {
        LogUtil.i(this.TAG, "delete");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            this.d.delete(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri")));
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void copy(long j, String str) {
        LogUtil.i(this.TAG, "copy");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("srcUri");
            String optString2 = jSONObject.optString("dstUri");
            this.d.copy(StorageUtil.webAppUriToNativePath(this.mContext, a(), optString), StorageUtil.webAppUriToNativePath(this.mContext, a(), optString2));
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void clearKeyValues(long j) {
        LogUtil.i(this.TAG, "clearKeyValues");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            this.d.clearKeyValues(a());
            onSuccessCallback(j);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void base64ToImageFile(final long j, String str) {
        LogUtil.i(this.TAG, "base64ToImageFile");
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString("uri");
            String optString2 = jSONObject.optString("buffer");
            String webAppUriToNativePath = StorageUtil.webAppUriToNativePath(this.mContext, a(), optString);
            if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(webAppUriToNativePath)) {
                this.d.base64ToImageFile(webAppUriToNativePath, optString2, new AndroidStorage.Callback() { // from class: com.huawei.health.h5pro.jsbridge.system.storage.StorageEntry.1
                    @Override // com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage.Callback
                    public <T> void onSuccess(T t) {
                        StorageEntry.this.onSuccessCallback(j, optString);
                    }

                    @Override // com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage.Callback
                    public void onFailure(String str2) {
                        StorageEntry.this.onFailureCallback(j, str2);
                    }
                });
                return;
            }
            onFailureCallback(j, "path or buffer is null");
        } catch (IOException | IllegalArgumentException | JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void base64ToFile(long j, String str) {
        LogUtil.i(this.TAG, "base64ToFile");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("uri");
            String optString2 = jSONObject.optString("data");
            this.d.base64ToFile(StorageUtil.webAppUriToNativePath(this.mContext, a(), optString), optString2);
            onSuccessCallback(j, optString);
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void access(long j, String str) {
        LogUtil.i(this.TAG, "access");
        if (e()) {
            onFailureCallback(j, "untrusted");
            return;
        }
        try {
            onSuccessCallback(j, Boolean.valueOf(this.d.access(StorageUtil.webAppUriToNativePath(this.mContext, a(), new JSONObject(str).optString("uri")))));
        } catch (Exception e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    private boolean e() {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        H5ProAppInfo appInfo = h5ProInstance == null ? null : h5ProInstance.getAppInfo();
        if (appInfo == null) {
            LogUtil.w(this.TAG, "isInterceptStorage: h5ProAppInfo is null");
            return true;
        }
        if (appInfo.getH5ProAppType() == H5ProAppInfo.H5ProAppType.H5_MINI_PROGRAM) {
            return false;
        }
        LogUtil.w(this.TAG, "isInterceptStorage: isH5MiniProgram -> false");
        return true;
    }

    private String a() {
        return CommonUtil.getAppId(this.mH5ProInstance);
    }
}
