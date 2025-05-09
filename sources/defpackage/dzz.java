package defpackage;

import com.huawei.networkclient.cache.ICacheStrategy;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class dzz implements ICacheStrategy {
    private String c;

    @Override // com.huawei.networkclient.cache.ICacheStrategy
    public int getCacheStrategy() {
        return 7;
    }

    @Override // com.huawei.networkclient.cache.ICacheStrategy
    public boolean needEncrypt() {
        return false;
    }

    @Override // com.huawei.networkclient.cache.ICacheStrategy
    public Long getDiskTimeOut() {
        return 2L;
    }

    @Override // com.huawei.networkclient.cache.ICacheStrategy
    public TimeUnit getDiskTimeUnit() {
        return TimeUnit.HOURS;
    }

    @Override // com.huawei.networkclient.cache.ICacheStrategy
    public String getKey() {
        return this.c;
    }

    public dzz d(String str) {
        this.c = str;
        return this;
    }
}
