package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.trade.datatype.BaseDeviceBenefitInfo;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import com.huawei.trade.datatype.Product;
import com.huawei.ui.commonui.dialog.VibrantLifeDialog;
import com.huawei.ui.main.R$string;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class qrm {
    private static long c;

    public static void d(Object obj, Context context) {
        if (obj instanceof List) {
            List<DeviceBenefits> list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("VibrantLifeMemberUtils", "deviceBenefitsList is empty!");
                return;
            }
            LogUtil.a("VibrantLifeMemberUtils", "deviceBenefitsList size = ", Integer.valueOf(list.size()));
            for (DeviceBenefits deviceBenefits : list) {
                List<DeviceInboxInfo> inboxInfos = deviceBenefits.getInboxInfos();
                if (!koq.b(inboxInfos)) {
                    Iterator<DeviceInboxInfo> it = inboxInfos.iterator();
                    while (it.hasNext()) {
                        c(it.next(), context, deviceBenefits.getDeviceSn());
                    }
                }
                List<DevicePerfPurchaseInfo> perfPurchaseInfos = deviceBenefits.getPerfPurchaseInfos();
                if (!koq.b(perfPurchaseInfos)) {
                    Iterator<DevicePerfPurchaseInfo> it2 = perfPurchaseInfos.iterator();
                    while (it2.hasNext()) {
                        c(it2.next(), context, deviceBenefits.getDeviceSn());
                    }
                }
            }
            return;
        }
        LogUtil.a("VibrantLifeMemberUtils", "objData", null);
    }

    private static void c(BaseDeviceBenefitInfo baseDeviceBenefitInfo, Context context, String str) {
        if (baseDeviceBenefitInfo.getBenefitType() == 1 && baseDeviceBenefitInfo.getActiveStatus() == 1 && e(baseDeviceBenefitInfo.getEffectiveStartTime(), baseDeviceBenefitInfo.getEffectiveEndTime())) {
            d(baseDeviceBenefitInfo, context, str);
        }
    }

    public static void d(BaseDeviceBenefitInfo baseDeviceBenefitInfo, Context context, String str) {
        LogUtil.a("VibrantLifeMemberUtils", "benefitType,", Integer.valueOf(baseDeviceBenefitInfo.getBenefitType()), "deviceBenefitInfo, ", baseDeviceBenefitInfo);
        int activeStatus = baseDeviceBenefitInfo.getActiveStatus();
        boolean e = e(baseDeviceBenefitInfo.getEffectiveStartTime(), baseDeviceBenefitInfo.getEffectiveEndTime());
        LogUtil.a("VibrantLifeMemberUtils", "deviceBenefitInfo,", Integer.valueOf(activeStatus), Boolean.valueOf(e));
        if (activeStatus == 1 && e) {
            qqs.e(2, context);
            boolean z = baseDeviceBenefitInfo instanceof DeviceInboxInfo;
            long b = gpn.b(z, str);
            LogUtil.a("VibrantLifeMemberUtils", "ShowBenefitDialog isInbox:", Boolean.valueOf(z), " lastShowDialogTime:", Long.valueOf(b));
            if (!rsr.c(b, System.currentTimeMillis()) || dpx.d(z, str)) {
                return;
            }
            c(context, baseDeviceBenefitInfo, z, str);
        }
    }

    private static void c(Context context, BaseDeviceBenefitInfo baseDeviceBenefitInfo, boolean z, String str) {
        if (context == null || ((context instanceof Activity) && ((Activity) context).isDestroyed())) {
            LogUtil.b("VibrantLifeMemberUtils", "startShowDialog failed, cause app has been destroyed!");
            return;
        }
        c = System.currentTimeMillis();
        b(context, baseDeviceBenefitInfo, z, str);
        gpn.a(z, str);
    }

    private static void b(final Context context, final BaseDeviceBenefitInfo baseDeviceBenefitInfo, final boolean z, final String str) {
        String e;
        int a2;
        String backgroundUrl;
        String e2;
        if (baseDeviceBenefitInfo instanceof DeviceInboxInfo) {
            DeviceInboxInfo deviceInboxInfo = (DeviceInboxInfo) baseDeviceBenefitInfo;
            e = gpp.e(deviceInboxInfo);
            backgroundUrl = deviceInboxInfo.getIntroductionUrl();
            a2 = 1;
        } else if (baseDeviceBenefitInfo instanceof DevicePerfPurchaseInfo) {
            DevicePerfPurchaseInfo devicePerfPurchaseInfo = (DevicePerfPurchaseInfo) baseDeviceBenefitInfo;
            e = gpp.e(devicePerfPurchaseInfo);
            a2 = gpn.a(devicePerfPurchaseInfo);
            backgroundUrl = devicePerfPurchaseInfo.getBackgroundUrl();
        } else {
            LogUtil.b("VibrantLifeMemberUtils", "showVibrantLifeDialog unknown deviceBenefitInfo type ", baseDeviceBenefitInfo);
            return;
        }
        if (e != null) {
            e2 = gpn.e(e, a2);
        } else {
            e2 = gpn.e(Product.YEAR_DURATION_FLAG, a2);
        }
        final String str2 = e2;
        qqs.b(2, context, 0);
        final Drawable cIb_ = nrf.cIb_(context, backgroundUrl);
        HandlerExecutor.e(new Runnable() { // from class: qrt
            @Override // java.lang.Runnable
            public final void run() {
                qrm.dHN_(context, baseDeviceBenefitInfo, cIb_, str2, z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dHN_(final Context context, BaseDeviceBenefitInfo baseDeviceBenefitInfo, Drawable drawable, String str, boolean z, String str2) {
        int i;
        final VibrantLifeDialog vibrantLifeDialog = new VibrantLifeDialog(context);
        if (drawable == null) {
            LogUtil.h("VibrantLifeMemberUtils", "drawable is null");
        }
        vibrantLifeDialog.czX_(drawable);
        String format = String.format(Locale.ENGLISH, context.getResources().getString(R$string.IDS_equity_first), baseDeviceBenefitInfo.getDeviceName());
        Locale locale = Locale.ENGLISH;
        Resources resources = context.getResources();
        if (z) {
            i = R$string.IDS_user_vibrant_life_content;
        } else {
            i = R$string.IDS_user_vibrant_life_content_not_free;
        }
        String format2 = String.format(locale, resources.getString(i), str);
        LogUtil.a("VibrantLifeMemberUtils", "showVibrantLifeDialog,", format, format2);
        vibrantLifeDialog.d(format);
        vibrantLifeDialog.c(format2);
        final String linkValue = baseDeviceBenefitInfo.getLinkValue();
        final int linkType = baseDeviceBenefitInfo.getLinkType();
        qqs.a(2, context, (int) (System.currentTimeMillis() - c));
        vibrantLifeDialog.czY_(new View.OnClickListener() { // from class: qrm.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                qqs.b(2, context, 1);
                qrm.a(context, linkValue, linkType);
                vibrantLifeDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        vibrantLifeDialog.enqueueShowing(vibrantLifeDialog);
        dpx.b(z, str2);
        ObserverManagerUtil.c("EQUITY_AND_VIBRANT", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, String str, int i) {
        String newPathConcat = OperationUtils.newPathConcat(str, "from", String.valueOf(8));
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
                    LogUtil.b("VibrantLifeMemberUtils", "ActivityNotFoundException", e.getMessage());
                    return;
                }
            }
            LogUtil.h("VibrantLifeMemberUtils", "clickDeviceInboxInfo error linkType");
        }
    }

    public static boolean e(long j, long j2) {
        long h = jec.h();
        return h > j && h < j2;
    }
}
