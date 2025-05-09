package defpackage;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;

/* loaded from: classes4.dex */
public class hpn {
    public static Drawable bog_(String str, int i) {
        LogUtil.a("SportRecordImageHelper", "getSportRecordDrawable imageName = ", str);
        String str2 = drd.d("com.huawei.health_Sport_Common", "sport_record_image", (String) null) + File.separator;
        BitmapDrawable cHP_ = nrf.cHP_(str2 + str + ".webp");
        if (cHP_ != null) {
            return cHP_;
        }
        BitmapDrawable cHP_2 = nrf.cHP_(str2 + str + ".png");
        return cHP_2 != null ? cHP_2 : BaseApplication.e().getDrawable(i);
    }

    public static Drawable bof_(String str) {
        LogUtil.a("SportRecordImageHelper", "getSportRecordDrawable imageName = ", str);
        String str2 = drd.d("com.huawei.health_Sport_Common", "sport_record_image", (String) null) + File.separator;
        BitmapDrawable cHP_ = nrf.cHP_(str2 + str + ".webp");
        if (cHP_ != null) {
            return cHP_;
        }
        return nrf.cHP_(str2 + str + ".png");
    }
}
