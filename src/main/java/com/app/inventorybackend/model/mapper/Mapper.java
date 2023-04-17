package com.app.inventorybackend.model.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();


    private Mapper() {
    }

    //TODO: ver funcionamiento de este metodo con los types genericos

    public static <T,E> E map(T source, Class<E> destinationType){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(source, destinationType);
    }


    public static <S,T> List<T> mapList(List<S> source, Class<T> destinationType){
        return source.stream().map(element -> map(element, destinationType)).collect(Collectors.toList());
    }
}
