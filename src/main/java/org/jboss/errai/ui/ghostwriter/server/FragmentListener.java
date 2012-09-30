package org.jboss.errai.ui.ghostwriter.server;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.errai.ui.ghostwriter.client.shared.Fragment;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentAdded;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentDelete;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentRemoved;
import org.jboss.errai.ui.ghostwriter.client.shared.FragmentSubmit;

@Stateful
@RequestScoped
@TransactionAttribute
public class FragmentListener {
  @PersistenceContext
  private EntityManager em;

  @Inject
  private Event<FragmentAdded> fragmentAdded;
  @Inject
  private Event<FragmentRemoved> fragmentRemoved;

  public void handleFragmentSubmit(@Observes FragmentSubmit event) {
    em.persist(event.getFragment());
    fragmentAdded.fire(new FragmentAdded(event.getFragment()));
  }

  public void handleFragmentSubmit(@Observes FragmentDelete event) {
    em.remove(em.find(Fragment.class, event.getFragment().getId()));
    fragmentRemoved.fire(new FragmentRemoved(event.getFragment()));
  }
}
