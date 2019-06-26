package com.in2minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveSomeBeans() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value12", "value22", "value32"));
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue retrieveSomeBeanDynamic() {
        DynamicBean dynamicBean = new DynamicBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicBean);
        mappingJacksonValue.setFilters(new SimpleFilterProvider()
                .addFilter("dynamicBeanFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2")));
        return mappingJacksonValue;
    }


    @GetMapping("/filtering-list-dynamic")
    public MappingJacksonValue retrieveSomeBeansDynamic() {
        List<DynamicBean> dynamicBeans = Arrays.asList(new DynamicBean("value1", "value2", "value3"),
                new DynamicBean("value12", "value22", "value32"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicBeans);
        mappingJacksonValue.setFilters(new SimpleFilterProvider()
                .addFilter("dynamicBeanFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3")));
        return mappingJacksonValue;
    }

}
