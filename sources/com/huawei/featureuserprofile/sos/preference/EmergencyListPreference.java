package com.huawei.featureuserprofile.sos.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter;
import com.huawei.featureuserprofile.sos.interf.OnPreferenceChangedListener;
import com.huawei.featureuserprofile.sos.interf.ReloadablePreferenceInterface;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.btz;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class EmergencyListPreference extends ListPreference implements ReloadablePreferenceInterface {

    /* renamed from: a, reason: collision with root package name */
    private CharSequence[] f2033a;
    private boolean b;
    private int c;
    private WeakReference<Context> d;
    private CharSequence[] e;
    private OnPreferenceChangedListener h;
    private String i;
    private String j;

    public EmergencyListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099992_res_0x7f060158, R.attr._2131100042_res_0x7f06018a, R.attr._2131100043_res_0x7f06018b, R.attr._2131100044_res_0x7f06018c, R.attr._2131100691_res_0x7f060413});
        this.e = obtainStyledAttributes.getTextArray(1);
        this.f2033a = obtainStyledAttributes.getTextArray(3);
        obtainStyledAttributes.recycle();
        if (this.e == null) {
            this.e = getEntries();
        }
        if (this.f2033a == null) {
            this.f2033a = getEntryValues();
        }
        setLayoutResource(R.layout.preference_item_double);
        this.j = getKey();
        this.d = new WeakReference<>(context);
    }

    @Override // com.huawei.featureuserprofile.sos.interf.ReloadablePreferenceInterface
    public void reloadFromPreference() {
        setValue(getValue());
    }

    @Override // android.preference.ListPreference, android.preference.Preference
    public CharSequence getSummary() {
        if (findIndexOfValue(getValue()) < 0) {
            return super.getSummary();
        }
        return getEntry();
    }

    @Override // android.preference.ListPreference
    public String getValue() {
        return EmergencyInfoManager.c().getSharedPreference(btz.c() + this.j);
    }

    @Override // android.preference.ListPreference
    public void setValue(String str) {
        boolean z = !TextUtils.equals(this.i, str);
        if (z || !this.b) {
            this.i = str;
            this.b = true;
            EmergencyInfoManager.c().setSharedPreference(btz.c() + this.j, str, new StorageParams(1));
            if (z) {
                notifyChanged();
                OnPreferenceChangedListener onPreferenceChangedListener = this.h;
                if (onPreferenceChangedListener != null) {
                    onPreferenceChangedListener.onPreferenceChanged(this.j, this.i);
                }
            }
        }
    }

    @Override // android.preference.ListPreference, android.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        setValue(!TextUtils.isEmpty(getValue()) ? getValue() : (String) obj);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        if (view == null) {
            LogUtil.h("EmergencyListPreference", "onBindView view is null");
            return;
        }
        super.onBindView(view);
        BaseActivity.setViewSafeRegion(true, (LinearLayout) view.findViewById(R.id.ll_edit_preference));
        ImageView imageView = (ImageView) view.findViewById(R.id.preference_image);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.double_title);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.double_summary);
        String key = getKey();
        key.hashCode();
        if (key.equals("blood_type")) {
            setDialogTitle(R.string._2130848861_res_0x7f022c5d);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131429738_res_0x7f0b096a));
            healthTextView2.setText(getContext().getString(R.string._2130848861_res_0x7f022c5d));
            if ("".equals(getValue())) {
                healthTextView.setText(getContext().getString(R.string._2130848862_res_0x7f022c5e));
                return;
            } else {
                healthTextView.setText(getValue());
                return;
            }
        }
        if (key.equals("organ_donor")) {
            setDialogTitle(R.string._2130848863_res_0x7f022c5f);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131430221_res_0x7f0b0b4d));
            healthTextView2.setText(getContext().getString(R.string._2130848863_res_0x7f022c5f));
            if ("".equals(getValue())) {
                healthTextView.setText(getContext().getString(R.string._2130848864_res_0x7f022c60));
                return;
            } else {
                healthTextView.setText(getValue());
                return;
            }
        }
        LogUtil.a("EmergencyListPreference", "onBindView default");
    }

    @Override // android.preference.ListPreference, android.preference.DialogPreference
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        if (this.e == null || this.f2033a == null) {
            LogUtil.h("EmergencyListPreference", "mEntries or mEntryValues is null");
            return;
        }
        int b = b();
        this.c = b;
        builder.setSingleChoiceItems(this.e, b, new DialogInterface.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.preference.EmergencyListPreference.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                EmergencyListPreference.this.c = i;
                LogUtil.a("EmergencyListPreference", "mClickedDialogEntryIndex:", Integer.valueOf(EmergencyListPreference.this.c));
                EmergencyListPreference.this.onClick(dialogInterface, -1);
                dialogInterface.dismiss();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    private int b() {
        return findIndexOfValue(this.i);
    }

    public void d(OnPreferenceChangedListener onPreferenceChangedListener) {
        this.h = onPreferenceChangedListener;
    }

    @Override // android.preference.ListPreference, android.preference.DialogPreference
    protected void onDialogClosed(boolean z) {
        int i;
        CharSequence[] charSequenceArr;
        if (!z || (i = this.c) < 0 || (charSequenceArr = this.f2033a) == null) {
            return;
        }
        String obj = charSequenceArr[i].toString();
        if (callChangeListener(obj)) {
            setValue(obj);
        }
    }

    @Override // android.preference.DialogPreference
    protected void showDialog(Bundle bundle) {
        WeakReference<Context> weakReference = this.d;
        if (weakReference == null) {
            LogUtil.a("EmergencyListPreference", "showDialog mContextReference is null");
            return;
        }
        Context context = weakReference.get();
        if (context == null) {
            LogUtil.a("EmergencyListPreference", "showDialog context is null");
            return;
        }
        View inflate = View.inflate(context, R.layout.layout_list_preference, null);
        ListView listView = (ListView) inflate.findViewById(R.id.lv_list_preference);
        final EmergencyListAdapter emergencyListAdapter = new EmergencyListAdapter(context, this.f2033a, b());
        listView.setAdapter((ListAdapter) emergencyListAdapter);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.a(getTitle() != null ? getTitle().toString() : null).czg_(inflate).czc_(R.string._2130848866_res_0x7f022c62, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.preference.EmergencyListPreference.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final CustomViewDialog e = builder.e();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.featureuserprofile.sos.preference.EmergencyListPreference.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                emergencyListAdapter.c(i);
                EmergencyListPreference emergencyListPreference = EmergencyListPreference.this;
                emergencyListPreference.setValue(emergencyListPreference.f2033a[i].toString());
                e.dismiss();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
        e.setCancelable(false);
        e.show();
        e.getWindow().setSoftInputMode(32);
    }
}
