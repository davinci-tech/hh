package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepSeriesCourseTopic;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepSeriesCourseBean;
import defpackage.jdw;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public abstract class BaseSleepSeriesCourseProvider extends BaseKnitDataProvider<List<SleepSeriesCourseTopic>> implements SleepSeriesCourseTopic.RequestListener {
    private static final String PAGE_TYPE = "pageType";
    private static final String TAG = "BaseSleepSeriesCourseProvider";
    private SectionBean mSectionBean;
    private SleepSeriesCourseTopic mSleepSeriesCourseTopic;
    private final List<SleepSeriesCourseTopic> mSleepSeriesCourseTopicList = new ArrayList();
    private String mSubHeaderTitle;

    protected abstract int getPageType();

    protected abstract int getPositionId();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (List<SleepSeriesCourseTopic>) obj);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepSeriesCourseTopic.RequestListener
    public void onRequestDone(SleepSeriesCourseTopic sleepSeriesCourseTopic) {
        SectionBean sectionBean = this.mSectionBean;
        if (sectionBean != null) {
            sectionBean.e(this.mSleepSeriesCourseTopicList);
        }
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, List<SleepSeriesCourseTopic> list) {
        setData(context, hashMap, list);
    }

    private void getSubHeaderTitle(SectionBean sectionBean) {
        ResourceBriefInfo m;
        ResourceContentBase content;
        JSONObject jSONObject;
        if (sectionBean == null || (m = sectionBean.m()) == null || (content = m.getContent()) == null) {
            return;
        }
        try {
            jSONObject = new JSONObject(content.getContent());
        } catch (JSONException e) {
            LogUtil.b(TAG, "getSubHeaderTitle jsonObject exception, cause ", e.getCause(), ", msg: ", e.getMessage());
            jSONObject = null;
        }
        if (jSONObject == null) {
            LogUtil.b(TAG, "getSubHeaderTitle jsonObject exception, json object is null");
            return;
        }
        String optString = jSONObject.optString("theme");
        if (TextUtils.isEmpty(optString)) {
            LogUtil.b(TAG, "getSubHeaderTitle jsonObject exception, find no theme = ", optString);
        } else {
            this.mSubHeaderTitle = optString;
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        getSubHeaderTitle(sectionBean);
        this.mSectionBean = sectionBean;
        if (this.mSleepSeriesCourseTopic == null) {
            SleepSeriesCourseTopic sleepSeriesCourseTopic = new SleepSeriesCourseTopic(getPositionId());
            this.mSleepSeriesCourseTopic = sleepSeriesCourseTopic;
            this.mSleepSeriesCourseTopicList.add(sleepSeriesCourseTopic);
        }
        this.mSleepSeriesCourseTopic.e(this);
    }

    private void setData(Context context, HashMap<String, Object> hashMap, List<SleepSeriesCourseTopic> list) {
        if (koq.b(list)) {
            return;
        }
        hashMap.put("PAGE_TYPE", Integer.valueOf(getPageType()));
        hashMap.put("TITLE", !TextUtils.isEmpty(this.mSubHeaderTitle) ? this.mSubHeaderTitle : context.getResources().getString(R$string.series_purchase_button_name));
        int min = Math.min(list.size(), 6);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < min; i++) {
            arrayList.add(Integer.valueOf(R.drawable.ic_popular_courses_background));
        }
        hashMap.put("BACKGROUND", arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < min; i2++) {
            arrayList2.add(list.get(i2).c());
        }
        hashMap.put("SERIES_COURSE_TITLE", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < min; i3++) {
            arrayList3.add(list.get(i3).b());
        }
        hashMap.put("SERIES_COURSE_SUBTITLE", arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (int i4 = 0; i4 < min; i4++) {
            addItemToMap(list.get(i4), arrayList4);
        }
        hashMap.put("RECYCLERVIEW", arrayList4);
        ArrayList arrayList5 = new ArrayList();
        for (int i5 = 0; i5 < min; i5++) {
            arrayList5.add(context.getResources().getString(com.huawei.ui.commonui.R$string.IDS_hw_common_ui_xlistview_footer_hint_normal));
        }
        hashMap.put("SHOWMORE", arrayList5);
        setClickListener(context, list, hashMap);
        LogUtil.a(TAG, "viewMap", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoMoreDetail(Context context) {
        Intent intent = new Intent(context, (Class<?>) SleepPopularCoursesActivity.class);
        intent.putExtra("pageType", getPageType());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "setClickListener ActivityNotFoundException.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoSingleDetail(Context context, SleepSeriesCourseBean sleepSeriesCourseBean) {
        String linkValue = sleepSeriesCourseBean.getLinkValue();
        if (linkValue == null) {
            LogUtil.b(TAG, "url is null");
            return;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(linkValue));
        intent.setPackage(context.getPackageName());
        intent.setFlags(268435456);
        jdw.bGh_(intent, context);
    }

    private void setClickListener(final Context context, final List<SleepSeriesCourseTopic> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.BaseSleepSeriesCourseProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("SUBTITILE_CLICK_EVENT".equals(str)) {
                    BaseSleepSeriesCourseProvider.this.gotoMoreDetail(context);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
                if (koq.b(list, i)) {
                    return;
                }
                SleepSeriesCourseTopic sleepSeriesCourseTopic = (SleepSeriesCourseTopic) list.get(i);
                if (koq.b(sleepSeriesCourseTopic.e(), i2)) {
                    return;
                }
                BaseSleepSeriesCourseProvider.this.gotoSingleDetail(context, sleepSeriesCourseTopic.e().get(i2));
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                if (koq.b(list, i)) {
                    return;
                }
                BaseSleepSeriesCourseProvider.this.gotoMoreDetail(context);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void addItemToMap(SleepSeriesCourseTopic sleepSeriesCourseTopic, List<Map<String, Object>> list) {
        Map<String, Object> hashMap = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int i = 0;
        for (SleepSeriesCourseBean sleepSeriesCourseBean : sleepSeriesCourseTopic.e()) {
            if (i >= 2) {
                break;
            }
            arrayList.add(sleepSeriesCourseBean.getPictureUrl());
            arrayList2.add(sleepSeriesCourseBean.getTheme());
            arrayList3.add(sleepSeriesCourseBean.getDesc());
            arrayList4.add("");
            i++;
        }
        hashMap.put("IMAGE", arrayList);
        hashMap.put("NAME", arrayList2);
        hashMap.put("DIFFICULTY", arrayList3);
        hashMap.put("TIME", arrayList4);
        list.add(hashMap);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return TAG;
    }
}
