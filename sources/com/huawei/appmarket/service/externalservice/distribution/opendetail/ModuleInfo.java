package com.huawei.appmarket.service.externalservice.distribution.opendetail;

import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;

/* loaded from: classes3.dex */
public class ModuleInfo extends AutoParcelable {
    public static final Parcelable.Creator<ModuleInfo> CREATOR = new AutoParcelable.AutoCreator(ModuleInfo.class);

    @EnableAutoParcel(2)
    private String abilityName;

    @EnableAutoParcel(3)
    private String dimension;

    @EnableAutoParcel(4)
    private String formName;

    @EnableAutoParcel(6)
    private boolean isAddedToDeskTop;

    @EnableAutoParcel(7)
    private boolean isAddedToHiBoard;

    @EnableAutoParcel(5)
    private boolean isServiceOpened;

    @EnableAutoParcel(1)
    private String moduleName;

    public String toString() {
        return "ModuleInfo{moduleName='" + this.moduleName + "', abilityName='" + this.abilityName + "', dimension=" + this.dimension + ", formName='" + this.formName + "', isServiceOpened=" + this.isServiceOpened + ", isAddedToDeskTop=" + this.isAddedToDeskTop + ", isAddedToHiBoard=" + this.isAddedToHiBoard + '}';
    }

    public boolean isServiceOpened() {
        return this.isServiceOpened;
    }

    public boolean isAddedToHiBoard() {
        return this.isAddedToHiBoard;
    }

    public boolean isAddedToDeskTop() {
        return this.isAddedToDeskTop;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getFormName() {
        return this.formName;
    }

    public String getDimension() {
        return this.dimension;
    }

    public String getAbilityName() {
        return this.abilityName;
    }
}
