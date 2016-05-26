package hu.nemi.appcompat.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class ViewFactoryV11 implements ViewFactory {
    private Method method;
    private LayoutInflater layoutInflater;

    public ViewFactoryV11(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        this.method = getOnCreateViewMethod();
    }

    @Override
    public View createView(View parent, String name, AttributeSet attrs) {
        if(method != null) {
            return onCreateView(parent, name, attrs);
        }

        return null;
    }

    private View onCreateView(View parent, String name, AttributeSet attrs){
        try {
            return (View) method.invoke(layoutInflater, parent, name, attrs);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method getOnCreateViewMethod() {
        try {
            Method method = LayoutInflater.class.getDeclaredMethod("onCreateView",
                    new Class[]{View.class, String.class, AttributeSet.class});
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
