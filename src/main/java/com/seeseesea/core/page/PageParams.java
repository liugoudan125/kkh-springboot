package com.seeseesea.core.page;

import lombok.Data;

/**
 * PageParams
 */
@Data
public class PageParams {

    private int current = 1;
    private int pageSize = 10;

}
