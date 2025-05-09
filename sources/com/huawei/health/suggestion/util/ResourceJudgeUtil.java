package com.huawei.health.suggestion.util;

import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.util.ResourceJudgeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import health.compact.a.Services;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public class ResourceJudgeUtil {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface JudgeErrorType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface JudgeResType {
    }

    public static void c(int i, String str, final int i2, final JudgeCallback<Integer> judgeCallback) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("Suggestion_VipUtil", "payApi is null in resourceJudge.");
            judgeCallback.onJudgeCallback(Integer.valueOf(i2));
        } else {
            payApi.resourceAuth(i, str, new IBaseResponseCallback() { // from class: ghs
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    ResourceJudgeUtil.a(JudgeCallback.this, i2, i3, obj);
                }
            });
        }
    }

    public static /* synthetic */ void a(JudgeCallback judgeCallback, int i, int i2, Object obj) {
        LogUtil.a("Suggestion_VipUtil", "resourceJudge errorCode: ", Integer.valueOf(i2));
        if (i2 != 0) {
            judgeCallback.onJudgeCallback(Integer.valueOf(i));
            return;
        }
        if (obj == null) {
            LogUtil.h("Suggestion_VipUtil", "resourceJudge resultData is null");
            judgeCallback.onJudgeCallback(Integer.valueOf(i));
            return;
        }
        LogUtil.h("Suggestion_VipUtil", "resourceJudge resultData is ", obj);
        if (obj instanceof Integer) {
            judgeCallback.onJudgeCallback((Integer) obj);
        } else {
            judgeCallback.onJudgeCallback(Integer.valueOf(i));
        }
    }
}
