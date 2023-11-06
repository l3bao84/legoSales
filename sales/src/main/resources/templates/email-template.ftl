<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Template</title>
    <style>
        * {
            padding: 0;
            margin: 0;
            font-family: 'Cera Pro', sans-serif;
        }
        html {
            box-sizing: border-box;
            height: 100%;
        }
        .sidebar-wrap {
            border: 1px solid rgb(224, 224, 224);
        }
        .order-detail-accordian-title {
            width: 100%;
        }
        .order-detail-accordian-style-title {
            width: 100%;
        }
        .order-detail-accordian-title-content {
            width: 100%;
            max-width: 100rem;
            margin: 0px auto;
            padding: 1.25rem 1.25rem 0.9375rem;
            display: flex;
            flex-direction: row;
            -webkit-box-pack: justify;
            justify-content: space between;
            -webkit-box-align: center;
            align-items: center;
            overflow: hidden;
        }
        .order-detail-accordian-title-row {
            display: flex;
            flex-direction: row;
            -webkit-box-align: baseline;
            align-items: baseline;
        }
        .order-detail-accordian-title-row > h2 {
            margin: 0px;
            text-align: left;
            overflow-wrap: normal;
            font-size: 1.125rem;
            line-height: 1.75rem;
            color: rgb(44, 44, 44);
            /* font-size: 1.125rem;
            line-height: 1.75rem; */
            font-weight: 500;
            text-transform: inherit;
        }
        .order-detail-accordian-content {
            height: auto;
            transition: height 0.3s cubic-bezier(0.4, 0, 0.2, 1) 0s;
            overflow: visible;
        }
        .order-detail-accordian-content::before {
            content: "";
            height: 0px;
            display: block;
        }
        .order-detail-accordian-content::after {
            content: "";
            height: 0px;
            display: block;
        }
        .accordion-style-content {
            overflow: visible;
            height: auto;
            position: relative;
        }
        .accordion-style-content::before {
            content: "";
            height: 1px;
            display: block;
        }
        .accordion-style-content::after {
            content: "";
            height: 1px;
            display: block;
        }
        .animate-height-wrapper {
            height: auto;
            transition: height 0.3s cubic-bezier(0.4, 0, 0.2, 1) 0s;
            overflow: visible;
        }
        .animate-height-wrapper::before {
            content: "";
            height: 0px;
            display: block;
        }
        .animate-height-wrapper::after {
            content: "";
            height: 0px;
            display: block;
        }
        .line-item-wrapper {
            margin-bottom: 0px;
        }
        .line-item-section-header {
            display: flex;
            flex-flow: column wrap;
            width: 100%;
            background-color: rgb(255, 255, 255);
            list-style: none;
            padding: 1.25rem;
            border-top: 1px solid rgb(224, 224, 224);
        }
        .line-item-section-heading {
            font-size: 1rem;
            margin: 0px;
            color: rgb(0, 133, 55);
            /* font-size: 1rem; */
            line-height: 1.5625rem;
            font-weight: 500;
            text-transform: inherit;
        }
        .line-item-content-wrapper {
            margin-bottom: 0px;
            padding: 0px 1.25rem;
        }
        .line-item-product-row-container {
            border-bottom: none;
            -webkit-box-align: center;
            align-items: center;
            padding: 1.25rem 0px;
            position: relative;
            display: flex;
            flex-flow: wrap;
            -webkit-box-pack: justify;
            justify-content: space-between;
            /* border-bottom: 1px solid rgb(224, 224, 224);
            padding: 1.25rem 0px; */
        }
        .line-item-custom-wrapper {
            width: calc(100% - 2rem);
            display: flex;
            flex-direction: row;
            -webkit-box-pack: start;
            justify-content: flex-start;
            position: relative;
        }
        .line-item-product-image-wrapper {
            width: 6rem;
            margin-right: 2.5rem;
            height: 100%;
            text-align: center;
            flex-shrink: 0;
        }
        .line-item-product-image-wrapper > img {
            max-width: 100%;
            height: auto;
            /* -ms-interpolation-mode: bicubic; */
            display: inline-block;
            vertical-align: middle;
        }
        .line-item-container {
            width: 60%;
            display: flex;
            flex-direction: column;
            align-content: space-between;
        }
        .line-item-text {
            display: flex;
        }
        .line-item-style-text {
            margin: 0.625rem 0px;
            color: rgb(0, 0, 0);
            font-size: 1rem;
            line-height: 1.5625rem;
            font-weight: 500;
            text-transform: inherit;
        }
        .line-item-product-infor-wrapper {
            display: flex;
            flex-direction: row;
            padding-top: 10px;
            -webkit-box-align: center;
            align-items: center;
        }
        .media-query {
            display: block;
        }
        .product-style-wrapper {
            width: 100%;
            text-align: left;
        }
        .product-style-text {
            display: flex;
            color: inherit;
            font-size: 1.125rem;
            line-height: 1.75rem;
            font-weight: 700;
            text-transform: inherit;
        }
        .line-item-quantity-text {
            padding-left: 0.9375rem;
            margin: 0px;
            color: rgb(117, 117, 117);
            font-size: 0.75rem;
            line-height: 1.1875rem;
            font-weight: 400;
            text-transform: inherit;
        }
    </style>
</head>
<body>
    <div class="sidebar-wrap">
        <div>
            <div class="order-detail-accordian-title">
                <div class="order-detail-accordian-style-title">
                    <div class="order-detail-accordian-title-content">
                        <div class="order-detail-accordian-title-row">
                            <h2>Order Details</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="order-detail-accordian-content">
                <div class="accordion-style-content">
                    <div class="animate-height-wrapper">
                        <div class="line-item-wrapper">
                            <div class="line-item-section-header">
                                <h2 class="line-item-section-heading">
                                    <span>Available now</span>
                                </h2>
                            </div>
                            <div class="line-item-content-wrapper">
                                <div class="line-item-product-row-container">
                                    <#list orderDetails as order>
                                        <div class="line-item-custom-wrapper">
                                            <div class="line-item-product-image-wrapper">
                                                <#if order.getProduct().getImages()?has_content>
                                                    <#assign firstImage = order.getProduct().getImages()[0]>
                                                    <img src="${firstImage}" alt="">
                                                </#if>
                                            </div>
                                            <div class="line-item-container">
                                                <div class="line-item-text">
                                                    <h3 class="line-item-style-text">
                                                        <span>${order.getProduct().getProductName()}</span>
                                                    </h3>
                                                </div>
                                                <div class="line-item-product-infor-wrapper">
                                                    <div class="media-query">
                                                        <div class="product-style-wrapper">
                                                            <span class="product-style-text">$${order.getUnitPrice()}</span>
                                                        </div>
                                                    </div>
                                                    <p class="line-item-quantity-text">Qty: ${order.getQuantity()}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
