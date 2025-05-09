package com.huawei.harmonyos.interwork.base.bundle;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ElementName implements Parcelable {
    public static final Parcelable.Creator<ElementName> CREATOR = new Parcelable.Creator<ElementName>() { // from class: com.huawei.harmonyos.interwork.base.bundle.ElementName.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: Ah_, reason: merged with bridge method [inline-methods] */
        public final ElementName createFromParcel(Parcel parcel) {
            return new ElementName(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final ElementName[] newArray(int i) {
            return new ElementName[i];
        }
    };
    private static final int PARAMS_LENGTH = 3;
    private String abilityName;
    private String bundleName;
    private String deviceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ElementName() {
        this.deviceId = "";
        this.bundleName = "";
        this.abilityName = "";
    }

    public ElementName(String str, String str2, String str3) {
        this.deviceId = str;
        this.bundleName = str2;
        this.abilityName = str3;
    }

    public ElementName(ElementName elementName) {
        this(elementName.deviceId, elementName.bundleName, elementName.abilityName);
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getAbilityName() {
        return this.abilityName;
    }

    public void setAbilityName(String str) {
        this.abilityName = str;
    }

    public String getBundleName() {
        return this.bundleName;
    }

    public void setBundleName(String str) {
        this.bundleName = str;
    }

    public String getURI() {
        return this.deviceId + "/" + this.bundleName + "/" + this.abilityName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bundleName);
        parcel.writeString(this.abilityName);
        parcel.writeString(this.deviceId);
    }

    public ElementName(Parcel parcel) {
        this.deviceId = "";
        this.bundleName = "";
        this.abilityName = "";
        this.bundleName = parcel.readString();
        this.abilityName = parcel.readString();
        this.deviceId = parcel.readString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElementName)) {
            return false;
        }
        ElementName elementName = (ElementName) obj;
        return Objects.equals(this.deviceId, elementName.deviceId) && Objects.equals(this.bundleName, elementName.bundleName) && Objects.equals(this.abilityName, elementName.abilityName);
    }

    public int hashCode() {
        return Objects.hash(this.deviceId, this.bundleName, this.abilityName);
    }

    public static ElementName createRelative(String str, String str2, String str3) {
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("the parameter abilityName cannot be empty");
        }
        return new ElementName(str3, str, getFullClassName(str, str2));
    }

    public String getShortClassName() {
        int length;
        int length2;
        String str = this.abilityName;
        if (str != null && str.startsWith(this.bundleName) && (length2 = this.abilityName.length()) > (length = this.bundleName.length()) && this.abilityName.charAt(length) == '.') {
            return this.abilityName.substring(length, length2);
        }
        return this.abilityName;
    }

    public static ElementName unflattenFromString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("the parameter elementName is null");
        }
        String[] split = str.split("/");
        if (split.length != 3) {
            throw new IllegalArgumentException("the parameter elementName is illegal");
        }
        String str2 = split[0];
        String str3 = split[1];
        return new ElementName(str2, str3, getFullClassName(str3, split[2]));
    }

    private static String getFullClassName(String str, String str2) {
        if (str2.charAt(0) != '.') {
            return str2;
        }
        return str + str2;
    }
}
