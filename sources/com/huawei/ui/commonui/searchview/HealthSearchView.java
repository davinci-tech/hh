package com.huawei.ui.commonui.searchview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$string;
import com.huawei.uikit.phone.hwsearchview.widget.HwSearchView;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class HealthSearchView extends HwSearchView {
    public HealthSearchView(Context context) {
        super(context);
        nsn.d(this);
        a();
        b();
    }

    public HealthSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        nsn.d(this);
        a();
        b();
    }

    public HealthSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        nsn.d(this);
        a();
        b();
    }

    private void a() {
        View findViewById = findViewById(R.id.search_close_btn);
        if (!(findViewById instanceof ImageView)) {
            ReleaseLogUtil.a("HealthSearchView", "setClearContentDescription view ", findViewById);
        } else {
            jcf.bEz_(findViewById, nsf.j(R$string.accessibility_clear));
        }
    }

    private void b() {
        jcf.bEE_(findViewById(R.id.search_plate), 2);
        jcf.bEE_(findViewById(R.id.search_src_text), 2);
        jcf.bEE_(findViewById(R.id.search_edit_frame), 2);
        jcf.bEE_(findViewById(R.id.hwsearchview_search_src_icon), 2);
    }

    public void setSearchBarContentDescription() {
        String b;
        CharSequence queryHint = getQueryHint();
        if (TextUtils.isEmpty(queryHint)) {
            b = nsf.h(R$string.accessibility_search);
        } else {
            b = nsf.b(R$string.accessibility_search_hint, queryHint);
        }
        jcf.bEA_(findViewById(R.id.hwsearchview_search_bar), b, EditText.class);
    }
}
