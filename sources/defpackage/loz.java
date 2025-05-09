package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.lang.ref.WeakReference;
import java.util.Map;
import net.openid.appauth.connectivity.ConnectionBuilder;

/* loaded from: classes5.dex */
public final class loz {

    /* renamed from: a, reason: collision with root package name */
    private String f14821a;
    private String c;
    private String d;
    private final SharedPreferences f;
    private Uri g;
    private Uri h;
    private final Context i;
    private Uri j;
    private String k;
    private String l;
    private final Resources n;
    private int o;
    private static WeakReference<loz> e = new WeakReference<>(null);
    private static final Object b = new Object();

    private loz(Context context, int i) {
        this.i = context;
        this.f = context.getSharedPreferences(NetworkService.Constants.CONFIG_SERVICE, 0);
        this.n = context.getResources();
        this.o = i;
        try {
            j();
        } catch (b e2) {
            this.c = e2.getMessage();
            loq.c("Configuration", "readConfiguration mConfigError: " + this.c);
        }
    }

    public static loz d(Context context, int i) {
        loz lozVar;
        synchronized (b) {
            lozVar = e.get();
            if (lozVar == null) {
                lozVar = new loz(context, i);
                e = new WeakReference<>(lozVar);
            }
        }
        return lozVar;
    }

    public boolean f() {
        String l = l();
        String str = this.f14821a;
        return (str == null || str.equals(l)) ? false : true;
    }

    public boolean g() {
        return this.c == null;
    }

    public void c() {
        this.f.edit().putString("lastHash", this.f14821a).apply();
    }

    public String b() {
        return this.d;
    }

    public String i() {
        return this.l;
    }

    public Uri bYL_() {
        return this.h;
    }

    public Uri bYJ_() {
        return this.g;
    }

    public ConnectionBuilder a() {
        return uua.e;
    }

    private String l() {
        return this.f.getString("lastHash", null);
    }

    public void j() throws b {
        lne b2 = lng.e().b(this.i, this.o);
        if (b2 == null) {
            throw new b("refreshCarrierConfig, refresh failed");
        }
        this.k = lop.b(this.i, this.o);
        this.d = b2.b();
        loq.c("Configuration", "CarrierConfigInfo mClientId = " + this.d);
        loq.c("Configuration", "CarrierConfigInfo getRedirectUrl = " + b2.i());
        this.h = bYI_(b2.i());
        this.l = b2.e();
        this.g = bYI_(b2.a());
        this.f14821a = String.valueOf((this.k + this.d + this.h + this.l + this.g).hashCode());
        if (!m()) {
            throw new b("redirect_uri is not handled by any activity in this app!");
        }
        loq.c("Configuration", "refreshCarrierConfig, refresh success");
    }

    public void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        try {
            this.d = map.get("client_id");
            this.h = bYI_(map.get(CommonConstant.ReqAccessTokenParam.REDIRECT_URI));
            this.l = map.get("scope");
            this.j = bYI_(map.get("oidc_url"));
            this.f14821a = String.valueOf((this.d + this.h + this.l).hashCode());
            if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.h.toString()) || TextUtils.isEmpty(this.l)) {
                loq.b("Configuration", "empty config, return");
            } else {
                this.c = null;
            }
        } catch (b unused) {
            loq.b("Configuration", "refreshConfiguration, refresh fail");
        }
    }

    public Uri bYK_() {
        return this.j;
    }

    private Uri bYI_(String str) throws b {
        try {
            return Uri.parse(str);
        } catch (Throwable th) {
            throw new b("Transfer failed, " + th.toString());
        }
    }

    private boolean m() {
        Intent intent = new Intent();
        intent.setPackage(this.i.getPackageName());
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(this.h);
        return !this.i.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public static final class b extends Exception {
        b(String str) {
            super(str);
        }
    }
}
