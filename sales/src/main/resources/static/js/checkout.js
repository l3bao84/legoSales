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
    if(shippingButton.length != 0) {
        shippingButton[0].style.border = '1px solid rgb(49, 161, 224)'
        shippingButton[0].querySelector('.radio-button-style').style.display = 'block'
    }
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

// chọn địa chỉ

const addressItem = document.querySelectorAll('.address-item')

if(addressItem !== null) {
    addressItem.forEach((item) => {
        item.addEventListener("click", () => {

            addressItem.forEach((item) => {
                item.querySelector('input[type="radio"]').checked = false
            })

            item.querySelector('input[type="radio"]').checked = true
        })
    })
}

// mở & đóng form chọn địa chỉ

const closeBtn = document.querySelector('.close-button-style')

if(closeBtn !== null) {
    closeBtn.addEventListener("click", () => {
        document.querySelector('.modal-style-container').style.display = 'none'
    })
}

const changeAddress = document.querySelector('.button-change')

if(changeAddress !== null) {
    changeAddress.addEventListener("click", () => {
        document.querySelector('.modal-style-container').style.display = 'flex'
    })
}

const selectBtn = document.querySelector('.select-address')

if(selectBtn !== null) {
    selectBtn.addEventListener("click", () => {
        addressItem.forEach((item) => {
            if(item.querySelector('input[type="radio"]').checked === true) {
                const selectedAddress = item.querySelector('.text-base').querySelector("span").textContent + ', ' + item.querySelector('.tb-bg').querySelector("span").textContent
                document.querySelector('.summary-style-text').querySelector("span").textContent = selectedAddress
                document.querySelector('.modal-style-container').style.display = 'none'
            }
        })
    })
}

function closeAside() {
    document.querySelector('.modal-style-container').style.display = 'none'
}

document.querySelector('.modal-window').addEventListener("click", (event) => {
    event.stopPropagation()
})

// mở form chỉnh sửa địa chỉ

const editSection = document.querySelector('.edit-section')
const editButtonSaved = document.querySelector('.button-edit')
const savedSection = document.querySelector('.saved-section')

if(editButtonSaved !== null) {
    editButtonSaved.addEventListener("click", () => {
        const address = document.querySelector('.summary-style-text').querySelector("span").textContent
        const addressParts = address.split(',')
        editSection.querySelector('#fullname').value = addressParts[0].trim()
        editSection.querySelector('#phone-number').value = addressParts[5].trim().slice(1, -1)
        editSection.querySelector('#city').value = addressParts[addressParts.length - 2].trim()
        editSection.querySelector('#address').value = addressParts[1].trim() + ', ' + addressParts[2].trim() + ', ' + addressParts[3].trim()

        if(savedSection !== null) {
            editSection.style.display = 'block'
            savedSection.style.display = 'none'
        }
    })

    if(editSection !== null) {
        const useThisAddress = editSection.querySelector('.button-address-submit')
        useThisAddress.addEventListener("click", (event) => {
            event.preventDefault()
            const editedAddress = editSection.querySelector('#fullname').value + ', ' + editSection.querySelector('#address').value + ', ' + editSection.querySelector('#city').value + ', (' + editSection.querySelector('#phone-number').value + ')'
            document.querySelector('.summary-style-text').querySelector("span").textContent = editedAddress  
            savedSection.style.display = 'block'
            editSection.style.display = 'none'
        })
    }
}

if(addressItem !== null) {
    addressItem.forEach((item) => {
        const editButton = item.querySelector('.edit-button')
        if(editButton !== null) {
            editButton.addEventListener("click", (event) => {

                event.stopPropagation()

                const address = item.querySelector('.tb-bg').querySelector("span").textContent
                const addressParts = address.split(',')

                editSection.querySelector('#fullname').value = item.querySelector('.tb-full-name').querySelector("span").textContent.trim()

                editSection.querySelector('#phone-number').value = addressParts[4].trim().slice(1, -1)
                editSection.querySelector('#city').value = addressParts[3].trim()
                editSection.querySelector('#address').value = addressParts[0].trim() + ', ' + addressParts[1].trim() + ', ' + addressParts[2].trim()
                
                editSection.style.display = 'block'
                savedSection.style.display = 'none'
                document.querySelector('.modal-style-container').style.display = 'none'
            }) 
        }
    })
}

// mở form thêm địa chỉ mới

const addAddress = document.querySelector('.add-new-address')

if(addAddress !== null) {
    addAddress.addEventListener("click", () => {
        editSection.style.display = 'block'
        editSection.querySelector('#fullname').value = ""
        editSection.querySelector('#phone-number').value = ""
        editSection.querySelector('#city').value = ""
        editSection.querySelector('#address').value = ""
        
        savedSection.style.display = 'none'
        closeAside()
    })
}

// thêm địa chỉ mới nếu chưa có

const emptySectionInput = document.querySelector('.empty-section-input')

if(emptySectionInput !== null) {
    const useThisAddress = emptySectionInput.querySelector('.button-address-submit')
    useThisAddress.addEventListener("click", (event) => {
        event.preventDefault()
        const emptySectionSaved = document.querySelector('.empty-section-saved')
        if(emptySectionSaved !== null) {
            if(emptySectionInput.querySelector('#fullname').value == "" || emptySectionInput.querySelector('#address').value == "" || emptySectionInput.querySelector('#city').value == "" || emptySectionInput.querySelector('#phone-number').value == "") {
                alert("Please fill in all the fields.")
            }else {
                emptySectionInput.style.display = 'none'
                const editedAddress = emptySectionInput.querySelector('#fullname').value + ', ' + emptySectionInput.querySelector('#address').value + ', ' + emptySectionInput.querySelector('#city').value + ', (' + emptySectionInput.querySelector('#phone-number').value + ')'
                emptySectionSaved.querySelector('.summary-style-text').querySelector("span").textContent = editedAddress  
                emptySectionSaved.style.display = 'block'
            }
        }
    })

    const emptySectionSaved = document.querySelector('.empty-section-saved')
    if(emptySectionSaved !== null) {
        const emptySectionEditButton = emptySectionSaved.querySelector('.button-edit')
        emptySectionEditButton.addEventListener("click", () => {
            const address = emptySectionSaved.querySelector('.summary-style-text').querySelector("span").textContent
            const addressParts = address.split(',')

            emptySectionInput.querySelector('#fullname').value = addressParts[0].trim()
            emptySectionInput.querySelector('#phone-number').value = addressParts[5].trim().slice(1, -1)
            emptySectionInput.querySelector('#city').value = addressParts[4].trim()
            emptySectionInput.querySelector('#address').value = addressParts[1].trim() + ', ' + addressParts[2].trim() + ', ' + addressParts[3].trim()
        
            emptySectionInput.style.display = 'block'
            emptySectionSaved.style.display = 'block'
        })
    }
}