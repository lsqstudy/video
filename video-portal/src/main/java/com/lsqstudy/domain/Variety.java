package com.lsqstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: LSQ
 * @Date: 2020-10-26 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "varietys")
public class Variety {
    @Id
    private String id;//主键
    private String title;//标题
    private String alex;//别名
    private String thumbnail;//缩略图
    @Field("is_banner")
    private Integer isBanner;//是否是Banner,1:是；0：否
    private String poster;//海报
    private String describe;//描述
    private Integer category;//一级类型:1.动漫；2、电影；3、电视剧；4；综艺；5.个人视频；7.其他视频；
    //二级类型:
    // 动漫：1.动漫电影；2、国产动漫；3、日本动漫；4:未分类动漫）
    // 电视剧：1.大陆剧;2.港台剧;3.日韩剧;4.欧美剧;5.新马泰,6.其他
    @Field("category_two")
    private Integer categoryTwo;
    //一、动漫：1.搞笑；2.经典；3.热血；4.催泪；5.治愈；6.猎奇；7.励志；8.战斗；9.后宫；10.机战；11.恋爱；12.百合；13.科幻；14.奇幻；15.推理；16.校园；17。运动；18.魔法；19.历史；20.伪娘；21.美少女；22.萝莉；23.亲子；24.青春；25.冒险；26.竞技；27.未分类
    //二、电影：1.喜剧；2.悲剧；3.爱情；4.动作；5；枪战；6.犯罪；7.惊悚；8.恐怖；9.悬疑;10.动画;11.家庭;12.奇幻;13.魔幻;14.科幻;15.战争;16.青春;17.剧情;18.未分类
    //三、电视剧：1.古装;2.言情;3.武侠;4.偶像;5.家庭;6.青春;7.都市;8.爱情;9.喜剧;10.战争;11.军旅;12.谍战;13.悬疑;14.罪案;15.穿越;16.宫廷;17.历史;18.神话;19.科幻;20.年代;21.农村;22.商战;23.剧情;24.奇幻;25.未分类
    //四、个人视频：1.MV；2.其他
    private List<String> type;
    private String region;//地区(如：1.国产、大陆；2.香港；3.英国；4.美国；5.韩国；6.泰国；7.日本;8.其他）
    private Integer year;//年份
    private List<String> actor;//主演
    private String status;//更新状态
    private String info;//简介
    private Integer area;//视频区，如①区，②区
    @Field("crawler_site")
    private String crawlerSite;
    @Field("crawler_url")
    private String crawlerUrl;
    @Field("current_crawler_url")
    private String currentCrawlerUrl;
    private Integer score;
    @Field("score_people_count")
    private Integer scorePeopleCount;
    @Field("play_num")
    private Integer playNum;
    @Field("update_time")
    private Date updateTime;//更新时间
    @Field("create_time")
    private Date createTime;//创建时间
    @Field("play_address")
    private List<List<PlayAddress>> playAddress;//视频地址源
    @Field("download_address")
    private List<List<DownloadAddress>> downloadAddress;//视频地址源

}
