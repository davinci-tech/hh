package defpackage;

import com.huawei.health.R;

/* loaded from: classes9.dex */
public class otq extends oth {
    @Override // defpackage.oth
    public int c() {
        return 6;
    }

    @Override // defpackage.oth
    public String b() {
        return this.e == null ? "" : String.valueOf(this.e.getExtendDataInt("golfSwingCount"));
    }

    @Override // defpackage.oth
    public String e() {
        return this.e == null ? "" : nsf.a(R.plurals._2130903269_res_0x7f0300e5, this.e.getExtendDataInt("golfSwingCount"), "");
    }
}
