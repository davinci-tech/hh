package defpackage;

import android.widget.RemoteViews;
import com.hihonor.assistant.cardmgrsdk.model.ClickPendingIntent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class glw {
    private RemoteViews b;
    private String d;
    private List<ClickPendingIntent> e;

    public glw(String str) {
        this.d = str;
    }

    public RemoteViews aOL_(WorkoutRecord workoutRecord, String str, String str2) {
        LogUtil.a("YoYoWidgetFitness", "generate called");
        RemoteViews aOK_ = aOK_(workoutRecord);
        this.b = aOK_;
        if (aOK_ == null) {
            LogUtil.b("YoYoWidgetFitness", "generate RemoteViews is null");
            return null;
        }
        this.e = gnn.d(str, str2);
        LogUtil.a("YoYoWidgetFitness", "generate RemoteViews: ", this.d);
        return this.b;
    }

    public static RemoteViews aOK_(WorkoutRecord workoutRecord) {
        LogUtil.a("YoYoWidgetFitness", "updateRemoteViewsContent ");
        RemoteViews remoteViews = new RemoteViews(BaseApplication.e().getPackageName(), R.layout.layout_yoyo_widget);
        remoteViews.setTextViewText(R.id.honor_yoyo_text_time, new SimpleDateFormat("MM/dd HH:mm").format(new Date(workoutRecord.isFitnessRecordFromDevice() ? workoutRecord.startTime() : workoutRecord.acquireExerciseTime())));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_details_data, gnn.d((int) workoutRecord.acquireActualCalorie()));
        int acquireAvgHeartRate = workoutRecord.acquireAvgHeartRate();
        if (acquireAvgHeartRate > 0) {
            remoteViews.setTextViewText(R.id.honor_yoyo_text_heart_data, String.valueOf(acquireAvgHeartRate));
        } else {
            LogUtil.a("YoYoWidgetFitness", "movement heartRate is zero ");
            remoteViews.setViewVisibility(R.id.honor_yoyo_image_heart, 8);
            remoteViews.setViewVisibility(R.id.honor_yoyo_text_heart_data, 8);
            remoteViews.setViewVisibility(R.id.honor_yoyo_text_heart_data_unit, 8);
        }
        remoteViews.setTextViewText(R.id.honor_yoyo_text_date, gnn.e(workoutRecord.getDuration()));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_heat_data, BaseApplication.e().getResources().getQuantityString(R.plurals._2130903498_res_0x7f0301ca, 1, 1));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_average_pace_data, String.valueOf(workoutRecord.acquireDifficulty()));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_details, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_strength_training));
        remoteViews.setImageViewResource(R.id.card_chahua_bg, R.drawable._2131430673_res_0x7f0b0d11);
        remoteViews.setTextViewText(R.id.honor_yoyo_details_data_unit, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_date_title, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_time_spent));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_heat, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_number_times));
        remoteViews.setViewVisibility(R.id.honor_yoyo_text_heat_unit, 8);
        remoteViews.setTextViewText(R.id.honor_yoyo_text_average_pace, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_difficulty));
        return remoteViews;
    }

    public List<ClickPendingIntent> e() {
        return this.e;
    }
}
