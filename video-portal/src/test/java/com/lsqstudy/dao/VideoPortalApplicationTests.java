package com.lsqstudy.dao;

import com.lsqstudy.domain.Cartoon;
import com.lsqstudy.service.ICartoonService;
import com.lsqstudy.vo.VideoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class VideoPortalApplicationTests {

	@Autowired
	private ICartoonDao cartoonDao;

	@Autowired
	private ICartoonService cartoonService;
	@Autowired
	private MongoTemplate mongoTemplate;


	//分页排序
	@Test
	void testFindAll() {
		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("update_time"));

		//设置分页条件
		Pageable pageable = PageRequest.of(0, 3, sort);
		Page<Cartoon> page = cartoonDao.findAll(pageable);
		List<Cartoon> cartoons = page.getContent();
		for (Cartoon cartoon : cartoons) {
			System.out.println("===============================");
			System.out.println(cartoon.getTitle()+cartoon.getUpdateTime());

		}
	}

	// //根据ID查询
	// @Test
	// void testindById(){
	// 	Optional<Cartoon> opt = cartoonDao.findById("5f7e13503eb6df2cde21979c");
	// 	System.out.println(opt.isPresent()?opt.get():null);
	// }
	//





	//根据title进行模糊查询
	@Test
	void testFindByTitleLike(){
		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("update_time"));
		//设置分页条件
		Pageable pageable = PageRequest.of(0, 3, sort);
		Page<Cartoon> page = cartoonDao.findByIsBanner(1,pageable);
		List<Cartoon> cartoons = page.getContent();
		for (Cartoon cartoon : cartoons) {
			System.out.println("===============================");
			System.out.println(cartoon.getTitle()+cartoon.getUpdateTime());
		}
		System.out.println("总条数据："+page.getTotalElements());
		System.out.println("总页数："+page.getTotalPages());
		System.out.println("当前页："+page.getNumber());
	}
	//
	// //根据演员进行模糊查询
	// @Test
	// void testfindByActorLike(){
	// 	//设置排序条件
	// 	Sort sort = Sort.by(Sort.Order.desc("update_time"));
	// 	//设置分页条件
	// 	Pageable pageable = PageRequest.of(0, 3, sort);
	// 	Page<Cartoon> page = cartoonDao.findByActorLike("榎木淳弥",pageable);
	// 	List<Cartoon> cartoons = page.getContent();
	//
	// 	for (Cartoon cartoon : cartoons) {
	// 		System.out.println(cartoon.getTitle()+"==>"+cartoon.getActor());
	// 	}
	//
	// }
	//
	// //根据类型进行模糊查询
	// @Test
	// void testfindByTypeLike(){
	// 	//设置排序条件
	// 	Sort sort = Sort.by(Sort.Order.desc("update_time"));
	// 	//设置分页条件
	// 	Pageable pageable = PageRequest.of(0, 3, sort);
	// 	Page<Cartoon> page = cartoonDao.findByTypeLike("奇幻",pageable);
	// 	List<Cartoon> cartoons = page.getContent();
	// 	for (Cartoon cartoon : cartoons) {
	// 		System.out.println(cartoon.getTitle()+"==>"+cartoon.getType());
	// 	}
	//
	// }
	//
	//
	// //根据类型、年代，地区进行查询
	// @Test
	// void testFindByTypeAndYearAndRegion(){
	// 	String type="动作";
	// 	Integer year=0;
	// 	String region="";
	// 	Integer currentPage = 3;
	// 	Integer limit = 30;
	//
	// 	Query query = new Query();
	// 	//设置起始数
	// 	query.skip((currentPage - 1) * limit);
	// 	//设置查询条数
	// 	query.limit(limit);
	//
	// 	//组装查询条件
	// 	Criteria criteria = new Criteria();
	// 	if (!StringUtils.isEmpty(type)) {
	// 		criteria.and("type").is(type);
	// 	}
	//
	// 	if (!StringUtils.isEmpty(region)) {
	// 		criteria.and("region").is(region);
	// 	}
	//
	// 	if (year!=0) {//按年份查询
	// 		criteria.and("year").is(year);
	// 	}else {//更早的年份
	// 		criteria.and("year").lt(2011);
	// 	}
	//
	// 	query.addCriteria(criteria);
	// 	query.with(Sort.by(Sort.Order.desc("update_time")));
	//
	// 	List<Cartoon> cartoons = mongoTemplate.find(query,Cartoon.class);
	// 	for (Cartoon cartoon : cartoons) {
	// 		System.out.println(cartoon.getTitle()+":"+cartoon.getType()+":"+cartoon.getYear());
	// 	}
	//
	// 	/**
	// 	 * 注意：查询偏移量(offset)和限制条数(limit)可能会直接影响最终查询文档的数量，因为这些值会传递到服务器，
	// 	 * 并可能限制服务器执行计数操作的范围和顺序。所以要使用没有分页数据的对象来查。
	// 	 * 所以问题的解决办法是把分页的offset和limit去掉即可
	// 	 */
	// 	BasicQuery basicQuery = new BasicQuery(query.getQueryObject().toJson());
	//
	// 	// 查询记录总数
	// 	int totalCount = (int) mongoTemplate.count(basicQuery, Cartoon.class);
	// 	// 数据总页数
	// 	int totalPage = totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1;
	//
	// 	// 设置记录总数和总页数
	// 	System.out.println("记录总数:"+totalCount);
	// 	System.out.println("总页数::"+totalPage);
	//
	// 	// System.out.println("总条数据："+page.getTotalElements());
	// 	// System.out.println("总页数："+page.getTotalPages());
	// 	// System.out.println("当前页："+page.getNumber());*/
	// }



	//根据ID查询
	@Test
	void testfindMath(){

		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("updateTime"));
		//处理分页条件
		Pageable pageable = PageRequest.of(0, 4, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("_id")//忽略属性
				.withNullHandler(ExampleMatcher.NullHandler.IGNORE)
				.withIgnoreCase(true)//忽略大小写
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //改变默认字符串匹配方式：模糊查询

		Cartoon cartoon=new Cartoon();
		cartoon.setTitle("第三季");

		Example<Cartoon> example = Example.of(cartoon,matcher);
		Page<Cartoon> page = cartoonDao.findAll(example, pageable);
		List<Cartoon> list = page.getContent();
		for (Cartoon cartoon1 : list) {
			System.out.println(cartoon1);
		}

	}

	@Test
	void testfindMath2(){
		VideoVo videoVo=new VideoVo();

		cartoonService.findAll(videoVo);

	}



}
