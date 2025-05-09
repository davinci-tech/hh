package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class sck {
    public static void c(final Context context) {
        final ArrayList<ContentValues> f = ceo.d().f();
        jdx.b(new Runnable() { // from class: sck.5
            @Override // java.lang.Runnable
            public void run() {
                sck.c(f, sck.a(context), context);
            }
        });
    }

    public static void b(final Context context) {
        jdx.b(new Runnable() { // from class: sck.4
            @Override // java.lang.Runnable
            public void run() {
                if (sck.a(context)) {
                    sck.c(ceo.d().f(), true, context);
                }
            }
        });
    }

    public static boolean a(Context context) {
        String value;
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_language_write_key_FEF629A75A8B472D886BABB73BE88952");
        if (userPreference == null || (value = userPreference.getValue()) == null) {
            return false;
        }
        Map<String, Object> c = JsonParser.c(value);
        String locale = context.getResources().getConfiguration().locale.toString();
        if (Constants.NULL.equals(String.valueOf(c.get("language")))) {
            return false;
        }
        return !r0.equals(locale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(ArrayList<ContentValues> arrayList, boolean z, Context context) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        Map hashMap = new HashMap();
        if (userPreference == null) {
            userPreference = new HiUserPreference();
        } else {
            hashMap = JsonParser.c(userPreference.getValue());
            if (z) {
                hashMap.clear();
            }
            userPreference.setSyncStatus(0);
        }
        e(hashMap, arrayList);
        userPreference.setKey("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        userPreference.setValue(JsonParser.b((Map<?, ?>) hashMap).toString());
        LogUtil.a("DevicesLanguageChangeUtils", "privacy hiUserPreference set ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(userPreference)));
        d(context);
    }

    private static void d(Context context) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_language_write_key_FEF629A75A8B472D886BABB73BE88952");
        if (userPreference == null) {
            userPreference = new HiUserPreference();
        } else {
            userPreference.setSyncStatus(0);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("language", context.getResources().getConfiguration().locale.toString());
        userPreference.setKey("privacy_data_source_language_write_key_FEF629A75A8B472D886BABB73BE88952");
        userPreference.setValue(JsonParser.b(hashMap).toString());
        LogUtil.a("DevicesLanguageChangeUtils", "updata wirte and language chage flags： ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(userPreference)));
    }

    private static void e(Map<String, Object> map, ArrayList<ContentValues> arrayList) {
        dcz d;
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("productId");
            String asString2 = next.getAsString("uniqueId");
            if (asString2 != null && asString != null && (d = ResourceManager.e().d(asString)) != null) {
                String asString3 = next.getAsString("sn");
                String d2 = dcx.d(asString, d.n().b());
                if (!TextUtils.isEmpty(asString3)) {
                    String str = d2 + com.huawei.openalliance.ad.constant.Constants.LINK + c(asString3);
                    map.put(asString2, str);
                    map.put(asString3, str);
                } else {
                    map.put(asString2, d2 + com.huawei.openalliance.ad.constant.Constants.LINK + c(asString2));
                }
            }
        }
    }

    private static String c(String str) {
        String replaceAll = str.replaceAll(":", "");
        return replaceAll.length() < 3 ? replaceAll : replaceAll.substring(replaceAll.length() - 3);
    }
}
