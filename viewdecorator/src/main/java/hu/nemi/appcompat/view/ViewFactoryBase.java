package hu.nemi.appcompat.view;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

class ViewFactoryBase implements ViewFactory {
    private Method method;
    private LayoutInflater layoutInflater;

    public ViewFactoryBase(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        this.method = getOnCreateViewMethod();
    }

    private Method getOnCreateViewMethod() {
        try {
            Method method = LayoutInflater.class.getDeclaredMethod("onCreateView",
                    String.class, AttributeSet.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override
    public View createView(View parent, String name, AttributeSet attrs) {
        if(method != null) {
            return onCreateView(name, attrs);
        }

        return null;
    }

    private View onCreateView(String name, AttributeSet attrs){
        try {
            return (View) method.invoke(layoutInflater, name, attrs);
        } catch (Exception e) {
            return null;
        }
    }
}
