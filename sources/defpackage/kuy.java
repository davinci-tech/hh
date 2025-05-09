package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class kuy extends kvb {

    @SerializedName("valueHolder")
    private Map<String, Object> c = new HashMap();

    @SerializedName("subType")
    private int d;

    @SerializedName("type")
    private int e;

    public kuy(HiHealthData hiHealthData) {
        d(hiHealthData.getStartTime());
        c(hiHealthData.getEndTime());
        a(DateFormatUtil.a(hiHealthData.getStartTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_MDY));
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(String str) {
        this.c.put("localDate", str);
    }

    public void b(int i) {
        this.d = i;
    }

    public void d(String str, Object obj) {
        this.c.put(str, obj);
    }
}
