package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.calendarview.MarkDateTrigger;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveDataMarkerTrigger;
import com.huawei.ui.main.stories.fitness.activity.active.writehelper.ThreeCircleDataManager;
import defpackage.efa;
import defpackage.jdl;
import defpackage.nsn;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ActiveDataMarkerTrigger implements MarkDateTrigger {
    public static final Parcelable.Creator<ActiveDataMarkerTrigger> CREATOR = new Parcelable.Creator<ActiveDataMarkerTrigger>() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveDataMarkerTrigger.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dpI_, reason: merged with bridge method [inline-methods] */
        public ActiveDataMarkerTrigger createFromParcel(Parcel parcel) {
            return new ActiveDataMarkerTrigger();
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActiveDataMarkerTrigger[] newArray(int i) {
            return new ActiveDataMarkerTrigger[i];
        }
    };
    private static final float KCAL_TO_CAL = 1000.0f;
    private static final String TAG = "ActiveDataMarkerTrigger";
    private Context mContext = BaseApplication.getContext();

    private int setGoal(int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        return i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // com.huawei.ui.commonui.calendarview.MarkDateTrigger
    public void retrieveMarkDate(final long j, long j2, final UiCallback<Map<String, HealthCalendar>> uiCallback) {
        LogUtil.d(TAG, "retrieveMarkDate, startTime: ", DateFormatUtil.a(j, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), "endTime: ", DateFormatUtil.a(j2, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
        ThreeCircleDataManager.a().a(j, j2, new ResponseCallback() { // from class: pht
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ActiveDataMarkerTrigger.this.m801x7e66ef6b(j, uiCallback, i, (List) obj);
            }
        });
    }

    /* renamed from: lambda$retrieveMarkDate$0$com-huawei-ui-main-stories-fitness-activity-active-provider-ActiveDataMarkerTrigger, reason: not valid java name */
    public /* synthetic */ void m801x7e66ef6b(long j, UiCallback uiCallback, int i, List list) {
        HashMap<String, HealthCalendar> hashMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(5, -1);
        long b = Utils.o() ? ThreeCircleDataManager.a().b() : 1530374400000L;
        int i2 = 0;
        while (true) {
            if (i2 >= jdl.o(j)) {
                break;
            }
            calendar.add(5, 1);
            HealthCalendar healthCalendar = new HealthCalendar();
            healthCalendar.transformFromCalendar(calendar);
            if (hashMap.get(healthCalendar.toString()) == null) {
                setMarkerData(null, hashMap, healthCalendar, calendar.getTimeInMillis() < b);
            }
            i2++;
        }
        if (list == null) {
            LogUtil.c(TAG, "data is empty");
            uiCallback.onSuccess(hashMap);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HiHealthData hiHealthData = (HiHealthData) it.next();
            HealthCalendar healthCalendar2 = new HealthCalendar();
            healthCalendar2.setTime(new Date(hiHealthData.getStartTime()));
            setMarkerData(hiHealthData, hashMap, healthCalendar2, hiHealthData.getStartTime() < b);
        }
        uiCallback.onSuccess(hashMap);
        LogUtil.d(TAG, "retrieveMarkDate is End, startTime: , ", DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
    }

    private static boolean isCalorieJoin(HiHealthData hiHealthData) {
        return hiHealthData.getInt("calorieIsRingNew") == 1;
    }

    private void initThreeCircleView(View view) {
        int c = nsn.c(com.huawei.haf.application.BaseApplication.e(), 30.0f);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(c, c));
    }

    private void setMarkerData(HiHealthData hiHealthData, HashMap<String, HealthCalendar> hashMap, HealthCalendar healthCalendar, boolean z) {
        ThreeCircleView threeCircleView = new ThreeCircleView(BaseApplication.getContext(), 1);
        initThreeCircleView(threeCircleView);
        if (hiHealthData != null && !z) {
            efa.b(efa.d(isCalorieJoin(hiHealthData)), threeCircleView);
            int i = hiHealthData.getInt("durationGoalValue");
            int i2 = hiHealthData.getInt("durationUserValue");
            int i3 = hiHealthData.getInt("activeGoalValue");
            int i4 = hiHealthData.getInt("activeUserValue");
            int i5 = hiHealthData.getInt("stepGoalValue");
            int i6 = hiHealthData.getInt("stepUserValue");
            int i7 = hiHealthData.getInt("calorieGoalValue");
            int i8 = hiHealthData.getInt("sport_calorie_sum");
            if (isCalorieJoin(hiHealthData)) {
                threeCircleView.c("firstCircle", getKCalorie(i8), setGoal(getKCalorie(i7), getKCalorie(i8)));
            } else {
                threeCircleView.c("firstCircle", i6, setGoal(i5, i6));
            }
            threeCircleView.c("secondCircle", i2, setGoal(i, i2));
            threeCircleView.c("thirdCircle", i4, setGoal(i3, i4));
        } else {
            efa.b(efa.f11993a, threeCircleView);
        }
        HealthMark healthMark = new HealthMark(HealthMark.MarkType.VIEW);
        healthMark.cxA_(threeCircleView);
        healthMark.d(nsn.c(this.mContext, 2.0f));
        healthCalendar.addMark(healthMark);
        hashMap.put(healthCalendar.toString(), healthCalendar);
    }

    private int getKCalorie(int i) {
        return Math.round(i / 1000.0f);
    }
}
