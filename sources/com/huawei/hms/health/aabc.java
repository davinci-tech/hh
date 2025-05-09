package com.huawei.hms.health;

import com.huawei.hmf.tasks.OnFailureListener;

/* loaded from: classes4.dex */
class aabc implements OnFailureListener {
    final /* synthetic */ aaba aab;

    @Override // com.huawei.hmf.tasks.OnFailureListener
    public void onFailure(Exception exc) {
        aabz.aabc("HealthKitAuthHubFragment", "can not get auth url");
        this.aab.aaba("");
    }

    aabc(aaba aabaVar) {
        this.aab = aabaVar;
    }
}
