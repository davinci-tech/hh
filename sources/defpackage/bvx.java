package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;

/* loaded from: classes3.dex */
public class bvx {
    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PersonalInfo_HeadImageUtils", "setHeadImage path is null");
            return;
        }
        Context context = BaseApplication.getContext();
        KeyValDbManager.b(context).d("user_profile_head_image_signature" + knl.d(LoginInit.getInstance(context).getAccountInfo(1011)), knl.d(str), null);
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context context = BaseApplication.getContext();
        String str2 = "user_profile_head_image_signature" + knl.d(LoginInit.getInstance(context).getAccountInfo(1011));
        String d = knl.d(str);
        String e = KeyValDbManager.b(context).e(str2);
        if (TextUtils.isEmpty(e) || !e.equals(d)) {
            return false;
        }
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        return new File(a2).exists();
    }

    public static void d(String str) {
        if (str == null) {
            LogUtil.h("PersonalInfo_HeadImageUtils", "setHeadImage path is null");
            return;
        }
        Context context = BaseApplication.getContext();
        SharedPreferenceManager.e(context, String.valueOf(PrebakedEffectId.RT_FLY), "user_profile_head_image_local_prefix_" + knl.d(LoginInit.getInstance(context).getAccountInfo(1011)), str, new StorageParams(1));
    }

    public static String a() {
        LogUtil.a("PersonalInfo_HeadImageUtils", "getHeadImageLocalPath.");
        return LoginInit.getInstance(BaseApplication.getContext()).getHeadImagePath();
    }

    public static void b(final Context context, final String str, final BaseResponseCallback<String> baseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bvx.1
            @Override // java.lang.Runnable
            public void run() {
                Bitmap cHT_ = nrf.cHT_(context, str);
                String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
                if (TextUtils.isEmpty(accountInfo)) {
                    LogUtil.h("PersonalInfo_HeadImageUtils", "downloadHeadPicInBackground run userId is empty");
                    BaseResponseCallback baseResponseCallback2 = baseResponseCallback;
                    if (baseResponseCallback2 != null) {
                        baseResponseCallback2.onResponse(-1, null);
                        return;
                    }
                    return;
                }
                String bEe_ = izx.bEe_(context, accountInfo, cHT_);
                BaseResponseCallback baseResponseCallback3 = baseResponseCallback;
                if (baseResponseCallback3 != null) {
                    baseResponseCallback3.onResponse(0, bEe_);
                }
            }
        });
    }
}
