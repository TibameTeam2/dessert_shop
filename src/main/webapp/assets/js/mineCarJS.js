$(window).on("load ", function() {

    count_total();
    sum_total();

    /*======================================= Plus =======================================*/
    $('button.add').on("click ", function(event) {
        let total = $(this).closest("tr ").find("span.total-span ");
        let quantity = $(this).closest("tr ").find('input.quantity-input').val();
        let unit_price = $(this).closest("tr ").find('span.unit-price').text();
        total.text(quantity * unit_price);
        count_total();
        sum_total();
    });


    /*======================================= Minus =======================================*/
    $('button.sub').click(function(event) {
        let total = $(this).closest("tr ").find("span.total-span ");
        let quantity = $(this).closest("tr ").find('input.quantity-input').val();
        let unit_price = $(this).closest("tr ").find('span.unit-price').text();
        total.text(quantity * unit_price);
        count_total();
        sum_total();
    });


    /*======================================= Delete =======================================*/


    $('button.remove').on("click ", function() {
        $(this).closest("tr ").remove();
        if ($(".td.Product-thumb ").length == 0) {
            $("span.count-price ").text(0);
        }
        count_total();
        sum_total();
    });



    /*======================================= 小計函式 =======================================*/
    function count_total() {
        let sum = 0;
        $("span.total-span ").each(function() {
            sum += parseInt($(this).text());
            $('span.count-price').text(sum);
        });
    }


    /*======================================= 總計函式 =======================================*/
    function sum_total() {

        let count_total = $('span.count-price').html();
        let coupon_price = $('span.coupon-price').html();

        $('span.sum-total').html(count_total - coupon_price);

    }



    /*================================== 優惠券LightBox並鎖住背景 ==================================*/
    // 開啟 Modal 彈跳視窗
    $("span.choose-coupon ").on("click ", function() {
        $("div.overlay ").fadeIn();
        $('body.body-scroll').attr("style", "overflow :hidden ");
    });

    // 關閉 Modal
    $("button.btn_modal_close ").on("click ", function() {
        $("div.overlay ").fadeOut();
        $('body.body-scroll').removeAttr("style", "overflow :hidden ");
    });

    $('button.btn_modal_check').on("click", function() {
        $("div.overlay ").fadeOut();
        $('body.body-scroll').removeAttr("style", "overflow :hidden ");
    });



    $('button.coupon-use').on("click", function() {
        $('div.coupon-non').remove();
        $('div.coupon-list-parent').toggle();
        let html = `<li>  1234567 </li>`
        $('ul.coupon-list').prepend(html);
    });



    /*======================================= MessageLightBox =======================================*/
    // 開啟 Modal 彈跳視窗
    $("img.message-btn").on("click ", function() {
        $("div.message-table").fadeIn();
        $('div.messages').toggle();
    });

    // 關閉 Modal
    $("button.message-close").on("click ", function() {
        $("div.message-table").fadeOut();
        $('div.messages').toggle();
    });



    /*================================= Function =============================================*/
    //新增的coupon
    function addCouponList(item) {


    }


});