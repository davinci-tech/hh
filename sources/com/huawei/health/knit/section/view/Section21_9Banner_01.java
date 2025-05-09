package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Banner_01_Adapter;
import com.huawei.health.knit.section.utils.SpacesItemDecoration;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class Section21_9Banner_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f2645a;
    private LinearLayout b;
    private Context c;
    private Map<String, List<Object>> d;
    private RelativeLayout e;
    private HealthRecycleView f;
    private HealthSubHeader g;
    private Pair<Integer, Integer> h;
    private View i;
    private boolean j;
    private Section21_9Banner_01_Adapter n;

    public Section21_9Banner_01(Context context) {
        super(context);
        this.j = false;
        this.h = BaseActivity.getSafeRegionWidth();
    }

    public Section21_9Banner_01(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = false;
        this.h = BaseActivity.getSafeRegionWidth();
    }

    public Section21_9Banner_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = false;
        this.h = BaseActivity.getSafeRegionWidth();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        a(hashMap);
        c();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(HashMap<String, Object> hashMap) {
        char c;
        this.d = new HashMap();
        setHealthSubHeaderTitle(nru.b(hashMap, "TITLE", ""));
        setHealthSubHeaderSubTitle(nru.b(hashMap, "SUBTITLE", ""));
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            key.hashCode();
            switch (key.hashCode()) {
                case -1981034679:
                    if (key.equals("NUMBER")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1839152142:
                    if (key.equals(CommonConstant.RETKEY.STATUS)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -749122684:
                    if (key.equals("ACTIVITY_TYPE_CONTENT")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 69775675:
                    if (key.equals("IMAGE")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1468011911:
                    if (key.equals("ACTIVITY_TYPE_DESCRIPTION")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1675868304:
                    if (key.equals("CLICK_EVENT_LISTENER")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c != 3) {
                            if (c != 4) {
                                if (c == 5) {
                                    a(hashMap, key);
                                }
                            } else if (entry.getValue() instanceof List) {
                                this.d.put("ACTIVITY_TYPE_DESCRIPTION", (List) entry.getValue());
                            }
                        } else if (entry.getValue() instanceof List) {
                            this.d.put("IMAGE", (List) entry.getValue());
                        }
                    } else if (entry.getValue() instanceof List) {
                        this.d.put("ACTIVITY_TYPE_CONTENT", (List) entry.getValue());
                    }
                } else if (entry.getValue() instanceof List) {
                    this.d.put(CommonConstant.RETKEY.STATUS, (List) entry.getValue());
                }
            } else if (entry.getValue() instanceof List) {
                this.d.put("NUMBER", (List) entry.getValue());
            }
        }
    }

    private void c() {
        Section21_9Banner_01_Adapter section21_9Banner_01_Adapter;
        if (this.n == null) {
            this.n = new Section21_9Banner_01_Adapter(this.c, this.f2645a);
        }
        Map<String, List<Object>> map = this.d;
        if (map != null && (section21_9Banner_01_Adapter = this.n) != null) {
            section21_9Banner_01_Adapter.e(map);
            this.f.setAdapter(this.n);
        } else {
            LogUtil.h("Section_Section21_9Banner_01", "subDataList is null or activityPageSectionAdapter is null");
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.configured_activity_page, (ViewGroup) this, false);
        this.i = inflate;
        this.e = (RelativeLayout) inflate.findViewById(R.id.activity_recycler_view_layout);
        this.b = (LinearLayout) this.i.findViewById(R.id.configured_activity_layout);
        this.g = (HealthSubHeader) this.i.findViewById(R.id.health_activity_header);
        this.f = (HealthRecycleView) this.i.findViewById(R.id.activity_recycle_view);
        if (nrt.a(this.c)) {
            this.b.setBackgroundColor(this.c.getResources().getColor(R$color.emui_color_bg));
        }
        this.g.setBackgroundColor(0);
        this.g.getBackground().setAlpha(0);
        this.g.setSubHeaderBackgroundColor(ContextCompat.getColor(this.i.getContext(), R$color.common_transparent));
        this.f.setNestedScrollingEnabled(false);
        this.f.setHasFixedSize(true);
        this.f.a(false);
        this.f.d(false);
        this.f.setLayoutManager(new LinearLayoutManager(this.c));
        e();
        return this.i;
    }

    private void a(final HashMap<String, Object> hashMap, final String str) {
        if (hashMap.get(str) instanceof OnClickSectionListener) {
            this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section21_9Banner_01.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ((OnClickSectionListener) hashMap.get(str)).onClick("TITLE_SHOW_MORE_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.f2645a = (OnClickSectionListener) hashMap.get(str);
        }
    }

    public void setSubTitleVisible(boolean z) {
        this.j = z;
    }

    private void setHealthSubHeaderTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.g.setHeadTitleText(str);
    }

    private void setHealthSubHeaderSubTitle(String str) {
        if (TextUtils.isEmpty(str) || !this.j) {
            this.g.setMoreTextVisibility(4);
        } else {
            this.g.setMoreTextVisibility(0);
            this.g.setMoreText(str);
        }
    }

    private void e() {
        int i = nsn.ag(this.c) ? 2 : 1;
        if (this.f.getItemDecorationCount() > 0 && this.f.getItemDecorationAt(0) != null) {
            this.f.removeItemDecorationAt(0);
        }
        this.f.addItemDecoration(new SpacesItemDecoration(0, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), i, new int[]{0, 0, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362624_res_0x7f0a0340) + ((Integer) this.h.second).intValue(), 0}));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.c, i);
        gridLayoutManager.setOrientation(1);
        this.f.setLayoutManager(gridLayoutManager);
        this.f.setHasFixedSize(true);
        this.f.setNestedScrollingEnabled(false);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section21_9Banner_01";
    }
}
