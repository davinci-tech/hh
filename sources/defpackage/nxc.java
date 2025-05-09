package defpackage;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import health.compact.a.Services;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes6.dex */
class nxc {
    private final int[] c = {R.string.IDS_device_pair_guide_b3_tip1_ex, R.string.IDS_device_pair_guide_b3_tip2_ex, R.string.IDS_device_pair_guide_b3_tip3};
    private final int[] d = {R.string.IDS_device_paring_type_le_des_info_21};
    private final int[] e = {R.string.IDS_select_device_connect_grus_change_tip_1, R.string.IDS_select_device_connect_grus_change_tip_2, R.string.IDS_device_pair_guide_b3_tip3};
    private final int[] b = {R.string._2130842747_res_0x7f02147b};

    /* renamed from: a, reason: collision with root package name */
    private Context f15527a = BaseApplication.getContext();

    nxc() {
    }

    protected void c(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity == null) {
            LogUtil.h("DevicePairGuideShowManager", "showPairGuide, activity is null");
            return;
        }
        LogUtil.a("DevicePairGuideShowManager", "Enter showPairGuide(): mDeviceProductType is ", Integer.valueOf(devicePairGuideActivity.an));
        devicePairGuideActivity.be = false;
        if (jfu.m(devicePairGuideActivity.an)) {
            if (devicePairGuideActivity.ah != null && devicePairGuideActivity.ah.f() != null) {
                e(devicePairGuideActivity.an, devicePairGuideActivity);
            }
        } else {
            c(devicePairGuideActivity.an, devicePairGuideActivity);
        }
        if (devicePairGuideActivity.an != 10) {
            devicePairGuideActivity.ax.sendEmptyMessage(4);
        }
    }

    private void e(int i, DevicePairGuideActivity devicePairGuideActivity) {
        if (i == 16) {
            o(devicePairGuideActivity);
            return;
        }
        if (i != 32) {
            if (i != 23 && i != 24 && i != 44 && i != 45) {
                switch (i) {
                    case 18:
                    case 19:
                        break;
                    case 20:
                    case 21:
                        b(BaseApplication.getContext().getString(R.string.IDS_device_fortuna_pair_guide_tip1), devicePairGuideActivity);
                        return;
                    default:
                        switch (i) {
                            case 34:
                            case 35:
                                b(BaseApplication.getContext().getString(R.string.IDS_device_latona_pair_guide_tip1_new), devicePairGuideActivity);
                                break;
                            case 36:
                            case 37:
                                break;
                            default:
                                s(devicePairGuideActivity);
                                break;
                        }
                        return;
                }
            }
            t(devicePairGuideActivity);
            return;
        }
        p(devicePairGuideActivity);
    }

    private void c(int i, DevicePairGuideActivity devicePairGuideActivity) {
        if (i != 3) {
            if (i == 32) {
                f(devicePairGuideActivity);
                return;
            }
            if (i == 7) {
                j(devicePairGuideActivity);
                return;
            }
            if (i == 8) {
                n(devicePairGuideActivity);
                return;
            }
            switch (i) {
                case 10:
                    break;
                case 11:
                    a(devicePairGuideActivity);
                    break;
                case 12:
                    h(devicePairGuideActivity);
                    break;
                case 13:
                    l(devicePairGuideActivity);
                    break;
                case 14:
                    m(devicePairGuideActivity);
                    break;
                case 15:
                    k(devicePairGuideActivity);
                    break;
                default:
                    a(i, devicePairGuideActivity);
                    break;
            }
        }
        g(devicePairGuideActivity);
    }

    private void a(int i, DevicePairGuideActivity devicePairGuideActivity) {
        if (i == 23) {
            e(devicePairGuideActivity);
            return;
        }
        if (i == 24) {
            b(devicePairGuideActivity);
            return;
        }
        if (i == 34) {
            b(this.f15527a.getString(R.string.IDS_device_latona_pair_guide_tip1_new), devicePairGuideActivity);
            return;
        }
        if (i == 36) {
            i(devicePairGuideActivity);
        } else if (i == 37) {
            d(devicePairGuideActivity);
        } else {
            LogUtil.a("DevicePairGuideShowManager", "no support type : ", Integer.valueOf(i));
        }
    }

    private void o(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, devicePairGuideActivity.ah.f().ao()));
        devicePairGuideActivity.bs.setVisibility(0);
        devicePairGuideActivity.bs.setImageResource(R.drawable._2131430724_res_0x7f0b0d44);
        devicePairGuideActivity.r = (AnimationDrawable) devicePairGuideActivity.bs.getDrawable();
        Iterator<String> it = devicePairGuideActivity.ah.f().ap().iterator();
        while (it.hasNext()) {
            devicePairGuideActivity.r.addFrame(new BitmapDrawable(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, it.next())), 100);
        }
        if (devicePairGuideActivity.r != null) {
            devicePairGuideActivity.r.start();
        } else {
            LogUtil.h("DevicePairGuideShowManager", "mB3PairGuideAnim is null");
        }
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(0);
        devicePairGuideActivity.cn.setVisibility(0);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.cf.setText(this.f15527a.getString(R.string.IDS_device_janus_pair_guide_tip1));
        HealthTextView healthTextView = devicePairGuideActivity.ce;
        Context context = this.f15527a;
        healthTextView.setText(context.getString(R.string.IDS_device_latona_pair_guide_tip3, context.getString(R.string.IDS_device_start_paring_title)));
    }

    private void t(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, devicePairGuideActivity.ah.f().ao()));
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(0);
        devicePairGuideActivity.co.setText(this.f15527a.getString(this.d[0], devicePairGuideActivity.aj));
    }

    private void b(String str, DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity.ah != null) {
            devicePairGuideActivity.bu.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, devicePairGuideActivity.ah.f().ao()));
        } else {
            devicePairGuideActivity.bu.setImageResource(R.mipmap._2131820673_res_0x7f110081);
        }
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(0);
        devicePairGuideActivity.cn.setVisibility(0);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.ch.setText(String.format(Locale.ENGLISH, this.f15527a.getResources().getString(R.string.IDS_device_latona_pair_guide_01), this.f15527a.getResources().getString(R.string.IDS_device_start_paring_title).toUpperCase(Locale.getDefault())));
        devicePairGuideActivity.cf.setText(str);
        HealthTextView healthTextView = devicePairGuideActivity.ce;
        Context context = this.f15527a;
        healthTextView.setText(context.getString(R.string.IDS_device_latona_pair_guide_tip3, context.getString(R.string.IDS_device_start_paring_title).toUpperCase(Locale.getDefault())));
    }

    private void p(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.cp.setVisibility(0);
        devicePairGuideActivity.bt.setText(this.f15527a.getResources().getString(R.string.IDS_device_cassini_no_fond_device_content_first, 1));
        devicePairGuideActivity.bw.setText(this.f15527a.getResources().getString(R.string.IDS_device_cassini_no_fond_device_content_second, 2));
    }

    private void s(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity.ah.f().h() == 1) {
            devicePairGuideActivity.bu.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, devicePairGuideActivity.ah.f().ao()));
            devicePairGuideActivity.bs.setVisibility(0);
            devicePairGuideActivity.bs.setImageResource(R.drawable._2131427995_res_0x7f0b029b);
            devicePairGuideActivity.r = (AnimationDrawable) devicePairGuideActivity.bs.getDrawable();
            if (devicePairGuideActivity.ah.f() == null) {
                LogUtil.h("DevicePairGuideShowManager", "mDeviceInfoForPlugin.getWearDeviceInfo() is null");
                return;
            }
            Iterator<String> it = devicePairGuideActivity.ah.f().ap().iterator();
            while (it.hasNext()) {
                devicePairGuideActivity.r.addFrame(new BitmapDrawable(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, it.next())), 100);
            }
            if (devicePairGuideActivity.r != null) {
                devicePairGuideActivity.r.start();
            } else {
                LogUtil.h("DevicePairGuideShowManager", "mB3PairGuideAnim is null!");
            }
        } else {
            devicePairGuideActivity.bu.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(devicePairGuideActivity.ah, devicePairGuideActivity.ah.f().ao()));
        }
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(0);
        String y = jfu.c(devicePairGuideActivity.an).y();
        if ("".equals(y)) {
            y = this.f15527a.getString(this.d[0], devicePairGuideActivity.aj);
        }
        devicePairGuideActivity.co.setText(y);
    }

    private void h(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821052_res_0x7f1101fc);
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(0);
        devicePairGuideActivity.co.setText(this.f15527a.getString(this.d[0], devicePairGuideActivity.aj));
    }

    private void n(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821243_res_0x7f1102bb);
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(0);
        devicePairGuideActivity.co.setText(this.f15527a.getString(this.d[0]));
    }

    private void f(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821243_res_0x7f1102bb);
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        p(devicePairGuideActivity);
    }

    private void l(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821241_res_0x7f1102b9);
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(0);
        devicePairGuideActivity.co.setText(this.f15527a.getString(this.d[0], devicePairGuideActivity.aj));
    }

    private void k(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821248_res_0x7f1102c0);
        devicePairGuideActivity.ci.setVisibility(8);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(0);
        devicePairGuideActivity.co.setText(this.f15527a.getString(this.d[0], devicePairGuideActivity.aj));
    }

    private void j(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821245_res_0x7f1102bd);
        devicePairGuideActivity.bs.setVisibility(0);
        devicePairGuideActivity.bs.setImageResource(R.drawable._2131427565_res_0x7f0b00ed);
        devicePairGuideActivity.r = (AnimationDrawable) devicePairGuideActivity.bs.getDrawable();
        if (devicePairGuideActivity.bs.getDrawable() instanceof AnimationDrawable) {
            devicePairGuideActivity.r = (AnimationDrawable) devicePairGuideActivity.bs.getDrawable();
        }
        if (devicePairGuideActivity.r != null) {
            devicePairGuideActivity.r.start();
        } else {
            LogUtil.h("DevicePairGuideShowManager", "mB3PairGuideAnim is null!");
        }
        r(devicePairGuideActivity);
    }

    private void m(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821250_res_0x7f1102c2);
        devicePairGuideActivity.bs.setVisibility(0);
        devicePairGuideActivity.bs.setImageResource(R.drawable._2131428304_res_0x7f0b03d0);
        devicePairGuideActivity.r = (AnimationDrawable) devicePairGuideActivity.bs.getDrawable();
        if (devicePairGuideActivity.r != null) {
            devicePairGuideActivity.r.start();
        } else {
            LogUtil.h("DevicePairGuideShowManager", "mGRUSPairGuideAnim is null!");
        }
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(0);
        devicePairGuideActivity.cl.setVisibility(0);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.ck.setText(this.e[2]);
        String string = this.f15527a.getResources().getString(this.e[0]);
        String string2 = this.f15527a.getResources().getString(this.e[1]);
        devicePairGuideActivity.cf.setText(string);
        devicePairGuideActivity.ce.setText(string2);
    }

    private void g(DevicePairGuideActivity devicePairGuideActivity) {
        if (devicePairGuideActivity.bh) {
            devicePairGuideActivity.ax.sendEmptyMessage(2);
            return;
        }
        devicePairGuideActivity.ax.sendEmptyMessage(1);
        devicePairGuideActivity.ax.sendEmptyMessage(4);
        devicePairGuideActivity.ax.sendEmptyMessage(5);
    }

    private void a(DevicePairGuideActivity devicePairGuideActivity) {
        String format;
        if ("HUAWEI CM-R1P".equals(devicePairGuideActivity.bl) || this.f15527a.getString(R.string._2130849807_res_0x7f02300f).equals(devicePairGuideActivity.bl) || this.f15527a.getString(R.string.IDS_device_r1_pro_name_title).equals(devicePairGuideActivity.bl)) {
            devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821459_res_0x7f110393);
        } else {
            devicePairGuideActivity.bu.setImageResource(R.mipmap._2131821043_res_0x7f1101f3);
        }
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(0);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cq.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, this.f15527a.getResources().getString(R.string.IDS_pad_device_paring_step1), new Object[0]);
        } else {
            format = String.format(Locale.ENGLISH, this.f15527a.getResources().getString(R.string.IDS_device_paring_type_r1_des_info_step1), new Object[0]);
        }
        devicePairGuideActivity.cf.setText(format);
        devicePairGuideActivity.ce.setText(R.string.IDS_device_paring_type_r1_des_info_guide_2);
    }

    private void e(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cq.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.cf.setText(this.b[0]);
    }

    private void b(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cq.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.cf.setText(devicePairGuideActivity.o[0]);
    }

    private void i(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cq.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.cf.setText(devicePairGuideActivity.o[0]);
    }

    private void d(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(8);
        devicePairGuideActivity.cl.setVisibility(8);
        devicePairGuideActivity.cq.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.cf.setText(devicePairGuideActivity.o[0]);
    }

    private void r(DevicePairGuideActivity devicePairGuideActivity) {
        devicePairGuideActivity.ci.setVisibility(0);
        devicePairGuideActivity.cj.setVisibility(0);
        devicePairGuideActivity.cl.setVisibility(0);
        devicePairGuideActivity.cn.setVisibility(8);
        devicePairGuideActivity.cp.setVisibility(8);
        devicePairGuideActivity.cm.setVisibility(8);
        devicePairGuideActivity.ck.setText(this.c[2]);
        Html.ImageGetter imageGetter = new Html.ImageGetter() { // from class: nxc.5
            @Override // android.text.Html.ImageGetter
            public Drawable getDrawable(String str) {
                int i;
                try {
                    i = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                    LogUtil.a("DevicePairGuideShowManager", "NumberFormatException");
                    i = 0;
                }
                Drawable drawable = nxc.this.f15527a.getResources().getDrawable(i);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        String string = this.f15527a.getResources().getString(this.c[0]);
        String string2 = this.f15527a.getResources().getString(this.c[1]);
        int indexOf = string.indexOf("%1$s");
        int indexOf2 = string2.indexOf("%1$s");
        if (indexOf > 0) {
            devicePairGuideActivity.cf.setText(string.substring(0, indexOf));
            devicePairGuideActivity.cf.append(Html.fromHtml("<img src='2131430679'/>", imageGetter, null));
            devicePairGuideActivity.cf.append(string.substring(indexOf + 4));
        }
        if (indexOf2 > 0) {
            devicePairGuideActivity.ce.setText(string2.substring(0, indexOf2));
            devicePairGuideActivity.ce.append(Html.fromHtml("<img src='2131430669'/>", imageGetter, null));
            devicePairGuideActivity.ce.append(string2.substring(indexOf2 + 4));
        }
    }
}
