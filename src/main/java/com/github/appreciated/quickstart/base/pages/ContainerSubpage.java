package com.github.appreciated.quickstart.base.pages;

/**
 * Created by appreciated on 06.12.2016.
 */
public interface ContainerSubpage extends ComponentSubpage {
    default boolean hasPadding() {
        return true;
    }
}
