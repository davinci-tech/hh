package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.openservice.ui.OpenServiceActivity;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pfh {
    private static NoTitleCustomAlertDialog b = null;
    private static String c = "";
    private static CustomTextAlertDialog e;

    /* renamed from: a, reason: collision with root package name */
    private static final int[][] f16110a = {new int[]{1, 2, 3, 4, 5, 10, 11, 12, 14}, new int[]{1, 2, 3, 4, 5, 14}, new int[]{6, 14}, new int[]{1, 2, 3, 4, 5, 6, 7, 9, 11, 14}, new int[]{2, 4, 8, 9, 14}};
    private static int[] d = new int[5];

    static {
        for (int i = 0; i < f16110a.length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr = f16110a[i];
                if (i2 < iArr.length) {
                    i3 += b(iArr[i2]);
                    i2++;
                }
            }
            d[i] = i3;
        }
    }

    public static boolean e(int i, int i2) {
        if (i < 1 || i > f16110a.length) {
            LogUtil.h("isCapabilitySupported() moduleType is not valid. please check.", new Object[0]);
            return false;
        }
        if (i2 < 1 || i2 > 32) {
            LogUtil.h("isCapabilitySupported() currentLayout is not valid. please check.", new Object[0]);
            return false;
        }
        int b2 = b(i2);
        return (d[i - 1] & b2) == b2;
    }

    private static int b(int i) {
        if (i < 1 || i > 32) {
            LogUtil.h("ConfiguredUtils", "convertNumber() number is outof bounds, please check.");
            return -1;
        }
        return (int) Math.pow(2.0d, i - 1);
    }

    public static void e(Context context, cdu cduVar) {
        if (cduVar == null || context == null) {
            LogUtil.h("ConfiguredUtils", "goToDetailActivity itemObject is null or context is null.");
            return;
        }
        a(cduVar.r());
        String e2 = cduVar.e();
        int n = cduVar.n();
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("ConfiguredUtils", "goToDetailActivity detailUrl is isEmpty.");
            return;
        }
        String trim = e2.trim();
        LogUtil.a("ConfiguredUtils", "goToDetailActivity serviceUrlType = ", Integer.valueOf(n));
        if (trim.contains("h5pro=true")) {
            n = 3;
        }
        if (n == 0) {
            d(context, trim, cduVar);
            return;
        }
        if (n == 1) {
            b(context, trim, cduVar);
            return;
        }
        if (n == 2) {
            mxv.d(context, trim);
        } else if (n == 3) {
            d(context, trim);
        } else {
            d(context, trim, cduVar);
        }
    }

    private static void a(final int i) {
        if (i <= 0) {
            LogUtil.h("ConfiguredUtils", "reportInfoReadNumber() informationId is empty.");
            return;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("messageCenterUrl");
        c = url;
        if (TextUtils.isEmpty(url)) {
            LogUtil.h("ConfiguredUtils", "reportInfoReadNumber() MESSAGE_CENTER_KEY sMessageCenterDomainUrl is empty,try again.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pfh.4
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject = new JSONObject();
                    AutoCloseable autoCloseable = null;
                    try {
                        try {
                            jSONObject.put("id", i);
                            Response d2 = jbj.d(pfh.c + "/messageCenter/information/readCountPlusOne", jSONObject);
                            int code = d2.getCode();
                            if (code != 200 || d2.getBody() == null) {
                                LogUtil.h("ConfiguredUtils", "getRequestResult responseCode = ", Integer.valueOf(code));
                            } else {
                                LogUtil.a("ConfiguredUtils", "getRequestResult success");
                            }
                            if (d2 != null) {
                                try {
                                    d2.close();
                                } catch (IOException e2) {
                                    LogUtil.b("ConfiguredUtils", "IOException: ", e2);
                                }
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                try {
                                    autoCloseable.close();
                                } catch (IOException e3) {
                                    LogUtil.b("ConfiguredUtils", "IOException: ", e3);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException | JSONException unused) {
                        LogUtil.b("ConfiguredUtils", "reportInfoReadNumber meet json exception or ioexception.");
                        if (0 != 0) {
                            try {
                                autoCloseable.close();
                            } catch (IOException e4) {
                                LogUtil.b("ConfiguredUtils", "IOException: ", e4);
                            }
                        }
                    }
                }
            });
        }
    }

    public static void d(Context context, String str) {
        Activity activity = BaseApplication.getActivity();
        if (activity == null || TextUtils.isEmpty(str)) {
            LogUtil.h("ConfiguredUtils", "jumpToH5Pro() activity is null or detailUrl is empty.");
            return;
        }
        if (context == null) {
            LogUtil.h("ConfiguredUtils", "jumpToH5Pro() context is null");
            return;
        }
        if (!CommonUtil.aa(context)) {
            h();
            return;
        }
        String trim = str.trim();
        H5proUtil.initH5pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule(RemoteMessageConst.NOTIFICATION, bzs.e().getCommonJsModule(RemoteMessageConst.NOTIFICATION)).addCustomizeJsModule("notificationAudioControl", bzs.e().getCommonJsModule("notificationAudioControl")).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement")).build();
        if (a(trim)) {
            LogUtil.a("ConfiguredUtils", "jumpToH5Application() is url.");
            H5ProClient.startH5LightApp(activity, trim, build);
        } else {
            LogUtil.a("ConfiguredUtils", "jumpToH5Application() is h5 mini program.");
            H5ProClient.startH5MiniProgram(activity, trim, build);
        }
    }

    private static boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith("http://") || str.startsWith("https://") || str.startsWith(Constants.PREFIX_FILE);
        }
        LogUtil.h("ConfiguredUtils", "isUrlScheme() serviceDetailUrl is empty.");
        return false;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x005b
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private static void b(android.content.Context r4, java.lang.String r5, defpackage.cdu r6) {
        /*
            java.lang.String r0 = "enter jumpToApk"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "ConfiguredUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            boolean r0 = defpackage.jdm.b(r4, r5)
            if (r0 == 0) goto L68
            int r0 = r6.v()
            com.huawei.hwcommonmodel.constants.AnalyticsValue r2 = com.huawei.hwcommonmodel.constants.AnalyticsValue.HEALTH_TO_THIRD_PART_APP_2041080
            java.lang.String r2 = r2.value()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            e(r5, r0, r2)
            java.lang.String r6 = r6.q()
            if (r6 == 0) goto L2c
            java.lang.String r6 = r6.trim()
        L2c:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: android.content.ActivityNotFoundException -> L5b
            if (r0 == 0) goto L36
            b(r4, r5)     // Catch: android.content.ActivityNotFoundException -> L5b
            goto L74
        L36:
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: android.content.ActivityNotFoundException -> L5b
            java.lang.String r2 = "enter scheme skip"
            r3 = 0
            r0[r3] = r2     // Catch: android.content.ActivityNotFoundException -> L5b
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)     // Catch: android.content.ActivityNotFoundException -> L5b
            android.content.Intent r0 = new android.content.Intent     // Catch: android.content.ActivityNotFoundException -> L5b
            r0.<init>()     // Catch: android.content.ActivityNotFoundException -> L5b
            java.lang.String r2 = "android.intent.action.VIEW"
            r0.setAction(r2)     // Catch: android.content.ActivityNotFoundException -> L5b
            r2 = 268435456(0x10000000, float:2.524355E-29)
            r0.setFlags(r2)     // Catch: android.content.ActivityNotFoundException -> L5b
            android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch: android.content.ActivityNotFoundException -> L5b
            r0.setData(r6)     // Catch: android.content.ActivityNotFoundException -> L5b
            r4.startActivity(r0)     // Catch: android.content.ActivityNotFoundException -> L5b
            goto L74
        L5b:
            java.lang.String r6 = "jumpToApk Exception: not found"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)
            b(r4, r5)
            goto L74
        L68:
            java.lang.String r0 = "thirdPart_setOnClickListener detailUrl is not install,jump to Market."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
            e(r4, r5, r6)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pfh.b(android.content.Context, java.lang.String, cdu):void");
    }

    private static void b(Context context, String str) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("ConfiguredUtils", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private static void d(Context context, String str, cdu cduVar) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ConfiguredUtils", "jumpToWebView() detailUrl is empty.");
            return;
        }
        String trim = str.trim();
        try {
            if (trim.contains("localeUrl:moduleName=openService")) {
                Intent intent = new Intent(context, (Class<?>) OpenServiceActivity.class);
                intent.addFlags(268435456);
                context.startActivity(intent);
            } else if (trim.startsWith("huaweischeme://")) {
                Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(trim));
                intent2.setPackage(context.getPackageName());
                intent2.addFlags(268435456);
                jdw.bGh_(intent2, context);
            } else {
                Intent intent3 = new Intent(context, (Class<?>) WebViewActivity.class);
                intent3.setFlags(268435456);
                intent3.putExtra("url", str);
                intent3.putExtra("EXTRA_BI_ID", String.valueOf(cduVar.j()));
                intent3.putExtra("EXTRA_BI_NAME", cduVar.o());
                intent3.putExtra("EXTRA_BI_SOURCE", "CONFIGURE_PAGE_CARD");
                intent3.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
                context.startActivity(intent3);
            }
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("ConfiguredUtils", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private static void e(Context context, String str, cdu cduVar) {
        LogUtil.a("ConfiguredUtils", "enter jumpToMarket");
        String ab = cduVar.ab();
        String aa = cduVar.aa();
        int v = cduVar.v();
        String configuredContents = new pfd().c(cduVar.v()).getConfiguredContents(aa);
        if (TextUtils.isEmpty(configuredContents)) {
            int v2 = cduVar.v();
            e(str, String.valueOf(v2), AnalyticsValue.HEALTH_TO_APP_MARKET_2041084.value());
            c(context, str, ab);
            return;
        }
        b(configuredContents, str, ab, String.valueOf(v));
    }

    private static void h() {
        LogUtil.a("ConfiguredUtils", "ENTER showNetworkErrorDialog");
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(activity.getResources().getString(R$string.IDS_hwh_ali_sport_net_error)).czA_(activity.getResources().getString(R$string.IDS_user_permission_know), new View.OnClickListener() { // from class: pfh.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pfh.b.dismiss();
                NoTitleCustomAlertDialog unused = pfh.b = null;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        b = e2;
        e2.setCancelable(false);
        b.show();
    }

    private static void b(String str, final String str2, final String str3, final String str4) {
        LogUtil.a("ConfiguredUtils", "enter showInstallDialog");
        final Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(activity).b(activity.getResources().getString(R$string.IDS_device_release_user_profile_log_privacy_tip_title)).e(str).cyU_(R$string.IDS_hw_watchface_go_hms_install, new View.OnClickListener() { // from class: pfh.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pfh.e.dismiss();
                CustomTextAlertDialog unused = pfh.e = null;
                pfh.e(str2, str4, AnalyticsValue.HEALTH_CONFIRM_TO_THIRD_PART_APP_2041082.value());
                pfh.c(activity, str2, str3);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: pfh.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pfh.e.dismiss();
                CustomTextAlertDialog unused = pfh.e = null;
                pfh.e(str2, str4, AnalyticsValue.HEALTH_CANCEL_TO_THIRD_PART_APP_2041081.value());
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        e = a2;
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str, String str2) {
        if (jdm.b(context, "com.huawei.appmarket")) {
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + str));
                intent.addFlags(268468224);
                intent.setPackage("com.huawei.appmarket");
                dot_(context, intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("ConfiguredUtils", "showInstallDialog Exception: not found");
                return;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("ConfiguredUtils", "weburl is empty");
            String string = context.getString(R$string.IDS_main_sns_app_store_content);
            if (nsn.ae(BaseApplication.getContext())) {
                string = context.getString(R$string.IDS_main_sns_app_content_pad);
            }
            nrh.d(context, string);
            return;
        }
        Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str2));
        intent2.setFlags(268435456);
        try {
            context.startActivity(intent2);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("ConfiguredUtils", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private static void dot_(Context context, Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("ConfiguredUtils", "ActivityNotFoundException", e2.getMessage());
        }
    }

    public static long g() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.getTimeInMillis();
    }

    public static List<cdu> c(int i, int i2, List<cdu> list) {
        if (koq.b(list)) {
            LogUtil.h("ConfiguredUtils", "getConfiguredDataIsShown list is empty.");
            return null;
        }
        switch (i) {
            case 1:
                return d(list, 1);
            case 2:
                return b(list, 2);
            case 3:
            case 9:
                return d(list, 3);
            case 4:
                return b(list, 4);
            case 5:
                return d(list, 6);
            case 6:
            case 7:
            case 11:
            case 12:
                return list;
            case 8:
                return d(list, 2);
            case 10:
                int size = list.size();
                if (size > 4) {
                    return list.subList(0, 4);
                }
                return size < 3 ? new ArrayList(10) : list;
            case 13:
            default:
                return new ArrayList(10);
            case 14:
                return b(list, 6);
        }
    }

    private static List<cdu> d(List<cdu> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("ConfiguredUtils", "getConfiguredPageShowList() list is empty.");
            return arrayList;
        }
        if (list.size() >= i) {
            return list.subList(0, i);
        }
        LogUtil.h("ConfiguredUtils", "getConfiguredPageShowList() ");
        return arrayList;
    }

    private static List<cdu> b(List<cdu> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("ConfiguredUtils", "getHorizonThreeGirdShowList() list is empty.");
            return arrayList;
        }
        if (list.size() >= i) {
            return list.subList(0, i);
        }
        LogUtil.h("ConfiguredUtils", "getHorizonThreeGirdShowList() ");
        return list;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int b(Context context, int i, int i2) {
        int c2 = nsn.c(context, 8.0f);
        int i3 = 1;
        int i4 = 2;
        int i5 = 4;
        switch (i) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 11:
                break;
            case 2:
            case 4:
                c2 = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                i3 = 2;
                i4 = i5;
                break;
            case 3:
            case 5:
                c2 = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                i3 = 3;
                i4 = i3;
                break;
            case 9:
                i5 = 3;
                i3 = 2;
                i4 = i5;
                break;
            case 10:
            case 13:
            default:
                i4 = i3;
                break;
            case 12:
                c2 = nsn.c(context, 16.0f);
                i3 = 4;
                i4 = i3;
                break;
            case 14:
                c2 = context.getResources().getDimensionPixelSize(R.dimen._2131362589_res_0x7f0a031d);
                i4 = 5;
                i3 = 3;
                break;
        }
        return b(context, i, i3, i4, c2);
    }

    private static int b(Context context, int i, int i2, int i3, int i4) {
        int c2 = c();
        if (i2 < 1 || i3 < 1) {
            return c2;
        }
        int dimensionPixelSize = i == 14 ? context.getResources().getDimensionPixelSize(R.dimen._2131362581_res_0x7f0a0315) : 0;
        if (nsn.ag(BaseApplication.getContext())) {
            return ((c2 - ((i3 - 1) * nrr.b(context))) - dimensionPixelSize) / i3;
        }
        return ((c2 - ((i2 - 1) * i4)) - dimensionPixelSize) / i2;
    }

    public static int c() {
        Context activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("ConfiguredUtils", "getActivity = null");
            activity = BaseApplication.getContext();
        }
        int h = nsn.h(activity);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        float f = h;
        try {
            float dimension = activity.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
            return (int) ((((f - dimension) - activity.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue());
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("ConfiguredUtils", "getItemsTotalWidth dimen id is not found.");
            return h;
        }
    }

    public static void e(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("packageName", str);
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, str2);
        ixx.d().d(BaseApplication.getContext(), str3, hashMap, 0);
    }

    public static int d(int i, boolean z, int i2, int i3) {
        switch (i) {
            case 1:
            case 7:
            case 8:
            case 9:
            case 11:
                if (!z) {
                    i3 = 0;
                    break;
                }
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                if (!z) {
                    i3 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                    break;
                }
                break;
            case 10:
                if (i2 == 3) {
                    return nsn.c(BaseApplication.getContext(), 40.0f);
                }
                if (i2 == 4) {
                    return 0;
                }
                LogUtil.h("ConfiguredUtils", "LAYOUT_DISCOVER_ICON cardItemSize = ", Integer.valueOf(i2));
                return 0;
            case 12:
                int b2 = b(12, z, i2);
                return (c() - nsn.c(BaseApplication.getContext(), b2 * 64.0f)) / (b2 - 1);
            case 13:
            default:
                return 0;
            case 14:
                if (!z) {
                    i3 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362589_res_0x7f0a031d);
                    break;
                }
                break;
        }
        return i3;
    }

    public static int e(int i) {
        if (i == 4 || i == 5 || i == 6 || i == 8 || i == 11) {
            return BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        }
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
    
        if (r4 != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(int r3, boolean r4, int r5) {
        /*
            r0 = 1
            r1 = 3
            r2 = 4
            switch(r3) {
                case 1: goto L2a;
                case 2: goto L27;
                case 3: goto L25;
                case 4: goto L27;
                case 5: goto L25;
                case 6: goto L2a;
                case 7: goto L2a;
                case 8: goto L2a;
                case 9: goto L23;
                case 10: goto Lc;
                case 11: goto L2a;
                case 12: goto L7;
                case 13: goto L6;
                case 14: goto L25;
                default: goto L6;
            }
        L6:
            goto L2d
        L7:
            if (r4 == 0) goto L11
            r0 = 8
            goto L2d
        Lc:
            if (r5 != r1) goto Lf
            goto L25
        Lf:
            if (r5 != r2) goto L13
        L11:
            r0 = r2
            goto L2d
        L13:
            java.lang.String r3 = "getLayoutGridNumber LAYOUT_DISCOVER_ICON cardItemSize = "
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            java.lang.String r4 = "ConfiguredUtils"
            com.huawei.hwlogsmodel.LogUtil.h(r4, r3)
            goto L2d
        L23:
            if (r4 == 0) goto L2d
        L25:
            r0 = r1
            goto L2d
        L27:
            if (r4 == 0) goto L2c
            goto L11
        L2a:
            if (r4 == 0) goto L2d
        L2c:
            r0 = 2
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pfh.b(int, boolean, int):int");
    }

    public static int[] b(int i, int i2) {
        int[] iArr = new int[4];
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (i2 == 7) {
            iArr[0] = 0;
            iArr[2] = 0;
            iArr[1] = nsn.c(BaseApplication.getContext(), 4.0f);
            iArr[3] = nsn.c(BaseApplication.getContext(), 4.0f);
        } else {
            iArr[1] = 0;
            iArr[3] = 0;
            iArr[0] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) safeRegionWidth.first).intValue();
            iArr[2] = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) safeRegionWidth.second).intValue();
        }
        return iArr;
    }

    public static BaseActivity d() {
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof BaseActivity) {
            return (BaseActivity) activity;
        }
        return null;
    }
}
