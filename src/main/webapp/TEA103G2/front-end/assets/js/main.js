(function($) {
    "use strict";

    /*--
		Header top Notification
    -----------------------------------*/
    function notificationClose() {
        $('.notification-close-btn').on('click', function() {
            $('.top-notification-bar').slideUp();
        });
    }
    notificationClose();

    /*--
		Header Sticky
    -----------------------------------*/
    $(window).on('scroll', function(event) {
        var scroll = $(window).scrollTop();
        if (scroll < 350) {
            $(".header-sticky").removeClass("sticky");
        } else {
            $(".header-sticky").addClass("sticky");
        }
    });

    /*--
        Bootstrap Dropdown Animation
	  -----------------------------------*/
    // Add slideDown animation to Bootstrap dropdown when expanding.
    $('.dropdown').on('show.bs.dropdown', function() {
        $(this).find('.dropdown-menu').first().stop(true, true).slideDown();
    });

    // Add slideUp animation to Bootstrap dropdown when collapsing.
    $('.dropdown').on('hide.bs.dropdown', function() {
        $(this).find('.dropdown-menu').first().stop(true, true).slideUp();
    });

    /*--
        Off Canvas Menu
	  -----------------------------------*/

    $('.mobile-menu-open').on('click', function() {
        $('.off-canvas-box').addClass('open')
        $('.menu-overlay').addClass('open')
    });

    $('.menu-close').on('click', function() {
        $('.off-canvas-box').removeClass('open')
        $('.menu-overlay').removeClass('open')
    });

    $('.menu-overlay').on('click', function() {
        $('.off-canvas-box').removeClass('open')
        $('.menu-overlay').removeClass('open')
    });

    /*Variables*/
    var $offCanvasNav = $('.canvas-menu'),
        $offCanvasNavSubMenu = $offCanvasNav.find('.sub-menu, .mega-sub-menu, .menu-item ');

    /*Add Toggle Button With Off Canvas Sub Menu*/
    $offCanvasNavSubMenu.parent().prepend('<span class="mobile-menu-expand"></span>');

    /*Close Off Canvas Sub Menu*/
    $offCanvasNavSubMenu.slideUp();

    /*Category Sub Menu Toggle*/
    $offCanvasNav.on('click', 'li a, li .mobile-menu-expand, li .menu-title', function(e) {
        var $this = $(this);
        if (($this.parent().attr('class').match(/\b(menu-item-has-children|has-children|has-sub-menu)\b/)) && ($this.attr('href') === '#' || $this.hasClass('mobile-menu-expand'))) {
            e.preventDefault();
            if ($this.siblings('ul:visible').length) {
                $this.parent('li').removeClass('active-expand');
                $this.siblings('ul').slideUp();
            } else {
                $this.parent('li').addClass('active-expand');
                $this.closest('li').siblings('li').find('ul:visible').slideUp();
                $this.closest('li').siblings('li').removeClass('active-expand');
                $this.siblings('ul').slideDown();
            }
        }
    });

    $(".sub-menu, .mega-sub-menu, .menu-item").parent("li").addClass("menu-item-has-children");
    $(".mega-sub-menu").parent("li").css("position", "static");

    /*--
		    Header Category Toggle
    -----------------------------------*/
    function categoryToggle() {
        $('.vertical-title').on('click', function() {
            $('.vertical-menu-content').slideToggle(500);
        });
    }
    categoryToggle();

    /*--
        Slider
    -----------------------------------*/
    $('.shop-by-category .title').on('click', function(event) {
        $('.shop-by-category .category-menu').slideToggle(500);
    });

    $('.menu-expand').each(function() {
        var $ul = $(this),
            $lis = $ul.find('.menu-item:gt(7)'),
            isExpanded = $ul.hasClass('expanded');
        $lis[isExpanded ? 'show' : 'hide']();

        if ($lis.length > 0) {
            $ul
                .append($('<li class="expand">' + (isExpanded ? '<a href="javascript:;">- Close Categories</a>' : '<a href="javascript:;">+ More Categories</a>') + '</li>')
                    .click(function(event) {
                        var isExpanded = $ul.hasClass('expanded');
                        event.preventDefault();
                        $(this).html(isExpanded ? '<a href="javascript:;">+ More Categories</a>' : '<a href="javascript:;">- Close Categories</a>');
                        $ul.toggleClass('expanded');
                        $lis.toggle(300);
                    }));
        }
    });

    /*--
        Slider
	  -----------------------------------*/
    var slider = new Swiper('.slider-active', {
        speed: 600,
        effect: "fade",
        loop: true,
        pagination: {
            el: '.slider-active .swiper-pagination',
            clickable: true,
        },
        // autoplay: {
        //     delay: 8000,
        // },
    });

    /*--
        Category
	  -----------------------------------*/
    var category = new Swiper('.category-active', {
        slidesPerView: 5,
        spaceBetween: 30,
        breakpoints: {
            0: {
                slidesPerView: 2,
            },
            576: {
                slidesPerView: 3,
            },
            768: {
                slidesPerView: 4,
            },
            992: {
                slidesPerView: 6,
            }
        }
    });

    /*--
        Product
	  -----------------------------------*/
    var product = new Swiper('.product-active', {
        slidesPerView: 4,
        spaceBetween: 30,
        loop: true,
        navigation: {
            nextEl: '.product-active .swiper-button-next',
            prevEl: '.product-active .swiper-button-prev',
        },
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 2,
            },
            768: {
                slidesPerView: 3,
            },
            992: {
                slidesPerView: 4,
            }
        }
    });

    /*--
        Product 2
	  -----------------------------------*/
    var product2 = new Swiper('.product-2-active .swiper-container', {
        slidesPerView: 4,
        spaceBetween: 30,
        loop: true,
        navigation: {
            nextEl: '.product-2-active .swiper-button-next',
            prevEl: '.product-2-active .swiper-button-prev',
        },
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 2,
            },
            768: {
                slidesPerView: 3,
            },
            992: {
                slidesPerView: 4,
            },
            1600: {
                slidesPerView: 5,
            }
        }
    });

    /*--
        Product 3
	  -----------------------------------*/
    var product3 = new Swiper('.product-3-active .swiper-container', {
        slidesPerView: 4,
        spaceBetween: 30,
        loop: true,
        navigation: {
            nextEl: '.product-3-active .swiper-button-next',
            prevEl: '.product-3-active .swiper-button-prev',
        },
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 2,
            },
            768: {
                slidesPerView: 3,
            },
            1200: {
                slidesPerView: 3,
            },
            1600: {
                slidesPerView: 5,
            }
        }
    });

    /*--
        Product 4
	  -----------------------------------*/
    var product4 = new Swiper('.product-4-active .swiper-container', {
        slidesPerView: 4,
        spaceBetween: 30,
        loop: true,
        navigation: {
            nextEl: '.product-4-active .swiper-button-next',
            prevEl: '.product-4-active .swiper-button-prev',
        },
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 2,
            },
            768: {
                slidesPerView: 3,
            },
            992: {
                slidesPerView: 4,
            },
            1300: {
                slidesPerView: 5,
            },
            1600: {
                slidesPerView: 6,
            }
        }
    });

    /*--
        Product 5
	  -----------------------------------*/
    var product5 = new Swiper('.product-5-active .swiper-container', {
        slidesPerView: 4,
        spaceBetween: 30,
        loop: true,
        navigation: {
            nextEl: '.product-5-active .swiper-button-next',
            prevEl: '.product-5-active .swiper-button-prev',
        },
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 2,
            },
            992: {
                slidesPerView: 3,
            },
            1600: {
                slidesPerView: 4,
            }
        }
    });

    /*--
        Testimonial
	  -----------------------------------*/
    var testimonial = new Swiper('.testimonial-active', {
        slidesPerView: 3,
        spaceBetween: 30,
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 1,
            },
            768: {
                slidesPerView: 2,
            },
            992: {
                slidesPerView: 3,
            }
        }
    });

    /*--
        Testimonial 2
	  -----------------------------------*/
    var testimonial2 = new Swiper('.testimonial-2-active', {
        slidesPerView: 3,
        spaceBetween: 30,
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 1,
            },
            768: {
                slidesPerView: 2,
            },
            1600: {
                slidesPerView: 3,
            }
        }
    });

    /*--
        Blog
	  -----------------------------------*/
    var blog = new Swiper('.blog-active', {
        slidesPerView: 3,
        spaceBetween: 30,
        navigation: {
            nextEl: '.blog-active .swiper-button-next',
            prevEl: '.blog-active .swiper-button-prev',
        },
        breakpoints: {
            0: {
                slidesPerView: 1,
            },
            576: {
                slidesPerView: 1,
            },
            768: {
                slidesPerView: 2,
            },
            992: {
                slidesPerView: 3,
            }
        }
    });

    /*--
        Shop Single
    -----------------------------------*/
    var galleryThumbs = new Swiper('.gallery-thumbs .swiper-container', {
        spaceBetween: 40,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
        navigation: {
            nextEl: '.gallery-thumbs .swiper-button-next',
            prevEl: '.gallery-thumbs .swiper-button-prev',
        },
        breakpoints: {
            0: {
                spaceBetween: 10,
            },
            576: {
                spaceBetween: 20,
            },
            768: {
                spaceBetween: 40,
            },
            992: {
                spaceBetween: 30,
            },
            1200: {
                spaceBetween: 40,
            },
        }
    });
    var galleryTop = new Swiper('.gallery-top .swiper-container', {
        spaceBetween: 10,
        thumbs: {
            swiper: galleryThumbs
        }
    });

    /*--
        Quick View
    -----------------------------------*/
    var quickViewThumbs = new Swiper('.quick-view-thumbs .swiper-container', {
        spaceBetween: 15,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
        navigation: {
            nextEl: '.quick-view-thumbs .swiper-button-next',
            prevEl: '.quick-view-thumbs .swiper-button-prev',
        },
    });
    var quickViewTop = new Swiper('.quick-view-top .swiper-container', {
        spaceBetween: 10,
        thumbs: {
            swiper: quickViewThumbs
        }
    });

    /*--
        Blog Gallery Active
    -----------------------------------*/
    var blog = new Swiper('.gallery-active .swiper-container', {
        slidesPerView: 1,
        spaceBetween: 0,
        loop: true,
        navigation: {
            nextEl: '.gallery-active .swiper-button-next',
            prevEl: '.gallery-active .swiper-button-prev',
        },
    });

    /*--
		    Magnific Popup Activation
    -----------------------------------*/
    $('a.btn-gallery').on('click', function(event) {
        event.preventDefault();

        var gallery = $(this).attr('href');

        $(gallery).magnificPopup({
            delegate: 'a',
            type: 'image',
            gallery: {
                enabled: true
            }
        }).magnificPopup('open');
    });

    /*--
        Countdown
    -----------------------------------*/
    function makeTimer($endDate, $this, $format) {
        var today = new Date();
        var BigDay = new Date($endDate),
            msPerDay = 24 * 60 * 60 * 1000,
            timeLeft = (BigDay.getTime() - today.getTime()),
            e_daysLeft = timeLeft / msPerDay,
            daysLeft = Math.floor(e_daysLeft),
            e_hrsLeft = (e_daysLeft - daysLeft) * 24,
            hrsLeft = Math.floor(e_hrsLeft),
            e_minsLeft = (e_hrsLeft - hrsLeft) * 60,
            minsLeft = Math.floor((e_hrsLeft - hrsLeft) * 60),
            e_secsLeft = (e_minsLeft - minsLeft) * 60,
            secsLeft = Math.floor((e_minsLeft - minsLeft) * 60);

        var yearsLeft = 0;
        var monthsLeft = 0
        var weeksLeft = 0;

        if ($format != 'short') {
            if (daysLeft > 365) {
                yearsLeft = Math.floor(daysLeft / 365);
                daysLeft = daysLeft % 365;
            }

            if (daysLeft > 30) {
                monthsLeft = Math.floor(daysLeft / 30);
                daysLeft = daysLeft % 30;
            }
            if (daysLeft > 7) {
                weeksLeft = Math.floor(daysLeft / 7);
                daysLeft = daysLeft % 7;
            }
        }

        var yearsLeft = yearsLeft < 10 ? "0" + yearsLeft : yearsLeft,
            monthsLeft = monthsLeft < 10 ? "0" + monthsLeft : monthsLeft,
            weeksLeft = weeksLeft < 10 ? "0" + weeksLeft : weeksLeft,
            daysLeft = daysLeft < 10 ? "0" + daysLeft : daysLeft,
            hrsLeft = hrsLeft < 10 ? "0" + hrsLeft : hrsLeft,
            minsLeft = minsLeft < 10 ? "0" + minsLeft : minsLeft,
            secsLeft = secsLeft < 10 ? "0" + secsLeft : secsLeft,
            yearsText = yearsLeft > 1 ? 'years' : 'year',
            monthsText = monthsLeft > 1 ? 'months' : 'month',
            weeksText = weeksLeft > 1 ? 'weeks' : 'week',
            daysText = daysLeft > 1 ? 'days' : 'day',
            hourText = hrsLeft > 1 ? 'hrs' : 'hr',
            minsText = minsLeft > 1 ? 'mins' : 'min',
            secText = secsLeft > 1 ? 'secs' : 'sec';

        var $markup = {
            wrapper: $this.find('.countdown__item'),
            year: $this.find('.yearsLeft'),
            month: $this.find('.monthsLeft'),
            week: $this.find('.weeksLeft'),
            day: $this.find('.daysLeft'),
            hour: $this.find('.hoursLeft'),
            minute: $this.find('.minsLeft'),
            second: $this.find('.secsLeft'),
            yearTxt: $this.find('.yearsText'),
            monthTxt: $this.find('.monthsText'),
            weekTxt: $this.find('.weeksText'),
            dayTxt: $this.find('.daysText'),
            hourTxt: $this.find('.hoursText'),
            minTxt: $this.find('.minsText'),
            secTxt: $this.find('.secsText')
        }

        var elNumber = $markup.wrapper.length;
        $this.addClass('item-' + elNumber);
        $($markup.year).html(yearsLeft);
        $($markup.yearTxt).html(yearsText);
        $($markup.month).html(monthsLeft);
        $($markup.monthTxt).html(monthsText);
        $($markup.week).html(weeksLeft);
        $($markup.weekTxt).html(weeksText);
        $($markup.day).html(daysLeft);
        $($markup.dayTxt).html(daysText);
        $($markup.hour).html(hrsLeft);
        $($markup.hourTxt).html(hourText);
        $($markup.minute).html(minsLeft);
        $($markup.minTxt).html(minsText);
        $($markup.second).html(secsLeft);
        $($markup.secTxt).html(secText);
    }

    $('.countdown').each(function() {
        var $this = $(this);
        var $endDate = $(this).data('countdown');
        var $format = $(this).data('format');
        setInterval(function() {
            makeTimer($endDate, $this, $format);
        }, 0);
    });

    /*--
        Product Color Active
    -----------------------------------*/
    $('.color-list li span').each(function() {
        var get_color = $(this).attr('data-color');
        $(this).css("background-color", get_color);
    });

    $('.color-select .color-label span').each(function() {
        var get_color = $(this).attr('data-color');
        $(this).css("background-color", get_color);
    });

    /*--
		    Single Product Zoom Activation
    -----------------------------------*/
    $('.zoom').zoom();

    /*--
        Nice Select Active
    -----------------------------------*/
    $('.custom-select').niceSelect();

    /*--
		    select2
    -----------------------------------*/
    $(".select2").select2({
        tags: true
    });

    /*--
		    Product Quantity Activation
    -----------------------------------*/
    $('.add').on('click', function() {
        // if ($(this).prev().val()) {
        //     $(this).prev().val(+$(this).prev().val() + 1);
        // }

        if ($(this).prev().val() < 20) {
            if ($(this).prev().val() < 20) $(this).prev().val(+$(this).prev().val() + 1);
        } else {
            alert("商品訂購數量最多20個");
        }
    });
    $('.sub').on('click', function() {
        if ($(this).next().val() > 1) {
            if ($(this).next().val() > 1) $(this).next().val(+$(this).next().val() - 1);
        } else {
            alert("商品訂購數量不能低於1個");
        }
    });

    /*--
		    Checkout Account Active
    -----------------------------------*/
    $('#account').on('click', function() {
        if ($('#account:checked').length > 0) {
            $('.checkout-account').slideDown();
        } else {
            $('.checkout-account').slideUp();
        }
    });


    /*--
        Checkout Shipping Active
    -----------------------------------*/
    $('#shipping').on('click', function() {
        if ($('#shipping:checked').length > 0) {
            $('.checkout-shipping').slideDown();
        } else {
            $('.checkout-shipping').slideUp();
        }
    });


    /*--
        Checkout Payment Active
    -----------------------------------*/
    var checked = $('.payment-radio input:checked')
    if (checked) {
        $(checked).siblings('.payment-details').slideDown(500);
    };
    $('.payment-radio input').on('change', function() {
        $('.payment-details').slideUp(500);
        $(this).siblings('.payment-details').slideToggle(500);
    });


    /*--
		    Price Range Activation
    -----------------------------------*/
    $("#slider-range").slider({
        range: true,
        min: 9,
        max: 36,
        values: [9, 36],
        slide: function(event, ui) {
            $("#amount").val("$" + ui.values[0] + ".00 - $" + ui.values[1] + ".00");
        }
    });
    $("#amount").val("$" + $("#slider-range").slider("values", 0) + ".00 - $" + $("#slider-range").slider("values", 1) + ".00");

    /*--
        Back to top Script
    -----------------------------------*/
    // Show or hide the sticky footer button
    $(window).on('scroll', function(event) {
        if ($(this).scrollTop() > 600) {
            $('.back-to-top').fadeIn(200)
        } else {
            $('.back-to-top').fadeOut(200)
        }
    });

    //Animate the scroll to yop
    $('.back-to-top').on('click', function(event) {
        event.preventDefault();

        $('html, body').animate({
            scrollTop: 0,
        }, 1500);
    });


<<<<<<< HEAD
})(jQuery);
=======
})(jQuery);

>>>>>>> master
