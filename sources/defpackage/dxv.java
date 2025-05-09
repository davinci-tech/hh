package defpackage;

import com.huawei.health.hwuserlabelmgr.manager.LabelMatcher;

/* loaded from: classes3.dex */
public class dxv implements LabelMatcher {
    private double b;
    private String c;

    public dxv(double d, String str) {
        this.b = d;
        this.c = str;
    }

    @Override // com.huawei.health.hwuserlabelmgr.manager.LabelMatcher
    public String getMatchResult(double d) {
        if (Math.abs(d - this.b) < 1.0E-6d) {
            return this.c;
        }
        return null;
    }
}
