package cn.xylvvv.gulimall.product.service.impl;

import cn.xylvvv.common.constant.ProductConstant;
import cn.xylvvv.common.to.es.SkuEsModel;
import cn.xylvvv.common.utils.R;
import cn.xylvvv.gulimall.product.entity.*;
import cn.xylvvv.gulimall.product.feign.SearchFeignService;
import cn.xylvvv.gulimall.product.feign.WareFeignService;
import cn.xylvvv.gulimall.product.service.*;
import cn.xylvvv.gulimall.product.vo.SkuHasStockVo;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.common.utils.Query;

import cn.xylvvv.gulimall.product.dao.SpuInfoDao;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductAttrValueService attrValueService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {

        // 1. 查出当前spuId对应的所有sku信息、品牌名称
        List<SkuInfoEntity> skuInfoEntities = skuInfoService.getSkusBySpuId(spuId);
        List<Long> skuIdList = skuInfoEntities.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

        // 4. 查询当前sku所有可以用来被检索的规格属性
        List<ProductAttrValueEntity> baseAttrs = attrValueService.baseAttrListforspu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(attr -> attr.getAttrId()).collect(Collectors.toList());

        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);

        Set<Long> idSet = new HashSet<>(searchAttrIds);

        List<SkuEsModel.Attrs> attrsList = baseAttrs
                .stream()
                .filter(item -> idSet.contains(item.getAttrId()))
                .map(item -> {
                    SkuEsModel.Attrs attrs1 = new SkuEsModel.Attrs();
                    BeanUtils.copyProperties(item, attrs1);
                    return attrs1;
                })
                .collect(Collectors.toList());

        // 1. 远程调用查询库存服务是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            R skusHasStock = wareFeignService.getSkuHasStock(skuIdList);
            TypeReference<List<SkuHasStockVo>> typeReference = new TypeReference<>() {};
            stockMap = skusHasStock.getData(typeReference).stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));
        } catch (Exception e) {
            log.error("库存服务查询异常：原因{}", e);
        }

        // 2. 封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> upProducts = skuInfoEntities.stream().map(sku -> {
            // 组装需要的数据
            SkuEsModel model = new SkuEsModel();
            BeanUtils.copyProperties(sku, model);

            model.setSkuPrice(sku.getPrice());
            model.setSkuImg(sku.getSkuDefaultImg());

            // 2. 热度评分hotScore，默认先为0
            model.setHotScore(0L);

            // 设置库存信息
            if (finalStockMap == null) {
                model.setHasStock(true);
            } else {
                model.setHasStock(finalStockMap.get(sku.getSkuId()));
            }

            // 3. 查询品牌和分类的名称
            BrandEntity brand = brandService.getById(model.getBrandId());
            model.setBrandName(brand.getName());
            model.setBrandImg(brand.getLogo());

            CategoryEntity category = categoryService.getById(model.getCatalogId());
            model.setCatalogName(category.getName());

            // 设置检索属性
            model.setAttrs(attrsList);

            return model;
        }).collect(Collectors.toList());

        // 5. 将数据发送给es进行保存；mall-search
        R r = searchFeignService.productStatusUp(upProducts);
        if (r.getCode() == 0) {
            // 远程调用成功
            // 6、修改当前spu的状态
            this.baseMapper.updaSpuStatus(spuId, ProductConstant.ProductStatusEnum.SPU_UP.getCode());
        } else {
            // 远程调用失败
            // TODO 7、重复调用？接口幂等性:重试机制
        }
    }

}