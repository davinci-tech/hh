package defpackage;

import com.huawei.health.facardagds.FaCardAgdsApi;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class dlf {
    public static FaCardAgdsApi c() {
        return (FaCardAgdsApi) Services.a("HWSmartInteractMgr", FaCardAgdsApi.class);
    }
}
