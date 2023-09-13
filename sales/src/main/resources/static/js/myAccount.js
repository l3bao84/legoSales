
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

const openAccount = document.querySelector('.openAccount')

// mở page account

openAccount.addEventListener('click', () => {
    var newUrl = `/my-account`;
    window.location.href = newUrl
})

// đổi màu tùy vào side menu

document.addEventListener("DOMContentLoaded", () => {
    const url = window.location.href
    const sideItems = document.querySelectorAll('.side-menu-bar-item')
    if(url.endsWith("/my-account")) {
        document.querySelector('.account-overview').style.display = ""
        sideItems.forEach((item) => {
            const span = item.querySelector('.side-menu-bar-item-label')
            if(span !== null) {
                if(span.textContent === "Account Overview") {
                    item.style.backgroundColor = "rgb(0, 109, 183)"
                    item.querySelector("a").style.cursor = "pointer"
                    item.querySelector("span").style.color = "rgb(255, 255, 255)"
                    item.querySelector("span").style.textDecoration = "underline"
                }
            }
        })
    }else if(url.endsWith("/my-account/orders")) {
        document.querySelector('.orders').style.display = ""
        sideItems.forEach((item) => {
            const span = item.querySelector('.side-menu-bar-item-label')
            if(span !== null) {
                if(span.textContent === "My Orders") {
                    item.style.backgroundColor = "rgb(0, 109, 183)"
                    item.querySelector("a").style.cursor = "pointer"
                    item.querySelector("span").style.color = "rgb(255, 255, 255)"
                    item.querySelector("span").style.textDecoration = "underline"
                }
            }
        })
    }else if(url.endsWith("/my-account/personal")) {
        document.querySelector('.personal').style.display = ""
        sideItems.forEach((item) => {
            const span = item.querySelector('.side-menu-bar-item-label')
            if(span !== null) {
                if(span.textContent === "Personal & Address Details") {
                    item.style.backgroundColor = "rgb(0, 109, 183)"
                    item.querySelector("a").style.cursor = "pointer"
                    item.querySelector("span").style.color = "rgb(255, 255, 255)"
                    item.querySelector("span").style.textDecoration = "underline"
                }
            }
        })
    }else if(url.endsWith("/my-account/wishlist")) {
        document.querySelector('.wishlist-menu').style.display = ""
        sideItems.forEach((item) => {
            const span = item.querySelector('.side-menu-bar-item-label')
            if(span !== null) {
                if(span.textContent === "Wish list") {
                    item.style.backgroundColor = "rgb(0, 109, 183)"
                    item.querySelector("a").style.cursor = "pointer"
                    item.querySelector("span").style.color = "rgb(255, 255, 255)"
                    item.querySelector("span").style.textDecoration = "underline"
                }
            }
        })
    }
})

// focus input field

const inputFields = document.querySelectorAll('.input-field')
const addressForm = document.querySelector('.address-form-input')

inputFields.forEach((input) => {
    input.addEventListener('click', () => {
        input.parentElement.querySelector('.input-title').classList.toggle('active')
    })
})

// mở/đóng form thêm địa chỉ

const openAddressForm = document.querySelector('.add-address-button')
const cancelButtonHeader = document.querySelector('.cancel-button-header')
const cancelButton = document.querySelector('.cancel-button')
const addressFormContainer = document.querySelector('.address-card-container')

openAddressForm.addEventListener('click', () => {
    addressFormContainer.style.display = 'flex'
    openAddressForm.style.display = 'none'
})

cancelButtonHeader.addEventListener('click', () => {
    addressFormContainer.style.display = 'none'
    openAddressForm.style.display = 'flex'
})

cancelButton.addEventListener('click', (event) => {
    event.preventDefault()
    addressFormContainer.style.display = 'none'
    openAddressForm.style.display = 'flex'
})

// mở form edit address

const editAddressButton = document.querySelectorAll('.edit-address')
const cancelButtonHeaderEdit = document.querySelectorAll('.cancel-button-header-edit')
const cancelButtonEdit = document.querySelectorAll('.cancel-button-edit')

