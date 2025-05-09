package defpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.ui.main.stories.privacy.template.model.config.PrivacyDataFragmentConfig;

/* loaded from: classes7.dex */
public class rsf implements BaseTemplateConfig {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("double_group_data_config")
    @Expose
    private PrivacyDataFragmentConfig f16894a;

    @SerializedName("group_data_config")
    @Expose
    private PrivacyDataFragmentConfig b;

    @SerializedName("day_data_config")
    @Expose
    private PrivacyDataFragmentConfig c;

    @SerializedName("activity_presenter")
    @Expose
    private String d;

    @SerializedName(CloudParamKeys.INFO)
    @Expose
    private ryo e;

    public ryo c() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public PrivacyDataFragmentConfig e() {
        return this.b;
    }

    public PrivacyDataFragmentConfig a() {
        return this.c;
    }

    public PrivacyDataFragmentConfig d() {
        return this.f16894a;
    }
}
