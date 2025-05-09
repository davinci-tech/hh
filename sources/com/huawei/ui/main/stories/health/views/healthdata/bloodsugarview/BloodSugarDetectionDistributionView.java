package com.huawei.ui.main.stories.health.views.healthdata.bloodsugarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.views.healthdata.bloodsugarview.BloodSugarDetectionDistributionView;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.view.BaseNoDataView;
import defpackage.koq;
import defpackage.nkz;
import defpackage.nld;
import defpackage.noh;
import defpackage.nsn;
import defpackage.pzy;
import defpackage.qle;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class BloodSugarDetectionDistributionView extends BaseNoDataView {
    private static final float DEFAULT_VALUE = 0.0f;
    private static final int PERCENT_MULTIPLIER = 100;
    private static final double REFERENCE_VALUE = 6.6d;
    private static final float SUB_HEADER_PADDING = 0.0f;
    private static final String TAG = "Main_BloodSugarDetectionDistributionView";
    private LinearLayout mAverageLayout;
    private HealthTextView mAverageType;
    private HealthTextView mBloodSugarAverageStatus;
    private LinearLayout mBloodSugarDetectionDistribution;
    private HealthDivider mBloodSugarHealthDivider;
    private HealthTextView mBloodSugarMax;
    private HealthTextView mBloodSugarMaxValue;
    private HealthTextView mBloodSugarMin;
    private HealthTextView mBloodSugarMinValue;
    private HealthRingChart mBloodSugarPieChartView;
    private ArrayList<Integer> mBloodSugarTimesList;
    private String mBloodSugarType;
    private HealthDivider mBottomDivider;
    private Context mContext;
    private HealthSubHeader mDetectionDistributionTitle;
    private HealthTextView mExportTable;
    private int mHighBloodSugarTimes;
    private int mLowBloodSugarTimes;
    private LinearLayout mMaxMinLayout;
    private ArrayList<pzy> mModelList;
    private int mNormalBloodSugarTimes;
    private Constants.PageType mPageType;
    private HealthTextView mReferenceData;
    private Map<Long, IStorageModel> mResultMap;

    public BloodSugarDetectionDistributionView(Context context) {
        this(context, null);
    }

    public BloodSugarDetectionDistributionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarDetectionDistributionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLowBloodSugarTimes = 0;
        this.mHighBloodSugarTimes = 0;
        this.mNormalBloodSugarTimes = 0;
        this.mBloodSugarTimesList = new ArrayList<>(10);
        this.mModelList = new ArrayList<>(10);
        this.mContext = context;
    }

    @Override // com.huawei.ui.main.stories.template.health.view.BaseNoDataView, com.huawei.ui.main.stories.template.BaseComponent
    public void refreshView(boolean z) {
        super.refreshView(z);
        removeAllViews();
        if (nsn.ag(this.mContext)) {
            LayoutInflater.from(this.mContext).inflate(R.layout.view_blood_sugar_detection_distribution_tahiti, this);
        } else {
            LayoutInflater.from(this.mContext).inflate(R.layout.view_blood_sugar_detection_distribution, this);
        }
        initView();
        initData(this.mBloodSugarType, this.mResultMap);
    }

    static class e implements Comparator<pzy>, Serializable {
        private static final long serialVersionUID = -8204187395565030989L;

        private e() {
        }

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(pzy pzyVar, pzy pzyVar2) {
            return Float.compare(pzyVar2.a(), pzyVar.a());
        }
    }

    private void initView() {
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.hw_hsh_detection_distribution_title);
        this.mDetectionDistributionTitle = healthSubHeader;
        healthSubHeader.setVisibility(0);
        this.mDetectionDistributionTitle.setPaddingStartEnd(0.0f, 0.0f);
        this.mDetectionDistributionTitle.setSubHeaderBackgroundColor(0);
        if (LanguageUtil.ar(this.mContext)) {
            this.mDetectionDistributionTitle.setSubHeaderTitleScaleTextSize(0.9f);
        }
        this.mBloodSugarMinValue = (HealthTextView) findViewById(R.id.tv_blood_sugar_min_value);
        this.mBloodSugarMaxValue = (HealthTextView) findViewById(R.id.tv_blood_sugar_max_value);
        this.mBloodSugarMin = (HealthTextView) findViewById(R.id.tv_blood_sugar_min);
        this.mBloodSugarMax = (HealthTextView) findViewById(R.id.tv_blood_sugar_max);
        this.mBloodSugarPieChartView = (HealthRingChart) findViewById(R.id.hw_blood_sugar_pie_chart);
        this.mBloodSugarDetectionDistribution = (LinearLayout) findViewById(R.id.ll_blood_sugar_detection_distribution);
        HealthDivider healthDivider = (HealthDivider) findViewById(R.id.hd_health_divider);
        this.mBloodSugarHealthDivider = healthDivider;
        healthDivider.setDividerBackground(R.color._2131297331_res_0x7f090433);
        this.mMaxMinLayout = (LinearLayout) findViewById(R.id.max_and_min_layout);
        this.mBottomDivider = (HealthDivider) findViewById(R.id.blood_health_divider);
        this.mExportTable = (HealthTextView) findViewById(R.id.blood_sugar_export_table);
        this.mAverageLayout = (LinearLayout) findViewById(R.id.blood_sugar_average_layout);
        this.mAverageType = (HealthTextView) findViewById(R.id.blood_sugar_average_type_title);
        this.mReferenceData = (HealthTextView) findViewById(R.id.blood_sugar_reference_data);
        this.mBloodSugarAverageStatus = (HealthTextView) findViewById(R.id.blood_sugar_average_state);
        this.mExportTable.setOnClickListener(new View.OnClickListener() { // from class: qte
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarDetectionDistributionView.lambda$initView$0(view);
            }
        });
    }

    public static /* synthetic */ void lambda$initView$0(View view) {
        qle.d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initData(String str, Map<Long, IStorageModel> map) {
        if (str == null || map == null || map.isEmpty()) {
            LogUtil.c(TAG, "bloodSugarType ==null or resultMap isEmpty");
            setBloodSugarDetectionDistributionVisibility(false);
            return;
        }
        setBloodSugarDetectionDistributionVisibility(true);
        showHeadTitle(str);
        parsingData(map);
        LogUtil.c(TAG, "initData type is  ", this.mPageType);
        if ("BLOOD_SUGAR_CONTINUE".equals(str)) {
            this.mBloodSugarHealthDivider.setVisibility(8);
            this.mMaxMinLayout.setVisibility(8);
            this.mAverageLayout.setVisibility(0);
            this.mBottomDivider.setVisibility(0);
            showBloodSugarAverageData();
        } else {
            this.mMaxMinLayout.setVisibility(0);
            if (nsn.ag(this.mContext)) {
                this.mBloodSugarHealthDivider.setVisibility(8);
            } else {
                this.mBloodSugarHealthDivider.setVisibility(0);
            }
            this.mAverageLayout.setVisibility(8);
            this.mBottomDivider.setVisibility(8);
            showBloodSugarMaxValueAndMinValue();
        }
        showExportTable();
        showBloodSugarPieChart(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0066, code lost:
    
        if (r5.equals("BLOOD_SUGAR_BEFORE_SLEEP_BlOOD_GLUCOSE") == false) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void showHeadTitle(java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.views.healthdata.bloodsugarview.BloodSugarDetectionDistributionView.showHeadTitle(java.lang.String):void");
    }

    private void parsingData(Map<Long, IStorageModel> map) {
        this.mLowBloodSugarTimes = 0;
        this.mHighBloodSugarTimes = 0;
        this.mNormalBloodSugarTimes = 0;
        if (koq.c(this.mModelList)) {
            this.mModelList.clear();
        }
        Iterator<Map.Entry<Long, IStorageModel>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            IStorageModel value = it.next().getValue();
            if (!(value instanceof pzy)) {
                break;
            }
            this.mModelList.add((pzy) value);
        }
        Iterator<pzy> it2 = this.mModelList.iterator();
        while (it2.hasNext()) {
            notifySugarLevelTimes(it2.next());
        }
    }

    private void showBloodSugarPieChart(String str) {
        nld b;
        this.mBloodSugarTimesList.clear();
        this.mBloodSugarTimesList.add(Integer.valueOf(this.mLowBloodSugarTimes));
        this.mBloodSugarTimesList.add(Integer.valueOf(this.mHighBloodSugarTimes));
        this.mBloodSugarTimesList.add(Integer.valueOf(this.mNormalBloodSugarTimes));
        if (this.mBloodSugarPieChartView != null) {
            nld nldVar = new nld();
            LogUtil.c(TAG, "showBloodSugarPieChart type ", str);
            if ("BLOOD_SUGAR_CONTINUE".equals(str)) {
                LogUtil.c(TAG, "showBloodSugarPieChart setShowValues false");
                b = nldVar.c(true).b(false);
            } else {
                b = nldVar.c(true).b(true);
            }
            LogUtil.a(TAG, "showBloodSugarPieChart config setShowValues ", Boolean.valueOf(b.a()));
            HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.mContext, b, getBloodSugarRingChartItems(this.mContext, this.mBloodSugarTimesList));
            healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: qsv
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    return BloodSugarDetectionDistributionView.this.m830x6e9dd95d(nkzVar);
                }
            });
            healthRingChartAdapter.b(new HealthRingChartAdapter.DataFormatter() { // from class: qtc
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    String e2;
                    e2 = UnitUtil.e(nkzVar.e() * 100.0f, 2, 1);
                    return e2;
                }
            });
            this.mBloodSugarPieChartView.setAdapter(healthRingChartAdapter);
            this.mBloodSugarPieChartView.c(this.mContext.getString(R$string.IDS_hw_pressure_ratio));
        }
    }

    /* renamed from: lambda$showBloodSugarPieChart$1$com-huawei-ui-main-stories-health-views-healthdata-bloodsugarview-BloodSugarDetectionDistributionView, reason: not valid java name */
    public /* synthetic */ String m830x6e9dd95d(nkz nkzVar) {
        int i = (int) nkzVar.i();
        return this.mContext.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, i, UnitUtil.e(i, 1, 0));
    }

    private List<nkz> getBloodSugarRingChartItems(Context context, List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_low));
        arrayList.add(context.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_high));
        arrayList.add(context.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_normal));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298938_res_0x7f090a7a)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298934_res_0x7f090a76)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298942_res_0x7f090a7e)));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298939_res_0x7f090a7b)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298935_res_0x7f090a77)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298943_res_0x7f090a7f)));
        int size = list != null ? list.size() : 0;
        ArrayList arrayList4 = new ArrayList(size);
        int i = 0;
        while (i < size) {
            arrayList4.add(new nkz(i < arrayList.size() ? (String) arrayList.get(i) : "", list.get(i).intValue(), i < arrayList3.size() ? ((Integer) arrayList3.get(i)).intValue() : 0, i < arrayList2.size() ? ((Integer) arrayList2.get(i)).intValue() : 0));
            i++;
        }
        return arrayList4;
    }

    private void setBloodSugarDetectionDistributionVisibility(boolean z) {
        LogUtil.a(TAG, "setBloodSugarDetectionDistributionVisibility");
        LinearLayout linearLayout = this.mBloodSugarDetectionDistribution;
        if (linearLayout != null) {
            if (z) {
                linearLayout.setVisibility(0);
                LogUtil.c(TAG, "mBloodSugarDetectionDistribution isVisible");
            } else {
                linearLayout.setVisibility(8);
                LogUtil.c(TAG, "mBloodSugarDetectionDistribution isGone");
            }
        }
    }

    public void setBloodSugarData(String str, Map<Long, IStorageModel> map) {
        this.mBloodSugarType = str;
        this.mResultMap = map;
        initData(str, map);
    }

    private void showBloodSugarMaxValueAndMinValue() {
        ArrayList<pzy> arrayList = this.mModelList;
        if (arrayList != null && this.mBloodSugarMaxValue != null && this.mBloodSugarMinValue != null) {
            if (arrayList.size() > 1) {
                Collections.sort(this.mModelList, new e());
                setDigitalLocalization(this.mBloodSugarMaxValue, this.mModelList.get(0).a());
                HealthTextView healthTextView = this.mBloodSugarMinValue;
                ArrayList<pzy> arrayList2 = this.mModelList;
                setDigitalLocalization(healthTextView, arrayList2.get(arrayList2.size() - 1).a());
            } else {
                setDigitalLocalization(this.mBloodSugarMaxValue, this.mModelList.get(0).a());
                setDigitalLocalization(this.mBloodSugarMinValue, this.mModelList.get(0).a());
            }
        }
        HealthTextView healthTextView2 = this.mBloodSugarMin;
        if (healthTextView2 != null) {
            healthTextView2.setText(R$string.IDS_hw_show_healthdata_bloodsugar_minimum_value);
        }
        HealthTextView healthTextView3 = this.mBloodSugarMax;
        if (healthTextView3 != null) {
            healthTextView3.setText(R$string.IDS_hw_show_healthdata_bloodsugar_maximum_value);
        }
    }

    private void setDigitalLocalization(HealthTextView healthTextView, float f) {
        if (healthTextView == null || koq.b(this.mModelList)) {
            LogUtil.h(TAG, "setDigitalLocalization view or bloodSugarStorageModel is null");
            return;
        }
        double a2 = UnitUtil.a(Math.abs(f), 1);
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, UnitUtil.e(a2), UnitUtil.e(a2, 1, 1));
        LogUtil.c(TAG, "setDigitalLocalization sugarData is ", quantityString);
        healthTextView.setText(UnitUtil.bCR_(this.mContext, "[\\.\\d]", quantityString, R.style.health_blood_min_max_value, R.style.health_blood_card_cursor_unit));
    }

    private void showBloodSugarAverageData() {
        String string;
        if (this.mAverageType == null || this.mReferenceData == null) {
            LogUtil.h(TAG, "showBloodSugarAverageData mAverageType is null");
            return;
        }
        String str = "  " + this.mContext.getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, UnitUtil.e(getAverageData()), noh.e(getAverageData()));
        if (this.mPageType == Constants.PageType.DAY) {
            string = this.mContext.getResources().getString(R$string.IDS_bloodsugar_avg_value_day, str);
        } else if (this.mPageType == Constants.PageType.WEEK) {
            string = this.mContext.getResources().getString(R$string.IDS_bloodsugar_avg_value_week, str);
        } else {
            string = this.mContext.getResources().getString(R$string.IDS_bloodsugar_avg_value_month, str);
        }
        this.mAverageType.setText(string);
        this.mReferenceData.setText(String.format(this.mContext.getResources().getString(R$string.IDS_bloodsugar_reference_range), this.mContext.getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, UnitUtil.e(REFERENCE_VALUE), UnitUtil.e(REFERENCE_VALUE, 1, 1))));
        showBloodSugarStatus(getAverageData());
    }

    private boolean isShowExport() {
        Iterator<pzy> it = this.mModelList.iterator();
        while (it.hasNext()) {
            if (it.next().o() != 2106) {
                return true;
            }
        }
        return false;
    }

    private float getAverageData() {
        int size;
        ArrayList<pzy> arrayList = this.mModelList;
        float f = 0.0f;
        if (arrayList == null || (size = arrayList.size()) <= 0) {
            return 0.0f;
        }
        for (int i = 0; i < size; i++) {
            f += this.mModelList.get(i).a();
        }
        float f2 = f / size;
        LogUtil.c(TAG, "averageData ", Float.valueOf(f2), " totalData ", Float.valueOf(f), " length ", Integer.valueOf(size));
        return f2;
    }

    private void showBloodSugarStatus(float f) {
        if (f < REFERENCE_VALUE) {
            this.mBloodSugarAverageStatus.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131296799_res_0x7f09021f));
            this.mBloodSugarAverageStatus.setText(R$string.IDS_hw_health_show_healthdata_status_normal);
        } else {
            this.mBloodSugarAverageStatus.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131296795_res_0x7f09021b));
            this.mBloodSugarAverageStatus.setText(R$string.IDS_hw_health_show_healthdata_status_high);
        }
    }

    public void showExportTable() {
        LogUtil.c(TAG, "showExportTable type is ", this.mPageType, ", mBloodSugarType ", this.mBloodSugarType);
        if (this.mPageType == Constants.PageType.WEEK && !"BLOOD_SUGAR_CONTINUE".equals(this.mBloodSugarType) && isShowExport()) {
            this.mExportTable.setVisibility(0);
            if (nsn.s()) {
                this.mExportTable.setTextSize(1, 30.0f);
            } else {
                this.mExportTable.setTextSize(2, 16.0f);
            }
            this.mBottomDivider.setVisibility(0);
            this.mExportTable.setOnClickListener(new View.OnClickListener() { // from class: qtd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarDetectionDistributionView.lambda$showExportTable$3(view);
                }
            });
            return;
        }
        this.mExportTable.setVisibility(8);
    }

    public static /* synthetic */ void lambda$showExportTable$3(View view) {
        qle.d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void notifySugarLevelTimes(pzy pzyVar) {
        switch (pzyVar.j()) {
            case 1001:
            case 1002:
                this.mLowBloodSugarTimes++;
                break;
            case 1003:
                this.mNormalBloodSugarTimes++;
                break;
            case 1004:
            case 1005:
            case 1006:
                this.mHighBloodSugarTimes++;
                break;
            default:
                LogUtil.a(TAG, "no support blood type");
                break;
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.view.BaseNoDataView, com.huawei.ui.main.stories.template.BaseComponent
    public void setPageType(Constants.PageType pageType) {
        this.mPageType = pageType;
    }
}
