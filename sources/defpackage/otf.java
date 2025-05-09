package defpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.ui.homehealth.search.template.SearchResultConfig;
import com.huawei.ui.homehealth.search.template.SearchSubTabConfig;

/* loaded from: classes9.dex */
public class otf implements BaseTemplateConfig {

    @SerializedName("result_page")
    @Expose
    private SearchResultConfig b;

    @SerializedName("default_page")
    @Expose
    private SearchSubTabConfig d;

    public SearchSubTabConfig b() {
        return this.d;
    }

    public SearchResultConfig d() {
        return this.b;
    }
}
