package defpackage;

import android.content.res.Resources;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class pru {

    /* renamed from: a, reason: collision with root package name */
    private float f16241a;
    private float b;
    private long c;
    private long d;
    private ArrayList<HeartRateDetailData> e;

    public long e() {
        return this.c;
    }

    public void d(long j) {
        this.c = j;
    }

    public long b() {
        return this.d;
    }

    public void e(long j) {
        this.d = j;
    }

    public void c(float f) {
        this.f16241a = f;
    }

    public void a(float f) {
        this.b = f;
    }

    public void d(ArrayList<HeartRateDetailData> arrayList) {
        this.e = arrayList;
    }

    public ArrayList<HeartRateDetailData> c() {
        return this.e;
    }

    public String d() {
        Resources resources = BaseApplication.e().getResources();
        int i = (int) this.b;
        return resources.getQuantityString(R.plurals._2130903151_res_0x7f03006f, i, Integer.valueOf(i), Integer.valueOf((int) this.f16241a));
    }
}
