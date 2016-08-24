package vip.xubin.portal.pojo;

import vip.xubin.pojo.TbItem;

/**
 * 商品展示POJO
 *
 * @author 許彬.
 * @creater 2016-08-24 22:48
 */

public class Item extends TbItem {

    public String[] getImages() {

        if (getImage() != null) {

            String[] split = getImage().split(",");

            return split;
        }

        return null;

    }
}
