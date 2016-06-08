/**
 *
 */
package com.bobkubista.domain.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author bkubista
 * @param <ID>
 *            identifier
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ActiveDomainObject<ID extends Serializable> extends FunctionalIdentifiableDomainObject<ID> {

    private static final long serialVersionUID = -7516244737080941032L;

    @NotNull
    @XmlElement(required = true)
    private boolean active;

    /**
     * Constructor
     */
    public ActiveDomainObject() {
    }

    /**
     * Constructor
     *
     * @param active
     *            active flag
     * @param functionalId
     *            the functional identifier
     */
    public ActiveDomainObject(final boolean active, final String functionalId) {
        super(functionalId);
        this.active = active;
    }

    /**
     * @return true if the object is active
     */
    public final boolean isActive() {
        return this.active;
    }

    /**
     * @param active
     *            is the object active
     */
    public final void setActive(final boolean active) {
        this.active = active;
    }

}
