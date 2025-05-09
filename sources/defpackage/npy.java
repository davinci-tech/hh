package defpackage;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class npy {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(BleConstants.KEY_PATH)
    private String f15434a;

    public static class a extends TypeToken<ArrayList<npy>> {
    }

    public String a() {
        return this.f15434a;
    }
}
