package defpackage;

import android.os.Bundle;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class jua {

    @SerializedName("value")
    private int b;

    @SerializedName("interval")
    private long e;

    public void bJu_(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.e = ((Long) jdy.d(Long.valueOf(bundle.getLong("interval", -1L)))).longValue();
        this.b = ((Integer) jdy.d(Integer.valueOf(bundle.getInt("value", -1)))).intValue();
    }
}
