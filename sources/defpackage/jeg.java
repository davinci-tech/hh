package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.Permissions;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class jeg {
    private static jeg d;
    private final Set<String> b = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private final List<PermissionsResultAction> f13763a = new ArrayList(16);

    private jeg() {
    }

    public static jeg d() {
        jeg jegVar;
        synchronized (jeg.class) {
            if (d == null) {
                d = new jeg();
            }
            jegVar = d;
        }
        return jegVar;
    }

    private void b(String[] strArr, PermissionsResultAction permissionsResultAction) {
        synchronized (this) {
            if (permissionsResultAction == null) {
                return;
            }
            permissionsResultAction.registerPermissions(strArr);
            this.f13763a.add(permissionsResultAction);
        }
    }

    public void b(PermissionsResultAction permissionsResultAction) {
        synchronized (this) {
            Iterator<PermissionsResultAction> it = this.f13763a.iterator();
            while (it.hasNext()) {
                PermissionsResultAction next = it.next();
                if (next == permissionsResultAction || next == null) {
                    it.remove();
                }
            }
        }
    }

    public boolean c(Context context, String str) {
        synchronized (this) {
            if (PermissionUtil.g()) {
                if ("android.permission.MANAGE_EXTERNAL_STORAGE".equals(str)) {
                    return Environment.isExternalStorageManager();
                }
                if (Constants.POST_NOTIFICATIONS.equals(str)) {
                    return NotificationManagerCompat.from(context).areNotificationsEnabled();
                }
            }
            return context != null && (ActivityCompat.checkSelfPermission(context, str) == 0 || !b(context, str));
        }
    }

    private boolean b(Context context, String str) {
        PermissionInfo permissionInfo;
        try {
            permissionInfo = context.getApplicationContext().getPackageManager().getPermissionInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("PermissionsManager", "cannot found permission: ", str);
            permissionInfo = null;
        }
        if (permissionInfo == null) {
            return false;
        }
        LogUtil.c("PermissionsManager", "android res: ", Boolean.valueOf(OsType.ANDROID.equals(permissionInfo.packageName)), ",hwext res: ", Boolean.valueOf("androidhwext".equals(permissionInfo.packageName)), ",hnext res: ", Boolean.valueOf("androidhnext".equals(permissionInfo.packageName)));
        return OsType.ANDROID.equals(permissionInfo.packageName) || "androidhwext".equals(permissionInfo.packageName) || "androidhnext".equals(permissionInfo.packageName);
    }

    public void bGx_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        synchronized (this) {
            if (activity == null) {
                return;
            }
            b(strArr, permissionsResultAction);
            List<String> bGv_ = bGv_(activity, strArr, permissionsResultAction);
            if (bGv_.isEmpty()) {
                b(permissionsResultAction);
                return;
            }
            String[] strArr2 = (String[]) bGv_.toArray(new String[0]);
            this.b.addAll(bGv_);
            try {
                ActivityCompat.requestPermissions(activity, strArr2, 20192);
                c(strArr2, "com.huawei.health.action.ACTION_PERMISSION_REQUEST");
            } catch (ActivityNotFoundException e) {
                LogUtil.e("PermissionsManager", "requestPermissionsIfNecessaryForResult ex: ", e.getMessage());
            }
        }
    }

    public void bGy_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction, int i) {
        synchronized (this) {
            if (activity == null) {
                return;
            }
            b(strArr, permissionsResultAction);
            List<String> bGw_ = bGw_(activity, strArr, permissionsResultAction);
            if (bGw_.isEmpty()) {
                b(permissionsResultAction);
            } else {
                String[] strArr2 = (String[]) bGw_.toArray(new String[0]);
                this.b.addAll(bGw_);
                ActivityCompat.requestPermissions(activity, strArr2, i);
                c(strArr2, "com.huawei.health.action.ACTION_PERMISSION_REQUEST");
            }
        }
    }

    public void a(Fragment fragment, String[] strArr, PermissionsResultAction permissionsResultAction) {
        synchronized (this) {
            if (fragment == null) {
                return;
            }
            bGy_(fragment.getActivity(), strArr, permissionsResultAction, 1);
        }
    }

    public void d(String[] strArr, int[] iArr) {
        int i;
        synchronized (this) {
            LogUtil.c("PermissionsManager", "notifyPermissionsChange");
            c((String[]) null, "com.huawei.health.action.ACTION_PERMISSION_REQUEST_FINISH");
            if (strArr != null && iArr != null) {
                int length = strArr.length;
                if (iArr.length < length) {
                    length = iArr.length;
                }
                Iterator<PermissionsResultAction> it = this.f13763a.iterator();
                while (it.hasNext()) {
                    PermissionsResultAction next = it.next();
                    for (0; i < length; i + 1) {
                        i = (next == null || next.onResult(strArr[i], iArr[i])) ? 0 : i + 1;
                        it.remove();
                        break;
                    }
                }
                for (int i2 = 0; i2 < length; i2++) {
                    this.b.remove(strArr[i2]);
                }
            }
        }
    }

    private void c(String[] strArr, String str) {
        if (CommonUtil.av() || (CommonUtil.bf() && !CommonUtil.ak())) {
            LogUtil.c("PermissionsManager", "no need to send");
            return;
        }
        Intent intent = new Intent();
        intent.setAction(str);
        intent.putExtra("Permissions", strArr);
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }

    private List<String> bGw_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        if (strArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (b(activity, str)) {
                if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                    if (!this.b.contains(str)) {
                        arrayList.add(str);
                    }
                } else if (permissionsResultAction != null) {
                    permissionsResultAction.onResult(str, Permissions.GRANTED);
                }
            } else if (permissionsResultAction != null) {
                permissionsResultAction.onResult(str, Permissions.NOT_FOUND);
            }
        }
        return arrayList;
    }

    private List<String> bGv_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        if (strArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (b(activity, str)) {
                if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                    arrayList.add(str);
                } else if (permissionsResultAction != null) {
                    permissionsResultAction.onResult(str, Permissions.GRANTED);
                }
            } else if (permissionsResultAction != null) {
                permissionsResultAction.onResult(str, Permissions.NOT_FOUND);
            }
        }
        return arrayList;
    }

    public static void a() {
        synchronized (jeg.class) {
            if (d != null) {
                d = null;
            }
        }
    }
}
