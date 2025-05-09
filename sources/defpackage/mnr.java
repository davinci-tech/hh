package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class mnr {

    @SerializedName(ParsedFieldTag.ACTION_TYPE)
    private int b;

    @SerializedName("actionId")
    private String c;

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public int c() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }
}
