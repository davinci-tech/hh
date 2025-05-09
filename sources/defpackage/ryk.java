package defpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.ui.main.stories.template.health.config.HealthHasDataConfig;
import com.huawei.ui.main.stories.template.health.config.HealthNoDeviceConfig;
import java.util.List;

/* loaded from: classes7.dex */
public class ryk implements BaseTemplateConfig {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("no_device_config")
    @Expose
    private HealthNoDeviceConfig f16966a;

    @SerializedName("activity_presenter")
    @Expose
    private String b;

    @SerializedName(CloudParamKeys.INFO)
    @Expose
    private ryo c;

    @SerializedName("operation_data")
    @Expose
    private ryl d;

    @SerializedName("has_data_config")
    @Expose
    private HealthHasDataConfig e;

    @SerializedName("version_controller")
    @Expose
    private List<Object> i;

    public ryo a() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public ryl b() {
        return this.d;
    }

    public HealthHasDataConfig d() {
        return this.e;
    }

    public HealthNoDeviceConfig e() {
        return this.f16966a;
    }
}
