package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodPressureActivity;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import defpackage.eeu;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qhm;
import defpackage.qkg;
import defpackage.rrf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodPressureListRecordProvider extends MinorProvider<qhm> {
    private boolean d;
    private List<Drawable> b = new ArrayList(5);
    private final List<qkg> e = new ArrayList();

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        Context context = BaseApplication.getContext();
        rrf.c(context);
        sectionBean.a(e(context));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.d;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qhm qhmVar) {
        a(context, hashMap, qhmVar);
    }

    private void a(Context context, HashMap<String, Object> hashMap, qhm qhmVar) {
        ArrayList arrayList = new ArrayList(5);
        new ArrayList(5);
        ArrayList arrayList2 = new ArrayList(5);
        ArrayList arrayList3 = new ArrayList(5);
        ArrayList arrayList4 = new ArrayList(5);
        ArrayList arrayList5 = new ArrayList(5);
        for (qkg qkgVar : new ArrayList(this.e)) {
            if (qkgVar == null) {
                LogUtil.b("BloodPressureListRecordProvider", "healthData is null");
                return;
            }
            int o = (int) qkgVar.o();
            int m = (int) qkgVar.m();
            arrayList.add(nsf.a(R.plurals._2130903433_res_0x7f030189, m, UnitUtil.e(o, 1, 0), UnitUtil.e(m, 1, 0)));
            arrayList4.add(DateUtils.formatDateTime(context, qkgVar.h(), 137));
            arrayList5.add(Integer.valueOf(LanguageUtil.bc(context) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202));
            arrayList2.add(eeu.b(o, m));
            arrayList3.add(Integer.valueOf(eeu.d(eeu.c(o, m))));
        }
        hashMap.put("LEFT_TOP_VALUE", arrayList);
        hashMap.put("LEFT_TOP_STATE", arrayList2);
        hashMap.put("LEFT_TOP_STATE_COLOR", arrayList3);
        hashMap.put("RIGHT_TIME", arrayList4);
        hashMap.put("RIGHT_ICON", arrayList5);
        hashMap.put("LEFT_IMAGE", new ArrayList(this.b));
        b(context, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HashMap<Integer, Integer> hashMap, List<Drawable> list, qkg qkgVar) {
        if (hashMap == null || qkgVar == null || list == null) {
            LogUtil.a("BloodPressureListRecordProvider", "initIcon null");
            return;
        }
        Context context = BaseApplication.getContext();
        if ("-1".equals(qkgVar.j())) {
            list.add(ContextCompat.getDrawable(context, R.drawable._2131428392_res_0x7f0b0428));
            return;
        }
        int a2 = qkgVar.a();
        if (hashMap.containsKey(Integer.valueOf(a2))) {
            list.add(ContextCompat.getDrawable(context, hashMap.get(Integer.valueOf(a2)).intValue()));
        } else {
            list.add(ContextCompat.getDrawable(context, R.drawable._2131428392_res_0x7f0b0428));
        }
    }

    private HashMap<String, Object> e(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("TITLE", context.getString(R$string.IDS_hw_abnormal_bp_record));
        hashMap.put("SHOWMORE", context.getString(R$string.IDS_hw_common_ui_xlistview_footer_hint_normal));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qhm qhmVar) {
        int i;
        ArrayList arrayList = new ArrayList(qhmVar.c());
        synchronized (this.e) {
            this.e.clear();
            if (koq.c(arrayList)) {
                this.d = true;
                this.e.addAll(arrayList.subList(0, Math.min(arrayList.size(), 5)));
            } else {
                this.d = false;
                LogUtil.b("BloodPressureListRecordProvider", "processAbnormalData failed cause abnormalList is empty!");
            }
        }
        int[] iArr = new int[this.e.size()];
        for (i = 0; i < this.e.size(); i++) {
            iArr[i] = this.e.get(i).a();
        }
        BloodPressureUtil.d(iArr, new b(sectionBean, qhmVar));
    }

    private void b(final Context context, HashMap<String, Object> hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new BaseKnitDataProvider.d(AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_EXCEPTION_DATA_2040121.value()) { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureListRecordProvider.3
            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (nsn.a(500)) {
                    return;
                }
                super.onClick(str);
                if (str.equals("SHOWMORE")) {
                    PageModelArgs pageModelArgs = new PageModelArgs(107, "PrivacyDataConstructor", 3, 3);
                    pageModelArgs.putInt("Subpage", 3);
                    BloodPressureListRecordProvider.this.b(pageModelArgs);
                    Intent intent = new Intent(context, (Class<?>) PrivacyDataModelActivity.class);
                    intent.putExtra("extra_page_model_args", pageModelArgs);
                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b("BloodPressureListRecordProvider", " Jump to InputBloodPressureActivity", e.getMessage());
                    }
                }
            }

            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                BloodPressureListRecordProvider.this.b(i, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(PageModelArgs pageModelArgs) {
        qkg b2 = b();
        if (b2 != null) {
            pageModelArgs.putInt("categoryType", 100);
            pageModelArgs.putLong("Time", b2.h());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Context context) {
        if (nsn.a(500)) {
            return;
        }
        qkg d = d(i);
        if (d == null) {
            LogUtil.b("BloodPressureListRecordProvider", "recordList click HealthData is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) InputBloodPressureActivity.class);
        intent.putExtra(MediaManager.ROPE_MEDIA_HIGH, d.o());
        intent.putExtra("low", d.m());
        intent.putExtra("deletetime", d.h());
        intent.putExtra("bmp", d.n());
        intent.putExtra("clientId", d.a());
        intent.putExtra("isInputData", "-1".equals(d.j()));
        intent.putExtra("isShowInput", false);
        intent.putExtra("measureAbnormal", (int) d.l());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("BloodPressureListRecordProvider", " Jump to InputBloodPressureActivity", e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private qkg b() {
        if (this.mData == 0) {
            LogUtil.a("BloodPressureListRecordProvider", "mData is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(((qhm) this.mData).c());
        if (arrayList.size() >= 5) {
            return (qkg) arrayList.get(4);
        }
        int size = arrayList.size() - 1;
        if (koq.b(arrayList, size)) {
            LogUtil.b("BloodPressureListRecordProvider", "getLastAbnormalData out of bounds");
            return null;
        }
        return (qkg) arrayList.get(size);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private qkg d(int i) {
        if (this.mData == 0) {
            LogUtil.a("BloodPressureListRecordProvider", "mData is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(((qhm) this.mData).c());
        if (koq.b(arrayList, i)) {
            LogUtil.b("BloodPressureListRecordProvider", "recordList click out of bounds");
            return null;
        }
        return (qkg) arrayList.get(i);
    }

    static class b implements BloodPressureUtil.IIconCallBack {
        private WeakReference<BloodPressureListRecordProvider> b;
        private qhm c;
        private SectionBean e;

        private b(BloodPressureListRecordProvider bloodPressureListRecordProvider, SectionBean sectionBean, qhm qhmVar) {
            this.b = new WeakReference<>(bloodPressureListRecordProvider);
            this.e = sectionBean;
            this.c = qhmVar;
        }

        @Override // com.huawei.ui.main.stories.health.util.BloodPressureUtil.IIconCallBack
        public void onReceive(HashMap<Integer, Integer> hashMap) {
            BloodPressureListRecordProvider bloodPressureListRecordProvider = this.b.get();
            if (bloodPressureListRecordProvider == null || this.e == null || this.c == null) {
                return;
            }
            synchronized (bloodPressureListRecordProvider.b) {
                bloodPressureListRecordProvider.b.clear();
                Iterator it = bloodPressureListRecordProvider.e.iterator();
                while (it.hasNext()) {
                    bloodPressureListRecordProvider.c(hashMap, (List<Drawable>) bloodPressureListRecordProvider.b, (qkg) it.next());
                }
            }
            this.e.e(this.c);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "BloodPressureListRecordProvider";
    }
}
