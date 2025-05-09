package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes7.dex */
public class raw {

    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private Long b;

    @SerializedName("devId")
    private String c;

    @SerializedName(BleConstants.DEV_INFO)
    private rat e;

    public rat c() {
        return this.e;
    }

    public String toString() {
        return "CommonDevice{devId=" + this.c + ", devInfo=" + this.e + ", userId=" + this.b + '}';
    }
}
