// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f0a0198;

  private View view7f0a019a;

  private View view7f0a0199;

  private View view7f0a0197;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.view_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'view_pager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.nav_beranda, "field 'nav_beranda' and method 'onClickNavBeranda'");
    target.nav_beranda = Utils.castView(view, R.id.nav_beranda, "field 'nav_beranda'", ElasticImageView.class);
    view7f0a0198 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNavBeranda();
      }
    });
    view = Utils.findRequiredView(source, R.id.nav_status, "field 'nav_status' and method 'onClickNavStatus'");
    target.nav_status = Utils.castView(view, R.id.nav_status, "field 'nav_status'", ElasticImageView.class);
    view7f0a019a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNavStatus();
      }
    });
    view = Utils.findRequiredView(source, R.id.nav_notifikasi, "field 'nav_notifikasi' and method 'onClickNavNotifikasi'");
    target.nav_notifikasi = Utils.castView(view, R.id.nav_notifikasi, "field 'nav_notifikasi'", ElasticImageView.class);
    view7f0a0199 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNavNotifikasi();
      }
    });
    view = Utils.findRequiredView(source, R.id.nav_akun, "field 'nav_akun' and method 'onClickNavAkun'");
    target.nav_akun = Utils.castView(view, R.id.nav_akun, "field 'nav_akun'", ElasticImageView.class);
    view7f0a0197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNavAkun();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomNavigation = null;
    target.view_pager = null;
    target.nav_beranda = null;
    target.nav_status = null;
    target.nav_notifikasi = null;
    target.nav_akun = null;

    view7f0a0198.setOnClickListener(null);
    view7f0a0198 = null;
    view7f0a019a.setOnClickListener(null);
    view7f0a019a = null;
    view7f0a0199.setOnClickListener(null);
    view7f0a0199 = null;
    view7f0a0197.setOnClickListener(null);
    view7f0a0197 = null;
  }
}
