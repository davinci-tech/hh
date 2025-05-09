package com.huawei.operation.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import defpackage.bzw;
import defpackage.ixx;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class KakaTaskUtils {
    private static final int MSG_REMIND = 4;
    private static final String PARAM_ACTIVITY_ID = "activityId";
    private static final String TAG = "PluginOperation_KakaTaskUtils";

    private KakaTaskUtils() {
    }

    public static void onAchieveEvent(String str, Context context) {
        LogUtil.c(TAG, "onAchieveEvent onPageStarted url = ", str);
        onWalkUpEvent(str, context);
        onRemindMsgRefreshEvent(str, context);
    }

    public static void onWalkUpEvent(String str, Context context) {
        if (isWalkupUrl(str, context)) {
            bzw.e().finishKakaTask(BaseApplication.e(), SmartMsgConstant.MSG_TYPE_RIDE_USER, null);
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            ixx.d().d(context, AnalyticsValue.WALKUP_VIEW_1100042.value(), hashMap, 0);
        }
    }

    public static boolean isWalkupUrl(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] stringArray = context.getResources().getStringArray(R.array._2130968719_res_0x7f04008f);
        if (stringArray.length != 1) {
            return false;
        }
        return Uri.decode(str).contains(stringArray[0]);
    }

    public static void onRemindMsgRefreshEvent(String str, Context context) {
        if (!TextUtils.isEmpty(str) && Uri.decode(str).contains("activityId")) {
            LogUtil.a(TAG, "onRemindMsgRefreshEvent refresh.");
            bzw.e().doPostByKey(context, 4);
        }
    }
}
