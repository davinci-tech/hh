package defpackage;

import com.careforeyou.library.bt.bean.TaskData;
import com.careforeyou.library.intface.AsynBLETaskCallback;
import com.careforeyou.library.utils.CsBtUtil_v11;

/* loaded from: classes2.dex */
public class nm {
    private CsBtUtil_v11 b;
    private no<TaskData> d = new no<>();
    private boolean e = false;

    public nm(CsBtUtil_v11 csBtUtil_v11) {
        this.b = csBtUtil_v11;
    }

    public void a() {
        this.e = false;
        this.d.a();
    }

    public void b(TaskData taskData) {
        this.d.d(taskData);
    }

    public void d() {
        d(true);
    }

    private void d(Boolean bool) {
        if (this.d.e() == 0) {
            this.e = false;
            return;
        }
        if (this.e && bool.booleanValue()) {
            return;
        }
        this.e = true;
        TaskData d = this.d.d();
        if (d.c == TaskData.TASK_TYPE.Read) {
            return;
        }
        if (d.c == TaskData.TASK_TYPE.Write) {
            this.b.c(d, new AsynBLETaskCallback() { // from class: nm.5
                @Override // com.careforeyou.library.intface.AsynBLETaskCallback
                public void success(Object obj) {
                    nm.this.c();
                }

                @Override // com.careforeyou.library.intface.AsynBLETaskCallback
                public void failed() {
                    nm.this.c();
                }
            });
        } else if (d.c == TaskData.TASK_TYPE.EnableNodification) {
            this.b.c(d.e, new AsynBLETaskCallback() { // from class: nm.2
                @Override // com.careforeyou.library.intface.AsynBLETaskCallback
                public void success(Object obj) {
                    nm.this.c();
                }

                @Override // com.careforeyou.library.intface.AsynBLETaskCallback
                public void failed() {
                    nm.this.c();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.d.e() == 0) {
            this.e = false;
            return;
        }
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d(false);
    }
}
