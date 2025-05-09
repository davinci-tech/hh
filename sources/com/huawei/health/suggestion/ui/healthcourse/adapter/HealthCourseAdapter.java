package com.huawei.health.suggestion.ui.healthcourse.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.suggestion.ui.healthcourse.adapter.HealthCourseAdapter;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.HealthWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.ffy;
import defpackage.gbr;
import defpackage.ggr;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nrq;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class HealthCourseAdapter extends PolymericDataAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Object> f3235a;

    public HealthCourseAdapter(List<Workout> list, RecyclerView recyclerView, int i, Map<String, Object> map) {
        super(list, recyclerView, i);
        this.f3235a = map;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setIsShowSquare(boolean z) {
        super.setIsShowSquare(z);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setRecycleHolder(RecyclerHolder recyclerHolder, final Workout workout, final int i) {
        HealthTextView healthTextView;
        if (!(workout instanceof HealthWorkout)) {
            LogUtil.h("Sug_HealthCourseAdapter", "convert, workout is null.");
            return;
        }
        LogUtil.a("Sug_HealthCourseAdapter", "convert, holder.itemView.getWidth() = ", Integer.valueOf(recyclerHolder.itemView.getWidth()));
        HealthWorkout healthWorkout = (HealthWorkout) workout;
        a(recyclerHolder, healthWorkout);
        if (this.mIsShowSquare) {
            healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_tag_square);
            d(recyclerHolder, workout, i, healthWorkout);
        } else {
            recyclerHolder.cwK_(R.id.recycle_item).setOnClickListener(new View.OnClickListener() { // from class: fsz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HealthCourseAdapter.this.aGj_(workout, i, view);
                }
            });
            recyclerHolder.cwK_(R.id.item_course).setVisibility(0);
            recyclerHolder.cwK_(R.id.item_square_course).setVisibility(8);
            healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_tag);
            b(recyclerHolder);
        }
        c(healthWorkout, healthTextView);
    }

    public /* synthetic */ void aGj_(Workout workout, int i, View view) {
        onItemClick(workout, i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(RecyclerHolder recyclerHolder, final Workout workout, final int i, HealthWorkout healthWorkout) {
        recyclerHolder.cwK_(R.id.item_course).setVisibility(8);
        recyclerHolder.cwK_(R.id.item_square_course).setVisibility(0);
        recyclerHolder.cwK_(R.id.recycle_item_square).setOnClickListener(new View.OnClickListener() { // from class: fsy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthCourseAdapter.this.aGk_(workout, i, view);
            }
        });
        setCardBackground((ImageView) recyclerHolder.cwK_(R.id.sug_img_item_pic_square), workout, i);
        b(recyclerHolder, healthWorkout);
        HealthImageView healthImageView = (HealthImageView) recyclerHolder.cwK_(R.id.item_device_icon);
        if (healthImageView == null) {
            return;
        }
        if ("1001".equals(workout.acquireId()) || BleConstants.CODE_AUTHORIZED_FAIL.equals(workout.acquireId())) {
            healthImageView.setVisibility(0);
        } else {
            healthImageView.setVisibility(8);
        }
    }

    public /* synthetic */ void aGk_(Workout workout, int i, View view) {
        onItemClick(workout, i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(RecyclerHolder recyclerHolder) {
        if (nsn.t()) {
            e(recyclerHolder, R.id.tv_subtitle, R.dimen._2131362886_res_0x7f0a0446);
            recyclerHolder.cwK_(R.id.play_num_layout).setVisibility(8);
        }
        if (nsn.m()) {
            e(recyclerHolder, R.id.tv_fe_name, R.dimen._2131362973_res_0x7f0a049d);
            e(recyclerHolder, R.id.member_price_title, R.dimen._2131362914_res_0x7f0a0462);
            e(recyclerHolder, R.id.member_price_unit, R.dimen._2131362914_res_0x7f0a0462);
            e(recyclerHolder, R.id.member_price, R.dimen._2131362937_res_0x7f0a0479);
            e(recyclerHolder, R.id.normal_price, R.dimen._2131362914_res_0x7f0a0462);
        }
    }

    private void e(RecyclerHolder recyclerHolder, int i, int i2) {
        View cwK_ = recyclerHolder.cwK_(i);
        Resources resources = BaseApplication.getContext().getResources();
        float dimension = resources == null ? 0.0f : resources.getDimension(i2);
        if (!(cwK_ instanceof HealthTextView) || dimension == 0.0f) {
            return;
        }
        ((HealthTextView) cwK_).setTextSize(0, dimension);
    }

    private void d(RecyclerHolder recyclerHolder, HealthWorkout healthWorkout) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.member_price_title);
        healthTextView.setText(ffy.d(healthTextView.getContext(), R.string._2130845864_res_0x7f0220a8, ""));
        HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.member_price_unit);
        healthTextView2.setText(this.mContext.getString(R.string._2130844959_res_0x7f021d1f, "¥", ""));
        HealthTextView healthTextView3 = (HealthTextView) recyclerHolder.cwK_(R.id.member_price);
        healthTextView3.setText(healthWorkout.getMemberPrice());
        HealthTextView healthTextView4 = (HealthTextView) recyclerHolder.cwK_(R.id.normal_price);
        healthTextView4.setText(this.mContext.getString(R.string._2130844959_res_0x7f021d1f, "¥", healthWorkout.getNormalPrice()));
        TextPaint paint = healthTextView4.getPaint();
        if (paint != null) {
            paint.setFlags(16);
        }
        if (TextUtils.isEmpty(healthWorkout.getNormalPrice())) {
            healthTextView4.setVisibility(8);
        } else {
            healthTextView4.setVisibility(0);
        }
        if (TextUtils.isEmpty(healthWorkout.getMemberPrice())) {
            healthTextView.setVisibility(8);
            healthTextView2.setVisibility(8);
            healthTextView3.setVisibility(8);
            healthTextView4.setPaintFlags(healthTextView4.getPaintFlags() & (-17));
            return;
        }
        healthTextView.setVisibility(0);
        healthTextView2.setVisibility(0);
        healthTextView3.setVisibility(0);
    }

    private void c(RecyclerHolder recyclerHolder, int i) {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(new gbr.c(recyclerHolder.cwK_(R.id.tv_fe_name), 4));
        arrayList.add(new gbr.c(recyclerHolder.cwK_(R.id.tv_subtitle)));
        arrayList.add(new gbr.c(recyclerHolder.cwK_(R.id.tv_duration)));
        arrayList.add(new gbr.c(recyclerHolder.cwK_(R.id.price_layout)));
        arrayList.add(new gbr.c(recyclerHolder.cwK_(R.id.tv_plan_peoples_num)));
        gbr.e(arrayList, i);
        if (nsn.t()) {
            recyclerHolder.cwK_(R.id.play_num_layout).setVisibility(8);
        }
        if (gbr.c(i)) {
            recyclerHolder.cwK_(R.id.sug_course_mask_bg).setVisibility(8);
        } else {
            e(recyclerHolder, i);
        }
    }

    private void e(RecyclerHolder recyclerHolder, int i) {
        LinearLayout linearLayout = (LinearLayout) recyclerHolder.cwK_(R.id.play_num_layout);
        linearLayout.getLayoutParams();
        if (!(linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            LogUtil.h("Sug_HealthCourseAdapter", "controlDisplayView playNumLayout null instanceof RelativeLayout");
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        if (gbr.d(i)) {
            layoutParams.removeRule(20);
            layoutParams.addRule(21);
        } else {
            layoutParams.removeRule(21);
            layoutParams.addRule(20);
        }
        linearLayout.setLayoutParams(layoutParams);
    }

    private void b(RecyclerHolder recyclerHolder, HealthWorkout healthWorkout) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name_square);
        if (!Utils.o()) {
            healthTextView.setMaxLines(1);
            healthTextView.setSingleLine(true);
            healthTextView.setEllipsize(TextUtils.TruncateAt.END);
        }
        healthTextView.setText(StringUtils.c((Object) healthWorkout.acquireName()));
    }

    private void c(HealthWorkout healthWorkout, HealthTextView healthTextView) {
        if (healthWorkout.getCommodityFlag() == 1) {
            healthTextView.setVisibility(0);
            healthTextView.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable._2131431678_res_0x7f0b10fe));
            healthTextView.setText(this.mContext.getString(R.string._2130847510_res_0x7f022716));
        } else if (healthWorkout.getLayType() == 1) {
            healthTextView.setVisibility(0);
            healthTextView.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable._2131431680_res_0x7f0b1100));
            healthTextView.setText(this.mContext.getString(R.string._2130848847_res_0x7f022c4f));
        } else {
            healthTextView.setVisibility(8);
            LogUtil.a("Sug_HealthCourseAdapter", "no tag.");
        }
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setCardBackground(ImageView imageView, Workout workout, int i) {
        if (!(workout instanceof HealthWorkout)) {
            LogUtil.h("Sug_HealthCourseAdapter", "convert, workout is null.");
            return;
        }
        HealthWorkout healthWorkout = (HealthWorkout) workout;
        String str = imageView.getTag() instanceof String ? (String) imageView.getTag() : null;
        String picture = healthWorkout.getPicture();
        if (TextUtils.isEmpty(picture)) {
            LogUtil.a("Sug_HealthCourseAdapter", "use square picture.");
            picture = healthWorkout.getSqurePicture();
        }
        if (TextUtils.isEmpty(picture)) {
            LogUtil.b("Sug_HealthCourseAdapter", "backgroundUrl is empty.", picture);
            return;
        }
        if (TextUtils.isEmpty(str) || !picture.equals(str)) {
            imageView.setTag(picture);
            if (this.mIsShowSquare) {
                nrf.cHI_(picture, imageView, nrf.c);
            } else {
                nrf.cHI_(picture, imageView, nrf.d);
            }
            ggr.c(e(i, healthWorkout), "1130069");
        }
    }

    private Map<String, Object> e(int i, HealthWorkout healthWorkout) {
        Map<String, Object> a2 = ggr.a(healthWorkout, i + 1);
        Map<String, Object> map = this.f3235a;
        if (map != null) {
            a2.putAll(map);
            a2.put("labelId", Integer.valueOf(this.mNavigationId));
        }
        return a2;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setNewPic(ImageView imageView, Workout workout) {
        if (!(workout instanceof HealthWorkout)) {
            LogUtil.h("Sug_HealthCourseAdapter", "convert, workout is null.");
        } else {
            imageView.setVisibility(8);
        }
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void onItemClick(Workout workout, int i) {
        String str;
        if (nsn.o() || !(workout instanceof HealthWorkout)) {
            LogUtil.h("Sug_HealthCourseAdapter", "mItemView click too fast.");
            return;
        }
        HealthWorkout healthWorkout = (HealthWorkout) workout;
        if (b(healthWorkout)) {
            int enableNewUrl = healthWorkout.getEnableNewUrl();
            LogUtil.a("Sug_HealthCourseAdapter", "healthWorkout isEnableNewUrl ", Integer.valueOf(enableNewUrl));
            if (enableNewUrl == 1) {
                JumpUtil.e(BaseApplication.getContext(), healthWorkout.acquireId(), 2);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("id", healthWorkout.acquireId());
                AppRouter.b("/Main/SeriesCourseListActivity").zF_(bundle).c(this.mContext);
            }
        } else {
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi == null) {
                LogUtil.a("Sug_HealthCourseAdapter", "marketRouterApi == null");
                return;
            }
            String acquireId = healthWorkout.acquireId();
            if (a(healthWorkout)) {
                str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&id=%s&type=sleepAudio&statusBarTextBlack&isImmerse".replace("%s", acquireId) + c(healthWorkout, i + 1);
            } else if (e(healthWorkout)) {
                str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.breath-training?h5pro=true&urlType=4&pName=com.huawei.health&statusBarTextBlack&isImmerse&path=trainPage&trainModeId=" + acquireId + c(healthWorkout, i + 1);
            } else {
                str = "";
            }
            LogUtil.a("Sug_HealthCourseAdapter", "type:", Integer.valueOf(healthWorkout.getType()), " url:", str);
            marketRouterApi.router(str);
        }
        ggr.a(e(i, healthWorkout));
    }

    private String c(HealthWorkout healthWorkout, int i) {
        return "&itemId=" + healthWorkout.acquireId() + "&labelId=" + this.mNavigationId + "&pullOrder=" + i + c();
    }

    private String c() {
        if (this.f3235a == null) {
            return "";
        }
        return "&pullfrom=" + this.f3235a.get(WebViewHelp.BI_KEY_PULL_FROM) + "&resourceId=" + this.f3235a.get("resourceId") + "&resourceName=" + this.f3235a.get("resourceName");
    }

    private void a(RecyclerHolder recyclerHolder, HealthWorkout healthWorkout) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        HealthTextView healthTextView3 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_subtitle);
        if (this.mIsShowSquare) {
            healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_duration_square);
        } else {
            HealthTextView healthTextView4 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_duration);
            healthTextView3.setText(healthWorkout.getSubtitle());
            c(recyclerHolder, healthWorkout);
            healthTextView = healthTextView4;
        }
        if (b(healthWorkout)) {
            int chapterCount = healthWorkout.getChapterCount();
            healthTextView.setText(nsf.a(R.plurals._2130903458_res_0x7f0301a2, chapterCount, UnitUtil.e(chapterCount, 1, 0)));
        } else if (healthWorkout.acquireDuration() == 0) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(ffy.d(healthTextView.getContext(), R.string._2130837534_res_0x7f02001e, moe.k(healthWorkout.acquireDuration())));
        }
        if (this.mIsShowSquare) {
            healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name_square);
            d(recyclerHolder, healthWorkout.getUsers());
        } else {
            healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name);
        }
        healthTextView2.setTextColor(getTextColor(R.color._2131299238_res_0x7f090ba6));
        healthTextView2.setTypeface(nsk.cKP_());
        if (b(healthWorkout) && !TextUtils.isEmpty(healthWorkout.getAggregateTitle())) {
            healthTextView2.setText(healthWorkout.getAggregateTitle());
        }
        ((TextView) recyclerHolder.cwK_(R.id.tv_plan_peoples_num)).setText(ffy.b(R.plurals._2130903492_res_0x7f0301c4, healthWorkout.getUsers(), UnitUtil.e(healthWorkout.getUsers(), 1, 0)));
    }

    private void c(RecyclerHolder recyclerHolder, HealthWorkout healthWorkout) {
        if (b(healthWorkout)) {
            c(recyclerHolder, healthWorkout.getDisPlaybit());
            d(recyclerHolder, healthWorkout);
        } else {
            recyclerHolder.cwK_(R.id.price_layout).setVisibility(8);
            e(recyclerHolder, healthWorkout.getDisPlaybit());
        }
    }

    private void d(RecyclerHolder recyclerHolder, int i) {
        ((TextView) recyclerHolder.cwK_(R.id.tv_plan_peoples_num_square)).setText(nrq.c(i));
    }

    private boolean b(HealthWorkout healthWorkout) {
        return healthWorkout.getType() == 1000625;
    }

    private boolean a(HealthWorkout healthWorkout) {
        return healthWorkout.getType() == 1000622;
    }

    private boolean e(HealthWorkout healthWorkout) {
        return healthWorkout.getType() == 1000623;
    }
}
