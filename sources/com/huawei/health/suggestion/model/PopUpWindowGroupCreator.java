package com.huawei.health.suggestion.model;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.FilterFlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class PopUpWindowGroupCreator implements View.OnClickListener {
    private static final String TAG = "Suggestion_PopUpWindowGroupCreator";
    private FilterFlowLayout mClassifyLayout;
    private List<Attribute> mClassifyList;
    private OnConfirmClickListener mConfirmListener;
    private Context mContext;
    private List<Attribute> mEquipments;
    private FilterFlowLayout mEquipmentsLayout;
    private LinearLayout mLinearLayout;
    private int mMaxWidth;
    private PopupWindow mPopupWindow;
    private View mPopupwindowSpace;
    private FilterFlowLayout mTrainingPointLayout;
    private List<Attribute> mTrainingPoints;
    private List<Integer> mClassifysStatus = new ArrayList();
    private List<Integer> mPointsStatus = new ArrayList();
    private List<Integer> mEquipmentsStatus = new ArrayList();
    private FilterFlowLayout.OnCheckChangeListener mSecondClsListener = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.model.PopUpWindowGroupCreator.1
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            PopUpWindowGroupCreator.this.mClassifysStatus.add(Integer.valueOf(i));
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            if (koq.c(PopUpWindowGroupCreator.this.mClassifysStatus)) {
                int indexOf = PopUpWindowGroupCreator.this.mClassifysStatus.indexOf(Integer.valueOf(i));
                LogUtil.c(PopUpWindowGroupCreator.TAG, "mSecondClsListener => index : ", Integer.valueOf(indexOf));
                PopUpWindowGroupCreator.this.mClassifysStatus.remove(indexOf);
                return;
            }
            LogUtil.h(PopUpWindowGroupCreator.TAG, "mSecondClsListener -> onUnCheck value is not exist");
        }
    };
    private FilterFlowLayout.OnCheckChangeListener mPointListener = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.model.PopUpWindowGroupCreator.2
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            PopUpWindowGroupCreator.this.mPointsStatus.add(Integer.valueOf(i));
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            int indexOf = PopUpWindowGroupCreator.this.mPointsStatus.indexOf(Integer.valueOf(i));
            LogUtil.c(PopUpWindowGroupCreator.TAG, "onPointListener => index : ", Integer.valueOf(indexOf));
            if (indexOf > -1) {
                PopUpWindowGroupCreator.this.mPointsStatus.remove(indexOf);
            } else {
                LogUtil.b(PopUpWindowGroupCreator.TAG, "onPointListener -> onUnCheck value is not exist");
            }
        }
    };
    private FilterFlowLayout.OnCheckChangeListener mEquipmentListener = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.model.PopUpWindowGroupCreator.3
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            PopUpWindowGroupCreator.this.mEquipmentsStatus.add(Integer.valueOf(i));
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            int indexOf = PopUpWindowGroupCreator.this.mEquipmentsStatus.indexOf(Integer.valueOf(i));
            LogUtil.c(PopUpWindowGroupCreator.TAG, "mEquipmentListener => index : ", Integer.valueOf(indexOf));
            if (indexOf > -1) {
                PopUpWindowGroupCreator.this.mEquipmentsStatus.remove(indexOf);
            } else {
                LogUtil.b(PopUpWindowGroupCreator.TAG, "mEquipmentListener -> onUnCheck value is not exist");
            }
        }
    };

    public interface OnConfirmClickListener {
        void onFilterChange(List<Integer> list, List<Integer> list2, List<Integer> list3);

        void resetFilter();
    }

    public PopUpWindowGroupCreator(Context context) {
        if (context == null) {
            LogUtil.b(TAG, " context is null");
            return;
        }
        this.mContext = context;
        this.mMaxWidth = nsn.h(context);
        LinearLayout linearLayout = (LinearLayout) LinearLayout.inflate(this.mContext, R.layout.pop_recom_view_health_dialog, null);
        this.mLinearLayout = linearLayout;
        this.mClassifyLayout = (FilterFlowLayout) linearLayout.findViewById(R.id.workout_filter_classify_group);
        this.mTrainingPointLayout = (FilterFlowLayout) this.mLinearLayout.findViewById(R.id.workout_filter_training_points_group);
        this.mEquipmentsLayout = (FilterFlowLayout) this.mLinearLayout.findViewById(R.id.workout_filter_equipments_group);
        this.mPopupwindowSpace = this.mLinearLayout.findViewById(R.id.recom_popup_dialog_space);
    }

    public PopupWindow create(List<Attribute> list, List<Attribute> list2, List<Attribute> list3) {
        View inflate = LinearLayout.inflate(this.mContext, R.layout.pop_recom_view_health_dialog, null);
        if (inflate instanceof LinearLayout) {
            this.mLinearLayout = (LinearLayout) inflate;
        }
        ((HealthScrollView) this.mLinearLayout.findViewById(R.id.health_dialog_scrollview)).setScrollViewVerticalDirectionEvent(true);
        this.mClassifyLayout = (FilterFlowLayout) this.mLinearLayout.findViewById(R.id.workout_filter_classify_group);
        this.mTrainingPointLayout = (FilterFlowLayout) this.mLinearLayout.findViewById(R.id.workout_filter_training_points_group);
        this.mEquipmentsLayout = (FilterFlowLayout) this.mLinearLayout.findViewById(R.id.workout_filter_equipments_group);
        this.mPopupwindowSpace = this.mLinearLayout.findViewById(R.id.recom_popup_dialog_space);
        ((HealthButton) this.mLinearLayout.findViewById(R.id.recom_popu_dialog_confirm)).setOnClickListener(this);
        this.mPopupwindowSpace.setOnClickListener(this);
        ((HealthButton) this.mLinearLayout.findViewById(R.id.recom_popu_dialog_reset)).setOnClickListener(this);
        setData(list, list2, list3);
        notifyDataSetChanged();
        return setPopupWindow(this.mLinearLayout, this.mMaxWidth);
    }

    public void setData(List<Attribute> list, List<Attribute> list2, List<Attribute> list3) {
        this.mClassifyList = list;
        this.mTrainingPoints = list2;
        this.mEquipments = list3;
        notifyDataSetChanged();
    }

    public void setClassifys(List<Attribute> list) {
        this.mClassifyList = list;
        notifyTargetChanged();
    }

    private void notifyDataSetChanged() {
        this.mClassifyLayout.d(this.mContext, this.mClassifyList);
        this.mClassifyLayout.setOnCheckedChangeListener(this.mSecondClsListener);
        this.mTrainingPointLayout.d(this.mContext, this.mTrainingPoints);
        this.mTrainingPointLayout.setOnCheckedChangeListener(this.mPointListener);
        this.mEquipmentsLayout.d(this.mContext, this.mEquipments);
        this.mEquipmentsLayout.setOnCheckedChangeListener(this.mEquipmentListener);
    }

    private void notifyTargetChanged() {
        this.mClassifyLayout.d(this.mContext, this.mClassifyList);
        this.mClassifyLayout.setOnCheckedChangeListener(this.mSecondClsListener);
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.mConfirmListener = onConfirmClickListener;
    }

    private PopupWindow setPopupWindow(View view, int i) {
        PopupWindow popupWindow = new PopupWindow(view, i, -2);
        this.mPopupWindow = popupWindow;
        popupWindow.setFocusable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        return this.mPopupWindow;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mConfirmListener == null) {
            LogUtil.h(TAG, " listener is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.recom_popu_dialog_reset) {
            LogUtil.c(TAG, " click reset");
            setData(this.mClassifyList, this.mTrainingPoints, this.mEquipments);
            this.mClassifysStatus.clear();
            this.mPointsStatus.clear();
            this.mEquipmentsStatus.clear();
            this.mConfirmListener.resetFilter();
        }
        if (id == R.id.recom_popu_dialog_confirm) {
            this.mConfirmListener.onFilterChange(this.mClassifysStatus, this.mPointsStatus, this.mEquipmentsStatus);
            this.mPopupWindow.dismiss();
        }
        if (id == R.id.recom_popup_dialog_space) {
            this.mPopupWindow.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
