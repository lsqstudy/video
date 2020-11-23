package com.lsqstudy.bussiness.constast;

/**
 * 常量接口
 *
 */
public interface BusConstast {
    /**
     * 首页的分页，默认page，limit
     *
     */
    public static final Integer INDEX_PAGE=0;
    public static final Integer INDEX_LIMIT=4;
    /**
     * 热门搜索，默认page，limit
     *
     */
    public static final Integer HOST_SEARCH_PAGE=0;
    public static final Integer HOST_SEARCH_LIMIT=1;
    /**
     * 猜你喜欢，默认page，limit
     *
     */
    public static final Integer GUSS_YOUR_LIKE_PAGE=1;
    public static final Integer GUSS_YOUR_LIKE_LIMIT=2;

    /**
     * 条件过滤
     *
     */
    public static final Integer YEAR=2011;//年份查询时：更早

    /**
     * banner,默认page，limit
     */
    public static final Integer BANNER_PAGE =0 ;
    public static final Integer BANNER_LIMIT =20 ;
    public static final Integer IS_BANNER =1 ;//是否是Banner,1:是；0：否

}
