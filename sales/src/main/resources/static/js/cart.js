
// Mở thanh search bar

const openOverlay = document.getElementById('myOverlay')
const search = document.getElementsByClassName('search')
const hiddenSearch = document.getElementsByClassName('hidden-search')

function showSearchBar() {
    openOverlay.style.display = 'block'
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'none';
    }

    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'flex';
    }
}

openOverlay.addEventListener('click', () => {
    openOverlay.style.display = 'none'
    openOverlay.style.zIndex = '2'
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'flex';
    }
    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'none';
    }
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    menuContent.forEach(element => {
        element.menu.style.textDecoration = "";
    })
    logo.style.positon = ''
    logo.style.zIndex = ''
    body.classList.remove('no-scroll')
})

// Real-time check email

const emailInput = document.getElementById('emailInput')
const emailStatus = document.querySelector('.email-sub-error-status')
const signal = document.querySelector('.email')

emailInput.addEventListener('input', function() {
    const email = emailInput.value;
    if (validateEmail(email)) {
        signal.style.borderColor = '#008537';
        signal.style.borderWidth = '2px'
        emailStatus.textContent = ''
    } else {
        signal.style.borderColor = '#d0021b';
        signal.style.borderWidth = '2px'
        emailStatus.textContent = 'Please enter a valid email address';
    }
  });
  
  function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  }

// Mở menu con

const hiddenMenu = document.getElementsByClassName('hidden-menu')
const btnExit = document.querySelector('.btn-exit')
const logo = document.querySelector('.header-logo')
var body = document.body

var menuContent = [
    {
        menu: document.querySelector('.shop'),
        submenu: document.querySelector('.content-1'),
    },
    {
        menu: document.querySelector('.dis'),
        submenu: document.querySelector('.content-2'),
    },
    {
        menu: document.querySelector('.help'),
        submenu: document.querySelector('.content-3'),
    },
  ];

menuContent.forEach(element => {
    element.menu.addEventListener('click', () => {

        menuContent.forEach(item => {
            item.menu.style.textDecoration = '';
            item.submenu.style.display = 'none'
        });

        openOverlay.style.display = 'block';
        for(let i = 0; i < hiddenMenu.length; i++) {
            hiddenMenu[i].style.display = 'block';
        }
        element.submenu.style.display = 'block'
        element.menu.style.textDecoration = "underline";
        logo.style.position = 'relative'
        logo.style.zIndex = '4'
        body.classList.add('no-scroll')
    });
})


btnExit.addEventListener('click', () => {
    openOverlay.style.display = 'none'
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    menuContent.forEach(element => {
        element.menu.style.textDecoration = "";
    })
    logo.style.positon = ''
    logo.style.zIndex = ''
    body.classList.remove('no-scroll')
})

const menuItems = document.querySelectorAll('.count')
const menuContentList = document.querySelector('.menu-content-list')

document.addEventListener("DOMContentLoaded", () => {
    if(menuItems.length >= 12) {
        menuContentList.style.columns = '2'
    }else {
        menuContentList.style.columns = '1'
    }
})

// chặn hành vi của button ẩn thanh tìm kiếm

const closeButton = document.querySelector('.hidden-search-close-btn')

closeButton.addEventListener('click', (event) => {
    openOverlay.style.display = 'none'
    openOverlay.style.zIndex = '2'
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'flex';
    }
    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'none';
    }
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    event.preventDefault()
})



// mở page account

const openAccount = document.querySelector('.openAccount')

if(openAccount !== null) {
    openAccount.addEventListener('click', () => {
        var newUrl = `/my-account`;
        window.location.href = newUrl
    })
}

// Next và Prev các sản phẩm được gợi ý

const products = document.querySelectorAll('.product-item')
const nextBtn = document.querySelector('.next')
const prevBtn = document.querySelector('.prev')

let currentIndex = 0;
const itemsPerPage = 4;

function showProducts(index) {

    products.forEach((product) => {
        product.classList.remove('active');
    });
  
    for (let i = index; i < index + itemsPerPage; i++) {
        if (products[i]) {
          products[i].classList.add('active');
        }
    }

    index == products.length - 4
    ? nextBtn.classList.add("hide")
    : nextBtn.classList.remove("hide");

    index == 0
    ? prevBtn.classList.add("hide")
    : prevBtn.classList.remove("hide");

}

prevBtn.addEventListener('click', () => {
    currentIndex -= itemsPerPage;
    if (currentIndex < 0) {
      currentIndex = 0;
    }
    showProducts(currentIndex);
});
  
nextBtn.addEventListener('click', () => {
    currentIndex += itemsPerPage;
    if (currentIndex >= products.length) {
      currentIndex = products.length - itemsPerPage;
    }
    showProducts(currentIndex);
});
  
showProducts(currentIndex);

// Nút tăng giảm số lượng

const minusButton = document.querySelectorAll('.modify-button-minus')
const plusButton = document.querySelectorAll('.modify-button-plus')

minusButton.forEach((button) => {
    button.addEventListener('click', () => {
        var currentValue = button.parentElement.getElementsByClassName("quantity-value")[0].value
        if(currentValue == 1) {
            button.querySelector('.minus').style.fill = "#E0E0E0"
            button.disabled = true;
            button.parentElement.querySelector('.modify-button-plus').disabled = false;
        }else {
            currentValue--;
            button.querySelector('.minus').style.fill = "black"
            button.disabled = false
            button.parentElement.querySelector('.add').style.fill = "black"
            button.parentElement.querySelector('.rect').style.fill = "black"
            button.parentElement.querySelector('.modify-button-plus').disabled = false;
            button.parentElement.querySelector('.quantity-value').value = currentValue--;
    
            calculateOrderPrice()
    
            if(button.parentElement.querySelector('.quantity-value').value == 1) {
                button.querySelector('.minus').style.fill = "#E0E0E0"
                button.disabled = true;
                button.parentElement.querySelector('.modify-button-plus').disabled = false;
            }
        }
    })
})

