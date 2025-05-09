package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class nkx {
    private static final HashMap<String, ArrayList<bdx>> d = new HashMap<>();

    static {
        nks.e(BaseApplication.getContext());
    }

    private nkx() {
    }

    public static View.OnClickListener cwZ_(View.OnClickListener onClickListener, BaseActivity baseActivity, boolean z, String str) {
        bdx bdxVar = new bdx(onClickListener, z, str);
        a(bdxVar, baseActivity.toString());
        return bdxVar;
    }

    public static View.OnClickListener cwY_(View.OnClickListener onClickListener, Context context, boolean z, String str) {
        bdx bdxVar = new bdx(onClickListener, z, str);
        a(bdxVar, context.toString());
        return bdxVar;
    }

    public static void a(bdx bdxVar, String str) {
        synchronized (nkx.class) {
            if (bdxVar != null) {
                if (!TextUtils.isEmpty(str)) {
                    HashMap<String, ArrayList<bdx>> hashMap = d;
                    ArrayList<bdx> arrayList = hashMap.get(str);
                    if (koq.b(arrayList)) {
                        hashMap.put(str, new ArrayList<>(Arrays.asList(bdxVar)));
                    } else {
                        arrayList.add(bdxVar);
                    }
                }
            }
        }
    }

    public static void e(String str) {
        ArrayList<bdx> arrayList = d.get(str);
        if (koq.b(arrayList)) {
            return;
        }
        Iterator<bdx> it = arrayList.iterator();
        while (it.hasNext()) {
            bdx next = it.next();
            if (next != null) {
                next.e();
            }
        }
        d.remove(str);
    }

    public static void c(final boolean z) {
        LogUtil.a("PLGLOGIN_BrowseLogin", "enter callClickEvent");
        if (Looper.getMainLooper() != Looper.myLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: nkx.2
                @Override // java.lang.Runnable
                public void run() {
                    nkx.c(z);
                }
            });
            return;
        }
        HashMap<String, ArrayList<bdx>> hashMap = d;
        if (hashMap.isEmpty()) {
            LogUtil.h("PLGLOGIN_BrowseLogin", "LISTENERS_MAPS is empty.");
            return;
        }
        Iterator<Map.Entry<String, ArrayList<bdx>>> it = hashMap.entrySet().iterator();
        bdx bdxVar = null;
        while (it.hasNext()) {
            ArrayList<bdx> value = it.next().getValue();
            if (!koq.b(value)) {
                Iterator<bdx> it2 = value.iterator();
                while (it2.hasNext()) {
                    bdx next = it2.next();
                    if (next.pv_() != null && !next.equals(bdxVar)) {
                        if (bdxVar != null) {
                            if (next.c(bdxVar)) {
                                bdxVar.e();
                            } else {
                                next.e();
                            }
                        }
                        bdxVar = next;
                    }
                }
            }
        }
        if (e(bdxVar, z)) {
            LogUtil.a("PLGLOGIN_BrowseLogin", "callClickEvent() do next.");
            bdxVar.onNext();
        } else if (bdxVar != null) {
            LogUtil.a("PLGLOGIN_BrowseLogin", "callClickEvent() siteid change.");
            bdxVar.e();
        } else {
            LogUtil.a("PLGLOGIN_BrowseLogin", "login not by click.");
        }
    }

    private static boolean e(bdx bdxVar, boolean z) {
        return (z || bdxVar == null || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() || LoginInit.getBrowseCallback() != null) ? false : true;
    }
}
