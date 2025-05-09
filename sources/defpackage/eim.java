package defpackage;

import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class eim {
    private static String d;

    public static void e(final IBaseResponseCallback iBaseResponseCallback) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.h("MemberDeviceBenefitsUtils", "getDeviceBenefits browseMode, return");
            return;
        }
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: eim.3
                @Override // java.lang.Runnable
                public void run() {
                    eim.e(IBaseResponseCallback.this);
                }
            });
        } else if (!TextUtils.isEmpty(d) || !CommonUtil.bh()) {
            LogUtil.a("MemberDeviceBenefitsUtils", "mDeviceSn is not empty or not huawei system");
            c(iBaseResponseCallback);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ein
                @Override // java.lang.Runnable
                public final void run() {
                    eim.a(IBaseResponseCallback.this);
                }
            });
        }
    }

    static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback) {
        String a2 = njn.a(BaseApplication.getContext());
        d = a2;
        LogUtil.a("MemberDeviceBenefitsUtils", "getDeviceSn end is deviceSn empty", Boolean.valueOf(TextUtils.isEmpty(a2)));
        c(iBaseResponseCallback);
    }

    private static void c(IBaseResponseCallback iBaseResponseCallback) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.a("MemberDeviceBenefitsUtils", "getDeviceBenefits payApi is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        if (CommonUtil.bh() && !TextUtils.isEmpty(d)) {
            arrayList.add(new DeviceBenefitQueryParam.Builder().setDeviceType(PhoneInfoUtils.getDeviceModel()).setDeviceSn(d).setBenefitType(DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_PERF_PURCHASE).setDeviceCategory(DeviceBenefitQueryParam.DeviceCategory.DEVICE_CATEGORY_PHONE.getCategory()).setNeedProductInfo(true).build());
        }
        arrayList.addAll(njn.e("MemberDeviceBenefitsUtils", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL));
        payApi.getAllDeviceBenefits(arrayList, iBaseResponseCallback);
    }
}
