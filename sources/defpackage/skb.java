package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class skb {
    private List<sjy> devices;

    @SerializedName("resultCode")
    private int resultCode;

    public List<sjy> getDevices() {
        if (koq.b(this.devices)) {
            return new ArrayList(16);
        }
        return this.devices;
    }

    public List<sjy> getBindDevices() {
        if (koq.b(this.devices)) {
            return new ArrayList(16);
        }
        ArrayList arrayList = new ArrayList();
        for (sjy sjyVar : this.devices) {
            if (sjyVar.getBindStatus() == 1) {
                arrayList.add(sjyVar);
            }
        }
        return arrayList;
    }

    public int getResultCode() {
        return this.resultCode;
    }
}
