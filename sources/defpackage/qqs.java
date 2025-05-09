package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.trade.datatype.BaseDeviceBenefitInfo;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import com.huawei.trade.datatype.Product;
import com.huawei.ui.commonui.dialog.EquityDialog;
import com.huawei.ui.main.R$string;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class qqs {

    /* renamed from: a, reason: collision with root package name */
    private static long f16548a;
    private static long b;
    private static long d;

    public static boolean c(long j, long j2) {
        long h = jec.h();
        return h > j && h < j2;
    }

    public static long a(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        return calendar.getTimeInMillis();
    }

    public static void d(Context context, String str, int i, int i2) {
        String newPathConcat = i2 == 1 ? OperationUtils.newPathConcat(str, "from", String.valueOf(6)) : null;
        if (i2 == 2) {
            newPathConcat = OperationUtils.newPathConcat(str, "from", String.valueOf(7));
        }
        if (i == 1) {
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addPath(newPathConcat);
            bzs.e().loadH5ProApp(context, "com.huawei.health.h5.vip", builder);
        } else {
            if (i == 2) {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(newPathConcat));
                intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
                try {
                    context.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("EquityUtils", "ActivityNotFoundException", e.getMessage());
                    return;
                }
            }
            LogUtil.h("EquityUtils", "clickDeviceInboxInfo error linkType");
        }
    }

    public static void dHA_(final Context context, final BaseDeviceBenefitInfo baseDeviceBenefitInfo, Drawable drawable, int i, final int i2) {
        if (context == null || baseDeviceBenefitInfo == null || drawable == null) {
            LogUtil.b("EquityUtils", "showEquityDialog failed, empty param");
            return;
        }
        String deviceName = baseDeviceBenefitInfo.getDeviceName();
        String a2 = a(baseDeviceBenefitInfo);
        if (TextUtils.isEmpty(deviceName) || TextUtils.isEmpty(a2)) {
            LogUtil.b("EquityUtils", "showEquityDialog failed, deviceName or benefitsTime empty");
            return;
        }
        LogUtil.a("EquityUtils", "showEquityDialog");
        e(i2, i);
        final EquityDialog equityDialog = new EquityDialog(context);
        equityDialog.czj_(drawable);
        String format = String.format(Locale.ENGLISH, context.getResources().getString(R$string.IDS_equity_first), deviceName);
        String format2 = String.format(Locale.ENGLISH, context.getResources().getString(R$string.IDS_equity_guardian_service), a2);
        equityDialog.a(format);
        equityDialog.e(format2);
        equityDialog.setCanceledOnTouchOutside(true);
        b(1, context, 0);
        equityDialog.czl_(new View.OnClickListener() { // from class: qqs.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("EquityUtils", "onclick activate");
                qqs.b(1, context, 1);
                qqs.d(context, baseDeviceBenefitInfo.getLinkValue(), baseDeviceBenefitInfo.getLinkType(), i2);
                equityDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        equityDialog.czk_(new View.OnClickListener() { // from class: qqs.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("EquityUtils", "onclick cancel");
                EquityDialog.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        a(1, context, (int) (System.currentTimeMillis() - f16548a));
        equityDialog.enqueueShowing(equityDialog);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: qqs.3
            @Override // java.lang.Runnable
            public void run() {
                ObserverManagerUtil.c("EQUITY_AND_VIBRANT", new Object[0]);
            }
        }, 800L);
    }

    private static String a(BaseDeviceBenefitInfo baseDeviceBenefitInfo) {
        String e;
        if (baseDeviceBenefitInfo instanceof DeviceInboxInfo) {
            e = gpp.e((DeviceInboxInfo) baseDeviceBenefitInfo);
        } else if (baseDeviceBenefitInfo instanceof DevicePerfPurchaseInfo) {
            e = gpp.e((DevicePerfPurchaseInfo) baseDeviceBenefitInfo);
        } else {
            LogUtil.b("EquityUtils", "getBenefitsTimeStr unknown deviceBenefitInfo type ", baseDeviceBenefitInfo);
            return "";
        }
        if (e != null) {
            return gpn.a(e);
        }
        return gpn.a(Product.YEAR_DURATION_FLAG);
    }

    private static void e(int i, int i2) {
        String str;
        String str2;
        String str3;
        if (i == 1) {
            str = "FRAGMENT_SHOW_DIALOG";
            str2 = "FRAGMENT_SHOW_DIALOG_COUNT";
            str3 = "FRAGMENT_START_TIME";
        } else {
            str = "BLOOD_SHOW_DIALOG";
            str2 = "BLOOD_SHOW_DIALOG_COUNT";
            str3 = "BLOOD_START_TIME";
        }
        nsd.e(str, false);
        d = System.currentTimeMillis();
        nsd.e(str2, i2 + 1);
        nsd.d(str3, d);
    }

    public static void d(Object obj, Context context, int i, long j, int i2) {
        if (obj != null) {
            List<DeviceBenefits> list = (List) obj;
            if (koq.b(list)) {
                LogUtil.c("EquityUtils", "deviceBenefitsList  =  null ");
                return;
            }
            LogUtil.a("EquityUtils", "startTime : ", Long.valueOf(j), ", showDialogCount : ", Integer.valueOf(i2), ", tag : ", Integer.valueOf(i), ", deviceBenefitsList== ", Integer.valueOf(list.size()));
            for (DeviceBenefits deviceBenefits : list) {
                List<DeviceInboxInfo> inboxInfos = deviceBenefits.getInboxInfos();
                LogUtil.a("EquityUtils", "inboxInfos  = ", inboxInfos);
                if (!koq.b(inboxInfos)) {
                    Iterator<DeviceInboxInfo> it = inboxInfos.iterator();
                    while (it.hasNext()) {
                        c(it.next(), context, i, j, i2);
                    }
                }
                List<DevicePerfPurchaseInfo> perfPurchaseInfos = deviceBenefits.getPerfPurchaseInfos();
                if (!koq.b(perfPurchaseInfos)) {
                    for (DevicePerfPurchaseInfo devicePerfPurchaseInfo : perfPurchaseInfos) {
                        LogUtil.a("EquityUtils", "purchaseInfo  = ", devicePerfPurchaseInfo);
                        c(devicePerfPurchaseInfo, context, i, j, i2);
                    }
                }
            }
            return;
        }
        LogUtil.a("EquityUtils", "objData  = ", null);
    }

    public static void e(int i, Context context) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("equityType", Integer.valueOf(i));
        String value = AnalyticsValue.VIBRANT_LIFE_AND_EQUITY_NO_LOGIN_EVENT.value();
        ixx.d().d(context, value, hashMap, 0);
        LogUtil.c("EquityUtils", i + ", equityType," + value);
    }

    public static void b(int i, Context context, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("equityType", Integer.valueOf(i));
        hashMap.put("event", Integer.valueOf(i2));
        String value = AnalyticsValue.VIBRANT_LIFE_AND_EQUITY_CLICK_EVENT.value();
        ixx.d().d(context, value, hashMap, 0);
        LogUtil.c("EquityUtils", i + ", equityType," + value + "event," + i2);
    }

    public static void a(int i, Context context, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("equityType", Integer.valueOf(i));
        hashMap.put("duration", Integer.valueOf(i2));
        String value = AnalyticsValue.VIBRANT_LIFE_AND_EQUITY_SHOWTIME_EVENT.value();
        ixx.d().d(context, value, hashMap, 0);
        LogUtil.c("EquityUtils", i + ", equityType," + value + "duration," + i2);
    }

    public static void c(BaseDeviceBenefitInfo baseDeviceBenefitInfo, Context context, int i, long j, int i2) {
        int benefitType = baseDeviceBenefitInfo.getBenefitType();
        LogUtil.a("EquityUtils", "benefitType== ", Integer.valueOf(benefitType));
        if (benefitType != 2) {
            return;
        }
        int activeStatus = baseDeviceBenefitInfo.getActiveStatus();
        boolean c = c(baseDeviceBenefitInfo.getEffectiveStartTime(), baseDeviceBenefitInfo.getEffectiveEndTime());
        int i3 = 1;
        if (activeStatus == 1 && c) {
            e(1, context);
            if (i == 1) {
                i3 = 3;
            } else if (i != 2) {
                LogUtil.h("EquityUtils", "tag error");
                return;
            }
            b = a(7);
            LogUtil.a("EquityUtils", "startTime：", Long.valueOf(j), " intervalDays:", Long.valueOf(b), " showDialogCount:", Integer.valueOf(i2), " maxTimes:", Integer.valueOf(i3));
            if (j == 0 || (j < b && i2 < i3)) {
                a(baseDeviceBenefitInfo, context, i, i2);
            }
        }
    }

    private static void a(final BaseDeviceBenefitInfo baseDeviceBenefitInfo, final Context context, final int i, final int i2) {
        String e = e(baseDeviceBenefitInfo);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("EquityUtils", "get empty background");
            return;
        }
        f16548a = System.currentTimeMillis();
        final Drawable cIb_ = nrf.cIb_(context, e);
        HandlerExecutor.e(new Runnable() { // from class: qqx
            @Override // java.lang.Runnable
            public final void run() {
                qqs.dHA_(context, baseDeviceBenefitInfo, cIb_, i2, i);
            }
        });
    }

    private static String e(BaseDeviceBenefitInfo baseDeviceBenefitInfo) {
        if (baseDeviceBenefitInfo instanceof DeviceInboxInfo) {
            return ((DeviceInboxInfo) baseDeviceBenefitInfo).getIntroductionUrl();
        }
        if (baseDeviceBenefitInfo instanceof DevicePerfPurchaseInfo) {
            return ((DevicePerfPurchaseInfo) baseDeviceBenefitInfo).getBackgroundUrl();
        }
        LogUtil.b("EquityUtils", "getBackgroundUrl unknown deviceBenefitInfo type ", baseDeviceBenefitInfo);
        return "";
    }

    public static void e(Context context, String str, int i) {
        String newPathConcat = OperationUtils.newPathConcat(str, "from", String.valueOf(6));
        if (i == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("url", newPathConcat);
            Intent createWebViewIntent = bzs.e().createWebViewIntent(context, bundle, null);
            if (createWebViewIntent != null && context != null) {
                try {
                    context.startActivity(createWebViewIntent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("EquityUtils", "ActivityNotFoundException", e.getMessage());
                }
                LogUtil.a("EquityUtils", "clickDeviceBenefit jump to webviewActivity");
                return;
            }
            LogUtil.h("EquityUtils", "clickDeviceBenefit h5 intent is null");
            return;
        }
        if (i == 2) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(newPathConcat));
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            if (context != null) {
                try {
                    context.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException e2) {
                    LogUtil.b("EquityUtils", "ActivityNotFoundException", e2.getMessage());
                    return;
                }
            }
            LogUtil.h("EquityUtils", "clickDeviceBenefit context is null");
            return;
        }
        LogUtil.h("EquityUtils", "clickDeviceInboxInfo error linkType");
    }
}
