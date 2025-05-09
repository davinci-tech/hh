package defpackage;

import android.content.Context;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class aaj extends aam {
    aaj(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void a(String str, String str2, List list) {
        super.a(str, str2, (List<UnstructData>) list);
    }

    @Override // defpackage.aam
    protected void c(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV102", "processUploadData");
        this.G.b(str, str2, list, list2, e(list2), list3, z, iSyncServiceCallback);
    }

    protected List<UnstructData> e(List<SyncData> list) {
        abd.c("CloudSyncV102", "getLocalFile");
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            abd.c("CloudSyncV102", "getLocalFile error: modify is null or empty");
            return arrayList;
        }
        abd.c("CloudSyncV102", "getLocalFile: modify.size = " + list.size());
        abd.a("CloudSyncV102", "getLocalFile: modify = " + list.toString());
        aad aadVar = new aad();
        ArrayList arrayList2 = new ArrayList();
        Iterator<SyncData> it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().getLuid());
        }
        abd.a("CloudSyncV102", "getLocalFile: queryList = " + arrayList2.toString());
        ArrayList<UnstructData> b = aadVar.b(arrayList2, this.y);
        abd.c("CloudSyncV102", "localFileList.size = " + b.size());
        abd.a("CloudSyncV102", "localFileList: " + b.toString());
        return b;
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public /* bridge */ /* synthetic */ void g() {
        super.g();
    }

    @Override // defpackage.aam, com.huawei.android.hicloud.sync.logic.c
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
