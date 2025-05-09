package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.ui.AchieveMedalDetailActivity;
import com.huawei.pluginachievement.ui.views.SameTypeMedalView;
import defpackage.gnm;
import defpackage.hcn;
import defpackage.jdx;
import defpackage.mcz;
import defpackage.mdn;
import defpackage.meh;
import defpackage.mlb;
import defpackage.mlu;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SameTypeMedalView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private final ImageView f8471a;
    private final LinearLayout b;
    private final TextView c;
    private final View d;

    public SameTypeMedalView(Context context) {
        this(context, null);
    }

    public SameTypeMedalView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SameTypeMedalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(context, R.layout.view_same_type_medal, null);
        this.d = inflate.findViewById(R.id.rl_single_root);
        this.f8471a = (ImageView) inflate.findViewById(R.id.iv_medal_image);
        this.c = (TextView) inflate.findViewById(R.id.htv_medal_name);
        this.b = (LinearLayout) inflate.findViewById(R.id.ll_multi_root);
        addView(inflate);
        setVisibility(8);
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void b(final String str, final String str2, final boolean z) {
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: mlv
                @Override // java.lang.Runnable
                public final void run() {
                    SameTypeMedalView.this.b(str, str2, z);
                }
            });
            return;
        }
        final List<MedalInfoDesc> d = d(str, str2, z);
        LogUtil.a("PLGACHIEVE_SameTypeMedalView", "showSameTypeMedals: medalInfoList -> " + d.size());
        HandlerExecutor.e(new Runnable() { // from class: mlx
            @Override // java.lang.Runnable
            public final void run() {
                SameTypeMedalView.this.a(d);
            }
        });
    }

    private List<MedalInfoDesc> d(String str, String str2, boolean z) {
        ArrayList arrayList = new ArrayList(2);
        meh c = meh.c(getContext());
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.MEDAL_TYPE, str2);
        List<mcz> b = c.b(9, hashMap);
        if (CollectionUtils.d(b)) {
            LogUtil.h("PLGACHIEVE_SameTypeMedalView", "initMedalData: medalConfigInfoList is null");
            return arrayList;
        }
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                if (TextUtils.equals(str, medalConfigInfo.acquireMedalID())) {
                    LogUtil.a("PLGACHIEVE_SameTypeMedalView", "initMedalData: do not show! medalId -> " + str);
                } else {
                    HashMap hashMap2 = new HashMap(2);
                    hashMap2.put("medalID", medalConfigInfo.acquireMedalID());
                    mcz d = c.d(8, hashMap2);
                    if (d instanceof MedalLocation) {
                        MedalLocation medalLocation = (MedalLocation) d;
                        if (!z && medalLocation.acquireGainedCount() <= 0) {
                            LogUtil.a("PLGACHIEVE_SameTypeMedalView", "initMedalData: gray medals don't show. medalId -> " + medalLocation.acquireMedalID());
                        } else {
                            a(arrayList, medalConfigInfo, medalLocation);
                        }
                    }
                }
            }
        }
        arrayList.sort(new Comparator() { // from class: mlw
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(nsn.h(((MedalInfoDesc) obj2).acquireGainTime()), nsn.h(((MedalInfoDesc) obj).acquireGainTime()));
                return compare;
            }
        });
        return arrayList;
    }

    private void a(List<MedalInfoDesc> list, MedalConfigInfo medalConfigInfo, MedalLocation medalLocation) {
        MedalInfoDesc medalInfoDesc = new MedalInfoDesc(medalConfigInfo.acquireMedalID());
        medalInfoDesc.saveLightDescription(medalConfigInfo.acquireLightDescription());
        medalInfoDesc.saveText(medalConfigInfo.acquireMedalName());
        medalInfoDesc.saveMedalTypeLevel(mdn.d(medalConfigInfo.acquireMedalType(), String.valueOf(medalConfigInfo.acquireMedalLevel())));
        medalInfoDesc.saveMedalType(medalConfigInfo.acquireMedalType());
        medalInfoDesc.savePromotionName(medalConfigInfo.acquireLightPromotionName());
        medalInfoDesc.savePromotionURL(medalConfigInfo.acquireLightPromotionUrl());
        medalInfoDesc.saveGainCount(medalLocation.acquireGainedCount());
        medalInfoDesc.saveGainTime(medalLocation.acquireMedalGainedTime());
        medalInfoDesc.saveMessage(medalConfigInfo.acquireMessage());
        list.add(medalInfoDesc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(List<MedalInfoDesc> list) {
        if (CollectionUtils.d(list)) {
            LogUtil.h("PLGACHIEVE_SameTypeMedalView", "showMedalView: medalInfoList is empty");
            return;
        }
        if (this.d == null || this.b == null) {
            LogUtil.h("PLGACHIEVE_SameTypeMedalView", "showMedalView: mSingleRootView/mMultiRootView is null");
            return;
        }
        if (list.size() == 1) {
            this.b.setVisibility(8);
            this.d.setVisibility(0);
            clv_(list.get(0), this.d, this.f8471a, this.c);
            setVisibility(0);
            return;
        }
        this.d.setVisibility(8);
        this.b.setVisibility(0);
        this.b.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, hcn.a(getContext(), 135.0f));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
        layoutParams2.weight = 1.0f;
        LinearLayout linearLayout = null;
        for (int i = 0; i < list.size(); i++) {
            if (i % 3 == 0) {
                linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(0);
                linearLayout.setWeightSum(3.0f);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.removeAllViews();
                this.b.addView(linearLayout);
            }
            LinearLayout linearLayout2 = (LinearLayout) View.inflate(getContext(), R.layout.view_item_same_type_medal, null);
            linearLayout2.setOrientation(1);
            linearLayout2.setLayoutParams(layoutParams2);
            clv_(list.get(i), linearLayout2, (ImageView) linearLayout2.findViewById(R.id.iv_medal_image), (TextView) linearLayout2.findViewById(R.id.htv_medal_name));
            linearLayout.addView(linearLayout2);
        }
        setVisibility(0);
    }

    private void clv_(final MedalInfoDesc medalInfoDesc, View view, ImageView imageView, TextView textView) {
        Bitmap cko_ = mlb.cko_(medalInfoDesc.acquireMedalId(), medalInfoDesc.acquireGainCount() > 0, false);
        if (cko_ != null) {
            imageView.setImageBitmap(cko_);
        } else {
            LogUtil.h("PLGACHIEVE_SameTypeMedalView", "setMedalViewData: medalBitmap is null");
        }
        textView.setText(medalInfoDesc.acquireText());
        view.setOnClickListener(new View.OnClickListener() { // from class: mly
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SameTypeMedalView.this.clw_(medalInfoDesc, view2);
            }
        });
    }

    public /* synthetic */ void clw_(MedalInfoDesc medalInfoDesc, View view) {
        d(medalInfoDesc);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(MedalInfoDesc medalInfoDesc) {
        Context context = getContext();
        if (context == null) {
            LogUtil.h("PLGACHIEVE_SameTypeMedalView", "jumpToMedalDetailActivity: context is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) AchieveMedalDetailActivity.class);
        intent.putExtra("medal_res_id", medalInfoDesc.acquireMedalId());
        intent.putExtra("medal_des_id", medalInfoDesc.acquireLightDescription());
        intent.putExtra("medal_content_id", medalInfoDesc.acquireText());
        intent.putExtra("medal_type_level", medalInfoDesc.acquireMedalTypeLevel());
        intent.putExtra("medal_type", medalInfoDesc.acquireMedalType());
        intent.putExtra("promotion_name", medalInfoDesc.acquirePromotionName());
        intent.putExtra("promotion_url", medalInfoDesc.acquirePromotionURL());
        intent.putExtra("medal_gain_time", medalInfoDesc.acquireGainTime());
        intent.putExtra("medal_gain_count", medalInfoDesc.acquireGainCount());
        intent.putExtra("KEY_IS_SAME_TYPE_MEDAL_VIEW", true);
        intent.putExtra("medal_obtain_id", String.valueOf(medalInfoDesc.acquireGainCount() > 0));
        intent.putExtra("click_x", mlu.f(context) / 2);
        intent.putExtra("click_y", mlu.j(context) / 2);
        intent.addFlags(268435456);
        gnm.aPB_(context, intent);
    }
}
