package com.huawei.operation.cloud;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.CloudParamKeys;
import defpackage.cea;
import defpackage.lqi;
import defpackage.mtd;
import defpackage.mte;
import defpackage.mtl;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes9.dex */
public class OperationCloudUtil {
    private static final String FUNCTION_ENTRANCE = "/operationgeneral/app/v1/functionentrance";
    private static final String FUNCTION_ENTRANCE_ANON = "/operationgeneral/app/v1/functionentrance/anon";
    private static final String R_TAG = "R_OperationCloudUtil";
    private static final String TAG = "OperationCloudUtil";

    private OperationCloudUtil() {
    }

    private static boolean isVerifyNetwork() {
        boolean aa = CommonUtil.aa(BaseApplication.e());
        boolean i = Utils.i();
        ReleaseLogUtil.b(R_TAG, "isVerify isNetworkConnected ", Boolean.valueOf(aa), " isAllowLogin ", Boolean.valueOf(i));
        return aa && i;
    }

    private static <T> void callHttpGet(String str, Map<String, String> map, Map<String, String> map2, Class<T> cls, ResultCallback<T> resultCallback) {
        LogUtil.a(TAG, "callHttpGet url ", str, " headers ", map, " queryParams ", map2, " responseType ", cls, " callback ", resultCallback);
        try {
            lqi.d().c(str, map, map2, cls, resultCallback);
        } catch (OutOfMemoryError e) {
            ReleaseLogUtil.c(R_TAG, "callHttpGet error ", ExceptionUtils.d(e));
        }
    }

    private static String getFunctionEntranceUrl(boolean z) {
        String url = GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl");
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append(z ? FUNCTION_ENTRANCE : FUNCTION_ENTRANCE_ANON);
        return sb.toString();
    }

    private static Map<String, String> getFunctionEntranceQueryParams() {
        HashMap hashMap = new HashMap();
        hashMap.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(nsn.b()));
        hashMap.put("countryCode", GRSManager.a(BaseApplication.e()).getCommonCountryCode());
        hashMap.put("clientVersion", AppBundleBuildConfig.l());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void functionEntrance(final String str, final boolean z, final ResponseCallback<List<mte>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a(R_TAG, "functionEntrance callback is null tag ", str, " isLogin ", Boolean.valueOf(z));
            return;
        }
        if (!isVerifyNetwork()) {
            responseCallback.onResponse(-1, null);
            return;
        }
        boolean o = Utils.o();
        ReleaseLogUtil.b(R_TAG, "functionEntrance tag ", str, " isLogin ", Boolean.valueOf(z), " isOversea ", Boolean.valueOf(o));
        if (!o) {
            responseCallback.onResponse(-1, null);
        } else if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.cloud.OperationCloudUtil$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OperationCloudUtil.functionEntrance(str, z, responseCallback);
                }
            });
        } else {
            callHttpGet(getFunctionEntranceUrl(z), cea.d(), getFunctionEntranceQueryParams(), mtd.class, new ResultCallback<mtd>() { // from class: com.huawei.operation.cloud.OperationCloudUtil.1
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(mtd mtdVar) {
                    LogUtil.a(OperationCloudUtil.TAG, "functionEntrance onSuccess tag ", str, " response ", mtdVar);
                    responseCallback.onResponse(mtdVar.c(), mtdVar.b());
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.a(OperationCloudUtil.R_TAG, "functionEntrance onFailure tag ", str, " throwable ", ExceptionUtils.d(th));
                    responseCallback.onResponse(-1, null);
                }
            });
        }
    }

    public static void functionEntrance(String str, boolean z) {
        functionEntrance(str, z, new ResponseCallback() { // from class: com.huawei.operation.cloud.OperationCloudUtil$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                OperationCloudUtil.lambda$functionEntrance$1(i, (List) obj);
            }
        });
    }

    static /* synthetic */ void lambda$functionEntrance$1(int i, List list) {
        ReleaseLogUtil.b(R_TAG, "functionEntrance errorCode ", Integer.valueOf(i));
        if (i != 0) {
            return;
        }
        String e = HiJsonUtil.e(list);
        String e2 = mtl.e();
        boolean equals = Objects.equals(e, e2);
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.b(R_TAG, "functionEntrance isSuccess ", Boolean.valueOf(mtl.a()));
        } else {
            mtl.b((List<mte>) list);
        }
        LogUtil.a(TAG, "functionEntrance isSame ", Boolean.valueOf(equals), " json ", e, " cacheJson ", e2);
        if (equals) {
            return;
        }
        ReleaseLogUtil.b(R_TAG, "functionEntrance isSame false");
        ObserverManagerUtil.c(ObserveLabels.OPERATION_FUNCTION_ENTRANCE, Integer.valueOf(i), list);
    }
}
