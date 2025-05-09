package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes7.dex */
public class ike extends ikb {

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private String d;

    public void b(String str) {
        this.d = str;
    }
}
