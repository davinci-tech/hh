package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes5.dex */
public class jiz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("appName")
    private String f13884a;

    @SerializedName("installStatus")
    private int b;

    @SerializedName("errorCode")
    private int c;

    @SerializedName("batteryLevel")
    private int d;

    @SerializedName("downloadProgress")
    private int e;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long f;

    @SerializedName("pkgMode")
    private int g;

    @SerializedName("preInstalled")
    private int h;

    @SerializedName("type")
    private int i;

    @SerializedName("packageName")
    private String j;

    @SerializedName("version")
    private String k;

    @SerializedName("versionCode")
    private int l;

    public String e() {
        return this.j;
    }
}
