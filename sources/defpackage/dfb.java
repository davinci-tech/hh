package defpackage;

import android.app.Activity;
import android.os.Message;
import android.widget.ImageView;
import com.huawei.health.ecologydevice.open.BaseCardHandler;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class dfb extends BaseCardHandler<Activity> {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f11631a;
    private Object b;
    private int c;
    private int d;
    private ArrayList<Object> e;

    public void e(int i) {
        this.c = i;
    }

    public dfb(Activity activity, Object obj, int i, ImageView imageView, ArrayList<Object> arrayList) {
        super(activity);
        this.c = 1500;
        this.e = arrayList;
        this.f11631a = imageView;
        this.d = i;
        this.b = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.ecologydevice.open.BaseCardHandler
    /* renamed from: TP_, reason: merged with bridge method [inline-methods] */
    public void handleMessageWhenReferenceNotNull(Activity activity, Message message) {
        if (message.what != 994) {
            return;
        }
        c();
    }

    private void c() {
        ArrayList<Object> arrayList = this.e;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        if (this.e.size() == this.d) {
            this.d = 0;
        }
        Object obj = this.e.get(this.d);
        this.b = obj;
        this.d++;
        ImageView imageView = this.f11631a;
        if (imageView != null) {
            if (obj instanceof String) {
                imageView.setImageBitmap(dcx.TK_((String) obj));
            }
            Object obj2 = this.b;
            if (obj2 instanceof Integer) {
                this.f11631a.setImageResource(((Integer) obj2).intValue());
            }
        }
        sendEmptyMessageDelayed(994, this.c);
    }
}
