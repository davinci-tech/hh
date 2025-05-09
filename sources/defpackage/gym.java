package defpackage;

import android.content.Context;
import android.view.View;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.CityListBean;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.downloadwidget.HealthDownLoadWidget;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.List;

/* loaded from: classes4.dex */
public class gym {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f13006a;
    private HealthTextView b;
    private int c = 0;
    private HealthDownLoadWidget d;
    private HealthTextView e;
    private HealthDivider j;

    public gym(View view) {
        if (view != null) {
            this.b = (HealthTextView) view.findViewById(R.id.city_name);
            this.f13006a = (HealthTextView) view.findViewById(R.id.city_size);
            this.j = (HealthDivider) view.findViewById(R.id.offline_view);
            this.e = (HealthTextView) view.findViewById(R.id.download_text);
            this.d = (HealthDownLoadWidget) view.findViewById(R.id.city_status);
            view.setTag(this);
        }
    }

    public void d(Context context, int i, int i2) {
        if (context != null) {
            this.e.setText((CharSequence) null);
            this.d.d();
            a(context, i, i2);
        }
    }

    private void a(Context context, int i, int i2) {
        if (i == 0) {
            a(8, 0);
            int i3 = this.c;
            this.c = i2;
            this.d.c(i2 - i3);
            return;
        }
        if (i == 1) {
            a(8, 0);
            if (i2 == 0) {
                this.d.setIdleText(context.getString(R.string._2130839737_res_0x7f0208b9));
            }
            this.d.setProgress(i2);
            this.d.setIdleText(context.getString(R.string._2130839737_res_0x7f0208b9));
            return;
        }
        if (i == 2) {
            a(8, 0);
            this.d.setIdleText(context.getString(R.string._2130839736_res_0x7f0208b8));
            return;
        }
        if (i != 3) {
            if (i == 4) {
                if (i2 == 100) {
                    this.d.b();
                    a(0, 8);
                    this.e.setText(context.getString(R.string._2130839738_res_0x7f0208ba));
                    return;
                }
                return;
            }
            if (i != 101) {
                d(context, i);
                return;
            }
        }
        a(8, 0);
        this.d.setIdleText(context.getString(R.string._2130839739_res_0x7f0208bb));
    }

    private void d(Context context, int i) {
        if (i == -1) {
            a(8, 0);
            this.d.setIdleText(context.getString(R.string._2130840222_res_0x7f020a9e));
            return;
        }
        if (i != 10) {
            if (i == 1002) {
                a(8, 0);
                this.d.setIdleText(context.getString(R.string._2130839740_res_0x7f0208bc));
                return;
            }
            if (i == 5) {
                a(8, 0);
                this.d.setIdleText(context.getString(R.string._2130840223_res_0x7f020a9f));
                return;
            }
            if (i == 6) {
                a(8, 0);
                this.d.setIdleText(context.getString(R.string._2130839735_res_0x7f0208b7));
                return;
            } else if (i != 7) {
                if (i == 102) {
                    a(8, 0);
                    this.d.setIdleText(context.getString(R.string._2130840219_res_0x7f020a9b));
                    return;
                } else {
                    if (i != 103) {
                        return;
                    }
                    a(8, 0);
                    this.d.setIdleText(context.getString(R.string._2130840220_res_0x7f020a9c));
                    return;
                }
            }
        }
        a(8, 0);
        this.d.setIdleText(context.getString(R.string._2130840213_res_0x7f020a95));
    }

    private void a(int i, int i2) {
        this.e.setVisibility(i);
        this.d.setVisibility(i2);
    }

    public HealthTextView d() {
        return this.b;
    }

    public HealthTextView b() {
        return this.f13006a;
    }

    public HealthDownLoadWidget a() {
        return this.d;
    }

    public void b(List<OfflineMapCity> list, int i) {
        if (koq.c(list)) {
            if (i == list.size() - 1) {
                this.j.setVisibility(8);
            } else {
                this.j.setVisibility(0);
            }
        }
    }

    public void e(CityListBean cityListBean, int i) {
        if (koq.c(cityListBean)) {
            if (i == cityListBean.size() - 1) {
                this.j.setVisibility(8);
            } else {
                this.j.setVisibility(0);
            }
        }
    }
}
