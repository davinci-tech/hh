package com.huawei.ui.commonui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.GroupData;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.d;
import defpackage.koq;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class BaseGroupDataAdapter<T extends GroupData<S>, S extends d> extends BaseExpandableListAdapter {
    private static final int NUM_ZERO = 0;
    private static final String TAG = "PrivacyExpandViewAdapter";
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private OnItemClickListener mOnItemClickListener;
    private OnLongClickListener mOnLongClickListener;
    protected List<T> mGroupData = new ArrayList(10);
    private int mState = 1;
    private ArrayList<List<Boolean>> mCheckList = new ArrayList<>(10);
    private int mTotalCount = 0;
    private int mCheckedCount = 0;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int i, int i2, boolean z);
    }

    public interface OnItemClickListener {
        void onItemClickListener(int i, int i2);
    }

    public interface OnLongClickListener {
        void onLongClickListener(int i, int i2);
    }

    public static class d {
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    protected int getChildLayoutId() {
        return R.layout.commonui_ext_list_item;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    protected int getGroupLayoutId() {
        return R.layout.commonui_ext_list_father;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void refresh(List<T> list) {
        this.mGroupData.clear();
        this.mGroupData.addAll(list);
        this.mCheckedCount = 0;
        this.mTotalCount = getTotalCount(this.mGroupData);
        initCheckListData();
        notifyDataSetChanged();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.mGroupData.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (i >= this.mGroupData.size()) {
            LogUtil.e(TAG, "getChildrenCount is out of bounds");
            return 0;
        }
        List childList = this.mGroupData.get(i).getChildList();
        if (childList == null) {
            return 0;
        }
        return childList.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public T getGroup(int i) {
        if (this.mGroupData.size() > i) {
            return this.mGroupData.get(i);
        }
        LogUtil.e(TAG, "getGroup is out of bounds");
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public S getChild(int i, int i2) {
        if (!koq.d(this.mGroupData, i)) {
            return null;
        }
        List childList = this.mGroupData.get(i).getChildList();
        if (koq.d(childList, i2) && childList.get(i2) != null) {
            return (S) childList.get(i2);
        }
        LogUtil.e(TAG, "getChild is out of bounds");
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        RecyclerHolder recyclerHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getGroupLayoutId(), viewGroup, false);
            recyclerHolder = new RecyclerHolder(view);
            view.setTag(recyclerHolder);
        } else {
            Object tag = view.getTag();
            recyclerHolder = tag instanceof RecyclerHolder ? (RecyclerHolder) tag : null;
        }
        bindGroupView(recyclerHolder, getGroup(i), z);
        return view;
    }

    protected void bindGroupView(RecyclerHolder recyclerHolder, T t, boolean z) {
        ViewGroup viewGroup;
        if (recyclerHolder == null || (viewGroup = (ViewGroup) recyclerHolder.cwK_(R.id.father_layout)) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        if (t != null) {
            recyclerHolder.b(R.id.father_title, t.getTitle());
        }
        if (z) {
            recyclerHolder.d(R.id.content_item_arrow, R$drawable.ic_health_list_drop_down_arrow_sel);
            viewGroup.setBackgroundResource(R$drawable.health_card_bg_style2_top);
        } else {
            recyclerHolder.d(R.id.content_item_arrow, R$drawable.ic_health_list_drop_down_arrow_nor);
            viewGroup.setBackgroundResource(R$drawable.health_card_bg_style2);
        }
        setCardMargin(viewGroup, layoutParams, !z);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        RecyclerHolder recyclerHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getChildLayoutId(), viewGroup, false);
            recyclerHolder = getItemHolder(view);
            view.setTag(recyclerHolder);
        } else {
            Object tag = view.getTag();
            recyclerHolder = tag instanceof RecyclerHolder ? (RecyclerHolder) tag : null;
        }
        bindChildView(recyclerHolder, i, i2, z);
        return view;
    }

    protected void bindChildView(RecyclerHolder recyclerHolder, int i, int i2, boolean z) {
        if (recyclerHolder != null) {
            ViewGroup viewGroup = (ViewGroup) recyclerHolder.cwK_(R.id.item_layout);
            if (viewGroup != null) {
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                if (z) {
                    viewGroup.setBackgroundResource(R$drawable.health_card_bg_style2_bottom);
                    recyclerHolder.a(R.id.divider, 8);
                } else {
                    viewGroup.setBackgroundColor(ContextCompat.getColor(BaseApplication.getContext(), R$color.colorCardBg));
                    recyclerHolder.a(R.id.divider, 0);
                }
                setCardMargin(viewGroup, layoutParams, z);
            }
            setChildViewData(recyclerHolder, i2, i);
        }
    }

    private boolean updateCheckStatus(int i, int i2, boolean z) {
        if (koq.b(this.mCheckList, i)) {
            LogUtil.c(TAG, "switchCheckStatus mCheckList isOutOfBounds");
            return false;
        }
        List<Boolean> list = this.mCheckList.get(i);
        if (koq.b(list, i2)) {
            LogUtil.c(TAG, "switchCheckStatus childList isOutOfBounds");
            return false;
        }
        boolean booleanValue = list.get(i2).booleanValue();
        if (booleanValue == z) {
            return false;
        }
        if (booleanValue) {
            this.mCheckedCount--;
        } else {
            this.mCheckedCount++;
        }
        list.set(i2, Boolean.valueOf(z));
        return true;
    }

    private void setCardMargin(View view, ViewGroup.LayoutParams layoutParams, boolean z) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (z) {
                marginLayoutParams.setMargins(0, 0, 0, dimensionPixelSize);
            } else {
                marginLayoutParams.setMargins(0, 0, 0, 0);
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public RecyclerHolder getItemHolder(View view) {
        return new RecyclerHolder(view);
    }

    public S getDataModel(int i, int i2) {
        if (i >= this.mGroupData.size()) {
            return null;
        }
        List childList = this.mGroupData.get(i).getChildList();
        if (i2 >= childList.size()) {
            return null;
        }
        return (S) childList.get(i2);
    }

    public void setChildViewData(RecyclerHolder recyclerHolder, final int i, final int i2) {
        if (this.mState == 2) {
            if (koq.b(this.mCheckList, i2)) {
                LogUtil.c(TAG, "setChildViewData mCheckList isOutOfBounds");
                return;
            }
            List<Boolean> list = this.mCheckList.get(i2);
            if (koq.b(list, i)) {
                LogUtil.c(TAG, "setChildViewData childCheckList isOutOfBounds");
                return;
            }
            recyclerHolder.cwO_(R.id.item_check_box, null);
            Boolean bool = list.get(i);
            if (bool != null) {
                recyclerHolder.a(R.id.item_check_box, bool.booleanValue());
            }
            recyclerHolder.a(R.id.item_check_box, 0);
            recyclerHolder.cwO_(R.id.item_check_box, new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.commonui.adapter.BaseGroupDataAdapter$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    BaseGroupDataAdapter.this.m776x699f9152(i2, i, compoundButton, z);
                }
            });
        } else {
            recyclerHolder.a(R.id.item_check_box, 8);
            recyclerHolder.cwQ_(R.id.item_layout, new View.OnLongClickListener() { // from class: nko
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return BaseGroupDataAdapter.this.m777xf6da42d3(i2, i, view);
                }
            });
        }
        recyclerHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: nkn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseGroupDataAdapter.this.m778x8414f454(i2, i, view);
            }
        });
    }

    /* renamed from: lambda$setChildViewData$0$com-huawei-ui-commonui-adapter-BaseGroupDataAdapter, reason: not valid java name */
    /* synthetic */ void m776x699f9152(int i, int i2, CompoundButton compoundButton, boolean z) {
        updateCheckStatus(i, i2, z);
        OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(i, i2, z);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    /* renamed from: lambda$setChildViewData$1$com-huawei-ui-commonui-adapter-BaseGroupDataAdapter, reason: not valid java name */
    public /* synthetic */ boolean m777xf6da42d3(int i, int i2, View view) {
        OnLongClickListener onLongClickListener = this.mOnLongClickListener;
        if (onLongClickListener == null) {
            return true;
        }
        onLongClickListener.onLongClickListener(i, i2);
        return true;
    }

    /* renamed from: lambda$setChildViewData$2$com-huawei-ui-commonui-adapter-BaseGroupDataAdapter, reason: not valid java name */
    public /* synthetic */ void m778x8414f454(int i, int i2, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClickListener(i, i2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setAllChecked(boolean z) {
        if (z) {
            this.mCheckedCount = this.mTotalCount;
        } else {
            this.mCheckedCount = 0;
        }
        Iterator<List<Boolean>> it = this.mCheckList.iterator();
        while (it.hasNext()) {
            List<Boolean> next = it.next();
            for (int i = 0; i < next.size(); i++) {
                next.set(i, Boolean.valueOf(z));
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    public void setState(int i) {
        this.mState = i;
        if (i == 1) {
            setAllChecked(false);
        } else {
            notifyDataSetChanged();
        }
    }

    public void initCheckListData() {
        this.mCheckList.clear();
        if (koq.b(this.mGroupData)) {
            return;
        }
        for (T t : this.mGroupData) {
            if (t != null) {
                List childList = t.getChildList();
                if (childList != null) {
                    ArrayList arrayList = new ArrayList(10);
                    for (int i = 0; i < childList.size(); i++) {
                        arrayList.add(false);
                    }
                    this.mCheckList.add(arrayList);
                } else {
                    this.mCheckList.add(new ArrayList(10));
                }
            }
        }
    }

    public void switchCheckStatus(int i, int i2) {
        if (koq.b(this.mCheckList, i)) {
            LogUtil.c(TAG, "switchCheckStatus mCheckList isOutOfBounds");
            return;
        }
        List<Boolean> list = this.mCheckList.get(i);
        if (koq.b(list, i2)) {
            LogUtil.c(TAG, "switchCheckStatus childList isOutOfBounds");
            return;
        }
        boolean booleanValue = list.get(i2).booleanValue();
        if (booleanValue) {
            this.mCheckedCount--;
        } else {
            this.mCheckedCount++;
        }
        list.set(i2, Boolean.valueOf(!booleanValue));
        notifyDataSetChanged();
    }

    public List<S> getCheckedList() {
        ArrayList arrayList = new ArrayList(10);
        ArrayList<List<Boolean>> arrayList2 = this.mCheckList;
        if (arrayList2 != null && arrayList2.size() != 0) {
            for (int i = 0; i < this.mCheckList.size(); i++) {
                List<Boolean> list = this.mCheckList.get(i);
                if (koq.d(this.mGroupData, i)) {
                    List childList = this.mGroupData.get(i).getChildList();
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (list.get(i2).booleanValue()) {
                            arrayList.add((d) childList.get(i2));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public void updateDataWithRemoveChecked() {
        ArrayList<List<Boolean>> arrayList = this.mCheckList;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        List<T> arrayList2 = new ArrayList<>();
        for (int i = 0; i < this.mCheckList.size(); i++) {
            ArrayList arrayList3 = new ArrayList();
            List<Boolean> list = this.mCheckList.get(i);
            if (koq.d(this.mGroupData, i)) {
                T t = this.mGroupData.get(i);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (!list.get(i2).booleanValue()) {
                        arrayList3.add((d) t.getChildList().get(i2));
                    }
                }
                if (!arrayList3.isEmpty()) {
                    t.setChildList(arrayList3);
                    arrayList2.add(t);
                }
            }
        }
        refresh(arrayList2);
    }

    public int getCheckedCount() {
        return this.mCheckedCount;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    private int getTotalCount(List<T> list) {
        int i = 0;
        if (list != null && list.size() != 0) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                i += it.next().getChildList().size();
            }
        }
        return i;
    }

    public static abstract class GroupData<T extends d> {
        List<T> mChildList;

        public abstract String getTitle();

        public List<T> getChildList() {
            List<T> list = this.mChildList;
            return list == null ? new ArrayList() : list;
        }

        public void setChildList(List<T> list) {
            this.mChildList = list;
        }

        public T getChild(int i) {
            if (koq.d(this.mChildList, i)) {
                return this.mChildList.get(i);
            }
            return null;
        }
    }
}
