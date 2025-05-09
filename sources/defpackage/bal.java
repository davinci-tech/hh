package defpackage;

import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.ResponseCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class bal implements HiDataOperateListener {
    public final /* synthetic */ ResponseCallback e;

    @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
    public final void onResult(int i, Object obj) {
        this.e.onResponse(i, obj);
    }
}
