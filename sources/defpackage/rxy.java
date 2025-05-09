package defpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.tabtemplate.BaseTemplateConfig;

/* loaded from: classes7.dex */
public class rxy<T extends BaseTemplateConfig> {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("version")
    @Expose
    private String f16961a;

    @SerializedName("template_parser")
    @Expose
    private String c;

    @SerializedName("template_config")
    @Expose
    private T d;

    @SerializedName("template_id")
    @Expose
    private String e;

    public String c() {
        return this.f16961a;
    }

    public T b() {
        return this.d;
    }
}
