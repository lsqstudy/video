package com.lsqstudy.controller;

import com.lsqstudy.common.Constast;
import com.lsqstudy.domain.Cartoon;
import com.lsqstudy.domain.Movice;
import com.lsqstudy.domain.TVPlay;
import com.lsqstudy.domain.Variety;
import com.lsqstudy.service.ICartoonService;
import com.lsqstudy.service.IMoviceService;
import com.lsqstudy.service.ITVPlayService;
import com.lsqstudy.service.IVarietyService;
import com.lsqstudy.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-29 16:19
 */
@Controller
public class VideoController {

    @Autowired
    private ICartoonService cartoonService;
    @Autowired
    private IMoviceService moviceService;
    @Autowired
    private ITVPlayService tvPlayService;
    @Autowired
    private IVarietyService varietyService;

    //查询视频列表
    @GetMapping("/")
    public String showIndex(VideoVo videoVo, Model model) {
        videoVo.setLimit(Constast.INDEX_LIMIT);

        //查询分页数据
        Page<Cartoon> cartoonPage = cartoonService.findAll(videoVo);
        Page<Movice> movicePage = moviceService.findAll(videoVo);
        Page<TVPlay> tvPlayPage = tvPlayService.findAll(videoVo);
        Page<Variety> varietyPage = varietyService.findAll(videoVo);

        //将数据列表设置到Map中
        model.addAttribute("cartoons", cartoonPage.getContent());
        model.addAttribute("movices", movicePage.getContent());
        model.addAttribute("tvPlays", tvPlayPage.getContent());
        model.addAttribute("varieties", varietyPage.getContent());

        //banner数据
        List<Cartoon> banners = getBannerData();
        model.addAttribute("banners",banners);

        //获取热门搜索
        List<String> hotSearch = getHotSearch();
        model.addAttribute("hotSearch", hotSearch);

        //猎取猜你喜欢
        List<Cartoon> videos = gussYourLike();
        model.addAttribute("videos", videos);

        return "index";
    }

    //查询视频分类;根据类型、地区、年份查询
    @GetMapping("/category")
    public String getVideoByCategory(VideoVo videoVo,Model model) {
        //数据回显
        if (null!=videoVo.getType()&& videoVo.getType().size()>0){
            model.addAttribute("type", videoVo.getType().get(0));
        }

        //处理过滤条件
        if (null!=videoVo.getType()&& videoVo.getType().size()==0){
            videoVo.setType(null);
        }
        if (StringUtils.isEmpty(videoVo.getRegion())){
            videoVo.setRegion(null);
        }

        videoVo.setLimit(Constast.CATEGOR_LIMIT);

        //查询分页数据
        if (videoVo.getCategory() == 1) {
            Page<Cartoon> cartoonPage = cartoonService.findByFilter(videoVo);
            model.addAttribute("page", cartoonPage);
        } else if (videoVo.getCategory()==2) {
            Page<Movice> movicePage = moviceService.findByFilter(videoVo);
            model.addAttribute("page", movicePage);
        } else if (videoVo.getCategory() == 3) {
            Page<TVPlay> tvPlayPage = tvPlayService.findByFilter(videoVo);
            model.addAttribute("page", tvPlayPage);
        } else if (videoVo.getCategory() == 4) {
            Page<Variety> varietyPage = varietyService.findByFilter(videoVo);
            model.addAttribute("page", varietyPage);
        }

        model.addAttribute("videoVo", videoVo);

        //获取热门搜索
        List<String> hotSearch = getHotSearch();
        model.addAttribute("hotSearch", hotSearch);

        return "category";
    }

    //视频详情
    @GetMapping("/detail")
    public String videoDetail(VideoVo videoVo, Model model) {
        //查询分页数据
        if (videoVo.getCategory() == 1) {
            Cartoon cartoon = cartoonService.findCartoonById(videoVo.getId());
            model.addAttribute("video", cartoon);
        } else if (videoVo.getCategory() == 2) {
            Movice movice = moviceService.findMoviceById(videoVo.getId());
            model.addAttribute("video", movice);
        } else if (videoVo.getCategory() == 3) {
            TVPlay tvPlay = tvPlayService.findTVPlayById(videoVo.getId());
            model.addAttribute("video", tvPlay);
        } else if (videoVo.getCategory() == 4) {
            Variety variety = varietyService.findVarietyById(videoVo.getId());
            model.addAttribute("video", variety);
        }

        model.addAttribute("videoVo", videoVo);
        //获取热门搜索
        List<String> hotSearch = getHotSearch();
        model.addAttribute("hotSearch", hotSearch);

        //猎取猜你喜欢
        List<Cartoon> videos = gussYourLike();
        model.addAttribute("videos", videos);

        return "detail";
    }

    //播放
    //视频详情
    @GetMapping("/play")
    public String playVideo(VideoVo videoVo, Model model) {
        //数据回显
        model.addAttribute("videoVo", videoVo);

        //查询分页数据
        if (videoVo.getCategory() == 1) {
            Cartoon cartoon = cartoonService.findCartoonById(videoVo.getId());
            model.addAttribute("video", cartoon);
        } else if (videoVo.getCategory() == 2) {
            Movice movice = moviceService.findMoviceById(videoVo.getId());
            model.addAttribute("video", movice);
        } else if (videoVo.getCategory() == 3) {
            TVPlay tvPlay = tvPlayService.findTVPlayById(videoVo.getId());
            model.addAttribute("video", tvPlay);
        } else if (videoVo.getCategory() == 4) {
            Variety variety = varietyService.findVarietyById(videoVo.getId());
            model.addAttribute("video", variety);
        }

        //获取热门搜索
        List<String> hotSearch = getHotSearch();
        model.addAttribute("hotSearch", hotSearch);

        //猎取猜你喜欢
        List<Cartoon> videos = gussYourLike();
        model.addAttribute("videos", videos);

        return "play";
    }


