package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils;
import java.util.List;

/* loaded from: classes5.dex */
public final /* synthetic */ class jzm implements ContactsDatabaseUtils.ResultCallback {
    public final /* synthetic */ List e;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
    public final void onResult(Object obj) {
        this.e.add((String) obj);
    }
}
