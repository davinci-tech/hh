package com.careforeyou.library.bt.bean;

import com.careforeyou.library.utils.CsBtUtil_v11;

/* loaded from: classes2.dex */
public class TaskData {
    private byte[] b;
    public TASK_TYPE c;
    public CsBtUtil_v11.Synchronization_Task_Key e;

    public enum TASK_TYPE {
        Read,
        Write,
        EnableNodification
    }

    public TaskData(CsBtUtil_v11.Synchronization_Task_Key synchronization_Task_Key, boolean z) {
        this.e = synchronization_Task_Key;
        this.c = TASK_TYPE.EnableNodification;
    }

    public TaskData(CsBtUtil_v11.Synchronization_Task_Key synchronization_Task_Key, byte[] bArr) {
        this.e = synchronization_Task_Key;
        if (bArr != null && bArr.length > 0) {
            this.b = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                this.b[i] = bArr[i];
            }
        }
        this.c = TASK_TYPE.Write;
    }

    public byte[] a() {
        int i = 0;
        byte[] bArr = new byte[0];
        byte[] bArr2 = this.b;
        if (bArr2 != null && bArr2.length > 0) {
            bArr = new byte[bArr2.length];
            while (true) {
                byte[] bArr3 = this.b;
                if (i >= bArr3.length) {
                    break;
                }
                bArr[i] = bArr3[i];
                i++;
            }
        }
        return bArr;
    }
}
