package com.lsqstudy.service;

import com.lsqstudy.domain.TVPlay;
import com.lsqstudy.vo.VideoVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface ITVPlayService {

    //根据列表
    Page<TVPlay> findAll(VideoVo videoVo);

    Page<TVPlay> findByFilter(VideoVo videoVo);

    TVPlay findTVPlayById(String id);

    Map<String,Object> search(VideoVo videoVo);

    Page<TVPlay> finBannerList(VideoVo videoVo);
}
