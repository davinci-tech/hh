package com.huawei.harmonyos.interwork.base.bundle;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.bwq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class ApplicationInfo implements Parcelable {
    public static final Parcelable.Creator<ApplicationInfo> CREATOR = new Parcelable.Creator<ApplicationInfo>() { // from class: com.huawei.harmonyos.interwork.base.bundle.ApplicationInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: Ag_, reason: merged with bridge method [inline-methods] */
        public final ApplicationInfo createFromParcel(Parcel parcel) {
            return new ApplicationInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final ApplicationInfo[] newArray(int i) {
            return new ApplicationInfo[i];
        }
    };
    private static final int MAX_LIMIT_SIZE = 1024;
    private String description;
    private int descriptionId;
    private String icon;
    private int iconId;
    private String label;
    private int labelId;
    private List<bwq> moduleInfos;
    private String name;
    private List<String> permissions;
    private int supportedModes;
    private Boolean systemApp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ApplicationInfo() {
        this.name = "";
        this.icon = "";
        this.label = "";
        this.description = "";
        this.systemApp = Boolean.FALSE;
        this.supportedModes = 0;
        this.labelId = 0;
        this.iconId = 0;
        this.descriptionId = 0;
        this.permissions = new ArrayList(0);
        this.moduleInfos = new ArrayList(0);
    }

    public ApplicationInfo(ApplicationInfo applicationInfo) {
        this.name = "";
        this.icon = "";
        this.label = "";
        this.description = "";
        this.systemApp = Boolean.FALSE;
        this.supportedModes = 0;
        this.labelId = 0;
        this.iconId = 0;
        this.descriptionId = 0;
        this.permissions = new ArrayList(0);
        this.moduleInfos = new ArrayList(0);
        this.name = applicationInfo.name;
        this.icon = applicationInfo.icon;
        this.label = applicationInfo.label;
        this.description = applicationInfo.description;
        this.permissions = applicationInfo.permissions;
        this.systemApp = applicationInfo.systemApp;
        this.moduleInfos = applicationInfo.moduleInfos;
        this.supportedModes = applicationInfo.supportedModes;
        this.labelId = applicationInfo.labelId;
        this.iconId = applicationInfo.iconId;
        this.descriptionId = applicationInfo.descriptionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getLabel() {
        return this.label;
    }

    public String getDescription() {
        return this.description;
    }

    public int getSupportedModes() {
        return this.supportedModes;
    }

    public List<String> getModuleSourceDirs() {
        ArrayList arrayList = new ArrayList();
        Iterator<bwq> it = this.moduleInfos.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c());
        }
        return arrayList;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public List<bwq> getModuleInfos() {
        return this.moduleInfos;
    }

    public Boolean getSystemApp() {
        return this.systemApp;
    }

    public int getIconId() {
        return this.iconId;
    }

    public int getDescriptionId() {
        return this.descriptionId;
    }

    public int getLabelId() {
        return this.labelId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.icon);
        parcel.writeString(this.label);
        parcel.writeString(this.description);
        parcel.writeBoolean(this.systemApp.booleanValue());
        parcel.writeInt(this.supportedModes);
        parcel.writeInt(this.iconId);
        parcel.writeInt(this.descriptionId);
        parcel.writeInt(this.labelId);
        parcel.writeInt(this.permissions.size());
        Iterator<String> it = this.permissions.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
        parcel.writeInt(this.moduleInfos.size());
        for (bwq bwqVar : this.moduleInfos) {
            parcel.writeString(bwqVar.e());
            parcel.writeString(bwqVar.c());
        }
    }

    public ApplicationInfo(Parcel parcel) {
        this.name = "";
        this.icon = "";
        this.label = "";
        this.description = "";
        this.systemApp = Boolean.FALSE;
        this.supportedModes = 0;
        this.labelId = 0;
        this.iconId = 0;
        this.descriptionId = 0;
        this.permissions = new ArrayList(0);
        this.moduleInfos = new ArrayList(0);
        this.name = parcel.readString();
        this.icon = parcel.readString();
        this.label = parcel.readString();
        this.description = parcel.readString();
        this.systemApp = Boolean.valueOf(parcel.readBoolean());
        this.supportedModes = parcel.readInt();
        this.iconId = parcel.readInt();
        this.descriptionId = parcel.readInt();
        this.labelId = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt > 1024) {
            return;
        }
        for (int i = 0; i < readInt; i++) {
            this.permissions.add(parcel.readString());
        }
        int readInt2 = parcel.readInt();
        if (readInt2 > 1024) {
            return;
        }
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.moduleInfos.add(new bwq(parcel.readString(), parcel.readString()));
        }
    }
}
