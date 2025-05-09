package defpackage;

import com.huawei.health.hwuserlabelmgr.manager.LabelMatcher;

/* loaded from: classes3.dex */
public class dyb implements LabelMatcher {
    private String c;
    private double d;
    private double e;

    public dyb(double d, double d2, String str) {
        this.d = d;
        this.e = d2;
        this.c = str;
    }

    @Override // com.huawei.health.hwuserlabelmgr.manager.LabelMatcher
    public String getMatchResult(double d) {
        if (d <= this.d || d > this.e) {
            return null;
        }
        return this.c;
    }
}
