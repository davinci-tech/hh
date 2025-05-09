package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.main.stories.oa.goodthingsrecommended.api.GoodThingsRecommendedApi;
import health.compact.a.util.LogUtil;

/* loaded from: classes9.dex */
public class rjc implements GoodThingsRecommendedApi {
    private Handler d = new Handler(Looper.getMainLooper());

    @Override // com.huawei.ui.main.stories.oa.goodthingsrecommended.api.GoodThingsRecommendedApi
    public void getShoppingInfoList(rjf rjfVar, final UiCallback<rjg> uiCallback) {
        LogUtil.d("GoodThingsRecommendedImpl", "getShoppingInfoList: Request Received, processing.....");
        riz.d().a(rjfVar, new ResultCallback<rjg>() { // from class: rjc.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(rjg rjgVar) {
                LogUtil.d("GoodThingsRecommendedImpl", "getShoppingInfoList: onSuccess . ", rjgVar.getResultCode());
                uiCallback.onSuccess(rjc.this.d, rjgVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(-1, th.getMessage());
            }
        });
    }
}
