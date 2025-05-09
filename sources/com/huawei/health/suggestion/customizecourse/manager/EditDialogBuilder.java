package com.huawei.health.suggestion.customizecourse.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.customizecourse.manager.adapter.FitnessEditListMoreAdapter;
import com.huawei.health.suggestion.customizecourse.manager.model.CourseConfig;
import com.huawei.health.suggestion.model.customcourse.IntensityHeartRateRange;
import com.huawei.health.suggestion.model.customcourse.IntensityHeartRateSection;
import com.huawei.health.suggestion.model.customcourse.IntensityPace;
import com.huawei.health.suggestion.model.customcourse.IntensityPaceSection;
import com.huawei.health.suggestion.model.customcourse.IntensityStepRate;
import com.huawei.health.suggestion.model.customcourse.TargetCalorie;
import com.huawei.health.suggestion.model.customcourse.TargetDistance;
import com.huawei.health.suggestion.model.customcourse.TargetHeartRate;
import com.huawei.health.suggestion.model.customcourse.TypeConfig;
import com.huawei.health.suggestion.ui.run.adapter.ChoiceDataAdapter;
import com.huawei.health.suggestion.ui.run.holder.RadioViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ffg;
import defpackage.fhx;
import defpackage.fjd;
import defpackage.gdg;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class EditDialogBuilder {

    /* renamed from: a, reason: collision with root package name */
    private Context f3011a;
    private CustomViewDialog c;
    private CustomViewDialog h;
    private CustomViewDialog i;
    private CustomViewDialog j;
    private OnDismissListener k;
    private CustomViewDialog n;
    private CustomViewDialog o;
    private CustomViewDialog r;
    private CustomViewDialog s;
    private CustomViewDialog t;
    private CustomViewDialog u;
    private CustomViewDialog v;
    private int[] b = {1, 0};
    private int[] x = {0, 3, 0};
    private int e = TargetCalorie.getDefaultValuePosition();
    private int[] g = {0, TargetHeartRate.getDefaultValuePosition()};
    private int[] m = {0, 0, 0, 0, 0};
    private int l = 0;
    private int[] p = {170, 0, 180};
    private int[] d = {40, 0, 50};
    private int[] f = {0, 1};
    private int q = 0;

    public interface OnDismissListener {
        void onDismiss();
    }

    public EditDialogBuilder(Context context, OnDismissListener onDismissListener) {
        this.f3011a = context;
        this.k = onDismissListener;
    }

    public void d(final ChoreographedSingleAction choreographedSingleAction, final Map<String, ChoreographedSingleAction> map) {
        if (choreographedSingleAction == null || map == null || map.isEmpty()) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        final ArrayList arrayList = new ArrayList(map.size());
        a(map, choreographedSingleAction, arrayList);
        final ChoiceDataAdapter choiceDataAdapter = new ChoiceDataAdapter(this.f3011a, arrayList);
        choiceDataAdapter.b(new RadioViewHolder.OnItemClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.3
            @Override // com.huawei.health.suggestion.ui.run.holder.RadioViewHolder.OnItemClickListener
            public void onItemClick(int i) {
                for (gdg gdgVar : arrayList) {
                    if (gdgVar.d()) {
                        gdgVar.a(false);
                    }
                }
                if (koq.b(arrayList, i)) {
                    LogUtil.b("Suggestion_EditDialogBuilder", "choiceList out of bound.");
                    return;
                }
                gdg gdgVar2 = (gdg) arrayList.get(i);
                gdgVar2.a(true);
                EditDialogBuilder.this.a(gdgVar2, choreographedSingleAction, (Map<String, ChoreographedSingleAction>) map, choiceDataAdapter);
            }
        });
        a(choiceDataAdapter, this.f3011a.getResources().getString(R.string._2130841780_res_0x7f0210b4));
    }

    private void a(Map<String, ChoreographedSingleAction> map, ChoreographedSingleAction choreographedSingleAction, List<gdg> list) {
        for (ChoreographedSingleAction choreographedSingleAction2 : map.values()) {
            if (choreographedSingleAction2 != null) {
                AtomicAction action = choreographedSingleAction.getAction();
                AtomicAction action2 = choreographedSingleAction2.getAction();
                list.add(new gdg((action == null || action2 == null || !action.getId().equals(action2.getId())) ? false : true, choreographedSingleAction2.getAction()));
            }
        }
        Collections.sort(list, new Comparator() { // from class: fja
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((gdg) obj).b().getId().compareTo(((gdg) obj2).b().getId());
                return compareTo;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(gdg gdgVar, ChoreographedSingleAction choreographedSingleAction, Map<String, ChoreographedSingleAction> map, ChoiceDataAdapter choiceDataAdapter) {
        if (gdgVar.b() instanceof AtomicAction) {
            AtomicAction atomicAction = (AtomicAction) gdgVar.b();
            choreographedSingleAction.setAction(atomicAction);
            ChoreographedSingleAction choreographedSingleAction2 = map.get(atomicAction.getId());
            if (choreographedSingleAction2 != null) {
                choreographedSingleAction.setTargetConfig(choreographedSingleAction2.getTargetConfig());
                choreographedSingleAction.setIntensityConfig(choreographedSingleAction2.getIntensityConfig());
            }
            choiceDataAdapter.notifyDataSetChanged();
            b(this.c);
            LogUtil.a("Suggestion_EditDialogBuilder", "action choose:", gdgVar.b().toString());
        }
    }

    public void e(final TargetConfig targetConfig, final CourseConfig courseConfig) {
        if (targetConfig == null || courseConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        final List<gdg> targetTypeList = TypeConfig.getTargetTypeList(this.f3011a, CommonUtil.e(targetConfig.getId(), 1));
        final ChoiceDataAdapter choiceDataAdapter = new ChoiceDataAdapter(this.f3011a, targetTypeList);
        choiceDataAdapter.b(new RadioViewHolder.OnItemClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.15
            @Override // com.huawei.health.suggestion.ui.run.holder.RadioViewHolder.OnItemClickListener
            public void onItemClick(int i) {
                for (gdg gdgVar : targetTypeList) {
                    if (gdgVar.d()) {
                        gdgVar.a(false);
                    }
                }
                if (koq.b(targetTypeList, i)) {
                    LogUtil.b("Suggestion_EditDialogBuilder", "targetList out of bound.");
                    return;
                }
                gdg gdgVar2 = (gdg) targetTypeList.get(i);
                gdgVar2.a(true);
                targetConfig.saveId(gdgVar2.b().getId());
                targetConfig.saveName(gdgVar2.b().getName());
                targetConfig.setValueH(0.0d);
                targetConfig.setValueL(0.0d);
                targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
                for (TargetConfig targetConfig2 : courseConfig.getDefaultTargetConfigs()) {
                    if (targetConfig2.getId().equals(targetConfig.getId())) {
                        targetConfig.setValueType(targetConfig2.getValueType());
                        targetConfig.setValueL(targetConfig2.getValueL());
                        targetConfig.setValueH(targetConfig2.getValueH());
                    }
                }
                TypeConfig.setDefaultImperialData(targetConfig);
                LogUtil.a("Suggestion_EditDialogBuilder", "target:", targetConfig.toString());
                choiceDataAdapter.notifyDataSetChanged();
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.c);
                LogUtil.a("Suggestion_EditDialogBuilder", "target choose:", gdgVar2.b().toString());
            }
        });
        a(choiceDataAdapter, this.f3011a.getResources().getString(R.string._2130848617_res_0x7f022b69));
    }

    public void d(final TargetConfig targetConfig, final CourseConfig courseConfig) {
        if (targetConfig == null || courseConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        final List<gdg> intensityTypeList = TypeConfig.getIntensityTypeList(this.f3011a, CommonUtil.e(targetConfig.getId(), 255));
        final ChoiceDataAdapter choiceDataAdapter = new ChoiceDataAdapter(this.f3011a, intensityTypeList);
        choiceDataAdapter.b(new RadioViewHolder.OnItemClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.23
            @Override // com.huawei.health.suggestion.ui.run.holder.RadioViewHolder.OnItemClickListener
            public void onItemClick(int i) {
                for (gdg gdgVar : intensityTypeList) {
                    if (gdgVar.d()) {
                        gdgVar.a(false);
                    }
                }
                if (koq.b(intensityTypeList, i)) {
                    return;
                }
                gdg gdgVar2 = (gdg) intensityTypeList.get(i);
                gdgVar2.a(true);
                targetConfig.saveId(gdgVar2.b().getId());
                targetConfig.saveName(gdgVar2.b().getName());
                targetConfig.setValueH(0.0d);
                targetConfig.setValueL(0.0d);
                targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
                for (TargetConfig targetConfig2 : courseConfig.getDefaultIntensityConfigs()) {
                    if (targetConfig2.getId().equals(targetConfig.getId())) {
                        targetConfig.setValueType(targetConfig2.getValueType());
                        targetConfig.setValueL(targetConfig2.getValueL());
                        targetConfig.setValueH(targetConfig2.getValueH());
                    }
                }
                TypeConfig.setDefaultImperialData(targetConfig);
                LogUtil.a("Suggestion_EditDialogBuilder", "intensity:", targetConfig.toString());
                choiceDataAdapter.notifyDataSetChanged();
                LogUtil.a("Suggestion_EditDialogBuilder", "intensity choose:", gdgVar2.b().toString());
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.c);
            }
        });
        a(choiceDataAdapter, this.f3011a.getResources().getString(R.string._2130848599_res_0x7f022b57));
    }

    private void a(ChoiceDataAdapter choiceDataAdapter, String str) {
        HealthRecycleView healthRecycleView = new HealthRecycleView(this.f3011a);
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.f3011a));
        healthRecycleView.setAdapter(choiceDataAdapter);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.a(str).czg_(healthRecycleView).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.c = e;
        e.show();
    }

    public void b(final TargetConfig targetConfig) {
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(this.f3011a);
        HashMap hashMap = new HashMap(2);
        String[] kiloMeters = TargetDistance.getKiloMeters(this.f3011a);
        String[] meters = TargetDistance.getMeters(this.f3011a);
        hashMap.put(0, kiloMeters);
        hashMap.put(1, meters);
        if (targetConfig.getValueL() > 0.0d && !UnitUtil.h()) {
            int floor = (int) Math.floor(targetConfig.getValueL() / 1000.0d);
            int valueL = (int) targetConfig.getValueL();
            int[] iArr = this.b;
            iArr[0] = floor;
            iArr[1] = (valueL % 1000) / 50;
        }
        healthMultiNumberPicker.setDataContent(2, hashMap, new boolean[]{true, true}, this.b);
        final CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.b(false);
        builder.d(R.string._2130841530_res_0x7f020fba).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EditDialogBuilder.this.a(healthMultiNumberPicker, targetConfig) > 0) {
                    EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                    editDialogBuilder.b(editDialogBuilder.r);
                } else {
                    builder.b(false);
                    nrh.d(EditDialogBuilder.this.f3011a, EditDialogBuilder.this.f3011a.getString(R.string._2130848713_res_0x7f022bc9, 0));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.r = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 2) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the SelectedLocation Value is empty");
            return 0;
        }
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        int[] iArr = this.b;
        iArr[0] = i;
        iArr[1] = i2;
        int d = UnitUtil.h() ? (int) ((UnitUtil.d(i, 3) * 1000.0d) + UnitUtil.d(i2 * 50, 2)) : (i2 * 50) + (i * 1000);
        if (d > 0) {
            double d2 = d;
            targetConfig.setValueL(d2);
            targetConfig.setValueH(d2);
            targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
            LogUtil.a("Suggestion_EditDialogBuilder", "mDistanceTargetSelected is:", Integer.valueOf(d));
        } else {
            LogUtil.b("Suggestion_EditDialogBuilder", "mDistanceTargetSelected is wrong");
        }
        return d;
    }

    public void e(final TargetConfig targetConfig) {
        if (targetConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(this.f3011a);
        HashMap hashMap = new HashMap(3);
        hashMap.put(0, healthMultiNumberPicker.d(0, 23, (String) null));
        hashMap.put(1, healthMultiNumberPicker.d(0, 59, (String) null));
        hashMap.put(2, healthMultiNumberPicker.d(0, 59, (String) null));
        if (targetConfig.getValueL() > 0.0d) {
            this.x[0] = ((int) targetConfig.getValueL()) / 3600;
            this.x[1] = ((int) (targetConfig.getValueL() % 3600.0d)) / 60;
            this.x[2] = ((int) targetConfig.getValueL()) % 60;
        }
        int[] iArr = this.x;
        healthMultiNumberPicker.setDataContent(3, hashMap, new boolean[]{true, true, true}, new int[]{iArr[0], iArr[1], iArr[2]});
        healthMultiNumberPicker.setColonAndUnit(3);
        final CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.b(false);
        builder.d(R.string._2130841790_res_0x7f0210be).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditDialogBuilder.this.c(targetConfig, healthMultiNumberPicker, builder);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                builder.b(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.u = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(TargetConfig targetConfig, HealthMultiNumberPicker healthMultiNumberPicker, CustomViewDialog.Builder builder) {
        int b = b(healthMultiNumberPicker);
        if (b > 0) {
            LogUtil.a("Suggestion_EditDialogBuilder", "mTimeTargetSelected is ", Integer.valueOf(b));
            double d = b;
            targetConfig.setValueL(d);
            targetConfig.setValueH(d);
            targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
            builder.b(true);
            b(this.u);
            return;
        }
        LogUtil.h("Suggestion_EditDialogBuilder", "mTimeTargetSelected is wrong");
        builder.b(false);
        Context context = this.f3011a;
        nrh.d(context, context.getResources().getString(R.string._2130848681_res_0x7f022ba9, 0));
    }

    private int b(HealthMultiNumberPicker healthMultiNumberPicker) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 3) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the minute and second SelectedLocation Value is empty");
            return 0;
        }
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        int i3 = selectedLocations[2];
        int[] iArr = this.x;
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        return (i * 3600) + (i2 * 60) + i3;
    }

    public void d(final TargetConfig targetConfig) {
        if (targetConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        HealthNumberPicker healthNumberPicker = new HealthNumberPicker(this.f3011a);
        healthNumberPicker.setDisplayedValues(TargetCalorie.getCalorieArray(this.f3011a));
        healthNumberPicker.setMaxValue(r1.length - 1);
        healthNumberPicker.setMinValue(0);
        if (targetConfig.getValueL() >= 50.0d) {
            this.e = (((int) targetConfig.getValueL()) / 50) - 1;
        }
        healthNumberPicker.setValue(this.e);
        healthNumberPicker.setSelectionDivider(ContextCompat.getDrawable(this.f3011a, R.drawable._2131430934_res_0x7f0b0e16));
        healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.33
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public void onValuePicked(int i, int i2) {
                EditDialogBuilder.this.e = i2;
                LogUtil.a("Suggestion_EditDialogBuilder", "caloriesTargetSelected:", Integer.valueOf(EditDialogBuilder.this.e));
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.czg_(healthNumberPicker);
        builder.d(R.string._2130841791_res_0x7f0210bf);
        builder.cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                targetConfig.setValueL((EditDialogBuilder.this.e + 1) * 50);
                targetConfig.setValueH((EditDialogBuilder.this.e + 1) * 50);
                targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.s);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.s = e;
        e.show();
    }

    public void c(TargetConfig targetConfig, fhx fhxVar) {
        if (targetConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        HashMap hashMap = new HashMap(2);
        String[] strArr = {this.f3011a.getResources().getString(R.string._2130848618_res_0x7f022b6a), this.f3011a.getResources().getString(R.string._2130848619_res_0x7f022b6b)};
        String[] hearRateArray = TargetHeartRate.getHearRateArray();
        hashMap.put(0, strArr);
        hashMap.put(1, hearRateArray);
        if (targetConfig.getValueL() > 0.0d) {
            if (targetConfig.getId().equals(Integer.toString(4))) {
                int[] iArr = this.g;
                iArr[0] = 0;
                iArr[1] = TargetHeartRate.getPositionByHeartRateValue((int) targetConfig.getValueL());
            } else {
                int[] iArr2 = this.g;
                iArr2[0] = 1;
                iArr2[1] = TargetHeartRate.getPositionByHeartRateValue((int) targetConfig.getValueH());
            }
        }
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{true, true});
        healthMultiNumberPicker.setDisplayedValues(0, strArr, this.g[0]);
        healthMultiNumberPicker.setDisplayedValues(1, hearRateArray, this.g[1]);
        View inflate = LayoutInflater.from(this.f3011a).inflate(R.layout.dialog_custom_courses_edit, (ViewGroup) null);
        axV_(inflate, healthMultiNumberPicker, fhxVar);
        axY_(inflate, healthMultiNumberPicker, targetConfig);
    }

    private void axV_(View view, final HealthMultiNumberPicker healthMultiNumberPicker, final fhx fhxVar) {
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.custom_courses_sub_header);
        healthSubHeader.setLayoutManager(new LinearLayoutManager(this.f3011a, 1, false) { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.5
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        final FitnessEditListMoreAdapter fitnessEditListMoreAdapter = new FitnessEditListMoreAdapter(this.f3011a.getResources().getString(R.string._2130848466_res_0x7f022ad2), "");
        healthSubHeader.setAdapter(fitnessEditListMoreAdapter);
        if (fhxVar != null) {
            fitnessEditListMoreAdapter.c(a(this.g, fhxVar));
        }
        ((RelativeLayout) view.findViewById(R.id.custom_courses_pick_content)).addView(healthMultiNumberPicker);
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.4
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker2, int i2, int i3) {
                if (fhxVar == null) {
                    LogUtil.h("Suggestion_EditDialogBuilder", "maxHeartRateZoneConfig == null");
                } else {
                    fitnessEditListMoreAdapter.c(EditDialogBuilder.this.a(healthMultiNumberPicker.getSelectedLocations(), fhxVar));
                }
            }
        });
    }

    private void axY_(View view, final HealthMultiNumberPicker healthMultiNumberPicker, final TargetConfig targetConfig) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.c(false).czg_(view).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EditDialogBuilder.this.b(healthMultiNumberPicker, targetConfig);
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.v);
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        CustomViewDialog e = builder.e();
        this.v = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int[] iArr, fhx fhxVar) {
        int i = iArr[0];
        int a2 = (int) UnitUtil.a(((fhxVar.e() * TargetHeartRate.getHeartRateValueByPosition(iArr[1])) * 1.0d) / 100.0d, 0);
        if (i == 0) {
            return this.f3011a.getResources().getQuantityString(R.plurals._2130903322_res_0x7f03011a, a2, Integer.valueOf(a2));
        }
        return this.f3011a.getResources().getQuantityString(R.plurals._2130903323_res_0x7f03011b, a2, Integer.valueOf(a2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 2) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the SelectedLocation Value is empty");
            return;
        }
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        int[] iArr = this.g;
        iArr[0] = i;
        iArr[1] = i2;
        int heartRateValueByPosition = TargetHeartRate.getHeartRateValueByPosition(i2);
        if (i == 0) {
            targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
            double d = heartRateValueByPosition;
            targetConfig.setValueL(d);
            targetConfig.setValueH(d);
            targetConfig.setId(Integer.toString(4));
        } else {
            targetConfig.setValueType(TargetConfig.ValueTypes.HIGH_VALUE.getValue());
            double d2 = heartRateValueByPosition;
            targetConfig.setValueH(d2);
            targetConfig.setValueL(d2);
            targetConfig.setId(Integer.toString(5));
        }
        if (heartRateValueByPosition > 0) {
            LogUtil.a("Suggestion_EditDialogBuilder", "heartRateTargetSelected is:", Integer.valueOf(heartRateValueByPosition));
        } else {
            LogUtil.b("Suggestion_EditDialogBuilder", "heartRateTargetSelected is wrong");
        }
    }

    public void a(TargetConfig targetConfig) {
        String[] paceMinuteArray;
        String[] paceMinuteArray2;
        if (targetConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        if (UnitUtil.h()) {
            paceMinuteArray = IntensityPace.getPaceMinuteInchArray();
            paceMinuteArray2 = IntensityPace.getPaceMinuteInchArray();
        } else {
            paceMinuteArray = IntensityPace.getPaceMinuteArray();
            paceMinuteArray2 = IntensityPace.getPaceMinuteArray();
        }
        String[] paceSecondArray = IntensityPace.getPaceSecondArray();
        String[] paceSecondArray2 = IntensityPace.getPaceSecondArray();
        HashMap hashMap = new HashMap(5);
        hashMap.put(0, paceMinuteArray);
        hashMap.put(1, paceSecondArray);
        hashMap.put(2, new String[]{Constants.LINK});
        hashMap.put(3, paceMinuteArray2);
        hashMap.put(4, paceSecondArray2);
        i(targetConfig);
        c(hashMap, targetConfig);
    }

    private void i(TargetConfig targetConfig) {
        if (UnitUtil.h()) {
            int[] iArr = this.m;
            iArr[0] = 9;
            iArr[1] = 30;
            iArr[3] = 11;
            iArr[4] = 0;
        } else {
            int[] iArr2 = this.m;
            iArr2[0] = 5;
            iArr2[1] = 30;
            iArr2[3] = 6;
            iArr2[4] = 30;
        }
        if (targetConfig.getValueL() <= 0.0d || targetConfig.getValueH() <= 0.0d || UnitUtil.h()) {
            return;
        }
        int valueL = ((int) targetConfig.getValueL()) / 60000;
        int valueL2 = (((int) targetConfig.getValueL()) % 60000) / 1000;
        int valueH = ((int) targetConfig.getValueH()) / 60000;
        int valueH2 = (((int) targetConfig.getValueH()) % 60000) / 1000;
        int[] iArr3 = this.m;
        iArr3[0] = valueL - 1;
        iArr3[1] = valueL2;
        iArr3[3] = valueH - 1;
        iArr3[4] = valueH2;
    }

    private void c(Map<Integer, String[]> map, final TargetConfig targetConfig) {
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        healthMultiNumberPicker.setDataContent(5, map, new boolean[]{true, true, true, true, true}, this.m);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        if (UnitUtil.h()) {
            builder.d(R.string._2130839823_res_0x7f02090f);
        } else {
            builder.d(R.string._2130839824_res_0x7f020910);
        }
        builder.czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditDialogBuilder.this.e(healthMultiNumberPicker, targetConfig);
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.j);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.j = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 5) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the SelectedLocation Value is empty");
            return;
        }
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        int[] iArr = this.m;
        int i3 = i + 1;
        iArr[0] = i3;
        iArr[1] = i2;
        int i4 = selectedLocations[3];
        int i5 = selectedLocations[4];
        int i6 = i4 + 1;
        iArr[3] = i6;
        iArr[4] = i5;
        int i7 = (i3 * 60) + i2;
        int i8 = (i6 * 60) + i5;
        if (i7 > i8) {
            i8 = i7;
            i7 = i8;
        }
        if (UnitUtil.h()) {
            targetConfig.setValueL(UnitUtil.e(i7, 3) * 1000.0d);
            targetConfig.setValueH(UnitUtil.e(i8, 3) * 1000.0d);
        } else {
            targetConfig.setValueL(i7 * 1000);
            targetConfig.setValueH(i8 * 1000);
        }
        targetConfig.setValueType(TargetConfig.ValueTypes.RANGE_VALUE.getValue());
        LogUtil.a("Suggestion_EditDialogBuilder", "mPacePickerSelected ", Integer.valueOf(i), " ", Integer.valueOf(i2), " ", Integer.valueOf(i4), " ", Integer.valueOf(i5));
    }

    public void a(TargetConfig targetConfig, ffg ffgVar) {
        if (targetConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        HealthNumberPicker healthNumberPicker = new HealthNumberPicker(this.f3011a);
        healthNumberPicker.setDisplayedValues(IntensityPaceSection.getPaceSectionArrays(this.f3011a));
        healthNumberPicker.setMaxValue(r1.length - 1);
        healthNumberPicker.setMinValue(0);
        if (targetConfig.getValueL() > 0.0d) {
            this.l = ((int) targetConfig.getValueL()) - 1;
        }
        healthNumberPicker.setValue(this.l);
        healthNumberPicker.setSelectionDivider(ContextCompat.getDrawable(this.f3011a, R.drawable._2131430934_res_0x7f0b0e16));
        View inflate = LayoutInflater.from(this.f3011a).inflate(R.layout.dialog_custom_courses_edit, (ViewGroup) null);
        axX_(inflate, ffgVar, healthNumberPicker);
        aya_(inflate, healthNumberPicker, targetConfig);
    }

    private void axX_(View view, final ffg ffgVar, HealthNumberPicker healthNumberPicker) {
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.custom_courses_sub_header);
        healthSubHeader.setLayoutManager(new LinearLayoutManager(this.f3011a, 1, false) { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.10
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        final FitnessEditListMoreAdapter fitnessEditListMoreAdapter = new FitnessEditListMoreAdapter(this.f3011a.getResources().getString(R.string._2130843948_res_0x7f02192c), d(this.l, ffgVar));
        healthSubHeader.setAdapter(fitnessEditListMoreAdapter);
        healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.11
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public void onValuePicked(int i, int i2) {
                fitnessEditListMoreAdapter.c(EditDialogBuilder.this.d(i2, ffgVar));
            }
        });
    }

    private void aya_(View view, HealthNumberPicker healthNumberPicker, final TargetConfig targetConfig) {
        ((RelativeLayout) view.findViewById(R.id.custom_courses_pick_content)).addView(healthNumberPicker);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.c(false);
        builder.czg_(view);
        builder.cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                targetConfig.setValueL(EditDialogBuilder.this.l + 1);
                targetConfig.setValueH(EditDialogBuilder.this.l + 1);
                targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.n);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        CustomViewDialog e = builder.e();
        this.n = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(int i, ffg ffgVar) {
        this.l = i;
        int b = ffgVar.b(i + 1, true);
        int b2 = ffgVar.b(this.l + 1, false);
        LogUtil.a("Suggestion_EditDialogBuilder", "paceSectionSelected:", Integer.valueOf(this.l), " ", Integer.valueOf(b), " ", Integer.valueOf(b2));
        String a2 = fjd.a(this.f3011a, b);
        String a3 = fjd.a(this.f3011a, b2);
        if (b > b2) {
            return e(this.f3011a, b, a3, a2);
        }
        return e(this.f3011a, b2, a2, a3);
    }

    private String e(Context context, int i, String str, String str2) {
        String string = context.getResources().getString(R.string._2130845600_res_0x7f021fa0, str, str2);
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903281_res_0x7f0300f1, i, string);
        }
        return context.getResources().getQuantityString(R.plurals._2130903280_res_0x7f0300f0, i, string);
    }

    public void c(final TargetConfig targetConfig) {
        if (targetConfig == null) {
            LogUtil.b("Suggestion_EditDialogBuilder", "args is empty.");
            return;
        }
        HashMap hashMap = new HashMap(3);
        String[] strArr = {Constants.LINK};
        String[] stepRateArray = IntensityStepRate.getStepRateArray();
        String[] stepRateArray2 = IntensityStepRate.getStepRateArray();
        hashMap.put(0, stepRateArray);
        hashMap.put(1, strArr);
        hashMap.put(2, stepRateArray2);
        if (targetConfig.getValueL() > 0.0d && targetConfig.getValueH() > 0.0d) {
            this.p[0] = IntensityStepRate.getPositionByStepRateValue((int) targetConfig.getValueL());
            this.p[2] = IntensityStepRate.getPositionByStepRateValue((int) targetConfig.getValueH());
        }
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(this.f3011a);
        healthMultiNumberPicker.setDataContent(3, hashMap, new boolean[]{true, true, true}, this.p);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.d(R.string._2130839754_res_0x7f0208ca).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditDialogBuilder.this.c(healthMultiNumberPicker, targetConfig);
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.o);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.o = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 3) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the SelectedLocation Value is empty");
            return;
        }
        int[] iArr = this.p;
        int i = selectedLocations[0];
        iArr[0] = i;
        iArr[2] = selectedLocations[2];
        int stepRateValueByPosition = IntensityStepRate.getStepRateValueByPosition(i);
        int stepRateValueByPosition2 = IntensityStepRate.getStepRateValueByPosition(this.p[2]);
        targetConfig.setValueType(TargetConfig.ValueTypes.RANGE_VALUE.getValue());
        if (stepRateValueByPosition >= stepRateValueByPosition2) {
            LogUtil.b("Suggestion_EditDialogBuilder", "mStepRateSelected is wrong.", Integer.valueOf(stepRateValueByPosition), Integer.valueOf(stepRateValueByPosition2));
            targetConfig.setValueL(stepRateValueByPosition2);
            targetConfig.setValueH(stepRateValueByPosition);
        } else {
            targetConfig.setValueL(stepRateValueByPosition);
            targetConfig.setValueH(stepRateValueByPosition2);
            LogUtil.a("Suggestion_EditDialogBuilder", "mStepRateSelected is right.", Integer.valueOf(stepRateValueByPosition), Integer.valueOf(stepRateValueByPosition2));
        }
    }

    public void a(TargetConfig targetConfig, final fhx fhxVar) {
        HashMap hashMap = new HashMap(3);
        String[] strArr = {Constants.LINK};
        String[] hearRateRangeArray = IntensityHeartRateRange.getHearRateRangeArray();
        String[] hearRateRangeArray2 = IntensityHeartRateRange.getHearRateRangeArray();
        boolean z = false;
        hashMap.put(0, hearRateRangeArray);
        int i = 1;
        hashMap.put(1, strArr);
        if (targetConfig.getValueL() >= 20.0d && targetConfig.getValueH() >= 20.0d) {
            this.d[0] = ((int) targetConfig.getValueL()) - 20;
            this.d[2] = ((int) targetConfig.getValueH()) - 20;
        }
        hashMap.put(2, hearRateRangeArray2);
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        healthMultiNumberPicker.setPickerCount(3, new boolean[]{false, false, false});
        healthMultiNumberPicker.setDisplayedValues(0, hearRateRangeArray, this.d[0]);
        healthMultiNumberPicker.setDisplayedValues(1, strArr, this.d[1]);
        healthMultiNumberPicker.setDisplayedValues(2, hearRateRangeArray2, this.d[2]);
        View inflate = LayoutInflater.from(this.f3011a).inflate(R.layout.dialog_custom_courses_edit, (ViewGroup) null);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.custom_courses_sub_header);
        healthSubHeader.setLayoutManager(new LinearLayoutManager(this.f3011a, i, z) { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.20
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        final FitnessEditListMoreAdapter fitnessEditListMoreAdapter = new FitnessEditListMoreAdapter(this.f3011a.getResources().getString(R.string._2130842622_res_0x7f0213fe), b(this.d, fhxVar));
        healthSubHeader.setAdapter(fitnessEditListMoreAdapter);
        ((RelativeLayout) inflate.findViewById(R.id.custom_courses_pick_content)).addView(healthMultiNumberPicker);
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.19
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i2, HealthMultiNumberPicker healthMultiNumberPicker2, int i3, int i4) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                selectedLocations[i2] = i4;
                fitnessEditListMoreAdapter.c(EditDialogBuilder.this.b(selectedLocations, fhxVar));
            }
        });
        axZ_(inflate, healthMultiNumberPicker, targetConfig);
    }

    private void axZ_(View view, final HealthMultiNumberPicker healthMultiNumberPicker, final TargetConfig targetConfig) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.c(false).czg_(view).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EditDialogBuilder.this.d(healthMultiNumberPicker, targetConfig);
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.h);
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        CustomViewDialog e = builder.e();
        this.h = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(int[] iArr, fhx fhxVar) {
        double d;
        double d2;
        int i = iArr[0] + 20;
        int i2 = iArr[2] + 20;
        if (i >= i2) {
            d = i2;
            d2 = i;
        } else {
            d = i;
            d2 = i2;
        }
        return b(this.f3011a, fhxVar, d, d2);
    }

    private String b(Context context, fhx fhxVar, double d, double d2) {
        if (d <= 0.0d || d2 <= 0.0d || fhxVar == null) {
            return "";
        }
        int a2 = (int) UnitUtil.a((fhxVar.e() * d) / 100.0d, 0);
        return context.getResources().getQuantityString(R.plurals._2130903483_res_0x7f0301bb, a2, Integer.valueOf(a2), Integer.valueOf((int) UnitUtil.a((fhxVar.e() * d2) / 100.0d, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 3) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the SelectedLocation Value is empty");
            return;
        }
        int[] iArr = this.d;
        int i = selectedLocations[0];
        iArr[0] = i;
        int i2 = selectedLocations[2];
        iArr[2] = i2;
        int i3 = i + 20;
        int i4 = i2 + 20;
        targetConfig.setValueType(TargetConfig.ValueTypes.RANGE_VALUE.getValue());
        if (i3 >= i4) {
            LogUtil.b("Suggestion_EditDialogBuilder", "mHeartRateIntensitySelected is wrong ", Integer.valueOf(i3), Integer.valueOf(i4));
            targetConfig.setValueL(i4);
            targetConfig.setValueH(i3);
        } else {
            targetConfig.setValueL(i3);
            targetConfig.setValueH(i4);
            LogUtil.a("Suggestion_EditDialogBuilder", "mHeartRateTargetSelected is ", Integer.valueOf(i3), Integer.valueOf(i4));
        }
    }

    public void c(final TargetConfig targetConfig, final fhx fhxVar, final fhx fhxVar2, final int i) {
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        a(i, healthMultiNumberPicker, targetConfig);
        View axW_ = axW_(fhxVar, fhxVar2, i, healthMultiNumberPicker);
        ((RelativeLayout) axW_.findViewById(R.id.custom_courses_pick_content)).addView(healthMultiNumberPicker);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.c(false).czg_(axW_).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditDialogBuilder.this.d(healthMultiNumberPicker, targetConfig, fhxVar, fhxVar2, i);
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.i);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.i = e;
        e.show();
    }

    private View axW_(final fhx fhxVar, final fhx fhxVar2, final int i, final HealthMultiNumberPicker healthMultiNumberPicker) {
        View inflate = LayoutInflater.from(this.f3011a).inflate(R.layout.dialog_custom_courses_edit, (ViewGroup) null);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.custom_courses_sub_header);
        healthSubHeader.setLayoutManager(new LinearLayoutManager(this.f3011a, 1, false) { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.21
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        final FitnessEditListMoreAdapter fitnessEditListMoreAdapter = new FitnessEditListMoreAdapter(this.f3011a.getResources().getString(R.string._2130841806_res_0x7f0210ce), a(this.f, fhxVar, fhxVar2, i));
        healthSubHeader.setAdapter(fitnessEditListMoreAdapter);
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.24
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i2, HealthMultiNumberPicker healthMultiNumberPicker2, int i3, int i4) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                selectedLocations[i2] = i4;
                fitnessEditListMoreAdapter.c(EditDialogBuilder.this.a(selectedLocations, fhxVar, fhxVar2, i));
            }
        });
        return inflate;
    }

    private void a(int i, HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig) {
        String[] strArr;
        String[] reserveHeartRateSectionArray;
        if (i == 0) {
            strArr = new String[]{this.f3011a.getResources().getString(R.string._2130842680_res_0x7f021438)};
            reserveHeartRateSectionArray = IntensityHeartRateSection.getMaxHeartRateSectionArray(this.f3011a);
        } else {
            strArr = new String[]{this.f3011a.getResources().getString(R.string._2130842681_res_0x7f021439)};
            reserveHeartRateSectionArray = IntensityHeartRateSection.getReserveHeartRateSectionArray(this.f3011a);
        }
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, false});
        if (targetConfig.getValueL() > 0.0d) {
            this.f[1] = ((int) targetConfig.getValueL()) - 1;
        }
        boolean bc = LanguageUtil.bc(this.f3011a);
        healthMultiNumberPicker.setDisplayedValues(bc ? 1 : 0, strArr, this.f[0]);
        healthMultiNumberPicker.setDisplayedValues(!LanguageUtil.bc(this.f3011a) ? 1 : 0, reserveHeartRateSectionArray, this.f[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int[] iArr, fhx fhxVar, fhx fhxVar2, int i) {
        int a2;
        if (iArr.length < 2) {
            return "";
        }
        int i2 = iArr[1];
        int i3 = 0;
        if (i == 0) {
            if (fhxVar != null) {
                int i4 = i2 + 1;
                int a3 = fhxVar.a(i4, true);
                a2 = fhxVar.a(i4, false);
                i3 = a3;
            }
            a2 = 0;
        } else {
            if (fhxVar2 != null) {
                int i5 = i2 + 1;
                int a4 = fhxVar2.a(i5, true);
                a2 = fhxVar2.a(i5, false);
                i3 = a4;
            }
            a2 = 0;
        }
        return this.f3011a.getResources().getQuantityString(R.plurals._2130903483_res_0x7f0301bb, a2, Integer.valueOf(i3), Integer.valueOf(a2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthMultiNumberPicker healthMultiNumberPicker, TargetConfig targetConfig, fhx fhxVar, fhx fhxVar2, int i) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 2) {
            LogUtil.c("Suggestion_EditDialogBuilder", "the SelectedLocation Value is empty");
            return;
        }
        int i2 = selectedLocations[1];
        this.f[1] = i2;
        targetConfig.setId(Integer.toString(17));
        if (i == 0) {
            if (fhxVar != null) {
                int i3 = i2 + 1;
                LogUtil.a("Suggestion_EditDialogBuilder", "maxHeartRateZoneConfig lowHeartRate:", Integer.valueOf(fhxVar.a(i3, true)), " highHeartRate:", Integer.valueOf(fhxVar.a(i3, false)));
            }
        } else if (fhxVar2 != null) {
            int i4 = i2 + 1;
            LogUtil.a("Suggestion_EditDialogBuilder", "reserveHeartRateZoneConfig lowHeartRate:", Integer.valueOf(fhxVar2.a(i4, true)), " highHeartRate:", Integer.valueOf(fhxVar2.a(i4, false)));
        }
        double d = i2 + 1;
        targetConfig.setValueL(d);
        targetConfig.setValueH(d);
        targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
        LogUtil.a("Suggestion_EditDialogBuilder", "mHeartRateSectionSelected is ", Integer.valueOf(i2));
    }

    public void e(final ChoreographedMultiActions choreographedMultiActions, int i) {
        final HealthNumberPicker healthNumberPicker = new HealthNumberPicker(this.f3011a);
        healthNumberPicker.setDisplayedValues(TypeConfig.getRepeatTimeArray(this.f3011a, i));
        healthNumberPicker.setMaxValue(r4.length - 1);
        healthNumberPicker.setMinValue(0);
        if (choreographedMultiActions.getRepeatTimes() >= 2) {
            this.q = choreographedMultiActions.getRepeatTimes() - 2;
        }
        healthNumberPicker.setValue(this.q);
        healthNumberPicker.setSelectionDivider(ContextCompat.getDrawable(this.f3011a, R.drawable._2131430934_res_0x7f0b0e16));
        healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.29
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public void onValuePicked(int i2, int i3) {
                LogUtil.a("Suggestion_EditDialogBuilder", "repeat times oldValue:", Integer.valueOf(i2), " newValue:", Integer.valueOf(i3));
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3011a);
        builder.czg_(healthNumberPicker);
        builder.d(R.string._2130841510_res_0x7f020fa6);
        builder.cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditDialogBuilder.this.q = healthNumberPicker.getValue();
                LogUtil.a("Suggestion_EditDialogBuilder", "repeat times selected:", Integer.valueOf(EditDialogBuilder.this.q));
                choreographedMultiActions.setRepeatTimes(EditDialogBuilder.this.q + 2);
                EditDialogBuilder editDialogBuilder = EditDialogBuilder.this;
                editDialogBuilder.b(editDialogBuilder.t);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.t = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CustomViewDialog customViewDialog) {
        OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }
}
