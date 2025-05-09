package defpackage;

import android.widget.RelativeLayout;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class hok {
    private HealthTextView b;
    private HealthTextView c = null;
    private HealthTextView d;
    private RelativeLayout e;

    public hok(HealthTextView healthTextView, HealthTextView healthTextView2, RelativeLayout relativeLayout) {
        this.d = healthTextView;
        this.b = healthTextView2;
        this.e = relativeLayout;
    }

    public RelativeLayout bnC_() {
        return this.e;
    }

    public HealthTextView d() {
        return this.b;
    }

    public HealthTextView a() {
        return this.d;
    }

    public void d(hoj hojVar) {
        if (hojVar == null) {
            LogUtil.b("Track_TrackMainViewHolderItem", "trackShowItemValue mTrackMainPageData is null,");
            return;
        }
        HealthTextView healthTextView = this.b;
        if (healthTextView != null) {
            healthTextView.setText(hojVar.e());
        }
        HealthTextView healthTextView2 = this.d;
        if (healthTextView2 != null) {
            healthTextView2.setText(hojVar.d());
        }
        HealthTextView healthTextView3 = this.c;
        if (healthTextView3 != null) {
            healthTextView3.setText(hojVar.b());
        }
    }
}
