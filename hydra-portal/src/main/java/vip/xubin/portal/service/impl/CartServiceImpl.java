package vip.xubin.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.xubin.common.utils.CookieUtils;
import vip.xubin.common.utils.HttpClientUtil;
import vip.xubin.common.utils.HydraResult;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.pojo.TbItem;
import vip.xubin.portal.pojo.CartItem;
import vip.xubin.portal.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 許彬.
 * @creater 2016-08-26 23:03
 */
@Service
public class CartServiceImpl implements CartService {

    public static final String TT_CART = "TT_CART";
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;

    @Override
    public void cartAdd(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        boolean flag = true;

        List<CartItem> cartItems = getCartItemList(request);

        if (cartItems != null && cartItems.size() > 0) {

            for (CartItem cartItem : cartItems) {

                if (cartItem.getId() == itemId) {

                    cartItem.setNum(cartItem.getNum() + num);

                    CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(cartItems), true);

                    flag = false;
                }
            }

        }

        if (flag) {

            String itemString = null;

            try {
                itemString = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO + itemId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (itemString != null) {

                HydraResult hydraResult = HydraResult.formatToPojo(itemString, TbItem.class);

                if (hydraResult.getStatus() == 200) {

                    TbItem item = (TbItem) hydraResult.getData();

                    CartItem cartItem = new CartItem();

                    cartItem.setId(itemId);
                    cartItem.setNum(num);
                    cartItem.setTitle(item.getTitle());
                    cartItem.setPrice(item.getPrice());
                    cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);

                    cartItems.add(cartItem);

                    CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(cartItems), true);
                }

            }

        }

    }

    @Override
    public List<CartItem> getCart(HttpServletRequest request) {

        return getCartItemList(request);
    }

    @Override
    public HydraResult updateNum(HttpServletRequest request,HttpServletResponse response, Long itemId, Integer num) {

        List<CartItem> itemList = getCartItemList(request);

        for (CartItem cartItem : itemList) {

            if (cartItem.getId() == itemId) {
                cartItem.setNum(num);
                break;
            }

        }

        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(itemList), true);

        return HydraResult.ok();
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response, Long itemId) {

        List<CartItem> itemList = getCartItemList(request);

        for (int i = 0; i < itemList.size(); i++) {

            CartItem cartItem = itemList.get(i);

            if (cartItem.getId() == itemId) {

                itemList.remove(i);
                break;
            }

        }

        CookieUtils.setCookie(request, response, TT_CART, JsonUtils.objectToJson(itemList), true);
    }

    private List<CartItem> getCartItemList(HttpServletRequest request) {
        try {

            String tt_cart = CookieUtils.getCookieValue(request, TT_CART, true);

            if (StringUtils.isBlank(tt_cart)) {
                return new ArrayList<>();
            }

            List<CartItem> cartItems = JsonUtils.jsonToList(tt_cart, CartItem.class);

            return cartItems;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
