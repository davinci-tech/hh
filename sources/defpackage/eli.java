package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.PopUpListItemsTemplate;
import com.huawei.health.marketing.views.dialog.ListItemsDialogAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.RotateDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class eli extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private final List<d> f12080a;
    private CountDownLatch b;
    private RotateDialog.OnVisibleListenter c;
    private final Context d;
    private ListItemsDialogAdapter e;
    private View f;

    public eli(Context context, PopUpListItemsTemplate popUpListItemsTemplate) {
        super(context, R.style.CustomDialog, 0);
        this.f12080a = new ArrayList();
        this.d = context;
        b(popUpListItemsTemplate);
    }

    private void b(PopUpListItemsTemplate popUpListItemsTemplate) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.list_items_dialog_layout, (ViewGroup) null);
        this.f = inflate;
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.list);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        this.e = new ListItemsDialogAdapter(this.d);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
        healthRecycleView.setAdapter(this.e);
        List<SingleGridContent> gridContents = popUpListItemsTemplate.getGridContents();
        this.f12080a.clear();
        if (gridContents != null) {
            Iterator<SingleGridContent> it = gridContents.iterator();
            while (it.hasNext()) {
                this.f12080a.add(new d(it.next()));
            }
        }
        aqH_(gridContents != null ? gridContents.size() : 0, this.f.findViewById(R.id.dialog_card_view));
        this.e.d(this.f12080a);
        ((HealthTextView) this.f.findViewById(R.id.desc)).setText(popUpListItemsTemplate.getDescription());
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.f.findViewById(R.id.title);
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this.d, R.color._2131299296_res_0x7f090be0));
        healthSubHeader.setHeadTitleText(popUpListItemsTemplate.getTheme());
        healthSubHeader.setPaddingStartEnd(0.0f, 0.0f);
        healthSubHeader.setMarginStartEnd(0.0f, 0.0f);
        this.f.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() { // from class: ell
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                eli.this.aqI_(view);
            }
        });
        setContentView(this.f);
    }

    /* synthetic */ void aqI_(View view) {
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aqH_(int i, View view) {
        if (i <= 3) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = this.d.getResources().getDimensionPixelSize(R.dimen._2131363055_res_0x7f0a04ef);
        view.setLayoutParams(layoutParams);
    }

    public void a(RotateDialog.OnVisibleListenter onVisibleListenter) {
        this.c = onVisibleListenter;
    }

    public void d() {
        if (koq.b(this.f12080a) || a()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: elo
            @Override // java.lang.Runnable
            public final void run() {
                eli.this.e();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        if (r5.b.await(8000, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ void e() {
        /*
            r5 = this;
            java.lang.String r0 = "ListItemsDialog"
            java.util.concurrent.CountDownLatch r1 = new java.util.concurrent.CountDownLatch
            java.util.List<eli$d> r2 = r5.f12080a
            int r2 = r2.size()
            r1.<init>(r2)
            r5.b = r1
            r1 = 0
        L10:
            java.util.List<eli$d> r2 = r5.f12080a
            int r2 = r2.size()
            if (r1 >= r2) goto L39
            java.util.List<eli$d> r2 = r5.f12080a
            java.lang.Object r2 = r2.get(r1)
            eli$d r2 = (eli.d) r2
            if (r2 != 0) goto L23
            goto L36
        L23:
            java.lang.String r2 = r2.c()
            if (r2 != 0) goto L2a
            goto L36
        L2a:
            com.huawei.haf.threadpool.ThreadPoolManager r3 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            elj r4 = new elj
            r4.<init>()
            r3.execute(r4)
        L36:
            int r1 = r1 + 1
            goto L10
        L39:
            java.util.concurrent.CountDownLatch r1 = r5.b     // Catch: java.lang.InterruptedException -> L46
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L46
            r3 = 8000(0x1f40, double:3.9525E-320)
            boolean r1 = r1.await(r3, r2)     // Catch: java.lang.InterruptedException -> L46
            if (r1 != 0) goto L5d
            goto L54
        L46:
            r1 = move-exception
            java.lang.String r2 = "loadItemsBackground failed, cause exception happened = "
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            health.compact.a.util.LogUtil.e(r0, r1)
        L54:
            java.lang.String r1 = "loadItemsBackground failed, cause time out!"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r1)
        L5d:
            boolean r1 = r5.a()
            if (r1 == 0) goto L6c
            java.lang.String r1 = "loadItemsBackground not show, cause dialog host is destroyed!"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r1)
        L6c:
            com.huawei.health.marketing.views.dialog.ListItemsDialogAdapter r0 = r5.e
            r0.notifyDataSetChanged()
            r5.show()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eli.e():void");
    }

    /* synthetic */ void e(String str, int i) {
        Drawable cIb_ = nrf.cIb_(BaseApplication.getContext(), str);
        if (this.f12080a.get(i) != null) {
            this.f12080a.get(i).aqK_(cIb_);
        }
        this.b.countDown();
    }

    private boolean a() {
        Context context = this.d;
        return context == null || ((context instanceof Activity) && (((Activity) context).isFinishing() || ((Activity) this.d).isDestroyed()));
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        Window window = getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        RotateDialog.OnVisibleListenter onVisibleListenter = this.c;
        if (onVisibleListenter != null) {
            onVisibleListenter.onVisible(true, this.f);
        }
        super.show();
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f12081a;
        private SingleGridContent e;

        public d(SingleGridContent singleGridContent) {
            this.e = singleGridContent;
        }

        public Drawable aqJ_() {
            return this.f12081a;
        }

        public void aqK_(Drawable drawable) {
            this.f12081a = drawable;
        }

        public String e() {
            SingleGridContent singleGridContent = this.e;
            if (singleGridContent != null) {
                return singleGridContent.getLinkValue();
            }
            return null;
        }

        public String c() {
            SingleGridContent singleGridContent = this.e;
            if (singleGridContent != null) {
                return singleGridContent.getPicture();
            }
            return null;
        }
    }
}
