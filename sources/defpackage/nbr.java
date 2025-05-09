package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import health.compact.a.AuthorizationUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.util.LogUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nbr {
    private static final Set<String> c = new HashSet<String>() { // from class: nbr.4
        private static final long serialVersionUID = -7011827657848604585L;

        {
            add("SC_EXERCISE");
            add("SC_DEVICE");
            add("SC_MALL");
            add("SC_KAKA");
        }
    };
    private static final Set<String> b = new HashSet<String>() { // from class: nbr.5
        private static final long serialVersionUID = -3061612464579099923L;

        {
            add("SC_EXERCISE");
            add("SC_DEVICE");
            add("SC_MALL");
        }
    };
    private static final Set<String> d = new HashSet<String>() { // from class: nbr.3
        private static final long serialVersionUID = 4788811629270512122L;

        {
            add("SC_EXERCISE");
            add("SC_DEVICE");
        }
    };
    private static final Map<String, nqm> e = new HashMap<String, nqm>() { // from class: nbr.2
        private static final long serialVersionUID = -3061612464579099923L;

        {
            put("SC_EXERCISE", new nqm("SC_EXERCISE", R$drawable.ic_btn_run, R$string.IDS_hw_shortcuts_start_exercise, 0, "com.huawei.health.ShortcutsActivity"));
            put("SC_DEVICE", new nqm("SC_DEVICE", R$drawable.ic_btn_device, R$string.IDS_hw_shortcuts_my_device, 0, "com.huawei.health.ShortcutsActivity"));
            put("SC_MALL", new nqm("SC_MALL", R$drawable.ic_btn_shop, R$string.IDS_hw_shortcuts_recommended_mall, 0, "com.huawei.health.ShortcutsActivity"));
            put("SC_KAKA", new nqm("SC_KAKA", R$drawable.ic_btn_kk, R$string.IDS_hw_shortcuts_my_points, 0, "com.huawei.health.ShortcutsActivity"));
        }
    };

    private static boolean a() {
        return true;
    }

    public static boolean b(Context context) {
        return AuthorizationUtils.a(context) && LoginInit.getInstance(context).getIsLogined();
    }

    public static String crG_(Intent intent) {
        if (intent == null) {
            LogUtil.c("ShortcutsUtil", "parseShortcutId intent null");
            return "";
        }
        String stringExtra = intent.getStringExtra("SHORTCUT");
        if (!TextUtils.isEmpty(stringExtra)) {
            return stringExtra;
        }
        if (intent.hasExtra(e.n)) {
            Map<String, String> a2 = moj.a(intent.getStringExtra(e.n));
            if (a2.containsKey("shortcutId")) {
                String str = a2.get("shortcutId");
                com.huawei.hwlogsmodel.LogUtil.c("ShortcutsUtil", "facard shortcutId :", str);
                return str;
            }
        }
        return "";
    }

    public static void a(Context context, String str) {
        LogUtil.c("ShortcutsUtil", "no logIn");
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                LogUtil.c("ShortcutsUtil", "launchIntentForPackage is null");
                return;
            }
            launchIntentForPackage.putExtra("SHORTCUT", str);
            launchIntentForPackage.addFlags(335544320);
            context.startActivity(launchIntentForPackage);
        } catch (ActivityNotFoundException e2) {
            LogUtil.e("ShortcutsUtil", "jumpToLoginAndAuth:", e2.getMessage());
        }
    }

    public static void c(Context context, int i, String str) {
        if (a()) {
            if (context == null) {
                LogUtil.c("ShortcutsUtil", "context is null");
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "shortcuts", "0", new StorageParams());
                return;
            }
            if (i == 0) {
                String c2 = c(str);
                if (!TextUtils.isEmpty(c2)) {
                    d(context, c2);
                    return;
                } else {
                    LogUtil.c("ShortcutsUtil", "resp is null ");
                    return;
                }
            }
            SharedPreferenceManager.e(context, String.valueOf(10000), "shortcuts", "0", new StorageParams());
        }
    }

    private static void d(Context context, String str) {
        nqm nqmVar;
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(new JSONObject(str).getString("shortCutList"));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("shortcutId");
                LogUtil.d("ShortcutsUtil", "shortcutid= ", string);
                if (!TextUtils.isEmpty(string) && !"SC_MALL".equals(string)) {
                    hashSet.add(string);
                    if (d(string) && !d.contains(string) && (nqmVar = e.get(string)) != null) {
                        nqmVar.e(jSONObject.getInt("rank"));
                        arrayList.add(nqmVar);
                    }
                }
            }
            a(context, arrayList, hashSet);
            SharedPreferenceManager.e(context, String.valueOf(10000), "shortcuts", "1", (StorageParams) null);
        } catch (JSONException e2) {
            SharedPreferenceManager.e(context, String.valueOf(10000), "shortcuts", "0", (StorageParams) null);
            LogUtil.e("ShortcutsUtil", "JSONException ", e2.getMessage());
        }
    }

    private static void a(Context context, List<nqm> list, Set<String> set) {
        LogUtil.c("ShortcutsUtil", "setDynamicShortcuts");
        if (!a()) {
            LogUtil.c("ShortcutsUtil", "is not support");
            return;
        }
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager == null) {
            LogUtil.c("ShortcutsUtil", "setDynamicShortcuts shortcutManager = null");
            return;
        }
        boolean isKidAccount = LoginInit.getInstance(context).isKidAccount();
        crH_(context, shortcutManager, set);
        ArrayList arrayList = new ArrayList();
        for (nqm nqmVar : list) {
            String e2 = nqmVar.e();
            if (!isKidAccount || (!"SC_MALL".equals(e2) && !"SC_KAKA".equals(e2))) {
                arrayList.add(crF_(context, nqmVar));
            }
        }
        try {
            boolean dynamicShortcuts = shortcutManager.setDynamicShortcuts(arrayList);
            LogUtil.d("ShortcutsUtil", "setDynamicShortcuts is", Boolean.valueOf(dynamicShortcuts));
            if (dynamicShortcuts) {
                String d2 = d(Locale.getDefault());
                if (TextUtils.isEmpty(d2)) {
                    return;
                }
                SharedPreferenceManager.e(context, String.valueOf(10000), "store_last_language", d2, (StorageParams) null);
            }
        } catch (IllegalArgumentException | IllegalStateException e3) {
            LogUtil.e("ShortcutsUtil", "setDynamicShortcuts IllegalArgumentException or IllegalStateException", e3.getMessage());
        }
    }

    private static void crH_(Context context, ShortcutManager shortcutManager, Set<String> set) {
        if (!a()) {
            LogUtil.c("ShortcutsUtil", "is not support");
            return;
        }
        try {
            List<ShortcutInfo> pinnedShortcuts = shortcutManager.getPinnedShortcuts();
            ArrayList arrayList = new ArrayList(pinnedShortcuts.size());
            for (ShortcutInfo shortcutInfo : pinnedShortcuts) {
                LogUtil.d("ShortcutsUtil", "removeItem: ", shortcutInfo.getId());
                if (!set.contains(shortcutInfo.getId())) {
                    arrayList.add(shortcutInfo.getId());
                }
            }
            shortcutManager.disableShortcuts(arrayList, context.getResources().getString(R$string.IDS_hw_shortcuts_disable));
            shortcutManager.removeDynamicShortcuts(arrayList);
        } catch (IllegalArgumentException | IllegalStateException e2) {
            LogUtil.e("ShortcutsUtil", "IllegalArgumentException or IllegalStateException", e2.getMessage());
        }
    }

    private static ShortcutInfo crF_(Context context, nqm nqmVar) {
        Intent intent = new Intent();
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), nqmVar.b());
        intent.setFlags(268468224);
        intent.setData(Uri.parse(nqmVar.e()));
        String string = context.getApplicationContext().getString(nqmVar.c());
        LogUtil.d("ShortcutsUtil", "getResources shortcuts name", string);
        return new ShortcutInfo.Builder(context, nqmVar.e()).setShortLabel(string).setLongLabel(string).setDisabledMessage(context.getResources().getString(R$string.IDS_hw_shortcuts_disable)).setIcon(Icon.createWithResource(context, nqmVar.a())).setRank(nqmVar.d()).setIntent(intent).build();
    }

    private static boolean d(String str) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return b.contains(str);
        }
        return c.contains(str);
    }

    private static String c(String str) {
        String str2;
        LogUtil.d("ShortcutsUtil", "enter getStringFile");
        str2 = "";
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("ShortcutsUtil", "filePath is empty");
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
                fileInputStream.close();
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.e("ShortcutsUtil", "getStringFile IOException");
        }
        return str2;
    }

    public static void e(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_SHORTCUTS_CLICK_2190003.value(), hashMap, 0);
    }

    public static void b(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click_enter_health_app", 1);
        hashMap.put("card_id", 1);
        ixx.d().d(context, AnalyticsValue.FA_ENTER_APP.value(), hashMap, 0);
        if (str.equals("SC_FA_CARD_EXERCISE")) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("click_start_sports", 1);
            ixx.d().d(context, AnalyticsValue.FA_START_SPORT.value(), hashMap2, 0);
        }
        b();
    }

    private static void b() {
        LogUtil.d("ShortcutsUtil", "doBiFromFaStartApp");
        HashMap hashMap = new HashMap(16);
        hashMap.put("from", 1);
        hashMap.put("click", 1);
        hashMap.put("FAPackageName", "com.huawei.ohos.health");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    public static void a(Context context) {
        if (!a()) {
            LogUtil.c("ShortcutsUtil", "is not support");
            return;
        }
        if (context == null) {
            LogUtil.c("ShortcutsUtil", "context is null");
            return;
        }
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager == null) {
            LogUtil.c("ShortcutsUtil", "get ShortcutManager failed");
            return;
        }
        try {
            List<ShortcutInfo> pinnedShortcuts = shortcutManager.getPinnedShortcuts();
            ArrayList arrayList = new ArrayList(pinnedShortcuts.size());
            for (ShortcutInfo shortcutInfo : pinnedShortcuts) {
                if (!"watchface_supermarket".equals(shortcutInfo.getId())) {
                    arrayList.add(shortcutInfo.getId());
                }
            }
            shortcutManager.disableShortcuts(arrayList, context.getResources().getString(R$string.IDS_hw_shortcuts_disable));
            shortcutManager.removeAllDynamicShortcuts();
        } catch (IllegalArgumentException | IllegalStateException e2) {
            LogUtil.e("ShortcutsUtil", "IllegalArgumentException or IllegalStateException", e2.getMessage());
        }
        SharedPreferenceManager.e(context, String.valueOf(10000), "shortcuts", "0", (StorageParams) null);
    }

    private static String d(Locale locale) {
        if (locale == null) {
            LogUtil.c("ShortcutsUtil", "locale is null");
            return "";
        }
        return locale.toString();
    }

    private static boolean d(Context context) {
        if (context == null) {
            LogUtil.c("ShortcutsUtil", "context is null");
            return false;
        }
        return !d(Locale.getDefault()).equals(SharedPreferenceManager.b(context, String.valueOf(10000), "store_last_language"));
    }

    public static void c(Context context) {
        if (!a()) {
            LogUtil.c("ShortcutsUtil", "is not support");
            return;
        }
        if (context == null) {
            LogUtil.c("ShortcutsUtil", "context is null");
        } else if (d(context)) {
            LogUtil.c("ShortcutsUtil", "change language or coldStart with completely new version first");
            if ("1".equals(SharedPreferenceManager.b(context, String.valueOf(10000), "shortcuts"))) {
                c(context, 0, drd.d("com.huawei.health_common_config", "shortcuts", "json"));
            }
        }
    }
}
