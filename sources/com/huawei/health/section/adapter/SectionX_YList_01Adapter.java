package com.huawei.health.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eed;
import defpackage.eeh;
import defpackage.eet;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class SectionX_YList_01Adapter extends RecyclerView.Adapter<b> {
    public static int BITMAP_DEFAULT_CORNER = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
    protected static final int IMAGE_SIZE = 2;
    protected static final int LAST_TWO = 2;
    private static final String TAG = "SectionX_YList_01Adapter";
    protected List<String> difficultyList;
    protected List<String> durationList;
    protected List<Object> imageList;
    protected List<Integer> imageSize;
    protected List<String> intervalList;
    protected Context mContext;
    protected List<String> nameList;
    protected OnClickSectionListener recycleViewListener;
    protected List<eed> tagDataList;
    protected b viewHolder;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public SectionX_YList_01Adapter(Context context, eeh eehVar) {
        this.mContext = context;
        setDataInternal(eehVar);
    }

    public void setData(eeh eehVar) {
        setDataInternal(eehVar);
        notifyDataSetChanged();
    }

    protected void setDataInternal(eeh eehVar) {
        this.nameList = eehVar.j();
        this.imageList = eehVar.a();
        this.imageSize = eehVar.b();
        this.intervalList = eehVar.e();
        this.difficultyList = eehVar.c();
        this.durationList = eehVar.d();
        this.tagDataList = eehVar.f();
        this.recycleViewListener = eehVar.h();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.mContext).inflate(R.layout.sectionx_ylist_01_item, viewGroup, false));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(b bVar, int i) {
        this.viewHolder = bVar;
        if (i == getItemCount() - 1) {
            setDividerViewVisibility(8);
        } else {
            Context context = this.mContext;
            if (context != null && nsn.ag(context) && i == getItemCount() - 2) {
                setDividerViewVisibility(8);
            } else {
                setDividerViewVisibility(0);
            }
        }
        setDataAndRefresh(bVar, i);
    }

    protected void setDataAndRefresh(b bVar, int i) {
        setItemImage(bVar, i);
        setHolderText(bVar, i);
    }

    protected void setItemImage(b bVar, int i) {
        if (this.imageList.size() == 0) {
            bVar.b.setVisibility(8);
            LogUtil.a(TAG, "setItemImage imageList.size() == 0");
            return;
        }
        if (!eet.b(this.imageList, i) || bVar.d == null) {
            return;
        }
        List<Integer> list = this.imageSize;
        if (list != null && list.size() == 2) {
            ViewGroup.LayoutParams layoutParams = bVar.d.getLayoutParams();
            layoutParams.height = (int) TypedValue.applyDimension(1, this.imageSize.get(0).intValue(), this.mContext.getResources().getDisplayMetrics());
            layoutParams.width = (int) TypedValue.applyDimension(1, this.imageSize.get(1).intValue(), this.mContext.getResources().getDisplayMetrics());
            bVar.d.setLayoutParams(layoutParams);
        }
        setLoadRoundRectangle(this.imageList.get(i), bVar.a(), bVar);
    }

    protected void setDividerViewVisibility(int i) {
        if (this.viewHolder.c == null) {
            return;
        }
        this.viewHolder.c.setVisibility(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.imageList, this.nameList, this.difficultyList, this.intervalList, this.durationList);
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        protected TextView f2971a;
        protected RelativeLayout b;
        protected LinearLayout c;
        protected HealthImageView d;
        protected TextView e;
        protected Object f;
        protected TextView g;
        protected RelativeLayout h;
        protected TextView i;
        protected TextView j;

        public b(View view) {
            super(view);
        }

        public void d(Object obj) {
            this.f = obj;
        }

        public Object a() {
            return this.f;
        }
    }

    protected void setHolderText(b bVar, int i) {
        if (eet.b(this.nameList, i) && bVar.j != null) {
            bVar.j.setText(this.nameList.get(i));
        }
        if (eet.b(this.difficultyList, i) && bVar.e != null) {
            bVar.e.setText(this.difficultyList.get(i));
        }
        if (eet.b(this.intervalList, i) && bVar.g != null) {
            bVar.g.setText(this.intervalList.get(i));
        }
        if (eet.b(this.durationList, i) && bVar.f2971a != null) {
            bVar.f2971a.setText(this.durationList.get(i));
        }
        bindHolderTag(bVar, i);
        setItemClickListener(bVar, i);
    }

    protected void setItemClickListener(b bVar, final int i) {
        if (bVar.h != null) {
            bVar.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.section.adapter.SectionX_YList_01Adapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (SectionX_YList_01Adapter.this.recycleViewListener != null) {
                        SectionX_YList_01Adapter.this.recycleViewListener.onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    protected void bindHolderTag(b bVar, int i) {
        if (bVar.i == null) {
            return;
        }
        eed eedVar = koq.d(this.tagDataList, i) ? this.tagDataList.get(i) : null;
        if (eedVar == null || TextUtils.isEmpty(eedVar.d())) {
            bVar.i.setVisibility(8);
            return;
        }
        bVar.i.setText(eedVar.d());
        bVar.i.setTextColor(eedVar.e());
        bVar.i.setBackground(eedVar.agR_());
        bVar.i.setVisibility(0);
    }

    protected void setLoadRoundRectangle(Object obj, Object obj2, b bVar) {
        if (obj instanceof String) {
            String str = (String) obj;
            String str2 = obj2 instanceof String ? (String) obj2 : "";
            if (str.length() <= 0 || str2.equals(str)) {
                return;
            }
            loadRoundRectangleImage(bVar, str, BITMAP_DEFAULT_CORNER);
            bVar.d(str);
            return;
        }
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if ((obj2 instanceof Integer ? ((Integer) obj2).intValue() : 0) != intValue) {
                nrf.a(intValue, bVar.d, BITMAP_DEFAULT_CORNER);
                bVar.d(Integer.valueOf(intValue));
                return;
            }
            return;
        }
        nrf.a(R.drawable._2131431393_res_0x7f0b0fe1, bVar.d, BITMAP_DEFAULT_CORNER);
    }

    private void loadRoundRectangleImage(b bVar, String str, int i) {
        bVar.d.setTag(R.id.item_picture, str);
        if (bVar.d.getTag(R.id.item_picture) == null || !str.equals(bVar.d.getTag(R.id.item_picture))) {
            return;
        }
        nrf.b(str, bVar.d, i);
    }
}
