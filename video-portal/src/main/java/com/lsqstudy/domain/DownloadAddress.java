package com.lsqstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-02 16:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadAddress {
    private  String  name;
    private  String  url;
}
