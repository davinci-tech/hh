package defpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.tabtemplate.SportSubTabConfig;
import java.util.List;

/* loaded from: classes6.dex */
public class nig implements BaseTemplateConfig {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("sub_tab")
    @Expose
    private List<SportSubTabConfig> f15301a;

    public List<SportSubTabConfig> d() {
        return this.f15301a;
    }
}
