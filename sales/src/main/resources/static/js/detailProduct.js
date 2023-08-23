
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
const iconIntro = document.querySelector('.plus-minus-icon-intro')
const iconReview = document.querySelector('.plus-minus-icon-reviews')
const fills = document.querySelectorAll('.fill')

toggleButton.addEventListener('click', () => {
    iconIntro.classList.toggle('activeRotate')
    animate.classList.toggle('show')
})


toggleButtonRV.addEventListener('click', () => {
    iconReview.classList.toggle('activeRotate')
    animateRV.classList.toggle('show')
    fills.forEach((element) => {
        const fillValue = element.getAttribute('fill')
        element.style.width = fillValue + '%';
        if(fillValue == 0) {
            element.closest('button').style.opacity = '0.5'
            element.closest('button').style.fontStyle = 'italic'
            element.closest('button').style.cursor = 'no-drop'
        }
    })
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

// Check điều kiện nội dung review

const textArea = document.querySelector('.text-area-review > textarea')
const requiredText = document.querySelector('.check-required')
const textAreaStatus = document.querySelector('.text-area-status')

textArea.addEventListener('input', () => {
    if(textArea.value.length < 50) {
        requiredText.textContent = "You must write at least 50 characters for this field."
        textAreaStatus.style.display = 'block'
        textAreaStatus.style.backgroundColor = 'rgb(208, 2, 27)'
    }else {
        requiredText.textContent = ""
        textAreaStatus.style.backgroundColor = 'rgb(0, 133, 55)'
    }
})

// Chọn file ảnh review

const fileButton = document.querySelector('.choose-image-button')
const fileInput = document.querySelector('.image-review-upload')

fileButton.addEventListener('click', () => {
    fileInput.click();
})

// Vote sao

const stars = document.querySelectorAll('.star')

stars.forEach((star) => {
    star.addEventListener('mouseover', () => {
        const rating = star.getAttribute("data-rating")
        stars.forEach((s) => {
            s.style.color = '#d7d6d6'
        })
        for(let i = 1; i <= rating; i++) {
            stars[i - 1].style.color = '#ffd500'
        }
    })
})

// Hiển thị ảnh sau khi được chọn và xóa ảnh được chọn

const imagesFileInput = document.querySelector('.image-review-upload')
const imageWrapper = document.querySelector('.image-reviews')
// const removeBtn = document.querySelectorAll('.remove-image')

imagesFileInput.addEventListener('change', (event) => {
    const files = event.target.files
    for(const file of files) {
        const li = document.createElement('li');
        const img = document.createElement('img');
        const removeButton = document.createElement('button');
        
        img.src = URL.createObjectURL(file);
        removeButton.classList.add('remove-image');
        removeButton.innerHTML = `
            <svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" viewBox="0 0 17 17" aria-hidden="true" class="Icon__StyledSVG-lm07h6-0 kBQgfx" data-di-res-id="29d199db-3481e91" data-di-rand="1692711741192">
                <path d="M10.377 8.142l5.953-5.954-2.234-2.234-5.954 5.954L2.188-.046-.046 2.188l5.954 5.954-5.954 5.954 2.234 2.234 5.954-5.953 5.954 5.953 2.234-2.234z" fill="currentColor" fill-rule="evenodd"></path>
            </svg>
        `;
        li.classList.add('image-preview-container');
        li.appendChild(img);
        li.appendChild(removeButton);
        imageWrapper.appendChild(li);
    }
})

document.addEventListener('DOMContentLoaded', () => {
    const removeBtn = document.querySelectorAll('.remove-image');
    removeBtn.forEach(button => {
        button.addEventListener('click', () => {
            const listItem = button.closest('.image-preview-container');
            listItem.remove();
        });
    });
});