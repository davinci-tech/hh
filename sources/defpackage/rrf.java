package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import defpackage.jbw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class rrf {
    public static final Map<String, Integer> b = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: rrf.2
        private static final long serialVersionUID = -1189305116792831566L;

        {
            put("manualInput", Integer.valueOf(R.mipmap._2131820869_res_0x7f110145));
            put("unknowApp", Integer.valueOf(R.mipmap._2131820874_res_0x7f11014a));
            put("unknowDevice", Integer.valueOf(R.mipmap._2131820875_res_0x7f11014b));
            put("06E", Integer.valueOf(R.mipmap._2131820865_res_0x7f110141));
            put("06D", Integer.valueOf(R.mipmap._2131820876_res_0x7f11014c));
            Integer valueOf = Integer.valueOf(R.mipmap._2131820870_res_0x7f110146);
            put("00E", valueOf);
            put("011", valueOf);
            put("082", Integer.valueOf(R.mipmap._2131820867_res_0x7f110143));
            put("088", Integer.valueOf(R.mipmap._2131820868_res_0x7f110144));
            put("025", Integer.valueOf(R.mipmap._2131820866_res_0x7f110142));
            put("08F", Integer.valueOf(R.mipmap._2131820873_res_0x7f110149));
            put("0B3", Integer.valueOf(R.mipmap._2131820872_res_0x7f110148));
            put("054", Integer.valueOf(R.mipmap._2131820871_res_0x7f110147));
            put("02B", Integer.valueOf(R.mipmap._2131820855_res_0x7f110137));
        }
    });

    public static String e(String str) {
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b2 = ProductMap.b("deviceId", str);
        return koq.b(b2) ? "" : b2.get(0).e();
    }

    public static int d(String str) {
        String e = e(str);
        Map<String, Integer> map = b;
        if (map.containsKey(e)) {
            return map.get(e).intValue();
        }
        if (g(str) && map.containsKey("025")) {
            return map.get("025").intValue();
        }
        return map.get("unknowDevice").intValue();
    }

    private static boolean g(String str) {
        return String.valueOf(85).equals(str) || String.valueOf(86).equals(str);
    }

    public static int a(String str) {
        String str2;
        if ("10004".equals(str) || "68".equals(str)) {
            str2 = "02B";
        } else {
            str2 = "104".equals(str) ? "06D" : e(str);
        }
        Map<String, Integer> map = b;
        return map.containsKey(str2) ? map.get(str2).intValue() : R.mipmap._2131821015_res_0x7f1101d7;
    }

    public static int d() {
        return b.get("manualInput").intValue();
    }

    public static int c() {
        return b.get("unknowDevice").intValue();
    }

    public static int e() {
        return b.get("unknowApp").intValue();
    }

    public static void c(final Context context) {
        ProductMapParseUtil.b(context);
        if (jbw.c(context)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.util.SourceUtil$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    jbw.d(context, 0);
                }
            });
        }
    }

    public static List<SourceInfo> b(List<SourceInfo> list) {
        ArrayList arrayList = new ArrayList(10);
        for (SourceInfo sourceInfo : list) {
            if (sourceInfo.getmItemType() == 3) {
                arrayList.add(sourceInfo);
            }
        }
        return arrayList;
    }

    public static void c(List<SourceInfo> list) {
        for (SourceInfo sourceInfo : list) {
            if (sourceInfo.getmItemType() == 3) {
                int sourceType = sourceInfo.getSourceType();
                if (sourceType == 2) {
                    sourceInfo.setIconResourceId(d(String.valueOf(sourceInfo.getDeviceType())));
                } else if (sourceType == 3) {
                    sourceInfo.setIconResourceId(d());
                } else {
                    sourceInfo.setIconResourceId(e());
                }
            }
        }
    }

    public static void e(List<SourceInfo> list) {
        String b2 = b();
        LogUtil.a("SourceUtil", "packageNameMapJson:", b2);
        if (TextUtils.isEmpty(b2)) {
            list.clear();
            return;
        }
        Map<String, String> a2 = moj.a(b2);
        Iterator<SourceInfo> it = list.iterator();
        while (it.hasNext()) {
            String packageName = it.next().getPackageName();
            if (!a2.containsKey(packageName)) {
                it.remove();
                LogUtil.a("SourceUtil", "remove packageName:", packageName);
            }
        }
    }

    public static String b() {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("privacy_data_source_third_party_app_flag_file", 0);
        return sharedPreferences == null ? "" : sharedPreferences.getString("privacy_data_source_third_party_app_flag_context", "");
    }

    public static void d(List<SourceInfo> list) {
        rrb.b(list, BaseApplication.getContext());
    }

    public static List<SourceInfo> a(List<SourceInfo> list) {
        ArrayList arrayList = new ArrayList(10);
        HashMap hashMap = new HashMap(16);
        for (SourceInfo sourceInfo : list) {
            if (!hashMap.containsKey(Integer.valueOf(sourceInfo.getDeviceId()))) {
                hashMap.put(Integer.valueOf(sourceInfo.getDeviceId()), sourceInfo);
            }
        }
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add((SourceInfo) ((Map.Entry) it.next()).getValue());
        }
        return arrayList;
    }

    public static SourceInfo d(HiDeviceInfo hiDeviceInfo) {
        String a2;
        SourceInfo sourceInfo = new SourceInfo();
        if (hiDeviceInfo == null) {
            return sourceInfo;
        }
        sourceInfo.setDeviceUniqueCode(hiDeviceInfo.getDeviceUniqueCode());
        sourceInfo.setSourceType(2);
        sourceInfo.setItemType(3);
        sourceInfo.setDeviceType(hiDeviceInfo.getDeviceType());
        sourceInfo.setDeviceId(hiDeviceInfo.getDeviceId());
        if (hiDeviceInfo.getDeviceType() == 32) {
            a2 = hiDeviceInfo.getModel();
            if (TextUtils.isEmpty(a2)) {
                a2 = hiDeviceInfo.getManufacturer();
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = hiDeviceInfo.getDeviceName();
            }
            String deviceName = hiDeviceInfo.getDeviceName();
            if (TextUtils.isEmpty(deviceName)) {
                sourceInfo.setOtherManufacturer(true);
            } else if (deviceName.toUpperCase(Locale.ROOT).contains("HUAWEI") || deviceName.toUpperCase(Locale.ROOT).contains("HONOR")) {
                sourceInfo.setOtherManufacturer(false);
            } else {
                sourceInfo.setOtherManufacturer(true);
            }
        } else {
            a2 = spx.a(hiDeviceInfo);
        }
        if (TextUtils.isEmpty(a2)) {
            if (hiDeviceInfo.getDeviceType() == 10004) {
                a2 = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_ecological_device);
            } else {
                a2 = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_data_origin_unknow_device);
            }
        }
        sourceInfo.setDeviceName(a2);
        return sourceInfo;
    }

    public static boolean c(String str) {
        return (rju.f16792a.equals(str) || iqw.d(str)) ? false : true;
    }

    public static boolean b(String str) {
        return !"-1".equals(str);
    }

    public static SourceInfo e(HiAppInfo hiAppInfo) {
        SourceInfo sourceInfo = new SourceInfo();
        sourceInfo.setSourceType(1);
        sourceInfo.setDeviceId(hiAppInfo.getAppId());
        if (hiAppInfo.getAppName() != null) {
            sourceInfo.setDeviceName(hiAppInfo.getAppName());
        } else {
            sourceInfo.setDeviceName(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_datasource_unknow_application));
        }
        sourceInfo.setPackageName(hiAppInfo.getPackageName());
        sourceInfo.setAppVertion(hiAppInfo.getVersion());
        sourceInfo.setDeviceType(Integer.MIN_VALUE);
        sourceInfo.setItemType(3);
        return sourceInfo;
    }
}
