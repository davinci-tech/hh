package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.doublescreen.IndoorTvBaseDisplay;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import health.compact.a.LogAnonymous;
import java.util.Map;

/* loaded from: classes5.dex */
public class law implements SportLifecycle {

    /* renamed from: a, reason: collision with root package name */
    private boolean f14734a;
    private IndoorTvBaseDisplay b;
    private Context c;
    private boolean d;
    private int e;
    private IndoorEquipViewModel f;

    private law() {
        this.e = 0;
        LogUtil.a("Track_IDEQ_IndoorEquipTvDisplayEngine", "constructor");
    }

    public static law b(Context context, boolean z, IndoorEquipViewModel indoorEquipViewModel) {
        law lawVar = c.d;
        lawVar.a(context, z, indoorEquipViewModel);
        return lawVar;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.onPauseSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.onResumeSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.onStartSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.m134x32b3e3a1();
        }
    }

    static class c {
        private static final law d = new law();
    }

    private void a(Context context, boolean z, IndoorEquipViewModel indoorEquipViewModel) {
        this.f14734a = z;
        this.c = context;
        this.f = indoorEquipViewModel;
        if (indoorEquipViewModel != null) {
            if (indoorEquipViewModel.a() != null) {
                LogUtil.a("Track_IDEQ_IndoorEquipTvDisplayEngine", "mViewModel.getSupportDataRange().getMax", Integer.valueOf(this.f.a().getMaxLevel()));
                LogUtil.a("Track_IDEQ_IndoorEquipTvDisplayEngine", "mViewModel.getSupportDataRange().getMin", Integer.valueOf(this.f.a().getMinLevel()));
            }
            this.e = this.f.getSportType();
        }
    }

    public void b(boolean z) {
        this.f14734a = z;
    }

    public void c(boolean z) {
        this.d = z;
    }

    public void d() {
        LogUtil.a("Track_IDEQ_IndoorEquipTvDisplayEngine", "tvShow()");
        Context context = this.c;
        if (context == null || !(context.getSystemService("display") instanceof DisplayManager)) {
            LogUtil.b("Track_IDEQ_IndoorEquipTvDisplayEngine", "DisplayManager instant of getSystemService or context is null", this.c);
            return;
        }
        Display[] displays = ((DisplayManager) this.c.getSystemService("display")).getDisplays();
        if (displays.length <= 1 || nsn.ae(this.c)) {
            return;
        }
        if (this.d) {
            bVi_(displays);
        } else if (this.e == 264) {
            bVk_(displays);
        } else {
            bVj_(displays);
        }
    }

    private void bVk_(Display[] displayArr) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay == null) {
            this.b = new lba(this.c, displayArr[1], this.f14734a, this.f);
            lbv.e(this.c, this.f.o());
        } else if (!indoorTvBaseDisplay.isShowing()) {
            this.b = new lba(this.c, displayArr[1], this.f14734a, this.f);
            lbv.e(this.c, this.f.o());
        }
        this.b.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: law.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent != null && i == 4 && keyEvent.getAction() == 0) {
                    Toast.makeText(law.this.c, law.this.c.getString(R.string._2130840247_res_0x7f020ab7), 0).show();
                }
                return false;
            }
        });
        this.b.setCanceledOnTouchOutside(false);
        try {
            this.b.show();
        } catch (WindowManager.InvalidDisplayException | IllegalArgumentException e) {
            LogUtil.b("Track_IDEQ_IndoorEquipTvDisplayEngine", "showTreadmillTvDisplay exception = ", LogAnonymous.b(e));
        }
    }

    private void bVj_(Display[] displayArr) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay == null) {
            this.b = new lax(this.c, displayArr[1], this.f14734a, this.f);
            lbv.e(this.c, this.f.o());
        } else if (!indoorTvBaseDisplay.isShowing()) {
            this.b = new lax(this.c, displayArr[1], this.f14734a, this.f);
            lbv.e(this.c, this.f.o());
        }
        this.b.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: lay
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return law.this.bVl_(dialogInterface, i, keyEvent);
            }
        });
        this.b.setCanceledOnTouchOutside(false);
        try {
            this.b.show();
        } catch (WindowManager.InvalidDisplayException | IllegalArgumentException e) {
            LogUtil.b("Track_IDEQ_IndoorEquipTvDisplayEngine", "showIndoorTvDisplay exception = ", LogAnonymous.b(e));
        }
    }

    /* synthetic */ boolean bVl_(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (keyEvent != null && i == 4 && keyEvent.getAction() == 0) {
            Context context = this.c;
            Toast.makeText(context, context.getString(R.string._2130840324_res_0x7f020b04), 0).show();
        }
        return false;
    }

    private void bVi_(Display[] displayArr) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay == null) {
            this.b = new lat(this.c, displayArr[1], this.f14734a, this.f);
            lbv.e(this.c, this.f.o());
        } else if (!indoorTvBaseDisplay.isShowing()) {
            this.b = new lat(this.c, displayArr[1], this.f14734a, this.f);
            lbv.e(this.c, this.f.o());
        }
        this.b.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: law.3
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent != null && i == 4 && keyEvent.getAction() == 0) {
                    Toast.makeText(law.this.c, law.this.c.getString(R.string._2130840247_res_0x7f020ab7), 0).show();
                }
                return false;
            }
        });
        this.b.setCanceledOnTouchOutside(false);
        try {
            this.b.show();
        } catch (WindowManager.InvalidDisplayException | IllegalArgumentException e) {
            LogUtil.b("Track_IDEQ_IndoorEquipTvDisplayEngine", "showHeartRateControlSportTvDisplay exception = ", LogAnonymous.b(e));
        }
    }

    public void b(Map<Integer, Object> map) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.updateUi(map);
        }
    }

    public void e(int i) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.updateProgress(i);
        }
    }

    public void b(String str) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.setBtConnectState(str);
        }
    }

    public void e(Map<String, Object> map) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay == null || !(indoorTvBaseDisplay instanceof lat)) {
            return;
        }
        ((lat) indoorTvBaseDisplay).b(map);
    }

    public void c(int i) {
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay == null || !(indoorTvBaseDisplay instanceof lat)) {
            return;
        }
        ((lat) indoorTvBaseDisplay).a(i);
    }

    public void c() {
        LogUtil.a("Track_IDEQ_IndoorEquipTvDisplayEngine", "dismiss()");
        IndoorTvBaseDisplay indoorTvBaseDisplay = this.b;
        if (indoorTvBaseDisplay != null) {
            indoorTvBaseDisplay.dismiss();
        } else {
            LogUtil.b("Track_IDEQ_IndoorEquipTvDisplayEngine", "mIndoorTreadmillTvDisplay = null");
        }
    }

    public String e() {
        return "Track_IDEQ_IndoorEquipTvDisplayEngine";
    }
}
