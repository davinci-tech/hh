package com.huawei.health.h5pro.jsbridge.system.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProWebViewActivity;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionRequestCallback;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class AndroidPermission implements IPermission {
    public static List<String> c;

    /* renamed from: a, reason: collision with root package name */
    public Context f2415a;
    public Map<PermissionCallback, List<String>> d = new ConcurrentHashMap();
    public Map<PermissionCallback, int[]> b = new ConcurrentHashMap();
    public Set<String> e = new HashSet();

    public interface PermissionCallback {
        void onComplete(int[] iArr);

        void onFailure(String str);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.permission.IPermission
    public void requestPermissions(final String[] strArr, final PermissionCallback permissionCallback) {
        if (this.f2415a == null) {
            permissionCallback.onFailure("h5proContext is null");
        } else {
            PermissionUtil.getInstance().checkAndRequestPermissions((Activity) this.f2415a, strArr, 758, new H5ProPermissionRequestCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission.1
                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onGranted() {
                    LogUtil.i("AndroidPermission", "requestPermissions: onGranted");
                    int[] iArr = new int[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        iArr[i] = 1;
                    }
                    permissionCallback.onComplete(iArr);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onForeverDenied(String[] strArr2) {
                    int[] iArr = new int[strArr2.length];
                    for (int i = 0; i < strArr2.length; i++) {
                        iArr[i] = -2;
                    }
                    permissionCallback.onComplete(iArr);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionCallback
                public void onDenied(String str) {
                    LogUtil.i("AndroidPermission", "requestPermissions: onDenied -> " + str);
                    int[] iArr = new int[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        iArr[i] = -1;
                    }
                    permissionCallback.onComplete(iArr);
                }

                @Override // com.huawei.health.h5pro.ext.permission.H5ProPermissionRequestCallback
                public void onDefault() {
                    LogUtil.i("AndroidPermission", "requestPermissions: onDefault");
                    AndroidPermission.this.d(strArr, permissionCallback);
                }
            });
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.permission.IPermission
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        String str;
        if (i == 758) {
            int min = Math.min(strArr.length, iArr.length);
            boolean z = false;
            for (int i2 = 0; i2 < min; i2++) {
                int i3 = 1;
                if (iArr[i2] == 0) {
                    str = strArr[i2];
                } else if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) this.f2415a, strArr[i2])) {
                    str = strArr[i2];
                    i3 = -1;
                } else {
                    c(strArr[i2], -2);
                    z = true;
                }
                c(str, i3);
            }
            if (z) {
                d();
            }
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.permission.IPermission
    public void onMount(Context context) {
        if (context instanceof H5ProWebViewActivity) {
            this.f2415a = context;
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.permission.IPermission
    public void destroy() {
        if (this.f2415a == null) {
            return;
        }
        this.f2415a = null;
        this.d.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", applicationContext.getPackageName(), null));
        ((H5ProWebViewActivity) this.f2415a).startActivityForResult(intent, 875);
    }

    private void d() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f2415a);
        String c2 = c();
        builder.setTitle(R.string._2130850310_res_0x7f023206);
        builder.setMessage(c2);
        builder.setPositiveButton(R.string._2130850309_res_0x7f023205, new DialogInterface.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AndroidPermission androidPermission = AndroidPermission.this;
                androidPermission.e(androidPermission.f2415a);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string._2130850308_res_0x7f023204, new DialogInterface.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.permission.AndroidPermission.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.show();
    }

    private void c(String str, int i) {
        for (PermissionCallback permissionCallback : this.d.keySet()) {
            List<String> list = this.d.get(permissionCallback);
            int[] iArr = this.b.get(permissionCallback);
            if (list != null && list.contains(str)) {
                int indexOf = list.indexOf(str);
                this.d.put(permissionCallback, list);
                if (iArr != null && iArr.length > indexOf) {
                    iArr[indexOf] = i;
                }
            }
            if (list == null || list.isEmpty() || iArr == null || a(iArr)) {
                permissionCallback.onComplete(iArr);
                this.d.remove(permissionCallback);
            }
        }
        this.e.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String[] strArr, PermissionCallback permissionCallback) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        this.d.put(permissionCallback, arrayList);
        this.b.put(permissionCallback, new int[strArr.length]);
        String[] b = b(arrayList);
        if (b.length == 0) {
            return;
        }
        ActivityCompat.requestPermissions((H5ProWebViewActivity) this.f2415a, b, 758);
    }

    private boolean a(int[] iArr) {
        for (int i : iArr) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    private List<String> XG_(Activity activity) {
        ArrayList arrayList;
        PackageInfo packageInfo;
        String[] strArr;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                LogUtil.d("AndroidPermission", activity.getPackageName());
                packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096);
            } catch (PackageManager.NameNotFoundException e) {
                LogUtil.e("AndroidPermission", "getManifestPermissions: " + e.getMessage());
                packageInfo = null;
            }
            if (packageInfo != null && (strArr = packageInfo.requestedPermissions) != null) {
                Collections.addAll(arrayList, strArr);
                LogUtil.d("AndroidPermission", "Manifest contained permission: " + Arrays.toString(strArr));
            }
        }
        return arrayList;
    }

    private String[] b(List<String> list) {
        int i;
        ArrayList arrayList = new ArrayList();
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!a(str)) {
                i = -1;
            } else if (PermissionUtils.isHasPermission(this.f2415a, str)) {
                i = 1;
            } else if (this.e.add(str)) {
                arrayList.add(str);
            }
            c(str, i);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private String c() {
        return this.f2415a.getResources().getString(R.string._2130850307_res_0x7f023203);
    }

    private boolean a(String str) {
        if (c == null) {
            c = XG_((Activity) this.f2415a);
        }
        return c.contains(str);
    }
}
