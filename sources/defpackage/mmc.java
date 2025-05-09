package defpackage;

import android.content.Context;
import android.widget.Toast;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class mmc {
    private static volatile mmc b;

    /* renamed from: a, reason: collision with root package name */
    private Toast f15059a;

    private mmc() {
    }

    public static mmc b() {
        mmc mmcVar;
        synchronized (mmc.class) {
            if (b == null) {
                synchronized (mmc.class) {
                    if (b == null) {
                        b = new mmc();
                    }
                }
            }
            mmcVar = b;
        }
        return mmcVar;
    }

    public void b(Context context, String str) {
        LogUtil.a("PLGACHIEVE_ToastUtils", "showShortToast");
        if (this.f15059a == null) {
            this.f15059a = Toast.makeText(context == null ? BaseApplication.getContext() : context.getApplicationContext(), str, 0);
        }
        this.f15059a.setText(str);
        this.f15059a.show();
    }
}
