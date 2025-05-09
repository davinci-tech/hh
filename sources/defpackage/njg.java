package defpackage;

import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class njg {

    /* renamed from: a, reason: collision with root package name */
    private String f15327a;
    private Map<String, List<String>> b;
    private List<String> c;
    private String d;

    public String c() {
        return this.f15327a;
    }

    public void e(String str) {
        this.f15327a = str;
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public Map<String, List<String>> d() {
        return this.b;
    }

    public void d(Map<String, List<String>> map) {
        this.b = map;
    }

    public List<String> e() {
        return this.c;
    }

    public void d(List<String> list) {
        this.c = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("ThreeCircleRulesBean{mPromptType=");
        sb.append(this.f15327a);
        sb.append(",mCategory=");
        sb.append(this.d);
        sb.append(",mRuleSet=");
        sb.append(this.b);
        sb.append(",mTotalParams=");
        sb.append(this.c);
        return sb.toString();
    }
}
