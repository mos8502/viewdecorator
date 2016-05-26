package hu.nemi.appcompat.view;


import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ViewDecoratorInstallerTest {

    @Mock AppCompatActivity appCompatActivity;
    @Mock AppCompatDelegate appCompatDelegate;
    @Mock ViewDecorator viewDecorator;
    @Mock LayoutInflater layoutInflater;

    ViewDecoratorInstaller viewDecoratorInstaller;

    @Before
    public void setup() {
        viewDecoratorInstaller = new ViewDecoratorInstaller(viewDecorator);
        doReturn(layoutInflater).when(appCompatActivity).getLayoutInflater();
    }

    @Test(expected = IllegalStateException.class)
    public void testInstallNoDelegate() {
        viewDecoratorInstaller.install(appCompatActivity);
    }

    @Test
    public void testInstallViewFactoryAlreadyInstalled() {
        LayoutInflater.Factory installedFactory = mock(LayoutInflater.Factory.class);
        doReturn(appCompatDelegate).when(appCompatActivity).getDelegate();
        doReturn(installedFactory).when(layoutInflater).getFactory();
        assertFalse(viewDecoratorInstaller.install(appCompatActivity));
    }

    @Test
    public void testInstallSuccess() {
        LayoutInflaterCompat.LayoutInflaterCompatImpl impl
                = mock(LayoutInflaterCompat.LayoutInflaterCompatImpl.class);
        LayoutInflaterCompat.setImpl(impl);

        ArgumentCaptor<LayoutInflaterFactory> factoryCaptor
                = ArgumentCaptor.forClass(LayoutInflaterFactory.class);

        doNothing().when(impl).setFactory(eq(layoutInflater), factoryCaptor.capture());
        doReturn(appCompatDelegate).when(appCompatActivity).getDelegate();

        assertTrue(viewDecoratorInstaller.install(appCompatActivity));
        assertTrue(factoryCaptor.getValue() instanceof AppCompatDecoratingLayoutInflaterFactory);

    }


}