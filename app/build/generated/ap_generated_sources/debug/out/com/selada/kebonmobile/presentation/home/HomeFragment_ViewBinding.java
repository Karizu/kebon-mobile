// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.home;

import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import com.skydoves.elasticviews.ElasticImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.rv_home_1 = Utils.findRequiredViewAsType(source, R.id.rv_home_1, "field 'rv_home_1'", RecyclerView.class);
    target.rv_home_2 = Utils.findRequiredViewAsType(source, R.id.rv_home_2, "field 'rv_home_2'", RecyclerView.class);
    target.rv_home_lahan = Utils.findRequiredViewAsType(source, R.id.rv_home_lahan, "field 'rv_home_lahan'", RecyclerView.class);
    target.img_title = Utils.findRequiredViewAsType(source, R.id.img_title, "field 'img_title'", ElasticImageView.class);
    target.layoutInvestor = Utils.findRequiredViewAsType(source, R.id.layoutInvestor, "field 'layoutInvestor'", LinearLayout.class);
    target.nestedScrollView = Utils.findRequiredViewAsType(source, R.id.nestedScrollView, "field 'nestedScrollView'", HorizontalScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rv_home_1 = null;
    target.rv_home_2 = null;
    target.rv_home_lahan = null;
    target.img_title = null;
    target.layoutInvestor = null;
    target.nestedScrollView = null;
  }
}
