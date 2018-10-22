package wiwi.qinj;

import java.io.File;
import java.util.Date;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import wiwi.qinj.dao.CouponMapper;
import wiwi.qinj.domain.Coupon;
import wiwi.qinj.utils.IDGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Qinj2ApplicationTests {

    @Resource
    CouponMapper couponMapper;

    @Test
    public void couponCreate() {
        Coupon coupon = new Coupon();
        coupon.setCouponId(IDGenerator.nextId("COUP"));
        coupon.setCouponName("琴江小白兔热卖");
        coupon.setCreateTime(new Date());
        coupon.setDescription("反季节小白兔");
        coupon.setPicture("");
        coupon.setPrePrice(2000L);
        coupon.setRealPrice(1200L);
        coupon.setStatus("1");
        coupon.setTotalCount(500);
        couponMapper.createCoupon(coupon);
    }

    @Test
    public void pathTest() throws Exception {
        File file2 = ResourceUtils.getFile("classpath:static/coupon_pic/");
        System.out.println("--1>" + file2.isDirectory());
        System.out.println("--2>" + file2.exists());
        System.out.println("--21>" + file2.getPath());
        System.out.println("--22>" + file2.toString());
        System.out.println("--3>" + ResourceUtils.getFile("classpath:12.gif").exists());
    }

}
