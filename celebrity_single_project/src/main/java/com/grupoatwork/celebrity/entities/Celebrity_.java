package com.grupoatwork.celebrity.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Celebrity.class )
public class Celebrity_ {
	public static volatile SingularAttribute<Celebrity, Long> id;
	public static volatile SingularAttribute<Celebrity, Long> entityVersion;
	public static volatile SingularAttribute<Celebrity, String> firstName;
	public static volatile SingularAttribute<Celebrity, String> lastName;
	public static volatile SingularAttribute<Celebrity, Integer> phoneNumber;
}
