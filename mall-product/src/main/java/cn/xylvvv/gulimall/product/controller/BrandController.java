package cn.xylvvv.gulimall.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.xylvvv.common.valid.AddGroup;
import cn.xylvvv.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xylvvv.gulimall.product.entity.BrandEntity;
import cn.xylvvv.gulimall.product.service.BrandService;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 18:47:43
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand){
//        if (result.hasErrors()) {
//            HashMap<String, String> map = new HashMap<>();
//            // 获取校验的错误结果
//            result.getFieldErrors().forEach((item) -> {
//                // FieldError获取错误提示
//                String message = item.getDefaultMessage();
//                // 获取错误的属性名称
//                String field = item.getField();
//                map.put(field, message);
//            });
//
//            return R.error(400, "提交的数据不合法").put("data", map);
//        }
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
