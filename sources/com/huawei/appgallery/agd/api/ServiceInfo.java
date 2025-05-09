package com.huawei.appgallery.agd.api;

import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;
import java.util.List;

/* loaded from: classes2.dex */
public class ServiceInfo extends AutoParcelable {
    public static final Parcelable.Creator<ServiceInfo> CREATOR = new AutoParcelable.AutoCreator(ServiceInfo.class);

    @EnableAutoParcel(4)
    private AbilityInfo abilityInfo;

    @EnableAutoParcel(1)
    private String bundleName;

    @EnableAutoParcel(3)
    private List<FormInfo> formInfos;

    @EnableAutoParcel(2)
    private List<String> moduleNames;

    public void setModuleNames(List<String> list) {
        this.moduleNames = list;
    }

    public static class FormInfo extends AutoParcelable {
        public static final Parcelable.Creator<FormInfo> CREATOR = new AutoParcelable.AutoCreator(FormInfo.class);

        @EnableAutoParcel(3)
        private List<String> dimensions;

        @EnableAutoParcel(2)
        private String formName;

        @EnableAutoParcel(1)
        private String moduleName;

        public void setDimensions(List<String> list) {
            this.dimensions = list;
        }

        public FormInfo(String str, String str2) {
            this.moduleName = str;
            this.formName = str2;
        }
    }

    public void setFormInfos(List<FormInfo> list) {
        this.formInfos = list;
    }

    /* loaded from: classes8.dex */
    public static class AbilityInfo extends AutoParcelable {
        public static final Parcelable.Creator<AbilityInfo> CREATOR = new AutoParcelable.AutoCreator(AbilityInfo.class);

        @EnableAutoParcel(2)
        private String abilityName;

        @EnableAutoParcel(1)
        private String moduleName;

        public AbilityInfo(String str, String str2) {
            this.moduleName = str;
            this.abilityName = str2;
        }
    }

    public void setAbilityInfo(AbilityInfo abilityInfo) {
        this.abilityInfo = abilityInfo;
    }

    public ServiceInfo(String str) {
        this.bundleName = str;
    }
}
