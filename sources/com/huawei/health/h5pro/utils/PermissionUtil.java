package com.huawei.health.h5pro.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.huawei.health.h5pro.ext.H5ProResidentExtManager;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionExtApi;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionRequestCallback;
import com.huawei.health.h5pro.ext.permission.PermissionResultHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PermissionUtil {
    public static PermissionUtil b;
    public static final String[] c;
    public static final String[] e;

    public void requestWebKitPermission(Activity activity, String[] strArr, int i) {
        if (activity == null || strArr == null || strArr.length == 0) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (TextUtils.equals("android.webkit.resource.AUDIO_CAPTURE", str)) {
                arrayList = b(arrayList, new String[]{"android.permission.RECORD_AUDIO"});
            } else if (TextUtils.equals("android.webkit.resource.VIDEO_CAPTURE", str)) {
                arrayList = b(arrayList, new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA"});
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        requestPermission(activity, (String[]) arrayList.toArray(new String[0]), i);
    }

    public void requestPermission(Activity activity, String[] strArr, int i) {
        if (activity == null || strArr == null || strArr.length == 0) {
            return;
        }
        ActivityCompat.requestPermissions(activity, strArr, i);
    }

    public boolean isAllPermitted(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public void handlePermissionResult(int i, String[] strArr, int[] iArr, PermissionResultHandler permissionResultHandler) {
        H5ProPermissionExtApi permissionExtApi = H5ProResidentExtManager.getPermissionExtApi();
        if (permissionExtApi == null || !permissionExtApi.isSupport(strArr)) {
            permissionResultHandler.handle(i, strArr, iArr);
        } else {
            permissionExtApi.notifyPermissionsChange(strArr, iArr);
        }
    }

    public boolean checkWebKitPermission(Context context, String[] strArr) {
        if (context == null || strArr == null || strArr.length == 0) {
            return false;
        }
        boolean z = false;
        for (String str : strArr) {
            if (TextUtils.equals("android.webkit.resource.AUDIO_CAPTURE", str)) {
                z = checkPermission(context, new String[]{"android.permission.RECORD_AUDIO"});
            } else if (TextUtils.equals("android.webkit.resource.VIDEO_CAPTURE", str)) {
                z = checkPermission(context, new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA"});
            } else {
                LogUtil.i("H5PRO_PermissionUtil", "Not supported：" + str);
                z = false;
            }
            if (!z) {
                break;
            }
        }
        return z;
    }

    public boolean checkPermission(Context context, String[] strArr) {
        if (context == null || strArr == null || strArr.length == 0) {
            return false;
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str) && ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public void checkAndRequestPermissions(Activity activity, final String[] strArr, final int i, boolean z, final H5ProPermissionCallback h5ProPermissionCallback) {
        H5ProPermissionExtApi permissionExtApi = H5ProResidentExtManager.getPermissionExtApi();
        if (permissionExtApi == null) {
            XS_(activity, strArr, i, h5ProPermissionCallback);
        } else {
            final WeakReference weakReference = new WeakReference(activity);
            permissionExtApi.checkAndRequestPermissions((Context) weakReference.get(), strArr, z, new H5ProPermissionRequestCallback() { // from class: com.huawei.health.h5pro.utils.PermissionUtil.1
                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onGranted() {
                    LogUtil.i("H5PRO_PermissionUtil", "checkAndRequestPermissions: ext -> onGranted");
                    h5ProPermissionCallback.onGranted();
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onForeverDenied(String[] strArr2) {
                    LogUtil.i("H5PRO_PermissionUtil", "checkAndRequestPermissions: ext -> onForeverDenied");
                    h5ProPermissionCallback.onForeverDenied(strArr2);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onDenied(String str) {
                    LogUtil.i("H5PRO_PermissionUtil", "checkAndRequestPermissions: ext -> onDenied");
                    h5ProPermissionCallback.onDenied(str);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionRequestCallback
                public void onDefault() {
                    LogUtil.i("H5PRO_PermissionUtil", "checkAndRequestPermissions: ext -> onDefault");
                    PermissionUtil.this.XS_((Activity) weakReference.get(), strArr, i, h5ProPermissionCallback);
                }
            });
        }
    }

    public void checkAndRequestPermissions(Activity activity, String[] strArr, int i, H5ProPermissionCallback h5ProPermissionCallback) {
        checkAndRequestPermissions(activity, strArr, i, true, h5ProPermissionCallback);
    }

    private ArrayList<String> b(ArrayList<String> arrayList, String[] strArr) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str) && !arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public static PermissionUtil getInstance() {
        if (b == null) {
            synchronized (PermissionUtil.class) {
                if (b == null) {
                    b = new PermissionUtil();
                }
            }
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void XS_(Activity activity, String[] strArr, int i, H5ProPermissionCallback h5ProPermissionCallback) {
        LogUtil.i("H5PRO_PermissionUtil", "checkAndRequestPermissions: default");
        if (h5ProPermissionCallback instanceof H5ProPermissionRequestCallback) {
            ((H5ProPermissionRequestCallback) h5ProPermissionCallback).onDefault();
        } else if (getInstance().checkPermission(activity, strArr)) {
            h5ProPermissionCallback.onGranted();
        } else {
            requestPermission(activity, strArr, i);
        }
    }

    static {
        String str;
        String str2;
        if (Build.VERSION.SDK_INT < 33) {
            str = "android.permission.WRITE_EXTERNAL_STORAGE";
            str2 = "android.permission.READ_EXTERNAL_STORAGE";
        } else {
            str = "android.permission.READ_MEDIA_IMAGES";
            str2 = "android.permission.READ_MEDIA_VIDEO";
        }
        e = new String[]{str, str2};
        c = new String[]{"android.permission.CAMERA"};
    }
}
