package com.huawei.hms.kit.awareness.internal.communication;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.b.a.c;
import com.huawei.hms.kit.awareness.b.e;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;
import com.huawei.hms.kit.awareness.barrier.internal.f;
import com.huawei.hms.kit.awareness.barrier.internal.g;

/* loaded from: classes9.dex */
public final class InnerKitRequest implements IInnerKitRequest {
    public static final Parcelable.Creator<InnerKitRequest> CREATOR = new Parcelable.Creator<InnerKitRequest>() { // from class: com.huawei.hms.kit.awareness.internal.communication.InnerKitRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InnerKitRequest[] newArray(int i) {
            return new InnerKitRequest[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InnerKitRequest createFromParcel(Parcel parcel) {
            try {
                g gVar = (g) parcel.readParcelable(f.class.getClassLoader());
                AppInfo appInfo = (AppInfo) parcel.readParcelable(AppInfo.class.getClassLoader());
                String readString = parcel.readString();
                if (gVar == null || appInfo == null) {
                    return null;
                }
                return new InnerKitRequest(readString, appInfo, gVar);
            } catch (BadParcelableException unused) {
                c.d(InnerKitRequest.TAG, "parse InnerKitRequest error.", new Object[0]);
                return null;
            }
        }
    };
    private static final int INVALID_UID = -1;
    private static final String TAG = "InnerKitRequest";
    private final AppInfo mAppInfo;
    private final String mKey;
    private final g mRequest;
    private int mUid = -1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRequest, i);
        parcel.writeParcelable(this.mAppInfo, i);
        parcel.writeString(this.mKey);
    }

    public void setCpInfo(int i) {
        this.mUid = i;
        if (this.mRequest instanceof f) {
            ((f) this.mRequest).a(new e(this.mAppInfo.mClientAppId, "", this.mUid, ""));
        }
    }

    @Override // com.huawei.hms.kit.awareness.internal.communication.IInnerKitRequest
    public int getUid() {
        return this.mUid;
    }

    @Override // com.huawei.hms.kit.awareness.internal.communication.IInnerKitRequest
    public g getRequest() {
        return this.mRequest;
    }

    @Override // com.huawei.hms.kit.awareness.internal.communication.IInnerKitRequest
    public String getKey() {
        return this.mKey;
    }

    @Override // com.huawei.hms.kit.awareness.internal.communication.IInnerKitRequest
    public AppInfo getAppInfo() {
        return this.mAppInfo;
    }

    public static InnerKitRequest generate(BarrierUpdateRequest barrierUpdateRequest, String str, AppInfo appInfo) {
        if (barrierUpdateRequest instanceof f) {
            return new InnerKitRequest(str, appInfo, (f) barrierUpdateRequest);
        }
        return null;
    }

    public InnerKitRequest(String str, AppInfo appInfo, g gVar) {
        this.mKey = str;
        this.mAppInfo = appInfo;
        this.mRequest = gVar;
    }
}
