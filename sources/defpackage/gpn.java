package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.vip.datatypes.MemberMessage;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.health.vip.datatypes.VipMessageInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.trade.datatype.BaseDeviceBenefitInfo;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

/* loaded from: classes4.dex */
public class gpn {
    private gpn() {
    }

    public static boolean d(UserMemberInfo userMemberInfo) {
        return userMemberInfo != null && userMemberInfo.getExpireTime() < userMemberInfo.getNowTime();
    }

    public static String b(VipMessageInfo vipMessageInfo, boolean z) {
        if (vipMessageInfo == null) {
            LogUtil.h("VipUtils", "getMessageContent vipMessageInfo is null");
            return "";
        }
        LogUtil.a("VipUtils", "OperateType:", Integer.valueOf(vipMessageInfo.getOperateType()));
        return e(vipMessageInfo, z);
    }

    private static String g() {
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1002);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ef A[Catch: all -> 0x014f, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x000b, B:7:0x0010, B:9:0x0015, B:10:0x0018, B:11:0x001b, B:15:0x00cd, B:16:0x00f4, B:17:0x0108, B:18:0x011c, B:19:0x0121, B:20:0x0126, B:21:0x0065, B:22:0x0072, B:23:0x0083, B:25:0x008b, B:26:0x00c5, B:27:0x00a5, B:28:0x0042, B:29:0x00ef, B:30:0x0021, B:31:0x003c), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(com.huawei.health.vip.datatypes.VipMessageInfo r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gpn.e(com.huawei.health.vip.datatypes.VipMessageInfo, boolean):java.lang.String");
    }

