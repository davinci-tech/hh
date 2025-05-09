package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class dxc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("labelName")
    private String f11874a;

    @SerializedName("labelCategory")
    private int b;

    @SerializedName("createTime")
    private long c;

    @SerializedName("description")
    private String d;

    @SerializedName("labelDisplayName")
    private String e;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long f;

    @SerializedName("serviceId")
    private int i;

    @SerializedName("labelThreshold")
    private dxe j;

    public String e() {
        return this.f11874a;
    }

    public dxe c() {
        return this.j;
    }
}
