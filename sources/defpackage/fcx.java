package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class fcx {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("taskId")
    private int f12450a;

    @SerializedName("taskCode")
    private String b;

    @SerializedName(ParsedFieldTag.TASK_TYPE)
    private int c;

    @SerializedName("dailyTaskCard")
    private fcq d;

    public int c() {
        return this.f12450a;
    }

    public int d() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public fcq a() {
        return this.d;
    }
}
