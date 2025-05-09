package defpackage;

import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class qak {
    private static Map<Integer, Integer> e = new HashMap(16);

    public static int a(int i, String str) {
        LogUtil.a("DataOriginIconUtils", "setDataIcon dataType:", Integer.valueOf(i));
        if (i == 32) {
            return R.mipmap._2131820858_res_0x7f11013a;
        }
        if (i != 39) {
            if (i != 41 && i != 46) {
                if (i != 51) {
                    if (i != 88 && i != 395) {
                        if (i == 506) {
                            return R.mipmap._2131820859_res_0x7f11013b;
                        }
                        if (i != 35 && i != 36) {
                            switch (i) {
                                case 21:
                                case 22:
                                case 23:
                                    break;
                                default:
                                    return e(i, str);
                            }
                        }
                    }
                }
            }
            return R.mipmap._2131820863_res_0x7f11013f;
        }
        return R.mipmap._2131820861_res_0x7f11013d;
    }

    private static int e(int i, String str) {
        if (d(i, "001")) {
            return R.mipmap._2131820857_res_0x7f110139;
        }
        if (d(i, "095")) {
            return R.mipmap._2131820860_res_0x7f11013c;
        }
        int c = c(i);
        return str != null ? c != R.mipmap._2131820858_res_0x7f11013a ? c : str.toUpperCase(Locale.ENGLISH).contains("WATCH") ? R.mipmap._2131820863_res_0x7f11013f : R.mipmap._2131820861_res_0x7f11013d : R.mipmap._2131820862_res_0x7f11013e;
    }

    private static int c(int i) {
        if (e.containsKey(Integer.valueOf(i))) {
            return e.get(Integer.valueOf(i)).intValue();
        }
        cvc pluginInfoByHiType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByHiType(i);
        int i2 = R.mipmap._2131820858_res_0x7f11013a;
        if (pluginInfoByHiType == null || pluginInfoByHiType.f() == null) {
            LogUtil.h("DataOriginIconUtils", "getDefaultTrack pluginInfo or pluginInfo.getWearDeviceInfo() is null");
            e.put(Integer.valueOf(i), Integer.valueOf(R.mipmap._2131820858_res_0x7f11013a));
            return R.mipmap._2131820858_res_0x7f11013a;
        }
        if (pluginInfoByHiType.f().ah() == 1) {
            i2 = R.mipmap._2131820861_res_0x7f11013d;
        } else if (pluginInfoByHiType.f().ah() == 2) {
            i2 = R.mipmap._2131820863_res_0x7f11013f;
        }
        e.put(Integer.valueOf(i), Integer.valueOf(i2));
        return i2;
    }

    private static boolean d(int i, String str) {
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b = ProductMap.b("deviceId", String.valueOf(i));
        LogUtil.a("DataOriginIconUtils", "isMatchDeviceType enter ", Integer.valueOf(b.size()), " productType ", str);
        if (koq.c(b)) {
            return str.equals(b.get(0).e());
        }
        return false;
    }
}
