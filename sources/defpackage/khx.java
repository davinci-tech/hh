package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils;
import java.util.List;

/* loaded from: classes5.dex */
public final /* synthetic */ class khx implements ContactsDatabaseUtils.ResultCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ List f14381a;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
    public final void onResult(Object obj) {
        this.f14381a.add((kil) obj);
    }

    public /* synthetic */ khx(List list) {
        this.f14381a = list;
    }
}
