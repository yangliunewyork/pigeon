package com.orchid.pigeon.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import javax.servlet.Filter;

public class ServletInInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {

        return new String[] { "/" };
    }

    /*
        Make sure UTF-8 is the encoding type
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);


        return new Filter[] {characterEncodingFilter};
    }

}