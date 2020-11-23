package com.lsqstudy.service;

import com.lsqstudy.domain.Movice;
import com.lsqstudy.vo.VideoVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface IMoviceService {

    //根据列表
    Page<Movice> findAll(VideoVo videoVo);

    Page<Movice> findByFilter(VideoVo videoVo);

    Movice findMoviceById(String id);

    Map<String,Object> search(VideoVo videoVo);

    Page<Movice> finBannerList(VideoVo videoVo);
}
