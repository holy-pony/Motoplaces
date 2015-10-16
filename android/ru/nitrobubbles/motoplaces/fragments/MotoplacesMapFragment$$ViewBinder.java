// Generated code from Butter Knife. Do not modify!
package ru.nitrobubbles.motoplaces.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MotoplacesMapFragment$$ViewBinder<T extends ru.nitrobubbles.motoplaces.fragments.MotoplacesMapFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624122, "field 'searchEdit'");
    target.searchEdit = finder.castView(view, 2131624122, "field 'searchEdit'");
    view = finder.findRequiredView(source, 2131624121, "field 'searchLayout'");
    target.searchLayout = finder.castView(view, 2131624121, "field 'searchLayout'");
    view = finder.findRequiredView(source, 2131624123, "field 'bottomContainer'");
    target.bottomContainer = finder.castView(view, 2131624123, "field 'bottomContainer'");
  }

  @Override public void unbind(T target) {
    target.searchEdit = null;
    target.searchLayout = null;
    target.bottomContainer = null;
  }
}
