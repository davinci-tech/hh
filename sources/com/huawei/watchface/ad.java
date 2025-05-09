package com.huawei.watchface;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.skinner.base.SkinBaseFragmentActivity;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes7.dex */
public class ad extends Dialog {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f10888a = false;
    private static WeakHashMap<Dialog, String> b = new WeakHashMap<>();
    private static WeakHashMap<Dialog, String> c = new WeakHashMap<>();
    private Context d;
    private int e;

    public ad(Context context, int i) {
        super(context, i);
        this.e = -1;
        this.d = context;
        b(context);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            if (!f10888a) {
                c();
                d();
            }
            super.dismiss();
        } catch (Exception e) {
            HwLog.i("BaseDialog", "dismiss: " + HwLog.printException(e));
        }
    }

    @Override // android.app.Dialog
    public void show() {
        int a2;
        HwLog.i("BaseDialog", "show() enter.");
        if (this.e != 0) {
            Window window = getWindow();
            window.setDimAmount(0.2f);
            Display defaultDisplay = ((WindowManager) this.d.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int a3 = CommonUtils.a(this.d, 16.0f);
            int width = defaultDisplay.getWidth();
            int a4 = CommonUtils.a(this.d, 191.0f);
            if (CommonUtils.d()) {
                int a5 = CommonUtils.a(this.d, 24.0f);
                float f = width;
                float f2 = (f - (a5 * 9.0f)) / 8.0f;
                if (f / defaultDisplay.getHeight() > 0.75f) {
                    a2 = ((int) (f2 * 2.0f)) + (a5 * 3);
                    HwLog.d("BaseDialog", "folding padding width:" + a2);
                } else {
                    a2 = ((int) (f2 * 1.5f)) + (a5 * 2);
                    HwLog.d("BaseDialog", "pad padding width:" + a2);
                }
            } else {
                a2 = CommonUtils.a(this.d, 12.0f);
            }
            if (this.d.getResources().getConfiguration().orientation != 2) {
                a4 = a2;
            }
            attributes.width = width - (a4 * 2);
            HwLog.i("BaseDialog", "show() mDialogType " + this.e);
            if (!CommonUtils.d()) {
                attributes.y = a3;
            }
            window.setAttributes(attributes);
            if (CommonUtils.d()) {
                window.setGravity(16);
            } else {
                window.setGravity(80);
            }
        }
        c(this.d);
        super.show();
    }

    private void b(Context context) {
        if (context instanceof SkinBaseFragmentActivity) {
            a(this, context.toString());
        }
    }

    private void c() {
        b(this);
    }

    private void c(Context context) {
        if (context instanceof SkinBaseFragmentActivity) {
            c.put(this, context.toString());
        }
    }

    private void d() {
        c.remove(this);
    }

    public static void a(Context context, Dialog dialog) {
        if ((context instanceof SkinBaseFragmentActivity) && !(dialog instanceof ad)) {
            a(dialog, context.toString());
        }
    }

    public static void a(Dialog dialog) {
        b(dialog);
    }

    public static void a() {
        HwLog.i("BaseDialog", "configureChangedShowDialog() enter");
        f10888a = true;
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.putAll(c);
        Iterator it = weakHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Dialog dialog = (Dialog) ((Map.Entry) it.next()).getKey();
            if (dialog != null) {
                dialog.dismiss();
            }
        }
        Iterator it2 = weakHashMap.entrySet().iterator();
        while (it2.hasNext()) {
            Dialog dialog2 = (Dialog) ((Map.Entry) it2.next()).getKey();
            if (dialog2 != null) {
                dialog2.show();
            }
        }
        f10888a = false;
    }

    public static void a(Context context) {
        ArrayList<Dialog> a2 = a(context.toString());
        if (a2 == null) {
            return;
        }
        Iterator<Dialog> it = a2.iterator();
        while (it.hasNext()) {
            it.next().dismiss();
        }
    }

    private static void a(Dialog dialog, String str) {
        b.put(dialog, str);
    }

    private static void b(Dialog dialog) {
        b.remove(dialog);
    }

    private static ArrayList<Dialog> a(String str) {
        c.clear();
        int size = b.size();
        if (size == 0) {
            return new ArrayList<>();
        }
        Iterator<Map.Entry<Dialog, String>> it = b.entrySet().iterator();
        ArrayList<Dialog> arrayList = null;
        while (it.hasNext()) {
            Map.Entry<Dialog, String> next = it.next();
            if (str.equals(next.getValue())) {
                Dialog key = next.getKey();
                if (key != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>(size);
                    }
                    arrayList.add(key);
                }
                it.remove();
            }
        }
        return arrayList;
    }

    public static void b() {
        c.clear();
        b.clear();
    }
}
