package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.Settings;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kaq {
    private static final String[] c;

    static {
        if (CommonUtil.av()) {
            c = new String[]{"hiCall_enable", "hiCall_registered", "hiCall_nick_name", "hiCall_registed_phonenum", "hicall_registering_background", "hicall_enable_manually", "hicall_phone_number_country_iso", "hicall_enable_message"};
            return;
        }
        if (CommonUtil.au()) {
            c = new String[]{"hiCall_enable", "hiCall_registered", "hiCall_nick_name", "hiCall_registed_phonenum", "hicall_registering_background", "hicall_enable_manually", "hicall_phone_number_country_iso"};
        } else if (CommonUtil.ba()) {
            c = new String[]{"hiCall_enable", "hiCall_registered", "hiCall_nick_name", "hiCall_registed_phonenum", "hicall_registering_background", "hicall_enable_manually"};
        } else {
            c = new String[0];
        }
    }

    public static JSONObject b(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context == null) {
            LogUtil.h("ContactSync", 1, "HiCallUtils", "buildHiCallStatus: context is null");
            return jSONObject;
        }
        Uri bMp_ = bMp_();
        if (bMp_ == Uri.EMPTY) {
            LogUtil.a("ContactSync", 1, "HiCallUtils", "buildHiCallStatus: uri is empty. ");
            return jSONObject;
        }
        if (e(context)) {
            try {
                Cursor query = context.getContentResolver().query(bMp_, c, null, null, null);
                try {
                    if (query == null) {
                        LogUtil.h("ContactSync", 1, "HiCallUtils", "buildHiCallStatus: cursor is null");
                        if (query != null) {
                            query.close();
                        }
                        return jSONObject;
                    }
                    while (query.moveToNext()) {
                        jSONObject.put("hiCall_enable", query.getInt(0));
                        jSONObject.put("hiCall_registered", query.getInt(1));
                        jSONObject.put("hiCall_nick_name", query.getString(2));
                        jSONObject.put("hiCall_registed_phonenum", query.getString(3));
                        jSONObject.put("hicall_registering_background", query.getInt(4));
                        jSONObject.put("hicall_enable_manually", query.getInt(5));
                        if (CommonUtil.au()) {
                            jSONObject.put("hicall_phone_number_country_iso", query.getString(6));
                        }
                        if (CommonUtil.av()) {
                            jSONObject.put("hicall_enable_message", query.getInt(7));
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (SQLiteException | IllegalArgumentException | SecurityException | JSONException e) {
                LogUtil.b("", "ContactSync", 1, "HiCallUtils", ExceptionUtils.d(e));
            }
        }
        return jSONObject;
    }

    private static boolean e(Context context) {
        if (context == null) {
            LogUtil.a("ContactSync", 1, "HiCallUtils", "isMeetingEnable: context is empty. ");
            return false;
        }
        try {
            int i = Settings.Secure.getInt(context.getContentResolver(), "hiCall_enable", -1);
            LogUtil.a("ContactSync", 1, "HiCallUtils", "isMeetingEnable: enable is ", Integer.valueOf(i));
            return i != 0;
        } catch (Exception e) {
            LogUtil.b("", "ContactSync", 1, "HiCallUtils", ExceptionUtils.d(e));
            return true;
        }
    }

    public static String e(kaj kajVar) {
        if (kajVar == null) {
            LogUtil.h("ContactSync", 1, "HiCallUtils", "getHiCallModelString invalid parameter");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_phone_number", kajVar.p());
            jSONObject.put("device_com_id", kajVar.c());
            jSONObject.put(DeviceCategoryFragment.DEVICE_TYPE, kajVar.f());
            jSONObject.put("device_is_private", kajVar.r());
            jSONObject.put("device_profile", kajVar.i());
            jSONObject.put("is_same_vibration", kajVar.q());
            jSONObject.put("device_ordinal", kajVar.o());
            jSONObject.put("device_normalized_number", kajVar.n());
            jSONObject.put("device_nick_name", kajVar.d());
            jSONObject.put("device_display_name", kajVar.b());
            jSONObject.put("device_id", kajVar.h());
            jSONObject.put("message_profile", kajVar.l());
            jSONObject.put("latest_login_time", kajVar.m());
            jSONObject.put("insert_time", kajVar.k());
            jSONObject.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, kajVar.a());
            jSONObject.put("hi_call_user_name", kajVar.j());
            jSONObject.put("device_status", kajVar.g());
            jSONObject.put("hi_call_account_id", kajVar.e());
        } catch (JSONException unused) {
            LogUtil.b("", "ContactSync", 1, "HiCallUtils", "getHiCallModelString JSONException");
        }
        return jSONObject.toString();
    }

    private static boolean a() {
        return c() >= 19;
    }

    private static int c() {
        return EmuiBuild.f13113a;
    }

    private static int e() {
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("ContactSync", 1, "HiCallUtils", "getHiCallVersion: context is null.");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.huawei.hwvoipservice", 128);
            if (applicationInfo.metaData == null) {
                LogUtil.h("ContactSync", 1, "HiCallUtils", "getHiCallVersion: appInfo.metaData is null.");
                return 0;
            }
            return applicationInfo.metaData.getInt("com.huawei.hwvoipservice.arch.version");
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("", "ContactSync", 1, "getHiCallVersion: the parameters are null");
            return 0;
        }
    }

    public static Uri bMp_() {
        if (!CommonUtil.bh() || !a()) {
            LogUtil.a("ContactSync", 1, "HiCallUtils", "buildHiCallStatus: not support.");
            return Uri.EMPTY;
        }
        return Uri.parse("content://" + (e() == 20 ? "com.huawei.meetime.hicall" : "com.huawei.hwvoipservice.hicall") + "/caas_hicall_status");
    }

    public static String b() {
        return e() == 20 ? "com.huawei.meetime.account" : "com.huawei.hwvoipservice.account";
    }
}
