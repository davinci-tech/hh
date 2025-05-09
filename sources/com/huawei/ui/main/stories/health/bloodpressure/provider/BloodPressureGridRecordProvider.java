package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import defpackage.eeu;
import defpackage.koq;
import defpackage.nsn;
import defpackage.qhm;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodPressureGridRecordProvider extends MinorProvider<qhm> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qhm qhmVar) {
        hashMap.put("TITLE", context.getString(R$string.IDS_hw_abnormal_bp_record));
        List<String> e = e();
        int size = e.size();
        List<Integer> b = b(size);
        List<SpannableString> b2 = b(qhmVar, size);
        hashMap.put("TOP_TEXT", e);
        hashMap.put("TOP_TEXT_COLOR", b);
        hashMap.put("BOTTOM_UNIT", b2);
        e(context, qhmVar, hashMap);
    }

    private List<Integer> b(int i) {
        Context e = BaseApplication.e();
        ArrayList<Integer> arrayList = new ArrayList<Integer>(e) { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureGridRecordProvider.1
            private static final long serialVersionUID = 3811114498591256154L;
            final /* synthetic */ Context e;

            {
                this.e = e;
                add(Integer.valueOf(ContextCompat.getColor(e, R.color._2131296530_res_0x7f090112)));
                add(Integer.valueOf(ContextCompat.getColor(e, R.color._2131296523_res_0x7f09010b)));
                add(Integer.valueOf(ContextCompat.getColor(e, R.color._2131299241_res_0x7f090ba9)));
            }
        };
        if (i == 4) {
            arrayList.add(0, Integer.valueOf(ContextCompat.getColor(e, R.color._2131296519_res_0x7f090107)));
        } else if (i == 5) {
            arrayList.add(0, Integer.valueOf(ContextCompat.getColor(e, R.color._2131296528_res_0x7f090110)));
            arrayList.add(0, Integer.valueOf(ContextCompat.getColor(e, R.color._2131296519_res_0x7f090107)));
        } else if (i == 6) {
            arrayList.add(0, Integer.valueOf(ContextCompat.getColor(e, R.color._2131296526_res_0x7f09010e)));
            arrayList.add(0, Integer.valueOf(ContextCompat.getColor(e, R.color._2131296528_res_0x7f090110)));
            arrayList.add(0, Integer.valueOf(ContextCompat.getColor(e, R.color._2131296519_res_0x7f090107)));
        } else {
            LogUtil.b("BloodPressureGridRecordProvider", "get size error");
        }
        return arrayList;
    }

    private void e(final Context context, final qhm qhmVar, HashMap<String, Object> hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new BaseKnitDataProvider.d(AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_EXCEPTION_DATA_2040121.value()) { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureGridRecordProvider.5
            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (nsn.a(500)) {
                    return;
                }
                super.onClick(i);
                PageModelArgs pageModelArgs = new PageModelArgs(107, "PrivacyDataConstructor", 3, 3);
                pageModelArgs.putInt("Subpage", 3);
                pageModelArgs.putInt("categoryType", BloodPressureGridRecordProvider.this.a(i));
                pageModelArgs.putLong("startTime", qhmVar.g());
                pageModelArgs.putLong("endTime", qhmVar.f());
                Intent intent = new Intent(context, (Class<?>) PrivacyDataModelActivity.class);
                intent.putExtra("extra_page_model_args", pageModelArgs);
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("BloodPressureGridRecordProvider", "file not found");
                }
            }
        });
    }

    private List<SpannableString> b(qhm qhmVar, int i) {
        int e = qhmVar.e(5);
        int e2 = qhmVar.e(4);
        int e3 = qhmVar.e(3);
        int e4 = qhmVar.e(2);
        int e5 = qhmVar.e(0);
        Context e6 = BaseApplication.e();
        ArrayList arrayList = new ArrayList(i);
        arrayList.add(dDJ_(e6, e4));
        arrayList.add(dDJ_(e6, e5));
        arrayList.add(dDJ_(e6, e + e2 + e3 + e4 + e5));
        if (i == 4) {
            arrayList.add(0, dDJ_(e6, e3));
        } else if (i == 5) {
            arrayList.add(0, dDJ_(e6, e3));
            arrayList.add(0, dDJ_(e6, e2));
        } else if (i == 6) {
            arrayList.add(0, dDJ_(e6, e3));
            arrayList.add(0, dDJ_(e6, e2));
            arrayList.add(0, dDJ_(e6, e));
        } else {
            LogUtil.a("BloodPressureGridRecordProvider", "getAbnormalTimes, get size error");
        }
        return arrayList;
    }

    private SpannableString dDJ_(Context context, int i) {
        String e = UnitUtil.e(i, 1, 0);
        SpannableString spannableString = new SpannableString(context.getResources().getQuantityString(R.plurals._2130903330_res_0x7f030122, i, Integer.valueOf(i)));
        spannableString.setSpan(new AbsoluteSizeSpan(16, true), 0, e.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), e.length(), spannableString.length(), 33);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i) {
        int c = eeu.c();
        if (c == 6) {
            if (i == 0) {
                return 5;
            }
            if (i == 1) {
                return 4;
            }
            if (i == 2) {
                return 3;
            }
            if (i == 3) {
                return 2;
            }
            return i == 4 ? 0 : 100;
        }
        if (c != 5) {
            if (i == 0) {
                return 3;
            }
            if (i == 1) {
                return 2;
            }
            return i == 2 ? 0 : 100;
        }
        if (i == 0) {
            return 4;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 2;
        }
        return i == 3 ? 0 : 100;
    }

    public List<String> e() {
        ArrayList arrayList = new ArrayList(Arrays.asList(eeu.e()));
        if (koq.b(arrayList)) {
            LogUtil.h("BloodPressureGridRecordProvider", "getAbnormalBPGradeDesList, get strings fail");
            return new ArrayList();
        }
        if (arrayList.size() < 2) {
            LogUtil.h("BloodPressureGridRecordProvider", "getAbnormalBPGradeDesList, strings length error");
            return new ArrayList();
        }
        arrayList.remove(1);
        Collections.reverse(arrayList);
        arrayList.add(BaseApplication.e().getString(R$string.IDS_bloodpressure_all_data));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qhm qhmVar) {
        BloodPressureUtil.c(sectionBean, qhmVar);
    }
}
