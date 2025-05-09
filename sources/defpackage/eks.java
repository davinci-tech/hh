package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleHorizontalGridContent;
import com.huawei.health.marketing.datatype.SingleHorizontalGridStandardContent;
import com.huawei.health.marketing.views.BaseSeriesSidingAdapter;
import com.huawei.health.marketing.views.holders.ThreeGridSeriesViewHolder;
import health.compact.a.util.LogUtil;
import java.util.Collection;
import java.util.List;

/* loaded from: classes3.dex */
public class eks extends BaseSeriesSidingAdapter<SingleHorizontalGridStandardContent> {
    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    public int getTitleColorId() {
        return 0;
    }

    public eks(Context context, int i, ResourceBriefInfo resourceBriefInfo, String str) {
        super(context, i, resourceBriefInfo, str);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingAdapter
    public int getItemWidth() {
        if (getItemCount() > 1) {
            return nsf.b(R.dimen._2131362983_res_0x7f0a04a7);
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ThreeGridSeriesViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.three_grid_series_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (koq.b((Collection<?>) this.mList, i)) {
            LogUtil.e(this.TAG, "position is error");
            return;
        }
        if (!(viewHolder instanceof ThreeGridSeriesViewHolder)) {
            LogUtil.e(this.TAG, "holder error");
            return;
        }
        ThreeGridSeriesViewHolder threeGridSeriesViewHolder = (ThreeGridSeriesViewHolder) viewHolder;
        SingleHorizontalGridStandardContent singleHorizontalGridStandardContent = (SingleHorizontalGridStandardContent) this.mList.get(i);
        nsy.cMv_(threeGridSeriesViewHolder.i(), singleHorizontalGridStandardContent.getThemeVisibility() ? singleHorizontalGridStandardContent.getTheme() : "", true);
        nsy.cMv_(threeGridSeriesViewHolder.g(), singleHorizontalGridStandardContent.getSubThemeVisibility() ? singleHorizontalGridStandardContent.getSubTheme() : "", true);
        nsy.cMv_(threeGridSeriesViewHolder.b(), singleHorizontalGridStandardContent.getTitle(), true);
        setBackground(singleHorizontalGridStandardContent.getBackgroundPicture(), singleHorizontalGridStandardContent.getBackgroundColor(), singleHorizontalGridStandardContent.getDarkBackgroundPicture(), singleHorizontalGridStandardContent.getDarkBackgroundColor(), threeGridSeriesViewHolder.arn_());
        setRecyclerViewItemLayout(viewHolder, i);
        nsy.cMn_(threeGridSeriesViewHolder.itemView, nkx.cwY_(getViewClickListener(singleHorizontalGridStandardContent.getLinkValue(), singleHorizontalGridStandardContent.getTheme(), i), this.mContext, true, ""));
        List<SingleHorizontalGridContent> subContents = singleHorizontalGridStandardContent.getSubContents();
        if (subContents == null || subContents.size() < 3) {
            return;
        }
        aqk_(threeGridSeriesViewHolder.ark_(), subContents, 0, singleHorizontalGridStandardContent.getTheme());
        aqk_(threeGridSeriesViewHolder.arl_(), subContents, 1, singleHorizontalGridStandardContent.getTheme());
        aqk_(threeGridSeriesViewHolder.arm_(), subContents, 2, singleHorizontalGridStandardContent.getTheme());
    }

    private void aqk_(ImageView imageView, List<SingleHorizontalGridContent> list, int i, String str) {
        if (nrt.a(this.mContext)) {
            imageView.setAlpha(0.86f);
        }
        SingleHorizontalGridContent singleHorizontalGridContent = list.get(i);
        if (!TextUtils.isEmpty(singleHorizontalGridContent.getPicture())) {
            nrf.cIS_(imageView, singleHorizontalGridContent.getPicture(), 0, 0, R.drawable._2131431376_res_0x7f0b0fd0);
        }
        nsy.cMn_(imageView, nkx.cwY_(getViewClickListener(singleHorizontalGridContent.getLink(), str, i), this.mContext, true, ""));
        setBiEventListener(str, "", null, i, imageView);
    }
}
