/**
 *
 */
package com.bobkubista.domain.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author bkubista
 * @param <ID>
 *            identifier
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class FunctionalIdentifiableDomainObject<ID extends Serializable> extends IdentifiableDomainObject<ID> {

    private static final long serialVersionUID = 332043130860626788L;
    @XmlElement(required = true)
    private String functionalId;

    /**
     * Constructor
     */
    public FunctionalIdentifiableDomainObject() {
    }

    /**
     * Constructor
     *
     * @param functionalId
     *            functionalId
     */
    public FunctionalIdentifiableDomainObject(final String functionalId) {
        this.functionalId = functionalId;
    }

    /**
     * @return the functional identifier
     */
    public final String getFunctionalId() {
        return this.functionalId;
    }

    /**
     * @param functionalId
     *            functional identifier
     */
    public final void setFunctionalId(final String functionalId) {
        this.functionalId = functionalId;
    }

}
