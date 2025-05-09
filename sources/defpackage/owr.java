package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.timepicker.HealthTimePicker;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class owr {

    /* renamed from: a, reason: collision with root package name */
    private GroupBtnSelectedAdapter f15992a;
    private String b;
    private Context c;
    private HealthTimePicker e;
    private boolean[] d = new boolean[7];
    private UiCallback i = new UiCallback<Map<String, String>>() { // from class: owr.2
        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_RemindSettingDialogUtil", "mSwitchUiCallback onFailure");
            owr.this.e();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<String, String> map) {
            LogUtil.a("Track_RemindSettingDialogUtil", "mSwitchUiCallback onSuccess");
            if (map == null) {
                LogUtil.h("Track_RemindSettingDialogUtil", "onSuccess data == null ");
                return;
            }
            owr.this.b = map.get("sport_remind_switch_status");
            LogUtil.a("Track_RemindSettingDialogUtil", "mSwitchStatus ", owr.this.b);
            owr.this.e();
        }
    };
    private Handler j = new Handler() { // from class: owr.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("Track_RemindSettingDialogUtil", "message is null.");
                return;
            }
            super.handleMessage(message);
            if (message.what != 200) {
                return;
            }
            owr.this.d();
        }
    };

    public void e(Context context) {
        LogUtil.a("Track_RemindSettingDialogUtil", "initReminderDialog Enter");
        this.c = context;
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "is_reminder_dialog_shown");
        LogUtil.a("Track_RemindSettingDialogUtil", "initReminderDialog isFirstInit ", b);
        if (b == null || "".equals(b) || Boolean.toString(Boolean.TRUE.booleanValue()).equals(b)) {
            osl.e((UiCallback<Map<String, String>>) this.i);
            SharedPreferenceManager.e(context, Integer.toString(20002), "is_reminder_dialog_shown", Boolean.toString(Boolean.FALSE.booleanValue()), (StorageParams) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("Track_RemindSettingDialogUtil", "initRemindPeriodArrayData ", this.b);
        if (!TextUtils.isEmpty(this.b)) {
            return;
        }
        int i = 0;
        while (true) {
            boolean[] zArr = this.d;
            if (i < zArr.length) {
                zArr[i] = i != 5;
                i++;
            } else {
                this.j.sendEmptyMessage(200);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("Track_RemindSettingDialogUtil", "showSportReminderSettingDialog enter ");
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.dialog_reminder_setting_view, (ViewGroup) null);
        this.e = (HealthTimePicker) inflate.findViewById(R.id.sport_remind_edit_time_pick);
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 18);
        calendar.set(12, 30);
        this.e.setTime(calendar.get(11), calendar.get(12));
        diZ_(inflate, new String[]{this.c.getString(R.string._2130841437_res_0x7f020f5d), this.c.getString(R.string._2130841539_res_0x7f020fc3), this.c.getString(R.string._2130841558_res_0x7f020fd6), this.c.getString(R.string._2130841538_res_0x7f020fc2), this.c.getString(R.string._2130841414_res_0x7f020f46), this.c.getString(R.string._2130841468_res_0x7f020f7c), this.c.getString(R.string._2130841537_res_0x7f020fc1)});
        this.f15992a.c(new GroupBtnSelectedAdapter.OnItemClickListener() { // from class: owr.1
            @Override // com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter.OnItemClickListener
            public void onItemClick(View view, HealthButton healthButton, int i) {
                boolean[] zArr = (boolean[]) owr.this.d.clone();
                zArr[i] = !zArr[i];
                for (int i2 = 0; i2 < 7; i2++) {
                    boolean z = zArr[i2];
                }
                owr.this.d[i] = !owr.this.d[i];
                owr.this.f15992a.cwF_(view, healthButton, i);
            }
        });
        diY_(inflate);
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 2);
        ixx.d().d(this.c, AnalyticsValue.REMIND_DIALOG_SHOW_COUNT.value(), hashMap, 0);
    }

    private void diY_(View view) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.c);
        builder.czg_(view).cze_(R.string._2130841772_res_0x7f0210ac, new View.OnClickListener() { // from class: owr.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R.string._2130844746_res_0x7f021c4a, new View.OnClickListener() { // from class: owr.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ash.a("is_reminder_dialog_click", String.valueOf(true));
                HashMap hashMap = new HashMap(2);
                hashMap.put("click", 1);
                hashMap.put("type", 2);
                ixx.d().d(owr.this.c, AnalyticsValue.REMIND_DIALOG_SELECT_BUTTON_DO_NOT_SET.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        CustomViewDialog e = builder.e();
        e.show();
        e.setCanceledOnTouchOutside(false);
        d(builder, e);
    }

    private void diZ_(View view, String[] strArr) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.period_reminder_layout);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.c, 4) { // from class: owr.8
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        healthRecycleView.setLayoutManager(gridLayoutManager);
        GroupBtnSelectedAdapter groupBtnSelectedAdapter = new GroupBtnSelectedAdapter(this.c, strArr, this.d);
        this.f15992a = groupBtnSelectedAdapter;
        groupBtnSelectedAdapter.cwI_(this.c.getResources().getDrawable(R.drawable._2131431983_res_0x7f0b122f), this.c.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.f15992a.cwJ_(this.c.getResources().getDrawable(R.drawable._2131431982_res_0x7f0b122e), this.c.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
        healthRecycleView.setAdapter(this.f15992a);
        healthRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: owr.9
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view2, RecyclerView recyclerView, RecyclerView.State state) {
                GridLayoutManager gridLayoutManager2 = gridLayoutManager;
                if (gridLayoutManager2 == null || gridLayoutManager2.getSpanCount() <= 1) {
                    return;
                }
                int spanCount = gridLayoutManager.getSpanCount();
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view2);
                int dimensionPixelSize = owr.this.c.getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
                int dimensionPixelSize2 = owr.this.c.getResources().getDimensionPixelSize(R.dimen._2131362480_res_0x7f0a02b0);
                if (childAdapterPosition < spanCount) {
                    rect.bottom = dimensionPixelSize2;
                } else {
                    rect.top = dimensionPixelSize2;
                }
                int i = ((spanCount - 1) * dimensionPixelSize) / spanCount;
                int i2 = (childAdapterPosition % spanCount) * (dimensionPixelSize - i);
                rect.left = i2;
                rect.right = i - i2;
            }
        });
    }

    private void d(CustomViewDialog.Builder builder, final CustomViewDialog customViewDialog) {
        builder.b().setOnClickListener(new View.OnClickListener() { // from class: owr.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = true;
                ash.a("is_reminder_dialog_click", String.valueOf(true));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < owr.this.d.length; i++) {
                    if (owr.this.d[i]) {
                        sb.append((i + 1) + "_");
                        z = false;
                    }
                }
                if (z) {
                    nrh.b(owr.this.c, R.string._2130844838_res_0x7f021ca6);
                } else {
                    owr.this.d(sb, customViewDialog);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(StringBuilder sb, CustomViewDialog customViewDialog) {
        String sb2 = sb.toString();
        String substring = sb2.substring(0, sb2.length() - 1);
        SharedPreferenceManager.e(this.c, Integer.toString(20002), "sport_remind_reminder_period", substring, (StorageParams) null);
        String num = Integer.toString((this.e.getHour() * 3600) + (this.e.getMinute() * 60));
        SharedPreferenceManager.e(this.c, Integer.toString(20002), "sport_remind_reminder_time", num, (StorageParams) null);
        SharedPreferenceManager.e(this.c, Integer.toString(20002), "sport_remind_is_opened", "1", (StorageParams) null);
        HashMap hashMap = new HashMap();
        hashMap.put("sport_remind_switch_status", "1");
        hashMap.put("sport_remind_period", substring);
        hashMap.put("sport_remind_time", num);
        osl.e(hashMap);
        customViewDialog.dismiss();
        osl.a(this.c);
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("click", 1);
        hashMap2.put("type", 2);
        ixx.d().d(this.c, AnalyticsValue.REMIND_DIALOG_SELECT_BUTTON_DONE.value(), hashMap2, 0);
        HashMap hashMap3 = new HashMap(4);
        hashMap3.put("click", 1);
        hashMap3.put("type", 1);
        hashMap3.put("time", num);
        hashMap3.put(TypedValues.CycleType.S_WAVE_PERIOD, substring);
        ixx.d().d(this.c, AnalyticsValue.REMIND_SPORT_SELECT_TIME_AND_PERIOD.value(), hashMap3, 0);
    }
}
