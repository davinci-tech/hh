package com.huawei.operation.h5pro.jsmodules;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.gso;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class Sport extends JsBaseModule {
    private static final int SPORT_GOAL_TYPE = 1;
    private static final int[] SPORT_TYPE = {-1, 257, 258, 259, 10001};
    private static final int SPORT_TYPE_FITNESS = 10001;
    private static final int SPORT_TYPE_INVALID = -1;
    private static final int SPORT_TYPE_NUM = 4;

    @JavascriptInterface
    public void startExercise(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("type");
            start(optInt, jSONObject.optInt(ParsedFieldTag.GOAL));
            LogUtil.a(this.TAG, "startExercise:", Integer.valueOf(optInt));
            onSuccessCallback(j, "");
        } catch (JSONException unused) {
            onFailureCallback(j, "param error");
        }
    }

    private void start(int i, int i2) {
        int transferSportType = transferSportType(i);
        if (transferSportType == 10001) {
            Bundle bundle = new Bundle();
            bundle.putString("SKIP_ALL_COURSE_KEY", "");
            AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(this.mContext);
        } else {
            gso.e().init(this.mContext);
            gso.e().c(0, transferSportType, 1, i2, null, this.mContext);
        }
    }

    private int transferSportType(int i) {
        if (i <= -1 || i > 4) {
            return -1;
        }
        return SPORT_TYPE[i];
    }
}
