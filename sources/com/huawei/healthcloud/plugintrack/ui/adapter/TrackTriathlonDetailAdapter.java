package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.gso;
import defpackage.gya;
import defpackage.gyb;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TrackTriathlonDetailAdapter extends BaseRecyclerAdapter<gya> implements BaseRecyclerAdapter.OnItemClickListener<gya> {
    private static final int[] e = {R.layout.layout_motion_track_sub_child_item, R.layout.track_triathlon_switch_item};
    private boolean b;
    private gyb c;

    public boolean d() {
        return false;
    }

    public TrackTriathlonDetailAdapter(List<gya> list, boolean z) {
        super(list, e);
        this.b = z;
        setOnItemClickListener(this);
        this.c = new gyb();
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i % e.length;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, gya gyaVar) {
        if (getItemViewType(i) == 0) {
            b(recyclerHolder, i, gyaVar);
        } else if (getItemViewType(i) == 1) {
            a(recyclerHolder, gyaVar);
        } else {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "convert error item, pls check the item type");
        }
    }

    private void a(RecyclerHolder recyclerHolder, gya gyaVar) {
        if (recyclerHolder == null) {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "holder is null, pls check");
            return;
        }
        if (gyaVar == null || gyaVar.d() == null) {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "sport change data is null, pls check");
            recyclerHolder.a(R.id.hw_layout_motion_track_triathlon_change, 8);
            return;
        }
        recyclerHolder.b(R.id.hw_motion_track_change_item_name, String.format(BaseApplication.getContext().getString(R.string._2130839851_res_0x7f02092b), UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(gyaVar.d().getChangeIntervalTime()))));
        boolean a2 = nrt.a(BaseApplication.getContext());
        int i = R.drawable.ic_health_list_change;
        if (a2) {
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                Context context = BaseApplication.getContext();
                if (this.b) {
                    i = R.drawable.ic_health_list_colours_change;
                }
                BitmapDrawable cKn_ = nrz.cKn_(context, i);
                if (cKn_ instanceof Drawable) {
                    recyclerHolder.cwN_(R.id.hw_motion_track_change_item_left_img, nrf.cJH_(cKn_, BaseApplication.getContext().getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
                    return;
                }
                return;
            }
            Resources resources = BaseApplication.getContext().getResources();
            if (this.b) {
                i = R.drawable.ic_health_list_colours_change;
            }
            recyclerHolder.cwN_(R.id.hw_motion_track_change_item_left_img, nrf.cJH_(resources.getDrawable(i), BaseApplication.getContext().getResources().getColor(R.color._2131297782_res_0x7f0905f6)));
            return;
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            Context context2 = BaseApplication.getContext();
            if (this.b) {
                i = R.drawable.ic_health_list_colours_change;
            }
            recyclerHolder.cwN_(R.id.hw_motion_track_change_item_left_img, nrz.cKn_(context2, i));
            return;
        }
        if (this.b) {
            i = R.drawable.ic_health_list_colours_change;
        }
        recyclerHolder.d(R.id.hw_motion_track_change_item_left_img, i);
    }

    private void b(RecyclerHolder recyclerHolder, int i, gya gyaVar) {
        AbstractTrackSummaryData abstractTrackSummaryData;
        if (b(recyclerHolder, gyaVar)) {
            return;
        }
        if (gyaVar.b() != null) {
            abstractTrackSummaryData = this.c.createSingleTrackSummaryData(gyaVar.b());
        } else if (gyaVar.d() != null) {
            abstractTrackSummaryData = this.c.createSingleTrackSummaryData(gyaVar.d());
            recyclerHolder.a(R.id.hw_motion_track_child_right_button, 8);
        } else {
            abstractTrackSummaryData = null;
        }
        if (abstractTrackSummaryData == null) {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "trackSummaryData is null, pls check");
            recyclerHolder.a(R.id.hw_layout_motion_track_child, 8);
            return;
        }
        recyclerHolder.b(R.id.hw_motion_track_child_item_chief_sport_data_text, abstractTrackSummaryData.getChiefDataString()).b(R.id.hw_motion_track_child_item_chief_sport_data_unit, abstractTrackSummaryData.getChiefDataUnitString()).b(R.id.hw_motion_track_child_item_center_text, abstractTrackSummaryData.getItemDurationString()).b(R.id.hw_motion_track_child_item_right_text, abstractTrackSummaryData.getAlterDataString()).b(R.id.hw_motion_track_item_accessory_sport_data_unit, abstractTrackSummaryData.getAlterUnitString());
        if (this.b) {
            recyclerHolder.a(R.id.hw_motion_track_child_right_button, 8);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            recyclerHolder.cwK_(R.id.hw_motion_track_child_right_button).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            recyclerHolder.cwN_(R.id.hw_motion_track_child_item_left_img, nrz.cKn_(BaseApplication.getContext(), abstractTrackSummaryData.getSportTypeDrawableSource(this.b))).cwN_(R.id.hw_motion_track_child_item_time_img, nrz.cKn_(BaseApplication.getContext(), abstractTrackSummaryData.getSportDurationDrawableSource()));
        } else {
            recyclerHolder.d(R.id.hw_motion_track_child_item_left_img, abstractTrackSummaryData.getSportTypeDrawableSource(this.b)).d(R.id.hw_motion_track_child_item_time_img, abstractTrackSummaryData.getSportDurationDrawableSource());
        }
        if (i == getItemCount() - 1) {
            recyclerHolder.a(R.id.hw_motion_track_bottom_image_interval, 8);
        }
    }

    private boolean b(RecyclerHolder recyclerHolder, gya gyaVar) {
        if (recyclerHolder == null) {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "holder is null, pls check");
            return true;
        }
        if (gyaVar != null) {
            return false;
        }
        recyclerHolder.a(R.id.hw_layout_motion_track_child, 8);
        LogUtil.h("Track_TrackTriathlonDetailAdapter", "itemData is null, pls check");
        return true;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int itemCount = super.getItemCount();
        if (itemCount <= 0) {
            return 0;
        }
        if (itemCount >= 3) {
            return (e.length * 3) - 1;
        }
        gya gyaVar = (gya) super.get(itemCount - 1);
        if (gyaVar == null || gyaVar.d() == null) {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "data error, set item count 0");
            return 0;
        }
        if (gyaVar.d().getChangeIntervalTime() > 0) {
            return e.length * itemCount;
        }
        return (e.length * itemCount) - 1;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public gya get(int i) {
        return (gya) super.get(i / e.length);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onItemClicked(RecyclerHolder recyclerHolder, int i, gya gyaVar) {
        if (getItemViewType(i) != 0 || d()) {
            LogUtil.h("Track_TrackTriathlonDetailAdapter", "click wrong");
            return;
        }
        if (gyaVar == null || gyaVar.b() == null) {
            return;
        }
        LogUtil.h("Track_TrackTriathlonDetailAdapter", "click to sub sport track detail");
        gso.e().c(gyaVar.a(), gyaVar.b(), Boolean.FALSE.booleanValue(), Boolean.TRUE.booleanValue());
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(gyaVar.b().requestSportType()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_ENTER_SUB_DETAIL_1040048.value(), hashMap, 0);
    }
}