plusButton.forEach((button) => {
    button.addEventListener('click', () => {
        var currentValue = button.parentElement.getElementsByClassName("quantity-value")[0].value
        if(currentValue == 3) {
            button.querySelector('.add').style.fill = "#E0E0E0"
            button.querySelector('.rect').style.fill = "#E0E0E0"
            button.disabled = true;
        }else {
            currentValue++;
            button.parentElement.querySelector('.minus').style.fill = "black"
            button.querySelector('.add').style.fill = "black"
            button.querySelector('.rect').style.fill = "black"
            button.parentElement.querySelector('.modify-button-minus').disabled = false;
            button.parentElement.querySelector('.quantity-value').value = currentValue++;
    
            calculateOrderPrice()
    
            if(button.parentElement.querySelector('.quantity-value').value == 3) {
                button.querySelector('.add').style.fill = "#E0E0E0"
                button.querySelector('.rect').style.fill = "#E0E0E0"
                button.disabled = true;
            }
        }
    })
})

// hiển thị tổng tiền(+VAT)

function updateTotalPrice() {
    if(document.querySelector('.order-price') !== null) {
        const orderPrice = parseFloat(document.querySelector('.order-price').textContent.substring(1))
        document.querySelector('.total-value').textContent = "$" + (orderPrice + orderPrice * 10 / 100).toFixed(2)
    }
}

// load tiền tạm thời
// thêm border khi có nhiều sản phẩm
// số lượng sản phẩm

function calculateOrderPrice() {
    var totalPrice = 0
    const actionWrappers = document.querySelectorAll('.product-action')
    actionWrappers.forEach((action) => {
        const stPrice = parseFloat(action.querySelector('.st-price').textContent.substring(1))
        const quantity = parseInt(action.querySelector('.quantity-value').value)
        totalPrice += stPrice * quantity
    })
    if(document.querySelector('.order-price') !== null) {
        document.querySelector('.order-price').textContent = "$" + totalPrice.toFixed(2)
    }
    updateTotalPrice()
}

document.addEventListener("DOMContentLoaded", () => {
    
    calculateOrderPrice()

    if(document.querySelectorAll('.product-row-container').length > 1) {
        document.querySelectorAll('.product-row-container').forEach((container,index) => {
            container.classList.toggle('boder')
            if(index == document.querySelectorAll('.product-row-container').length - 1) {
                container.classList.toggle('boder')
            }
        })
        
    }
    if(document.querySelector('.cart-title') !== null && document.querySelector('.order-value-text-title') !== null) {
        document.querySelector('.cart-title').textContent = "My Bag (" + document.querySelectorAll('.product-row-container').length + ")"
        document.querySelector('.order-value-text-title').textContent = "Order value (" + document.querySelectorAll('.product-row-container').length + ") items"
    }
    
    const quantityValues = document.querySelectorAll('.quantity-value') 
    if(quantityValues !== null) {
        quantityValues.forEach((quantity) => {
            var wrapper = quantity.closest('.quantity-wrapper')
            var plusButton = wrapper.querySelector('.modify-button-plus')
            var minusButton = wrapper.querySelector('.modify-button-minus')
            if(parseInt(quantity.value) == 3) {
                plusButton.querySelector('.add').style.fill = "#E0E0E0"
                plusButton.querySelector('.rect').style.fill = "#E0E0E0"
                minusButton.querySelector('.minus').style.fill = "black"
                plusButton.disabled = true
                minusButton.disabled = false
            }else if(parseInt(quantity.value) == 1) { 
                minusButton.querySelector('.minus').style.fill = "#E0E0E0"
                plusButton.querySelector('.add').style.fill = "black"
                plusButton.querySelector('.rect').style.fill = "black"
                minusButton.disabled = true
                plusButton.disabled = false
            }else {
                plusButton.querySelector('.add').style.fill = "black"
                plusButton.querySelector('.rect').style.fill = "black"
                minusButton.querySelector('.minus').style.fill = "black"
                minusButton.disabled = false
                plusButton.disabled = false
            }
        })
    }

})

// Mở overlay đăng nhập hoặc đăng ký

const accountPanel = document.querySelector('.account-panel')
const offPanel = document.querySelector('.exit-account-modal')

document.addEventListener("DOMContentLoaded", () => {
    const account = document.querySelector('.openAccount')
    if(account !== null) {
        account.addEventListener('click', () => {
            var newUrl = `/my-account`;
            window.location.href = newUrl
        })
    }else {
        const openSignIn = document.querySelector('.sign-in')
        openSignIn.addEventListener('click', () => {
            accountPanel.style.display = 'flex'
            body.classList.add('no-scroll')
        })
    }
})

offPanel.addEventListener('click', () => {
    accountPanel.style.display = 'none'
    body.classList.remove('no-scroll')
})

function switchOffPanel() {
    accountPanel.style.display = 'none'
    body.classList.remove('no-scroll')
}

function preventEventPropagation(event) {
    event.stopPropagation();
}