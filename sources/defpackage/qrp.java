package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class qrp {

    /* renamed from: a, reason: collision with root package name */
    private static long f16555a;

    public static int a(double d) {
        if ((d * 100.0d) % 100.0d == 0.0d) {
            return (int) d;
        }
        return 100;
    }

    public static boolean b(int i) {
        return i < 66 && i >= 18;
    }

    public static boolean d(int i) {
        return i < 81 && i >= 6;
    }

    public static boolean e(int i) {
        return i < 81 && i >= 18;
    }

    public static int a(Context context, float f) {
        if (context == null) {
            LogUtil.h("HealthUtil", "context is null");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5d);
    }

    public static boolean e() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f16555a < 500) {
            return true;
        }
        f16555a = elapsedRealtime;
        return false;
    }

    public static boolean e(String str, Context context) {
        if (context == null) {
            LogUtil.b("HealthUtil", "isShowOverseaConfiguredPage context is null");
            return false;
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
        boolean isOperation = OperationUtils.isOperation(accountInfo);
        LogUtil.c(str, "isShowOverseaConfiguredPage countryCode = ", accountInfo, " isOperation = ", Boolean.valueOf(isOperation));
        return isOperation;
    }

    public static boolean d(cfe cfeVar, int i) {
        if (cfeVar == null) {
            LogUtil.h("HealthUtil", "isCommonViewVisible weightBean is null");
            return false;
        }
        if (i == 0) {
            return cfeVar.ax() > 0.0d;
        }
        if (i == 2) {
            double j = cfeVar.j();
            if (!dph.e(j, Utils.o())) {
                j = doj.a(cfeVar.ax(), cfeVar.t());
            }
            return dph.e(j, Utils.o());
        }
        if (i == 11) {
            return dph.f(cfeVar.p());
        }
        if (i != 12) {
            return true;
        }
        double ad = cfeVar.ad();
        return dph.m(ad) && ad > 0.0d;
    }

    public static boolean e(cfe cfeVar, int i) {
        if (cfeVar == null) {
            LogUtil.h("HealthUtil", "isNotOverseaViewVisible weightBean is null");
            return false;
        }
        if (i == 9) {
            return cfeVar.b() > 0.0d;
        }
        if (i == 13) {
            int e = qsj.e(cfeVar);
            return dph.d((double) e) && e != 0;
        }
        if (i != 25) {
            return true;
        }
        return dph.w(cfeVar.as());
    }

    public static boolean a(cfe cfeVar, int i) {
        if (cfeVar == null) {
            LogUtil.h("HealthUtil", "isNotOverseaViewVisible weightBean is null");
            return false;
        }
        if (i == 1) {
            return dph.j(cfeVar.a());
        }
        if (i == 7) {
            return dph.b(cfeVar.i());
        }
        if (i == 10) {
            return dph.p(cfeVar.aj());
        }
        if (i == 26) {
            return dph.r(cfeVar.af());
        }
        if (i == 3) {
            return dph.t(cfeVar.ap()) || dph.t(cfeVar.al());
        }
        if (i == 4) {
            return dph.e(cfeVar.d());
        }
        if (i == 5) {
            return dph.y(cfeVar.s());
        }
        return c(cfeVar, i);
    }

    private static boolean c(cfe cfeVar, int i) {
        if (i == 6) {
            return dph.k(cfeVar.z());
        }
        if (i != 8) {
            if (i != 14) {
                return true;
            }
            double a2 = cfeVar.a();
            return dph.j(a2) && dph.g(doj.e(a2, cfeVar.ax()));
        }
        double ap = cfeVar.ap();
        if (!dph.t(ap)) {
            ap = cfeVar.al();
        }
        double d = ap;
        if (dph.t(d)) {
            return dph.n(doj.d(cfeVar.ax(), d, cfeVar.a(), cfeVar.i(), cfeVar.getFractionDigitByType(0)));
        }
        return false;
    }

    public static HealthCalendar a(HealthCalendar healthCalendar, int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (healthCalendar == null) {
            healthCalendar = new HealthCalendar();
        }
        return healthCalendar.transformFromCalendar(calendar);
    }

    public static Activity dHL_(Context context) {
        Context context2 = (Context) new WeakReference(context).get();
        if (context2 == null) {
            return null;
        }
        if (context2 instanceof Activity) {
            return (Activity) context2;
        }
        if (context2 instanceof ContextWrapper) {
            return dHL_(((ContextWrapper) context2).getBaseContext());
        }
        return null;
    }

    public static void b(String str, String str2, String str3, HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.h("HealthUtil", "setSampleConfig listener is null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            hiDataOperateListener.onResult(-1, "setSampleConfig type " + str + " configId " + str2 + " configData " + str3);
            return;
        }
        HiSampleConfig.Builder builder = new HiSampleConfig.Builder();
        builder.c(System.currentTimeMillis());
        builder.g(String.valueOf(0));
        builder.c(String.valueOf(-1));
        builder.h(String.valueOf(0));
        builder.j(str);
        builder.d(str2);
        builder.b(str3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfig(builder));
        HiHealthManager.d(BaseApplication.e()).setSampleConfig(arrayList, hiDataOperateListener);
    }

    public static void e(final String str, final String str2, final HiDataReadResultListener hiDataReadResultListener) {
        if (hiDataReadResultListener == null) {
            LogUtil.h("HealthUtil", "getSampleConfig listener is null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            hiDataReadResultListener.onResult("getSampleConfig type " + str + " configId " + str2, -1, -1);
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qrq
                @Override // java.lang.Runnable
                public final void run() {
                    qrp.e(str, str2, hiDataReadResultListener);
                }
            });
            return;
        }
        HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
        builder.a(String.valueOf(0));
        builder.e(String.valueOf(0));
        builder.b(str);
        builder.d(str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfigKey(builder));
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        HiHealthManager.d(BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, hiDataReadResultListener);
    }

    public static void a(String str, String str2, HiDataOperateListener hiDataOperateListener) {
        b("9003", str, str2, hiDataOperateListener);
    }

    public static void b(String str, HiDataReadResultListener hiDataReadResultListener) {
        e("9003", str, hiDataReadResultListener);
    }

    public static void d(String str, String str2, HiDataOperateListener hiDataOperateListener) {
        b("9001", str, str2, hiDataOperateListener);
    }

    public static void c(String str, HiDataReadResultListener hiDataReadResultListener) {
        e("9001", str, hiDataReadResultListener);
    }

    public static boolean d() {
        return !Utils.o() || Utils.c(BaseApplication.e()) || Utils.a(BaseApplication.e());
    }
}
