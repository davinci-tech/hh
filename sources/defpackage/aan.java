package defpackage;

import android.content.Context;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.SyncDataCompatible;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class aan extends aaj {
    aan(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
        aal.d(true);
        r();
    }

    private void r() {
        try {
            abe e = abe.e(this.C);
            if (e.d()) {
                return;
            }
            e.c(true);
            new aab().c();
            abd.c("CloudSyncV104", "first time recycleProcess clear ctag");
        } catch (Exception e2) {
            abd.b("CloudSyncV104", "first time recycleProcess local Ctag clear exception " + e2.toString());
        }
    }

    @Override // defpackage.aaj, defpackage.aam
    protected void c(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV104", "processUploadData");
        List<UnstructData> e = e(list2);
        ArrayList arrayList = new ArrayList(8);
        if (list != null && !list.isEmpty()) {
            for (SyncData syncData : list) {
                arrayList.add(new SyncDataCompatible(syncData.getVersion(), syncData.getLuid(), syncData.getGuid(), syncData.getUnstruct_uuid(), syncData.getEtag(), syncData.getData(), syncData.getDownFileList(), syncData.getDeleteFileList(), syncData.getFileList(), syncData.getRecycleStatus(), syncData.getRecycleTime()));
            }
        }
        ArrayList arrayList2 = new ArrayList(8);
        if (list2 != null && !list2.isEmpty()) {
            for (SyncData syncData2 : list2) {
                arrayList2.add(new SyncDataCompatible(syncData2.getVersion(), syncData2.getLuid(), syncData2.getGuid(), syncData2.getUnstruct_uuid(), syncData2.getEtag(), syncData2.getData(), syncData2.getDownFileList(), syncData2.getDeleteFileList(), syncData2.getFileList(), syncData2.getRecycleStatus(), syncData2.getRecycleTime()));
            }
        }
        this.G.c(str, str2, arrayList, arrayList2, e, list3, z, iSyncServiceCallback);
    }
}