editAddressButton.forEach((button) => {
    button.addEventListener('click', () => {
        const editForm = button.closest('.saved-address-card-container').querySelector('.edit-form')
        editForm.style.display = 'flex'
        button.closest('.saved-address-card-container').querySelector('.saved-address-content-container').style.display = 'none'
        inputFields.forEach((input) => {
            input.parentElement.querySelector('.input-title').classList.add('active')
        })
        editForm.querySelector('.fullname-input').value = document.querySelector('.fullname').textContent
        editForm.querySelector('.phone-input').value = document.querySelector('.phone').textContent
        editForm.querySelector('.city-input').value = document.querySelector('.city').textContent
        editForm.querySelector('.detail-input').value = document.querySelector('.detail-address').textContent
    })
})

cancelButtonHeaderEdit.forEach((button) => {
    button.addEventListener('click', () => {
        const editForm = button.closest('.edit-form')
        editForm.style.display = 'none'
        editForm.parentElement.querySelector('.saved-address-content-container').style.display = 'flex'
    })
})

cancelButtonEdit.forEach((button) => {
    button.addEventListener('click', (event) => {
        event.preventDefault()
        const editForm = button.closest('.edit-form')
        editForm.style.display = 'none'
        editForm.parentElement.querySelector('.saved-address-content-container').style.display = 'flex'
    })
})

// hiển thị direct tùy vào side menu 

document.addEventListener("DOMContentLoaded", () => {
    const url = window.location.href
    if(url.endsWith("/my-account/orders")) {
        const newDiv1 = document.createElement('div');
        newDiv1.className = 'bread-crumb-item direct';

        const newIcon1 = document.createElement('i');
        newIcon1.className = 'fa-solid fa-chevron-right';

        newDiv1.appendChild(newIcon1);

        const newDiv2 = document.createElement('div');
        newDiv2.className = 'bread-crumb-item';
        newDiv2.textContent = 'My Orders';

        const newA = document.createElement('a')
        newA.textContent = "Account Overview"
        document.querySelector('.back-link').textContent = ""
        newA.setAttribute('href','/my-account')

        document.querySelector('.back-link').appendChild(newA)
        document.querySelector('.bread-crumb-wrapper').appendChild(newDiv1)
        document.querySelector('.bread-crumb-wrapper').appendChild(newDiv2)
    }else if(url.endsWith("/my-account/personal")) {
        const newDiv1 = document.createElement('div');
        newDiv1.className = 'bread-crumb-item direct';

        const newIcon1 = document.createElement('i');
        newIcon1.className = 'fa-solid fa-chevron-right';

        newDiv1.appendChild(newIcon1);

        const newDiv2 = document.createElement('div');
        newDiv2.className = 'bread-crumb-item';
        newDiv2.textContent = 'Personal & Address Details';

        const newA = document.createElement('a')
        newA.textContent = "Account Overview"
        document.querySelector('.back-link').textContent = ""
        newA.setAttribute('href','/my-account')

        document.querySelector('.back-link').appendChild(newA)
        document.querySelector('.bread-crumb-wrapper').appendChild(newDiv1)
        document.querySelector('.bread-crumb-wrapper').appendChild(newDiv2)
    }else if(url.endsWith("/my-account/wishlist")) {
        const newDiv1 = document.createElement('div');
        newDiv1.className = 'bread-crumb-item direct';

        const newIcon1 = document.createElement('i');
        newIcon1.className = 'fa-solid fa-chevron-right';

        newDiv1.appendChild(newIcon1);

        const newDiv2 = document.createElement('div');
        newDiv2.className = 'bread-crumb-item';
        newDiv2.textContent = 'Wish list';

        const newA = document.createElement('a')
        newA.textContent = "Account Overview"
        document.querySelector('.back-link').textContent = ""
        newA.setAttribute('href','/my-account')

        document.querySelector('.back-link').appendChild(newA)
        document.querySelector('.bread-crumb-wrapper').appendChild(newDiv1)
        document.querySelector('.bread-crumb-wrapper').appendChild(newDiv2)
    }
})