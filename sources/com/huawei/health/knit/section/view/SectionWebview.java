package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.util.Consumer;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.SleepMonitorAlgorithmApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bzh;
import defpackage.bzi;
import defpackage.ffy;
import defpackage.jdl;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionWebview extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, Object> f2750a;
    private HealthTextView b;
    private Observer c;
    private Context d;
    private LinearLayout e;
    private CustomH5ProWebview f;
    private HealthTextView g;
    private LinearLayout h;
    private HealthTextView i;
    private int j;
    private View l;
    private LinearLayout o;

    public SectionWebview(Context context) {
        this(context, null);
    }

    public SectionWebview(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionWebview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.d = context;
        b();
        return this.l;
    }

    private void b() {
        if (this.l == null) {
            LogUtil.h("SectionWebView", "initView mainView is null, start to inflate");
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.section_webview_layout, (ViewGroup) this, false);
            this.l = inflate;
            this.o = (LinearLayout) inflate.findViewById(R.id.section_webview_ll);
            this.h = (LinearLayout) this.l.findViewById(R.id.webview_title);
            this.g = (HealthTextView) this.l.findViewById(R.id.section_webview_subhead);
            this.f = (CustomH5ProWebview) this.l.findViewById(R.id.section_webview);
            this.e = (LinearLayout) this.l.findViewById(R.id.no_dream_snore_data_layout);
            this.b = (HealthTextView) this.l.findViewById(R.id.no_dream_snore_item_left_text);
            this.i = (HealthTextView) this.l.findViewById(R.id.no_dream_snore_item_right_text);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public List<Bitmap> getShareBitmap() {
        ArrayList arrayList = new ArrayList();
        if (!isSupportShare()) {
            return arrayList;
        }
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.section_webview_share_layout, (ViewGroup) null, false);
        ajP_(inflate);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        inflate.setPadding(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) safeRegionWidth.first).intValue(), 0, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) safeRegionWidth.second).intValue(), 0);
        inflate.measure(View.MeasureSpec.makeMeasureSpec(nsn.n(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(nsn.j(), Integer.MIN_VALUE));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
        int height = inflate.getHeight();
        int width = inflate.getWidth();
        if (height != 0 && width != 0) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            inflate.draw(new Canvas(createBitmap));
            arrayList.add(createBitmap);
        }
        return arrayList;
    }

    private void ajP_(View view) {
        LogUtil.a("SectionWebView", "SectionWebview: ", this);
        long startTime = getStartTime();
        long endTime = getEndTime();
        LogUtil.a("SectionWebView", "startTime: ", Long.valueOf(startTime), ", endTime: ", Long.valueOf(endTime));
        Map<String, Integer> a2 = a(startTime, endTime);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.up_sleep_item_left_text);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.up_sleep_item_right_text);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.down_sleep_item_left_text);
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.down_sleep_item_right_text);
        int intValue = a2.get("DreamFrequency").intValue();
        nsy.cMs_(healthTextView, this.d.getResources().getQuantityString(R.plurals._2130903117_res_0x7f03004d, intValue, Integer.valueOf(intValue)), true);
        int intValue2 = a2.get("DreamDuration").intValue() / 60;
        int intValue3 = a2.get("DreamDuration").intValue() % 60;
        nsy.cMs_(healthTextView2, ffy.d(this.d, R$string.IDS_share_dream_talk_snore_sleep, this.d.getResources().getQuantityString(R.plurals._2130903119_res_0x7f03004f, intValue2, UnitUtil.e(intValue2, 1, 0)), this.d.getResources().getQuantityString(R.plurals._2130903120_res_0x7f030050, intValue3, UnitUtil.e(intValue3, 1, 0))), true);
        int intValue4 = a2.get("SnoreFrequency").intValue();
        nsy.cMs_(healthTextView3, this.d.getResources().getQuantityString(R.plurals._2130903118_res_0x7f03004e, intValue4, Integer.valueOf(intValue4)), true);
        int intValue5 = a2.get("SnoreDuration").intValue() / 60;
        int intValue6 = a2.get("SnoreDuration").intValue() % 60;
        nsy.cMs_(healthTextView4, ffy.d(this.d, R$string.IDS_share_dream_talk_snore_sleep, this.d.getResources().getQuantityString(R.plurals._2130903119_res_0x7f03004f, intValue5, UnitUtil.e(intValue5, 1, 0)), this.d.getResources().getQuantityString(R.plurals._2130903120_res_0x7f030050, intValue6, UnitUtil.e(intValue6, 1, 0))), true);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionWebView", "start to bindViewParams, ", this);
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionWebView", "no need to bind");
            return;
        }
        this.f2750a = hashMap;
        nsy.cMr_(this.g, nru.b(hashMap, "TITLE", ""));
        long startTime = getStartTime();
        long endTime = getEndTime();
        LogUtil.a("SectionWebView", "startTime: ", new Date(startTime), ", endTime: ", new Date(endTime));
        Map<String, Integer> a2 = a(startTime, endTime);
        int intValue = a2.get("DreamFrequency").intValue();
        int intValue2 = a2.get("SnoreFrequency").intValue();
        if (intValue == 0 && intValue2 == 0) {
            LogUtil.a("SectionWebView", "no dreamFrequency and snoreFrequency");
            nsy.cMA_(this.h, 8);
            nsy.cMr_(this.b, nru.b(hashMap, "LEFT_TEXT", ""));
            nsy.cMr_(this.i, nru.b(hashMap, "RIGHT_TEXT", ""));
            nsy.cMA_(this.e, 0);
            nsy.cMA_(this.f, 8);
        } else {
            LogUtil.a("SectionWebView", "start load dream and snore");
            nsy.cMA_(this.f, 0);
            nsy.cMA_(this.e, 8);
            Consumer consumer = (Consumer) nru.c(hashMap, "CALL_BACK", Consumer.class, null);
            if (consumer != null) {
                consumer.accept(this.f);
            }
        }
        e();
    }

    private void e() {
        if (this.c == null) {
            Observer observer = new Observer() { // from class: com.huawei.health.knit.section.view.SectionWebview.4
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    Object obj;
                    if (objArr == null || objArr.length <= 0 || (obj = objArr[0]) == null || !(obj instanceof Integer)) {
                        return;
                    }
                    SectionWebview.this.j = ((Integer) obj).intValue();
                    LogUtil.a("SectionWebView", "webview height is: " + SectionWebview.this.j);
                    HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionWebview.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = SectionWebview.this.f.getLayoutParams();
                            layoutParams.height = (int) Utils.convertDpToPixel(SectionWebview.this.j);
                            SectionWebview.this.f.setLayoutParams(layoutParams);
                        }
                    });
                }
            };
            this.c = observer;
            ObserverManagerUtil.d(observer, "REFRESH_H5_HEIGHT");
        }
    }

    public static Map<String, Integer> a(long j, long j2) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList(((SleepMonitorAlgorithmApi) Services.c("SleepMonitor", SleepMonitorAlgorithmApi.class)).querySleepVoiceInfo(j / 1000, j2 / 1000));
        Collections.sort(arrayList, new bzi());
        LogUtil.a("SectionWebView", "sleepVoiceInfoWrappersList size: " + arrayList.size());
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            bzh.c a2 = ((bzi) arrayList.get(i5)).a();
            int c = a2.c();
            if (1 == c && i < 10) {
                i3 += a2.a();
                i++;
            } else if (2 == c && i2 < 10) {
                i4 += a2.a();
                i2++;
            } else {
                LogUtil.a("SectionWebView", "not dream and snore");
            }
        }
        hashMap.put("SnoreFrequency", Integer.valueOf(i));
        hashMap.put("DreamFrequency", Integer.valueOf(i2));
        hashMap.put("SnoreDuration", Integer.valueOf(i3));
        hashMap.put("DreamDuration", Integer.valueOf(i4));
        return hashMap;
    }

    private long getStartTime() {
        return nru.d(this.f2750a, "SLEEP_START_TIME", jdl.e(jdl.b(System.currentTimeMillis(), -1), 20, 0));
    }

    private long getEndTime() {
        return nru.d(this.f2750a, "SLEEP_END_TIME", jdl.e(System.currentTimeMillis(), 20, 0));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionWebView";
    }
}
