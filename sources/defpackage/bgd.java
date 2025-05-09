package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.CloudParamKeys;

/* loaded from: classes8.dex */
public class bgd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deviceCloudState")
    private String f353a;

    @SerializedName("state")
    private String b;

    @SerializedName(CloudParamKeys.INFO)
    private bgg d;

    public void c(String str) {
        this.f353a = str;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(bgg bggVar) {
        this.d = bggVar;
    }
}
