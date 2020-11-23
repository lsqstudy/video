package com.lsqstudy.service;

import com.lsqstudy.domain.Variety;
import com.lsqstudy.vo.VideoVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface IVarietyService {

    //根据列表
    Page<Variety> findAll(VideoVo videoVo);

    Page<Variety> findByFilter(VideoVo videoVo);

    Variety findVarietyById(String id);

    Map<String,Object> search(VideoVo videoVo);

    Page<Variety> finBannerList(VideoVo videoVo);
}
