package com.forsrc.jredmine.server.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class BeanUtil {

	public static final ConcurrentMap<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();
	
	public static BeanCopier create(Class source, Class target, boolean useConverter) {
		String key = source.getName() + target.getName();
		BeanCopier beanCopier = BEAN_COPIER_MAP.get(key);
		if (beanCopier == null) {
			synchronized (BEAN_COPIER_MAP) {
				if (beanCopier == null) {
					 beanCopier = BeanCopier.create(source, target, useConverter);
					 BEAN_COPIER_MAP.put(key, beanCopier);
				} 
			}
		}
		return beanCopier;
	}

	public static <S, T> void copy(S source, T target) {
		BeanCopier beanCopier = create(source.getClass(), target.getClass(), false);
		beanCopier.copy(source, target, null);
	}
	
	public static <S, T> void copyIgnoreNull(S source, T target) {
		BeanCopier beanCopier = create(source.getClass(), target.getClass(), true);
		beanCopier.copy(source, target, new Converter() {
			
			@Override
			public Object convert(Object sourceValue, Class targetClass, Object context) {
				if (sourceValue == null) {
					String name = ObjectUtils.nullSafeToString(context).substring(3);
					String fieddName = StringUtils.uncapitalize(name);
					try {
						Object value = FieldUtils.getFieldValue(target, fieddName);
						return value;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					}
				}
				if (sourceValue.getClass() == targetClass) {
					return sourceValue;
				}
				return null;
			}
			
		});
	}
	
	public static <S, T> void copy(S source, T target, Converter converter) {
		BeanCopier beanCopier = create(source.getClass(), target.getClass(), converter != null);
		beanCopier.copy(source, target, converter);
	}
	
}
