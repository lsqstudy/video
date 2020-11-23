package com.lsqstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayAddress {
    private  String  name;
    private  String  url;
    private  String  crawler_url;

}
