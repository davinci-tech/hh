package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class kur extends kul {

    @SerializedName("dataType")
    private List<kun> d;

    public int[] j() {
        ArrayList arrayList = new ArrayList();
        Iterator<kun> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().a()));
        }
        return CommonUtil.b(arrayList);
    }

    @Override // defpackage.kul
    public String toString() {
        return "SamplePointDeleteRequest{dataTypes=" + this.d + '}';
    }
}
