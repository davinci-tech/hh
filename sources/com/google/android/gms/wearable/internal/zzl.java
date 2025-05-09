package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    private int id;

    @Nullable
    private final String packageName;
    private final String zzbf;

    @Nullable
    private final String zzbg;
    private final String zzbh;
    private final String zzbi;
    private final String zzbj;

    @Nullable
    private final String zzbk;
    private final byte zzbl;
    private final byte zzbm;
    private final byte zzbn;
    private final byte zzbo;

    public zzl(int i, String str, @Nullable String str2, String str3, String str4, String str5, @Nullable String str6, byte b, byte b2, byte b3, byte b4, @Nullable String str7) {
        this.id = i;
        this.zzbf = str;
        this.zzbg = str2;
        this.zzbh = str3;
        this.zzbi = str4;
        this.zzbj = str5;
        this.zzbk = str6;
        this.zzbl = b;
        this.zzbm = b2;
        this.zzbn = b3;
        this.zzbo = b4;
        this.packageName = str7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.id);
        SafeParcelWriter.writeString(parcel, 3, this.zzbf, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbg, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbh, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbi, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbj, false);
        String str = this.zzbk;
        if (str == null) {
            str = this.zzbf;
        }
        SafeParcelWriter.writeString(parcel, 8, str, false);
        SafeParcelWriter.writeByte(parcel, 9, this.zzbl);
        SafeParcelWriter.writeByte(parcel, 10, this.zzbm);
        SafeParcelWriter.writeByte(parcel, 11, this.zzbn);
        SafeParcelWriter.writeByte(parcel, 12, this.zzbo);
        SafeParcelWriter.writeString(parcel, 13, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        int i = this.id;
        String str = this.zzbf;
        String str2 = this.zzbg;
        String str3 = this.zzbh;
        String str4 = this.zzbi;
        String str5 = this.zzbj;
        String str6 = this.zzbk;
        byte b = this.zzbl;
        byte b2 = this.zzbm;
        byte b3 = this.zzbn;
        byte b4 = this.zzbo;
        String str7 = this.packageName;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + a.D + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length() + String.valueOf(str7).length());
        sb.append("AncsNotificationParcelable{, id=");
        sb.append(i);
        sb.append(", appId='");
        sb.append(str);
        sb.append("', dateTime='");
        sb.append(str2);
        sb.append("', notificationText='");
        sb.append(str3);
        sb.append("', title='");
        sb.append(str4);
        sb.append("', subtitle='");
        sb.append(str5);
        sb.append("', displayName='");
        sb.append(str6);
        sb.append("', eventId=");
        sb.append((int) b);
        sb.append(", eventFlags=");
        sb.append((int) b2);
        sb.append(", categoryId=");
        sb.append((int) b3);
        sb.append(", categoryCount=");
        sb.append((int) b4);
        sb.append(", packageName='");
        sb.append(str7);
        sb.append("'}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzl zzlVar = (zzl) obj;
            if (this.id != zzlVar.id || this.zzbl != zzlVar.zzbl || this.zzbm != zzlVar.zzbm || this.zzbn != zzlVar.zzbn || this.zzbo != zzlVar.zzbo || !this.zzbf.equals(zzlVar.zzbf)) {
                return false;
            }
            String str = this.zzbg;
            if (str == null ? zzlVar.zzbg != null : !str.equals(zzlVar.zzbg)) {
                return false;
            }
            if (!this.zzbh.equals(zzlVar.zzbh) || !this.zzbi.equals(zzlVar.zzbi) || !this.zzbj.equals(zzlVar.zzbj)) {
                return false;
            }
            String str2 = this.zzbk;
            if (str2 == null ? zzlVar.zzbk != null : !str2.equals(zzlVar.zzbk)) {
                return false;
            }
            String str3 = this.packageName;
            String str4 = zzlVar.packageName;
            if (str3 != null) {
                return str3.equals(str4);
            }
            if (str4 == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.id;
        int hashCode = this.zzbf.hashCode();
        String str = this.zzbg;
        int hashCode2 = str != null ? str.hashCode() : 0;
        int hashCode3 = this.zzbh.hashCode();
        int hashCode4 = this.zzbi.hashCode();
        int hashCode5 = this.zzbj.hashCode();
        String str2 = this.zzbk;
        int hashCode6 = str2 != null ? str2.hashCode() : 0;
        byte b = this.zzbl;
        byte b2 = this.zzbm;
        byte b3 = this.zzbn;
        byte b4 = this.zzbo;
        String str3 = this.packageName;
        return ((((((((((((((((((((((i + 31) * 31) + hashCode) * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + hashCode5) * 31) + hashCode6) * 31) + b) * 31) + b2) * 31) + b3) * 31) + b4) * 31) + (str3 != null ? str3.hashCode() : 0);
    }
}
