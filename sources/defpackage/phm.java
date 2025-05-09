package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes9.dex */
public class phm extends php {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("taskScenarios")
    private int f16133a;

    @SerializedName("appVersion")
    private String b;

    @SerializedName("taskId")
    private String c;

    @SerializedName("reqSource")
    private int d;

    @SerializedName(ParsedFieldTag.KAKA_TASK_RULE)
    private int e;

    public void d(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.f16133a = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.d = i;
    }
}
