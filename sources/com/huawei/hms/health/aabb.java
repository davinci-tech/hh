package com.huawei.hms.health;

import com.huawei.hms.health.aaba;

/* loaded from: classes4.dex */
class aabb implements Runnable {
    final /* synthetic */ Boolean aab;
    final /* synthetic */ aaba.aabc aaba;

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.aaba.aab.dismiss();
            aabz.aabb("HealthKitAuthHubFragment", "checkOrAuthorizeHealth get result success");
            if (Boolean.TRUE.equals(this.aab)) {
                aabz.aabb("HealthKitAuthHubFragment", "Health authorize result is success");
                aaba.this.aab();
            } else {
                aabz.aabb("HealthKitAuthHubFragment", "Health authorize result is fail and openAuth is " + this.aaba.aaba);
                aaba.this.aaba(this.aaba.aaba);
            }
        } catch (Throwable unused) {
            aabz.aab("HealthKitAuthHubFragment", "checkAuthorizeHealth success run has exception");
        }
    }

    aabb(aaba.aabc aabcVar, Boolean bool) {
        this.aaba = aabcVar;
        this.aab = bool;
    }
}
