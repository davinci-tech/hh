package defpackage;

import android.widget.RemoteViews;
import com.hihonor.assistant.cardmgrsdk.model.ClickPendingIntent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class glu {
    private String b;
    private RemoteViews d;
    private List<ClickPendingIntent> e;

    public glu(String str) {
        this.b = str;
    }

    public RemoteViews aON_(HiTrackMetaData hiTrackMetaData, long j, String str, String str2) {
        LogUtil.a("YoYoWidgetMovement", "generate called: ", Long.valueOf(j));
        RemoteViews aOM_ = aOM_(hiTrackMetaData, j);
        this.d = aOM_;
        if (aOM_ == null) {
            LogUtil.b("YoYoWidgetMovement", "generate RemoteViews is null");
            return null;
        }
        this.e = gnn.d(str, str2);
        LogUtil.a("YoYoWidgetMovement", "generate RemoteViews: ", this.b);
        return this.d;
    }

    public static RemoteViews aOM_(HiTrackMetaData hiTrackMetaData, long j) {
        LogUtil.a("YoYoWidgetMovement", "updateRemoteViewsContent ");
        RemoteViews remoteViews = new RemoteViews(BaseApplication.e().getPackageName(), R.layout.layout_yoyo_widget);
        remoteViews.setTextViewText(R.id.honor_yoyo_text_time, new SimpleDateFormat("MM/dd HH:mm").format(new Date(j)));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_details_data, c(hiTrackMetaData));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_average_pace_data, d(hiTrackMetaData));
        if (hiTrackMetaData.getAvgHeartRate() > 0) {
            remoteViews.setTextViewText(R.id.honor_yoyo_text_heart_data, String.valueOf(hiTrackMetaData.getAvgHeartRate()));
        } else {
            LogUtil.a("YoYoWidgetMovement", "movement heartRate is zero ");
            remoteViews.setViewVisibility(R.id.honor_yoyo_image_heart, 8);
            remoteViews.setViewVisibility(R.id.honor_yoyo_text_heart_data, 8);
            remoteViews.setViewVisibility(R.id.honor_yoyo_text_heart_data_unit, 8);
        }
        remoteViews.setTextViewText(R.id.honor_yoyo_text_date, gnn.e(hiTrackMetaData.getTotalTime()));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_heat_data, gnn.d(hiTrackMetaData.getTotalCalories()));
        if (hiTrackMetaData.getSportType() == 257 || hiTrackMetaData.getSportType() == 281) {
            remoteViews.setTextViewText(R.id.honor_yoyo_text_details, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_single_walk));
            remoteViews.setImageViewResource(R.id.card_chahua_bg, R.drawable._2131430699_res_0x7f0b0d2b);
        } else {
            remoteViews.setTextViewText(R.id.honor_yoyo_text_details, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_single_run));
            remoteViews.setImageViewResource(R.id.card_chahua_bg, R.drawable._2131427750_res_0x7f0b01a6);
        }
        remoteViews.setTextViewText(R.id.honor_yoyo_details_data_unit, BaseApplication.e().getResources().getString(UnitUtil.b() ? R.string._2130841383_res_0x7f020f27 : R.string._2130841382_res_0x7f020f26));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_date_title, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_exercise_time));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_heat, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_hw_phonecounter_widget_kalo_prefix));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_heat_unit, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit));
        remoteViews.setTextViewText(R.id.honor_yoyo_text_average_pace, BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_yoyo_average_pace));
        return remoteViews;
    }

    private static String c(HiTrackMetaData hiTrackMetaData) {
        double totalDistance = hiTrackMetaData.getTotalDistance() / 1000.0d;
        if (UnitUtil.b()) {
            return UnitUtil.e(UnitUtil.e(totalDistance, 3), 1, 2);
        }
        return UnitUtil.e(totalDistance, 1, 2);
    }

    private static String d(HiTrackMetaData hiTrackMetaData) {
        if (UnitUtil.b()) {
            return gnn.c((float) gnn.e(true, 3, hiTrackMetaData.getAvgPace()));
        }
        return gnn.c(hiTrackMetaData.getAvgPace());
    }

    public List<ClickPendingIntent> b() {
        return this.e;
    }
}
