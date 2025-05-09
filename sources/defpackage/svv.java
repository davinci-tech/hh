package defpackage;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wear.oversea.util.PropertyUtil;
import com.huawei.wear.oversea.util.SystemPropertyValues;

/* loaded from: classes7.dex */
public class svv {
    public static String e() {
        int i;
        String prop = PropertyUtil.getProp("ro.product.locale.region");
        stq.d("FetchCountryCodeHelp", "ro.product.locale.region region=" + prop, false);
        if (!TextUtils.isEmpty(prop)) {
            return prop;
        }
        String prop2 = PropertyUtil.getProp(SystemPropertyValues.PROP_LOCALE_KEY);
        stq.d("FetchCountryCodeHelp", "ro.product.locale region=" + prop2, false);
        if (!TextUtils.isEmpty(prop2) && prop2.contains("CN")) {
            return "CN";
        }
        int lastIndexOf = prop2.lastIndexOf(Constants.LINK);
        if (lastIndexOf >= 0 && (i = lastIndexOf + 1) < prop2.length()) {
            prop2 = prop2.substring(i);
        }
        TextUtils.isEmpty(prop2);
        return prop2;
    }
}
