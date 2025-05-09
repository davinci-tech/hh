package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jmc {

    /* renamed from: a, reason: collision with root package name */
    private int f13954a;
    private String c;
    private String[] d;

    public int e() {
        return this.f13954a;
    }

    public String c() {
        return this.c;
    }

    public String[] d() {
        String[] strArr = this.d;
        if (strArr != null) {
            return (String[]) strArr.clone();
        }
        LogUtil.c("ApduCardBean", "swInfos is null");
        return null;
    }
}
