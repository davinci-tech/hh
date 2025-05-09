package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.caq;
import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class caq {
    public static boolean c(Context context) {
        return (PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED) && !(PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) == PermissionUtil.PermissionResult.GRANTED);
    }

    public static int b(Context context, PermissionUtil.PermissionType permissionType, PermissionUtil.PermissionResult permissionResult, boolean z) {
        boolean e = PermissionUtil.e(context, PermissionUtil.c(PermissionUtil.PermissionType.LOCATION));
        boolean j = PermissionUtil.j();
        LogUtil.a("SportApplyPermissionUtils", "isHasLocation:", Boolean.valueOf(e), " isUseOnlyAllowedLocation():", Boolean.valueOf(j));
        if (permissionType == d() && e && j) {
            return b(context, permissionType, PermissionUtil.PermissionResult.GRANTED);
        }
        a(context, permissionType, e, z);
        return b(context, permissionType, permissionResult);
    }

    private static void a(final Context context, PermissionUtil.PermissionType permissionType, boolean z, boolean z2) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.huawei.health.basesport.utils.SportApplyPermissionUtils$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                caq.Cv_(context, view);
            }
        };
        if (PermissionUtil.a() && CommonUtil.bh() && permissionType == d()) {
            if (!z) {
                nsn.cLP_(context, z2, onClickListener);
                return;
            } else {
                nsn.cLQ_(context, z2, onClickListener);
                return;
            }
        }
        View.OnClickListener onClickListener2 = z2 ? onClickListener : null;
        if (!z2) {
            onClickListener = null;
        }
        nsn.cLJ_(context, permissionType, onClickListener2, onClickListener);
    }

    public static /* synthetic */ void Cv_(Context context, View view) {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static int b(Context context, PermissionUtil.PermissionType permissionType, PermissionUtil.PermissionResult permissionResult) {
        int e = e(permissionResult);
        String[] c = PermissionUtil.c(permissionType);
        if (context != null && c != null) {
            return e;
        }
        LogUtil.h("SportApplyPermissionUtils", "checkPermission param not valid");
        return 1;
    }

    public static int e(PermissionUtil.PermissionResult permissionResult) {
        return permissionResult == PermissionUtil.PermissionResult.FOREVER_DENIED ? 8 : 0;
    }

    public static boolean b(PermissionUtil.PermissionType permissionType) {
        return CommonUtil.av() && PermissionUtil.a() && (permissionType == PermissionUtil.PermissionType.CAMERA_IMAGE || permissionType == d());
    }

    public static PermissionUtil.PermissionType d() {
        return Build.VERSION.SDK_INT <= 29 ? PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND : PermissionUtil.PermissionType.LOCATION;
    }
}
