
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

// Slide tự chuyển động

const slides = document.querySelectorAll('.slide');


let currentSlide = 0;

function showSlide() {
  slides.forEach((slide) => {
    slide.classList.remove('active');
  });

  slides[currentSlide].classList.add('active');
}

// nextBtn.addEventListener("click", () => {
//     currentSlide != slides.length - 1 ? currentSlide++ : undefined;
//     showSlide()
// })

// prevBtn.addEventListener('click', () => {
//     currentSlide != 0 ? currentSlide-- : undefined;
//     showSlide()
// })

//Tự động chuyển slide sau mỗi 5 giây
setInterval(() => {
    currentSlide = (currentSlide + 1) % slides.length;
    showSlide()
}, 5000);

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

// xử lý thông báo order thành công

const closeModal = document.querySelector('.close-button-style')
const continueShopping = document.querySelector('.continue-button')
const orderModal = document.querySelector('.modal-style-container')

if(closeModal !== null) {
    closeModal.addEventListener("click", () => {
        orderModal.style.display = 'none'
    })
}


if(continueShopping !== null) {
    continueShopping.addEventListener("click", () => {
        var homeUrl = `/home`
        window.location.href = homeUrl
    })
}

document.addEventListener("DOMContentLoaded", () => {
    var url = new URL(window.location.href);

    var payValue = url.searchParams.get("pay");
    var orderValue = url.searchParams.get("order")

    if (payValue === "success" || orderValue === "success") {
        orderModal.style.display = 'flex'
    }
})