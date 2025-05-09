package defpackage;

import android.content.Context;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.SyncDataCompatible;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class aao extends aai {
    aao(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
        aal.d(true);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(String str, String str2, List list) {
        super.a(str, str2, (List<UnstructData>) list);
    }

    @Override // defpackage.aam
    protected void c(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV105", "processUploadData");
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
        this.G.c(str, str2, arrayList, arrayList2, new ArrayList(), list3, z, iSyncServiceCallback);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void g() {
        super.g();
    }

    @Override // defpackage.aai, defpackage.aam, com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(String str, List list, List list2) {
        super.a(str, (List<String>) list, (List<String>) list2);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(Context context, String str, List list) {
        super.a(context, str, (List<String>) list);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ int a(Map map, String str) {
        return super.a((Map<String, String>) map, str);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(int i, String str) {
        super.a(i, str);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(String str, String str2, int i, JSONObject jSONObject) {
        super.a(str, str2, i, jSONObject);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(String str, int i, String str2) {
        super.a(str, i, str2);
    }
}
