package com.huawei.hms.update.ui;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.ButtonConfig;
import com.huawei.hms.update.ui.HwAlertController;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes9.dex */
public class HwAlertDialog extends Dialog {

    /* renamed from: a, reason: collision with root package name */
    final HwAlertController f6104a;
    private final Paint b;
    private int c;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final HwAlertController.AlertParams f6105a;
        private final int b;
        private int c;

        public Builder(Context context) {
            this(context, HwAlertDialog.a(context, ResourceLoaderUtil.getStyleId("HwCloudAlertDialog")));
        }

        public HwAlertDialog create() {
            HwAlertDialog hwAlertDialog = new HwAlertDialog(this.f6105a.mCtx, this.b, this.c);
            this.f6105a.apply(hwAlertDialog.f6104a);
            hwAlertDialog.setCancelable(this.f6105a.mCancelable);
            if (this.f6105a.mCancelable) {
                hwAlertDialog.setCanceledOnTouchOutside(true);
            }
            hwAlertDialog.setOnCancelListener(this.f6105a.mOnCancelListener);
            hwAlertDialog.setOnDismissListener(this.f6105a.mOnDismListener);
            DialogInterface.OnKeyListener onKeyListener = this.f6105a.mOnKeyListener;
            if (onKeyListener != null) {
                hwAlertDialog.setOnKeyListener(onKeyListener);
            }
            return hwAlertDialog;
        }

        public Context getContext() {
            return this.f6105a.mCtx;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mListAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setButtonBgColor(int i, int i2, int i3) {
            ButtonConfig buttonConfig = this.f6105a.btnConfigs.get(Integer.valueOf(i));
            if (buttonConfig != null) {
                buttonConfig.f6083a = i2;
                buttonConfig.b = i3;
            }
            return this;
        }

        public Builder setButtonLevel(int i, ButtonConfig.Level level) {
            ButtonConfig buttonConfig = this.f6105a.btnConfigs.get(Integer.valueOf(i));
            if (buttonConfig != null) {
                buttonConfig.f = level;
                buttonConfig.b = HwDialogUtil.a(getContext(), level);
                buttonConfig.f6083a = HwDialogUtil.c(getContext(), level);
                buttonConfig.d = HwDialogUtil.b(getContext(), level);
                buttonConfig.c = HwDialogUtil.d(getContext(), level);
            }
            return this;
        }

        public Builder setButtonTextColor(int i, int i2, int i3) {
            ButtonConfig buttonConfig = this.f6105a.btnConfigs.get(Integer.valueOf(i));
            if (buttonConfig != null) {
                buttonConfig.c = i2;
                buttonConfig.d = i3;
            }
            return this;
        }

        public Builder setButtonTextSize(int i) {
            Set<Integer> keySet = this.f6105a.btnConfigs.keySet();
            if (keySet != null) {
                Iterator<Integer> it = keySet.iterator();
                while (it.hasNext()) {
                    this.f6105a.btnConfigs.get(it.next()).e = i;
                }
            }
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.f6105a.mCancelable = z;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mCursor = cursor;
            alertParams.mLabelColumn = str;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCustomTitle(View view) {
            this.f6105a.mCtmTitleView = view;
            return this;
        }

        public Builder setDialogBackground(int i) {
            this.c = i;
            return this;
        }

        public Builder setIcon(int i) {
            this.f6105a.mIcId = i;
            return this;
        }

        public Builder setIconAttribute(int i) {
            TypedValue typedValue = new TypedValue();
            this.f6105a.mCtx.getTheme().resolveAttribute(i, typedValue, true);
            this.f6105a.mIcId = typedValue.resourceId;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean z) {
            this.f6105a.mForceInverseBg = z;
            return this;
        }

        public Builder setItems(int i, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mItemArray = alertParams.mCtx.getResources().getTextArray(i);
            this.f6105a.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMessage(int i) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mMsg = alertParams.mCtx.getText(i);
            return this;
        }

        public Builder setMultiChoiceItems(int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mItemArray = alertParams.mCtx.getResources().getTextArray(i);
            HwAlertController.AlertParams alertParams2 = this.f6105a;
            alertParams2.mOnCkbClickListener = onMultiChoiceClickListener;
            alertParams2.mChkItems = zArr;
            alertParams2.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mNegativeBtnText = alertParams.mCtx.getText(i);
            this.f6105a.mNegativeBtnListener = onClickListener;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable drawable) {
            this.f6105a.mNegativeBtnIcon = drawable;
            return this;
        }

        public Builder setNeutralButton(int i, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mNeutralBtnText = alertParams.mCtx.getText(i);
            this.f6105a.mNeutralBtnListener = onClickListener;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable drawable) {
            this.f6105a.mNeutralBtnIcon = drawable;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f6105a.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f6105a.mOnDismListener = onDismissListener;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f6105a.mOnItemSleListener = onItemSelectedListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f6105a.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mPositiveBtnText = alertParams.mCtx.getText(i);
            this.f6105a.mPositiveBtnListener = onClickListener;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable drawable) {
            this.f6105a.mPositiveBtnIcon = drawable;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean z) {
            this.f6105a.mRecycleOnMeasure = z;
            return this;
        }

        public Builder setSingleChoiceItems(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mItemArray = alertParams.mCtx.getResources().getTextArray(i);
            HwAlertController.AlertParams alertParams2 = this.f6105a;
            alertParams2.mOnClickListener = onClickListener;
            alertParams2.mChkItem = i2;
            alertParams2.mIsSingleChoice = true;
            return this;
        }

        public Builder setSubTitle(int i) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mSubTitle = alertParams.mCtx.getText(i);
            return this;
        }

        public Builder setTitle(int i) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mTitle = alertParams.mCtx.getText(i);
            return this;
        }

