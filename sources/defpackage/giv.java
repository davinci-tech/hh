package defpackage;

import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class giv {
    public static void b(njp njpVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (njpVar == null || TextUtils.isEmpty(njpVar.f())) {
            LogUtil.h("TtdServiceManager", "productQueryByType productTyp is empty");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        giu giuVar = new giu();
        HashMap hashMap = new HashMap();
        hashMap.put("productType", njpVar.f());
        if (!TextUtils.isEmpty(njpVar.b())) {
            hashMap.put("productCode", njpVar.b());
        }
        HashMap hashMap2 = new HashMap();
        if (!TextUtils.isEmpty(njpVar.a())) {
            hashMap2.put("x-devicemodel", njpVar.a());
        }
        if (!TextUtils.isEmpty(njpVar.e())) {
            hashMap2.put("deviceid", njpVar.e());
        }
        if (!TextUtils.isEmpty(njpVar.d())) {
            hashMap2.put("devicetype", njpVar.d());
        }
        if (!TextUtils.isEmpty(njpVar.c())) {
            hashMap2.put("x-brandchannel", njpVar.c());
        }
        LogUtil.a("TtdServiceManager", "productQueryByType header:", hashMap2.values().toString());
        giuVar.e(hashMap2);
        git.e().d(giuVar, hashMap, new IBaseResponseCallback() { // from class: giw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                giv.a(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("TtdServiceManager", "productQueryByType resultCode:", Integer.valueOf(i));
        if (i == 0 && (obj instanceof giz)) {
            iBaseResponseCallback.d(0, ((giz) obj).c());
        } else {
            iBaseResponseCallback.d(i, null);
        }
    }
}
