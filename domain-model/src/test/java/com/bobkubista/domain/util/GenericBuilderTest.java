package com.bobkubista.domain.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.bobkubista.domain.model.GenericTestActiveDomainObject;

/**
 * @author Bob
 *
 */
public class GenericBuilderTest {

    @Test
    public void test() {
        Assert.assertNotNull(GenericBuilder.of(GenericTestActiveDomainObject::new)
                .with(GenericTestActiveDomainObject::setFunctionalId, "funct")
                .voilation(t -> StringUtils.isNotBlank(t.getFunctionalId()))
                .build());
    }

}
