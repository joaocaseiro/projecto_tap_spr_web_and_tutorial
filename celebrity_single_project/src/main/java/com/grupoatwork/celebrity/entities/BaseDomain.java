package com.grupoatwork.celebrity.entities;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.core.style.ToStringCreator;


@MappedSuperclass
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 47396264832642736L;
	
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BaseDomainGenerator")
	@TableGenerator(name = "BaseDomainGenerator", table = "ObjectIdSequences", pkColumnName = "ObjectClass", valueColumnName = "CurrentId", initialValue = 20120427)
	private Long id; 
	
	@Version
	@XmlElement(name="version")
    private Long entityVersion = new Long(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Long entityVersion) {
		this.entityVersion = entityVersion;
	}
	
	@Override
    public String toString() {
		ToStringCreator creator = new ToStringCreator(this);
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(getClass());
			for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()) {
				String name = desc.getName();
				Object result = null;
				
				if (Collection.class.isAssignableFrom(desc.getPropertyType())) {
					result = "[collection ]";
				} else {
					if (BaseDomain.class.isAssignableFrom(desc.getPropertyType()) && desc.getReadMethod().invoke(this) != null) {
						result = "" + desc.getPropertyType().getName() + " - " + ((BaseDomain) desc.getReadMethod().invoke(this)).getId();
					} else {
						if (desc.getReadMethod().invoke(this) == null) {
							result = "null";
						} else {
							result = desc.getReadMethod().invoke(this);
						}
					}
				}
				creator.append(name, result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return creator.toString();
	}
}
