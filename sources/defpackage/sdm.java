package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import defpackage.dcz;
import java.util.List;

/* loaded from: classes7.dex */
public class sdm {
    @Deprecated
    public static String a(int i) {
        LogUtil.a("RopeDeviceNameUtils", "deviceType = ", Integer.valueOf(i));
        return b("deviceId", String.valueOf(i));
    }

    private static String b(String str, String str2) {
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b = ProductMap.b(str, str2);
        if (koq.b(b)) {
            LogUtil.a("RopeDeviceNameUtils", "productmap is null or empty");
            return "";
        }
        String h = b.get(0).h();
        if (TextUtils.isEmpty(h)) {
            LogUtil.a("RopeDeviceNameUtils", "productmap no productId");
            return "";
        }
        dcz d = ResourceManager.e().d(h);
        if (d == null) {
            LogUtil.a("RopeDeviceNameUtils", "product file is null");
            return "";
        }
        dcz.b n = d.n();
        if (n == null) {
            LogUtil.a("RopeDeviceNameUtils", "productInfo manifest is null");
            return "";
        }
        return dcx.d(h, n.b());
    }

    public static void c(String str) {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences(PluginPayAdapter.KEY_DEVICE_INFO_NAME, 0).edit();
        if (edit != null) {
            edit.putString("device_name_key", str);
            edit.apply();
        }
    }
}
