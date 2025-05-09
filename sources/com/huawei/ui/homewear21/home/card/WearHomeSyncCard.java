package com.huawei.ui.homewear21.home.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class WearHomeSyncCard extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9668a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;

    public WearHomeSyncCard(Context context) {
        this(context, null);
    }

    public WearHomeSyncCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WearHomeSyncCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LogUtil.a("WearHomeSyncCard", "WearHomeSyncCard enter");
        dlS_(LayoutInflater.from(context).inflate(R.layout.wear_home_sync_card, this));
    }

    private void dlS_(View view) {
        this.b = (LinearLayout) nsy.cMd_(view, R.id.lin_sync_error);
        this.d = (LinearLayout) nsy.cMd_(view, R.id.lin_syncing);
        this.c = (LinearLayout) nsy.cMd_(view, R.id.lin_sync_success);
        this.f9668a = (HealthTextView) nsy.cMd_(view, R.id.toast_cancel_tv);
    }

    public void e(int i) {
        if (i == 0) {
            e();
            return;
        }
        if (i == 1) {
            c();
        } else if (i == 2) {
            a();
        } else {
            LogUtil.h("WearHomeSyncCard", "accountStateChange state default");
        }
    }

    private void e() {
        LogUtil.a("WearHomeSyncCard", "syncSuccess in");
        this.b.setVisibility(8);
        this.d.setVisibility(8);
        this.c.setVisibility(0);
    }

    private void a() {
        LogUtil.a("WearHomeSyncCard", "syncLoading in");
        this.b.setVisibility(8);
        this.d.setVisibility(0);
        this.c.setVisibility(8);
    }

    private void c() {
        LogUtil.a("WearHomeSyncCard", "syncFail in");
        this.b.setVisibility(0);
        this.d.setVisibility(8);
        this.c.setVisibility(8);
    }

    public void dlT_(View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            LogUtil.h("WearHomeSyncCard", "cardCancel listener is null");
        } else {
            this.f9668a.setOnClickListener(onClickListener);
        }
    }
}
