package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class nti {
    public static boolean d(List<String> list) {
        if (koq.b(list)) {
            LogUtil.a("PageConfigUtil", "supportDeviceList is empty.");
            return false;
        }
        LogUtil.c("PageConfigUtil", "isSupportDevice supportDeviceList:", HiJsonUtil.e(list));
        boolean bd = CommonUtil.bd();
        for (String str : list) {
            boolean equals = "AllDeviceType".equals(str);
            boolean z = "ThirdPartyType".equals(str) && (bd ^ true);
            LogUtil.c("PageConfigUtil", "isAll: ", Boolean.valueOf(equals), ", isThirdParty: ", Boolean.valueOf(z));
            if (equals || z) {
                return true;
            }
        }
        return CommonUtil.c((String[]) list.toArray(new String[0]));
    }

    public static boolean b(List<Integer> list, String str) {
        if (koq.b(list)) {
            LogUtil.a("PageConfigUtil", "huidTailList is empty.");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PageConfigUtil", "huid is empty");
            return false;
        }
        LogUtil.c("PageConfigUtil", "isSupportHuid huidTailList:", HiJsonUtil.e(list), ",huid:", str);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            boolean endsWith = str.endsWith(String.valueOf(it.next()));
            if (endsWith) {
                LogUtil.c("PageConfigUtil", "isSupportHuid isCurHuidMatch: ", Boolean.valueOf(endsWith));
                return true;
            }
        }
        return false;
    }

    public static boolean c(List<String> list, String str) {
        if (koq.b(list)) {
            LogUtil.a("PageConfigUtil", "isBlockHuid blockHuidTailList is empty.");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PageConfigUtil", "isBlockHuid huid is empty");
            return false;
        }
        LogUtil.c("PageConfigUtil", "isBlockHuid blockHuidTailList:", HiJsonUtil.e(list), ",huid:", str);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            boolean endsWith = str.endsWith(it.next());
            if (endsWith) {
                LogUtil.c("PageConfigUtil", "isBlockHuid isCurHuidMatch: ", Boolean.valueOf(endsWith));
                return true;
            }
        }
        return false;
    }

    public static boolean e(List<String> list, String str) {
        if (koq.b(list)) {
            LogUtil.a("PageConfigUtil", "isWhiteCountry whiteCountryList is empty.");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PageConfigUtil", "isWhiteCountry countryCode is empty");
            return false;
        }
        LogUtil.c("PageConfigUtil", "isWhiteCountry whiteCountryList:", HiJsonUtil.e(list), ",countryCode:", str);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            boolean contains = str.contains(it.next());
            if (contains) {
                LogUtil.c("PageConfigUtil", "isWhiteCountry isCurCountryMatch: ", Boolean.valueOf(contains));
                return true;
            }
        }
        return false;
    }
}
