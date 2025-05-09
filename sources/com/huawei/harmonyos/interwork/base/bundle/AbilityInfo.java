package com.huawei.harmonyos.interwork.base.bundle;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class AbilityInfo implements Parcelable {
    public static final Parcelable.Creator<AbilityInfo> CREATOR = new Parcelable.Creator<AbilityInfo>() { // from class: com.huawei.harmonyos.interwork.base.bundle.AbilityInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: Af_, reason: merged with bridge method [inline-methods] */
        public final AbilityInfo createFromParcel(Parcel parcel) {
            return new AbilityInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final AbilityInfo[] newArray(int i) {
            return new AbilityInfo[i];
        }
    };
    private static final int MAX_DEVICETYPE_SIZE = 50;
    private static final int MAX_LIMIT_SIZE = 1024;
    private ApplicationInfo applicationInfo;
    private int backgroudModes;
    private String bundleName;
    private String className;
    private int defaultFormHeight;
    private int defaultFormWidth;
    private String description;
    private int descriptionId;
    private List<String> deviceCapabilities;
    private String deviceId;
    private List<String> deviceTypes;
    private boolean formEnabled;
    private int formEntity;
    private int iconId;
    private String iconPath;
    private boolean isVisible;
    private String label;
    private int labelId;
    private LaunchMode launchMode;
    private int minFormHeight;
    private int minFormWidth;
    private String moduleName;
    private String name;
    private DisplayOrientation orientation;
    private String packageName;
    private List<String> permissions;
    private AbilityType type;
    private String uri;

    public enum AbilityType {
        UNKNOWN,
        PAGE,
        SERVICE,
        PROVIDER,
        WEB
    }

    public enum DisplayOrientation {
        UNSPECIFIED,
        LANDSCAPE,
        PORTRAIT
    }

    public enum LaunchMode {
        SINGLETON,
        STANDARD
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AbilityInfo() {
        this.packageName = "";
        this.name = "";
        this.label = "";
        this.description = "";
        this.iconPath = "";
        this.isVisible = false;
        this.formEnabled = false;
        this.type = AbilityType.UNKNOWN;
        this.orientation = DisplayOrientation.UNSPECIFIED;
        this.launchMode = LaunchMode.SINGLETON;
        this.bundleName = "";
        this.className = "";
        this.deviceId = "";
        this.uri = "";
        this.moduleName = "";
        this.backgroudModes = 0;
        this.formEntity = 0;
        this.minFormHeight = 0;
        this.defaultFormHeight = 0;
        this.minFormWidth = 0;
        this.defaultFormWidth = 0;
        this.permissions = new ArrayList(0);
        this.deviceTypes = new ArrayList(0);
        this.deviceCapabilities = new ArrayList(0);
        this.applicationInfo = new ApplicationInfo();
    }

    public AbilityInfo(AbilityInfo abilityInfo) {
        this.packageName = "";
        this.name = "";
        this.label = "";
        this.description = "";
        this.iconPath = "";
        this.isVisible = false;
        this.formEnabled = false;
        this.type = AbilityType.UNKNOWN;
        this.orientation = DisplayOrientation.UNSPECIFIED;
        this.launchMode = LaunchMode.SINGLETON;
        this.bundleName = "";
        this.className = "";
        this.deviceId = "";
        this.uri = "";
        this.moduleName = "";
        this.backgroudModes = 0;
        this.formEntity = 0;
        this.minFormHeight = 0;
        this.defaultFormHeight = 0;
        this.minFormWidth = 0;
        this.defaultFormWidth = 0;
        this.permissions = new ArrayList(0);
        this.deviceTypes = new ArrayList(0);
        this.deviceCapabilities = new ArrayList(0);
        this.applicationInfo = new ApplicationInfo();
        this.packageName = abilityInfo.packageName;
        this.name = abilityInfo.name;
        this.label = abilityInfo.label;
        this.description = abilityInfo.description;
        this.iconPath = abilityInfo.iconPath;
        this.isVisible = abilityInfo.isVisible;
        this.formEnabled = abilityInfo.formEnabled;
        this.backgroudModes = abilityInfo.backgroudModes;
        this.type = abilityInfo.type;
        this.orientation = abilityInfo.orientation;
        this.launchMode = abilityInfo.launchMode;
        this.bundleName = abilityInfo.bundleName;
        this.className = abilityInfo.className;
        this.deviceId = abilityInfo.deviceId;
        this.permissions = abilityInfo.permissions;
        this.deviceTypes = abilityInfo.deviceTypes;
        this.deviceCapabilities = abilityInfo.deviceCapabilities;
        this.uri = abilityInfo.uri;
        this.moduleName = abilityInfo.moduleName;
        this.applicationInfo = abilityInfo.applicationInfo;
        this.formEntity = abilityInfo.formEntity;
        this.minFormHeight = abilityInfo.minFormHeight;
        this.defaultFormHeight = abilityInfo.defaultFormHeight;
        this.minFormWidth = abilityInfo.minFormWidth;
        this.defaultFormWidth = abilityInfo.defaultFormWidth;
        this.labelId = abilityInfo.labelId;
        this.descriptionId = abilityInfo.descriptionId;
        this.iconId = abilityInfo.iconId;
    }

    @Deprecated
    public String getPackageName() {
        return this.packageName;
    }

    @Deprecated
    public void setPackageName(String str) {
        this.packageName = str;
    }

    @Deprecated
    public String getName() {
        return this.name;
    }

    @Deprecated
    public void setName(String str) {
        this.name = str;
    }

    public String getLabel() {
        return this.label;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public AbilityType getType() {
        return this.type;
    }

    public void setType(AbilityType abilityType) {
        this.type = abilityType;
    }

    public DisplayOrientation getOrientation() {
        return this.orientation;
    }

    public LaunchMode getLaunchMode() {
        return this.launchMode;
    }

    public String getBundleName() {
        return this.bundleName;
    }

    public void setBundleName(String str) {
        this.bundleName = str;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public List<String> getDeviceTypes() {
        return this.deviceTypes;
    }

    public void setDeviceTypes(List<String> list) {
        this.deviceTypes = list;
    }

    public List<String> getDeviceCapabilities() {
        return this.deviceCapabilities;
    }

    public String getURI() {
        return this.uri;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public boolean getFormEnabled() {
        return this.formEnabled;
    }

    public int getBackgroundModes() {
        return this.backgroudModes;
    }

    public ApplicationInfo getApplicationInfo() {
        return this.applicationInfo;
    }

    public int getFormEntity() {
        return this.formEntity;
    }

    public int getMinFormHeight() {
        return this.minFormHeight;
    }

    public int getDefaultFormHeight() {
        return this.defaultFormHeight;
    }

    public int getMinFormWidth() {
        return this.minFormWidth;
    }

    public int getDefaultFormWidth() {
        return this.defaultFormWidth;
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
        parcel.writeString(this.packageName);
        parcel.writeString(this.name);
        parcel.writeString(this.bundleName);
        parcel.writeString(this.className);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.label);
        parcel.writeString(this.description);
        parcel.writeString(this.iconPath);
        parcel.writeString(this.uri);
        parcel.writeString(this.moduleName);
        parcel.writeBoolean(this.isVisible);
        parcel.writeBoolean(this.formEnabled);
        parcel.writeInt(this.backgroudModes);
        parcel.writeInt(this.formEntity);
        parcel.writeInt(this.minFormHeight);
        parcel.writeInt(this.defaultFormHeight);
        parcel.writeInt(this.minFormWidth);
        parcel.writeInt(this.defaultFormWidth);
        parcel.writeInt(this.iconId);
        parcel.writeInt(this.descriptionId);
        parcel.writeInt(this.labelId);
        parcel.writeInt(this.type.ordinal());
        parcel.writeInt(this.orientation.ordinal());
        parcel.writeInt(this.launchMode.ordinal());
        parcel.writeTypedObject(this.applicationInfo, 0);
        parcel.writeInt(this.permissions.size());
        Iterator<String> it = this.permissions.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
        parcel.writeInt(this.deviceTypes.size());
        Iterator<String> it2 = this.deviceTypes.iterator();
        while (it2.hasNext()) {
            parcel.writeString(it2.next());
        }
        parcel.writeInt(this.deviceCapabilities.size());
        Iterator<String> it3 = this.deviceCapabilities.iterator();
        while (it3.hasNext()) {
            parcel.writeString(it3.next());
        }
    }

    public void setModuleName(String str) {
        this.moduleName = str;
    }

    public AbilityInfo(Parcel parcel) {
        this.packageName = "";
        this.name = "";
        this.label = "";
        this.description = "";
        this.iconPath = "";
        this.isVisible = false;
        this.formEnabled = false;
        this.type = AbilityType.UNKNOWN;
        this.orientation = DisplayOrientation.UNSPECIFIED;
        this.launchMode = LaunchMode.SINGLETON;
        this.bundleName = "";
        this.className = "";
        this.deviceId = "";
        this.uri = "";
        this.moduleName = "";
        this.backgroudModes = 0;
        this.formEntity = 0;
        this.minFormHeight = 0;
        this.defaultFormHeight = 0;
        this.minFormWidth = 0;
        this.defaultFormWidth = 0;
        this.permissions = new ArrayList(0);
        this.deviceTypes = new ArrayList(0);
        this.deviceCapabilities = new ArrayList(0);
        this.applicationInfo = new ApplicationInfo();
        this.packageName = parcel.readString();
        this.name = parcel.readString();
        this.bundleName = parcel.readString();
        this.className = parcel.readString();
        this.deviceId = parcel.readString();
        this.label = parcel.readString();
        this.description = parcel.readString();
        this.iconPath = parcel.readString();
        this.uri = parcel.readString();
        this.moduleName = parcel.readString();
        this.isVisible = parcel.readBoolean();
        this.formEnabled = parcel.readBoolean();
        this.backgroudModes = parcel.readInt();
        this.formEntity = parcel.readInt();
        this.minFormHeight = parcel.readInt();
        this.defaultFormHeight = parcel.readInt();
        this.minFormWidth = parcel.readInt();
        this.defaultFormWidth = parcel.readInt();
        this.iconId = parcel.readInt();
        this.descriptionId = parcel.readInt();
        this.labelId = parcel.readInt();
        int readInt = parcel.readInt();
        this.type = AbilityType.values()[(readInt < 0 || readInt >= AbilityType.values().length) ? 0 : readInt];
        int readInt2 = parcel.readInt();
        this.orientation = DisplayOrientation.values()[(readInt2 < 0 || readInt2 >= DisplayOrientation.values().length) ? 0 : readInt2];
        int readInt3 = parcel.readInt();
        this.launchMode = LaunchMode.values()[(readInt3 < 0 || readInt3 >= LaunchMode.values().length) ? 0 : readInt3];
        this.applicationInfo = (ApplicationInfo) parcel.readTypedObject(ApplicationInfo.CREATOR);
        int readInt4 = parcel.readInt();
        if (readInt4 > 1024) {
            return;
        }
        for (int i = 0; i < readInt4; i++) {
            this.permissions.add(parcel.readString());
        }
        int readInt5 = parcel.readInt();
        if (readInt5 > 50) {
            return;
        }
        for (int i2 = 0; i2 < readInt5; i2++) {
            this.deviceTypes.add(parcel.readString());
        }
        int readInt6 = parcel.readInt();
        if (readInt6 > 1024) {
            return;
        }
        for (int i3 = 0; i3 < readInt6; i3++) {
            this.deviceCapabilities.add(parcel.readString());
        }
    }
}
