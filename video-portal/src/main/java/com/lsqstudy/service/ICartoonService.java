package com.lsqstudy.service;

import com.lsqstudy.domain.Cartoon;
import com.lsqstudy.vo.VideoVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 14:58
 */

public interface ICartoonService {

    //根据列表
    Page<Cartoon> findAll(VideoVo videoVo);

    Page<Cartoon> findByFilter(VideoVo videoVo);

    Cartoon findCartoonById(String id);

    Map<String,Object> search(VideoVo videoVo);

    Page<Cartoon> finBannerList(VideoVo videoVo);
}
