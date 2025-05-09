package com.huawei.hianalytics;

import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import java.io.File;

/* loaded from: classes4.dex */
public class e implements IStoragePolicy {

    /* renamed from: a, reason: collision with root package name */
    public File f3848a;

    /* renamed from: a, reason: collision with other field name */
    public final String f30a;

    @Override // com.huawei.hianalytics.framework.policy.IStoragePolicy
    public boolean decide(String str, String str2) {
        str.hashCode();
        if (str.equals(IStoragePolicy.PolicyType.STORAGE_LENGTH)) {
            if (this.f3848a == null) {
                this.f3848a = new File(EnvUtils.getAppContext().getDatabasePath("haformal_event.db").getPath());
            }
            return this.f3848a.length() > i.a().m550a().f48a;
        }
        if (str.equals(IStoragePolicy.PolicyType.NETWORK)) {
            return j.m567b(EnvUtils.getAppContext());
        }
        return true;
    }

    @Override // com.huawei.hianalytics.framework.policy.IStoragePolicy
    public int getDecryptBatchSize(String str) {
        a1 a2 = j.a(this.f30a, str);
        if (a2 == null) {
            return 500;
        }
        return a2.e;
    }

    @Override // com.huawei.hianalytics.framework.policy.IStoragePolicy
    public int getDecryptBatchSleepTime(String str) {
        a1 a2 = j.a(this.f30a, str);
        if (a2 == null) {
            return 0;
        }
        return a2.f;
    }

    @Override // com.huawei.hianalytics.framework.policy.IStoragePolicy
    public int getDecryptMaxBatchSize(String str) {
        a1 a2 = j.a(this.f30a, str);
        if (a2 == null) {
            return 500;
        }
        return a2.d;
    }

    @Override // com.huawei.hianalytics.framework.policy.IStoragePolicy
    public boolean decide(String str, String str2, long j) {
        str.hashCode();
        if (str.equals(IStoragePolicy.PolicyType.STORAGE_CYCLE)) {
            long currentTimeMillis = System.currentTimeMillis();
            a1 a2 = j.a(this.f30a, str2);
            return currentTimeMillis - j > ((long) (a2 != null ? a2.b : 7)) * 86400000;
        }
        if (!str.equals(IStoragePolicy.PolicyType.STORAGE_SIZE)) {
            return false;
        }
        a1 a3 = j.a(this.f30a, str2);
        return j >= ((long) (a3 != null ? a3.f3833a : 30));
    }

    public e(String str) {
        this.f30a = str;
    }
}
