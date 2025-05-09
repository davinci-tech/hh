package com.huawei.hms.kit.awareness.b;

import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.kit.awareness.internal.hmscore.AwarenessInBean;
import com.huawei.hms.kit.awareness.internal.hmscore.AwarenessOutBean;

/* loaded from: classes4.dex */
class HHC extends HuaweiApi<Api.ApiOptions> {

    /* renamed from: a, reason: collision with root package name */
    private static AwarenessInBean f4811a = new AwarenessInBean();
    private HHA b;
    private int c;
    private int d;

    Task<AwarenessOutBean> c() {
        return doWrite(this.b);
    }

    int b() {
        return this.c;
    }

    void a(AwarenessInBean awarenessInBean) {
        this.b = new HHA(awarenessInBean);
    }

    void a(int i) {
        this.d = i;
        this.b.setApiLevel(i);
    }

    static final class HHA extends TaskApiCall<HmsClient, AwarenessOutBean> {

        /* renamed from: a, reason: collision with root package name */
        private static final String f4812a = "awareness_kit.getAwarenessService";

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.hms.common.internal.TaskApiCall
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void doExecute(HmsClient hmsClient, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<AwarenessOutBean> taskCompletionSource) {
            taskCompletionSource.setResult(new AwarenessOutBean(responseErrorCode.getErrorCode(), responseErrorCode.getParcelable()));
        }

        HHA(AwarenessInBean awarenessInBean) {
            super(f4812a, awarenessInBean.toJsonString(), null);
        }
    }

    void a() {
        this.c = this.d;
    }

    HHC(Context context) {
        super(context, (Api<HHH>) new Api("HuaweiAwareness.API"), new HHH(), new HHD());
        this.b = new HHA(f4811a);
    }
}
