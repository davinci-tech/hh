package defpackage;

import android.content.Context;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.SyncDataCompatible;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class aar extends aao {
    aar(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void b(String str, String str2, int i) {
        abd.c("CloudSyncV107", "notifySyncModel dataType = " + str2 + ", syncModel = " + i);
        aal.b(i);
        this.G.d(str, str2, i, this.O);
    }

    @Override // defpackage.aam, com.huawei.android.hicloud.sync.logic.c
    public void f(int i) {
        abd.c("CloudSyncV107", "processParitcalSucQueryResult errCode = " + i);
        List<SyncData> o = o();
        if (o != null && e(o, (List<String>) null) == 0) {
            d(o);
            if (abl.d(this.C)) {
                d(i);
            } else {
                this.G.c(this.x, this.y, c(i), o, null, false, this.O);
            }
        }
    }

    @Override // defpackage.aam, com.huawei.android.hicloud.sync.logic.c
    public void j() {
        abd.c("CloudSyncV107", "processQueryResult");
        List<SyncData> o = o();
        if (o != null && e(o, (List<String>) null) == 0) {
            d(o);
            if (abl.d(this.C)) {
                i();
            } else {
                this.G.a(this.x, this.y, o, (List<String>) null, false, this.O);
            }
        }
    }

    @Override // defpackage.aao, defpackage.aam
    protected void c(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV107", "processUploadData");
        ArrayList arrayList = new ArrayList(8);
        if (list != null && !list.isEmpty()) {
            for (SyncData syncData : list) {
                arrayList.add(new SyncDataCompatible(syncData.getVersion(), syncData.getLuid(), syncData.getGuid(), syncData.getUnstruct_uuid(), syncData.getEtag(), syncData.getData(), new ArrayList(), new ArrayList(), new ArrayList(), syncData.getRecycleStatus(), syncData.getRecycleTime(), syncData.getExtensionData(), syncData.getExtraParam(), aap.b(syncData.getDownFileList()), aap.b(syncData.getDeleteFileList()), aap.b(syncData.getFileList())));
            }
        }
        ArrayList arrayList2 = new ArrayList(8);
        if (list2 != null && !list2.isEmpty()) {
            for (SyncData syncData2 : list2) {
                arrayList2.add(new SyncDataCompatible(syncData2.getVersion(), syncData2.getLuid(), syncData2.getGuid(), syncData2.getUnstruct_uuid(), syncData2.getEtag(), syncData2.getData(), new ArrayList(), new ArrayList(), new ArrayList(), syncData2.getRecycleStatus(), syncData2.getRecycleTime(), syncData2.getExtensionData(), syncData2.getExtraParam(), aap.b(syncData2.getDownFileList()), aap.b(syncData2.getDeleteFileList()), aap.b(syncData2.getFileList())));
            }
        }
        this.G.c(str, str2, arrayList, arrayList2, new ArrayList(), list3, z, iSyncServiceCallback);
    }

    @Override // defpackage.aam
    protected void b(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, List<SyncData> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV107", "processUploadDataWithLost");
        ArrayList arrayList = new ArrayList(8);
        if (list != null && !list.isEmpty()) {
            for (SyncData syncData : list) {
                arrayList.add(new SyncDataCompatible(syncData.getVersion(), syncData.getLuid(), syncData.getGuid(), syncData.getUnstruct_uuid(), syncData.getEtag(), syncData.getData(), new ArrayList(), new ArrayList(), new ArrayList(), syncData.getRecycleStatus(), syncData.getRecycleTime(), syncData.getExtensionData(), syncData.getExtraParam(), aap.b(syncData.getDownFileList()), aap.b(syncData.getDeleteFileList()), aap.b(syncData.getFileList())));
            }
        }
        ArrayList arrayList2 = new ArrayList(8);
        if (list2 != null && !list2.isEmpty()) {
            for (SyncData syncData2 : list2) {
                arrayList2.add(new SyncDataCompatible(syncData2.getVersion(), syncData2.getLuid(), syncData2.getGuid(), syncData2.getUnstruct_uuid(), syncData2.getEtag(), syncData2.getData(), new ArrayList(), new ArrayList(), new ArrayList(), syncData2.getRecycleStatus(), syncData2.getRecycleTime(), syncData2.getExtensionData(), syncData2.getExtraParam(), aap.b(syncData2.getDownFileList()), aap.b(syncData2.getDeleteFileList()), aap.b(syncData2.getFileList())));
            }
        }
        ArrayList arrayList3 = new ArrayList(8);
        if (list4 != null && !list4.isEmpty()) {
            for (SyncData syncData3 : list4) {
                arrayList3.add(new SyncDataCompatible(syncData3.getVersion(), syncData3.getLuid(), syncData3.getGuid(), syncData3.getUnstruct_uuid(), syncData3.getEtag(), syncData3.getData(), new ArrayList(), new ArrayList(), new ArrayList(), syncData3.getRecycleStatus(), syncData3.getRecycleTime(), syncData3.getExtensionData(), syncData3.getExtraParam(), aap.b(syncData3.getDownFileList()), aap.b(syncData3.getDeleteFileList()), aap.b(syncData3.getFileList())));
            }
        }
        this.G.a(str, str2, arrayList, arrayList2, list3, arrayList3, z, iSyncServiceCallback);
    }
}