    private static String d(VipMessageInfo vipMessageInfo, boolean z) {
        String a2 = a(vipMessageInfo.getMemberType());
        String g = g();
        if (z) {
            return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845641_res_0x7f021fc9), a2);
        }
        return String.format(Locale.ENGLISH, nsf.h(R.string._2130847555_res_0x7f022743), g, c(vipMessageInfo.getExpireTime()));
    }

    private static String c(long j) {
        String a2 = UnitUtil.a("yyyy-MM-dd", j);
        if (LanguageUtil.bn(com.huawei.haf.application.BaseApplication.e())) {
            return jec.g(a2);
        }
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            return jec.c(a2);
        }
        if (LanguageUtil.bp(BaseApplication.getContext()) || LanguageUtil.bt(BaseApplication.getContext())) {
            return jec.d(a2);
        }
        return LanguageUtil.am(BaseApplication.getContext()) ? jec.a(a2) : a2;
    }

    public static boolean c(List<DeviceBenefits> list) {
        if (koq.b(list)) {
            return false;
        }
        for (DeviceBenefits deviceBenefits : list) {
            if (deviceBenefits != null && (e(deviceBenefits.getInboxInfos()) || a(deviceBenefits.getPerfPurchaseInfos()))) {
                return true;
            }
        }
        return false;
    }

    private static boolean e(List<DeviceInboxInfo> list) {
        if (koq.b(list)) {
            return false;
        }
        for (DeviceInboxInfo deviceInboxInfo : list) {
            if (b(deviceInboxInfo) && !TextUtils.isEmpty(gpp.e(deviceInboxInfo)) && deviceInboxInfo.getBenefitType() == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(List<DevicePerfPurchaseInfo> list) {
        if (koq.b(list)) {
            return false;
        }
        for (DevicePerfPurchaseInfo devicePerfPurchaseInfo : list) {
            if (b(devicePerfPurchaseInfo) && !TextUtils.isEmpty(gpp.e(devicePerfPurchaseInfo))) {
                return true;
            }
        }
        return false;
    }

    public static List<LinearLayout> d(Context context, List<DeviceBenefits> list, boolean z) {
        LinearLayout aRr_;
        LinearLayout aRr_2;
        ArrayList arrayList = new ArrayList(10);
        if (!koq.b(list) && context != null) {
            for (DeviceBenefits deviceBenefits : list) {
                if (deviceBenefits != null) {
                    List<DeviceInboxInfo> inboxInfos = deviceBenefits.getInboxInfos();
                    if (koq.c(inboxInfos)) {
                        for (DeviceInboxInfo deviceInboxInfo : inboxInfos) {
                            if (b(deviceInboxInfo) && !TextUtils.isEmpty(gpp.e(deviceInboxInfo)) && deviceInboxInfo.getBenefitType() == 1) {
                                if (z) {
                                    aRr_2 = aRq_(context, deviceInboxInfo, R.string._2130845202_res_0x7f021e12, gpp.e(deviceInboxInfo), 0);
                                } else {
                                    aRr_2 = aRr_(context, deviceInboxInfo, R.string._2130845202_res_0x7f021e12, gpp.e(deviceInboxInfo), 0);
                                }
                                if (aRr_2 != null) {
                                    arrayList.add(aRr_2);
                                }
                            }
                        }
                    }
                    List<DevicePerfPurchaseInfo> perfPurchaseInfos = deviceBenefits.getPerfPurchaseInfos();
                    if (koq.c(perfPurchaseInfos)) {
                        for (DevicePerfPurchaseInfo devicePerfPurchaseInfo : perfPurchaseInfos) {
                            if (b(devicePerfPurchaseInfo) && !TextUtils.isEmpty(gpp.e(devicePerfPurchaseInfo))) {
                                if (z) {
                                    aRr_ = aRq_(context, devicePerfPurchaseInfo, e(devicePerfPurchaseInfo), gpp.e(devicePerfPurchaseInfo), a(devicePerfPurchaseInfo));
                                } else {
                                    aRr_ = aRr_(context, devicePerfPurchaseInfo, e(devicePerfPurchaseInfo), gpp.e(devicePerfPurchaseInfo), a(devicePerfPurchaseInfo));
                                }
                                if (aRr_ != null) {
                                    arrayList.add(aRr_);
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static LinearLayout aRr_(final Context context, final BaseDeviceBenefitInfo baseDeviceBenefitInfo, int i, String str, int i2) {
        String e = e(str, i2);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("VipUtils", "initMemberTabMsgView benefitInfo = ", baseDeviceBenefitInfo.toString());
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) View.inflate(context, R.layout.member_tab_msg_layout, null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.vip_inbox_message);
        if (context.getResources().getConfiguration().densityDpi > c()) {
            textView.setMaxWidth(nrr.e(context, 170.0f));
        }
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.vip_inbox_actative);
        final ImageView imageView = (ImageView) linearLayout.findViewById(R.id.vip_inbox_red_point);
        LogUtil.c("VipUtils", "initMemberTabMsgView benefitInfo = ", baseDeviceBenefitInfo.toString());
        try {
            String string = BaseApplication.getContext().getResources().getString(i, e);
            textView.setText(string);
            LogUtil.a("VipUtils", "initMemberTabMsgView inbox description = ", string);
        } catch (IllegalFormatConversionException | MissingFormatArgumentException e2) {
            LogUtil.b("VipUtils", "initMemberTabMsgView string format exception", e2.getMessage());
        }
        textView2.setText(R.string._2130845339_res_0x7f021e9b);
        imageView.setVisibility(d(baseDeviceBenefitInfo.getProductId()) ? 0 : 8);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: gpn.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                gpn.a(2, false);
                gpn.a(context, baseDeviceBenefitInfo.getLinkValue(), baseDeviceBenefitInfo.getLinkType(), baseDeviceBenefitInfo.getProductId(), 0);
                imageView.setVisibility(gpn.d(baseDeviceBenefitInfo.getProductId()) ? 0 : 8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(int i, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (z) {
            hashMap.put(CommonUtil.PAGE_TYPE, 3);
        } else {
            hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(((d(a()) ^ true) || ase.c()) ? 2 : 1));
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.VIP_EQUITY_CLICK_EVENT.value(), hashMap, 0);
    }

    public static int c() {
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            Method method = cls.getMethod("getWindowManagerService", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(cls, new Object[0]);
            Method method2 = invoke.getClass().getMethod("getInitialDisplayDensity", Integer.TYPE);
            method2.setAccessible(true);
            return ((Integer) method2.invoke(invoke, 0)).intValue();
        } catch (Exception e) {
            LogUtil.b("VipUtils", "getDefaultDisplayDensity Exception ", ExceptionUtils.d(e));
            return -1;
        }
    }

    private static int e(DevicePerfPurchaseInfo devicePerfPurchaseInfo) {
        return (devicePerfPurchaseInfo == null || !koq.c(devicePerfPurchaseInfo.getOtherServiceList())) ? R.string._2130845202_res_0x7f021e12 : R.string.IDS_device_benefit_to_pick;
    }

    private static LinearLayout aRq_(final Context context, final BaseDeviceBenefitInfo baseDeviceBenefitInfo, int i, String str, int i2) {
        String e = e(str, i2);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("VipUtils", "initBenefitView benefitInfo = ", baseDeviceBenefitInfo.toString());
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) View.inflate(context, R.layout.my_page_inbox_message_layout, null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.vip_inbox_message);
        if (f()) {
            textView.setTextColor(ContextCompat.getColor(context, R.color._2131299312_res_0x7f090bf0));
        } else {
            textView.setTextColor(ContextCompat.getColor(context, R.color._2131298787_res_0x7f0909e3));
        }
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.vip_inbox_red_point);
        LogUtil.c("VipUtils", "initBenefitView benefitInfo = ", baseDeviceBenefitInfo.toString());
        try {
            String string = BaseApplication.getContext().getResources().getString(i, e);
            textView.setText(string);
            LogUtil.a("VipUtils", "initBenefitView inbox description = ", string);
        } catch (IllegalFormatConversionException | MissingFormatArgumentException e2) {
            LogUtil.b("VipUtils", "initBenefitView string format exception", e2.getMessage());
        }
        imageView.setVisibility(d(baseDeviceBenefitInfo.getProductId()) ? 0 : 8);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: gpu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gpn.aRs_(context, baseDeviceBenefitInfo, view);
            }
        });
        a(1, true);
        return linearLayout;
    }

    static /* synthetic */ void aRs_(Context context, BaseDeviceBenefitInfo baseDeviceBenefitInfo, View view) {
        a(context, baseDeviceBenefitInfo.getLinkValue(), baseDeviceBenefitInfo.getLinkType(), baseDeviceBenefitInfo.getProductId(), 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, String str, int i, String str2, int i2) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("VipUtils", "clickDeviceBenefit can not jump");
            return;
        }
        String d = d(str, "from", String.valueOf(i2 == 1 ? 9 : 10));
        if (i == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("url", d);
            Intent createWebViewIntent = bzs.e().createWebViewIntent(context, bundle, null);
            if (createWebViewIntent != null) {
                context.startActivity(createWebViewIntent);
                LogUtil.a("VipUtils", "clickDeviceBenefit jump to webviewActivity");
            } else {
                LogUtil.h("VipUtils", "clickDeviceBenefit h5 intent is null");
            }
        } else if (i == 2) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(d));
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            jdw.bGh_(intent, context);
            LogUtil.a("VipUtils", "clickDeviceBenefit jump to scheme page");
        } else {
            LogUtil.h("VipUtils", "clickDeviceBenefit error linkType");
        }
        if (!TextUtils.isEmpty(str2)) {
            h(str2);
        }
        a(2, true);
    }

    public static String d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("VipUtils", "newPathConcat oldUrl is empty");
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        sb.append(str2);
        sb.append("=");
        sb.append(str3);
        LogUtil.c("VipUtils", "newPathConcat newUrl is ", sb.toString());
        return sb.toString();
    }

    private static void h(String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(30008), "vip_inbox_msg_read_list");
        if (!TextUtils.isEmpty(b)) {
            str = b.concat(",").concat(str);
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(30008), "vip_inbox_msg_read_list", str, (StorageParams) null);
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("VipUtils", "isNeedShowInboxRedPoint productId is empty");
            return false;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(30008), "vip_inbox_msg_read_list");
        return TextUtils.isEmpty(b) || !b.contains(str);
    }

    private static boolean b(BaseDeviceBenefitInfo baseDeviceBenefitInfo) {
        if (baseDeviceBenefitInfo == null || !baseDeviceBenefitInfo.isBenefitInfoValid()) {
            return false;
        }
        LogUtil.h("VipUtils", "isBenefitsNeedShow benefitInfo is empty");
        return true;
    }

    private static String b(VipMessageInfo vipMessageInfo) {
        return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845492_res_0x7f021f34), c(vipMessageInfo.getExpireTime()));
    }

    private static String d(VipMessageInfo vipMessageInfo) {
        return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845587_res_0x7f021f93), a(vipMessageInfo.getDuration()), c(vipMessageInfo.getExpireTime()));
    }

    private static String c(VipMessageInfo vipMessageInfo, String str) {
        return String.format(Locale.ENGLISH, str, c(vipMessageInfo.getExpireTime()));
    }

    private static String f(VipMessageInfo vipMessageInfo) {
        if (vipMessageInfo.getOperateType() == 30007) {
            return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845592_res_0x7f021f98), c(vipMessageInfo.getCreateTime()));
        }
        if (vipMessageInfo.getOperateType() == 30008) {
            return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845593_res_0x7f021f99), c(vipMessageInfo.getExpireTime()));
        }
        if (vipMessageInfo.getOperateType() == 10012) {
            return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130846018_res_0x7f022142), c(vipMessageInfo.getCreateTime()));
        }
        if (vipMessageInfo.getOperateType() == 10013) {
            return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130846019_res_0x7f022143), c(vipMessageInfo.getExpireTime()));
        }
        LogUtil.h("VipUtils", "OperateType is wrong.");
        return "";
    }

    private static String c(VipMessageInfo vipMessageInfo, boolean z) {
        int operateType = vipMessageInfo.getOperateType();
        if (operateType == 10005) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845646_res_0x7f021fce);
        }
        switch (operateType) {
            case 20001:
                if (z) {
                    return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845633_res_0x7f021fc1), e(vipMessageInfo.getUserAccountAnon()));
                }
                return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845634_res_0x7f021fc2), e(vipMessageInfo.getUserAccountAnon()), UnitUtil.e(10.0d, 1, 0));
            case 20002:
                if (z) {
                    return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845635_res_0x7f021fc3), e(vipMessageInfo.getUserAccountAnon()));
                }
                return BaseApplication.getContext().getResources().getString(R.string._2130845636_res_0x7f021fc4);
            case 20003:
                return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845637_res_0x7f021fc5), e(vipMessageInfo.getUserAccountAnon()));
            case 20004:
                return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845638_res_0x7f021fc6), e(vipMessageInfo.getUserAccountAnon()));
            case 20005:
                return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845639_res_0x7f021fc7), e(vipMessageInfo.getUserAccountAnon()));
            default:
                return e(vipMessageInfo);
        }
    }

    private static String e(VipMessageInfo vipMessageInfo) {
        String c = c(vipMessageInfo.getExpireTime());
        String g = g();
        int operateType = vipMessageInfo.getOperateType();
        if (operateType != 10027) {
            switch (operateType) {
                case 10006:
                    return nsf.b(R.string._2130847557_res_0x7f022745, g);
                case 10007:
                    return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845652_res_0x7f021fd4), c);
                case 10008:
                    return String.format(Locale.ENGLISH, nsf.h(R.string._2130847569_res_0x7f022751), g, c);
                case 10009:
                    return a(vipMessageInfo);
                case 10010:
                    String b = b(vipMessageInfo.getResourceTypes());
                    return !TextUtils.isEmpty(b) ? String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string.IDS_device_unbind), b) : b;
                case 10011:
                    return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845654_res_0x7f021fd6), vipMessageInfo.getDeviceType(), d(vipMessageInfo.getInterval()));
                default:
                    LogUtil.a("VipUtils", "getContent getOperateType() not match:", Integer.valueOf(vipMessageInfo.getOperateType()));
                    return "";
            }
        }
        return nsf.b(R.string._2130847572_res_0x7f022754, g, a(vipMessageInfo.getInterval()), c);
    }

    private static String a(VipMessageInfo vipMessageInfo) {
        String b = b(vipMessageInfo.getResourceTypes());
        if (TextUtils.isEmpty(b)) {
            return b;
        }
        if (vipMessageInfo.getResourceTypes().size() > 1) {
            return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string.IDS_device_bound_many), b);
        }
        return String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string.IDS_device_bound), b);
    }

    private static String e(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    private static String a(int i) {
        LogUtil.a("VipUtils", "memberType:", Integer.valueOf(i));
        if (i == 1) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845631_res_0x7f021fbf);
        }
        LogUtil.a("VipUtils", "memberType not match:", Integer.valueOf(i));
        return "";
    }

    private static String b(List<Integer> list) {
        if (koq.b(list)) {
            LogUtil.a("VipUtils", "resourceTypes is empty");
            return "";
        }
        return c(list.get(0).intValue());
    }

    private static String c(int i) {
        LogUtil.a("VipUtils", "resourceType:", Integer.valueOf(i));
        if (i == 1) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845662_res_0x7f021fde);
        }
        if (i == 2) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845663_res_0x7f021fdf);
        }
        if (i == 5) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845664_res_0x7f021fe0);
        }
        if (i == 6) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845665_res_0x7f021fe1);
        }
        if (i == 7) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845666_res_0x7f021fe2);
        }
        if (i == 8) {
            return BaseApplication.getContext().getResources().getString(R.string._2130845667_res_0x7f021fe3);
        }
        LogUtil.h("VipUtils", "resourceType not match:", Integer.valueOf(i));
        return "";
    }

    public static String c(VipMessageInfo vipMessageInfo) {
        if (vipMessageInfo == null) {
            LogUtil.h("VipUtils", "getMessageTitle vipMessageInfo is null");
            return "";
        }
        return h(vipMessageInfo);
    }

    private static String h(VipMessageInfo vipMessageInfo) {
        Resources resources = BaseApplication.getContext().getResources();
        int operateType = vipMessageInfo.getOperateType();
        switch (operateType) {
            case 10001:
                return resources.getString(R.string._2130845640_res_0x7f021fc8);
            case 10002:
            case 10003:
                break;
            default:
                switch (operateType) {
                    case 10005:
                        break;
                    case 10006:
                        return resources.getString(R.string._2130847560_res_0x7f022748);
                    case 10007:
                        return resources.getString(R.string._2130845651_res_0x7f021fd3);
                    case 10008:
                        return resources.getString(R.string._2130845649_res_0x7f021fd1);
                    case 10009:
                        return String.format(Locale.ENGLISH, resources.getString(R.string.IDS_device_bound_title), vipMessageInfo.getDeviceType());
                    case 10010:
                        return String.format(Locale.ENGLISH, resources.getString(R.string.IDS_device_unbind_title), vipMessageInfo.getDeviceType());
                    case 10011:
                        return resources.getString(R.string._2130845653_res_0x7f021fd5);
                    case 10012:
                    case 10013:
                    case PrebakedEffectId.RT_CALENDAR_DATE /* 10014 */:
                    case 10015:
                        break;
                    default:
                        switch (operateType) {
                            case 10024:
                                return resources.getString(R.string._2130846671_res_0x7f0223cf);
                            case 10025:
                                return resources.getString(R.string._2130847853_res_0x7f02286d);
                            case 10026:
                                return nsf.h(R.string._2130847558_res_0x7f022746);
                            case PrebakedEffectId.RT_SPEED_UP /* 10027 */:
                                return nsf.h(R.string._2130847559_res_0x7f022747);
                            default:
                                switch (operateType) {
                                    case 20001:
                                    case 20002:
                                    case 20003:
                                    case 20004:
                                    case 20005:
                                        return resources.getString(R.string._2130845632_res_0x7f021fc0);
                                    default:
                                        switch (operateType) {
                                            case 30002:
                                                return resources.getString(R.string._2130845357_res_0x7f021ead);
                                            case OnRegisterSkinAttrsListener.REGISTER_BY_SCAN /* 30003 */:
                                                return resources.getString(R.string._2130845491_res_0x7f021f33);
                                            case 30004:
                                                return resources.getString(R.string._2130845586_res_0x7f021f92);
                                            case 30005:
                                                return resources.getString(R.string._2130845588_res_0x7f021f94);
                                            case 30006:
                                                return resources.getString(R.string._2130845569_res_0x7f021f81);
                                            case 30007:
                                            case 30008:
                                                break;
                                            case 30009:
                                            case 30010:
                                                return resources.getString(R.string._2130845594_res_0x7f021f9a);
                                            default:
                                                LogUtil.h("VipUtils", "getTitle getOperateType not match:", Integer.valueOf(vipMessageInfo.getOperateType()));
                                                return "";
                                        }
                                }
                        }
                }
                return resources.getString(R.string._2130845591_res_0x7f021f97);
        }
        return resources.getString(R.string._2130845643_res_0x7f021fcb);
    }

    public static List<MessageObject> e(List<MemberMessage> list, boolean z) {
        String valueOf;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.a("VipUtils", "memberMessages is empty");
            return arrayList;
        }
        for (MemberMessage memberMessage : list) {
            MessageObject messageObject = new MessageObject();
            messageObject.setMsgType(2);
            if (z) {
                messageObject.setPosition(3);
            } else {
                messageObject.setPosition(1);
            }
            messageObject.setMsgPosition(11);
            messageObject.setExpireTime(memberMessage.getExpireTime());
            messageObject.setReceiveTime(memberMessage.getGenTime());
            messageObject.setReadFlag(memberMessage.getVisitFlag());
            messageObject.setMsgId(memberMessage.getMsgId());
            VipMessageInfo b = b(memberMessage.getInfoValue());
            boolean z2 = b.getOperateType() >= 30000;
            if (z2) {
                valueOf = String.valueOf(90);
            } else {
                valueOf = String.valueOf(70);
            }
            messageObject.setModule(valueOf);
            messageObject.setType(z2 ? MessageConstant.BENEFIT_TYPE : MessageConstant.MEMBER_TYPE);
            String c = c(b);
            if (TextUtils.isEmpty(c)) {
                LogUtil.h("VipUtils", "operateType not support, operateType:", Integer.valueOf(b.getOperateType()));
            } else {
                messageObject.setMsgTitle(c);
                messageObject.setInfoClassify(BaseApplication.getContext().getResources().getString(R.string._2130845630_res_0x7f021fbe));
                String c2 = c(b, memberMessage, c, z2);
                LogUtil.a("VipUtils", "vipMessageInfo.getOperateType(): ", Integer.valueOf(b.getOperateType()), "; url = ", c2);
                messageObject.setDetailUri(c2);
                String b2 = b(b, z);
                if (TextUtils.isEmpty(b2)) {
                    LogUtil.h("VipUtils", "content is empty", Integer.valueOf(b.getOperateType()));
                } else {
                    messageObject.setMsgContent(b2);
                    messageObject.setCreateTime(memberMessage.getGenTime());
                    messageObject.setHuid(LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).getAccountInfo(1011));
                    arrayList.add(messageObject);
                }
            }
        }
        List<MessageObject> d = d(arrayList);
        LogUtil.a("VipUtils", "messageObjects.size:", Integer.valueOf(d.size()));
        return d;
    }

    private static String c(VipMessageInfo vipMessageInfo, MemberMessage memberMessage, String str, boolean z) {
        return z ? i(vipMessageInfo) : b(vipMessageInfo, memberMessage, str);
    }

    private static String b(VipMessageInfo vipMessageInfo, MemberMessage memberMessage, String str) {
        String str2 = "messagecenter://member?uniqueId=" + vipMessageInfo.getShareInfoUniqueId() + "&type=" + vipMessageInfo.getOperateType() + "&messageTitle=" + str + "&activityId=" + memberMessage.getMsgId() + "&orderCode=" + vipMessageInfo.getOrderCode();
        if (vipMessageInfo.getOperateType() != 10025) {
            return str2;
        }
        return str2 + "&promotionPolicyId=" + vipMessageInfo.getPromotionPolicyId() + "&productId=" + vipMessageInfo.getProductId() + "&promotionId=" + vipMessageInfo.getPromotionId() + "&popUpTypeSelect=true&vipOpenFrom=pushNotification&payResourceType=push到期提醒";
    }

    private static List<MessageObject> d(List<MessageObject> list) {
        Collections.sort(list, new Comparator<MessageObject>() { // from class: gpn.2
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(MessageObject messageObject, MessageObject messageObject2) {
                if (messageObject.getReceiveTime() < messageObject2.getReceiveTime()) {
                    return -1;
                }
                return messageObject.getReceiveTime() == messageObject2.getReceiveTime() ? 0 : 1;
            }
        });
        return list;
    }

    private static String i(VipMessageInfo vipMessageInfo) {
        return (vipMessageInfo.getOperateType() == 30005 || vipMessageInfo.getOperateType() == 30006) ? "messagecenter://benefit?type=2&key=huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?h5pro=true%26isNeedLogin=true%26path=VipRenewal" : (vipMessageInfo.getOperateType() == 30003 || vipMessageInfo.getOperateType() == 30008 || vipMessageInfo.getOperateType() == 30004) ? "messagecenter://benefit?type=2&key=huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?h5pro=true%26urlType=4%26pName=com.huawei.health%26path=ExclusiveGuardService" : "";
    }

    public static VipMessageInfo b(String str) {
        VipMessageInfo vipMessageInfo = new VipMessageInfo();
        try {
            return (VipMessageInfo) new Gson().fromJson(str, VipMessageInfo.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("VipUtils", "gson exception");
            return vipMessageInfo;
        }
    }

    private static String d(long j) {
        String quantityString;
        int[] e = e(j);
        int i = e[0];
        if (i > 0) {
            if (e[1] <= 0) {
                quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903364_res_0x7f030144, e[0], UnitUtil.e(i, 1, 0));
            } else {
                int i2 = (int) (j / 2592000000L);
                quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903365_res_0x7f030145, i2, UnitUtil.e(i2, 1, 0));
            }
        } else {
            int i3 = e[1];
            if (i3 > 0) {
                if (e[2] >= 28) {
                    i3++;
                }
                quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903365_res_0x7f030145, i3, UnitUtil.e(i3, 1, 0));
            } else {
                quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, e[2], UnitUtil.e(e[2], 1, 0));
            }
        }
        LogUtil.a("VipUtils", "duration:", quantityString);
        return quantityString;
    }

    private static String a(long j) {
        String a2;
        int[] e = e(j);
        if (e.length != 3) {
            ReleaseLogUtil.c("VipUtils", "unknow leftTime result");
            return "";
        }
        int i = e[0];
        if (i > 0) {
            if (e[1] <= 0) {
                a2 = nsf.a(R.plurals._2130903430_res_0x7f030186, e[0], UnitUtil.e(i, 1, 0));
            } else {
                a2 = nsf.a(R.plurals._2130903431_res_0x7f030187, e[1], UnitUtil.e((int) (j / 2592000000L), 1, 0));
            }
        } else {
            int i2 = e[1];
            if (i2 > 0) {
                if (e[2] >= 28) {
                    i2++;
                }
                a2 = nsf.a(R.plurals._2130903431_res_0x7f030187, e[1], UnitUtil.e(i2, 1, 0));
            } else {
                a2 = nsf.a(R.plurals._2130903432_res_0x7f030188, e[2], UnitUtil.e(e[2], 1, 0));
            }
        }
        LogUtil.a("VipUtils", "duration:", a2);
        return a2;
    }

    private static int[] e(long j) {
        LogUtil.a("VipUtils", "timeMillonSecond:", Long.valueOf(j));
        int i = (int) (j / 31536000000L);
        long j2 = j - (i * 31536000000L);
        int i2 = (int) (j2 / 2592000000L);
        int i3 = (int) ((j2 - (i2 * 2592000000L)) / 86400000);
        LogUtil.a("VipUtils", "year:", Integer.valueOf(i), "month:", Integer.valueOf(i2), "day:", Integer.valueOf(i3));
        return new int[]{i, i2, i3};
    }

    public static String d(String str, int i) {
        String a2 = a(str, i);
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        try {
            return BaseApplication.getContext().getResources().getString(R.string.IDS_user_member_has_pick_device_tip, a2);
        } catch (IllegalFormatConversionException | MissingFormatArgumentException e) {
            LogUtil.b("VipUtils", "getInboxMessageStr string format exception", e.getMessage());
            return "";
        }
    }

    public static int a(DevicePerfPurchaseInfo devicePerfPurchaseInfo) {
        if (devicePerfPurchaseInfo == null || devicePerfPurchaseInfo.getAutoRenew() == 1 || devicePerfPurchaseInfo.getSubscriptionPromotion() == null) {
            return 0;
        }
        return devicePerfPurchaseInfo.getSubscriptionPromotion().getNumOfPeriods();
    }

    public static String e(String str, int i) {
        LogUtil.c("VipUtils", "getBenefitsTimeStr duration == ", str);
        if (TextUtils.isEmpty(str) || str.length() < 3) {
            LogUtil.h("VipUtils", "getInboxMessageStr duration is error");
            return "";
        }
        int c = c(str, i);
        return c <= 0 ? "" : a(c, str);
    }

    private static String a(String str, int i) {
        LogUtil.a("VipUtils", "getDeviceInboxTimeStr duration == ", str);
        if (TextUtils.isEmpty(str) || str.length() < 3) {
            LogUtil.h("VipUtils", "getDeviceInboxTimeStr duration is error");
            return "";
        }
        int c = c(str, i);
        if (c <= 0) {
            return "";
        }
        if (str.charAt(str.length() - 1) == 'D') {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903367_res_0x7f030147, c, UnitUtil.e(c, 1, 0));
        }
        return a(c, str);
    }

    public static String a(String str) {
        LogUtil.c("VipUtils", "getBenefitsTimeStr duration == ", str);
        if (TextUtils.isEmpty(str) || str.length() < 3) {
            LogUtil.h("VipUtils", "getInboxMessageStr duration is error");
            return "";
        }
        int c = c(str, 1);
        return c <= 0 ? "" : a(c, str);
    }

    public static int c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int m = health.compact.a.CommonUtil.m(BaseApplication.getContext(), str.substring(1, str.length() - 1));
        LogUtil.a("VipUtils", "getBenefitsTimeStr getNum == ", str);
        if (i <= 0) {
            return m;
        }
        LogUtil.a("VipUtils", "getBenefitsTimeStr getNum plus numOfPeriods == ", str);
        return m * i;
    }

    public static String a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String e = UnitUtil.e(i, 1, 0);
        char charAt = str.charAt(str.length() - 1);
        if (charAt == 'D') {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, e);
        }
        if (charAt == 'M') {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903365_res_0x7f030145, i, e);
        }
        if (charAt == 'W') {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903311_res_0x7f03010f, i, Integer.valueOf(i));
        }
        if (charAt == 'Y') {
            int i2 = i * 12;
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903365_res_0x7f030145, i2, UnitUtil.e(i2, 1, 0));
        }
        LogUtil.h("VipUtils", "getInboxMessageStr duration format error");
        return "";
    }

    public static boolean f() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        return SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_AWARD), accountInfo + "key_is_vip", false);
    }

    public static UserMemberInfo a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "USER_VIP_INFO_KEY");
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return (UserMemberInfo) moj.e(b, UserMemberInfo.class);
    }

    @Deprecated
    public static boolean b() {
        return !Utils.o() || ((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isModuleOperatedToOversea("ai-vip-oversea-001");
    }

    public static boolean d() {
        if (Utils.o()) {
            return mtl.b("1");
        }
        return true;
    }

    public static void a(boolean z, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = z ? new StringBuilder("SHOW_LIFE_TIME_") : new StringBuilder("SHOW_LIFE_TIME_PURCHASE_");
        sb.append(str);
        jfa.b(Integer.toString(10000), sb.toString(), currentTimeMillis);
        jfa.b(Integer.toString(10000), "last_benefit_show_time", currentTimeMillis);
    }

    public static long b(boolean z, String str) {
        StringBuilder sb = z ? new StringBuilder("SHOW_LIFE_TIME_") : new StringBuilder("SHOW_LIFE_TIME_PURCHASE_");
        sb.append(str);
        return jfa.d(Integer.toString(10000), sb.toString(), 0L);
    }

    public static long e() {
        return jfa.d(Integer.toString(10000), "last_benefit_show_time", 0L);
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "#/MemberCenter?currentBenefit=1&benefitList=%5B1%5D";
        }
        return "#/MemberCenter?currentBenefit=1&benefitList=%5B1%5D&" + str;
    }

    public static void c(Context context, String str) {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(c(str));
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.vip", builder);
    }
}
