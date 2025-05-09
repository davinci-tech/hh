package com.amap.api.offlineservice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class AMapPermissionActivity extends Activity {
    protected String[] needPermissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};

    /* renamed from: a, reason: collision with root package name */
    private boolean f1461a = true;

    @Override // android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (this.f1461a) {
                a(this.needPermissions);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(String... strArr) {
        List<String> b;
        try {
            if (getApplicationInfo().targetSdkVersion < 23 || (b = b(strArr)) == null) {
                return;
            }
            if (b.size() > 0) {
                try {
                    getClass().getMethod("requestPermissions", String[].class, Integer.TYPE).invoke(this, (String[]) b.toArray(new String[b.size()]), 0);
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private List<String> b(String[] strArr) {
        try {
            ArrayList arrayList = new ArrayList();
            if (getApplicationInfo().targetSdkVersion >= 23) {
                for (String str : strArr) {
                    if (a(str) != 0 || b(str)) {
                        arrayList.add(str);
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private int a(String str) {
        try {
            return ((Integer) getClass().getMethod("checkSelfPermission", String.class).invoke(this, str)).intValue();
        } catch (Throwable unused) {
            return -1;
        }
    }

    private boolean b(String str) {
        try {
            return ((Boolean) getClass().getMethod("shouldShowRequestPermissionRationale", String.class).invoke(this, str)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a(int[] iArr) {
        try {
            for (int i : iArr) {
                if (i != 0) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 0) {
            try {
                if (a(iArr)) {
                    return;
                }
                a();
                this.f1461a = false;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void a() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.amap.api.offlineservice.AMapPermissionActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        AMapPermissionActivity.this.finish();
                        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    } catch (Throwable th) {
                        th.printStackTrace();
                        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    }
                }
            });
            builder.setPositiveButton("设置", new DialogInterface.OnClickListener() { // from class: com.amap.api.offlineservice.AMapPermissionActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        AMapPermissionActivity.this.b();
                        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    } catch (Throwable th) {
                        th.printStackTrace();
                        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
