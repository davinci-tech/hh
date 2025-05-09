package defpackage;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class npw {

    @SerializedName(BleConstants.KEY_PATH)
    private List<nqd> d;

    @SerializedName(HwPayConstant.KEY_SIGN)
    private int e;

    public static class e extends TypeToken<ArrayList<npw>> {
    }

    public List<nqd> b() {
        return this.d;
    }

    public int e() {
        return this.e;
    }
}
