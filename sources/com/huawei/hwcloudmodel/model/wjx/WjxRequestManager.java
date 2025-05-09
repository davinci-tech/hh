package com.huawei.hwcloudmodel.model.wjx;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import defpackage.jbs;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class WjxRequestManager {
    private static final String TAG = "WjxRequestManager";

    private WjxRequestManager() {
    }

    public static void getSurveyId(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().submit(new Callable<Void>() { // from class: com.huawei.hwcloudmodel.model.wjx.WjxRequestManager.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                SurveyIdResponse requestSurveyId = WjxRequestManager.requestSurveyId();
                if (requestSurveyId == null) {
                    LogUtil.h(WjxRequestManager.TAG, "surveyIdRsp is null ");
                    IBaseResponseCallback.this.d(-1, VastAttribute.ERROR);
                    return null;
                }
                LogUtil.a(WjxRequestManager.TAG, "surveyIdRsp.getResultCode(): ", requestSurveyId.getResultCode());
                IBaseResponseCallback.this.d(requestSurveyId.getResultCode().intValue(), requestSurveyId.getSurveyId());
                LogUtil.a(WjxRequestManager.TAG, "getSurveyId end");
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SurveyIdResponse requestSurveyId() {
        SurveyIdResponse e = jbs.a(BaseApplication.getContext()).e(new SurveyIdRequest());
        if (e != null) {
            return e;
        }
        LogUtil.h(TAG, "response = null");
        return null;
    }
}
