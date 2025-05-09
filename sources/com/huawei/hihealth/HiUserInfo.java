package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;

/* loaded from: classes.dex */
public class HiUserInfo implements Parcelable {
    public static final int BIRTHDAY_DEFAULT = 19900801;
    public static final int BIRTHDAY_YEAR_DIVISOR = 10000;
    public static final int CREATE_TIME_DEFAULT = 1;
    public static final Parcelable.Creator<HiUserInfo> CREATOR = new Parcelable.Creator<HiUserInfo>() { // from class: com.huawei.hihealth.HiUserInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buZ_, reason: merged with bridge method [inline-methods] */
        public HiUserInfo createFromParcel(Parcel parcel) {
            return new HiUserInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HiUserInfo[] newArray(int i) {
            return new HiUserInfo[i];
        }
    };
    public static final int DATA_ALL = 0;
    public static final int DATA_CLOUD = 805306368;
    public static final int DP_DATA_ONLY = 536870912;
    public static final int GENDER_DEFAULT = 1;
    public static final int GENDER_FEMALE = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_SECRET = 2;
    public static final int GENDER_UNKNOWN = -1;
    public static final int HEIGHT_DEFAULT = 170;
    public static final int UNIT_CM_KG = 0;
    public static final int UNIT_DEFAULT = 0;
    public static final int UNIT_FT_LB = 1;
    public static final int UNIT_UNKNOWN = -1;
    public static final int UP_DATA_ONLY = 268435456;
    public static final int USER_MGR = 1073741824;
    public static final int USER_NORMAL = 0;
    public static final float WEIGHT_DEFAULT = 60.0f;
    private int age;
    private int birthday;
    private long createTime;
    private String email;
    private int gender;
    private String headImgUrl;
    private int height;
    private String huid;
    private String mobile;
    private int modifiedIntent;
    private String name;
    private int ownerId;
    private int relateType;
    private int unitType;
    private int user;
    private float weight;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiUserInfo() {
        this.user = 0;
        this.modifiedIntent = 0;
        this.gender = -100;
        this.birthday = -100;
        this.height = 0;
        this.weight = -100.0f;
        this.createTime = System.currentTimeMillis();
        this.unitType = 0;
    }

    protected HiUserInfo(Parcel parcel) {
        this.user = 0;
        this.modifiedIntent = 0;
        this.gender = -100;
        this.birthday = -100;
        this.height = 0;
        this.weight = -100.0f;
        this.createTime = System.currentTimeMillis();
        this.unitType = 0;
        this.ownerId = parcel.readInt();
        this.huid = parcel.readString();
        this.name = parcel.readString();
        this.gender = parcel.readInt();
        this.birthday = parcel.readInt();
        int readInt = parcel.readInt();
        if ((readInt & 1073741824) != 0) {
            this.user = 1073741824;
        }
        int i = readInt & 536870912;
        if (i == 0 && (readInt & 268435456) == 268435456) {
            this.modifiedIntent = 268435456;
        } else if (i == 536870912 && (readInt & 268435456) == 0) {
            this.modifiedIntent = 536870912;
        } else {
            this.modifiedIntent = 0;
        }
        this.height = 65535 & readInt;
        this.weight = parcel.readFloat();
        this.age = parcel.readInt();
        this.email = parcel.readString();
        this.mobile = parcel.readString();
        this.headImgUrl = parcel.readString();
        this.unitType = parcel.readInt();
        this.relateType = parcel.readInt();
        this.createTime = parcel.readLong();
    }

    public String getHuid() {
        return this.huid;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public int getBirthday() {
        return this.birthday;
    }

    public void setBirthday(int i) {
        this.birthday = i;
        if (isBirthdayValid()) {
            this.age = (HiDateUtil.c(System.currentTimeMillis()) / 10000) - (i / 10000);
        }
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float f) {
        this.weight = f;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getHeadImgUrl() {
        return this.headImgUrl;
    }

    public void setHeadImgUrl(String str) {
        this.headImgUrl = str;
    }

    public int getUnitType() {
        return this.unitType;
    }

    public void setUnitType(int i) {
        this.unitType = i;
    }

    public int getRelateType() {
        return this.relateType;
    }

    public void setRelateType(int i) {
        this.relateType = i;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int i) {
        this.ownerId = i;
    }

    public int getAge() {
        int i = this.age;
        if (i > 0) {
            return i;
        }
        if (isBirthdayValid()) {
            return (HiDateUtil.c(System.currentTimeMillis()) / 10000) - (this.birthday / 10000);
        }
        return this.age;
    }

    public int getGenderOrDefaultValue() {
        if (isGenderValid()) {
            return this.gender;
        }
        return 1;
    }

    public int getHeightOrDefaultValue() {
        if (isHeightValid()) {
            return this.height;
        }
        if (this.unitType == 0) {
            return 170;
        }
        return HiCommonUtil.d(170);
    }

    public float getWeightOrDefaultValue() {
        if (isWeightValid()) {
            return this.weight;
        }
        if (this.unitType == 0) {
            return 60.0f;
        }
        return (float) HiCommonUtil.e(60.0d);
    }

    public int getAgeOrDefaultValue() {
        int i = this.birthday;
        if (!isBirthdayValid()) {
            i = BIRTHDAY_DEFAULT;
        }
        return (HiDateUtil.c(System.currentTimeMillis()) / 10000) - (i / 10000);
    }

    public void setAge(int i) {
        this.age = i;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        if (j <= 0) {
            return;
        }
        this.createTime = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ownerId);
        parcel.writeString(this.huid);
        parcel.writeString(this.name);
        parcel.writeInt(this.gender);
        parcel.writeInt(this.birthday);
        parcel.writeInt(this.height | this.user | this.modifiedIntent);
        parcel.writeFloat(this.weight);
        parcel.writeInt(this.age);
        parcel.writeString(this.email);
        parcel.writeString(this.mobile);
        parcel.writeString(this.headImgUrl);
        parcel.writeInt(this.unitType);
        parcel.writeInt(this.relateType);
        parcel.writeLong(this.createTime);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiUserInfo{, ownerId=");
        stringBuffer.append(this.ownerId);
        stringBuffer.append(", relateType=").append(this.relateType);
        stringBuffer.append(", createTime=").append(this.createTime);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public int getModifiedIntent() {
        return this.modifiedIntent;
    }

    public void setModifiedIntent(int i) {
        this.modifiedIntent = i;
    }

    public int getUser() {
        return this.user;
    }

    public boolean isGenderValid() {
        int i = this.gender;
        return i == 1 || i == 0 || i == 2;
    }

    public boolean isBirthdayValid() {
        return this.birthday > 0;
    }

    public boolean isHeightValid() {
        return this.height > 0;
    }

    public boolean isWeightValid() {
        return this.weight > 0.0f;
    }

    public void setUser(int i) {
        this.user = i;
    }
}
