package com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.czl;
import defpackage.dju;
import defpackage.dsu;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RateControlCourseListAdapter extends RecyclerView.Adapter<ContentHolder> {
    private static final String TAG = "RateControlCourseListAdapter";
    private Context mContext;
    private List<dsu> mCourseInfoList = new ArrayList(10);
    private OnItemClickListener mOnItemClickListener;
    private int mType;

    public interface OnItemClickListener {
        void onItemClick(dsu dsuVar);
    }

    public RateControlCourseListAdapter(Context context, int i, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mType = i;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ContentHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ContentHolder(LayoutInflater.from(this.mContext).inflate(R.layout.item_rate_control_course, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ContentHolder contentHolder, int i) {
        dsu dsuVar = koq.d(this.mCourseInfoList, i) ? this.mCourseInfoList.get(i) : null;
        if (dsuVar == null) {
            LogUtil.h(TAG, "onBindViewHolder model is null");
        } else {
            contentHolder.setHolderData(dsuVar);
        }
    }

    public void setData(List<dsu> list) {
        if (list == null) {
            return;
        }
        if (koq.c(this.mCourseInfoList)) {
            this.mCourseInfoList.clear();
        }
        this.mCourseInfoList.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<dsu> list = this.mCourseInfoList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class ContentHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mContentRl;
        private HealthTextView mDescription;
        private HealthTextView mDetail;
        private HealthImageView mHealthImageView;
        private HealthTextView mLevelTitle;
        private HealthTextView mTitle;

        ContentHolder(View view) {
            super(view);
            this.mContentRl = (RelativeLayout) view.findViewById(R.id.item_content_rl);
            this.mHealthImageView = (HealthImageView) view.findViewById(R.id.item_rate_mode_bg_image);
            this.mLevelTitle = (HealthTextView) view.findViewById(R.id.level_title_text);
            this.mTitle = (HealthTextView) view.findViewById(R.id.item_rate_mode_title);
            this.mDetail = (HealthTextView) view.findViewById(R.id.item_rate_mode_detail);
            this.mDescription = (HealthTextView) view.findViewById(R.id.description_text);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHolderData(final dsu dsuVar) {
            this.mLevelTitle.setVisibility(TextUtils.isEmpty(dsuVar.getGroupName()) ? 8 : 0);
            this.mLevelTitle.setText(czl.d(RateControlCourseListAdapter.this.mType, dsuVar.getGroupName()));
            this.mTitle.setText(czl.d(RateControlCourseListAdapter.this.mType, dsuVar.getCourseName()));
            this.mDetail.setText(dsuVar.getLevel() + "  " + RateControlCourseListAdapter.this.mContext.getResources().getString(R.string._2130837641_res_0x7f020089, dju.a(dsuVar.getTotalTime())));
            this.mDescription.setText(czl.d(RateControlCourseListAdapter.this.mType, dsuVar.getDescription()));
            Glide.with(RateControlCourseListAdapter.this.mContext).load(czl.b(RateControlCourseListAdapter.this.mType, dsuVar.getDetailImage())).transform(new RoundedCorners(RateControlCourseListAdapter.this.mContext.getResources().getDimensionPixelOffset(R.dimen._2131362922_res_0x7f0a046a))).into(this.mHealthImageView);
            this.mContentRl.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseListAdapter$ContentHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RateControlCourseListAdapter.ContentHolder.this.m412xf39b9f3d(dsuVar, view);
                }
            });
        }

        /* renamed from: lambda$setHolderData$0$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlCourseListAdapter$ContentHolder, reason: not valid java name */
        /* synthetic */ void m412xf39b9f3d(dsu dsuVar, View view) {
            RateControlCourseListAdapter.this.mOnItemClickListener.onItemClick(dsuVar);
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
