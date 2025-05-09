package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes2.dex */
public final class Device extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Device> CREATOR = new zzo();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    private final int type;
    private final String zzcc;
    private final String zzcd;
    private final String zzce;
    private final int zzcf;

    public static Device getLocalDevice(Context context) {
        int zza = com.google.android.gms.internal.fitness.zzi.zza(context);
        return new Device(Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, Settings.Secure.getString(context.getContentResolver(), "android_id"), zza, 2);
    }

    public Device(String str, String str2, String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    public Device(String str, String str2, String str3, int i, int i2) {
        this.zzcc = (String) Preconditions.checkNotNull(str);
        this.zzcd = (String) Preconditions.checkNotNull(str2);
        if (str3 == null) {
            throw new IllegalStateException("Device UID is null.");
        }
        this.zzce = str3;
        this.type = i;
        this.zzcf = i2;
    }

    @Deprecated
    private Device(String str, String str2, String str3, String str4, int i, int i2) {
        this(str, str2, str4, i, 2);
    }

    public final String getManufacturer() {
        return this.zzcc;
    }

    public final String getModel() {
        return this.zzcd;
    }

    public final String getUid() {
        return this.zzce;
    }

    public final int getType() {
        return this.type;
    }

    final String getStreamIdentifier() {
        return String.format("%s:%s:%s", this.zzcc, this.zzcd, this.zzce);
    }

    public final String toString() {
        return String.format("Device{%s:%s:%s}", getStreamIdentifier(), Integer.valueOf(this.type), Integer.valueOf(this.zzcf));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.zzcc, device.zzcc) && Objects.equal(this.zzcd, device.zzcd) && Objects.equal(this.zzce, device.zzce) && this.type == device.type && this.zzcf == device.zzcf;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcc, this.zzcd, this.zzce, Integer.valueOf(this.type));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getManufacturer(), false);
        SafeParcelWriter.writeString(parcel, 2, getModel(), false);
        SafeParcelWriter.writeString(parcel, 4, getUid(), false);
        SafeParcelWriter.writeInt(parcel, 5, getType());
        SafeParcelWriter.writeInt(parcel, 6, this.zzcf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
