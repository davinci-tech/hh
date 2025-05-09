package defpackage;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.huawei.codevalueplatform.coderule.bean.basebean.Rule;
import com.huawei.codevalueplatform.coderule.model.CodeRulesUpdater;
import com.huawei.hms.network.NetworkKit;
import java.util.List;

/* loaded from: classes3.dex */
public class bec {

    /* renamed from: a, reason: collision with root package name */
    private static Application f344a = null;
    private static int b = 250;
    private static boolean c = false;
    private static String d = "";
    private static boolean e = false;

    public static void pw_(Application application, String str) {
        f344a = application;
        d = str;
        NetworkKit.init(application, new NetworkKit.Callback() { // from class: bec.5
            @Override // com.huawei.hms.network.NetworkKit.Callback
            public void onResult(boolean z) {
                bes.e("CodeValuePlatform", "NetworkKit init isSuccess: " + z);
            }
        });
    }

    public static void g() {
        c = true;
    }

    public static boolean a() {
        return c;
    }

    public static boolean i() {
        return e;
    }

    public static int b() {
        return b;
    }

    public static Context e() {
        return f344a;
    }

    public static String c() {
        return d;
    }

    public static void d(String str, CodeRulesUpdater.UpdateCallback updateCallback) {
        new CodeRulesUpdater().a(str, updateCallback);
    }

    public static List<Rule> d() {
        return new CodeRulesUpdater().e();
    }

    public static Intent px_(String str, List<Rule> list) {
        return new bea().pJ_(str, list);
    }
}
