package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cav {
    public static String e() {
        String format = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date());
        StringBuilder sb = new StringBuilder(16);
        sb.append("gps_maptracking_");
        sb.append(format);
        return sb.toString();
    }

    public static void d(Context context) {
        if (context == null) {
            LogUtil.b("SportTrackUtils", "initHiHealth error: context is null");
            return;
        }
        final SharedPreferences sharedPreferences = context.getSharedPreferences("HiHealth_UUID", 0);
        if (sharedPreferences.getString("PHONE_UUID", null) != null) {
            return;
        }
        HiHealthManager.d(context).fetchPhoneDataClient(new HiDataClientListener() { // from class: cav.5
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                if (koq.b(list)) {
                    return;
                }
                sharedPreferences.edit().putString("PHONE_UUID", list.get(0).getDeviceUuid()).commit();
            }
        });
    }
}
