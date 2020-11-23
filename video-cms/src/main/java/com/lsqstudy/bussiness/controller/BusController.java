package com.lsqstudy.bussiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-11-21 17:37
 */

@Controller
@RequestMapping("bussiness")
public class BusController {
    /**
     * 跳转动漫管理
     */
    @RequestMapping("/toCartoonManager.html")
    public String toCartoonManager() {

        return "bussiness/cartoon/cartoonManager";
    }
    /**
     * 跳转动漫管理
     */
    @RequestMapping("/toMoviceManager.html")
    public String toMoviceManager() {

        return "bussiness/movice/moviceManager";
    }
    /**
     * 跳转动漫管理
     */
    @RequestMapping("/toTVPlayManager.html")
    public String toTVPlayManager() {

        return "bussiness/tvPlay/tvPlayManager";
    }
    /**
     * 跳转动漫管理
     */
    @RequestMapping("/toVarietyManager.html")
    public String toVarietyManager() {

        return "bussiness/variety/varietyManager";
    }

}
