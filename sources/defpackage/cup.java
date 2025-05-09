package defpackage;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public class cup {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, Integer> f11485a;
    private static HashMap<Integer, Integer> b = new HashMap<Integer, Integer>() { // from class: cup.3
        private static final long serialVersionUID = 1;

        {
            put(72, 1);
            put(73, 2);
            put(74, 1);
            put(75, 2);
            put(76, 1);
            put(79, 2);
            put(78, 1);
            put(80, 1);
            put(81, 2);
            put(82, 1);
            put(83, 1);
            put(84, 1);
            put(85, 1);
            put(88, 1);
            put(89, 1);
            put(90, 1);
            put(91, 1);
        }
    };
    private static HashMap<String, Integer> c;

    static {
        int i = 16;
        f11485a = new HashMap(i) { // from class: cup.2
            private static final long serialVersionUID = 1647400644090599380L;

            {
                put("2d63dc6a-30bf-4bfe-b27c-79b7a462442a", 16);
                put("6083cb0b-3388-4fb5-bce7-a0242fcbcce7", 18);
                put("1508e6af-4135-4d10-bb8a-4ba6bda8a8ec", 19);
                put("11642280-3614-4f5a-9696-f3cf4ddc07e2", 20);
                put("a8ab58b1-0893-42ce-8b2d-2de08aed4ab4", 21);
                put("57e30a50-8b51-4c11-bff0-807beef064b9", 23);
                put("83492bdc-e3eb-4ea8-abde-8e7a558eae72", 24);
                put("f2828ab4-d896-46de-8a09-ae0b9418b386", 36);
                put("aba0a74c-b1e0-4c7f-8448-8fe75f7b6e9c", 37);
                put("f7df3b17-cbb6-42b5-b8b3-3a30240c2ec3", 44);
                put("856b8a94-5218-4e04-b355-62505e9e59bf", 45);
                put("ce2ceada-adf8-40f2-b8b7-d595a6628a67", 34);
                put("568d8802-6506-4f74-991b-76b5c50b915b", 35);
                put("acccc80b-1b60-4a23-8f3c-c48c7483ac89", 55);
                put("69a968a4-5db8-4d1e-a390-762cb8039784", 57);
                put("0753996d-5b27-4867-9564-240e45b5289c", 58);
                put("67216939-80bf-47eb-9913-2a33763deefd", 61);
                put("87b9e25c-9b4e-473e-a1dc-c94b9f827f69", 60);
                put("aa3946cc-d2c7-4ffc-b167-1a6fede861ae", 65);
                put("7236036a-ba76-486a-9320-a1d3db62bebe", 64);
                put("24a18888-fed0-414d-a075-8649d48ddf5c", 63);
                put("b81581e1-5c5a-4e30-b099-0a448ef56a24", 66);
                put("24572deb-6b22-4a10-ab5d-4aa3c20c87d1", 71);
                put("8df8e0d8-e4bf-42f0-b0f3-0e11bf16caff", 72);
                put("f6f6eb8d-6168-4d42-ae08-27da0efda3a9", 73);
                put("17fa9133-b89a-4f76-aa44-1bd91f0549fc", 74);
                put("2785d456-09a6-4bca-beaa-485270df50c7", 75);
                put("26b05035-2a3f-4338-b564-6a9068b2b5c2", 269);
                put("ddbacead-850b-4adb-9e3b-7d53bbc0f416", 76);
                put("2378be1c-3e6f-4804-b17c-b9f00f9a13a8", 79);
                put("96cea4f7-9a5e-4db4-9dda-244885c5446f", 78);
                put("00259b85-c182-4e47-8236-c57d55a27301", 80);
                put("2f651f9f-5516-48bb-ab97-fa48841c1421", 81);
                put("fb5a1162-e71b-4b44-b8c2-c58ec475c085", 77);
                put("ba50037c-f395-4a5d-907d-d98b90c73840", 82);
                put("5bd4ac51-2379-4ff4-af4f-1b1e591f381c", 83);
                put("c680ed06-b360-4d34-ae5e-038f0aff4a16", 84);
                put("c7d28238-fec2-4985-b2a4-f29c96637ac4", 85);
                put("f5195256-bc1c-44b8-a4b7-52866f623d11", 91);
                put("e9e34cf9-b945-41a7-af0e-ec18b2904b79", 88);
                put("86f9682a-f439-4104-8a54-f5d1d6dcde3e", 89);
                put("488f8d9f-7428-4bd0-9a6b-5ff107e9613c", 90);
                put("7730ce60-8035-4e80-abd0-f8fbc6e74d2e", 93);
                put("6881432b-36ab-49d7-950c-8c2b5433c9b8", 94);
                put("e750d63e-a8fe-4980-8a99-8e6971b63bd5", 86);
                put("503b3f62-2a54-48e1-ad32-84f7971ab433", 87);
                put("68808678-2c89-45ab-9d58-793ea1291eff", 97);
                put("259cdcb3-d333-4f96-9160-c3c91d6ba959", 98);
                put("29b7de7d-e12f-3ce9-7120-323256d30419", 99);
                put("356aab94-7fec-465b-8936-8afff0c7d811", 101);
                put("8aaabb45-cc68-4b70-bf3f-09fe5f478dee", 100);
                put("45533294-b32d-4a30-b5b0-25cb942b335b", 102);
            }
        };
        c = new HashMap(i) { // from class: cup.1
            private static final long serialVersionUID = -4544457657602997361L;

            {
                put("e5ae9c59-0db8-4624-9862-09725412fff2", 10);
                put("85acddd2-2a69-462b-9766-61602023199b", 10);
                put("a4da0665-b209-4dfe-8617-cad54daf1b1f", 8);
                put("6e9be1de-3c45-424f-bc88-6c484eccbf77", 7);
                put("4a4406bb-db20-4f0e-90e4-13dcb0ed5a7f", 15);
                put("e85257bf-80cc-4c34-b5d2-3c524c73783d", 14);
                put("845dd3d4-83f4-4b6a-b721-46170b5ed857", 13);
                put("f98754bd-bc30-4c72-8d25-a1d17e13671b", 12);
                put("92d0af08-80c4-4039-8761-718ee249d5a4", 11);
                put("59ae6410-34d1-4971-b473-9247c4cbcd1f", 11);
            }
        };
    }

    public static HashMap<String, Integer> b() {
        return f11485a;
    }

    public static HashMap<Integer, Integer> a() {
        return b;
    }

    public static HashMap<String, Integer> c() {
        return c;
    }

    public static boolean d(String str) {
        return TextUtils.equals("85acddd2-2a69-462b-9766-61602023199b", str);
    }

    public static boolean a(String str) {
        return "92d0af08-80c4-4039-8761-718ee249d5a4".equals(str);
    }
}
