package com.bigdata.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigdata.dao.DealerDao;
import com.bigdata.dao.DealerMonthSalesDao;
import com.bigdata.model.Dealer;
import com.bigdata.model.DealerMonthSales;
import com.bigdata.model.ProvinceDealer;
import com.bigdata.service.DealerService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/12/24 15:21
 * UpdateTime 2017/12/24 15:21
 */
@Service("dealerService")
public class DealerServiceImpl implements DealerService {

    @Autowired
    private DealerDao dealerDao;

    @Autowired
    private DealerMonthSalesDao dealerMonthSalesDao;

    /**
     * 经销商地图接口
     *
     * @return
     */
    @Override
    public JSONObject getAllDealer() {

        try {
            List<Dealer> baseInfo = dealerDao.findAll();
            List<Dealer> shopInfo = dealerDao.findAllShop();

            JSONObject map = new JSONObject();
            JSONArray datas = new JSONArray();
            shopInfo.sort(Comparator.comparing(Dealer::getShopNum).reversed());

            shopInfo.forEach(shop -> {
                List<Dealer> tmp = baseInfo.stream().filter(base -> StringUtils.equals(shop.getPhone(), base.getPhone())).collect(Collectors.toList());
                if (tmp.size() > 0) {
                    Dealer dealer = tmp.get(0);
                    // 经销商数据data
                    JSONObject data = new JSONObject();
                    data.put("name", dealer.getCity());
                    data.put("value", shop.getShopNum());
                    datas.add(data);

                    // 经销商地图map
                    List<BigDecimal> location = new ArrayList<>();
                    location.add(dealer.getLa());
                    location.add(dealer.getLo());
                    map.put(dealer.getCity(), location);
                }
            });

            JSONObject result = new JSONObject();
            result.put("map", map);
            result.put("datas", datas);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 柱状图-省份
     *
     * @return
     */
    @Override
    public JSONObject getDealerShopByProvince() {

        try {
            // 将经销商的省份信息放入对象中
            List<Dealer> baseInfo = dealerDao.findAll();
            List<Dealer> shopInfo = dealerDao.findAllShop();
            shopInfo.forEach(shop -> {
                List<Dealer> tmp = baseInfo.stream().filter(base -> StringUtils.equals(shop.getPhone(), base.getPhone())).collect(Collectors.toList());
                if (tmp.size() > 0) {
                    shop.setProvince(tmp.get(0).getProvince());
                }
            });

            // 计算出每个经销商的门店数、红星门店数、经销商数量
            List<ProvinceDealer> pds = new ArrayList<>();
            shopInfo.forEach(dealer -> {
                ProvinceDealer pd = new ProvinceDealer();
                int shopNum = shopInfo.stream().filter(dl -> StringUtils.equals(dealer.getProvince(), dl.getProvince())).mapToInt(Dealer::getShopNum).sum();
                int redStarShopNum = shopInfo.stream().filter(dl -> StringUtils.equals(dealer.getProvince(), dl.getProvince())).mapToInt(Dealer::getRedStarShopNum).sum();
                int dealerNum = shopInfo.stream().filter(dl -> StringUtils.equals(dealer.getProvince(), dl.getProvince())).collect(Collectors.toList()).size();
                pd.setProvince(dealer.getProvince());
                pd.setShopNum(shopNum);
                pd.setRedStarShopNum(redStarShopNum);
                pd.setDealerNum(dealerNum);
                if (pds.stream().filter(a -> StringUtils.equals(a.getProvince(), dealer.getProvince())).collect(Collectors.toList()).size() < 1) {
                    pds.add(pd);
                }
            });

            // 按照门店数排序（从大到小）
            pds.sort(Comparator.comparing(ProvinceDealer::getShopNum).reversed());

            // 拼接返回数据格式
            JSONArray shopNumArray = new JSONArray();
            JSONArray redStarArray = new JSONArray();
            JSONArray provinceArray = new JSONArray();
            JSONArray dealerArray = new JSONArray();
            pds.forEach(p -> {
                shopNumArray.add(p.getShopNum());
                redStarArray.add(p.getRedStarShopNum());
                provinceArray.add(p.getProvince());
                dealerArray.add(p.getDealerNum());
            });
            JSONObject res = new JSONObject();
            res.put("shopNum", shopNumArray);
            res.put("redStar", redStarArray);
            res.put("province", provinceArray);
            res.put("dealer", dealerArray);

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 柱状图-城市
     *
     * @return
     */
    @Override
    public JSONObject getDealerShopByCity() {

        try {
            // 将经销商的城市信息放入对象中
            List<Dealer> baseInfo = dealerDao.findAll();
            List<Dealer> shopInfo = dealerDao.findAllShop();
            shopInfo.forEach(shop -> {
                List<Dealer> tmp = baseInfo.stream().filter(base -> StringUtils.equals(shop.getPhone(), base.getPhone())).collect(Collectors.toList());
                if (tmp.size() > 0) {
                    shop.setCity(tmp.get(0).getCity());
                }
            });

            // 计算出每个经销商的门店数和红星门店数
            List<ProvinceDealer> pds = new ArrayList<>();
            shopInfo.forEach(dealer -> {
                ProvinceDealer pd = new ProvinceDealer();
                int shopNum = shopInfo.stream().filter(dl -> StringUtils.equals(dealer.getCity(), dl.getCity())).mapToInt(Dealer::getShopNum).sum();
                int redStarShopNum = shopInfo.stream().filter(dl -> StringUtils.equals(dealer.getCity(), dl.getCity())).mapToInt(Dealer::getRedStarShopNum).sum();
                pd.setCity(dealer.getCity());
                pd.setShopNum(shopNum);
                pd.setRedStarShopNum(redStarShopNum);
                if (pds.stream().filter(a -> StringUtils.equals(a.getCity(), dealer.getCity())).collect(Collectors.toList()).size() < 1) {
                    pds.add(pd);
                }
            });

            // 按照门店数排序（从大到小）
            pds.sort(Comparator.comparing(ProvinceDealer::getShopNum).reversed());

            // 拼接返回数据格式
            JSONArray shopNumArray = new JSONArray();
            JSONArray redStarArray = new JSONArray();
            JSONArray provinceArray = new JSONArray();
            pds.forEach(p -> {
                shopNumArray.add(p.getShopNum());
                redStarArray.add(p.getRedStarShopNum());
                provinceArray.add(p.getCity());
            });
            JSONObject res = new JSONObject();
            res.put("shopNum", shopNumArray);
            res.put("redStar", redStarArray);
            res.put("province", provinceArray);

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 折线图-经销商的单元销售总金额
     *
     * @return
     */
    @Override
    public JSONArray getAmountByYearAndMonth() {

        try {
            List<DealerMonthSales> dealerMonthSalesList = dealerMonthSalesDao.findAmountByYearAndMonth();
            List<DealerMonthSales> allYears = dealerMonthSalesDao.findAllYears();
            if (dealerMonthSalesList.size() < 0 || allYears.size() < 0) {
                return null;
            }

            JSONArray result = new JSONArray();
            // 将不同年份的数据分类，将金额单位转为元
            allYears.forEach(year -> {
                List<DealerMonthSales> list = dealerMonthSalesList.stream()
                        .filter(sale -> sale.getYear() == year.getYear())
                        .map(sale -> sale.setAmount(sale.getAmount().divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP)))
                        .collect(Collectors.toList());
                if (list.size() > 0) {
                    JSONObject tmp = new JSONObject();
                    JSONArray tmpArr = new JSONArray();
                    tmp.put("year", year.getYear() + "年");
                    // 按照月份排序
                    list.sort(Comparator.comparing(DealerMonthSales::getMonth));
                    list.forEach(sale -> tmpArr.add(sale.getAmount()));
                    tmp.put("amount", tmpArr);
                    result.add(tmp);
                }
            });

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
