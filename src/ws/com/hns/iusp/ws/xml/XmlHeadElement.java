package com.hns.iusp.ws.xml;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.xml.bind.annotation.XmlSchema;
@Retention(RUNTIME)
@Target({TYPE})
public @interface XmlHeadElement {
    /**
     * namespace name of the XML element.
     * <p>
     * If the value is "##default", then the XML namespace name is derived
     * from the package of the class ( {@link XmlSchema} ). If the
     * package is unnamed, then the XML namespace is the default empty
     * namespace.
     */
    String namespace() default "##default";

    /**
     * local name of the XML element.
     * <p>
     * If the value is "##default", then the name is derived from the
     * class name.
     *
     */
    String name() default "##default";

}
