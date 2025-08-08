package com.onedimension.pojo;

import lombok.Data;

@Data
public class PageParams {
    private Integer page = 1;
    private Integer pageSize = 10;
}
