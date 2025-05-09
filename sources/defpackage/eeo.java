package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eeo {
    public static void a(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("PressureMeasureUtils", "callback is null in isAlreadyDoPressureAdjust");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eep
                @Override // java.lang.Runnable
                public final void run() {
                    eeo.c(IBaseResponseCallback.this);
                }
            });
        }
    }

    static /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.pressure_adjust_userinfo");
        if (userPreference != null) {
            iBaseResponseCallback.d(0, Boolean.valueOf(e(userPreference.getValue())));
        } else {
            LogUtil.b("PressureMeasureUtils", "hiUserPreferenceBase is null");
            iBaseResponseCallback.d(-1, false);
        }
    }

    private static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtil.a("PressureMeasureUtils", "isValidAdjustData value :", str);
        HiStressMetaData d = d(str);
        if (d == null) {
            return false;
        }
        int fetchStressCalibFlag = d.fetchStressCalibFlag();
        int fetchStressScore = d.fetchStressScore();
        LogUtil.a("PressureMeasureUtils", "calibFlag = ", Integer.valueOf(fetchStressCalibFlag), "score = ", Integer.valueOf(fetchStressScore));
        return fetchStressCalibFlag == 1 && fetchStressScore > 0 && fetchStressScore < 100;
    }

    public static HiStressMetaData d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("startTime", jSONObject.optLong("startTime", 0L));
            jSONObject.put("endTime", jSONObject.optLong("endTime", 0L));
            return (HiStressMetaData) HiJsonUtil.e(jSONObject.toString(), HiStressMetaData.class);
        } catch (NullPointerException unused) {
            LogUtil.b("PressureMeasureUtils", "adapterJsonAboutTime fromJson nullPointerException.");
            return null;
        } catch (NumberFormatException e) {
            LogUtil.b("PressureMeasureUtils", "adapterJsonAboutTime format exception : ", e.getMessage());
            return null;
        } catch (JSONException unused2) {
            LogUtil.b("PressureMeasureUtils", "adapterJsonAboutTime json exception");
            return null;
        }
    }
}
