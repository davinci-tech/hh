package defpackage;

import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes4.dex */
public class fqt {

    @SerializedName("negativeStretchedMap")
    private Map<String, Integer> b;

    @SerializedName("positiveStretchedMap")
    private Map<String, Integer> d;

    public SparseArray<Integer> aDG_() {
        return aDE_(this.d);
    }

    public SparseArray<Integer> aDF_() {
        return aDE_(this.b);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("MuscleStretchingBean{positiveStretchedMap=");
        stringBuffer.append(this.d);
        stringBuffer.append(", negativeStretchedMap=").append(this.b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    private SparseArray<Integer> aDE_(Map<String, Integer> map) {
        if (map == null) {
            return new SparseArray<>(0);
        }
        SparseArray<Integer> sparseArray = new SparseArray<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sparseArray.put(nsn.e(entry.getKey()), entry.getValue());
        }
        return sparseArray;
    }
}
