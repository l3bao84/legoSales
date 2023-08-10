
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
    for (let i = 0; i < search.length; i++) {
        search[i].style.display = 'flex';
    }
    for (let i = 0; i < hiddenSearch.length; i++) {
        hiddenSearch[i].style.display = 'none';
    }
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    btnInMenu.forEach(element => {
        element.style.textDecoration = "";
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

const menu = document.querySelector('.menu')
const btnInMenu = menu.querySelectorAll("*")
const hiddenMenu = document.getElementsByClassName('hidden-menu')
const btnExit = document.querySelector('.btn-exit')
const logo = document.querySelector('.header-logo')
var body = document.body

btnInMenu.forEach(element => {
    element.addEventListener('click', () => {

        btnInMenu.forEach(item => {
            item.style.textDecoration = '';
        });

        openOverlay.style.display = 'block';
        for(let i = 0; i < hiddenMenu.length; i++) {
            hiddenMenu[i].style.display = 'block';
        }
        element.style.textDecoration = "underline";
        logo.style.position = 'relative'
        logo.style.zIndex = '4'
        body.classList.add('no-scroll')
    });
});


btnExit.addEventListener('click', () => {
    openOverlay.style.display = 'none'
    for(let i = 0; i < hiddenMenu.length; i++) {
        hiddenMenu[i].style.display = 'none'
    }
    btnInMenu.forEach(element => {
        element.style.textDecoration = "";
    })
    logo.style.positon = ''
    logo.style.zIndex = ''
    body.classList.remove('no-scroll')
})