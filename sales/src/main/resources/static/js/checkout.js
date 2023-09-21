// load order total

document.addEventListener("DOMContentLoaded", () => {
    const prices = document.querySelectorAll('.product-infor-wrapper')
    var totalPrice = 0
    prices.forEach((price) => {
        const stPrice = parseFloat(price.querySelector('.product-price-text').textContent.substring(1))
        const quantity = parseInt(price.querySelector('.quantity-text').textContent.substring(5))
        totalPrice += quantity * stPrice
    })
    document.querySelector('.style-line-text-subtotal').textContent = "$" + totalPrice.toFixed(2)
    calculateOrderTotal()

    // bỏ boder sp cuối
    const orderProducts = document.querySelectorAll('.line-item-detail')
    orderProducts[orderProducts.length - 1].style.borderBottom = 'none'

    // hiện pt vận chuyển mặc định
    const shippingButton = document.querySelectorAll('.radion-button-label')
    shippingButton[0].style.border = '1px solid rgb(49, 161, 224)'
    shippingButton[0].querySelector('.radio-button-style').style.display = 'block'
})

// tính tiền tổng tiền bao gồm ship, VAT

function calculateOrderTotal() {
    var subTotal = parseFloat(document.querySelector('.style-line-text-subtotal').textContent.substring(1))
    subTotal += (subTotal * 10 / 100)
    if(document.querySelector('.shipping-type-summary').textContent === "Standard shipping") {
        subTotal += 0
    }else if(document.querySelector('.shipping-type-summary').textContent === "Express") {
        subTotal += 27.95
    }else if(document.querySelector('.shipping-type-summary').textContent === "Express Saver") {
        subTotal += 22.95
    }
    document.querySelector('.style-line-heading-order-total').textContent = "$" + subTotal.toFixed(2)
    document.querySelector('.primary-payment-text-total').textContent = "Total: $" + subTotal.toFixed(2)
}

// chọn phương thức giao hàng

const shippingButton = document.querySelectorAll('.radion-button-label')

shippingButton.forEach((button) => {
    button.addEventListener('click', () => {

        shippingButton.forEach((button) => {
            button.style.border = '1px solid rgb(224, 224, 224)'
            button.querySelector('.radio-button-style').style.display = 'none'
        })

        button.style.border = '1px solid rgb(49, 161, 224)'
        button.querySelector('.radio-button-style').style.display = 'block'
        const shippingType = button.querySelector('.shipping-type').textContent
        const shippingValue = button.querySelector('.shipping-value').textContent
        document.querySelector('.shipping-type-summary').textContent = shippingType
        document.querySelector('.shipping-value-summary').textContent = shippingValue.substring(1,shippingValue.length - 1)
        calculateOrderTotal()
    })
})

// chọn pt thanh toán

const paymentMethods = document.querySelectorAll('.payment-item-wrapper')

paymentMethods.forEach((method) => {
    method.addEventListener('click', () => {

        paymentMethods.forEach((method) => {
            method.style.borderColor = 'rgb(224, 224, 224)'
            method.querySelector('input[type="radio"]').checked = false
        })
        method.style.borderColor = 'rgb(0, 109, 183)'
        method.querySelector('input[type="radio"]').checked = true
    })
})