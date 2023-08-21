
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

const openAccount = document.querySelector('.openAccount')
const accountPanel = document.querySelector('.account-panel')
const offPanel = document.querySelector('.exit-account-modal')

openAccount.addEventListener('click', () => {
    accountPanel.style.display = 'flex'
    body.classList.add('no-scroll')
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

// Nút tăng giảm số lượng


const minus = document.querySelector('.minus')
const add = document.querySelector('.add')
const rect = document.querySelector('.rect')
const minusButton = document.querySelector('.modify-button-minus')
const plusButton = document.querySelector('.modify-button-plus')

minusButton.addEventListener('click', () => {
    var currentValue = document.getElementsByClassName("quantity-value")[0].value
    if(currentValue == 1) {
        minus.style.fill = "#E0E0E0"
        minusButton.disabled = true;
        plusButton.disabled = false;
    }else {
        currentValue--;
        minus.style.fill = "black"
        add.style.fill = "black"
        rect.style.fill = "black"
        minusButton.disabled = false;
        plusButton.disabled = false;
        document.querySelector('.quantity-value').value = currentValue--;
        if(document.querySelector('.quantity-value').value == 1) {
            minus.style.fill = "#E0E0E0"
            minusButton.disabled = true;
            plusButton.disabled = false;
        }
    }
})

plusButton.addEventListener('click', () => {
    var currentValue = document.getElementsByClassName("quantity-value")[0].value
    if(currentValue == 5) {
        add.style.fill = "#E0E0E0"
        rect.style.fill = "#E0E0E0"
        plusButton.disabled = true;
        // minusButton.disabled = false;
    }else {
        currentValue++;
        minus.style.fill = "black"
        add.style.fill = "black"
        rect.style.fill = "black"
        minusButton.disabled = false;
        document.querySelector('.quantity-value').value = currentValue++;
        if(document.querySelector('.quantity-value').value == 5) {
            add.style.fill = "#E0E0E0"
            rect.style.fill = "#E0E0E0"
            plusButton.disabled = true;
            // minusButton.disabled = false;
        }
    }
})

// Mở introductions, descriptions & Reviews

const toggleButton = document.querySelector('.product-accordion-toggle-button')
const toggleButtonRV = document.querySelector('.product-accordion-toggle-button-reviews')
const animate = document.querySelector('.animate-height-wrapper')
const animateRV = document.querySelector('.animate-height-wrapper-reviews')
const icon = document.querySelector('.plus-minus-icon')

toggleButton.addEventListener('click', () => {
    icon.style.setProperty("--after-transform", "translate(-50%, -50%) rotate(-360deg)")
    icon.style.setProperty("--before-transform", "translate(-50%, -50%) rotate(-90deg)")
    if(animate.style.display == 'none') {
        animate.style.display = 'block'
    }else {
        animate.style.display = 'none'
    }
})

toggleButtonRV.addEventListener('click', () => {
    icon.style.setProperty("--after-transform", "translate(-50%, -50%) rotate(-360deg)")
    icon.style.setProperty("--before-transform", "translate(-50%, -50%) rotate(-90deg)")
    if(animateRV.style.display == 'none') {
        animateRV.style.display = 'block'
    }else {
        animateRV.style.display = 'none'
    }
})

// Chọn ảnh từ gallery thumbnail

const images = document.querySelectorAll('.gallery-thumbnails-img')
const imgSource = document.querySelector('.media-wrapper-img')
const counter = document.querySelector('.product-counter-galley')
const nextDetailBtn = document.querySelector('.control-button-next')
const prevDetailBtn = document.querySelector('.control-button-prev')

let currentImg = 0
images.forEach((img,index) => {
    img.addEventListener('click', () => {
        currentImg = index
        if(currentImg == 0) {
            prevDetailBtn.style.pointerEvents = 'none'
            prevDetailBtn.style.backgroundColor = '#a9a9a9'
        }else {
            prevDetailBtn.style.pointerEvents = 'auto'
            prevDetailBtn.style.backgroundColor = '#000000ab'
        }
        if(currentImg == images.length - 1) {
            nextDetailBtn.style.pointerEvents = 'none'
            nextDetailBtn.style.backgroundColor = '#a9a9a9'
        }else {
            nextDetailBtn.style.pointerEvents = 'auto'
            nextDetailBtn.style.backgroundColor = '#000000ab'
        }
        showImage(currentImg)
        counter.textContent = (currentImg + 1) + '/' + images.length
    })
})

function showImage(index) {
    imgSource.src = images[index].src
}

prevDetailBtn.addEventListener('click', () => {
    currentImg--;
    counter.textContent = (currentImg + 1) + '/' + images.length
    if(currentImg == 0) {
        prevDetailBtn.style.pointerEvents = 'none'
        prevDetailBtn.style.backgroundColor = '#a9a9a9'
    }else {
        prevDetailBtn.style.pointerEvents = 'auto'
        prevDetailBtn.style.backgroundColor = '#000000ab'
        nextDetailBtn.style.pointerEvents = 'auto'
        nextDetailBtn.style.backgroundColor = '#000000ab'
    }
    showImage(currentImg);
})

nextDetailBtn.addEventListener('click', () => {
    currentImg++;
    counter.textContent = (currentImg + 1) + '/' + images.length
    if(currentImg == images.length - 1) {
        nextDetailBtn.style.pointerEvents = 'none'
        nextDetailBtn.style.backgroundColor = '#a9a9a9'
    }else {
        prevDetailBtn.style.pointerEvents = 'auto'
        prevDetailBtn.style.backgroundColor = '#000000ab'
        nextDetailBtn.style.pointerEvents = 'auto'
        nextDetailBtn.style.backgroundColor = '#000000ab'
    }
    showImage(currentImg)
})