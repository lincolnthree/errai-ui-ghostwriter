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
import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.ui.ghostwriter.client.shared.Fragment;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentSubmit;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Main application entry point.
 */
@Dependent
@Templated("Mockup.html#fragment-form")
public class FragmentFormWidget extends Composite {

  @Inject
  @Bound
  @DataField
  private TextBox name;

  @Inject
  @Bound
  @DataField
  private TextArea text;

  @Inject
  @DataField
  private Button submit;

  @Inject
  @AutoBound
  private DataBinder<Fragment> fragmentBinder;

  @Inject
  private Event<FragmentSubmit> fragmentSubmit;

  @EventHandler("submit")
  void handleSubmitClicked(ClickEvent event) {
    fragmentSubmit.fire(new FragmentSubmit(fragmentBinder.getModel()));
    text.setText("");
    text.setFocus(true);
  }
}
