package hu.nemi.appcompat.view;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

public class DecoratingLayoutInflaterFactory implements LayoutInflaterFactory {
    private ViewDecorator viewDecorator;
    private ViewFactory viewFactory;

    public DecoratingLayoutInflaterFactory(LayoutInflater layoutInflater, ViewDecorator viewDecorator) {
        this.viewDecorator = viewDecorator;
        this.viewFactory = new ViewFactory(layoutInflater);

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = viewFactory.createView(parent, name, attrs);
        if(view != null) {
            viewDecorator.decorate(parent, view, context, attrs);
        }
        return view;
    }

    private static class ViewFactory {
        private Method method;
        private LayoutInflater layoutInflater;

        public ViewFactory(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
            this.method = getOnCreateViewMethod();
        }

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
}
