package com.huawei.hms.health;

import com.huawei.hmf.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
class aabd implements OnSuccessListener<String> {
    final /* synthetic */ aaba aab;

    @Override // com.huawei.hmf.tasks.OnSuccessListener
    public void onSuccess(String str) {
        aabz.aabb("HealthKitAuthHubFragment", "get auth url success");
        this.aab.aaba(str);
    }

    aabd(aaba aabaVar) {
        this.aab = aabaVar;
    }
}
