package defpackage;

import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;

/* loaded from: classes7.dex */
public class vhg {
    static MDCAdapter d;

    private static MDCAdapter c() throws NoClassDefFoundError {
        try {
            return StaticMDCBinder.getSingleton().getMDCA();
        } catch (NoSuchMethodError unused) {
            return StaticMDCBinder.SINGLETON.getMDCA();
        }
    }

    static {
        try {
            d = c();
        } catch (Exception e) {
            vhj.a("MDC binding unsuccessful.", e);
        } catch (NoClassDefFoundError e2) {
            d = new vhl();
            String message = e2.getMessage();
            if (message != null && message.contains("StaticMDCBinder")) {
                vhj.d("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
                vhj.d("Defaulting to no-operation MDCAdapter implementation.");
                vhj.d("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
                return;
            }
            throw e2;
        }
    }

    public static void b(String str, String str2) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        }
        MDCAdapter mDCAdapter = d;
        if (mDCAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        mDCAdapter.put(str, str2);
    }

    public static void d() {
        MDCAdapter mDCAdapter = d;
        if (mDCAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        mDCAdapter.clear();
    }
}
