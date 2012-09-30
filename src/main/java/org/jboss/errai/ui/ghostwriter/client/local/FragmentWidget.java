/*
 * Copyright 2009 JBoss, a division of Red Hat Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.errai.ui.ghostwriter.client.local;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.ui.ghostwriter.client.shared.Fragment;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentDelete;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentRemoved;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Main application entry point.
 */
@Dependent
@Templated("Mockup.html#fragment")
public class FragmentWidget extends Composite {

  @Inject
  @DataField("fragment-text")
  private Anchor text;

  private Fragment fragment;

  public Widget setFragment(Fragment fragment) {
    this.fragment = fragment;
    if (fragment.getText() != null)
      text.setHTML(fragment.getText().replaceAll("\\n+$", "")
              .replaceAll("\\n+", "<br/></br>"));

    if (fragment.getName() == null)
      fragment.setName("Anonymous");

    getElement().setTitle("by: " + fragment.getName() + " (click to delete)");
    return this;
  }

  @Inject
  private Event<FragmentDelete> fragmentDelete;

  @EventHandler("fragment-text")
  public void handleDeleteClick(ClickEvent event) {
    fragmentDelete.fire(new FragmentDelete(fragment));
  }

  public void handleFragmentRemoved(@Observes FragmentRemoved event) {
    if (fragment != null
            && event.getFragment().getId().equals(fragment.getId()))
      this.removeFromParent();
  }

  /*
   * jQuery integration
   */
  @Override
  protected native void onLoad() /*-{
		$wnd.jQuery(".fragment").tooltip();
  }-*/;

  @Override
  protected native void onUnload() /*-{
		$wnd.jQuery(".fragment").tooltip('hide');
  }-*/;

}
