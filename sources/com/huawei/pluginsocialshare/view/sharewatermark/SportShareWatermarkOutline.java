package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fed;
import defpackage.mwd;
import defpackage.nrf;
import health.compact.a.CommonUtil;
import java.io.File;

/* loaded from: classes6.dex */
public class SportShareWatermarkOutline extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8546a;
    private HealthTextView b;
    private int c;
    private HealthTextView d;
    private boolean e = true;
    private boolean f = false;
    private View g;
    private int h;
    private HealthTextView i;
    private String j;
    private HealthTextView k;
    private ImageView n;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public SportShareWatermarkOutline(Context context) {
        b(context);
        d(context);
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.sport_share_watermark_outline, null);
        this.g = inflate;
        this.n = (ImageView) inflate.findViewById(R.id.share_watermark_outline);
        this.k = (HealthTextView) this.g.findViewById(R.id.share_watermark_main_data_value);
        this.i = (HealthTextView) this.g.findViewById(R.id.share_watermark_main_data_unit);
        this.f8546a = (HealthTextView) this.g.findViewById(R.id.share_watermark_bottom_first_data_value);
        this.b = (HealthTextView) this.g.findViewById(R.id.share_watermark_bottom_second_data_value);
        this.d = (HealthTextView) this.g.findViewById(R.id.share_watermark_bottom_third_data_value);
    }

    private void d(Context context) {
        this.k.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf"));
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.k.setTextColor(i);
        this.i.setTextColor(i);
        this.f8546a.setTextColor(i);
        this.b.setTextColor(i);
        this.d.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.g;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.c;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.c = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.j;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.j = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.e = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.e;
    }

    private void e(String str) {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("Share_SportShareWatermarkOutline", "refreshOutLine path is empty");
            return;
        }
        File file = new File(c);
        if (file.exists()) {
            nrf.cIs_(file, this.n);
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            String j = fedVar.j();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m) || mwd.b(j)) {
                this.f = true;
                return;
            }
            mwd.b(this.k, a2);
            mwd.b(this.i, fedVar.c());
            mwd.b(this.f8546a, i);
            mwd.b(this.b, m);
            mwd.b(this.d, j);
            e(fedVar.n());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.h = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.h;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.f;
    }
}