        public Builder setView(int i) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mView = null;
            alertParams.mViewLayoutResId = i;
            alertParams.mViewSpcSpecified = false;
            return this;
        }

        public HwAlertDialog show() {
            HwAlertDialog create = create();
            create.show();
            return create;
        }

        public Builder(Context context, int i) {
            this.c = -1;
            this.f6105a = new HwAlertController.AlertParams(new ContextThemeWrapper(context, HwAlertDialog.a(context, i)));
            this.b = i;
        }

        public Builder setIcon(Drawable drawable) {
            this.f6105a.mIc = drawable;
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.f6105a.mMsg = charSequence;
            return this;
        }

        public Builder setSubTitle(CharSequence charSequence) {
            this.f6105a.mSubTitle = charSequence;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.f6105a.mTitle = charSequence;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mItemArray = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mNegativeBtnText = charSequence;
            alertParams.mNegativeBtnListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mNeutralBtnText = charSequence;
            alertParams.mNeutralBtnListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mPositiveBtnText = charSequence;
            alertParams.mPositiveBtnListener = onClickListener;
            return this;
        }

        public Builder setView(View view) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpcSpecified = false;
            return this;
        }

        public Builder setButtonBgColor(int i, int i2) {
            return setButtonBgColor(i, i2, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_normal"));
        }

        public Builder setButtonTextColor(int i, int i2) {
            return setButtonTextColor(i, i2, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color"));
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mItemArray = charSequenceArr;
            alertParams.mOnCkbClickListener = onMultiChoiceClickListener;
            alertParams.mChkItems = zArr;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mCursor = cursor;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mChkItem = i;
            alertParams.mLabelColumn = str;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        @Deprecated
        public Builder setView(View view, int i, int i2, int i3, int i4) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpcSpecified = true;
            alertParams.mViewSpcLeft = i;
            alertParams.mViewSpcTop = i2;
            alertParams.mViewSpcRight = i3;
            alertParams.mViewSpcBottom = i4;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mCursor = cursor;
            alertParams.mOnCkbClickListener = onMultiChoiceClickListener;
            alertParams.mIsChkColumn = str;
            alertParams.mLabelColumn = str2;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mItemArray = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mChkItem = i;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            HwAlertController.AlertParams alertParams = this.f6105a;
            alertParams.mListAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mChkItem = i;
            alertParams.mIsSingleChoice = true;
            return this;
        }
    }

    protected HwAlertDialog(Context context) {
        this(context, ResourceLoaderUtil.getStyleId("HwCloudAlertDialog"));
    }

    static int a(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        if (context == null) {
            HMSLog.w("HwAlertDialog", "ctx is null");
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    private boolean b() {
        return getContext() != null && (getContext().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    private void c() {
        if (this.f6104a == null) {
            return;
        }
        ArrayList<Button> arrayList = new ArrayList<>();
        LinkedHashMap<Button, Integer> linkedHashMap = new LinkedHashMap<>();
        a(arrayList, linkedHashMap);
        if (arrayList.size() == 0) {
            return;
        }
        int i = getWindow().getAttributes().width;
        int a2 = HwDialogUtil.a(getContext(), 16.0f);
        int a3 = HwDialogUtil.a(getContext(), 12.0f);
        int a4 = HwDialogUtil.a(getContext(), 8.0f);
        int a5 = HwDialogUtil.a(getContext(), 32.0f) * arrayList.size();
        int a6 = HwDialogUtil.a(getContext(), 8.0f) * arrayList.size();
        int i2 = (i - (a2 * 2)) - (a3 * 2);
        int a7 = ((((i2 - (a4 * 2)) - a5) - a6) - (HwDialogUtil.a(getContext(), 1.0f) * (arrayList.size() - 1))) / arrayList.size();
        this.c = (int) arrayList.get(0).getTextSize();
        Iterator<Button> it = arrayList.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            Button next = it.next();
            if (i3 == 1) {
                break;
            }
            int a8 = a(next, a7);
            if (a8 == 1) {
                i3 = a8;
            }
        }
        ViewParent parent = arrayList.get(0).getParent();
        boolean z = parent instanceof LinearLayout;
        if (z && i3 == 1) {
            ((LinearLayout) parent).setOrientation(1);
            a(arrayList, (i2 - a5) - a6);
        }
        if (!z || arrayList.size() <= 0) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) parent;
        linearLayout.removeAllViews();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            Button button = arrayList.get(i4);
            linearLayout.addView(button);
            a(arrayList, linkedHashMap, linearLayout, i4, button);
            if (i4 == arrayList.size() - 1 && linearLayout.getOrientation() == 1 && arrayList.size() > 1) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
                layoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_vertical_divider_bottom_height"));
                button.setLayoutParams(layoutParams);
            }
        }
    }

    private void d() {
        TextView textView;
        HwAlertController hwAlertController = this.f6104a;
        if (hwAlertController == null || (textView = hwAlertController.J) == null) {
            return;
        }
        textView.setTextColor(getContext().getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_msg")));
        this.f6104a.J.setTextSize(0, getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_msg_text_size")));
    }

    private void e() {
        TextView textView;
        TextView textView2;
        HwAlertController hwAlertController = this.f6104a;
        if (hwAlertController != null && (textView2 = hwAlertController.H) != null) {
            textView2.setTextColor(getContext().getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_title_text_color")));
            this.f6104a.H.setTextSize(0, getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_title_text_size")));
        }
        HwAlertController hwAlertController2 = this.f6104a;
        if (hwAlertController2 == null || (textView = hwAlertController2.I) == null) {
            return;
        }
        textView.setTextColor(getContext().getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_subtitle_text_color")));
        this.f6104a.I.setTextSize(0, getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_subtitle_text_size")));
    }

    public Activity getActivityFromContext(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (!(context instanceof Application) && !(context instanceof Service)) {
            while (context != null && (context instanceof ContextWrapper)) {
                context = ((ContextWrapper) context).getBaseContext();
                if (context instanceof Activity) {
                    return (Activity) context;
                }
            }
        }
        return null;
    }

    public Button getButton(int i) {
        return this.f6104a.getButton(i);
    }

    public ListView getListView() {
        return this.f6104a.getListView();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f6104a.installContent();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f6104a.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f6104a.onKeyUp(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void setButton(int i, CharSequence charSequence, Message message) {
        this.f6104a.setButton(i, charSequence, null, message, null);
    }

    public void setCustomTitle(View view) {
        this.f6104a.setCustomTitle(view);
    }

    public void setEmuiStyle() {
        e();
        d();
        c();
    }

    public void setIcon(int i) {
        this.f6104a.setIcon(i);
    }

    public void setIconAttribute(int i) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i, typedValue, true);
        this.f6104a.setIcon(typedValue.resourceId);
    }

    public void setMessage(CharSequence charSequence) {
        this.f6104a.setMessage(charSequence);
    }

    public void setSubTitle(CharSequence charSequence) {
        this.f6104a.setSubTitle(charSequence);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f6104a.setTitle(charSequence);
    }

    public void setView(View view) {
        this.f6104a.setView(view);
    }

    public void setWindowStyle(Window window) {
        float f;
        if (window == null) {
            return;
        }
        window.setDimAmount(0.2f);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.getDecorView().setBackgroundResource(R.color.transparent);
        window.setWindowAnimations(ResourceLoaderUtil.getStyleId("HwCloudDialogWindowAnim"));
        WindowManager.LayoutParams attributes = window.getAttributes();
        int i = getContext().getResources().getConfiguration().orientation;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int i2 = displayMetrics.widthPixels;
        float max = Math.max(i2, displayMetrics.heightPixels);
        if (max != 0.0f) {
            f = Math.min(i2, r2) / max;
        } else {
            HMSLog.e("HwAlertDialog", "maxPixels is 0.");
            f = 1.0f;
        }
        HMSLog.i("HwAlertDialog", "scale = " + f);
        if (i == 2) {
            attributes.width = (int) (i2 * 0.5d);
            window.setGravity(17);
            HMSLog.i("HwAlertDialog", "ORIENTATION_LANDSCAPE");
        } else {
            attributes.width = i2;
            window.setGravity(80);
            HMSLog.i("HwAlertDialog", "ORIENTATION_PORTRAIT");
        }
        if (b()) {
            if (f > 0.8d) {
                if (i == 2) {
                    attributes.width = (int) (i2 * 0.63f);
                } else {
                    attributes.width = (int) (i2 * 0.63f);
                }
            } else if (i == 2) {
                attributes.width = (int) (i2 * 0.42f);
            } else {
                attributes.width = (int) (i2 * 0.63f);
            }
            window.setGravity(17);
            HMSLog.i("HwAlertDialog", "IS_LARGE_SCREEN");
        }
        Activity activityFromContext = getActivityFromContext(getContext());
        if (activityFromContext != null && activityFromContext.isInMultiWindowMode()) {
            attributes.width = i2;
            window.setGravity(17);
            HMSLog.i("HwAlertDialog", "IS_MULTI_WINDOW_MODE");
        }
        attributes.height = -2;
        window.setAttributes(attributes);
        window.clearFlags(131080);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        setWindowStyle(getWindow());
        setEmuiStyle();
    }

    protected HwAlertDialog(Context context, int i) {
        super(context, a(context, i));
        this.b = new TextPaint();
        this.f6104a = new HwAlertController(getContext(), this, getWindow());
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.f6104a.setButton(i, charSequence, onClickListener, null, null);
    }

    public void setIcon(Drawable drawable) {
        this.f6104a.setIcon(drawable);
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.f6104a.setView(view, i, i2, i3, i4);
    }

    public void setButton(int i, CharSequence charSequence, Drawable drawable, DialogInterface.OnClickListener onClickListener) {
        this.f6104a.setButton(i, charSequence, onClickListener, null, drawable);
    }

    private void a(ArrayList<Button> arrayList, LinkedHashMap<Button, Integer> linkedHashMap, LinearLayout linearLayout, int i, Button button) {
        boolean z = false;
        if (linearLayout.getOrientation() == 0) {
            ButtonConfig buttonConfig = this.f6104a.q.get(linkedHashMap.get(button));
            if (buttonConfig == null) {
                buttonConfig = ButtonConfig.createDefault(getContext());
                this.f6104a.q.put(linkedHashMap.get(button), buttonConfig);
            }
            ButtonConfig.Level level = buttonConfig.f;
            ButtonConfig.Level level2 = ButtonConfig.Level.NORMAL;
            boolean z2 = level != level2;
            int i2 = i + 1;
            if (i2 < arrayList.size()) {
                Button button2 = arrayList.get(i2);
                ButtonConfig buttonConfig2 = this.f6104a.q.get(linkedHashMap.get(button2));
                if (buttonConfig2 == null) {
                    buttonConfig2 = ButtonConfig.createDefault(getContext());
                    this.f6104a.q.put(linkedHashMap.get(button2), buttonConfig2);
                }
                if (z2 || buttonConfig2.f != level2) {
                    z = true;
                }
            } else {
                z = z2;
            }
        }
        if (arrayList.size() <= 1 || i >= arrayList.size() - 1) {
            return;
        }
        if (!z) {
            linearLayout.addView(a(linearLayout.getOrientation()));
        } else {
            linearLayout.addView(a());
        }
    }

    protected HwAlertDialog(Context context, int i, int i2) {
        super(context, a(context, i));
        this.b = new TextPaint();
        this.f6104a = new HwAlertController(getContext(), this, getWindow());
    }

    protected HwAlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        this(context, ResourceLoaderUtil.getStyleId("HwCloudAlertDialog"));
        setCancelable(z);
        setOnCancelListener(onCancelListener);
    }

    private void a(ArrayList<Button> arrayList, int i) {
        if (arrayList.size() > 2) {
            Collections.reverse(arrayList);
        }
        Iterator<Button> it = arrayList.iterator();
        while (it.hasNext()) {
            Button next = it.next();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) next.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.weight = 1.0f;
            layoutParams.bottomMargin = 0;
            layoutParams.topMargin = 0;
            next.setLayoutParams(layoutParams);
            a(next, i, 1);
        }
        Iterator<Button> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().setTextSize(0, this.c);
        }
    }

    private void a(ArrayList<Button> arrayList, LinkedHashMap<Button, Integer> linkedHashMap) {
        if (!TextUtils.isEmpty(this.f6104a.w)) {
            Button button = this.f6104a.v;
            button.setText(button.getText().toString().toUpperCase(Locale.ROOT));
            arrayList.add(this.f6104a.v);
            linkedHashMap.put(this.f6104a.v, -3);
        }
        if (!TextUtils.isEmpty(this.f6104a.A)) {
            Button button2 = this.f6104a.z;
            button2.setText(button2.getText().toString().toUpperCase(Locale.ROOT));
            arrayList.add(this.f6104a.z);
            linkedHashMap.put(this.f6104a.z, -2);
        }
        if (TextUtils.isEmpty(this.f6104a.s)) {
            return;
        }
        Button button3 = this.f6104a.r;
        button3.setText(button3.getText().toString().toUpperCase(Locale.ROOT));
        arrayList.add(this.f6104a.r);
        linkedHashMap.put(this.f6104a.r, -1);
    }

    private int a(TextView textView, int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.weight = 1.0f;
        layoutParams.width = 0;
        layoutParams.height = HwDialogUtil.a(getContext(), 40.0f);
        textView.setLayoutParams(layoutParams);
        String obj = textView.getText().toString();
        this.b.setTextSize((int) textView.getTextSize());
        return ((float) i) > this.b.measureText(obj) ? 0 : 1;
    }

    private boolean a(TextView textView, int i, int i2) {
        boolean z;
        int textSize = (int) textView.getTextSize();
        String obj = textView.getText().toString();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_min_button_text_size"));
        this.b.setTextSize(textSize);
        float measureText = this.b.measureText(obj);
        while (true) {
            if (i >= measureText) {
                z = true;
                break;
            }
            if (textSize < dimensionPixelSize) {
                z = false;
                break;
            }
            textSize--;
            this.b.setTextSize(textSize);
            measureText = this.b.measureText(obj);
        }
        if (z || i2 == 1) {
            textView.setTextSize(0, textSize);
            if (((int) textView.getTextSize()) < this.c) {
                this.c = (int) textView.getTextSize();
            }
        }
        return z;
    }

    private View a(int i) {
        View view = new View(getContext());
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_divider_width"));
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_divider_height"));
        if (i == 1) {
            dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_vertical_divider_height"));
            dimensionPixelSize = -1;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2);
        layoutParams.gravity = 16;
        int dimensionPixelSize3 = getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_margin"));
        layoutParams.setMarginStart(dimensionPixelSize3);
        layoutParams.setMarginEnd(dimensionPixelSize3);
        view.setLayoutParams(layoutParams);
        if (i == 0) {
            view.setBackgroundResource(ResourceLoaderUtil.getColorId("hw_cloud_dialog_list_divider"));
        }
        return view;
    }

    private Space a() {
        Space space = new Space(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_horizontal_divider_strong_width")), getContext().getResources().getDimensionPixelSize(ResourceLoaderUtil.getDimenId("hw_cloud_dialog_button_space_min")));
        layoutParams.gravity = 16;
        space.setLayoutParams(layoutParams);
        return space;
    }
}
