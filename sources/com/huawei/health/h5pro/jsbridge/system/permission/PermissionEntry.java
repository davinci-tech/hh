package com.huawei.health.h5pro.jsbridge.system.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PermissionEntry extends JsBaseModule {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2417a = false;
    public long b;
    public Activity c;
    public IPermission e;

    @JavascriptInterface
    public void request(final long j, String str) {
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("permissions");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                final List<String> b = b(optJSONArray);
                IPermission iPermission = this.e;
                if (iPermission == null) {
                    LogUtil.w(this.TAG, "IPermission is null");
                    return;
                } else {
                    iPermission.requestPermissions((String[]) b.toArray(new String[0]), new AndroidPermission.PermissionCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.permission.PermissionEntry.1
                        @Override // com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission.PermissionCallback
                        public void onFailure(String str2) {
                            PermissionEntry.this.onFailureCallback(j, str2);
                        }

                        @Override // com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission.PermissionCallback
                        public void onComplete(int[] iArr) {
                            JSONArray jSONArray = new JSONArray();
                            for (int i = 0; i < iArr.length; i++) {
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("permission", b.get(i));
                                    jSONObject.put("result", iArr[i]);
                                    jSONArray.put(jSONObject);
                                } catch (JSONException e) {
                                    LogUtil.i(PermissionEntry.this.TAG, String.valueOf(e));
                                }
                            }
                            PermissionEntry.this.onSuccessCallback(j, jSONArray.toString());
                        }
                    });
                    return;
                }
            }
            onFailureCallback(j, "permission list is empty!");
        } catch (JSONException unused) {
            onFailureCallback(j, "request permission fail:param invalid");
        }
    }

    @JavascriptInterface
    public void openGpsService(final long j, String str) {
        LogUtil.i(this.TAG, "openGpsService");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.permission.PermissionEntry.2
            @Override // java.lang.Runnable
            public void run() {
                if (!PermissionEntry.this.c()) {
                    if (PermissionEntry.this.f2417a) {
                        return;
                    }
                    PermissionEntry.this.b(j);
                    return;
                }
                PermissionEntry.this.onSuccessCallback(j, "success");
            }
        });
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        IPermission iPermission = this.e;
        if (iPermission != null) {
            iPermission.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.c = null;
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        Context context = this.mContext;
        if (context instanceof Activity) {
            this.c = (Activity) context;
        }
        if (this.e == null) {
            this.e = new AndroidPermission();
        }
        this.e.onMount(this.mContext);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1360) {
            LogUtil.i(this.TAG, "Enter checkGpsPermission");
            this.f2417a = false;
            if (c()) {
                onSuccessCallback(this.b, "success");
            } else {
                onFailureCallback(this.b, "user rejected!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        if (this.mContext == null) {
            LogUtil.w(this.TAG, "showGpsDialog: mContext is null");
            return;
        }
        this.f2417a = true;
        this.b = j;
        LogUtil.i(this.TAG, "showGpsDialog() mIsGpsOpenDialog is open");
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(R.string._2130850314_res_0x7f02320a);
        builder.setMessage(R.string._2130850311_res_0x7f023207);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string._2130850313_res_0x7f023209, new OnGpsDialogClickListener(this, true));
        builder.setNegativeButton(R.string._2130850312_res_0x7f023208, new OnGpsDialogClickListener(this, false));
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        if (!PermissionUtils.isGpsLocationEnable(this.mContext)) {
            LogUtil.w(this.TAG, "openGeolocationForWebView: isGpsLocationEnable false");
            return false;
        }
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        WebView webView = h5ProInstance == null ? null : h5ProInstance.getWebView();
        if (webView == null) {
            LogUtil.w(this.TAG, "openGeolocationForWebView: webView is null");
            return false;
        }
        webView.getSettings().setGeolocationEnabled(true);
        return true;
    }

    private List<String> b(JSONArray jSONArray) {
        boolean z = Build.VERSION.SDK_INT >= 33;
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            String obj = jSONArray.get(i).toString();
            if (z && ("android.permission.READ_EXTERNAL_STORAGE".equalsIgnoreCase(obj) || "android.permission.WRITE_EXTERNAL_STORAGE".equalsIgnoreCase(obj))) {
                obj = "android.permission.READ_MEDIA_IMAGES";
            }
            if (!arrayList.contains(obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* loaded from: classes8.dex */
    public static class OnGpsDialogClickListener implements DialogInterface.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        public final boolean f2420a;
        public final WeakReference<PermissionEntry> b;

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            PermissionEntry permissionEntry = (PermissionEntry) GeneralUtil.getReferent(this.b);
            if (permissionEntry == null) {
                LogUtil.w("H5PRO_PermissionEntry_OnGpsDialogClickListener", "permissionEntry is null");
            } else if (this.f2420a) {
                LogUtil.i("H5PRO_PermissionEntry_OnGpsDialogClickListener", "onClick PositiveButton");
                Activity activity = permissionEntry.c;
                if (activity == null) {
                    LogUtil.w("H5PRO_PermissionEntry_OnGpsDialogClickListener", "activity is null");
                    permissionEntry.f2417a = false;
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                    try {
                        PermissionUtils.avoidImplicitProblem(activity, intent, 1360);
                    } catch (ActivityNotFoundException unused) {
                        intent.setAction("android.settings.SETTINGS");
                        try {
                            PermissionUtils.avoidImplicitProblem(activity, intent, 1360);
                        } catch (ActivityNotFoundException unused2) {
                            permissionEntry.f2417a = false;
                            permissionEntry.onFailureCallback(permissionEntry.b, "request location service fail: activity not found");
                        }
                    }
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    return;
                }
            } else {
                permissionEntry.f2417a = false;
                LogUtil.i("H5PRO_PermissionEntry_OnGpsDialogClickListener", "onClick NegativeButton");
                permissionEntry.onFailureCallback(permissionEntry.b, "request location service fail: user canceled");
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        public OnGpsDialogClickListener(PermissionEntry permissionEntry, boolean z) {
            this.b = new WeakReference<>(permissionEntry);
            this.f2420a = z;
        }
    }
}
