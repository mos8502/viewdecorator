package android.support.v4.view;

import android.view.LayoutInflater;

public class LayoutInflaterCompat {
    public interface LayoutInflaterCompatImpl {
        void setFactory(LayoutInflater layoutInflater, LayoutInflaterFactory factory);
    }

    private static final ThreadLocal<LayoutInflaterCompatImpl> IMPL = new ThreadLocal<>();

    public static void setImpl(LayoutInflaterCompatImpl impl) {
        IMPL.set(impl);
    }

    public static void setFactory(LayoutInflater inflater, LayoutInflaterFactory factory) {
        LayoutInflaterCompatImpl layoutInflaterCompat = IMPL.get();
        if(layoutInflaterCompat != null) {
            layoutInflaterCompat.setFactory(inflater, factory);
        }
    }

}
