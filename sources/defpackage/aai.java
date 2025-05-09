package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import java.util.List;

/* loaded from: classes8.dex */
class aai extends aam {
    aai(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
    }

    @Override // defpackage.aam, com.huawei.android.hicloud.sync.logic.c
    public void a(Bundle bundle) {
        this.M = bundle.getInt("hicloud_new_version");
        abd.c("CloudSyncV101", "newVersion = " + this.M);
        if (e(this.M)) {
            this.G.a(this.x, this.y, new aab().b(), this.z, this.A, this.O);
        }
    }

    @Override // defpackage.aam, com.huawei.android.hicloud.sync.logic.c
    public void a(String str, List<String> list, List<String> list2) {
        abd.c("CloudSyncV101", "App call: endSyncV101");
        this.G.a(str, list, list2, null, this.O);
    }
}
