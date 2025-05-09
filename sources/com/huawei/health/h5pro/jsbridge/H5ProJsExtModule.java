package com.huawei.health.h5pro.jsbridge;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.account.AccountEntry;
import com.huawei.health.h5pro.jsbridge.system.network.NetWorkEntry;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;

/* loaded from: classes3.dex */
public enum H5ProJsExtModule implements Parcelable {
    MODULE_ACCOUNT("account", AccountEntry.class),
    MODULE_NETWORK(HAWebViewInterface.NETWORK, NetWorkEntry.class);

    public static final Parcelable.Creator<H5ProJsExtModule> CREATOR = new Parcelable.Creator<H5ProJsExtModule>() { // from class: com.huawei.health.h5pro.jsbridge.H5ProJsExtModule.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProJsExtModule[] newArray(int i) {
            return new H5ProJsExtModule[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProJsExtModule createFromParcel(Parcel parcel) {
            return H5ProJsExtModule.values()[parcel.readInt()];
        }
    };
    public Class<? extends JsBaseModule> moduleClass;
    public String moduleName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }

    H5ProJsExtModule(String str, Class cls) {
        this.moduleName = str;
        this.moduleClass = cls;
    }
}
