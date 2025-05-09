package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class oue extends oth {
    @Override // defpackage.oth
    public int c() {
        return 10;
    }

    @Override // defpackage.oth
    public String a(long j) {
        return String.valueOf(j);
    }

    @Override // defpackage.oth
    public String a(int i) {
        if (this.e == null) {
            return "";
        }
        String e = gwg.e(this.e);
        if (!TextUtils.isEmpty(e) && LanguageUtil.h(BaseApplication.e())) {
            return String.format(nsf.h(R.string._2130843638_res_0x7f0217f6), gwg.e(BaseApplication.e(), i), e);
        }
        return gwg.e(BaseApplication.e(), i);
    }
}
