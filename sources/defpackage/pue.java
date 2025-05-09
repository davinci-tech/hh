package defpackage;

import com.huawei.networkclient.cache.ICacheStrategy;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class pue implements ICacheStrategy {

    /* renamed from: a, reason: collision with root package name */
    private String f16262a;

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
        return this.f16262a;
    }

    public pue d(String str) {
        this.f16262a = str;
        return this;
    }
}
