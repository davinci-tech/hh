package defpackage;

import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.android.telephony.SubscriptionManagerEx;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kke {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14428a = {"android.permission.READ_PHONE_STATE"};
    private static final Map<String, Integer> d = new HashMap(16);

    public static boolean h() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "DualSimCardUtils");
        if (deviceList.isEmpty()) {
            LogUtil.h("DualSimCardUtils", "isSupportSmsReject deviceInfo is null");
            return false;
        }
        boolean c2 = cwi.c(deviceList.get(0), 16);
        LogUtil.a("DualSimCardUtils", "Is device SupportSmsReject inComingCall? ", Boolean.valueOf(c2));
        return c2;
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DualSimCardUtils", "doPhoneRing saveCallingSimId incomingNumber is empty");
            return;
        }
        synchronized (c) {
            int b = b();
            d.put(str, Integer.valueOf(b));
            LogUtil.a("DualSimCardUtils", "doPhoneRing saveCallingSimId into SUBSCRIPTION_ID_MAP success,subscriptionId: ", Integer.valueOf(b));
        }
    }

    public static int e(String str) {
        int intValue;
        if (!TextUtils.isEmpty(str)) {
            Map<String, Integer> map = d;
            if (map.containsKey(str)) {
                synchronized (c) {
                    intValue = map.get(str).intValue();
                }
                return intValue;
            }
        }
        LogUtil.h("DualSimCardUtils", "get incomingNumber is empty or map not containsKey");
        return -1;
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Map<String, Integer> map = d;
            if (map.containsKey(str)) {
                synchronized (c) {
                    map.remove(str);
                }
                return;
            }
        }
        LogUtil.h("DualSimCardUtils", "remove incomingNumber is empty or map not containsKey");
    }

    public static void d() {
        synchronized (c) {
            d.clear();
        }
    }

    public static boolean a() {
        return e() == 2;
    }

    public static boolean i() {
        return e() == 1;
    }

    public static int e() {
        if (Build.VERSION.SDK_INT >= 30) {
            Object systemService = BaseApplication.getContext().getSystemService("phone");
            if (systemService instanceof TelephonyManager) {
                return ((TelephonyManager) systemService).getActiveModemCount();
            }
        }
        LogUtil.a("DualSimCardUtils", "getCurrentSimCount getActiveModemCount() failed,Build.VERSION_CODES: ", Integer.valueOf(Build.VERSION.SDK_INT));
        if (!jdi.c(BaseApplication.getContext(), f14428a)) {
            LogUtil.h("DualSimCardUtils", "getCurrentSimCount have not READ_PHONE_STATE permission");
            return 0;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(BaseApplication.getContext()).getActiveSubscriptionInfoList();
        if (bkz.e(activeSubscriptionInfoList)) {
            LogUtil.b("DualSimCardUtils", "smsReply getCurrentSimCount no sim card");
            return 0;
        }
        int size = activeSubscriptionInfoList.size();
        LogUtil.a("DualSimCardUtils", "smsReply getCurrentSimCardCount is: ", Integer.valueOf(size));
        return size;
    }

    public static int b() {
        LogUtil.a("DualSimCardUtils", "doPhoneRing saveCallingSimId getCurrentSubscriptionId start!");
        List<Integer> c2 = c();
        if (c2.isEmpty()) {
            return -1;
        }
        if (c2.size() == 1) {
            LogUtil.h("DualSimCardUtils", "doPhoneRing saveCallingSimId getCurrentSubscriptionId single sim:", c2.get(0));
            return c2.get(0).intValue();
        }
        Object systemService = BaseApplication.getContext().getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("DualSimCardUtils", "doPhoneRing saveCallingSimId getCurrentSubscriptionId object not instanceof TelephonyManager");
            return -1;
        }
        TelephonyManager telephonyManager = (TelephonyManager) systemService;
        try {
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getCallState", Integer.TYPE);
            declaredMethod.setAccessible(true);
            for (Integer num : c2) {
                Object invoke = declaredMethod.invoke(telephonyManager, num);
                if (invoke instanceof Integer) {
                    int intValue = ((Integer) invoke).intValue();
                    LogUtil.a("DualSimCardUtils", "doPhoneRing saveCallingSimId getCurrentSubscriptionId callState:", Integer.valueOf(intValue), " multiSimSubId:", num);
                    if (intValue == 1) {
                        return num.intValue();
                    }
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            LogUtil.b("DualSimCardUtils", "doPhoneRing saveCallingSimId getCurrentSubscriptionId exception", LogAnonymous.b(e));
        }
        LogUtil.a("DualSimCardUtils", "doPhoneRing saveCallingSimId getCurrentSubscriptionId other");
        return -1;
    }

    public static List<Integer> c() {
        SystemInfo.a(new StringBuilder("getDualSimSubscriptionIds start: "));
        ArrayList arrayList = new ArrayList(2);
        if (!jdi.c(BaseApplication.getContext(), f14428a)) {
            LogUtil.h("DualSimCardUtils", "getDualSimSubscriptionIds have not READ_PHONE_STATE permission");
            return arrayList;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(BaseApplication.getContext()).getActiveSubscriptionInfoList();
        if (bkz.e(activeSubscriptionInfoList)) {
            LogUtil.h("DualSimCardUtils", "getDualSimSubscriptionIds no sim");
            return arrayList;
        }
        Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getSubscriptionId()));
        }
        return arrayList;
    }

    public static SmsManager bPk_(int i) {
        LogUtil.a("DualSimCardUtils", "smsReply getSmsManager start!,input param: uniqueId= ", Integer.valueOf(i));
        SmsManager smsManager = SmsManager.getDefault();
        if (i()) {
            LogUtil.a("DualSimCardUtils", "smsReply getSmsManager current is single sim card");
            return smsManager;
        }
        int a2 = a(i);
        ReleaseLogUtil.b("DualSimCardUtils", "smsReply getSmsManager subscriptionId: ", Integer.valueOf(a2), " uniqueId:", Integer.valueOf(i));
        return a2 != -1 ? SmsManager.getSmsManagerForSubscriptionId(a2) : smsManager;
    }

    public static int e(int i) {
        if (!jdi.c(BaseApplication.getContext(), f14428a)) {
            LogUtil.h("DualSimCardUtils", "smsReply getSmsManager have not READ_PHONE_STATE permission");
            return -1;
        }
        SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = SubscriptionManager.from(BaseApplication.getContext()).getActiveSubscriptionInfoForSimSlotIndex(i);
        Object[] objArr = new Object[2];
        objArr[0] = "smsReply getSmsManager subInfo is ";
        objArr[1] = Boolean.valueOf(activeSubscriptionInfoForSimSlotIndex != null);
        LogUtil.a("DualSimCardUtils", objArr);
        if (activeSubscriptionInfoForSimSlotIndex != null) {
            return activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
        }
        if (i > 1) {
            return i;
        }
        LogUtil.h("DualSimCardUtils", "smsReply getSmsManager subInfo is null and slotId less than 1");
        return -1;
    }

    private static int a(int i) {
        String e = kiq.e("DualSimCardUtils");
        if (CommonUtil.bh()) {
            if (TextUtils.equals(e, "com.google.android.apps.messaging")) {
                return i;
            }
            int[] subId = SubscriptionManagerEx.getSubId(i);
            if (subId == null || subId.length <= 0) {
                return -1;
            }
            return subId[0];
        }
        if (Build.VERSION.SDK_INT < 29) {
            return e(i);
        }
        if (i == 0) {
            int[] subscriptionIds = SubscriptionManager.from(BaseApplication.getContext()).getSubscriptionIds(i);
            if (subscriptionIds == null || subscriptionIds.length <= 0) {
                return -1;
            }
            return subscriptionIds[0];
        }
        if (i > 1) {
            return i;
        }
        boolean d2 = kiq.d("DualSimCardUtils");
        ReleaseLogUtil.b("DualSimCardUtils", "smsReply getSmsManager getSubscriptionId isSubIdHasZero: ", Boolean.valueOf(d2));
        if (!d2) {
            return i;
        }
        int[] subscriptionIds2 = SubscriptionManager.from(BaseApplication.getContext()).getSubscriptionIds(i);
        if (subscriptionIds2 == null || subscriptionIds2.length <= 0) {
            return -1;
        }
        return subscriptionIds2[0];
    }
}
