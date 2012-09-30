package org.jboss.errai.ui.ghostwriter.client.shared;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

import java.lang.Override;

@Bindable
@Portable
@Entity
public class Fragment implements Serializable {
  private static final long serialVersionUID = 5365045775822942813L;

  @Id
  private @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  Long id = null;
  @Version
  private @Column(name = "version")
  int version = 0;

  @Column
  private String name;

  @Column
  private String text;

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public int getVersion() {
    return this.version;
  }

  public void setVersion(final int version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    if (id != null) {
      return id.equals(((Fragment) that).id);
    }
    return super.equals(that);
  }

  @Override
  public int hashCode() {
    if (id != null) {
      return id.hashCode();
    }
    return super.hashCode();
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getText() {
    return this.text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public String toString() {
    String result = "";
    if (name != null && !name.trim().isEmpty())
      result += name;
    if (text != null && !text.trim().isEmpty())
      result += " " + text;
    return result;
  }
}