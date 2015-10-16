// Generated code from Butter Knife. Do not modify!
package ru.nitrobubbles.motoplaces.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InfoFooterFragment$$ViewBinder<T extends ru.nitrobubbles.motoplaces.fragments.InfoFooterFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624092, "field 'subTitleText'");
    target.subTitleText = finder.castView(view, 2131624092, "field 'subTitleText'");
    view = finder.findRequiredView(source, 2131624093, "field 'address'");
    target.address = finder.castView(view, 2131624093, "field 'address'");
    view = finder.findRequiredView(source, 2131624091, "field 'titleText'");
    target.titleText = finder.castView(view, 2131624091, "field 'titleText'");
    view = finder.findRequiredView(source, 2131624090, "field 'compositeLayout'");
    target.compositeLayout = finder.castView(view, 2131624090, "field 'compositeLayout'");
  }

  @Override public void unbind(T target) {
    target.subTitleText = null;
    target.address = null;
    target.titleText = null;
    target.compositeLayout = null;
  }
}
