package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ehz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("meanHr")
    double f12025a;

    @SerializedName("maxHr")
    double b;

    @SerializedName("firstValley")
    int c;

    @SerializedName("isPremBeat")
    int d;

    @SerializedName("afibRstFlag")
    int e;

    @SerializedName("measureType")
    int f;

    @SerializedName("predictProb")
    double g;

    @SerializedName("predictValue")
    int h;

    @SerializedName("minHr")
    double i;

    @SerializedName("premPredictProb")
    double j;

    @SerializedName("premRstFlag")
    int k;

    @SerializedName("rriArrayLen")
    int l;

    @SerializedName(UserCloseRecord.TIME_STAMP)
    long o;

    @SerializedName("rriArray")
    List<Integer> n = new ArrayList();

    @SerializedName("waveClass")
    List<Integer> m = new ArrayList();
}
