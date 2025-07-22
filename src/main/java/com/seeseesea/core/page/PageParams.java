package com.seeseesea.core.page;

/**
 * PageParams
 */
public class PageParams {

    private int current = 1;
    private int size = 10;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