    //banner数据
    public List<Cartoon> getBannerData(){
        VideoVo videoVo=new VideoVo();
        videoVo.setLimit(Constast.BANNER_LIMIT);
        //查询分页数据
        Page<Cartoon> cartoonPage = cartoonService.finBannerList(videoVo);
        Page<Movice> movicePage = moviceService.finBannerList(videoVo);
        Page<TVPlay> tvPlayPage = tvPlayService.finBannerList(videoVo);
        Page<Variety> varietyPage = varietyService.finBannerList(videoVo);

        List<Cartoon> list = tranferForCartoon(cartoonPage, movicePage, tvPlayPage, varietyPage);

        return list;
    }


    //搜索
    @GetMapping("/search")
    public String search(VideoVo videoVo, Model model) {
        videoVo.setLimit(Constast.SEARCH_lIMIT);
        //数据回显
        model.addAttribute("videoVo", videoVo);

        //查询数据
        Map<String, Object> cartoonMap = cartoonService.search(videoVo);
        Map<String, Object> moviceMap = moviceService.search(videoVo);
        Map<String, Object> tvPlayMap = tvPlayService.search(videoVo);
        Map<String, Object> varietyMap = varietyService.search(videoVo);

        model.addAttribute("cartoons", cartoonMap.get("list"));
        model.addAttribute("movices", moviceMap.get("list"));
        model.addAttribute("tvPlays", tvPlayMap.get("list"));
        model.addAttribute("varietys", varietyMap.get("list"));

        Integer cartoonPages = (Integer) cartoonMap.get("totalPage");
        Integer movicePages = (Integer) moviceMap.get("totalPage");
        Integer tvPlayPages = (Integer) tvPlayMap.get("totalPage");
        Integer varietyPages = (Integer) varietyMap.get("totalPage");

        //处理总页数
        Integer  totalPages= cartoonPages >movicePages ? cartoonPages : movicePages;
        totalPages = totalPages > tvPlayPages ? totalPages : tvPlayPages;
        totalPages = totalPages > varietyPages ? totalPages : varietyPages;
        model.addAttribute("totalPages", totalPages);

        //获取热门搜索
        List<String> hotSearch = getHotSearch();
        model.addAttribute("hotSearch", hotSearch);

        return "search";
    }


    //热门搜索
    private List<String> getHotSearch() {
        VideoVo videoVo=new VideoVo();
        videoVo.setLimit(Constast.HOST_SEARCH_LIMIT);

        //查询分页数据
        Page<Cartoon> cartoonPage = cartoonService.findAll(videoVo);
        Page<Movice> movicePage = moviceService.findAll(videoVo);
        Page<TVPlay> tvPlayPage = tvPlayService.findAll(videoVo);
        Page<Variety> varietyPage = varietyService.findAll(videoVo);
        //取出数据列表
        List<Cartoon> cartoons = cartoonPage.getContent();
        List<Movice> movices = movicePage.getContent();
        List<TVPlay> tvPlays = tvPlayPage.getContent();
        List<Variety> varieties = varietyPage.getContent();

        //将数据列表设置到Map中
        List<String> list = new ArrayList<>();
        for (Cartoon cartoon : cartoons) {
            list.add(cartoon.getTitle());
        }
        for (Movice movice : movices) {
            list.add(movice.getTitle());
        }
        for (TVPlay tvPlay : tvPlays) {
            list.add(tvPlay.getTitle());
        }
        for (Variety variety : varieties) {
            list.add(variety.getTitle());
        }

        return list;
    }

    //猜你喜欢
    private List<Cartoon> gussYourLike() {
        VideoVo videoVo=new VideoVo();
        videoVo.setPage(Constast.GUSS_YOUR_LIKE_PAGE);
        videoVo.setLimit(Constast.GUSS_YOUR_LIKE_LIMIT);

        //查询分页数据
        Page<Cartoon> cartoonPage = cartoonService.findAll(videoVo);
        Page<Movice> movicePage = moviceService.findAll(videoVo);
        Page<TVPlay> tvPlayPage = tvPlayService.findAll(videoVo);
        Page<Variety> varietyPage = varietyService.findAll(videoVo);

        List<Cartoon> list = tranferForCartoon(cartoonPage, movicePage, tvPlayPage, varietyPage);

        return list;
    }


    //将Movice、TVPlay、Variety的list转换成Cartoon类型的list
    private List<Cartoon> tranferForCartoon(Page<Cartoon> cartoonPage, Page<Movice> movicePage,Page<TVPlay> tvPlayPage,Page<Variety> varietyPage ) {
        //取出数据列表
        List<Cartoon> cartoons = cartoonPage.getContent();
        List<Movice> movices = movicePage.getContent();
        List<TVPlay> tvPlays = tvPlayPage.getContent();
        List<Variety> varieties = varietyPage.getContent();

        //将数据列表设置到Map中
        List<Cartoon> list = new ArrayList<>();
        list.addAll(cartoons);

        for (Movice movice : movices) {
            Cartoon cartoon = new Cartoon();
            BeanUtils.copyProperties(movice, cartoon);
            list.add(cartoon);
        }
        for (TVPlay tvPlay : tvPlays) {
            Cartoon cartoon = new Cartoon();
            BeanUtils.copyProperties(tvPlay, cartoon);
            list.add(cartoon);

        }
        for (Variety variety : varieties) {
            Cartoon cartoon = new Cartoon();
            BeanUtils.copyProperties(variety, cartoon);
            list.add(cartoon);
        }

        return list;
    }
}
