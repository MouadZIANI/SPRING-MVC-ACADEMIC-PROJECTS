package com.isi.converter;

import com.isi.entities.Tag;
import com.isi.services.TagService;
import lombok.SneakyThrows;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

public class TagFormatter extends CustomCollectionEditor {




    public TagFormatter(Class<? extends Collection> collectionType ) {
        super(collectionType);
    }

    @SneakyThrows
    @Override
    protected Object convertElement(Object element) {
        if(!element.equals(null)) {
            Tag tag =  new Tag(Long.parseLong(String.valueOf(element)));
            return tag;
        }else{
            return null;
        }
    }


}