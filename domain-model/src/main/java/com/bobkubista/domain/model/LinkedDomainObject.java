package com.bobkubista.domain.model;

import java.util.List;

import javax.ws.rs.core.Link;

/**
 * A {@link DomainObject} that contains HATEOAS links
 * 
 * @author Bob
 *
 */
public interface LinkedDomainObject extends DomainObject {

    /**
     * @return the links
     */
    public List<Link> getLinks();

}
