package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class eir {

    @SerializedName(UserCloseRecord.TIME_STAMP)
    long d;

    @SerializedName("dataArray")
    List<eis> e = new ArrayList();
}
