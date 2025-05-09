package defpackage;

import com.huawei.hwidauth.utils.b.b;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class ksj {

    /* renamed from: a, reason: collision with root package name */
    private b.a f14572a;
    private b d;
    private final String e;

    static class d {
        public static ksj b = new ksj();
    }

    private ksj() {
        this.e = "c";
        this.f14572a = b.a.MODE_SUPPORT_UNKNOWN;
    }

    public static ksj b() {
        return d.b;
    }

    public boolean c() {
        if (this.f14572a != b.a.MODE_SUPPORT_UNKNOWN) {
            if (this.f14572a == b.a.MODE_SUPPORT_HW_GEMINI || this.f14572a == b.a.MODE_SUPPORT_MTK_GEMINI) {
                return true;
            }
        } else {
            try {
                if (d()) {
                    d(b.a.MODE_SUPPORT_MTK_GEMINI);
                    return true;
                }
                if (e()) {
                    d(b.a.MODE_SUPPORT_HW_GEMINI);
                    return true;
                }
                d(b.a.MODE_NOT_SUPPORT_GEMINI);
            } catch (Error e) {
                ksy.a(this.e, "" + e.getClass().getSimpleName(), true);
            } catch (Exception e2) {
                ksy.a(this.e, " " + e2.getClass().getSimpleName(), true);
            }
        }
        return false;
    }

    public b a() {
        if (this.f14572a == b.a.MODE_SUPPORT_MTK_GEMINI) {
            this.d = ksn.e();
        } else {
            this.d = ksh.d();
        }
        return this.d;
    }

    public boolean e() {
        boolean z = false;
        try {
            Object e = ksh.e();
            if (e != null) {
                z = ((Boolean) e.getClass().getMethod("isMultiSimEnabled", new Class[0]).invoke(e, new Object[0])).booleanValue();
            }
        } catch (Error e2) {
            ksy.a(this.e, "108isMultiSimEnabled()" + e2.getClass().getSimpleName(), true);
        } catch (Exception e3) {
            ksy.a(this.e, "isMultiSimEnabled()?" + e3.getClass().getSimpleName(), true);
        }
        ksy.a(this.e, "isHwGeminiSupport1" + z, true);
        return z;
    }

    private boolean d() {
        boolean z;
        try {
            Field declaredField = Class.forName("com.mediatek.common.featureoption.FeatureOption").getDeclaredField("MTK_GEMINI_SUPPORT");
            declaredField.setAccessible(true);
            z = declaredField.getBoolean(null);
        } catch (Error e) {
            ksy.a(this.e, "FeatureOption.MTK_GEMINI_SUPPORT" + e.getClass().getSimpleName(), true);
            z = false;
            ksy.a(this.e, "isMtkGeminiSupport" + z, true);
            return z;
        } catch (Exception e2) {
            ksy.a(this.e, "FeatureOption.MTK_GEMINI_SUPPORT" + e2.getClass().getSimpleName(), true);
            z = false;
            ksy.a(this.e, "isMtkGeminiSupport" + z, true);
            return z;
        }
        ksy.a(this.e, "isMtkGeminiSupport" + z, true);
        return z;
    }

    private void d(b.a aVar) {
        this.f14572a = aVar;
    }
}
