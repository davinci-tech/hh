package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.HwEncryptUtil;
import health.compact.a.util.LogUtil;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class btz {
    private static final String d = Pattern.quote("|");
    private static Map<String, Integer> c = null;
    private static Map<String, Integer> b = null;

    public static String a(Context context) {
        if (context == null) {
            LogUtil.c("EmergencyInfoUtils", "getEmergencyContactsString context is null");
            return "";
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return HwEncryptUtil.c(context).a(2, defaultSharedPreferences.getString(c() + "emergency_contacts", ""));
        } catch (ClassCastException | GeneralSecurityException unused) {
            LogUtil.e("EmergencyInfoUtils", "getEmergencyContactsString catch exception");
            defaultSharedPreferences.getStringSet(c() + "emergency_contacts", Collections.emptySet());
            return "";
        }
    }

    public static List<gmx> c(String str, Context context, String str2) {
        return b(str, context, str2, true);
    }

    public static List<gmx> b(String str, Context context, String str2, boolean z) {
        if (context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.c("EmergencyInfoUtils", "deserializeAndFilter context is null or key is empty or emergencyContact is empty");
            return new ArrayList(0);
        }
        String[] split = str2.split(d);
        ArrayList arrayList = new ArrayList(split.length);
        c(context, split, arrayList, z);
        if (PermissionUtil.e(context, new String[]{"android.permission.READ_CONTACTS"}) && arrayList.size() != split.length) {
            String b2 = b(arrayList, context);
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(c() + str, b2).apply();
        }
        return arrayList;
    }

    private static void c(Context context, String[] strArr, List<gmx> list, boolean z) {
        gmx gmxVar;
        for (String str : strArr) {
            if (z) {
                try {
                    gmxVar = (gmx) new Gson().fromJson(str, gmx.class);
                } catch (JsonSyntaxException unused) {
                    LogUtil.e("EmergencyInfoUtils", "deserializeAndFilter catch JsonSyntaxException");
                    gmxVar = null;
                }
                if (gmxVar == null) {
                    LogUtil.c("EmergencyInfoUtils", "fromJson info is null");
                } else if (!gmxVar.b()) {
                    list.add(gmxVar);
                } else if (PermissionUtil.e(context, new String[]{"android.permission.READ_CONTACTS"})) {
                    d(gmxVar.d(), context, list);
                }
            } else if (PermissionUtil.e(context, new String[]{"android.permission.READ_CONTACTS"})) {
                d(str, context, list);
            }
        }
    }

    private static boolean d(String str, Context context, List<gmx> list) {
        Uri parse = Uri.parse(str);
        if (!EmergencyInfoManager.c().vg_(context, parse) || list.size() >= 3) {
            return false;
        }
        list.add(EmergencyInfoManager.c().vf_(parse));
        return true;
    }

    public static String b(List<gmx> list, Context context) {
        if (list == null || context == null) {
            LogUtil.c("EmergencyInfoUtils", "serialize emergencyContacts or context is null");
            return null;
        }
        StringBuilder sb = new StringBuilder(16);
        try {
            for (gmx gmxVar : list) {
                if (gmxVar == null) {
                    LogUtil.c("EmergencyInfoUtils", "serialize contactInfo is null");
                } else {
                    sb.append(new Gson().toJson(gmxVar));
                    sb.append("|");
                }
            }
        } catch (JsonIOException unused) {
            LogUtil.e("EmergencyInfoUtils", "serialize catch JsonIOException");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        try {
            return HwEncryptUtil.c(context).b(2, sb.toString());
        } catch (GeneralSecurityException unused2) {
            LogUtil.e("EmergencyInfoUtils", "serialize catch GeneralSecurityException");
            return "";
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.c("EmergencyInfoUtils", "hasAtLeastOneEmergencyContact context is null");
            return false;
        }
        return c("emergency_contacts", context, a(context)).isEmpty();
    }

    public static boolean c(Context context) {
        int i;
        int i2;
        if (context == null) {
            LogUtil.c("EmergencyInfoUtils", "isInStartUpGuide context is null");
            return false;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver != null) {
                i2 = Settings.Secure.getInt(contentResolver, "user_setup_complete");
                i = Settings.Secure.getInt(contentResolver, "device_provisioned");
            } else {
                i = 0;
                i2 = 0;
            }
        } catch (Settings.SettingNotFoundException unused) {
            LogUtil.e("EmergencyInfoUtils", "SettingNotFoundException");
        }
        return (i2 == 1 && i == 1) ? false : true;
    }

    private static void d(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array._2130968635_res_0x7f04003b);
        int i = 0;
        c.put(stringArray[stringArray.length - 1], 0);
        while (i < stringArray.length - 1) {
            Map<String, Integer> map = c;
            String str = stringArray[i];
            i++;
            map.put(str, Integer.valueOf(i));
        }
    }

    public static int a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.c("EmergencyInfoUtils", "context is null 0r convertBloodTypeToIntValue value is empty ");
            return 0;
        }
        if (c == null) {
            c = new HashMap(16);
            d(context);
        }
        Integer num = c.get(str);
        if (num != null) {
            return num.intValue();
        }
        LogUtil.c("EmergencyInfoUtils", "convertBloodTypeToIntValue value is empty ");
        return 0;
    }

    private static void e(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array._2130968640_res_0x7f040040);
        int i = 0;
        b.put(stringArray[stringArray.length - 1], 0);
        while (i < stringArray.length - 1) {
            Map<String, Integer> map = b;
            String str = stringArray[i];
            i++;
            map.put(str, Integer.valueOf(i));
        }
    }

    public static int d(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.c("EmergencyInfoUtils", "context is null or convertOrganDonor value is empty ");
            return 0;
        }
        if (b == null) {
            b = new HashMap(16);
            e(context);
        }
        Integer num = b.get(str);
        if (num != null) {
            return num.intValue();
        }
        LogUtil.c("EmergencyInfoUtils", "convertOrganDonor morganDonorMap not contains:");
        return 0;
    }

    public static String c() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        return accountInfo == null ? "" : accountInfo;
    }
}
