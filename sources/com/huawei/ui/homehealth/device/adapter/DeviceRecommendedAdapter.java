package com.huawei.ui.homehealth.device.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.device.adapter.DeviceRecommendedAdapter;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import defpackage.nkx;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DeviceRecommendedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9390a;
    private RecommendListener b;
    private int c;
    private List<RecommendedItem> d = new ArrayList(16);
    private LayoutInflater e;
    private int g;

    public interface RecommendListener {
        void onPersonalEquipment(RecommendedItem recommendedItem);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DeviceRecommendedAdapter(Context context) {
        this.f9390a = context;
        this.e = LayoutInflater.from(context);
    }

    public void b(List<RecommendedItem> list) {
        this.d = new ArrayList(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (getItemCount() == 1 || getItemCount() == 2 || Utils.o()) ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("DeviceRecommendedAdapter", "getItemCount() = ", Integer.valueOf(getItemCount()), " isOversea() = ", Boolean.valueOf(Utils.o()));
        if (i == 0) {
            return new RecommendOneViewHolder(this.e.inflate(R.layout.fragment_device_recommend_one_item, viewGroup, false));
        }
        return new RecommendVmallViewHolder(this.e.inflate(R.layout.fragment_device_recommend_vmall_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RecommendOneViewHolder) {
            a((RecommendOneViewHolder) viewHolder, i);
        } else {
            c((RecommendVmallViewHolder) viewHolder, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.huawei.ui.homehealth.device.adapter.DeviceRecommendedAdapter.4
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    return 1;
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    private void a(RecommendOneViewHolder recommendOneViewHolder, int i) {
        if (i < 0 || i >= this.d.size()) {
            return;
        }
        RecommendedItem recommendedItem = this.d.get(i);
        d(recommendedItem);
        d(recommendOneViewHolder, this.c, this.g, recommendedItem);
    }

    private void c(RecommendVmallViewHolder recommendVmallViewHolder, int i) {
        if (i < 0 || i >= this.d.size()) {
            return;
        }
        RecommendedItem recommendedItem = this.d.get(i);
        d(recommendedItem);
        a(recommendVmallViewHolder, this.c, this.g, recommendedItem);
    }

    private void d(RecommendedItem recommendedItem) {
        int id = recommendedItem.getId();
        if (!Utils.o()) {
            switch (id) {
                case 1:
                    this.c = R.mipmap._2131821268_res_0x7f1102d4;
                    this.g = R.string._2130843014_res_0x7f021586;
                    LogUtil.a("DeviceRecommendedAdapter", "ID_DEVICE_SETTING_WATCH_FACE mTitle = ", Integer.valueOf(R.string._2130843014_res_0x7f021586));
                    break;
                case 2:
                    this.c = R.mipmap._2131821264_res_0x7f1102d0;
                    this.g = R.string.IDS_device_music;
                    LogUtil.a("DeviceRecommendedAdapter", "ID_DEVICE_SETTING_MUSIC mTitle = ", Integer.valueOf(R.string.IDS_device_music));
                    break;
                case 3:
                    this.c = R.mipmap._2131821267_res_0x7f1102d3;
                    this.g = R.string.IDS_device_wallet;
                    break;
                case 4:
                    this.c = R.mipmap._2131821262_res_0x7f1102ce;
                    this.g = R.string.IDS_device_application;
                    break;
                case 5:
                    this.c = R.mipmap._2131821263_res_0x7f1102cf;
                    this.g = R.string._2130841564_res_0x7f020fdc;
                    break;
                case 6:
                    this.c = R.mipmap._2131821265_res_0x7f1102d1;
                    this.g = R.string._2130847219_res_0x7f0225f3;
                    break;
                default:
                    this.c = R.mipmap._2131821268_res_0x7f1102d4;
                    this.g = R.string.IDS_device_watch_face;
                    break;
            }
        }
        d(id);
    }

    private void d(int i) {
        switch (i) {
            case 1:
                this.c = R.mipmap._2131821268_res_0x7f1102d4;
                this.g = R.string._2130843014_res_0x7f021586;
                break;
            case 2:
                this.c = R.mipmap._2131821264_res_0x7f1102d0;
                this.g = R.string._2130842049_res_0x7f0211c1;
                break;
            case 3:
                this.c = R.mipmap._2131821266_res_0x7f1102d2;
                this.g = R.string.IDS_device_wallet;
                break;
            case 4:
                this.c = R.mipmap._2131821262_res_0x7f1102ce;
                this.g = R.string.IDS_device_fragment_application_market;
                break;
            case 5:
                this.c = R.mipmap._2131821263_res_0x7f1102cf;
                this.g = R.string._2130841564_res_0x7f020fdc;
                break;
            case 6:
                this.c = R.mipmap._2131821265_res_0x7f1102d1;
                this.g = R.string._2130847219_res_0x7f0225f3;
                break;
            default:
                this.c = R.mipmap._2131821268_res_0x7f1102d4;
                this.g = R.string._2130843014_res_0x7f021586;
                break;
        }
    }

    private int b() {
        return (int) (this.f9390a.getResources().getDisplayMetrics().widthPixels / 4.0f);
    }

    private void d(RecommendOneViewHolder recommendOneViewHolder, int i, int i2, RecommendedItem recommendedItem) {
        if (this.f9390a == null) {
            this.f9390a = BaseApplication.getContext();
        }
        recommendOneViewHolder.f9391a.setVisibility(0);
        if (nsn.r()) {
            LogUtil.a("DeviceRecommendedAdapter", "isLargeFontScaleMode");
            recommendOneViewHolder.d.setMaxLines(2);
            recommendOneViewHolder.d.setMaxEms(2);
            if (recommendedItem.getId() == 1 && LanguageUtil.m(BaseApplication.getContext())) {
                recommendOneViewHolder.d.setText(this.f9390a.getResources().getString(i2) + "\n    ");
            } else {
                recommendOneViewHolder.d.setText(this.f9390a.getResources().getString(i2));
            }
        } else {
            LogUtil.a("DeviceRecommendedAdapter", "no isLargeFontScaleMode");
            recommendOneViewHolder.d.setText(this.f9390a.getResources().getString(i2));
        }
        recommendOneViewHolder.b.setImageResource(i);
        if (recommendedItem.getId() == 4) {
            if (recommendedItem.isShowRedDot()) {
                recommendOneViewHolder.c.setVisibility(0);
            } else {
                recommendOneViewHolder.c.setVisibility(4);
            }
        } else {
            recommendOneViewHolder.c.setVisibility(4);
        }
        cYE_(recommendOneViewHolder.f9391a, recommendedItem);
    }

    private void a(RecommendVmallViewHolder recommendVmallViewHolder, int i, int i2, RecommendedItem recommendedItem) {
        if (this.f9390a == null) {
            this.f9390a = BaseApplication.getContext();
        }
        recommendVmallViewHolder.b.setVisibility(0);
        if (!nsn.ag(this.f9390a) && nsn.r() && !Utils.o() && this.d.size() > 3) {
            ViewGroup.LayoutParams layoutParams = recommendVmallViewHolder.b.getLayoutParams();
            layoutParams.width = b();
            layoutParams.height = -2;
            recommendVmallViewHolder.b.setLayoutParams(layoutParams);
        }
        if (nsn.r()) {
            LogUtil.a("DeviceRecommendedAdapter", "vmall isLargeFontScaleMode");
            recommendVmallViewHolder.c.setMaxLines(2);
            recommendVmallViewHolder.c.setMaxEms(2);
            if (recommendedItem.getId() == 1 && LanguageUtil.m(BaseApplication.getContext())) {
                recommendVmallViewHolder.c.setText(this.f9390a.getResources().getString(i2) + "\n    ");
            } else {
                recommendVmallViewHolder.c.setText(this.f9390a.getResources().getString(i2));
            }
        } else {
            LogUtil.a("DeviceRecommendedAdapter", "vmall no isLargeFontScaleMode");
            recommendVmallViewHolder.c.setText(this.f9390a.getResources().getString(i2));
        }
        recommendVmallViewHolder.e.setImageResource(i);
        if (recommendedItem.getId() == 4) {
            if (recommendedItem.isShowRedDot()) {
                recommendVmallViewHolder.d.setVisibility(0);
            } else {
                recommendVmallViewHolder.d.setVisibility(4);
            }
        } else {
            recommendVmallViewHolder.d.setVisibility(4);
        }
        cYE_(recommendVmallViewHolder.b, recommendedItem);
    }

    private void cYE_(LinearLayout linearLayout, final RecommendedItem recommendedItem) {
        if (linearLayout != null) {
            Context context = this.f9390a;
            BaseActivity baseActivity = context instanceof BaseActivity ? (BaseActivity) context : null;
            if (baseActivity != null) {
                linearLayout.setOnClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: ofl
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DeviceRecommendedAdapter.this.cYF_(recommendedItem, view);
                    }
                }, baseActivity, true, ""));
            }
        }
    }

    public /* synthetic */ void cYF_(RecommendedItem recommendedItem, View view) {
        RecommendListener recommendListener = this.b;
        if (recommendListener != null) {
            recommendListener.onPersonalEquipment(recommendedItem);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(RecommendListener recommendListener) {
        this.b = recommendListener;
    }

    public static class RecommendOneViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        LinearLayout f9391a;
        ImageView b;
        ImageView c;
        HealthTextView d;

        public RecommendOneViewHolder(View view) {
            super(view);
            if (view == null) {
                LogUtil.h("DeviceRecommendedAdapter", "itemView is null");
                return;
            }
            this.f9391a = (LinearLayout) view.findViewById(R.id.item_layout);
            this.d = (HealthTextView) view.findViewById(R.id.recommend_device_name);
            this.b = (ImageView) view.findViewById(R.id.recommend_device_image);
            this.c = (ImageView) view.findViewById(R.id.device_app_update_red_dot);
        }
    }

    public static class RecommendVmallViewHolder extends RecyclerView.ViewHolder {
        LinearLayout b;
        HealthTextView c;
        ImageView d;
        ImageView e;

        public RecommendVmallViewHolder(View view) {
            super(view);
            if (view == null) {
                LogUtil.h("DeviceRecommendedAdapter", "itemView is null");
                return;
            }
            this.b = (LinearLayout) view.findViewById(R.id.vmall_item_layout);
            this.c = (HealthTextView) view.findViewById(R.id.vmall_recommend_device_name);
            this.e = (ImageView) view.findViewById(R.id.vmall_recommend_device_image);
            this.d = (ImageView) view.findViewById(R.id.vmall_device_app_update_red_dot);
        }
    }
}
