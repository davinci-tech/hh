package defpackage;

import com.huawei.health.R;

/* loaded from: classes9.dex */
public class oty extends oti {
    @Override // defpackage.oti, defpackage.oth
    public int c() {
        return 2;
    }

    @Override // defpackage.oth
    public String a() {
        return nsf.h(R.string.IDS_main_watch_heart_rate_unit_string);
    }

    @Override // defpackage.oth
    public String d() {
        return this.e == null ? "" : String.valueOf(this.e.requestAvgHeartRate());
    }
}
